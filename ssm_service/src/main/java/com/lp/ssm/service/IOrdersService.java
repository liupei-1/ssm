package com.lp.ssm.service;

import com.lp.ssm.domain.Orders;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/9 11:33
 */
public interface IOrdersService {

    //查询全部订单
    List<Orders> findAll(int page, int size) throws Exception;

    //根据id查询
    Orders findById(String ordersId)throws Exception;
}
