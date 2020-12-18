package org.jeecg.modules.demo.product.service;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect//切面注解
@Component//构成
public class ProductAspect {
    //设置在ManagerController的login方法的切点
    @Pointcut("execution(public * org.jeecg.modules.demo.torder.controller.TorderController.exportXls(..))")
    public void export(){//切点映射，命名不规定
        System.out.println("aspect");
    }
    //在本类的login执行之前
    @Before("export()")
    public void beforeExport()
    {
        System.out.println(System.currentTimeMillis());
        System.out.println("before");
    }
    //在本类的login执行之后执行
    @After("export()")
    public void afterExport(){
        System.out.println(System.currentTimeMillis());
        System.out.println("after");
    }

}
