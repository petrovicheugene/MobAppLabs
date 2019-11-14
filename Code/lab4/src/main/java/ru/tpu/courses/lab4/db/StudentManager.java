package ru.tpu.courses.lab4.db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

//*******************************************************
public class StudentManager {
    private final StudentDao studentDao;
    private String filter = new String();

    public StudentManager(AppCompatActivity activity) {
        studentDao = Lab4Database.getInstance(activity).studentDao();
    }

    //*******************************************************
    public void setFilter(final String filter) {
        // установка фильтра
        this.filter = filter.toLowerCase();
    }

    //*******************************************************
    public void insert(Student student) {
        studentDao.insert(student);
    }

    //*******************************************************
//    @NonNull
//    public List<Student> getStudents1() {
//        // получаем полный список студентов из StudentCashe
//        List<Student> students = studentDao.getAll();
//        // если строка фильтра не пустая, удаляем из списка все элементы,
//        // которые не содержат строку фильтра
//        if (!filter.isEmpty()) {
//            for (int i = students.size() - 1; i >= 0; --i) {
//                Student student = students.get(i);
//                if (student.firstName.toLowerCase().contains(filter) |
//                        student.secondName.toLowerCase().contains(filter) |
//                        student.lastName.toLowerCase().contains(filter)) {
//                    continue;
//                }
//
//                students.remove(i);
//            }
//        }
//        return students;
//    }
    //*******************************************************
    public List<Student> getStudents() {
        if(filter.isEmpty())
        {
            return studentDao.getAll();
        }
        else
        {
            return studentDao.getFiltered(filter);
        }

//        // получаем список студентов из StudentCashe
//        List<Student> students = studentDao.getAll();
//        // если строка фильтра не пустая, удаляем из списка все элементы,
//        // которые не содержат строку фильтра
//        if (!filter.isEmpty()) {
//            for (int i = students.size() - 1; i >= 0; --i) {
//                Student student = students.get(i);
//                if (student.firstName.toLowerCase().contains(filter) |
//                        student.secondName.toLowerCase().contains(filter) |
//                        student.lastName.toLowerCase().contains(filter)) {
//                    continue;
//                }
//
//                students.remove(i);
//            }
//        }
//        return students;
    }
}
