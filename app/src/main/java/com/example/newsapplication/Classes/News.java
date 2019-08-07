package com.example.newsapplication.Classes;

public class News {

    private String newTitle;
    private String newPhotoUrl;
    private String newDescription;
    private String newUrl;
    private String newAuthor;
    private String publishedAt;

    public String getPublishedAt()
    {
        return publishedAt;
    }
    public String getNewTitle()
    {
        return newTitle;
    }
    public String getNewAuthor()
    {
        return newAuthor;
    }

    public String getNewPhotoUrl()
    {
        return newPhotoUrl;
    }

    public String getNewUrl()
    {
        return newUrl;
    }

    public String getNewDescription()
    {
        return newDescription;
    }

    public void setNewPhotoUrl(String newPhotoUrl) {
        this.newPhotoUrl = newPhotoUrl;
    }

    public News(String newTitle, String newPhotoUrl, String newDescription , String newUrl, String newAuthor, String publishedAt)
    {
        this.publishedAt=publishedAt;
        this.newAuthor=newAuthor;
        this.newUrl=newUrl;
        this.newTitle = newTitle;
        this.newPhotoUrl = newPhotoUrl;
        this.newDescription = newDescription;
    }
}
