<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'page.jsp' starting page</title>
  </head>
  <script type="text/javascript">
   				//1.获取当前页的元素
   				//2.判断点击的业务
   				//3.将当前页元素赋值为指定业务
   				//4.将表单提交，调用查询方法
   			function toPage(flag){
   				var pageNumber = document.getElementById("pageNumber");
   				var pageSumNumber = document.getElementById("pageSumNumber").value;
   				if(flag == 1){
   					if(pageNumber.value == 1){
   						alert("已经为首页！");
   						return ;
   					}
   					pageNumber.value = 1;
   				}else if(flag == 2){
   					if(pageNumber.value == 1){
   						alert("已经为首页！");
   						return ;
   					}
   					pageNumber.value = pageNumber.value - 1;
   				}else if(flag == 3){
   					//判断，如果为最后一页，则弹出提示框
   					 if(pageNumber.value == pageSumNumber){
   					 	alert("已经为最后一页！");
   					 	return ;
   					 }
   					 pageNumber.value = pageNumber.value +1;
   				}
   				//使用document.forms获取页面中所有的表单
   				//使用document.forms[0]获取页面中第一个表单
   				//使用document.forms[0].submit()调用了表单提交的方法
   				document.forms[0].submit();
   			}
   		</script>
  <body>
    	<center>
   				当前 <input type="text"  name="pageNum"  style="width:30px" id="pageNumber"  value="${pageNum }"/> 页
   				共有 ${totalPage } 页
   				<input type="hidden"  id="pageSumNumber"  value="${page.pageSumNumber }"/>
   				共有 ${count } 条
   				<a href="javaScript:toPage(1)">首   页</a>
   				<a href="javaScript:toPage(2)">上一页</a>
   				<a href="javaScript:toPage(3)">下一页</a>
   				每页 <input type="text"  name="pageSize"  style="width:30px"  value="${pageSize }"/> 条
   				<input type="submit"  value="跳转"/>
   		</center>
  </body>
</html>
