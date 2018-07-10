package com.tom.core.service;

import com.tom.core.model.AbstractModel;
import com.tom.core.repository.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<Mapper extends AbstractRepository<TModel,Integer>, TModel extends AbstractModel> implements
        BaseService<TModel> {

    @Autowired
    protected  Mapper mapper;


}

