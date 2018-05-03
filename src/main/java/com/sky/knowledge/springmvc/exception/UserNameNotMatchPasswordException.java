package com.sky.knowledge.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @ResponseStatus 用法
 * 自定义异常
 * @description
 * @create xq
 * @date 2015-1-29
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="用户名和密码不匹配!")
public class UserNameNotMatchPasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
