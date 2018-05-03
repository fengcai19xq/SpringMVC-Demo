<%@ page language="java" session="false" contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html> 
    <head> 
        <title>Adobocode : Sample Spring MVC</title> 
        <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
           $(function(){
			$("#testJson").click(function(){
				var url = this.href;
				var args = {};
				$.post(url, args, function(data){
					for(var i = 0; i < data.length; i++){
						var id = data[i].id;
						var lastName = data[i].lastName;
						
						alert(id + ": " + lastName);
					}
				});
				return false;
			});
		})
		</script>
    </head> 
    <body> 
        <h2>Adobocode : Hello World</h2> 
        <br/> 
        <a href="<c:url value="personDisplay"/>">Person List</a> 

        <a href="helloworld">hello world</a> 
        <br></br>
        <a href="springmvctest/testRequestMapping">testRequestMapping</a>
        
        
        <form action="springmvctest/testMethod" method="post">
          <input type="submit" value="testMethod"></input>
        </form>
        
        <a href="springmvctest/testParamsAndHeaders?username=xq&age=10">testParamsAndHeaders</a>
        
        <br></br>
        <a href="springmvctest/testAntPath/sffdfdf/abc">testAntPath</a>
        
         <br></br>
        <a href="springmvctest/testPathVariable/1">testPathVariable</a>
           <br></br>
              <br></br>
        
        <a href="springmvctest/testRest/1">test rest get </a>
        <br/>
         <form action="springmvctest/testRest" method="post">
          <input type="submit" value="test rest post"></input>
        </form>
        <br/>
         <form action="springmvctest/testRestDelete/1" method="post">
          <input type="hidden" name="_method" value="delete">
          <input type="submit" value="test rest delete"></input>
        </form>
        
           <br/>
         <form action="springmvctest/testRestPut/1" method="post">
          <input type="hidden" name="_method" value="put">
          <input type="submit" value="test rest put"></input>
        </form>
         
         
           <br/>
             <a href="springmvctest/testRequestParam?username=徐倩&age=101">testRequestParam</a>
           
           <br/>
             <a href="springmvctest/testRequestHeader">testRequestHeader</a>  
             
             <br/>   <br/>
             <a href="springmvctest/testCookieValue">testCookieValue</a>   
             
             <br/>   <br/>
             
             <form action="springmvctest/testPojo" method="post">
                username:<input type="text" name="username"><br>
                password:<input type="password" name="password"><br>
                email:<input type="text" name="email"><br>
                age:<input type="text" name="age"><br>
                 province:<input type="text" name="address.province"><br>
                  city:<input type="text" name="address.city"><br>
                <input type="submit" value="testPojo"></input>
             </form>
             
                     <br/>   <br/>
             <a href="springmvctest/testServletApi">testServletApi</a>  
             
             
                     <br/>   <br/>
             <a href="springmvctest/testModelAndView">testModelAndView</a> 
             
                    
                     <br/>   <br/>
             <a href="springmvctest/testMap">testMap</a>
             
                     <br/>   <br/>
             <a href="springmvctest/testSessionAttributes">testSessionAttributes</a>
             
                     <br/>   <br/>
                <!-- 
                                   模拟修改操作：
                  1、原始数据为：1 ，tom,123,tom@163.com,12
                  2、密码不能被修改
                  3、表单回显，模拟操作直接在表单 填写对应的属性值
                 -->     
               <form action="springmvctest/testModelAttribute" method="post">
                 <input type="hidden" name="id" value="1"><br>
                  username:<input type="text" name="username" value="tom"><br>
                  email:<input type="text" name="email" value="tom@163.com"><br>
                  age:<input type="text" name="age" value="12"><br>
                  <input type="submit" value="submit"><br>
               </form>
               
               
              <br/>   <br/>
             <a href="springmvctest/testSessionAttributes">testSessionAttributes</a>
             
               <br/>   <br/>
             <a href="springmvctest/testViewAndViewResolver">testViewAndViewResolver</a>
             
              <br/>   <br/>
             <a href="springmvctest/testView">testView</a>
             
              <br/>   <br/>
             <a href="springmvctest/testRedirect">testRedirect</a>
             <br/>   <br/>
             <a href="emps">列表信息</a>
             
             
              <br/>   <br/>
             <a href="springmvctest/testJson" id="testJson">Test Json</a>
             
             <br><br>
             
             	<form action="springmvctest/testHttpMessageConverter" method="POST" enctype="multipart/form-data">
					File: <input type="file" name="file"/>
					Desc: <input type="text" name="desc"/>
					<input type="submit" value="Submit"/>
				</form>
				
				<br><br>
				<a href="springmvctest/testResponseEntity">Test ResponseEntity</a>
				
				<br><br>
				<!--  
				关于国际化:
				1. 在页面上能够根据浏览器语言设置的情况对文本(不是内容), 时间, 数值进行本地化处理
				2. 可以在 bean 中获取国际化资源文件 Locale 对应的消息
				3. 可以通过超链接切换 Locale, 而不再依赖于浏览器的语言设置情况
				
				解决:
				1. 使用 JSTL 的 fmt 标签
				2. 在 bean 中注入 ResourceBundleMessageSource 的示例, 使用其对应的 getMessage 方法即可
				3. 配置 LocalResolver 和 LocaleChangeInterceptor
			-->	
				<br><br>
	           <a href="i18n">I18N PAGE</a>
	
				<br><br>
				<form action="springmvctest/testFileUpload" method="POST" enctype="multipart/form-data">
					File: <input type="file" name="file"/>
					Desc: <input type="text" name="desc"/>
					<input type="submit" value="Submit"/>
				</form>
				
					<br><br>
	            <a href="springmvctest/testExceptionHandlerExceptionResolver?id=0">Test ExceptionHandlerExceptionResolver</a>
	            
	            	<br><br>
	            <a href="springmvctest/testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>
	            
	            <br><br>
	            <a href="springmvctest/testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>
	            <br><br>
	            <a href="springmvctest/testSimpleMappingExceptionResolver?i=21">Test SimpleMappingExceptionResolver</a>
	
	
    </body> 
</html>