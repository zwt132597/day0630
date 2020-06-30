<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

<link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value='/csstree/zTreeStyle.css'/>" type="text/css">

<!-- tree.js 树的js文件，jquery不能删，jquery和tree的结合的 -->
<script type="text/javascript" src="<c:url value='/js/tree.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.ztree.all-3.3.js'/>"></script>

<script type="text/javascript">

	var setting={
		view:{
			showLine:true//是否显示线，默认为true
		},
		data:{
			key:{
				name:"pname"
			},
			simpleData:{
				enable:true,//开启树的层级结构
				idKey:"id",//设置树节点id，节点参数id必须与之相匹配，即在Actiob中穿过来的值必须与之相对应
				pIdKey:"pid"//设置pid，节点参数pid必须与之相匹配
			}
		},
		check:{
			enable:true
		},
		callback:{
			onClick:zTreeClick//定义节点点击的事件为单击事件
		}
	};
	
	var zNodes =${json};//后台传递过来的json数据

	var currentSelectedNode; //定义全局变量  查看当前节点

	function zTreeClick(event, treeId, treeNode) {

	   var treeObj = $.fn.zTree.getZTreeObj("treeClass");
		
	   currentSelectedNode = treeObj.getNodeByTId(treeNode.tId);
	 
	  var path =currentSelectedNode.url;

	  parent.frames[3].location.href = path;

	};
	$(document).ready(function(){
		$.fn.zTree.init($("#treeClass"),setting,zNodes);

	});
</script>
</head>
<body>
		<form action="saveRolePower.do" method="post">
			<input type="hidden" name="rid" value="${rid}">
			<input type="hidden" name="ids">
			<input type="button" value="保存" onclick="saverolepower()"/>
		</form>
		<div id="nodes">
		        <ul id="treeClass" class="ztree"></ul>
         </div>
</body>
	<script>
		function saverolepower() {
            var treeObj = $.fn.zTree.getZTreeObj("treeClass");
            var nodes = treeObj.getCheckedNodes(true);
            var ids="";
            for(var x=0;x<nodes.length;x++){
                ids+=nodes[x].id+",";
			}
			if(ids.length==0){
			    if(window.confirm("改角色没有选择权限，您确定要提交吗?")){
                    $("[name=ids]").val(ids);
                    document.forms[0].submit();
				}
			}else{
			    ids= ids.substring(0,ids.length-1);
                $("[name=ids]").val(ids);
                document.forms[0].submit();
			}

        }
	</script>
</html>