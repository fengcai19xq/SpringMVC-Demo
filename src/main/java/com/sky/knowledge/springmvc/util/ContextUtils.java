/** 
 * 文件名：ContextUtils.java 
 * 版本信息：1.0
 * 日期：2016年4月15日-上午10:56:39   
 */
package com.sky.knowledge.springmvc.util;

import org.springframework.context.ApplicationContext;


/**
 * ContextUtils
 *
 * @author qian.xu
 * @date 2016年4月15日 上午10:56:39
 * @version 
 * 
 */

public class ContextUtils {

    private static ApplicationContext applicationContext = null ;
    
    public static void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ContextUtils.class) {
            if(ContextUtils.applicationContext == null ){
                ContextUtils.applicationContext = applicationContext;
            }
        }
     }
    public static ApplicationContext getApplicationContext() {
        
         return applicationContext;
      }
}
