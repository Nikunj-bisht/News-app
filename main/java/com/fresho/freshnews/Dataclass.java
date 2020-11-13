package com.fresho.freshnews;

import java.io.Serializable;

public class Dataclass implements Serializable {

    private String name;
    private String author;
    private String title;
    private String description;
    private String url;
    private String imageurl;
    private String publish;
    private String content;

    public Dataclass(String name, String author, String title, String description, String url, String imageurl, String publish, String content) {
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageurl = imageurl;
        this.publish = publish;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getPublish() {
        return publish;
    }

    public String getContent() {
        return content;
    }
}
