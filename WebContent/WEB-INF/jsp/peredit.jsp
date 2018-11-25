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
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
    <c:if test="${success =='OK'}">
    	<script type="text/javascript">
    		$(function (){
    			alert ('${msg}');
    		})
    	</script>
    </c:if>
    <c:if test="${success == 'NO'}">
    	<script type="text/javascript">
    		$(function (){
    			alert ('${msg}');
    		})
    	</script>
    </c:if>
</head>
<body>
	
	<div class="container">
		<form action="<%=basePath %>per/edit.spring" class="form-horizontal">
			<!--以下录入表单-->
			<input type="hidden" name="permissionId" value="${entity.permissionId }">
			<input type="hidden" name="permissionMenuPid" value="${entity.permissionMenuPid }">
		    <div class="form-group">
		        <label for="permissionName" class="contro-label">权限:</label>
		        <input class="form-control" value="${entity.permissionName }" id="permissionName" name="permissionName" type="text"/>
		    </div>
		    <div class="form-group">
		        <label for="permissionMenuName" class="contro-label">描述:</label>
		    	<input class="form-control" value="${entity.permissionMenuName }" id="permissionMenuName" name="permissionMenuName" type="text"/>
		    </div>
		    
			<p class="text-center"><button type="submit" class="btn btn-primary">提交</button></p>

		</form>
	</div>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
   <script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>