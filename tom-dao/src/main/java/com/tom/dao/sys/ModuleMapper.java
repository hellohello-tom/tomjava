package com.tom.dao.sys;

import com.tom.core.repository.AbstractRepository;
import com.tom.model.Module;
import com.tom.model.Role;
import com.tom.model.dto.GetModuleRoleDto;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModuleMapper extends AbstractRepository<Module, Integer> {

    /**
     *
     * 功能描述:获取模块中角色信息
     *
     * @param: 
     * @return: 
     * @auther: 
     * @date:  
     */
    List<GetModuleRoleDto> selectModuleRole();

}
