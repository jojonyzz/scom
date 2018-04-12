<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人成绩信息</title>
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
	$(function(){
            $('#btn').click(function (){
            	var sno = $("#sno").val();
            	//查询学生是否存在
            	$.post('${pageContext.request.contextPath}/student_isExistStudentBySno.action', {
    				sno: sno
                }, function (data) {
                	// 如果存在返回 1
                	if(data==1){
	                	var url ="${pageContext.request.contextPath}/role_findAll.action";
						$.post(url,{},function(data){
							for(var i=0;i<data.length;i++){
								$("#roles").append('<input type="checkbox" name="roleIds" value="'+data[i].id+'">'+data[i].name);
							}
						},"json");
                	}else{
                		$.messager.alert("系统提示","未找到学生!","info");
                	}
            	});	
            });
    		$('#save').click(function() {
    			var v = $('#updaterole').form("validate");
    			if (v) {
    				$('#updaterole').submit();
    			}else{
    				$.messager.alert("系统提示","提交数据不能为空","info");
    			}
    		});
        });
    </script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div region="center" style="height: 80px;padding: 10px 50px; " title="输入选择信息下载年级学习成绩表" iconCls="icon-tip">
      	<div class="datagrid-toolbar">
			<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
		</div>
        <form id="updaterole" method="post" action="role_update.action">
            <table>
                <tr>
                    <td style="text-align: center">学号</td>
                    <td><input id="sno" name="sno"></td>
                    <td><input id="btn" type="button" value="查询"></td>
                <tr>
                <tr>
					<td>角色:</td>
					<td id="roles" colspan="3"></td>
				</tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>