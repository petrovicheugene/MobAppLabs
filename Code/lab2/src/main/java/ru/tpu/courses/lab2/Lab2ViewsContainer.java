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

        // создание imageView для отображения картинки
        imageView = new ImageView(getContext());
        // генерируем и присваиваем id
        imageView.setId(View.generateViewId());
        // установка пустого bitmap
        Bitmap bitmap = null;
        imageView.setImageBitmap(bitmap);
        imageView.setVisibility(View.GONE);
        addView(imageView);

        // создание textView для отображения заголовка
        titleView = new TextView(getContext());
        titleView.setId(View.generateViewId());

        titleView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        titleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.lab2_title_size));
        titleView.setText(getResources().getString(R.string.lab2_title));
        addView(titleView);

        subtitleView = new TextView(getContext());
        subtitleView.setId(View.generateViewId());
        subtitleView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        subtitleView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        subtitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.lab2_subtitle_size));
        subtitleView.setText(getResources().getString(R.string.lab2_subtitle));
        addView(subtitleView);

        checkBox = new CheckBox(getContext());
        checkBox.setId(View.generateViewId());
        addView(checkBox);

        // установка всем view "слушателя кликов" - класса Lab2ViewsContainer
        // для переключения checkBox при клике по любому месту всего объекта класса Lab2ViewsContainer
        imageView.setOnClickListener(this);
        titleView.setOnClickListener(this);
        subtitleView.setOnClickListener(this);
        setOnClickListener(this);

        // размещение всех view на макете
        adjustViews();
    }

    //****************************************************
    @Override
    public void onClick(View v) {
        // переключение checkBox на противоположное
        checkBox.setChecked(!checkBox.isChecked());
    }

    //****************************************************
    public void setInstanceState(@NonNull Bundle savedInstanceState) {
        // восстановление состояний всех view
        Bitmap bitmap = savedInstanceState.getParcelable(getResources().getString(R.string.lab2_image));
        setImage(bitmap);
        setTitle(savedInstanceState.getString(getResources().getString(R.string.lab2_title)));
        setSubtitle(savedInstanceState.getString(getResources().getString(R.string.lab2_subtitle)));
        checkBox.setChecked(savedInstanceState.getBoolean(getResources().getString(R.string.lab2_checkbox)));
        adjustViews();
    }

    //****************************************************
    public void saveInstanceState(@NonNull Bundle outState) {
        // сохранение состояний всех view
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
        // набор настроек для макета СonstraintLayout
        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        // imageView
        // привязка в левый верхний угол макета
        set.connect(imageView.getId(), ConstraintSet.LEFT,
                R.id.container, ConstraintSet.LEFT);
        set.connect(imageView.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP);

        // checkBox
        // привязка в правый верхний угол макета
        // с отступами по вертикали и горизонтали
        // значения отступов - в ресурсах dimens
        set.connect(checkBox.getId(), ConstraintSet.RIGHT,
                R.id.container, ConstraintSet.RIGHT,
                getResources().getDimensionPixelOffset(R.dimen.lab2_checkBox_h_margin));
        set.connect(checkBox.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP,
                getResources().getDimensionPixelOffset(R.dimen.lab2_checkBox_v_margin));

        // titleView
        // привязка левого края к правому краю картинки
        // правого края к checkBox
        // в ширину titleView занимает всё свободное место от картинки до checkBox
        set.connect(titleView.getId(), ConstraintSet.LEFT,
                imageView.getId(), ConstraintSet.RIGHT, padding);
        set.connect(titleView.getId(), ConstraintSet.TOP,
                R.id.container, ConstraintSet.TOP);
        set.connect(titleView.getId(), ConstraintSet.RIGHT,
                checkBox.getId(), ConstraintSet.LEFT, padding);
        set.constrainWidth(titleView.getId(), ConstraintSet.MATCH_CONSTRAINT);

        // subtitle view
        // привязка левого края к правому краю картинки
        // правого края к checkBox
        // в ширину titleView занимает всё свободное место от картинки до checkBox
        set.connect(subtitleView.getId(), ConstraintSet.LEFT,
                imageView.getId(), ConstraintSet.RIGHT, padding);
        set.connect(subtitleView.getId(), ConstraintSet.RIGHT,
                checkBox.getId(), ConstraintSet.LEFT, padding);
        set.constrainWidth(subtitleView.getId(), ConstraintSet.MATCH_CONSTRAINT);

        // применение настроек к макету
        set.applyTo(this);
        adjustSubtitleView();
    }

    //****************************************************
    protected void adjustSubtitleView() {
        // набор настроек для макета СonstraintLayout
        ConstraintSet set = new ConstraintSet();
        set.clone(this);

        if ((titleView.getVisibility() == View.GONE) &
                (subtitleView.getVisibility() != View.GONE)) {
            // привязка центра textView подзаголовка к центру контейнера по вертикали
            set.clear(subtitleView.getId(), ConstraintSet.TOP);
            set.clear(subtitleView.getId(), ConstraintSet.BOTTOM);
            set.connect(subtitleView.getId(), ConstraintSet.TOP,
                    R.id.container, ConstraintSet.TOP);
            set.connect(subtitleView.getId(), ConstraintSet.BOTTOM,
                    R.id.container, ConstraintSet.BOTTOM);
        } else {
            // привязка верхнего края textView подзаголовка к нижнему краю textView заголовка
            set.clear(subtitleView.getId(), ConstraintSet.TOP);
            set.clear(subtitleView.getId(), ConstraintSet.BOTTOM);
            set.connect(subtitleView.getId(), ConstraintSet.TOP,
                    titleView.getId(), ConstraintSet.BOTTOM);
        }
        // применение настроек к макету
        set.applyTo(this);
    }

    //****************************************************
    // установка картинки
    public void setImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        if (bitmap == null) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
        adjustSubtitleView();
    }

    //****************************************************
    // установка заголовка
    public void setTitle(String title) {
        if (titleView.getText().toString() != title) {
            titleView.setText(title);
            if (title.isEmpty()) {
                // при пустой строке titleView удаляется с макета и не занимает места
                titleView.setVisibility(View.GONE);
            } else {
                titleView.setVisibility(View.VISIBLE);
            }
            adjustSubtitleView();
        }
    }

    //****************************************************
    // установка подзаголовка
    public void setSubtitle(String subtitle) {
        if (subtitleView.getText().toString() != subtitle) {
            subtitleView.setText(subtitle);
            if (subtitle.isEmpty()) {
                // при пустой строке subtitleView удаляется с макета и не занимает места
                subtitleView.setVisibility(View.GONE);
            } else {
                subtitleView.setVisibility(View.VISIBLE);
            }
            adjustSubtitleView();
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
