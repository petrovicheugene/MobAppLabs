package ru.tpu.courses.lab3;

import androidx.annotation.NonNull;

import java.util.List;

//*******************************************************
class StudentFilter {

    private final StudentsCache studentsCache = StudentsCache.getInstance();
    private String filter = new String();

    //*******************************************************
    public void setFilter(final String filter) {
        // установка фильтра
        this.filter = filter.toLowerCase();
    }

    //*******************************************************
    @NonNull
    public List<Student> getStudents() {
        // получаем полный список студентов из StudentCashe
        List<Student> students = studentsCache.getStudents();
        // если строка фильтра не пустая, удаляем из списка все элементы,
        // которые не содержат строку фильтра
        if (!filter.isEmpty()) {
            for (int i = students.size() - 1; i >= 0; --i) {
                Student student = students.get(i);
                if (student.firstName.toLowerCase().contains(filter) |
                        student.secondName.toLowerCase().contains(filter) |
                        student.lastName.toLowerCase().contains(filter)) {
                    continue;
                }

                students.remove(i);
            }
        }
        return students;
    }
}
