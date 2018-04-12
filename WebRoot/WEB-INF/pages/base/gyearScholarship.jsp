<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
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
		var academy = $('#academy')
				.combobox(
						{
							valueField : 'id',
							textField : 'acadname',
							editable : false,
							url : '${pageContext.request.contextPath}/academy_list.action',
							onChange : function(newValue, oldValue) {
								$
										.post(
												'${pageContext.request.contextPath}/major_listByAcademy.action',
												{
													academyId : newValue
												}, function(data) {
													$("#major").combobox(
															"enable");
													major.combobox("clear")
															.combobox(
																	'loadData',
																	data);
												}, 'json');
							},
						});

		var major = $('#major')
				.combobox(
						{
							valueField : 'id',
							textField : 'majorName',
							editable : false,
							disabled : true,
							onChange : function(newValue, oldValue) {
								$
										.post(
												'${pageContext.request.contextPath}/gyear_listByMajor.action',
												{
													mid : newValue
												}, function(data) {
													$("#gyear").combobox(
															"enable");
													gyear.combobox("clear")
															.combobox(
																	'loadData',
																	data);
												}, 'json');
							},

							onLoadSuccess : onLoadSuccess
						});

		var gyear = $('#gyear').combobox({
			valueField : 'id',
			textField : 'name',
			editable : false,
			disabled : true,
			onChange : function() {
				$("#term").combobox("enable");

			},
			onLoadSuccess : onLoadSuccess
		});
		var term = $('#term').combobox({
			editable : false,
			disabled : true
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

		$('#downOfExcel')
				.click(
						function() {
							var termid = $('#term').combobox('getValue');
							var yid = $("#gyear").combobox('getValue');
							//首先 ajax 判断该年级的是否完成综合测评
							$.post('${pageContext.request.contextPath}/student_isScom.action', {
					    				termid: termid,
	                                	yid:yid
	                                }, function (data) {
	                                	// 完成 1 ,0 未完成
	                                	if(data=='0'){
	                                		$.messager.alert('未完成','还未完成综合测评 ,不能进行奖学金计算!','info');  
	                                	}else{
	                                		window.location.href = "${pageContext.request.contextPath}/student_getScholarshiplist.action?yid="
	        									+ yid + "&termid=" + termid;
	                                	}
                                	});	
						});

	});
</script>
</head>

<body>

	<div class="easyui-layout" fit="true">
		<div region="center" title="选择信息下载综合测评数据" iconCls="icon-tip">
			<form method="post">
				<table class="table-edit">
					<tr>
					</tr>
					<tr>
						<td style="text-align:center;">学院名称</td>
						<td><input id="academy"></td>

						<td style="text-align:center;">专业名称</td>
						<td><input id="major"></td>

						<td style="text-align:center;">年级名称</td>
						<td><input id="gyear" name="yid"></td>
						<td style="text-align: center">学期</td>
						<td><input id="term" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'"
							name="termid" /></td>
						<td><a class="easyui-linkbutton" id="downOfExcel">导出</a></td>
					<tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>