<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  
<jsp:useBean id="personList" scope="request" type="java.util.List" /> 
<html> 
    <head> 
        <title>Adobocode : Sample Spring MVC using JSTL iteration</title> 
    </head> 
    <body> 
        <h2>Adobocode : Person List</h2> 
        <table border="1"> 
            <tr> 
                <th>Id</th> 
                <th>Name</th> 
                <th>Age</th> 
                <th>Address</th> 
            </tr> 
            <c:forEach var="p" items="${personList}"> 
            <tr> 
                <td> 
                    <c:url var="editUrl" value="personForm.do"> 
                                    <c:param name="personId" value="${p.id}" /> 
                            </c:url> 
                    <a href='<c:out value="${editUrl}"/>'>${p.id}</a> 
                </td> 
                <td><c:out value="${p.name}" /></td> 
                <td>${p.age}</td> 
                <td>${p.address}</td> 
            </tr> 
            </c:forEach> 
        </table> 
    </body>