package com.tom.core.service;

import com.tom.core.mapper.AbstractMapper;
import com.tom.core.model.AbstractModel;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<Mapper extends AbstractMapper<TModel>, TModel extends AbstractModel> implements
        BaseService<TModel> {

    @Autowired
    protected  Mapper mapper;

}

