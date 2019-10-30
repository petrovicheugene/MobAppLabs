package ru.tpu.courses.lab1;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Lab1Activity extends AppCompatActivity {

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab1Activity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
    }
}
