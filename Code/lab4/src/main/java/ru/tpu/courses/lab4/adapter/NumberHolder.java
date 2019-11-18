package ru.tpu.courses.lab4.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.tpu.courses.lab4.R;

class NumberHolder extends RecyclerView.ViewHolder {

	private final TextView number;

	public NumberHolder(ViewGroup parent) {
		super(LayoutInflater.from(parent.getContext()).inflate(R.layout.lab4_item_number, parent, false));
		number = itemView.findViewById(R.id.number);
	}

	public void bind(int studentIndex) {
		number.setText((studentIndex + 1) + ")");
	}
}
