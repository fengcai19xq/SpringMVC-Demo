package com.sky.knowledge.springmvc.handlers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sky.knowledge.springmvc.dao.DepartmentDao;
import com.sky.knowledge.springmvc.dao.EmployeeDao;
import com.sky.knowledge.springmvc.model.Employee;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map){
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id, Map<String, Object> map){
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	public String save(@Valid Employee employee , Errors result){
		System.out.println("save: " + employee);
		if(result.getErrorCount()>0){
			System.out.println("出错了");
			for(FieldError error:result.getFieldErrors()){
				System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			return "input";
		}
		
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("departments", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
	
	@RequestMapping("/emps")
	public String list(Map<String, Object> map){
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	/**
	 * 不自动绑定对象中的lastName属性，另行处理。
	 * • 由@InitBinder 标识的方法，可以对WebDataBinder 对 
		   象进行初始化。WebDataBinder 是DataBinder 的子类，用 
		   于完成由表单字段到JavaBean 属性的绑定 
		•  @InitBinder方法不能有返回值，它必须声明为void。 
		•  @InitBinder方法的参数通常是是WebDataBinder 
	 * @description
	 * @create xq
	 * @date 2015-1-26
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setDisallowedFields("lastName");
	}
	
}
