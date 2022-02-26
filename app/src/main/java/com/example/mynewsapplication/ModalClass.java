package com.example.mynewsapplication;

public class ModalClass {


    private String author,title,discription,url,urlToiImage,publishment;

    public ModalClass(String author, String title, String discription, String url, String urlToiImage, String publishment) {
        this.author = author;
        this.title = title;
        this.discription = discription;
        this.url = url;
        this.urlToiImage = urlToiImage;
        this.publishment = publishment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToiImage() {
        return urlToiImage;
    }

    public void setUrlToiImage(String urlToiImage) {
        this.urlToiImage = urlToiImage;
    }

    public String getPublishment() {
        return publishment;
    }

    public void setPublishment(String publishment) {
        this.publishment = publishment;
    }
}
