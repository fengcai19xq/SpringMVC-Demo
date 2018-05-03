package com.sky.knowledge.springmvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sky.knowledge.springmvc.service.DummyService;
import com.sky.knowledge.springmvc.util.SpringContextHelper;
/**
 * 自定义拦截器
 * @description
 * @create xq
 * @date 2015-1-29
 */
public class FirstInterceptor implements HandlerInterceptor{

	/**
	 * 该方法在目标方法之前被调用.
	 * 若返回值为 true, 则继续调用后续的拦截器和目标方法. 
	 * 若返回值为 false, 则不会再调用后续的拦截器和目标方法. 
	 * 
	 * 可以考虑做权限. 日志, 事务等. 
	 * @description
	 * @create xq
	 * @date 2015-1-28
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("[FirstInterceptor] preHandle");
		DummyService dummyService = (DummyService)SpringContextHelper.getBean("dummyService");
		System.out.println(dummyService);
		return true;
	}

	/**
	 * 
	 * 调用目标方法之后, 但渲染视图之前. 
	 * 可以对请求域中的属性或视图做出修改.
	 * @description
	 * @create xq
	 * @date 2015-1-28
	 */
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("[FirstInterceptor] postHandle");
	}

	/**
	 * 渲染视图之后被调用. 释放资源 
	 * @description
	 * @create xq
	 * @date 2015-1-28
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("[FirstInterceptor] afterCompletion");
	}

}
