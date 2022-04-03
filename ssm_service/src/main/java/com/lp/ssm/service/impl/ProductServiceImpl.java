package com.lp.ssm.service.impl;

import com.lp.ssm.dao.IProductDao;
import com.lp.ssm.domain.Product;
import com.lp.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/7 0:22
 */

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    public IProductDao productDao;

    /**
     * 查询全部产品
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    /**
     * 添加产品
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
