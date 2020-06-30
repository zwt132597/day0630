<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${msg}
<form action="getLogin.do" method="post">
    用户名:<input name="username" type ="text"/><br>
    密码:<input name="password" type ="password"/><br>
    <input type="submit" value="登录"/>
</form>
</body>
</html>