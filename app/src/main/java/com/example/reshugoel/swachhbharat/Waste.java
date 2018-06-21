package com.example.reshugoel.swachhbharat;

/**
 * Created by Reshu Goel on 6/16/2018.
 */

public class Waste {
    //homepage content class
    private String Name;
    private String Type;
    private int Thumbnail;

    public Waste(){

    }

    public Waste(String name, String type, int thumbnail){
        Name=name;
        Type=type;
        Thumbnail=thumbnail;
    }

    public String getName(){
        return Name;
    }
    public String getType(){
        return Type;
    }
    public int getThumbnail(){
        return Thumbnail;
    }

    public void setName(String name){
        Name=name;
    }
    public void setType(String type){
        Type=type;
    }
    public void setThumbnail(int thumbnail){
        Thumbnail=thumbnail;
    }

}
