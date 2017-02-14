package com.zzheads.volgofit.dao;

import com.zzheads.volgofit.model.News.News;
import org.springframework.data.repository.CrudRepository;

//
// volgofit / ${PACKAGE_NAME}
// Created by zzheads on 10.02.17.
//

public interface NewsDao extends CrudRepository<News, Long> {
}
