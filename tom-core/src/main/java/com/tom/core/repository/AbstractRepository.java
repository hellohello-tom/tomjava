package com.tom.core.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tom.core.model.AbstractModel;

import java.io.Serializable;

public interface AbstractRepository <T extends AbstractModel, PK extends Serializable> extends BaseMapper<T> {

}
