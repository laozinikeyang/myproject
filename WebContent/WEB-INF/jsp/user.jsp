<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String socketPath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
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
	<jsp:include page="./menu.jsp"></jsp:include>

	<div class="container">
		<%-- <button class="btn btn-primary" data-toggle="collapse" data-target="#collapseNewQuestion" aria-expanded="false" aria-controls="collapseNewQuestion">新建用户</button>
		<div class="collapse" id="collapseNewQuestion">
			<div class="well">
				<form class="form-inline" method="post" id="newQuestion" action="<%=basePath %>login/register">
					<div class="form-group">
						<label for="username">用户名</label>
						<input type="text" class="form-control" id="username" name="username" placeholder="" maxlength="20">
					</div>
					<div class="form-group">
						<label for="password">密码</label>
						<input type="text" class="form-control" id="password" name="password" placeholder="" maxlength="20">
					</div>
					<div class="form-group">
						<label for="email">电子邮件</label>
						<input type="email" class="form-control" id="email" name="email" placeholder="" maxlength="50">
					</div>
					<div class="form-group">
						<label for="wxname">真是姓名</label>
						<input type="text" class="form-control" id="wxname" name="wxname" placeholder="" maxlength="10">
					</div>
					<button type="submit" class="btn btn-success">保存</button>
				</form>
			</div>
		</div> --%>
		<form id="delCheckForm" method="post">
			<table class="table table-bordered table-hover">
			<tr class="danger">
				<th>
					<h5 >序号:</h5>
				</th>
				<th>
					<h5 >用户名:</h5>
				</th>
				<th>
					<h5 >电子邮件:</h5>
				</th>
				<th>
					<h5 >姓名:</h5>
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
								<span>${vs.index }</span>
							</td>
							<td>
								<span>${item.username }</span>
							</td>
							<td>
								<span>${item.email }</span>
							</td>
							<td>
								<span>${item.wxname }</span>
							</td>
							<td class="text-center">
								<div class="input-group-btn" id="ssdiv">
									<input type="hidden" id="temoLoginId" >
									<button class="btn btn-warning" type="button" onclick="delModal('${item.login_id }')">删除</button>
									<button type="button" class="btn btn-success dropdown-toggle" style="width: 70px;" data-toggle="dropdown" id="s_${item.login_id }">
											<c:choose>
												<c:when test="${not empty roles }">
													<c:forEach items="${roles }" var="temp" varStatus="vs">
														<c:if test="${temp.type eq item.role_name }">
															${temp.name}
														</c:if>
													</c:forEach>
												</c:when>
											</c:choose>
										</button>
										<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" >
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<c:choose>
												<c:when test="${not empty roles }">
													<c:forEach items="${roles }" var="temp" varStatus="vs">
														<c:if test="${temp.id ne 0}">
															<li><a href="javascript:void(0)" onclick="setRole('${temp.id }','${temp.name }','${item.login_id}','${item.username}')">${temp.name }</a></li>
															<li class="divider"></li>
														</c:if>
													</c:forEach>
												</c:when>
											</c:choose>
										</ul>
									
								</div>
							</td>
						</tr>	
					</c:forEach>
				</c:when>
			</c:choose>
			
				<tr>
					<td colspan="5">
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
						<p>您确定要删除这个用户吗？</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
						<button class="btn btn-primary" onclick="deleteUser()">确定</button>
					</div>
				</div>
			</div>
		</div>
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
						<p id="alertOKText">权限更改成功</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
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
    <script src="<%=basePath %>js/user.js?v=0.5"></script>
</body>
</html>