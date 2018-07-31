//package com.tom.web.core.shiro.config;
//
//import com.tom.model.dto.GetModuleRoleDto;
//import com.tom.service.sys.ModuleService;
//import org.apache.logging.log4j.util.Strings;
//import org.apache.shiro.config.Ini;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.util.CollectionUtils;
//import org.apache.shiro.web.config.IniFilterChainResolverFactory;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * 功能描述:创建动态权限
// *
// * @param:
// * @return:
// * @auther:
// * @date:
// */
//public class ShiroDynamicPermissionFactory extends ShiroFilterFactoryBean {
//
//    @Resource
//    ModuleService moduleService;
//
//    @Override
//    public void setFilterChainDefinitions(String definitions) {
//        List<GetModuleRoleDto> moduleRoles = moduleService.getModuleRole();
//        Set<String> urls = new LinkedHashSet<>();
//        moduleRoles.stream().forEach(x -> urls.add(x.getUrl()));
//        Map<String, String> otherChains = new HashMap<>();
//        for (String url : urls) {
//            StringBuilder roleOrFilters = new StringBuilder();
//            for (int i = 0; i < moduleRoles.size(); i++) {
//                if (Objects.equals(url, moduleRoles.get(i).getUrl())) {
//                    roleOrFilters.append(Strings.join(moduleRoles.get(i).getRoles(), ','));
//                }
//            }
//            String rolesStr = roleOrFilters.toString();
//            if (!"".equals(rolesStr)) {
//                otherChains.put(url, "roleOrFilter[" + rolesStr + "]"); //  /discover/newstag  authc,roles[user,admin]
//            }
//        }
//        //加载配置默认的过滤链
//        Ini ini = new Ini();
//        ini.load(definitions);
//        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
//        if (CollectionUtils.isEmpty(section)) {
//            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//        }
//        //加上数据库中过滤链
//        section.putAll(otherChains);
//        section.put("/**", "anon");
//        setFilterChainDefinitionMap(section);
//    }
//}
