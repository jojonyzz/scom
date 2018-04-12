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
        $(function () {


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
                $.messager.progress(); // 显示进度条
                $.ajax({
                    url:"student_upload.action",
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
                            $('#grid').datagrid('reload');
                            $('#Excel_dialog').dialog('close');
                            $('#Excel').form('clear');
                        }
                        $.messager.progress('close');
                        $.messager.alert('提示',res.message);
                    }
                });
            });
            $("#grid").datagrid({
                columns:[[
                    {field:'id',title:'编号',width:50,align:'center'},
                    {field:'time',title:'学期',width:160,align:'center'},
                    {field:'className',title:'班级',width:460,align:'center'}
                    ,  {field:'edit',title:'操作',width:140,align:'center',
                        formatter : function(value, record,
                                             index) {
                            return "<a id='delete' href='javascript:deleteExcel()' style=color:red;z-index: 1000;>撤销</a>";
                        },
                    }
                ]],
                url:"${pageContext.request.contextPath }/gradeRecord_queryPage.action",
                toolbar: [{
                    id: 'add',
                    text: '增加',
                    iconCls: 'icon-add',
                    handler: function () {
                        doAdd();
                    },
                }, {
                    id: 'all',
                    text: '从Excel中导入',
                    iconCls: 'icon-save',
                    handler: function () {
                        doOfExcel();
                    }
                }],
                width: 'auto',
                height: 'auto',
                pagination : true,
                //每页显示记录数
                pageList : [ 10, 15, 20],
                singleSelect:true,
            });

            var academy = $('#academy').combobox({
                valueField: 'id',
                textField: 'acadname',
                editable: false,
                url: '${pageContext.request.contextPath}/academy_list.action',
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/major_listByAcademy.action', {
                        academyId: newValue
                    }, function (data) {
                        major.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },
                onLoadSuccess: onLoadSuccess
            });

            var major = $('#major').combobox({
                valueField: 'id',
                textField: 'majorName',
                editable: false,
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/gyear_listByMajor.action', {
                        mid: newValue
                    }, function (data) {
                        gyear.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },

                onLoadSuccess: onLoadSuccess
            });
            var gyear = $('#gyear').combobox({
                valueField: 'id',
                textField: 'name',
                editable: false,
                onChange: function (newValue, oldValue) {
                    $.post('${pageContext.request.contextPath}/stuclass_listByGyear.action', {
                        gid: newValue
                    }, function (data) {
                        classname.combobox("clear").combobox('loadData', data);
                    }, 'json');
                },
                onLoadSuccess: onLoadSuccess
            });
            var classname = $('#classname').combobox({
                valueField: 'id',
                textField: 'className',
                editable: false,

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

            $('#addClassBtn').click(function () {

                $('#addClassLoc').before("" +
                    "" +
                    "<tr>" +
                    "<td style='text-align:center; width: 80px'>课程名</td>" +
                    "<td width='240'>" +
                    "<input url='' name='classname' valueField='classname' textField='text' class='easyui-combobox' editable='false'"+
                    "style='width:158px;' multiple='true' panelHeight='auto'>" +
                    "</td>"+
                    "<td style='text-align:center;'>成绩</td>"+
                    "<td><input type='text' name='score' style='width: 158px'></td>"+
                    "</tr>");
            });
        });
        function deleteExcel() {
            var attr =  $('#grid').datagrid('getSelected');
            window.location.href="${pageContext.request.contextPath }/grade_cancelGradeImport.action?id="+attr.id;
        }
    </script>
</head>
<body>
<table id="grid"></table>
<div id="editdialog" class="easyui-dialog" closed="true"
     style="width:650px" modal="true" draggable="false" title="添加成绩信息">
    <form id="editForm">
        <table class="table-edit" border="0" width="636px" height="80">
            <tr>
                <td colspan="4" class="title">选择班级</td>
            </tr>
            <tr>
                <td style="text-align:center;">学院名称</td>
                <td><input class="academy" name="academy"></td>
                <td style="text-align:center;">专业名称</td>
                <td><input class="major" name="major"></td>
            </tr>
            <tr>
                <td style="text-align:center;">年级名称</td>
                <td><input class="gyear" name="gyear"></td>
                <td style="text-align:center;">班级名称</td>
                <td><input class="classname" name="classname" type="text"/></td>
            </tr>
            <tr>
                <td style="text-align:center; width: 80px">年份</td>
                <td width="240"><input url=" " name="year" valueField="time" textField="text"
                                       class="easyui-combobox" editable="false" style="width:158px;" multiple="true"
                                       panelHeight="auto"></td>
                <td style="text-align:center;">学期</td>
                <td><select style="width:158px;" class="easyui-combobox" editable="false" name="term">
                    <option value="第一学期">第一学期</option>
                    <option value="第二学期">第二学期</option>

                </select></td>
            </tr>
            <tr>
                <td colspan="4" class="title">选择学生</td>
            </tr>
            <tr>
                <td style="text-align:center;">学号</td>
                <td><input type="text" name=""></td>
                <td style="text-align:center;">姓名</td>
                <td><input type="text" name="stuname"></td>
            </tr>
            <tr>
                <td colspan="4" class="title">添加成绩</td>
            </tr>
            <tr>
                <td style="text-align:center; width: 80px">课程名</td>
                <td width="240"><input url="" name="classname" valueField="classname" textField="text"
                                       class="easyui-combobox" editable="false"
                                       style="width:158px;" multiple="true" panelHeight="auto"></td>
                <td style="text-align:center;">成绩</td>
                <td><input type="text" name="score" style="width: 158px"></td>
            </tr>
            <tr id="addClassLoc"><td colspan="4" style="text-align: center">
                <a id="addClassBtn" class="easyui-linkbutton">在添加一门课程</a>
            </td></tr>
            <tr style="text-align: center">
                <td colspan="4" >
                    <a id="saveForm" class="easyui-linkbutton">确定</a>
                    <a id="close" class="easyui-linkbutton" href="javascript:close('#editForm','#editdialog')">关闭</a>
            </tr>

        </table>
    </form>
</div>
<div id="Excel_dialog" class="easyui-dialog" closed="true"
     style="width:450px" title="从Excel中上传成绩信息" modal="true" draggable="false">
    <form id="Excel" action="" method="post">
        <table class="table-edit" style="margin: 30px auto;">

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
                <td><input id="gyear" name="yid"></td>
            </tr>
            <tr>
                <td style="text-align:center;">班级名称</td>
                <td><input id="classname" name="class_id" type="text"/></td>
            </tr>
            <tr>
                <td style="text-align:center;">学期</td>
                <td><input class="easyui-combobox"
                           data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'"
                           name="termid"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="file" name="upload"></td>

            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button type="button" id="btnUpload">上传Excel文件</button>
                    <a href="javascript:close(' #Excel','#Excel_dialog')" class="easyui-linkbutton">关闭</a></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>

