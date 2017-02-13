package com.zzheads.volgofit.model.News;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zzheads on 10.02.17.
 */

@Entity(name = "news")
public class News implements Serializable {
    private Long id;
    private Date date;
    private String text;
    private String author;
    private Set<String> hashTags = new HashSet<String>(0);
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
    @DateTimeFormat(pattern = "DD/MM/YYYY")
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

    private static ExclusionStrategy NewsExclusionStartegy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return (Objects.equals(f.getName(), "news"));
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private static Gson gson = new GsonBuilder().setExclusionStrategies(NewsExclusionStartegy).serializeNulls().create();

    public String toJson() {
        return gson.toJson(this, News.class);
    }

    public News(String json) {
        News newsFromJson = gson.fromJson(json, News.class);
        this.id = newsFromJson.id;
        this.date = newsFromJson.date;
        this.text = newsFromJson.text;
        this.author = newsFromJson.author;
        this.hashTags = newsFromJson.hashTags;
        this.image = newsFromJson.image;
    }

    public static String toJson(Collection<News> news) {
        java.lang.reflect.Type type = new TypeToken<Collection<News>>(){}.getType();
        return gson.toJson(news, type);
    }

    public static Collection<News> fromJson(String json) {
        java.lang.reflect.Type type = new TypeToken<Collection<News>>(){}.getType();
        return gson.fromJson(json, type);
    }


}
