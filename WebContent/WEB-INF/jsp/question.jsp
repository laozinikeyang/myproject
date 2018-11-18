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
	String socketPath = request.getServerName() + ":" + request.getServerPort() + path + "/";
	
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
								minDate:"<%=spdfDate%>",
								okfun:function(val){alert(val)}
							})
						</script>
					<button type="submit" class="btn btn-info">保存</button>
				</form>
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
								dateCell:"#mainCreatSS",
								format:"YYYY-MM-DD",
								isinitVal:true,
								isTime:false, //isClear:false,
								minDate:"2000-01-01",
								okfun:function(val){alert(val)}
							})
							jeDate({
								dateCell:"#mainEndtimeSS",
								format:"YYYY-MM-DD",
								isinitVal:true,
								isTime:false, //isClear:false,
								minDate:"2000-01-01",
								okfun:function(val){alert(val)}
							})
					</script>
			</div>
		</div>
		<form id="delCheckForm" method="post">
			<table class="table table-bordered table-hover">
			<tr class="danger">
				<th>
					<h5 >序号:</h5>
				</th>
				<th>
					<h5 >选择:</h5>
				</th>
				<th>
					<h5 >问卷标题:</h5>
				</th>
				<th>
					<h5 >创建时间:</h5>
				</th>
				<th>
					<h5 >截止时间:</h5>
				</th>
				<th>
					<h5 >是否发布:</h5>
				</th>
				<th>
					<h5 >发布人:</h5>
				</th>
				<th width="256px">
					<h5 >操作:</h5>
				</th>
			</tr>
			<c:choose>
				<c:when test="${not empty mainList }">
					<c:forEach items="${mainList }" var="item" varStatus="vs">
						<tr class="success">	
							<td>
								<span>${vs.index + 1 }</span>
							</td>
							<td>
								<input type="checkbox" name="id" value="${item.mainId }">
							</td>
							<td>
								<span>${item.mainTitle }</span>
							</td>
							<td>
								<span><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${item.mainCreat }"/></span>
							</td>
							<td>
								<span><fmt:formatDate pattern="yyyy-MM-dd" value="${item.mainEndtime }"/></span>
							</td>
							<td>
								<span>
									<c:if test="${item.mainIsuse eq 'y' }">
										已发布
									</c:if>
									<c:if test="${item.mainIsuse eq 'n' }">
										未发布
									</c:if>
								</span>
							</td>
							<td>
								<span>${item.mainCreatuser }</span>
							</td>
							<td class="text-center">
								<div class="input-group-btn" id="ssdiv">
									<button class="btn btn-primary" type="button" onclick="delModal('${item.mainId }')">删除</button>
									<a href="<%=basePath %>question/edit/${item.mainId }.spring" class="btn btn-info">编辑</a>
									<button type="button" class="btn btn-warning" onclick="actionModel('${item.mainId }')">发布</button>
									<button type="button" class="btn btn-warning" onclick="pauseModel('${item.mainId }')">停止</button>
									<button type="button" class="btn btn-danger" onclick="copyQuestion('${item.mainId }')">复制</button>
									<a class="btn btn-success" href="<%=basePath %>question/statistics/${item.mainId }.spring" target="_blank" >查看结果</a>
								</div>
							</td>
						</tr>	
					</c:forEach>
				</c:when>
			</c:choose>
				<tr>
					<td colspan="8">
	 					${currentPage }
	 				</td>
	 			</tr>
			</table>
		</form>

	</div>
<div class="container">
		<div class="modal fade" id="delModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<p>您确定要删除该条数据吗？</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="deleteQyestion()">确定</button>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="tempId" value="">
	</div>
	
	<div class="container">
		<div class="modal fade" id="delCheckModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<p>您确定要删除这些数据吗？</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="submitFormDel('#delCheckForm','<%=basePath%>question/delCheck.spring')">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="modal fade" id="actionModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="actionModalText">确定要把这份问卷发布给小伙伴吗?</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="actionStart()">确定</button>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="tempActionMainId" />
	</div>
	
	<div class="container">
		<div class="modal fade" id="pauseModel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="pauseModelText">确定要停止发布问卷吗?</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="pause()">确定</button>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="temppauseMainId" />
	</div>
	<div class="container">
		<div class="modal fade" id="alertOK">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span>
							<span class="sr-only">Close</span>
						</button>
					</div>
					<div class="modal-body">
						<p id="alertOKText">成功</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal" onclick="javascript:location.reload();">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		var basePath = '<%=basePath%>';
		var socketPath = '<%=socketPath%>';
	</script>
	<!-- jquery.min.js必须放bootstrap-3.3.7-dist/js/bootstrap.min.js上面 -->
	<script src="js/jquery.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/question.js?v=5.1"></script>
</body>
</html>