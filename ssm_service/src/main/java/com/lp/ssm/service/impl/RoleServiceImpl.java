package com.lp.ssm.service.impl;

import com.lp.ssm.dao.IRoleDao;
import com.lp.ssm.domain.Permission;
import com.lp.ssm.domain.Role;
import com.lp.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/29 1:35
 */

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 根据id查询角色
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    /**
     * 根据id查询可以添加的权限
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    @Override
    public List<Permission> findOtherPermissions(String roleId) throws Exception {
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 根据角色添加权限
     *
     * @param roleId
     * @param permissionIds
     * @throws Exception
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     *
     * @throws Exception
     */
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }
}
