package com.nemestniy.admin.picturestream;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nemestniy.admin.picturestream.API.ExampleResponse;
import com.nemestniy.admin.picturestream.API.OnBottomReachedListener;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    ArrayList<ExampleResponse> items = new ArrayList<>();

    OnBottomReachedListener onBottomReachedListener;

    public void addAll(ArrayList<ExampleResponse> _items){
        int pos = getItemCount();
        this.items.addAll(_items);
        notifyItemRangeInserted(pos, this.items.size());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        if (position == items.size() - 1){

            onBottomReachedListener.onBottomReached(position);

        }
        holder.bind(items.get(position));
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
        onBottomReachedListener.onBottomReached(0);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
