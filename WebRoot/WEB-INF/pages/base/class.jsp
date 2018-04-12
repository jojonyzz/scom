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
		 $("#saveForm").click(function() {
	            $.messager.progress(); // 显示进度条
	            $('#editForm').form('submit',{
	                url : '${pageContext.request.contextPath}/stuclass_save.action',
	                onSubmit : function() {
	                    var isValid = $(this).form('validate');
	                    if (!isValid) {
	                        $.messager.progress('close'); // 如果表单是无效的则隐藏进度条
	                    }
	                    return isValid; // 返回false终止表单提交
	                },
	                success : function(data) {
	                    var data = eval('(' + data + ')');
	                    $.messager.progress('close'); // 如果提交成功则隐藏进度条
	                    $('#grid').datagrid('reload');
	                    if (data.success){
	                        $("#editdialog").dialog('close');
	                        $.messager.alert("系统提示",data.message, "info");
	                    }else{
	                        //1.清空表单
	                        $("#editForm").get(0).reset();
	                        $.messager.alert("系统提示",data.message, "wanning");
	                    }
	                }
	            });
	        });
		$('#grid').datagrid({

			url : '${pageContext.request.contextPath}/stuclass_queryPage.action',
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
			pageList : [ 15, 20, 25 ],
			pageSize : 5,
			toolbar : [ {
				id : 'add',
				iconCls : 'icon-add',
				text : '增加',
				handler : function() {
					doAdd();
				}
			}, ],
			columns : [ [ {
				field : 'academyName',
				title : '学院名称',
				width : 200
			}, {
				field : 'majorname',
				title : '专业名称',
				width : 200
			}, {
				field : 'gyearname',
				title : '年级名称',
				width : 200
			}, {
				field : 'name',
				title : '班级名称',
				width : 200
			}, ] ],
		});

		var academy = $('#academy').combobox({
			valueField : 'id',
			textField : 'acadname',
			editable : false,
			url : '${pageContext.request.contextPath}/academy_list.action',
			onChange : function(newValue, oldValue) {
				$.post('${pageContext.request.contextPath}/major_listByAcademy.action', {
					academyId : newValue
				}, function(data) {
					major.combobox("clear").combobox('loadData', data);
				}, 'json');
			},
			onLoadSuccess : onLoadSuccess
		});
		
		var major = $('#major').combobox({
			valueField : 'id',
			textField : 'majorName',
			editable : false,
			onChange : function(newValue, oldValue) {
					$.post('${pageContext.request.contextPath}/gyear_listByMajor.action', {
						mid : newValue
					}, function(data) {
						gyear.combobox("clear").combobox('loadData', data);
					}, 'json');
			},

			onLoadSuccess : onLoadSuccess
		});
		var gyear = $('#gyear').combobox({
			valueField : 'id',
			textField : 'name',
			editable : false,
			onLoadSuccess : onLoadSuccess
		});
		
		function onLoadSuccess() {
			var target = $(this);
			var data = target.combobox("getData");
			var options = target.combobox("options");
			if (data && data.length > 0) {
				var fs = data[0];
				target.combobox("setValue", fs[options.valueField]);
			}
		}

	});
</script>
</head>
<body>
	<table id="grid"></table>
	<div id="editdialog" class="easyui-dialog" closed="true"
		style="width:450px" modal="true" draggable="false" title="新增用户">
		<form id="editForm" method="post">
			<table class="table-edit" border="0" width="400" height="80">
				<tr>
					<td style="text-align:center;">学院名称</td>
					<td><input id="academy"></td>
				</tr>
				<tr>
					<td style="text-align:center;">专业名称</td>
					<td><input id="major"></td>
				</tr>
				<tr>
					<td style="text-align:center;">年级名称</td>
					<td><input id="gyear" name="gyear.id"></td>
				</tr>
				<tr>
					<td style="text-align:center;">班级名称</td>
					<td><input name="name" type="text" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><a id="saveForm"
						class="easyui-linkbutton">确定</a> <a id="close"
						class="easyui-linkbutton"
						href="javascript:close('#editForm','#editdialog')">关闭</a></td>
				<tr>
			</table>
		</form>
	</div>

</body>
</html>
