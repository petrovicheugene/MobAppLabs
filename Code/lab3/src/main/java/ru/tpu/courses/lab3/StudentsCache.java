package ru.tpu.courses.lab3;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class StudentsCache {

    private static StudentsCache instance;

    public static StudentsCache getInstance() {
        if (instance == null) {
            synchronized (StudentsCache.class) {
                if (instance == null) {
                    instance = new StudentsCache();
                }
            }
        }
        return instance;
    }

    private final Set<Student> students = new LinkedHashSet<>();

    private StudentsCache() {
    }
    //*******************************************************
    // перегрузка метода для фильтрации
    public List<Student> getStudents(String filter) {

        if (filter.isEmpty()) {
            return getStudents();
        }

        List<Student> filteredStudents = new ArrayList<>();
        Iterator<Student> iterator = this.students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.firstName.toLowerCase().contains(filter) |
                    student.secondName.toLowerCase().contains(filter) |
                    student.lastName.toLowerCase().contains(filter)) {
                filteredStudents.add(student);
            }

        }
        return filteredStudents;
    }
    //*******************************************************
    @NonNull
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    //*******************************************************
    public void addStudent(@NonNull Student student) {
        students.add(student);
    }
    //*******************************************************
    public boolean contains(@NonNull Student student) {
        return students.contains(student);
    }
}
