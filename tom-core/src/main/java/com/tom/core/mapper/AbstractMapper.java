package com.tom.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tom.core.model.AbstractModel;

public interface AbstractMapper<T extends AbstractModel> extends BaseMapper<T> {

}
