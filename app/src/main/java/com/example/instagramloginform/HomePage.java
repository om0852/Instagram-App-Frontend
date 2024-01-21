package com.example.instagramloginform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;

import storiesclasses.StoriesAdapter;
import storiesclasses.StoriesDec;
import storiesclasses.Story;

public class HomePage extends AppCompatActivity {
    RecyclerView storiesBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        init();
    }
    public void init() {
        storiesBar = findViewById(R.id.storybar);
        List<Story> stories = new ArrayList<>();

//        Picasso.get().load(imageUrl).into(imageView);
        stories.add(new Story(false));
        stories.add(new Story(false));
        stories.add(new Story(false));
        stories.add(new Story(false));
        stories.add(new Story(false));
        stories.add(new Story(false));
        stories.add(new Story(true));

        StoriesAdapter adapter = new StoriesAdapter(stories, this);
        storiesBar.setAdapter(adapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        storiesBar.addItemDecoration(new StoriesDec(10));
    }
}
