<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- 导入jquery核心类库 -->
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
	<!-- 导入easyui类库 -->
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath }/css/default.css">
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script
			src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
			type="text/javascript"></script>
	<script src="${pageContext.request.contextPath }/js/form.js"
			type="text/javascript"></script>
<script type="text/javascript">
	$(function() {

		$('#grid').datagrid({

			url : '../xueqi/xueqi.json',
			//datagrid的唯一辨识
			idField : 'grid',
			width : 'auto',
			height : 'auto',
			//显示行号
			rownumbers : true,
			//设置奇偶行背景色不同
			striped : true,
			//分页工具
			pagination : true,
			//每页显示记录数
			pageList : [ 5, 10, 15, 20, 25 ],
			pageSize : 5,
			toolbar : [ {
				id : 'add',
				iconCls : 'icon-add',
				text : '增加',
				handler : function() {
					$('#editForm').form('clear');
					$('#editdialog').window('open');
				}
			}, ],
			columns : [ [ {
				field : 'name',
				title : '权限名称',
				width : 200
			}, {
				field : 'code',
				title : '关键字',
				width : 200
			}, {
				field : 'description',
				title : '描述',
				width : 200
			} , {
				field : 'page',
				title : '路径',
				width : 200,
			}, {
				field : 'generatemenu',
				title : '是否生产菜单',
				width : 200
			} , {
				field : 'zindex',
				title : '优先级',
				width : 200
			} ] ]
		});

	});
</script>
</head>
<body>
	<table id="grid"></table>
	<div id="editdialog" class="easyui-dialog" closed="true"
		style="width:450px" modal="true" draggable="false" title="新增用户">
		<form id="editForm">
			<table class="table-edit" border="0" width="400" height="80">
				<tr>
					<td style="text-align:center;">权限名称</td>
					<td><input id="name" name="name" type="text"></td>
				</tr>
				<tr>
					<td style="text-align:center;">关键字</td>
					<td><input id="code" name="code" type="text"></td>
				</tr>
				<tr>
					<td style="text-align:center;">路径</td>
					<td><input id="page" url="" name="page" 
						style="width:160px;" type="text"></td>
				</tr>
				<tr>
					<td style="text-align:center;">是否生成菜单</td>
					<td><input id="generatemenu" name="generatemenu" type="text" /></td>
				</tr>
				<tr>
					<td style="text-align:center;">优先级</td>
					<td><input id="zindex" name="zindex" type="text" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<a id="saveForm" class="easyui-linkbutton">确定</a>
						<a id="close" class="easyui-linkbutton" href="javascript:close('#editForm','#editdialog')">关闭</a></td>
				<tr>
			</table>
		</form>
	</div>

</body>
</html>
