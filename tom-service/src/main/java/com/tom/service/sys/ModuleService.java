package com.tom.service.sys;

import com.tom.core.service.BaseService;
import com.tom.model.Module;
import com.tom.model.dto.GetModuleRoleDto;

import java.util.List;

public interface ModuleService extends BaseService<Module> {

    List<GetModuleRoleDto> getModuleRole();
}
