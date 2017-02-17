package com.zzheads.volgofit.dao;

//
// volgofit / ${PACKAGE_NAME}
// Created by zzheads on 10.02.17.
//

import com.zzheads.volgofit.model.News.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsDao extends CrudRepository<News, Long> {
}
