package ru.tpu.courses.lab5;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Lab5Activity extends AppCompatActivity {

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab5Activity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab5);
    }
}
