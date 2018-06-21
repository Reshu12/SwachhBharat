package com.example.reshugoel.swachhbharat;

/**
 * Created by Reshu Goel on 6/17/2018.
 */

public class SegContent {
    public String image,category,search;

    public SegContent() {
    }

    public SegContent(String image, String category, String search) {
        this.image = image;
        this.category = category;
        this.search = search;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
