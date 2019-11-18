package ru.tpu.courses.lab4.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Fts4
@Entity
public class Student implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    @Ignore
    private int id;
    @NonNull
    @ColumnInfo(name = "first_name")
    public final String firstName;
    @NonNull
    @ColumnInfo(name = "second_name")
    public final String secondName;
    @NonNull
    @ColumnInfo(name = "last_name")
    public final String lastName;
    @Nullable
    @ColumnInfo(name = "photo_path")
    public final String photoPath;

    public Student(
            @NonNull String firstName,
            @NonNull String secondName,
            @NonNull String lastName,
            @Nullable String photoPath
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.photoPath = photoPath;
    }

    Student(Parcel in) {
        id = in.readInt();
        firstName = Objects.requireNonNull(in.readString());
        secondName = Objects.requireNonNull(in.readString());
        lastName = Objects.requireNonNull(in.readString());
        photoPath = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(secondName);
        dest.writeString(lastName);
        dest.writeString(photoPath);
    }
}

