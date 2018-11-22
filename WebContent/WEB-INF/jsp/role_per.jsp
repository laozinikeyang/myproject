<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=basePath%>css/thinksite.css">
<script src="<%=basePath%>js/html5shiv.min.js"></script>
<script src="<%=basePath%>js/respond.min.js"></script>
<script src="<%=basePath%>jedate/jedate.js"></script>

<link rel="stylesheet"
	href="<%=basePath%>zTree_v3-master/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>zTree_v3-master/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>zTree_v3-master/js/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>zTree_v3-master/js/jquery.ztree.exedit.min.js"></script>
<script type="text/javascript">
    var setting = {
			async: {
				enable: true,
				url:"<%=basePath%>role/getTreeCheck.do?roleName=${entity.roleName}"
			},data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: "0"
				}
			},check: {
				enable: true
			},
			callback: {
				beforeClick: function(treeId, treeNode) {
					return true;
				}
			}
		};

    	$(document).ready(function(){
			$.fn.zTree.init($("#tree"), setting);
		});
    	
    	function tijiao(){
    		var zTree = $.fn.zTree.getZTreeObj("tree");
    		var nodes = zTree.getCheckedNodes(true);
    		var parm = "";
    		for (var i = 1;i<nodes.length;i++){
    				parm+=nodes[i].id;
    			if (nodes.length-i>1){
    				parm+=',';
    			}
    		}
    		 $.ajax({
    		     type: 'POST',
    		     url: "<%=basePath%>role/addRolePer.do",
    		     data: {
    		    	roleId:${entity.roleId},
    		    	perparm:parm,
    		    	roleName:'${entity.roleName}'
    		     },
    		     success: function(data){
    		    	if(data.success==='OK'){
    		    		alert (data.msg)
    		    	}else{
    		    		alert (data.msg)
    		    	}
    		     },
    		     dataType: 'json'
    		}); 
    	}
    </script>
</head>
<body>
	<div class="container">
		<ul id="tree" class="ztree" ></ul>
		
		<p class="text-center"><a href="javascript:void(0)" class="btn btn-primary" onclick="tijiao()">提交</a></p>
	</div>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
   <script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>