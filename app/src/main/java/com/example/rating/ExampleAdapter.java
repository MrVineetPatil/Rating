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
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;
        public TextView mTextViewLine3;
        public TextView mTextViewLine4;
        public TextView mTextViewLine5;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line2);
            mTextViewLine3 = itemView.findViewById(R.id.textview_line3);
            mTextViewLine4 = itemView.findViewById(R.id.textview_line4);
            mTextViewLine5 = itemView.findViewById(R.id.textview_line5);

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

        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}