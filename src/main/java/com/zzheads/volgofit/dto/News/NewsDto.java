package com.zzheads.volgofit.dto.News;//

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.util.DateConverter;

import java.util.Collection;
import java.util.stream.Collectors;

//  created by zzheads on 14.02.17
//
public class NewsDto {
    private Long id;
    private String date;
    private String text;
    private String author;
    private String imagePath;
    private String[] hashTags;

    public NewsDto(News news) {
        this.id = news.getId();
        this.date = DateConverter.dateToString(news.getDate(), false);
        this.text = news.getText();
        this.author = news.getAuthor();
        this.imagePath = news.getImagePath();
        this.hashTags = news.getHashTags().toArray(new String[news.getHashTags().size()]);
    }

    public static Collection<NewsDto> toDto(Collection<News> news) {
        return news.stream().map(NewsDto::new).collect(Collectors.toList());
    }

    public static Collection<News> fromDto(Collection<NewsDto> news) {
        return news.stream().map(News::new).collect(Collectors.toList());
    }

    public NewsDto(Long id, String date, String text, String author, String imagePath, String[] hashTags) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.author = author;
        this.imagePath = imagePath;
        this.hashTags = hashTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String[] getHashTags() {
        return hashTags;
    }

    public void setHashTags(String[] hashTags) {
        this.hashTags = hashTags;
    }

    private static Gson gson = new GsonBuilder().serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, NewsDto.class);
    }

    public NewsDto(String json) {
        NewsDto newsDto = gson.fromJson(json, NewsDto.class);
        this.id = newsDto.getId();
        this.date = newsDto.getDate();
        this.text = newsDto.getText();
        this.author = newsDto.getAuthor();
        this.imagePath = newsDto.getImagePath();
        this.hashTags = newsDto.getHashTags();
    }

    public static String toJson(Collection<NewsDto> newsDto) {
        java.lang.reflect.Type type = new TypeToken<Collection<NewsDto>>(){}.getType();
        return gson.toJson(newsDto, type);
    }

    public static Collection<NewsDto> fromJson(String json) {
        java.lang.reflect.Type type = new TypeToken<Collection<NewsDto>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
