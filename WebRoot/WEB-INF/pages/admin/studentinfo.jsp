<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("body").css({
			visibility : "visible"
		});
		$('#save').click(function() {
			var v = $('#form').form("validate");
			if (v) {
				$('#form').submit();
			}
		});
	});
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="north" style="height:31px;overflow:hidden;" split="false"
		border="false">
		<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
				plain="true">保存</a>
		</div>
	</div>
	<div region="center" style="overflow:auto;padding:5px;" border="false">
		<form id="form" method="post"
			action="${pageContext.request.contextPath}/user_save.action">
			<table class="table-edit" width="95%" align="center">
				<tr class="title">
					<td colspan="4">基本信息</td>
				</tr>
				<tr>
					<td>姓名:</td>
					<td><input type="text" name="student.name" class="easyui-validatebox"
						/></td>
					<td>学号:</td>
					<td><input type="text" name="student.sno" class="easyui-validatebox"
						/></td>
						<td>账号:</td>
					<td><input type="text" name="account" class="easyui-validatebox"
						/></td>
				</tr>
				<tr>
					<td>电话号码:</td>
					<td><input type="text" name="student.tel" class="easyui-validatebox"
						 /></td>
						<td>邮件</td>
					<td><input type="text" name="student.email"
						class="easyui-validatebox" /></td>
				</tr>

				<tr class="title">
					<td colspan="4">其他信息</td>
				</tr>
				<tr>
					<td>QQ号</td>
					<td colspan="3"><input type="text" name="student.qq"
						class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>微信号</td>
					<td colspan="3"><input type="text" name="student.weixin"
						class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>角色:</td>
					<td id="roles" colspan="3">
						<script type="text/javascript">
							$(function (){
								var url ="${pageContext.request.contextPath}/role_findAll.action";
								$.post(url,{},function(data){
									for(var i=0;i<data.length;i++){
										$("#roles").append('<input type="checkbox" name="roleIds" value="'+data[i].id+'">'+data[i].name);
									}
								},"json");
							});
						</script>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>