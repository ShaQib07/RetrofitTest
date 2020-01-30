package com.shakib.retrofittest;

public class Dota2Hero {

    private String name;
    private String role;
    private String image_url;

    public Dota2Hero(String name, String role, String image_url) {
        this.name = name;
        this.role = role;
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImage_url() {
        return image_url;
    }
}
