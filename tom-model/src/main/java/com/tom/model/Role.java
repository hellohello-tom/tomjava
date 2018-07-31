package com.tom.model;

import com.baomidou.mybatisplus.annotations.TableName;
import com.tom.core.model.AbstractModel;

@TableName("role")
public class Role extends AbstractModel {
    private String name;

    private String displayName;
}
