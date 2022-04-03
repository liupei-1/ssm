package com.lp.ssm.service.impl;

import com.lp.ssm.dao.IPermissionDao;
import com.lp.ssm.domain.Permission;
import com.lp.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/31 0:46
 */

@Service
@Transactional
public class permissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;


    /**
     * 查询所有权限
     *
     * @return
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 添加一个权限
     *
     * @param permission
     */
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }


}
