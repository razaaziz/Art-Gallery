package com.example.artgallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.artgallery.HomeFragPage1.EXTRA_CREATOR;
import static com.example.artgallery.HomeFragPage1.EXTRA_LIKES;
import static com.example.artgallery.HomeFragPage1.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        int likeCount = intent.getIntExtra(EXTRA_LIKES, 0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView creator = findViewById(R.id.creator_detail);
        TextView likes = findViewById(R.id.like_detail);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        creator.setText(creatorName);
        likes.setText("Likes: " + likeCount);
    }
}
