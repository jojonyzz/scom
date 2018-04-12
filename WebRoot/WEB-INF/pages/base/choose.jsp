<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统主界面</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 导入ztree类库 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"
	type="text/css" />
<script
	src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/outOfBounds.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var falg = true; 
        $(function () {

   	     
		     $('#grid').datagrid({
	                //datagrid的唯一辨识
	                idField: 'id',
	                width: 'auto',
	                height: 'auto',
	                //显示行号
	                rownumbers: true,
	                //设置奇偶行背景色不同
	                striped: true,
	                //分页工具
	                toolbar:[
				         {text:'确定导出测评数据',iconCls:'icon-save',handler:function(){	
				     		var ids = $("#grid").datagrid("getSelections");
				        	var termid = $('#term').combobox('getValue');
	                    	var class_id = $('#classname').combobox('getValue');

				    		var array = new Array();
				    		if(ids.length == 0){
				    			$.messager.alert("系统提示","请选择要参加的条目","warning");
				    		}else{
				    			for(var i = 0 ;i<ids.length;i++){
				    				var id = ids[i].id;
				    				array.push(id);
				    			}
				    			var ids= array.join(",");
				    			//直接进入,为 true ,重新计算为 false
				    			if(flag){
				    				window.location.href="${pageContext.request.contextPath}/student_getClassScom.action?ids="
                		    			+ids+"&termid="+termid+"&class_id="+class_id+"&flag=2";//不存在直接计算
				    			}else{
				    				window.location.href="${pageContext.request.contextPath}/student_getClassScom.action?ids="
                		    			+ids+"&termid="+termid+"&class_id="+class_id+"&flag=1";//存在重新计算
				    			}
				    		}
				         }}
				    ],
	                frozenColumns: [[
	                    {
	                        field: 'ck',
	                        checkbox: true
	                    }
	                ]],
	                columns: [[{
	                    field: 'cname',
	                    title: '科目',
	                    width: 200,
	                    align: 'center',
	                    sortable: true
	                },{
	                    field: 'cno',
	                    title: '课程号',
	                    width: 200,
	                    align: 'center',
	                    sortable: true
	                },{
	                    field: 'credit',
	                    title: '学分',
	                    width: 200,
	                    align: 'center',
	                    sortable: true
	                }
	                ]],
	            });
            var academy = $('#academy').combobox({
                valueField: 'id',
                textField: 'acadname',
                editable: false,
                width:120,
                url: '${pageContext.request.contextPath}/academy_list.action',
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/major_listByAcademy.action', {
                        academyId: newValue
                    }, function (data) {
                        $("#major").combobox("enable");
                        major.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },
            });

            var major = $('#major').combobox({
                valueField: 'id',
                textField: 'majorName',
                editable: false,
                width:220,
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/gyear_listByMajor.action', {
                    	mid: newValue
                    }, function (data) {
                        $("#gyear").combobox("enable");
                        gyear.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },

                onLoadSuccess: onLoadSuccess
            });
            var gyear = $('#gyear').combobox({
                valueField: 'id',
                textField: 'name',
                width:80,
                editable: false,
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/stuclass_listByGyear.action', {
                    	gid: newValue
                    }, function (data) {
                        $("#classname").combobox("enable");
                        classname.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },
                onLoadSuccess: onLoadSuccess
            });
            var classname = $('#classname').combobox({
                valueField: 'id',
                textField: 'name',
                editable: false,
				width:100,
                onLoadSuccess: onLoadSuccess
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
            disableCombobox();
        });
        function disableCombobox(){
            $("#major").combobox("disable");
            $("#gyear").combobox("disable");
            $("#classname").combobox("disable");
        }
    </script>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div region="north" title="班级、学期选择"
			style="overflow:hidden;height:80px;padding:10px">
			<form id="myform" method="post" action="">
				<table class="table-edit">
					<tr>
						<td style="text-align:center;">学院名称</td>
						<td><input id="academy"></td>

						<td style="text-align:center;">专业名称</td>
						<td><input id="major"></td>

						<td style="text-align:center;">年级名称</td>
						<td><input id="gyear"></td>
						<td style="text-align: center">班级名称</td>
						<td><input id="classname" name="class_id"></td>
						<td style="text-align: center">学期</td>
						<td><input id="term" class="easyui-combobox"
							data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'"
							name="termid" /></td>
						<td><input id="btn" type="button" value="提交查询"/>
						<script type="text/javascript">
                    	$(function(){
                    		$("#btn").click(function(){
                    			var termid = $('#term').combobox('getValue');
    	                    	var class_id = $('#classname').combobox('getValue');
	                    			$.post('${pageContext.request.contextPath}/student_isExistScom.action', {
					    				termid: termid,
	                                	class_id:class_id
	                                }, function (data) {
	                                	// 如果存在返回 1
	                                	if(data=='1'){
	                                		//存在,询问是否使用以前的测评数据
	                                		$.messager.confirm('存在数据','存在测评数据,是否重新计算?',function(r){    
	                                		    if (r){
	                                		    	flag = false;
	                                		        //重新计算 
	                                		    	$('#myform').form("submit",{
	                                        		    url:"${pageContext.request.contextPath }/student_coursesByClass.action",    
	                                        		    success:function(data){   
	                                        		    	 data = $.parseJSON(data); 
	                                        		    	 if(data == ''){
	                                        		    		 $.messager.alert("系统提示","未找到相关课程","info");
	                                        		    	 }
	                    	                    		     $('#grid').datagrid('loadData',data);
	                                        		    }  
	                                        		});
	                                		    }else{
	                                		    	//读取以前的测评数据
	                                		    	window.location.href="${pageContext.request.contextPath}/student_getClassHistoryScom.action?&termid="+termid+"&class_id="+class_id;
	                                		    }
	                                		});  
	                                	}else{
	                                		flag =true;
	                                		$('#myform').form("submit",{
	                                		    url:"${pageContext.request.contextPath }/student_coursesByClass.action",    
	                                		    success:function(data){   
	                                		    	 data = $.parseJSON(data); 
	                                		    	 if(data == ''){
	                                		    		 $.messager.alert("系统提示","未找到相关课程","info");
	                                		    	 }
	            	                    		     $('#grid').datagrid('loadData',data);
	                                		    }  
	                                		});
	                                	}
                                	});	
                    		});
                    	});
                    		function loaddatagrid() {
                    			var target = $(this);
                                var data = target.datagrid("getData");
                                var options = target.datagrid("options");
                                if (data && data.length > 0) {
                                    var fs = data[0];
                                    target.datagrid("setValue", fs[options.valueField]);
                                }
							}
                    </script></td>
					<tr>
				</table>
			</form>
		</div>
		<div region="center" title="科目选择"
			style="background:#fafafa;">
			<table id="grid"></table>
		</div>
	</div>
</body>
</html>