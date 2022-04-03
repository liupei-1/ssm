package com.lp.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.lp.ssm.dao.IOrdersDao;
import com.lp.ssm.domain.Orders;
import com.lp.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/9 11:34
 */

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    /**
     * 订单表
     * @return
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //参数pageNum是页码值      参数pageSize是每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    /**
     * 根据id查询订单
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Override
    public Orders findById(String ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }


}
