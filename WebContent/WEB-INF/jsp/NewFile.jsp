<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
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
<body style="background-color: #fefefe;">
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

		<div class="collapse" id="collapseFind">
			<div class="well">
				<form action="" class="form-inline" method="get">
					<label for="mainTitleSS">问卷标题：</label> <input name="mainTitle"
						id="mainTitleSS" type="text" class="form-control" placeholder="" />
					<label for="mainCreatSS">创建时间：</label> <input name="mainStartTime"
						id="mainCreatSS" type="text" class="form-control" placeholder="" />
					<label for="mainEndtimeSS">截止时间：</label> <input name="mainOverTime"
						id="mainEndtimeSS" type="text" class="form-control" placeholder="" />
					<button class="btn btn-primary" type="submit">搜索</button>
				</form>
				<script type="text/javascript">
					jeDate({
						dateCell : "#mainCreatSS",
						format : "YYYY-MM-DD",
						isinitVal : true,
						isTime : false, //isClear:false,
						minDate : "2000-01-01",
						okfun : function(val) {
							alert(val)
						}
					})
					jeDate({
						dateCell : "#mainEndtimeSS",
						format : "YYYY-MM-DD",
						isinitVal : true,
						isTime : false, //isClear:false,
						minDate : "2000-01-01",
						okfun : function(val) {
							alert(val)
						}
					})
				</script>
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

	<script src="js/jquery.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>