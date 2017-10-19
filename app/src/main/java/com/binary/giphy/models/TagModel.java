package com.binary.giphy.models;

/**
 * Created by duong on 10/19/2017.
 */

public class TagModel {
    String name;
    String urlPhoto;

    public TagModel(String name, String urlPhoto) {
        this.name = name;
        this.urlPhoto = urlPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}

