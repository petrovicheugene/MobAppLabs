package ru.tpu.courses.lab3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;

import java.util.Objects;

public class Student implements Parcelable {

	@NonNull
	public final String firstName;
	@NonNull
	public final String secondName;
	@NonNull
	public final String lastName;

	public Student(@NonNull String firstName, @NonNull String secondName, @NonNull String lastName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	private Student(Parcel in) {
		firstName = Objects.requireNonNull(in.readString());
		lastName = Objects.requireNonNull(in.readString());
		secondName = Objects.requireNonNull(in.readString());
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
		dest.writeString(firstName);
		dest.writeString(lastName);
		dest.writeString(secondName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Student)) return false;
		Student student = (Student) o;
		return lastName.equals(student.lastName) &&
				firstName.equals(student.firstName) &&
				secondName.equals(student.secondName);
	}

	@Override
	public int hashCode() {
		return ObjectsCompat.hash(lastName, firstName, secondName);
	}
}
