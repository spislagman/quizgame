package com.example.quizgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterChoices extends RecyclerView.Adapter<AdapterChoices.ViewHolder>{

    private String[] mDataset = new String[4];
    private RecyclerViewListnerInterface mListenr = null;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ViewHolder(View v){
            super(v);
            mTextView = (TextView)v.findViewById(R.id.textViewChoice);
        }
    }

    AdapterChoices(String[] dataset){
        mDataset = dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_choice, parent,false);

        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                if(mListenr != null) {
                    mListenr.click_item(position);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public void setListener(RecyclerViewListnerInterface listener){
        this.mListenr = listener;
    }

    public void RemoveListener(){
        this.mListenr = null;
    }
}
