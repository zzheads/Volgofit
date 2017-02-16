package com.zzheads.volgofit.model.News;

import com.zzheads.volgofit.dto.News.NewsDto;
import com.zzheads.volgofit.util.DateConverter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
/* volgofit - ${PACKAGE_NAME}
/* created by zzheads on 10.02.17
**/

@Entity(name = "news")
public class News implements Serializable {
    private Long id;
    private Date date;
    private String text;
    private String author;
    private Set<String> hashTags = new HashSet<>(0);
    private String image;

    public News() {
    }

    public News(Date date, String text, String author, Set<String> hashTags, String image) {
        this.date = date;
        this.text = text;
        this.author = author;
        this.hashTags = hashTags;
        this.image = image;
    }

    public News(NewsDto news) {
        this.id = news.getId();
        this.date = DateConverter.stringToDate(news.getDate(), false);
        this.text = news.getText();
        this.author = news.getAuthor();
        this.hashTags = Arrays.stream(news.getHashTags()).collect(Collectors.toSet());
        this.image = news.getImage();
    }

    @Id
    @Column(name = "news_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Lob
    @Type(type = "org.hibernate.type.TextType")
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

    @ElementCollection
    @Cascade(value = CascadeType.ALL)
    public Set<String> getHashTags() {
        return hashTags.stream().map(String::toUpperCase).collect(Collectors.toSet());
    }

    public void setHashTags(Set<String> hashTags) {
        this.hashTags = hashTags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toJson() {
        return new NewsDto(this).toJson();
    }

    public News(String json) {
        NewsDto newsDto = new NewsDto(json);
        this.id = newsDto.getId();
        this.date = DateConverter.stringToDate(newsDto.getDate(), false);
        this.text = newsDto.getText();
        this.author = newsDto.getAuthor();
        this.hashTags = Arrays.stream(newsDto.getHashTags()).collect(Collectors.toSet());
        this.image = newsDto.getImage();
    }

    public static String toJson(Collection<News> news) {
        return NewsDto.toJson(NewsDto.toDto(news));
    }

    public static Collection<News> fromJson(String json) {
        return NewsDto.fromDto(NewsDto.fromJson(json));
    }


}
