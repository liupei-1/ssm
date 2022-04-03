package com.lp.ssm.dao;

import com.lp.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/12 3:20
 */

public interface ITravellerDao {

    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId}) ")
    public List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
