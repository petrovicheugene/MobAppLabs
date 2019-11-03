package ru.tpu.courses.lab2;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class Lab2ViewsContainer extends GridLayout {
    // Этот конструктор используется при создании View в коде.
    public Lab2ViewsContainer(Context context) {
        this(context, null);
    }

    //Этот конструктор вызывается при создании View из XML.
    public Lab2ViewsContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // Конструктор, вызывается при инфлейте View, когда у View указан дополнительный стиль.
    public Lab2ViewsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // Свои атрибуты описываются в файле res/values/attrs.xml
        // Эта строчка объединяет возможные применённые к View стили
        // TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Lab2ViewsContainer, defStyleAttr, 0);

        // Полученный TypedArray необходимо обязательно очистить.
        //a.recycle();

    }

    //
    public void setInstanceState(Bundle savedInstanceState) {
    }

    public void saveInstanceState(Bundle savedInstanceState) {
    }

    // Метод трансформирует указанные dp в пиксели, используя density экрана.

//    @Px
//    public int dpToPx(float dp) {
//        if (dp == 0) {
//            return 0;
//        }
//        float density = getResources().getDisplayMetrics().density;
//        return (int) Math.ceil(density * dp);
//    }

}
