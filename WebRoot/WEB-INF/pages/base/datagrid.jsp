<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>datagrid 的使用</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body>
	<h2>请选择参加测评的科目</h2>
	<table id="grid"></table>
	<script type="text/javascript">
		$(function (){
			$("#grid").datagrid({
				columns:[[
				          {field:'id',title:'编号',checkbox:true},
				          {field:'cname',title:'科目名'}
				          ]],
				url:'${pageContext.request.contextPath }/student_coursesByYearATid.action?yid=1&termid=1',
				toolbar:[
				         {text:'选择',iconCls:'icon-remove',handler:function(){
				     		var ids = $("#grid").datagrid("getSelections");
				    		var array = new Array();
				    		if(ids.length == 0){
				    		$.messager.alert("系统提示","请选择要参加的条目","warning");
				    		}else{
				    			for(var i = 0 ;i<ids.length;i++){
				    				var id = ids[i].id;
				    				array.push(id);
				    			}
				    			var ids= array.join(",");
				    			window.location.href="${pageContext.request.contextPath}/student_getSelections.action?ids="+ids+"&termid=1&yid=1";
				    		}
				         }}
				         ]
			});
		});
	</script>
</body>
</html>
