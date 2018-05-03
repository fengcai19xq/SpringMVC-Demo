<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>success page</h1>
<h1>time:<%=request.getAttribute("time") %></h1>
${requestScope.time }
<br><br><br>
names:<%=request.getAttribute("names") %><br><br><br>

request user :<%=request.getAttribute("user") %><br><br><br>
session user :<%=session.getAttribute("user") %><br><br><br>

request school :<%=request.getAttribute("school") %><br><br><br>
session school :<%=session.getAttribute("school") %><br><br><br>

	<br><br>
	
	<fmt:message key="i18n.username"></fmt:message>
	<br><br>
	
	<fmt:message key="i18n.password"></fmt:message>
	<br><br>
	
</body>
</html>