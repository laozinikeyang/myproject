<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
   meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link href="<%=basePath%>bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="<%=basePath%>stylesheet" href="css/thinksite.css">
<script src="<%=basePath%>js/html5shiv.min.js"></script>
<script src="<%=basePath%>js/respond.min.js"></script>
<script src="<%=basePath%>jedate/jedate.js"></script>
  </head>
<body style="background-color: #fefefe;">
	<!-- 这是导航条 -->
	<jsp:include page="./WEB-INF/jsp/menu.jsp"></jsp:include>


	<div class="container" style="padding-bottom: 70px;">
		您的操作步骤出问题了.
	</div>
	
	<footer class="footer navbar-fixed-bottom ">
	    <div class="container" style="background-color: #000000">
	    	<div class="row">
	                <div class="col-sm-12">
	                    <span><a href="http://www.mrkj.com/">明日科技</a></span> | 
	                    <span>Copyright &copy; <a href="http://www.mrkj.com/">吉林省明日科技有限公司</a></span> | 
	                    <span>吉ICP备16003039号-1</span>
	                    <span>站长QQ:80303857</span>
	                </div>
	            </div>
	    </div>
	</footer>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript">
		var basePath = '<%=basePath%>';
		var socketPath = '<%=socketPath%>';
	</script>
    <script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>