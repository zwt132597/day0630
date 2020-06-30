<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/9
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../../js/jquery-1.8.2.js"></script>
</head>
<body>
    <form action="/saveUser.do" method="get">
        <h2>添加新用户信息列表</h2>
       姓名： <input type="text" name="username"placeholder="请输入你的姓名"><br>
       密码： <input type="text" name="password" value="123" placeholder="默认为123"readonly><br>
       年龄：<input type="number" name="age" placeholder="请输入你的年龄"><br>
       出生年月：<input type="date" name="birthday" placeholder="请选择你的出生年月"><br>
        分配部门:<select name="birthday1" onchange="getrolelist(this)">
                    <option value="-1">--请选择部门--</option>
                    <c:forEach var="d" items="${dlist}">
                        <option value="${d.deptid}">${d.dname}</option>
                    </c:forEach>
                </select><br>
        分配角色:<select name="birthday2" id="rid">
                    <option value="-1">--请选择角色--</option>
                    <c:forEach var="r" items="${rlist}">
                        <option value="${r.rid}">${r.rname}</option>
                    </c:forEach>
            </select><br>
        <input type="submit" value="保存">
    </form>
</body>
<script>
    function getrolelist (obj) {

        //ajax去获取列表在查询，异步操作
         $.post("getRlistJosn.do",{deptid:obj.value},function (data) {
             var rselect  = $("#rid");
             rselect.html("<option value='-1'>--请选择角色--</option>");
             var rlist = data;
             for (var i = 0; i <rlist.length ; i++) {
                 //要拼接一个option，在之前要先把原来的删掉
                 rselect.append("<option value="+rlist[i].rid+">"+rlist[i].rname+"</option>")
             }
         })
    }
</script>
</html>
