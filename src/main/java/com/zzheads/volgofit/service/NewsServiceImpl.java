package com.zzheads.volgofit.service;

import com.zzheads.volgofit.dao.NewsDao;
import com.zzheads.volgofit.model.News.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzheads on 10.02.17.
 */

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsDao newsDao;

    @Autowired
    public NewsServiceImpl(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public News findById(Long id) {
        return newsDao.findOne(id);
    }

    @Override
    public List<News> findAll() {
        return (List<News>) newsDao.findAll();
    }

    @Override
    public News save(News news) {
        newsDao.save(news);
        return news;
    }

    @Override
    public void delete(News news) {
        newsDao.delete(news);
    }

    @Override
    public List<News> findByHashTag(String hashTag) {
        return findAll().stream().filter(news -> news.getHashTags().contains(hashTag.toUpperCase())).collect(Collectors.toList());
    }
}
