package com.example.prema.prema.Model;

import java.io.Serializable;

/**
 * Created by prema on 27/8/17.
 */
public class Contacts implements Serializable {
    private static final long id = 1L;
    private String name;
    private String image;

    public Contacts(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
