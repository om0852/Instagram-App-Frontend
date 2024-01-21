package com.example.instagramloginform;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        StoryLoader();
    }
    public void StoryLoader() {
        storiesBar = findViewById(R.id.storybar);
        List<Story> stories = new ArrayList<>();
                String imageUrl = "om";

        stories.add(new Story("salunke","https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png",false));
        stories.add(new Story("om","https://media.geeksforgeeks.org/wp-content/cdn-uploads/gfg_200x200-min.png",false));
        stories.add(new Story("sachin","om",false));
        stories.add(new Story("om","om",false));
        stories.add(new Story("om","om",false));
        stories.add(new Story("om","om",false));
        StoriesAdapter adapter = new StoriesAdapter(stories, this);
        storiesBar.setAdapter(adapter);
        storiesBar.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        storiesBar.addItemDecoration(new StoriesDec(10));

    }
}
