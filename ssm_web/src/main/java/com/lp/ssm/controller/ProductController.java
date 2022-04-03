package com.lp.ssm.controller;

import com.lp.ssm.domain.Product;
import com.lp.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/7 0:45
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public IProductService productService;

    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }

    //查询全部产品
    @RequestMapping("/findAll.do")
    @RolesAllowed("admin")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();

        List<Product> ps = productService.findAll();

        mv.addObject("productList", ps);

        mv.setViewName("product-list");
        return mv;
    }

}
