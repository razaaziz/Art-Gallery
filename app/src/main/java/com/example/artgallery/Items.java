package com.example.artgallery;

public class Items {
    private String ImageUrl;
    private String Creator;
    private int Likes;

    public Items(String imageUrl, String creator, int likes) {
        ImageUrl = imageUrl;
        Creator = creator;
        Likes = likes;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getCreator() {
        return Creator;
    }

    public int getLikeCount() {
        return Likes;
    }
}
