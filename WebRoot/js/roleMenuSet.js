var roleId;//角色ID

var clickRow=function(rowIndex, rowData){
	
	$('#tree').tree({
		url:'role_readRoleMenus.action?id='+rowData.uuid,
		animate:true,
		checkbox:true
	});
	roleId=rowData.uuid;
}

function save(){
	
	var nodes= $('#tree').tree("getChecked");//获取你选中的节点集合
	var checkStr="";
	for(var i=0;i<nodes.length;i++){
		if(i>0){
			checkStr+=",";
		}
		checkStr+=nodes[i].id;		
	}

	
	$.ajax({
		url:'role_updateRoleMenus.action',
		data:{'id':roleId,'menuIds':checkStr},
		type:'post',
		dataType:'json',
		success:function(value){
			
			$.messager.alert('提示信息',value.message);			
		}
		
	});
	
}