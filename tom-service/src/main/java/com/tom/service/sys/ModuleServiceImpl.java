package com.tom.service.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tom.core.service.BaseServiceImpl;
import com.tom.dao.sys.ModuleMapper;
import com.tom.model.Module;
import com.tom.model.dto.GetModuleRoleDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<ModuleMapper, Module> implements ModuleService {


    @Override
    public List<GetModuleRoleDto> getModuleRole() {
        List<GetModuleRoleDto> result = mapper.selectModuleRole();
        return result;
    }
}
