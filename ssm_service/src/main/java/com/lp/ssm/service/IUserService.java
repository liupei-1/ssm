package com.lp.ssm.service;

import com.lp.ssm.domain.Role;
import com.lp.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/17 1:46
 */
public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    List<Role> findOtherRole(String id) throws Exception;
}

