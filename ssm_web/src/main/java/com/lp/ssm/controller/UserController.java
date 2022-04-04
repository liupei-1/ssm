package com.lp.ssm.controller;

import com.lp.ssm.domain.Role;
import com.lp.ssm.domain.UserInfo;
import com.lp.ssm.service.IRoleService;
import com.lp.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * @author LiuPei
 * @date 2022/3/27 14:46
 * 用户控制器
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    /**
     * 添加一个用户
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 查询所有
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 根据id查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    /**
     * 此方法用于查找要操作的用户及可以添加的角色，参数是要操作的用户id
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo user = userService.findById(id);
        //2.根据用户id查询可以添加的角色
        List<Role> roleList = userService.findOtherRole(id);

        mv.addObject("user", user);
        mv.addObject("roleList", roleList);

        mv.setViewName("user-role-add");
        return mv;

    }

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }

}
