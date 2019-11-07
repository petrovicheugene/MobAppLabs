package ru.tpu.courses.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//****************************************************
public class Lab2Activity extends AppCompatActivity implements OnClickListener {
    //****************************************************
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, Lab2Activity.class);
    }

    //****************************************************
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

    //****************************************************
    @Override
    protected void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        // сохранение состояние экрана
        Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
        // Передаем в контейнер Lab2ViewsContainer Bundle instanceState
        // и свое сотояние контейнер сохраняет сам
        lab2ViewsContainer.saveInstanceState(instanceState);
    }

    //****************************************************
    @Override
    public void onClick(View v) {
        // обработка нажатий кнопок
        int id = v.getId();
        if (id == R.id.lab2_imageBtn) {
            // Запуск диалога для установки картинки
            getPictureAndSetToView();
        } else if (id == R.id.lab2_titleBtn) {
            // Запуск диалога для записи заголовка
            getUserStringAndWriteToView(R.string.lab2_title);
        } else if (id == R.id.lab2_subtitleBtn) {
            // Запуск диалога для записи подзаголовка
            getUserStringAndWriteToView(R.string.lab2_subtitle);
        }
    }

    //****************************************************
    protected Lab2ViewsContainer getViewContainer() {
        Lab2ViewsContainer viewContainer = findViewById(R.id.container);
        return viewContainer;
    }

    //****************************************************
    protected void getUserStringAndWriteToView(int strId) {
        // Создание и настройка диалога ввода текста
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(strId));

        // Создание поля для ввода текста
        EditText input = new EditText(this);

        // настройка типа вводимого текста
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        // установка в диалог
        builder.setView(input);

        // Установка кнопки ОК и обработчик ее нажатия
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Получаем введенную строку и записываем в контейнер
                // strId - для определения куда записывать строку - в заголовок или подзаголовок
                Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
                if (strId == R.string.lab2_title) {
                    lab2ViewsContainer.setTitle(input.getText().toString());
                } else if (strId == R.string.lab2_subtitle) {
                    lab2ViewsContainer.setSubtitle(input.getText().toString());
                }
            }
        });
        // Установка кнопки Cancel и обработчик ее нажатия
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // старт диалога
        builder.show();
    }

    //****************************************************
    protected void getPictureAndSetToView() {
        // R.drawable.class.getField("name_of_the_resource").getInt(getResources())

        // Создание и настройка диалога ввода текста
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.lab2_image));

        // Создание списка картинок
        final String[] pictureName = {"None", "Andriod", "GPS", "Teacher"};

        // Установка списка картинок и обработчик нажатия
        builder.setItems(pictureName, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                // Получаем введенную строку и загружаем картинку в контейнер
                Bitmap bitmap;
                bitmap = null;
                switch (item) {
                    case 0:
                        // пустой битмап
                        break;
                    case 1: // Andriod
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.android_games);
                        break;
                    case 2: // GPS
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.gps);
                        break;
                    case 3: // Teacher
                        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.teacher);
                        break;

                }
                Lab2ViewsContainer lab2ViewsContainer = getViewContainer();
                lab2ViewsContainer.setImage(bitmap);
            }
        });

        // Установка кнопки Cancel и обработчик ее нажатия
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // старт диалога
        builder.show();
    }
}
