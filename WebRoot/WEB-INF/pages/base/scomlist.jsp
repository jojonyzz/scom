<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>班级学习成绩测评结果</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<link href="css/list.css" type="text/css" rel="stylesheet">

<script>
	$(function() {

		$('#stuScore').panel({
			title : "班级学习成绩测评结果",
			fit : true,
			iconCls : 'icon-tip',
		});
	})
</script>
<style>
</style>
</head>
<body>
	<div id="stuScore" class="easyui-panel" title="班级学习成绩测评结果" fit="true"
		iconCls="icon-save">

		<div class="toolbar">
			<a id="downOfExcel"
				href="${pageContext.request.contextPath }/student_downloadClass.action?termid=${tid}&class_id=${cid}"
				class="easyui-linkbutton" iconCls="icon-save">保存为Excel格式并下载</a>
		</div>
		<table id="table3">
			<thead>
				<tr>
					<th>序号</th>
					<th>学号</th>
					<th>姓名</th>
					<th>学习成绩</th>
					<th>计算机成绩</th>
					<th>英语成绩</th>
					<th>合计</th>
					<th>班级排名</th>
					<th>不及格课程门次</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="scom" varStatus="s">
					<tr>
						<td>${s.count }</td>
						<td>${scom.sno }</td>
						<td>${scom.name }</td>
						<td>${scom.grade }</td>
						<td>${scom.computer }</td>
						<td>
							<c:if test="${scom.english ne '0.0'}">
								${scom.english }
							</c:if>
						</td>
						<td>${scom.amount }</td>
						<td>${scom.rank }</td>
						<td>${scom.nopass }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

</body>
</html>