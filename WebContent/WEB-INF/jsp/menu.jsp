<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

	<div class="container" style="padding-top: 45px;">
		<div class="row">
			<div class="col-sm-12 col-md-12 col-lg-12">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<div class="navbar-header">
							<button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
								<span class="sr-only">明日科技</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand">明日 Office Auto</a>
						</div>
						<div id="navbar" class="navbar-collapse collapse">
							<ul class="nav navbar-nav">
							 
								<li><a href="<%=basePath %>index.spring">首页</a></li>
							<shiro:hasPermission name="jsszDH">
							
								<li><a href="<%=basePath %>login/user.spring">权限设置1</a></li>
								</shiro:hasPermission>
							<shiro:hasPermission name="wjdcDH">
								<li><a href="<%=basePath %>question.spring">维护问卷</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="sfqxDH">
								<li><a href="<%=basePath %>role/mainView.spring">角色设置</a></li>
								</shiro:hasPermission>
								<shiro:hasPermission name="wjdcME">
								<li><a href="<%=basePath %>question/me.spring">我的问卷</a></li>
								</shiro:hasPermission>
									<shiro:hasRole name="admin">
								<li><a href="<%=basePath %>per/mainView.spring">权限设置</a></li>
							
								
								</shiro:hasRole>
							</ul>
							<ul class="nav navbar-nav navbar-right">
								<li>
									<a href="<%=basePath %>login/logout.spring" target="_self">退出登录</a>
								</li>
							</ul>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>