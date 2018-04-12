<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>公告</title>
<link rel="stylesheet" type="text/css" href="../css/notice.css">
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
<script type="text/javascript">
	$(function() {
		$('#grid')
				.datagrid(
						{
							url : '${pageContext.request.contextPath}/notice_queryPage.action',
							//datagrid的唯一辨识
							idField : 'grid',
							width : 'auto',
							height : 'auto',
							title : '公告',
							iconCls : "icon-tip",
							singleSelect : true,
							columns : [ [
									{
										field : 'title',
										title : '标题',
										align : 'center',
										width : 200,
									},
									{
										field : 'time',
										title : '上传时间',
										align : 'center',
										width : 140,
									},
									{
										field : 'author',
										title : '上传人',
										align : 'center',
										width : 140,
									},
									{
										field : 'edit',
										title : '操作',
										width : 140,
										align : 'center',
										formatter : function(value, record,
												index) {
											return "<a id='delete' href='javascript:visitMore()' style=color:#185fc2;z-index: 1000;>查看</a>";
										}
									} ] ]
						});
	});
	function visitMore() {
		var attr = $('#grid').datagrid('getSelected');
		var notive = $('#editdialog').dialog('setTitle', attr.name);
		var notiveTitle = $('#noticeTitle').text(attr.title);
		var noticeCon = $('#noticeCon').text(attr.context);
		notive.dialog('open');
	}
</script>
</head>
<body>
	<div class="easyui-datagrid" id="grid"></div>
	<div class="easyui-dialog"
		style="width:500px;height:150px;padding:10px;background:#fafafa;"
		closable="true" closed="true"></div>

	<div id="editdialog" class="easyui-dialog" closed="true"
		iconCls="icon-tip" modal="true" style="width: 500px" draggable="false"
		title="发布新公告">
		<table>
			<tr style="border: 1px solid #cccccc">
				<td class="noticeTh"><img src="../images/noTitle.png"
					class="noticeImg" alt="">
					<p>公告标题</p></td>
				<td style="width: 300px">
					<p id="noticeTitle"></p>
				</td>
			</tr>
			<tr>
				<td class="noticeTh"><img src="../images/nocontent_lcon.png"
					class="noticeImg" alt="">
					<p>公告内容</p></td>
				<td>
					<p id="noticeCon" style="padding: 20px;"></p>
				</td>

			</tr>
			<tr>
				<td colspan="2" align="center">
					<div class="btnbox">
						<a id="close" class="greenBtn" style="color: white"
							href="javascript:closeDialog()">关闭</a>
					</div>
			<tr>
		</table>
	</div>


</body>
</html>
