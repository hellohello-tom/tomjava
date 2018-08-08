package com.tom.dao;

import com.tom.core.repository.AbstractRepository;
import com.tom.model.User;
import com.tom.model.dto.GetUserLoginDto;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends AbstractRepository<User, Integer> {
    /**
     * 功能描述:用户登录
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    GetUserLoginDto login(@Param("account") String account, @Param("password") String password);

    /**
     * 功能描述:用户登录
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    GetUserLoginDto getUserPermission(@Param("account")String account);
}
