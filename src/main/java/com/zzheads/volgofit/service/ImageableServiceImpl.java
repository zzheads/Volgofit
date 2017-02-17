package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.dao.ClientDao;
import com.zzheads.volgofit.dao.NewsDao;
import com.zzheads.volgofit.dao.TrainerDao;
import com.zzheads.volgofit.dao.WorkoutDao;
import com.zzheads.volgofit.model.Imageable.Imageable;
import com.zzheads.volgofit.model.News.News;
import com.zzheads.volgofit.model.Person.Client;
import com.zzheads.volgofit.model.Person.Trainer;
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
    private final TrainerDao trainerDao;
    private final ClientDao clientDao;

    @Autowired
    public ImageableServiceImpl(NewsDao newsDao, WorkoutDao workoutDao, TrainerDao trainerDao, ClientDao clientDao) {
        this.newsDao = newsDao;
        this.workoutDao = workoutDao;
        this.trainerDao = trainerDao;
        this.clientDao = clientDao;
    }

    @Override
    public Collection<? extends Imageable> findAll(String className) {
        switch (className.toLowerCase()) {
            case "news": return (Collection<News>) newsDao.findAll();
            case "workout": return (Collection<Workout>) workoutDao.findAll();
            case "trainer": return (Collection<Trainer>) trainerDao.findAll();
            case "client": return (Collection<Client>) clientDao.findAll();
        }
        return null;
    }

    @Override
    public Imageable findById(String className, Long id) {
        switch (className.toLowerCase()) {
            case "news": return newsDao.findOne(id);
            case "workout": return workoutDao.findOne(id);
            case "trainer": return trainerDao.findOne(id);
            case "client": return clientDao.findOne(id);
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
            case "trainer":
                Trainer trainer = trainerDao.findOne(id);
                trainerDao.save(trainer);
                break;
            case "client":
                Client client = clientDao.findOne(id);
                clientDao.save(client);
                break;
        }
        return null;
    }

    @Override
    public void delete(String className, Long id) {
        switch (className.toLowerCase()) {
            case "news": newsDao.delete(id); break;
            case "workout": workoutDao.delete(id); break;
            case "trainer": trainerDao.delete(id); break;
            case "client": clientDao.delete(id); break;
        }
    }
}
