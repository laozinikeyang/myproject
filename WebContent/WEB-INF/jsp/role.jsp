<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
				url:"<%=basePath%>role/getTree.spring"
			},data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: "0"
				}
			},
			callback: {
				beforeClick: function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("tree");
					$("#testIframe").attr("src","<%=basePath%>role/editView.spring?roleId="+treeNode.id+"&rolePid="+treeNode.pId+"&roleName="+treeNode.type+"&roleDes="+treeNode.name);
					return true;
				},
				onRightClick: OnRightClick
			}
		};
		//显示菜单
	    function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
	
	    function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
	
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}

    	$(document).ready(function(){
			$.fn.zTree.init($("#tree"), setting);
			zTree = $.fn.zTree.getZTreeObj("tree");
			rMenu = $("#rMenu");
		});
		
    	function addTreeNode(){
    		if (zTree.getSelectedNodes()[0]) {
				var node = zTree.getSelectedNodes()[0];
				$("#testIframe").attr("src","<%=basePath%>role/addView.spring");
			} else {
				//zTree.addNodes(null, newNode);
			}
    	}
    	
    	function editTreeNode(){
    		if (zTree.getSelectedNodes()[0]) {
				var node = zTree.getSelectedNodes()[0];
				$("#testIframe").attr("src","<%=basePath%>role/editFieldView.do?roleId="+node.id+"&rolePid="+node.pId+"&roleName="+node.name+"&roleDes="+node.type);
			} else {
				//zTree.addNodes(null, newNode);
			}
    	}
    	
    	function removeTreeNode(){
			var node = zTree.getSelectedNodes()[0];
    		
    		$.ajax({
    			url:"<%=basePath%>role/remove/"+node.id,
    			type:'GET',
    			success:function (data){
    				if (data.success){
    					alert ("删除成功");
    					window.location.reload();
    				}else{
    					alert ('删除失败');
    				}
    			}
    		}); 
    	}
    	
    </script>

<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	text-align: left;
	padding: 1px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 1px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>
</head>
<body>
	<!-- 这是导航条 -->
	<jsp:include page="./menu.jsp"></jsp:include>

	<div class="container">
		<table border="0" height="100%" align="left">
			<tr>
				<td width="260px" align="left" valign="top"
					style="BORDER-RIGHT: #999999 1px dashed">
					<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>
				</td>
				<td width="770px" align="left" valign="top"><iframe
						id="testIframe" name="testIframe" frameborder="0" scrolling="AUTO"
						width="100%" height="600px" SRC="<%=basePath%>jsp/treeIframe.jsp"></iframe>
				</td>
			</tr>
		</table>

		<div id="rMenu">
			<ul>
				<li id="m_add" onclick="addTreeNode();">增加</li>
				<li id="m_edit" onclick="editTreeNode();">修改</li>
				<li id="m_del" onclick="removeTreeNode();">删除</li>
			</ul>
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

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
	</script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<%=basePath%>bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>