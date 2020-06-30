<%--
  Created by IntelliJ IDEA.
  User: 周伟桐
  Date: 2020/4/10
  Time: 13:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<form action="saveRoleDept.do" method="post">
    <h1>目前正在给--${rd.rname}--角色选择部门</h1>
    <input type="hidden" name="rid" value="${rd.rid}"/>
    <c:forEach items="${list}" var="d">
        <input type="radio" name="deptid" value="${d.deptid}" ${d.deptid==rd.deptBean.deptid?'checked':''}/>${d.dname}<br>
    </c:forEach>
    <input type="submit" value="保存">
</form>
</body>
</html>
