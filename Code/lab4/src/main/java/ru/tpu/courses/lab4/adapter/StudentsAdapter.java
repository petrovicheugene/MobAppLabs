package ru.tpu.courses.lab4.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.tpu.courses.lab4.db.Student;

//*******************************************************
public class StudentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_NUMBER = 0;
    public static final int TYPE_STUDENT = 1;

    private List<Student> students = new ArrayList<>();
    // строка для подсветки
    public String highlight = new String();

    //*******************************************************
    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_NUMBER:
                return new NumberHolder(parent);
            case TYPE_STUDENT:
                return new StudentHolder(parent);
        }
        throw new IllegalArgumentException("unknown viewType = " + viewType);
    }

    //*******************************************************
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NUMBER:
                NumberHolder numberHolder = (NumberHolder) holder;
                numberHolder.bind((position + 1) / 2);
                break;
            case TYPE_STUDENT:
                StudentHolder studentHolder = (StudentHolder) holder;
                Student student = students.get(position / 2);

                // метод createStudentString() формирует строку для отображения
                studentHolder.student.setText(createStudentString(student));

                if (!TextUtils.isEmpty(student.photoPath)) {
                    studentHolder.photo.setVisibility(View.VISIBLE);
                    studentHolder.photo.setImageURI(Uri.parse(student.photoPath));
                } else {
                    studentHolder.photo.setVisibility(View.GONE);
                    studentHolder.photo.setImageURI(null);
                }
                break;
        }
    }

    //*******************************************************
    @Override
    public int getItemCount() {
        return students.size() * 2;
    }

    //*******************************************************
    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? TYPE_NUMBER : TYPE_STUDENT;
    }

    //*******************************************************
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //*******************************************************
    // Установка строки для подсвечивания
    public void setHighlight(String highlight) {
        this.highlight = highlight.toLowerCase();
    }

    //*******************************************************
    // Формирование строки для отображения
    private Spannable createStudentString(final Student student) {
        // Строка для отображения
        String studentString = student.lastName + " " + student.firstName + " " + student.secondName;
        // поиск позиций вхождения строки highlight
        List<Integer> entries = findHighLightEntries(studentString);
        // устанавливаем подсветку во все позиции строки
        Spannable highlightedString = new SpannableString(studentString);
        for (int i = 0; i < entries.size(); ++i) {
            highlightedString.setSpan(new ForegroundColorSpan(Color.GREEN),
                    entries.get(i),
                    entries.get(i) + highlight.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return highlightedString;
    }

    //*******************************************************
    private List<Integer> findHighLightEntries(final String string) {
        // приводим исходную строку к нижнему регистру
        String lowerString = string.toLowerCase();
        // список позиций вхождения
        List<Integer> entries = new ArrayList<>();

        // объект класса Matcher представляет исходную строку
        // и производит поиск в ней вхождения регулярного выражения
        Matcher m = Pattern.compile("(?=(" + highlight + "))").matcher(lowerString);
        while (m.find()) {
            // найденная позиция помещается в список
            entries.add(m.start());
        }

        return entries;
    }
}
