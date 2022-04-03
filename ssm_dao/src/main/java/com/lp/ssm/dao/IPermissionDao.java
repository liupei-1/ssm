package com.lp.ssm.dao;

import com.lp.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/29 0:46
 */
public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_Permission where roleId=#{id})")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;


}
