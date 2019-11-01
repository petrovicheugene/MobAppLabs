package ru.tpu.courses.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.tpu.courses.lab1.Lab1Activity;
import ru.tpu.courses.lab2.Lab2Activity;
import ru.tpu.courses.lab3.Lab3Activity;
import ru.tpu.courses.lab4.Lab4Activity;
import ru.tpu.courses.lab5.Lab5Activity;
import ru.tpu.courses.lab6.Lab6Activity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.lab1).setOnClickListener((v) -> {
            startActivity(Lab1Activity.newIntent(this));
        });

        findViewById(R.id.lab2).setOnClickListener((v) -> startActivity(Lab2Activity.newIntent(this)));
        findViewById(R.id.lab3).setOnClickListener((v) -> startActivity(Lab3Activity.newIntent(this)));
        findViewById(R.id.lab4).setOnClickListener((v) -> startActivity(Lab4Activity.newIntent(this)));
        findViewById(R.id.lab5).setOnClickListener((v) -> startActivity(Lab5Activity.newIntent(this)));
        findViewById(R.id.lab6).setOnClickListener((v) -> startActivity(Lab6Activity.newIntent(this)));


    }
}
