<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>公告</title>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/notice.css">
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

		$('#grid').datagrid({
			url : '${pageContext.request.contextPath}/notice_queryPage.action',
			//datagrid的唯一辨识
			idField : 'grid',
			width : 'auto',
			height : 'auto',
			
			toolbar : [ {
				id : 'add',
				iconCls : 'icon-add',
				text : '发布新公告',
				handler : function() {
					$('#editForm').form('clear');
					$('#editdialog').window('open');
				}
			}, ]
		});
	});
	function saveForm(){
		var v = $('#saveNoticeForm').form('validate');
		if(v){
			$('#saveNoticeForm').submit();
		}
	}
</script>
</head>
<body>
	<table id="grid"></table>
	<div id="editdialog" class="easyui-dialog" closed="true" modal="true"
		style="width: 600px" draggable="false" title="发布新公告">
		<form id="saveNoticeForm" action="${pageContext.request.contextPath}/notice_save.action" method="post">
			<table>
				<tr>
					<td class="noticeTh"><img src="${pageContext.request.contextPath}/images/noTitle.png"
						class="noticeImg" alt="">
					<p>公告标题</p></td>
					<td><textarea rows="1" cols="60" id="title" name="title"
							class="easyui-validatebox noticeTitle"></textarea></td>
				</tr>
				<tr>
					<td class="noticeTh"><img src="${pageContext.request.contextPath}/images/nocontent_lcon.png"
						class="noticeImg" alt="">
					<p>公告内容</p></td>
					<td><textarea rows="12" cols="60" id="content"
							style="padding: 10px" name="context" class="easyui-validatebox"></textarea>
					</td>

				</tr>
				<tr>
					<td colspan="2" align="center">
						<div class="btnbox">
							<a id="saveForm" class="blueBtn" style="color: white"
								href="javascript:saveForm()">发布</a> <a id="close" class="redBtn"
								style="color: white" href="javascript:closeDialog()">取消</a>
						</div>
				<tr>
			</table>
		</form>
	</div>
</body>
</html>
