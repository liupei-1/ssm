package com.lp.ssm.service;

import com.lp.ssm.domain.Product;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/7 0:20
 */
public interface IProductService {

    List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
