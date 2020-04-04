package com.example.rating;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<exampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView Date;
        public TextView Time;
        public TextView Min;
        public TextView Max;
        public TextView Rating;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.textview_Date);
            Time = itemView.findViewById(R.id.textview_Time);
            Min = itemView.findViewById(R.id.textview_Min);
            Max = itemView.findViewById(R.id.textview_Max);
            Rating = itemView.findViewById(R.id.textview_Rating);

        }
    }

    public ExampleAdapter(ArrayList<exampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        exampleItem currentItem = mExampleList.get(position);

        holder.Date.setText(currentItem.getLine1());
        holder.Time.setText(currentItem.getLine2());
        holder.Min.setText(currentItem.getLine3());
        holder.Max.setText(currentItem.getLine4());
        holder.Rating.setText(currentItem.getLine5());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}