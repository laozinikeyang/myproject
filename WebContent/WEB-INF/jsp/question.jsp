<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	java.util.Date nowDate = new java.util.Date();
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	String spdfDate = sdf.format(nowDate);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/thinksite.css">
<script src="js/html5shiv.min.js"></script>
<script src="js/respond.min.js"></script>
<script src="jedate/jedate.js"></script>
</head>
<body>
	<!-- 这是导航条 -->
	<jsp:include page="./menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-12 ">
				<button class="btn btn-primary" data-toggle="collapse"
					data-target="#collapseNewQuestion" aria-expanded="false"
					aria-controls="collapseNewQuestion">新建</button>
				<button class="btn btn-success" onclick="delCheckModal()">删除</button>
				<button class="btn btn-primary" data-toggle="collapse"
					data-target="#collapseFind" aria-expanded="false"
					aria-controls="collapseFind">搜索</button>
			</div>
		</div>
		<div class="collapse" id="collapseNewQuestion">
			<div class="well">
				<form class="form-inline" method="post" id="newQuestion">
					<div class="form-group">
						<label for="mainTitle">问卷标题:</label> <input type="text"
							class="form-control" id="mainTitle" name="mainTitle"
							placeholder="关于某某什么的问卷调查">
					</div>
					<div class="form-group date" id="datetimepicker">
						<label for="mainEndtime">截止日期:</label> <input type="text"
							class="form-control" id="mainEndtime" name="mainEndtime"
							readonly="readonly">
					</div>
					<script type="text/javascript">
							jeDate({
								dateCell:"#mainEndtime",
								format:"YYYY-MM-DD",
								isinitVal:true,
								isTime:false, //isClear:false,
								minDate:"<%=spdfDate%>
						",
							okfun : function(val) {
								alert(val)
							}
						})
					</script>
					<button type="submit" class="btn btn-info">保存</button>
				</form>
			</div>
		</div>

	</div>
	<footer class="footer navbar-fixed-bottom ">
		<div class="container" style="background-color: #000000">
			<div class="row">
				<div class="col-sm-12">
					<span><a href="http://www.mrkj.com/">明日科技</a></span> | <span>Copyright
						&copy; <a href="http://www.mrkj.com/">吉林省明日科技有限公司</a>
					</span> | <span>吉ICP备16003039号-1</span> <span>站长QQ:80303857</span>
				</div>
			</div>
		</div>
	</footer>

</body>
</html>