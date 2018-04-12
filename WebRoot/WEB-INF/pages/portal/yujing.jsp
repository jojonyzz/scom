<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="padding:10px;">
	<table class="easyui-datagrid" data-options="url:'${pagecontext.request.contextPath }/bug_list.action'"
		style="height:auto;overflow:auto">
		<thead>
			<tr>
				<th data-options="field:'priority'">优先级</th>
				<th data-options="field:'state'">状态</th>
				<th data-options="field:'subject'">主题</th>
				<th data-options="field:'sender'">发送人</th>
				<th data-options="field:'senddate'">发送日期</th>
			</tr>
		</thead>
	</table>
</div>