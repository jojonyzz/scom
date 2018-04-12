<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
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
    
    $(function () {
    	$('#closebtn').click(function (){
    		$('#editdialog').dialog('close');
    	});

        $("#saveForm").click(function() {
            $.messager.progress(); // 显示进度条
            $('#editForm').form('submit',{
                url : '${pageContext.request.contextPath}/collengtest_save.action',
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

        //excel上传
        $("#btnUpload").bind("click", function() {
            $.ajax({
                url:"collengtest_upload.action",
                type: 'POST',
                cache: false,
                data: new FormData($('#Excel')[0]),
                processData: false,
                contentType: false,
                success:function(value)
                {
                    var res = $.parseJSON(value);
                    if(res.success)
                    {
                        $('#Excel_dialog').dialog('close');
                        $('#Excel').form('clear');
                        $('#grid').datagrid('reload');
                    }
                    $.messager.alert('提示',res.message);
                }
            });
        });
			$("#grid").datagrid({
				columns:[[
				          {field:'id',title:'编号',width:50},
				          {field:'name',title:'姓名',width:120},
				          {field:'sno',title:'学号',width:120},
				          {field:'time',title:'录入时间',width:160},
				          {field:'course',title:'科目',width:180},
				          {field:'score',title:'分数',width:100},
				          {field:'importName',title:'录入人姓名',width:100},
				          {field:'edit',title:'操作',width:140,align:'center',
		                        formatter : function(value, record,
		                                             index) {
		                            return "<a id='delete' href='javascript:deleteExcel()' style=color:red;z-index: 1000;>删除</a>";
		                        },
		                  }
				          ]],
				url:'${pageContext.request.contextPath }/collengtest_getImportListBySid.action',
				toolbar: [{
                    id: 'add',
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        doAdd();
                    },
                }/* , {
                    id: 'all',
                    text: '从Excel中导入',
                    iconCls: 'icon-save',
                    handler: function () {
                        doOfExcel();
                    }
                } */],
                width: 'auto',
                height: 'auto'
			});
        });
    function deleteExcel() {
        var attr =  $('#grid').datagrid('getSelected');
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
            if (r){    
                window.location.href='${pageContext.request.contextPath}/collengtest_delete.action?id='+attr.id;
            }    
        });  
    }
    </script>
</head>
<body>
<table id="grid"></table>
<!-- 导入框 -->
<div id="Excel_dialog" class="easyui-dialog" title="批量导入"
     style="width: 460px;height: 230px" data-options="closed:true">
    <form id="Excel" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            <tr>
                <td>下载模板文件：</td>
                <td><a
                        href="${pageContext.request.contextPath }/download_excel.action?fid=1">【点此下载等级考试
                    Excel模板文件】</a>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td width="100">请选择Excel文件:</td>
                <td><input type="file" name="upload" class="inputstyle"
                           style="width: 200px;"/></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>考试时间：</td>
                <td><input class="easyui-combobox"
                           data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'"
                           name="termid"/></td>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="button" id="btnUpload">上传Excel文件</button>
                    <a class="easyui-linkbutton" href="javascript:close('#Excel','#Excel_dialog')">关闭</a></td>
        </table>
    </form>
</div>

<div id="editdialog" class="easyui-dialog" closed="true"
     style="width:450px" title="添加成绩" modal="true" draggable="false">
    <form id="editForm" action="" method="post">
        <table class="table-edit" style="margin:0 auto">
            <tr>
                <td>学号</td>
                <td><input type="text" name="student.sno"></td>
            </tr>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="student.name"></td>
            </tr>
            <tr>
                <td>考试科目</td>
                <td><input class="easyui-combobox"
                           data-options="url:'${pageContext.request.contextPath }/rankList_list.action',
		valueField:'id',textField:'name'"
                           name="rankList.id"/></td>
            </tr>
            <tr>
                <td>考试时间</td>
                <td><input class="easyui-combobox"
                           data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'"
                           name="term.id"/></td>
            </tr>
            <tr>
                <td>成绩</td>
                <td><input type="text" name="score"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <a id="saveForm" class="easyui-linkbutton">确定</a>
                    <a id="closebtn" class="easyui-linkbutton">关闭</a>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
