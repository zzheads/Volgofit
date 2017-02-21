package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.dao.NewsDao;
import com.zzheads.volgofit.dao.UserDao;
import com.zzheads.volgofit.dao.WorkoutDao;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.model.User.User;
import com.zzheads.volgofit.model.Workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

//  created by zzheads on 17.02.17
//

@Service
public class ImageableServiceImpl implements ImageableService {
    private final NewsDao newsDao;
    private final WorkoutDao workoutDao;
    private final UserDao userDao;

    @Autowired
    public ImageableServiceImpl(NewsDao newsDao, WorkoutDao workoutDao, UserDao userDao) {
        this.newsDao = newsDao;
        this.workoutDao = workoutDao;
        this.userDao = userDao;
    }

    @Override
    public Collection<? extends Imageable> findAll(String className) {
        switch (className.toLowerCase()) {
            case "news": return (Collection<News>) newsDao.findAll();
            case "workout": return (Collection<Workout>) workoutDao.findAll();
            case "user": return (Collection<User>) userDao.findAll();
        }
        return null;
    }

    @Override
    public Imageable findById(String className, Long id) {
        switch (className.toLowerCase()) {
            case "news": return newsDao.findOne(id);
            case "workout": return workoutDao.findOne(id);
            case "user": return userDao.findOne(id);
        }
        return null;
    }

    @Override
    public Imageable save(String className, Long id) {
        switch (className.toLowerCase()) {
            case "news":
                News news = newsDao.findOne(id);
                newsDao.save(news);
                break;
            case "workout":
                Workout workout = workoutDao.findOne(id);
                workoutDao.save(workout);
                break;
            case "user":
                User user = userDao.findOne(id);
                userDao.save(user);
                break;
        }
        return null;
    }

    @Override
    public void delete(String className, Long id) {
        switch (className.toLowerCase()) {
            case "news": newsDao.delete(id); break;
            case "workout": workoutDao.delete(id); break;
            case "user": userDao.delete(id); break;
        }
    }
}
