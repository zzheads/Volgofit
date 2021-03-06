package com.zzheads.volgofit.model.News;

import com.zzheads.volgofit.dto.News.NewsDto;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.util.DateConverter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

/**
/* volgofit - ${PACKAGE_NAME}
/* created by zzheads on 10.02.17
**/

@Entity(name = "news")
public class News extends Imageable {
    private Long id;
    private Date date;
    private String text;
    private String author;
    private String imagePath;
    private Set<String> hashTags = new HashSet<>(0);

    public News() {
    }

    public News(Long id, Date date, String text, String author, String imagePath, Set<String> hashTags) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.author = author;
        this.imagePath = imagePath;
        this.hashTags = hashTags;
    }

    public News(NewsDto news) {
        if (news == null) return;
        this.id = news.getId();
        this.date = DateConverter.stringToDate(news.getDate(), false);
        this.text = news.getText();
        this.author = news.getAuthor();
        this.imagePath = news.getImagePath();
        this.hashTags = Arrays.stream(news.getHashTags()).collect(Collectors.toSet());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    public Long getId() {
        return this.id;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @ElementCollection
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    public Set<String> getHashTags() {
        return hashTags.stream().map(String::toUpperCase).collect(Collectors.toSet());
    }

    public void setHashTags(Set<String> hashTags) {
        this.hashTags = hashTags;
    }

    public String toJson() {
        return new NewsDto(this).toJson();
    }

    public News(String json) {
        NewsDto newsDto = new NewsDto(json);
        this.imagePath = newsDto.getImagePath();
        this.id = newsDto.getId();
        this.date = DateConverter.stringToDate(newsDto.getDate(), false);
        this.text = newsDto.getText();
        this.author = newsDto.getAuthor();
        if (newsDto.getHashTags() != null) {
            this.hashTags = Arrays.stream(newsDto.getHashTags()).collect(Collectors.toSet());
        }
    }

    public static String toJson(Collection<News> news) {
        return NewsDto.toJson(NewsDto.toDto(news));
    }

    public static Collection<News> fromJson(String json) {
        return NewsDto.fromDto(NewsDto.fromJson(json));
    }
}
