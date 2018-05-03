<%@ page language="java" session="false" contentType="text/html; charset=UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html> 
    <head> 
        <title>Adobocode : Sample Spring MVC using Forms</title> 
    </head> 
  
    <body> 
        <h2>Adobocode : Person Form</h2> 
        <form:form modelAttribute="person"> 
            <form:hidden path="id" /> 
            <fieldset> 
                <table> 
                    <tr> 
                        <td>Name</td> 
                        <td><form:input path="name" /></td> 
                    </tr> 
                    <tr> 
                        <td>Age</td> 
                        <td><form:input path="age" /></td> 
                    </tr> 
                    <tr> 
                        <td>Address</td> 
                        <td><form:input path="address" /></td> 
                    </tr> 
                    <tr> 
                        <td></td> 
                        <td> 
                            <input type="submit" id="save" name="_eventId_save" value="Save" /> 
                            <input type="submit" onClick="history.go(-1);" name="_eventId_cancel" value="Cancel" /> 
                        </td> 
                    </tr> 
                </table> 
            </fieldset> 
        </form:form> 
    </body> 
</html>