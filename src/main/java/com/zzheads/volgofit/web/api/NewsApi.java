package com.zzheads.volgofit.web.api;

import com.zzheads.volgofit.exceptions.ApiError;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.service.NewsService;
import com.zzheads.volgofit.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 created by zzheads on 11.02.17
 **/

@RestController
@RequestMapping(value = "/api/news")
public class NewsApi {
    private final NewsService newsService;

    @Autowired
    public NewsApi(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String getAllNews() {
        List<News> news = newsService.findAll();
        return News.toJson(news);
    }

    @RequestMapping(value = "/{id}", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String getNews(@PathVariable Long id) {
        News news = newsService.findById(id);
        if (news != null) {
            return news.toJson();
        }
        throw new ApiError(NOT_FOUND);
    }

    @RequestMapping(value = "/byHashTag/{hashTagName}", method = GET, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String findNewsByHashTags(@PathVariable String hashTagName) {
        return News.toJson(newsService.findByHashTag(hashTagName));
    }

    @RequestMapping(method = RequestMethod.POST, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String addNews(@RequestBody String jsonString) {
        if (LoggedUser.isAdmin()) {
            News news = new News(jsonString);
            if (news.getText() != null) {
                if (news.getDate() == null) {
                    news.setDate(new Date());
                }
                return (newsService.save(news)).toJson();
            }
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(OK)
    public @ResponseBody String updateNews(@PathVariable Long id, @RequestBody String jsonString) {
        if (LoggedUser.isAdmin()) {
            if (newsService.findById(id) == null) {
                throw new ApiError(NOT_FOUND);
            }
            News news = new News(jsonString);
            if (news.getText() != null) {
                news.setId(id);
                return (newsService.save(news)).toJson();
            }
        }
        throw new ApiError(FORBIDDEN);
    }

    @RequestMapping(value = "/{id}", method = DELETE, produces = {APPLICATION_JSON_UTF8_VALUE}, consumes = {APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(NO_CONTENT)
    public void deleteNews(@PathVariable Long id) {
        if (LoggedUser.isAdmin()) {
            News news = newsService.findById(id);
            if (news != null) {
                newsService.delete(news);
                return;
            }
            throw new ApiError(NOT_FOUND);
        }
        throw new ApiError(FORBIDDEN);
    }

}
