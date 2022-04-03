package com.lp.ssm.service;

import com.lp.ssm.domain.Permission;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/31 0:46
 */
public interface IPermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
