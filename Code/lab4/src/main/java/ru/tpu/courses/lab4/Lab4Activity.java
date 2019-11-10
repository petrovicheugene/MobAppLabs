package ru.tpu.courses.lab4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ru.tpu.courses.lab4.adapter.StudentsAdapter;
import ru.tpu.courses.lab4.add.AddStudentActivity;
import ru.tpu.courses.lab4.db.Lab4Database;
import ru.tpu.courses.lab4.db.Student;
import ru.tpu.courses.lab4.db.StudentDao;
import ru.tpu.courses.lab4.db.StudentManager;

/**
 * <b>RecyclerView, взаимодействие между экранами. Memory Cache.</b>
 * <p>
 * Ознакомиться с примером в модуле lab4.
 * Все задания подразумевают продолжение выполнение задания из Лабораторной работы №3.
 * Вариант №1
 * Организовать поиск через БД. Индексировать ФИО студента через отдельную виртуальную таблицу.
 */
public class Lab4Activity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final int REQUEST_STUDENT_ADD = 1;

    //*******************************************************
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab4Activity.class);
    }

    //*******************************************************
    //private StudentDao studentDao;
    private RecyclerView list;
    private FloatingActionButton fab;
    private StudentsAdapter studentsAdapter;
    private SearchView filter;
    private StudentManager studentManager;
    //*******************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //studentDao = Lab4Database.getInstance(this).studentDao();

        setTitle(getString(R.string.lab4_title, getClass().getSimpleName()));

        setContentView(R.layout.lab4_activity);
        list = findViewById(android.R.id.list);
        fab = findViewById(R.id.fab);
        studentManager = new StudentManager(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);

        list.setAdapter(studentsAdapter = new StudentsAdapter());
        studentsAdapter.setStudents(studentManager.getStudents());

        fab.setOnClickListener(
                v -> startActivityForResult(
                        AddStudentActivity.newIntent(this),
                        REQUEST_STUDENT_ADD
                )
        );

        // Установка "слушателя" SearchView
        filter = findViewById(R.id.filterView);
        filter.setOnQueryTextListener(this);
    }

    //*******************************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_STUDENT_ADD && resultCode == RESULT_OK) {
            Student student = AddStudentActivity.getResultStudent(data);
            studentManager.insert(student);
            //studentDao.insert(student);
//            studentsAdapter.setStudents(studentDao.getAll());
//            studentsAdapter.notifyItemRangeInserted(studentsAdapter.getItemCount() - 2, 2);
//            list.scrollToPosition(studentsAdapter.getItemCount() - 1);
            updateAdapterData();
        }
    }

    //*******************************************************
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    //*******************************************************
    @Override
    public boolean onQueryTextChange(String newText) {
        studentManager.setFilter(newText);
        studentsAdapter.setHighlight(newText);
        updateAdapterData();
        return false;
    }

    //*******************************************************
    private void updateAdapterData() {
        studentsAdapter.setStudents(studentManager.getStudents());
        studentsAdapter.notifyDataSetChanged();
        // studentsAdapter.notifyItemRangeInserted(studentsAdapter.getItemCount() - 2, 2);
        list.scrollToPosition(studentsAdapter.getItemCount() - 1);    }
}
