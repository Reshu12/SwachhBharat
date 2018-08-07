package com.example.reshugoel.swachhbharat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Vector;

public class Videos extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<Youtubevideos> youtubevideos=new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        recyclerView=(RecyclerView)findViewById(R.id.vid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4Eig7-gVJew\" frameborder=\"0\" allowfullscreen>"));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WIFfequREjo\" frameborder=\"0\" allowfullscreen>"));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zuX9wAOJ9mg\" frameborder=\"0\" allowfullscreen>"));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4Eig7-gVJew\" frameborder=\"0\" allowfullscreen>"));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/WIFfequREjo\" frameborder=\"0\" allowfullscreen>"));
        youtubevideos.add(new Youtubevideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/zuX9wAOJ9mg\" frameborder=\"0\" allowfullscreen>"));

        VideoAdapter videoAdapter=new VideoAdapter(youtubevideos);
        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
