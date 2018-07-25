package com.example.reshugoel.swachhbharat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.List;

/**
 * Created by Reshu Goel on 7/25/2018.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<Youtubevideos> youtubevideos;

    public VideoAdapter() {
    }

    public VideoAdapter(List<Youtubevideos> youtubevideos) {
        this.youtubevideos = youtubevideos;
    }

    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card_view,parent,false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.VideoViewHolder holder, int position) {

        holder.videoWeb.loadData(youtubevideos.get(position).getVideourl(),"text/html","utf-8");
    }

    @Override
    public int getItemCount() {
        return youtubevideos.size();
    }
    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoWeb;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoWeb=(WebView)itemView.findViewById(R.id.we);
            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebViewClient(new WebViewClient(){

            });
            videoWeb.setWebChromeClient(new WebChromeClient() {

            });

        }
    }
}
