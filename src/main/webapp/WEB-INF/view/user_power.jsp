<%--
  Created by IntelliJ IDEA.
  User: 周伟桐
  Date: 2020/4/13
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>工员权限</td>
    </tr>
    <tr>
        <c:forEach items="${power}" var="p">
        <td>${p.pname}</td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
