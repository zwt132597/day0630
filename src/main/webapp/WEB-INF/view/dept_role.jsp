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
<form action="saveDeptRole.do" method="post">
    <h1>目前正在给--${db.dname}--部门选择角色</h1>
    <input type="hidden" name="deptid" value="${db.deptid}"/>
    <c:forEach items="${list}" var="r">
        <input type="checkbox" name="rids" value="${r.rid}" <c:forEach var="rr" items="${rlist}">${r.rid==rr?'checked':''}</c:forEach>/>${r.rname}<br>
    </c:forEach>
    <input type="submit" value="保存">
</form>
</body>
</html>
