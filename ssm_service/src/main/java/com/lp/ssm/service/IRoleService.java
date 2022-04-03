package com.lp.ssm.service;

import com.lp.ssm.domain.Permission;
import com.lp.ssm.domain.Role;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/29 1:34
 */
public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;
}
