package com.lp.ssm.dao;

import com.lp.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * @author LiuPei
 * @date 2022/3/12 3:13
 */
public interface IMemberId {

    @Select("select * from member where id = #{id}")
    public Member findById(String id) throws Exception;
}
