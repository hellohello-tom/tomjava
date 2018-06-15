package com.tom.core.utils;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Auther: tom
 * @Date:
 * @Description: mapper 实体之间的转换
 */
public class Dozer extends DozerBeanMapper {
    public <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List destinationList = new ArrayList();

        sourceList.forEach(obj -> destinationList.add(map(obj, destinationClass)));
        return destinationList;
    }
}
