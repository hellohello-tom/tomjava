package com.tom.service.sys;

import com.tom.core.service.BaseService;
import com.tom.model.Permission;

import java.util.List;

public interface PermissionService extends BaseService<Permission> {
    /**
     *
     * 功能描述:获取系统设定的操作权限
     *
     * @param: 
     * @return: 
     * @auther: 
     * @date:  
     */
    List<Permission> getSysPermissionList();


}
