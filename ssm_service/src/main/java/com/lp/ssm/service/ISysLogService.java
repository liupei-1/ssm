package com.lp.ssm.service;

import com.lp.ssm.domain.SysLog;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/4/4 0:33
 */
public interface ISysLogService {

    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
