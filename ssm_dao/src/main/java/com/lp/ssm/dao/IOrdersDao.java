package com.lp.ssm.dao;

import com.lp.ssm.domain.Member;
import com.lp.ssm.domain.Orders;
import com.lp.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/9 11:33
 */

public interface IOrdersDao {

    @Select("select * from orders")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",
                    one = @One(select = "com.lp.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;


    @Select("select * from orders where id =#{ordersId}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", javaType = Product.class, one = @One(select = "com.lp.ssm.dao.IProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(select = "com.lp.ssm.dao.IMemberId.findById")),
            @Result(column = "id", property = "travellers" ,javaType =java.util.List.class, many = @Many(select = "com.lp.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId) throws Exception;

}
