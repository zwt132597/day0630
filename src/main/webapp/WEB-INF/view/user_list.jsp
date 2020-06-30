<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/4/8
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
</head>

<body>
<form action="getUserList.do" method="post" >
    姓名:<input type="text" name="username"><br>
    出生年月：<input type="date" name="birthday1">----<input type="date" name="birthday2">
    <input type="submit" value="查询">
</form>
        <table>
            <tr>
                <th>编码</th>
                <th>姓名</th>
                <th>密码</th>
                <th>部门</th>
                <th>角色</th>
                <th>年龄</th>
                <th>生日</th>
                <th>操作</th>
            </tr>
            <c:forEach var="u" items="${list}">
                <tr>
                    <td>
                        ${u.id}
                    </td>
                    <td>
                            ${u.username}
                    </td>
                    <td>
                            ${u.password}
                    </td>
                    <td>
                        ${u.deptBean.dname}
                    </td>
                    <td>
                        ${u.roleBean.rname}
                    </td>
                    <td>
                            ${u.age}
                    </td>
                    <td>
                        <f:formatDate value="${u.birthday}" pattern="yyyy-MM-dd"></f:formatDate>

                    </td>
                    <td>
                        <a href="toUserDeptRole.do?id=${u.id}"><button>给员工分配部门和角色</button></a>
                        <a href="deleteUserByid.do?id=${u.id}"><button>删除</button></a>
                        <a href="toUpdateUser.do?id=${u.id}"><button>修改</button></a>
                        <a href="getPowerUserByid.do?id=${u.id}"><button>查看权限</button></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="toinsertUser.do"><button>添加新用户</button></a>
<jsp:include page="/page.jsp"></jsp:include>
</body>
</html>
