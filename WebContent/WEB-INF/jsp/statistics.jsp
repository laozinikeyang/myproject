<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<table class="table">
			<tr class="danger">
				<th>
					<h5 style="padding-left: 40px">${questionnaire.main.mainTitle}</h5>
				</th>
			</tr>
			<c:choose>
				<c:when test="${not empty questionnaire.question }">
					<c:forEach items="${questionnaire.question }" var="question" varStatus="vs">
						<tr class="info">
							<th >
								<h4 style="padding-left: 40px"><small>Question ${vs.index+1 }：${question.questionTitle }</small></h4>
							</th>
						</tr>
						<tr class="success">
							<c:choose>
								<c:when test="${not empty questionnaire.answer }">
									<td ><%//答案开始咯 %>
									<br />
									<c:forEach items="${questionnaire.answer}" var="answer" varStatus="vs">
										<c:if test="${question.questionId eq answer.questionId }">
											<c:if test="${answer.answerType eq 'n'}"><%//这里是文字类答案 %>
												<blockquote>
													<p style="padding-left: 40px">总共<b>${answer.answerValue }</b>个人选择</p>
													<footer style="padding-left: 40px"><b>${answer.answerDestype }</b></footer>
												</blockquote>
											</c:if>
											
											<c:if test="${answer.answerType eq 'y'}"><%//这里是图片类答案 %>
												<blockquote>
													<p style="padding-left: 40px">总共<b>${answer.answerValue }</b>个人选择</p>
													<footer style="padding-left: 40px"><a src="<%=basePath %>${answer.answerText }" >${answer.answerDestype }</a></footer>
												</blockquote>
											</c:if>
										</c:if>
									</c:forEach>
										<br />
									</td>
								</c:when>
								<c:otherwise>
									<td style="padding-left: 40px">
										<span>暂无任何数据</span>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="warning">
						<td>
							<span>暂无任何数据</span>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	
	<div class="container">
		<table class="table">
			<c:choose>
				<c:when test="${not empty questionnaire.msgs }">
					<c:forEach items="${questionnaire.msgs }" var="item" varStatus="vs">
						<c:if test="${vs.index/2 != 0  }">
							<tr class="success">
								<td>
									<dl>
										<dt>${item.msgCreatuser }</dt>
										<dd>${item.msgText }</dd>
									</dl>
								</td>
							</tr>
						</c:if>
						
						<c:if test="${vs.index/2 == 0  }">
							<tr class="info">
								<td>
									<dl>
										<dt>${item.msgCreatuser }</dt>
										<dd>${item.msgText }</dd>
									</dl>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="danger">
						<td>
							<dl>
								<dt>信息：</dt>
								<dd>暂无任何数据</dd>
							</dl>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			<!-- <tr class="warning">
				<td>
					<dl>
						<dt>于国良</dt>
						<dd>整体效果很不错</dd>
					</dl>
				</td>
			</tr>
			<tr class="success">
				<td>
					<dl>
						<dt>于国良</dt>
						<dd>整体效果很不错</dd>
					</dl>
				</td>
			</tr> -->
		</table>
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
	</script>
	<!-- jquery.min.js必须放bootstrap-3.3.7-dist/js/bootstrap.min.js上面 -->
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="<%=basePath %>js/guid.js"></script>
	<script src="<%=basePath%>js/question.js?v=5.1"></script>
</body>
</html>