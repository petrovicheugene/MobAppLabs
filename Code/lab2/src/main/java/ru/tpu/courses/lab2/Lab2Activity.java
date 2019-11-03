package ru.tpu.courses.lab2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lab2Activity extends AppCompatActivity {

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab2Activity.class);
    }

    private Lab2ViewsContainer lab2ViewsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // установка содержимого активности из ресурса макета
        setContentView(R.layout.activity_lab2);
        // установка заголовка экрана
        setTitle(getString(R.string.lab2_app_name));
        // восстанавление состояния экрана, если оно до этого было сохранено
        if (savedInstanceState != null) {
            lab2ViewsContainer.setInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        // сохранение состояние экрана
        lab2ViewsContainer.saveInstanceState(instanceState);
    }
}
