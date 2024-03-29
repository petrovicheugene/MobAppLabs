package ru.tpu.courses.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.tpu.courses.lab1.Lab1Activity;
import ru.tpu.courses.lab2.Lab2Activity;
import ru.tpu.courses.lab3.Lab3Activity;
import ru.tpu.courses.lab4.Lab4Activity;
import ru.tpu.courses.lab5.Lab5Activity;
import ru.tpu.courses.lab6.Lab6Activity;

// * Задания на [Wiki](https://github.com/ekzee/tpu-android-courses/wiki)
// * Темы лабораторных работ:
//         * <ul>
//        * <li>Знакомство с Android Studio и Git</li>
//        * <li>Вёрстка UI. Сохранение состояния</li>
//        * <li>RecyclerView, взаимодействие между экранами. Memory Cache</li>
//        * <li>Взаимодействие с файловой системой, SQLite</li>
//        * <li>Запросы к сети, многопоточность</li>
//        * <li>Render Loop, Canvas, анимации</li>
//        * </ul>



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.lab1).setOnClickListener((v) -> startActivity(Lab1Activity.newIntent(this)));

        findViewById(R.id.lab2).setOnClickListener((v) -> startActivity(Lab2Activity.newIntent(this)));
        findViewById(R.id.lab3).setOnClickListener((v) -> startActivity(Lab3Activity.newIntent(this)));
        findViewById(R.id.lab4).setOnClickListener((v) -> startActivity(Lab4Activity.newIntent(this)));
        findViewById(R.id.lab5).setOnClickListener((v) -> startActivity(Lab5Activity.newIntent(this)));
        findViewById(R.id.lab6).setOnClickListener((v) -> startActivity(Lab6Activity.newIntent(this)));


    }
}
// Tasks: https://github.com/ekzee/tpu-android-courses/wiki