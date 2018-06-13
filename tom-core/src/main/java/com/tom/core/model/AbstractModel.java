package com.tom.core.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
}
