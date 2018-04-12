function doAdd() {
    $("#editdialog").dialog({
        title : '新增'
    });
    //1.清空表单
    $("#editForm").get(0).reset();
    //2.打开dialog
    $("#editdialog").dialog('open');

}
function doOfExcel() {
    // 1.清空表单
    $("#Excel").get(0).reset();
    // 2.打开dialog
    $("#Excel_dialog").dialog('open');

}
