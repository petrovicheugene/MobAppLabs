package ru.tpu.courses.lab2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;


public class Lab2ViewsContainer extends LinearLayout {

    ImageView imageView;
    TextView titleView;
    TextView subtitleView;
    CheckBox checkBox;
    int currentImageId = 0;

    // Этот конструктор используется при создании View в коде.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context) {
        this(context, null);
    }

    //Этот конструктор вызывается при создании View из XML.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // Конструктор, вызывается при инфлейте View, когда у View указан дополнительный стиль.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        // Свои атрибуты описываются в файле res/values/attrs.xml
        // Эта строчка объединяет возможные применённые к View стили
        // TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Lab2ViewsContainer, defStyleAttr, 0);

        // Полученный TypedArray необходимо обязательно очистить.
        //a.recycle();

        //

        // LayoutParams lpView = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        imageView = new ImageView(getContext());
        addView(imageView);

        titleView = new TextView(getContext());
        titleView.setText("titleView");
        addView(titleView);

        subtitleView = new TextView(getContext());
        subtitleView.setText("subtitleView");
        addView(subtitleView);

        checkBox = new CheckBox(getContext());
        addView(checkBox);

        adjustViews();
    }

    //
    public void setInstanceState(@NonNull Bundle savedInstanceState) {
        setImage(savedInstanceState.getInt("imageId"));
        titleView.setText(savedInstanceState.getString("title"));
        subtitleView.setText(savedInstanceState.getString("subtitle"));
        checkBox.setChecked(savedInstanceState.getBoolean("checked"));
        adjustViews();
    }

    public void saveInstanceState(@NonNull Bundle instanceState) {
        instanceState.putInt("imageId", currentImageId);
        instanceState.putCharSequence("title", titleView.getText());
        instanceState.putCharSequence("subtitle", subtitleView.getText());
        instanceState.putBoolean("checked", checkBox.isChecked());
    }

    public void adjustViews() {
//        ConstraintSet set = new ConstraintSet();
//        set.clone(this);
//
//        set.applyTo(this);
    }

    protected void setImage(int imageId) {


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
