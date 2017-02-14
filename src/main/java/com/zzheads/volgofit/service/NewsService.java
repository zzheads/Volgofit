package com.zzheads.volgofit.service;

import com.zzheads.volgofit.model.News.News;

import java.util.List;

/**
 created by zzheads on 10.02.17
 **/

public interface NewsService {
    News findById(Long id);
    List<News> findAll();
    News save(News news);
    void delete(News news);
    List<News> findByHashTag(String hashTag);
}
