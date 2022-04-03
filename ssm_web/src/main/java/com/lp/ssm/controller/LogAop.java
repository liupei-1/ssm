package com.lp.ssm.controller;

import com.lp.ssm.domain.SysLog;
import com.lp.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author LiuPei
 * @date 2022/4/3 22:47
 */

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始的时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //前置通知  主要是获取开始的时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.lp.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问方法的参数

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName);//只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }

    }

    //后置通知
    @After("execution(* com.lp.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime() - visitTime.getTime();//获取访问的时长

        String url = "";

        //获取url
        if (clazz != null && method != null && clazz != LogAop.class) {
            //1.获取类上面的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping(xxx)
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();

                    // TODO: 2022/4/4  
                    //排除不访问的RequestMapping的value
                    // 把/sysLog排除(访问日志页面不计入到日志目录)
                    /*if (!"/sysLog".equals(classValue[0]) && !"/findAll.do".equals(methodValue[0])) {
                        
                    }*/
                    
                        //拼接url（类路径+方法路径）
                        url = classValue[0] + methodValue[0];

                        //获取访问的ip
                        String ip = request.getRemoteAddr();

                        //获取当前操作的用户
                        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取了当前登入的用户
                        User user = (User) context.getAuthentication().getPrincipal();
                        String username = user.getUsername();

                        //将日志相关信息封装到SysLog对象
                        SysLog sysLog = new SysLog();
                        sysLog.setExecutionTime(time);
                        sysLog.setIp(ip);
                        sysLog.setMethod("[类名] " + clazz.getName() + "[方法名] " + method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setVisitTime(visitTime);

                        //调用Service完成操作保存日志信息
                        sysLogService.save(sysLog);
                    


                }
            }
        }
    }
}
