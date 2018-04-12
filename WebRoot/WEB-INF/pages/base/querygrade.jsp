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
        $(function () {
            var editing = undefined;
            var title = "学生学习成绩测评表";
            var columns = [[{
                field: 'cno',
                title: '课程号',
                align: 'center',
                width: 100
            }, {
                field: 'cname',
                title: '课程名',
                align: 'center',
                width: 200
            }, {
                field: 'credit',
                title: '学分',
                width: 50,
                align: 'center',
                sortable: true
            }, {
                field: 'score',
                title: '成绩',
                width: 100,
                align: 'center',
                sortable: true,
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true,
                        missingMessage: '成绩必须填写！'
                    },
                }

            }]];
            var grid = $('#grid').datagrid({
                //datagrid的唯一辨识
                width: 'auto',
                title: title,
                iconCls: 'icon-tip',
                height: 'auto',
                //组合使用：remoteSort、sortName、sortOrder
                //默认服从服务器排序
                remoteSort: false,
                //按照那一列排序
                //排序方式:desc:降序 asc:升序（默认）
                sortOrder: 'asc',
                //显示行号
                rownumbers: true,
                //设置奇偶行背景色不同
                striped: true,
                frozenColumns: [[{
                    field: 'aa',
                    title: '全选',
                    width: 30,
                    checkbox: true,
                }]],
                toolbar: [{
                    id: 'update',
                    text: '修改成绩',
                    iconCls: 'icon-edit',
                    handler: function () {
                        var attr = $('#grid').datagrid('getSelections');
                        if (attr.length != 1) {
                            $.messager.show({
                                title: '提示信息',
                                msg: '选择一条数据进行修改'
                            });
                        } else {
                            $('#add').show();
                            $('#update').hide();
                            $('#cancel').show();
                            //根据航距离对象获取改行的索引位置
                            editing = $('#grid')
                                .datagrid(
                                    'getRowIndex',
                                    attr[0]);
                            //开启编辑状态
                            $('#grid').datagrid(
                                'beginEdit',
                                editing);

                        }
                    }
                },
                    {
                        id: 'add',
                        text: '保存修改',
                        iconCls: 'icon-save',
                        handler: function () {
                            //保存之前进行数据校验，然后结束编辑并重置编辑字段
                            if ($('#grid').datagrid(
                                    'validateRow', editing)) {
                                $('#grid').datagrid('endEdit',
                                    editing);
                            }
                            //保存数据datagrid 事件 onAfterEdit
                            $('#grid').datagrid('unselectAll');
                            $('#update').show();
                            $('#add').hide();
                            $('#cancel').hide();
                        }
                    }, {
                        id: 'cancel',
                        text: '取消修改',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            //回滚
                            $('#grid').datagrid('rejectChanges');
                            editing = undefined;
                            $('#update').show();
                            $('#add').hide();
                            $('#cancel').hide();

                        }
                    },],
                columns: columns,

                onAfterEdit: function (index, record) {
                    $.post('${pageContext.request.contextPath}/grade_update.action', {
                    	id:record.id,
                    	score:record.score
                    },function (data) {
                    	$.messager.alert("系统提示","修改成功!", "info");
                    });
                }
            });
            $('#add').hide();
            $('#cancel').hide();
            
            $('#btn').click(function (){
            	var sno = $("#sno").val();
            	var termid = $('#termid').combobox('getValue');
            	//查询学生是否存在
            	$.post('${pageContext.request.contextPath}/student_isExistStudentBySno.action', {
    				sno: sno
                }, function (data) {
                	// 如果存在返回 1
                	if(data=='1'){
                		//直接查询成绩
                		$.post('${pageContext.request.contextPath}/grade_getGradeBySnoAndTid.action', {
            				sno: sno,
    						tid:termid
                        }, function (data) {
                        	$('#grid').datagrid('loadData',data);
                    	});	
                	}else{
                		$.messager.alert('未找到','未找到该学生,请确认学号正确!','warning');    
                	}
            	});	
            });
        });
    </script>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div region="north" style="height: 80px;padding: 10px 50px; " title="输入选择信息下载年级学习成绩表" iconCls="icon-tip">
        <form method="post" action="">
            <table>
                <tr>
                    <td style="text-align: center">学期</td>
                    <td><input class="easyui-combobox"
                           data-options="url:'${pageContext.request.contextPath }/term_list.action',
		valueField:'id',textField:'time'" id="termid"
                           name="tid"/></td>
                    <td style="text-align: center">学号</td>
                    <td><input id="sno" name="sno"></td>
                    <td><input id="btn" type="button" value="查询"></td>
                <tr>
            </table>
        </form>
    </div>
    <div region="center">
        <table id="grid"></table>
    </div>
</div>

</body>
</html>