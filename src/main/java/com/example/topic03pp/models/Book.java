package com.example.topic03pp.models;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@ApiModel(value = "BookModel")
public class Book {

    private Integer id;

    @NotNull
    @Size(min = 5, max = 50)
    private String title;

    private String author;
    private String publisher;
    private String thumbnail;

    @NotNull
    private Category category;

    public Book() {
    }

    public Book(Integer id, @NotNull @Size(min = 5, max = 50) String title, String author, String publisher, String thumbnail, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", category=" + category +
                '}';
    }
}
