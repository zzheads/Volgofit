package com.zzheads.volgofit.service;//

import com.zzheads.volgofit.dao.ClientDao;
import com.zzheads.volgofit.dao.NewsDao;
import com.zzheads.volgofit.dao.TrainerDao;
import com.zzheads.volgofit.dao.WorkoutDao;
import com.zzheads.volgofit.model.Imageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    private CrudRepository dao(String className) {
        Map<String, CrudRepository> daos = new HashMap<String, CrudRepository>(){{
            put("news", newsDao);
            put("workout", workoutDao);
            put("trainer", trainerDao);
            put("client", clientDao);
        }};
        return daos.get(className);
    }

    @Override
    public Imageable findById(String className, Long id) {
        return (Imageable) dao(className).findOne(id);
    }

    @Override
    public Imageable save(String className, Imageable imageable) {
        return (Imageable) dao(className).save(imageable);
    }

    @Override
    public void delete(String className, Imageable imageable) {
        dao(className).delete(imageable);
    }
}
