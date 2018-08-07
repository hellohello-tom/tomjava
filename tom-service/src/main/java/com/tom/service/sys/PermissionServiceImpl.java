package com.tom.service.sys;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.sys.PermissionMapper;
import com.tom.model.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService {
    
    /**
     *
     * 功能描述:获取系统设定的动态操作权限
     *
     * @param:
     * @return: 
     * @auther: 
     * @date:  
     */
    @Override
    public List<Permission> getSysPermissionList() {
        Wrapper<Permission> conditions = new EntityWrapper<>();
        conditions.setSqlSelect("operator_name","action").eq("del_status", 0).groupBy("action,operator_name");
        return mapper.selectList(conditions);
    }
}
