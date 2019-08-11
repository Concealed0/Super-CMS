<%@ page language="java" contentType="text/html; charset=utf-8"
     pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><title>Insert title here</title>
</head>
<body>
<table>
            <tr>
                <th>userI</th>
                <th>groupId</th>
                <th>111</th>
                <th>2222</th>

            </tr>
            <c:forEach items="${adminuser}" var="user">
                <tr>
                    <td>${user.userId }</td>
                    <td>${user.groupId }</td>
                    <td>${user.username }</td>
                    <td>${user.ceshi }</td>          
                </tr>
            </c:forEach>
        </table>
</body>
</html>