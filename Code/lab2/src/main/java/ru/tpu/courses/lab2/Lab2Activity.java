package ru.tpu.courses.lab2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Lab2Activity extends AppCompatActivity implements OnClickListener {


    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab2Activity.class);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // установка содержимого активности из ресурса макета
        setContentView(R.layout.activity_lab2);
        // установка заголовка экрана
        setTitle(getString(R.string.lab2_app_name));

        // Определение кнопок и установка в них слушателя нажатий Lab2Activity
        Button imgBtn = findViewById(R.id.lab2_imageBtn);
        imgBtn.setOnClickListener(this);

        Button titleBtn = findViewById(R.id.lab2_titleBtn);
        titleBtn.setOnClickListener(this);

        Button subtitleBtn = findViewById(R.id.lab2_subtitleBtn);
        subtitleBtn.setOnClickListener(this);

        // восстанавление состояния экрана, если оно до этого было сохранено
        if (savedInstanceState != null) {
            Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
            // Передаем в контейнер Bundle c сохраненным состоянием
            // и контейнер восстанавливает свое состояние сам
            lab2ViewsContainer.setInstanceState(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        // сохранение состояние экрана
        Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
        // Передаем в контейнер Lab2ViewsContainer Bundle instanceState
        // и свое сотояние контейнер сохраняет сам
        lab2ViewsContainer.saveInstanceState(instanceState);
    }

    @Override
    public void onClick(View v) {
        // обработка нажатий кнопок
        int id = v.getId();
        if(id == R.id.lab2_imageBtn)
        {
            // Установка картинки
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_games);
            Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
            lab2ViewsContainer.setImage(bitmap);
        }
        else if(id == R.id.lab2_titleBtn)
        {
            // Установка заголовка
            Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
            lab2ViewsContainer.setTitle();
        }
        else if(id == R.id.lab2_subtitleBtn)
        {
            // Установка подзаголовка
            Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
            lab2ViewsContainer.setSubtitle();
        }
    }

    protected Lab2ViewsContainer getViewContainer()
    {
        Lab2ViewsContainer viewContainer = findViewById(R.id.container);
        return viewContainer;
    }


}
