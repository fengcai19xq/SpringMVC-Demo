package com.sky.knowledge.springmvc.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 读取spring配置文件操作
 * @author zhenxin.li
 *
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {
//	private  static ApplicationContext context = null;  
	  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
//	    SpringContextHelper.context = applicationContext;  
	    ContextUtils.setApplicationContext(applicationContext);
    }  
      
    public static Object getBean(String name){  
//        return context.getBean(name);  
        return ContextUtils.getApplicationContext().getBean(name);
    }  
}
