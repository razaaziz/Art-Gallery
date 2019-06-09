package com.example.artgallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ItemViewHolder> {
    private Context Context;
    private ArrayList<Items> ExampleList;
    private OnItemClickListener Listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        Listener = listener;
    }
    public Adapter(Context context, ArrayList<Items> exampleList) {
        Context = context;
        ExampleList = exampleList;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Context).inflate(R.layout.items, parent, false);
        return new ItemViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Items currentItem = ExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        int likeCount = currentItem.getLikeCount();

        holder.Creator.setText(creatorName);
        holder.Likes.setText("Likes: " + likeCount);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.ImageView);
    }


    @Override
    public int getItemCount() {
        return ExampleList.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView ImageView;
        public TextView Creator;
        public TextView Likes;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ImageView = itemView.findViewById(R.id.imageView);
            Creator = itemView.findViewById(R.id.creator);
            Likes = itemView.findViewById(R.id.likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Listener.onItemClick(position);

                        }
                    }
                }
            });
        }
    }

}
