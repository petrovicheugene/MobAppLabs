package ru.tpu.courses.lab2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

//****************************************************
public class Lab2ViewsContainer extends ConstraintLayout implements OnClickListener {

    ImageView imageView;
    TextView titleView;
    TextView subtitleView;
    CheckBox checkBox;
    //****************************************************
    // Этот конструктор используется при создании View в коде.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context) {
        this(context, null);
    }
    //****************************************************
    //Этот конструктор вызывается при создании View из XML.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    //****************************************************
    // Конструктор, вызывается при инфлейте View, когда у View указан дополнительный стиль.
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public Lab2ViewsContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // Свои атрибуты описываются в файле res/values/attrs.xml
        // Эта строчка объединяет возможные применённые к View стили
         TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Lab2ViewsContainer, defStyleAttr, 0);

        // Полученный TypedArray необходимо обязательно очистить.
        a.recycle();

        //
        imageView = new ImageView(getContext());
        imageView.setId(View.generateViewId());
        Bitmap bitmap = null;
        imageView.setImageBitmap(bitmap);
        addView(imageView);

        titleView = new TextView(getContext());
        titleView.setId(View.generateViewId());
        titleView.setGravity(Gravity.LEFT | Gravity.CENTER);
        titleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.lab2_title_size));
        titleView.setText(getResources().getString(R.string.lab2_title));
        addView(titleView);

        subtitleView = new TextView(getContext());
        subtitleView.setId(View.generateViewId());
        subtitleView.setGravity(Gravity.LEFT | Gravity.CENTER);
        subtitleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        subtitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.lab2_subtitle_size));
        subtitleView.setText(getResources().getString(R.string.lab2_subtitle));
        addView(subtitleView);

        checkBox = new CheckBox(getContext());
        checkBox.setId(View.generateViewId());
        addView(checkBox);

        // 
        imageView.setOnClickListener(this);
        titleView.setOnClickListener(this);
        subtitleView.setOnClickListener(this);
        setOnClickListener(this);

        adjustViews();
    }
    //****************************************************
    @Override
    public void onClick(View v) {
        //
        checkBox.setChecked(!checkBox.isChecked());
    }
    //****************************************************
    public void setInstanceState(@NonNull Bundle savedInstanceState) {
        Bitmap bitmap = savedInstanceState.getParcelable(getResources().getString(R.string.lab2_image));
        imageView.setImageBitmap(bitmap);
        titleView.setText(savedInstanceState.getString(getResources().getString(R.string.lab2_title)));
        subtitleView.setText(savedInstanceState.getString(getResources().getString(R.string.lab2_subtitle)));
        checkBox.setChecked(savedInstanceState.getBoolean(getResources().getString(R.string.lab2_checkbox)));
        adjustViews();
    }

    //****************************************************
    public void saveInstanceState(@NonNull Bundle outState) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        outState.putParcelable(getResources().getString(R.string.lab2_image), bitmap);
        outState.putCharSequence(getResources().getString(R.string.lab2_title), titleView.getText());
        outState.putCharSequence(getResources().getString(R.string.lab2_subtitle), subtitleView.getText());
        outState.putBoolean(getResources().getString(R.string.lab2_checkbox), checkBox.isChecked());
    }

    //****************************************************
    public void adjustViews() {
        // горизонтальное расстояние между view
        int padding = getResources().getDimensionPixelOffset(R.dimen.lab2_view_padding);
        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        // imageView
        set.connect(imageView.getId(), ConstraintSet.LEFT,
                R.id.container, ConstraintSet.LEFT);
        set.connect(imageView.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP);

        // checkBox
        set.connect(checkBox.getId(), ConstraintSet.RIGHT,
                R.id.container, ConstraintSet.RIGHT,
                getResources().getDimensionPixelOffset(R.dimen.lab2_checkBox_h_margin));
        set.connect(checkBox.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP,
                getResources().getDimensionPixelOffset(R.dimen.lab2_checkBox_v_margin));

        // titleView
        set.connect(titleView.getId(), ConstraintSet.LEFT,
                imageView.getId(), ConstraintSet.RIGHT, padding);
        set.connect(titleView.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP);
        set.connect(titleView.getId(), ConstraintSet.RIGHT,
                checkBox.getId(), ConstraintSet.LEFT, padding);
        set.constrainWidth(titleView.getId(), ConstraintSet.MATCH_CONSTRAINT);

        // subtitle view
        set.connect(subtitleView.getId(), ConstraintSet.LEFT,
                imageView.getId(), ConstraintSet.RIGHT, padding);
        set.connect(subtitleView.getId(), ConstraintSet.TOP,
                titleView.getId(), ConstraintSet.BOTTOM);
        set.connect(subtitleView.getId(), ConstraintSet.RIGHT,
                checkBox.getId(), ConstraintSet.LEFT, padding);
        set.constrainWidth(subtitleView.getId(), ConstraintSet.MATCH_CONSTRAINT);

        set.applyTo(this);
    }

    //****************************************************
    // установка картинки
    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    //****************************************************
    // установка заголовка
    public void setTitle(String title) {
        titleView.setText(title);
        if (title.isEmpty()) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.VISIBLE);
        }
    }

    //****************************************************
    // установка подзаголовка
    public void setSubtitle(String subtitle) {
        subtitleView.setText(subtitle);
        if (subtitle.isEmpty()) {
            subtitleView.setVisibility(View.GONE);
        } else {
            subtitleView.setVisibility(View.VISIBLE);
        }
    }
    //****************************************************
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
