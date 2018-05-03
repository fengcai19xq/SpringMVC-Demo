package com.sky.knowledge.springmvc.handlers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sky.knowledge.springmvc.dao.DepartmentDao;
import com.sky.knowledge.springmvc.dao.EmployeeDao;
import com.sky.knowledge.springmvc.exception.UserNameNotMatchPasswordException;
import com.sky.knowledge.springmvc.model.Employee;
import com.sky.knowledge.springmvc.model.User;


@SessionAttributes(value={"user"},types={String.class})
@RequestMapping("/springmvctest")
@Scope("prototype")
@Controller
public class SpringMVCTest {

	
	private static final String  SUCCESS = "success";
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
//	@Autowired
//	private ResourceBundleMessageSource messageSource;
	
	public SpringMVCTest(){
		System.out.println("SpringMVCTest..........."+this.toString());
	}
	
	/**
	 * 1、@RequestMapping除了修饰方法，还可以修饰类
	 * 2、
	 * ①类定义处：提供初步的请求映射信息，相当于WEB应用的根目录
	 * ②方法处：提供进一步的细分映射信息，相当于类定义出的URL。若类定义处未标注@RequestMapping，则方法处标记的URL相当于WEB应用的根目录
	 * @description
	 * @create xq
	 * @date 2015-1-15
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	/**
	 * 常用：使用Method 属性来指定请求方式
	 * @description
	 * @create xq
	 * @date 2015-1-15
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 *了解 ：可以使用 params和 headers来更加精确的映射请求，params和headers支持简单的表达式。
	 * @description
	 * @create xq
	 * @date 2015-1-15
	 */
	@RequestMapping(value="/testParamsAndHeaders", 
			params={"username","age!=10"},
			headers={"Accept-Language=zh-CN,zh;q=0.8"})
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	/**
	 * @RequestMapping 支持Ant风格的URL
	 * Ant 风格资源地址支持3种匹配符
	 *  - ?:匹配文件名中的一个字符,例如：/testAntPath/t/abc 
	 *  - *:匹配文件名中的任意字符，例如：/testAntPath/ttt/abc 、/testAntPath/fff/abc 等URL
	 *  - **:**匹配多层路径, 例如：/testAntPath/abc 、/testAntPath/fff/rrr/abc 等URL
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath(){
		System.out.println("testAntPath");
		return SUCCESS ;
	}
	
	/**
	 * @PathVariable 可以映射URL中的占位符到目标方法的参数中
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id){
		System.out.println("testPathVariable:"+id);
		return SUCCESS ;
	}
	
	/**
	 * Rest 风格的URL
	 * 以CRUD为例
	 * 新增： /order/   post
	 * 修改： /order/1  put
	 * 获取：/order/1   get
	 * 删除   /order/1   delete
	 * 
	 * 如何发送put请求和delete请求？
	 *  1、需要配置HiddenHttpMethodFilter
	 *  2、需求发送post请求
	 *  3、需求在发送post请求时携带一个name="_method" 的隐藏域，值为delete或put
	 *  
	 *  
	 *  在springmvc的目标方法中如何得到id呢？
	 *   使用@PathVariable注解
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRest(@PathVariable Integer id){
		System.out.println("testRest get:"+id);
		return SUCCESS; 
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRest(){
		System.out.println("testRest post ");
		return SUCCESS; 
	}
	
	@RequestMapping(value="/testRestDelete/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id){
		System.out.println("testRest delete:"+id);
		return SUCCESS; 
	}
	
	@RequestMapping(value="/testRestPut/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id){
		System.out.println("testRest post:"+id);
		return SUCCESS; 
	}
	
	/**
	 * @RequestParam 来映射请求参数
	 * value 值即请求的参数的参数名
	 * required 该参数是否必须，默认为true
	 * defaultValue 请求参数的默认值
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value="username") String un,
			@RequestParam(value="age",required=false,defaultValue="0")int age){
		System.out.println("testRequestParam,username:"+un+",age:"+age);
		return  SUCCESS;
	}
	
	/**了解
	 * 映射请求信息
	 * 用法同@RequestParam
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping("testRequestHeader")
	public String testRequestHeader(@RequestHeader(value="Accept-Language")String al){
		System.out.println("testRequestHeader,Accept-Language:"+al);
		return SUCCESS ;
	}
	
	/**
	 * 了解
	 * @CookieValue：映射一个cookie值，属性同 @RequestParam
	 * 
	 */
	@RequestMapping("testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID")String sessionId){
		System.out.println("testCookieValue,sessionId:"+sessionId);
		return SUCCESS;
	}
	
	/**
	 * Spring Mvc 会按请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，支持级联属性。例如：dept.deptid,dept.deptName
	 * @description
	 * @create xq
	 * @date 2015-1-19
	 */
	@RequestMapping("testPojo")
	public String testPojo(User user){
		System.out.println("testPojo,user:"+user.toString());
		return SUCCESS ;
	}
	
	
	/**
	 *  可以使用Servlet原生的API作为目标方法的参数，具体支持以下类型
	 * •    HttpServletRequest 
		•   HttpServletResponse 
		•   HttpSession 
		•   java.security.Principal 
		•   Locale 
		•   InputStream 
		•   OutputStream 
		•   Reader 
		•   Writer
	 * @throws IOException 
	 * @description
	 * @create xq
	 * @date 2015-1-20
	 */
	@RequestMapping("/testServletApi")
	public void testServletApi(HttpServletRequest request,HttpServletResponse response,Writer out) throws IOException{
		System.out.println("testServletApi:"+request+","+response);
		out.write("hello springmvc servlet");
	}
	
	/**
	 * 目标方法的返回值可以是ModelAndView类型
	 * 其中可以包含视图和模型信息
	 * SpringMvc会把ModelAndView的model中的数据放到 request 域对象中。
	 * @description
	 * @create xq
	 * @date 2015-1-20
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		String viewName=SUCCESS;
		ModelAndView modelandview = new ModelAndView(viewName);
		modelandview.addObject("time",new Date());
		return modelandview;
	}
	
	/**
	 * 目标方法可以添加map类型(实际上也可以是Model、ModelMap)的参数
	 * @description
	 * @create xq
	 * @date 2015-1-20
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String,Object> map){
		System.out.println(map.getClass().getName());
		map.put("names",Arrays.asList("Tom","xq","zhangsan"));
		return SUCCESS ;
	}
	/**
	 * @SessionAttributes除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是 value属性值）
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到session会话中（实际上使用的是types属性值）
	 * 注意：该注解只能放到类的上面，而不能修饰方法
	 * @description
	 * @create xq
	 * @date 2015-1-20
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String,Object> map){
		User user = new User("xq","222","xuqian@163.com",26);
		map.put("user",user);
		map.put("school","shagnqiu");
		return SUCCESS;
	}
	
	/**
	* 1. 有 @ModelAttribute 标记的方法, 会在每个目标方法执行之前被 SpringMVC 调用! 
	 * 2. @ModelAttribute 注解也可以来修饰目标方法 POJO 类型的入参, 其 value 属性值有如下的作用:
	 * 1). SpringMVC 会使用 value 属性值在 implicitModel 中查找对应的对象, 若存在则会直接传入到目标方法的入参中.
	 * 2). SpringMVC 会一 value 为 key, POJO 类型的对象为 value, 存入到 request 中. 
	 * @description
	 * @create xq
	 * @date 2015-1-21
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map){
		if(id!=null){
			System.out.println("@ModelAttribute 方法");
			User user = new User(1,"tom","123","tom@163.com",12);
			System.out.println("从数据库中获取一个对象："+user);
			map.put("user",user);
		}
	}
	/** 运行流程：
	 *  1、执行@ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到Map中，键为：user
	 *  2、SpringMvc从map中取出user对象，并把表单的请求参数赋给该user对象的对应属性。
	 *  3、SpringMvc 把上述对象传入目标方法的参数
	 *  
	 *  注意：在@ModelAttribute 修饰的方法中，放入到map时的键需要和目标方法的参数类型的第一个字母小写时字符串一致
	 *  
	 * SpringMVC 确定目标方法 POJO 类型入参的过程
	 * 1. 确定一个 key:
	 * 1). 若目标方法的 POJO 类型的参数木有使用 @ModelAttribute 作为修饰, 则 key 为 POJO 类名第一个字母的小写
	 * 2). 若使用了  @ModelAttribute 来修饰, 则 key 为 @ModelAttribute 注解的 value 属性值. 
	 * 2. 在 implicitModel 中查找 key 对应的对象, 若存在, 则作为入参传入
	 * 1). 若在 @ModelAttribute 标记的方法中在 Map 中保存过, 且 key 和 1 确定的 key 一致, 则会获取到. 
	 * 3. 若 implicitModel 中不存在 key 对应的对象, 则检查当前的 Handler 是否使用 @SessionAttributes 注解修饰, 
	 * 若使用了该注解, 且 @SessionAttributes 注解的 value 属性值中包含了 key, 则会从 HttpSession 中来获取 key 所
	 * 对应的 value 值, 若存在则直接传入到目标方法的入参中. 若不存在则将抛出异常. 
	 * 4. 若 Handler 没有标识 @SessionAttributes 注解或 @SessionAttributes 注解的 value 值中不包含 key, 则
	 * 会通过反射来创建 POJO 类型的参数, 传入为目标方法的参数
	 * 5. SpringMVC 会把 key 和 POJO 类型的对象保存到 implicitModel 中, 进而会保存到 request 中. 
	 *  
	 *  
	 * 源代码分析的流程
	 * 1. 调用 @ModelAttribute 注解修饰的方法. 实际上把 @ModelAttribute 方法中 Map 中的数据放在了 implicitModel 中.
	 * 2. 解析请求处理器的目标参数, 实际上该目标参数来自于 WebDataBinder 对象的 target 属性
	 * 1). 创建 WebDataBinder 对象:
	 * ①. 确定 objectName 属性: 若传入的 attrName 属性值为 "", 则 objectName 为类名第一个字母小写. 
	 * *注意: attrName. 若目标方法的 POJO 属性使用了 @ModelAttribute 来修饰, 则 attrName 值即为 @ModelAttribute 
	 * 的 value 属性值 
	 * 
	 * ②. 确定 target 属性:
	 * 	> 在 implicitModel 中查找 attrName 对应的属性值. 若存在, ok
	 * 	> *若不存在: 则验证当前 Handler 是否使用了 @SessionAttributes 进行修饰, 若使用了, 则尝试从 Session 中
	 * 获取 attrName 所对应的属性值. 若 session 中没有对应的属性值, 则抛出了异常. 
	 * 	> 若 Handler 没有使用 @SessionAttributes 进行修饰, 或 @SessionAttributes 中没有使用 value 值指定的 key
	 * 和 attrName 相匹配, 则通过反射创建了 POJO 对象
	 * 
	 * 2). SpringMVC 把表单的请求参数赋给了 WebDataBinder 的 target 对应的属性. 
	 * 3). *SpringMVC 会把 WebDataBinder 的 attrName 和 target 给到 implicitModel. 
	 * 近而传到 request 域对象中. 
	 * 4). 把 WebDataBinder 的 target 作为参数传递给目标方法的入参. 
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改,user:"+user.toString());
		
		return  SUCCESS ;
	}
	/**
	 * 对于那些返回String，View 或ModeMap 等类型的 
	        处理方法，Spring MVC 也会在内部将它们装配成一个 
	   ModelAndView 对象，它包含了逻辑名和模型对象的视图 。
	   Spring MVC 借助视图解析器 （ViewResolver）得到最终 
		   的视图对象（View），最终的视图可以是JSP ，也可能是 
		   Excel、JFreeChart  等各种表现形式的视图 。
	 * @description
	 * @create xq
	 * @date 2015-1-22
	 */
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver(){
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	
	@RequestMapping("/testView")
	public String testView(){
		System.out.println("testView");
		return "helloView";
	}
	
	/**
	 * 如果返回的字符串中帶 forward：或者redirect：前綴時，SpringMvc會對他們進行特殊處理，將 forward：和redirect：當成指示符，起后的字符串作為URL地址
	 * @description
	 * @create xq
	 * @date 2015-1-23
	 */
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}
	
	/**
	 * 使用自定义转换器，将字符串转化为 Employee 实体对象
	 * @description
	 * @create xq
	 * @date 2015-1-24
	 */
	@RequestMapping("/testConversionServiceConverer")
	public String testConverter(@RequestParam("employee") Employee employee){
		System.out.println("save: " + employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	/**处理json
	 * •   1. 加入jar 包： 
            jackson-databind-2.1.5.jar
            jackson-annotations-2.1.5.jar
            jackson-core-2.1.5.jar  
		•   2. 编写目标方法，使其返回JSON 对应的对象或集合 
		•   3. 在方法上添加@ResponseBody 注解 

	 * @description
	 * @create xq
	 * @date 2015-1-27
	 */
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testJson(){
		Collection<Employee> e = employeeDao.getAll(); 
		return e;
	}
	
	/**
	 * HttpMessageConverter用法
	 * @description
	 * @create xq
	 * @date 2015-1-27
	 */
	@ResponseBody
	@RequestMapping("/testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body){
		System.out.println(body);
		return "helloworld! " + new Date();
	}
	
	/**
	 * ResponseEntity用法
	 * 下载文件
	 * @description
	 * @create xq
	 * @date 2015-1-27
	 */
	@RequestMapping("/testResponseEntity")
	public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
		byte [] body = null;
		ServletContext servletContext = session.getServletContext();
		InputStream in = servletContext.getResourceAsStream("/files/2222.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
		return response;
	}
	
	
	@RequestMapping("/i18n")
	public String testI18n(Locale locale){
//		String val = messageSource.getMessage("i18n.user", null, locale);
//		System.out.println(val); 
		return "i18n";
	}
	
	/**
	 * 文件上传
	 * @description
	 * @create xq
	 * @date 2015-1-28
	 */
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam("desc") String desc, 
			@RequestParam("file") MultipartFile file) throws IOException{
		System.out.println("desc: " + desc);
		System.out.println("OriginalFilename: " + file.getOriginalFilename());
		System.out.println("InputStream: " + file.getInputStream());
		return "success";
	}
	
	
//	@ExceptionHandler({RuntimeException.class})
//	public ModelAndView handleArithmeticException2(Exception ex){
//		System.out.println("[出异常了]: " + ex);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", ex);
//		return mv;
//	}
	
	/**
	  * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
	 * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级的问题. 
	 * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来处理当前方法出现的异常, 
	 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常.  
	 * @description
	 * @create xq
	 * @date 2015-1-29
	 */
//	@ExceptionHandler({ArithmeticException.class})
//	public ModelAndView handleArithmeticException(Exception e){
//		System.out.println("出异常啦，"+e);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("exception", e); 
//		return mv;
//	}
	
	@RequestMapping("/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("id") int id){
		System.out.println("10 /i =" + 10/id);
		return SUCCESS ;
	}
	
//	@ResponseStatus(reason="测试",value=HttpStatus.NOT_FOUND)
	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
		if(i == 13){
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver...");
		
		return "success";
	}
	/**
	 * DefaultHandlerExceptionResolver提供个很多默认异常
	 * @description
	 * @create xq
	 * @date 2015-1-30
	 */
	@RequestMapping(value="/testDefaultHandlerExceptionResolver",method=RequestMethod.POST)
	public String testDefaultHandlerExceptionResolver(){
		System.out.println("testDefaultHandlerExceptionResolver...");
		return "success";
	}
	/**
	 * SimpleMappingExceptionResolver
	 * @description
	 * @create xq
	 * @date 2015-1-30
	 */
	@RequestMapping("/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
		String [] vals = new String[10];
		System.out.println(vals[i]);
		return "success";
	}
	
}
