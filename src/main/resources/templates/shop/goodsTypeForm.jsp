<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/views/include/easyui.jsp"%>

</head>
<body>
<div>
	<form id="mainform" action="${ctx}/shop/goodsType/${action}" method="post">
	<table  class="formTable">
		<tr>
			<td>类型名称：</td>
			<td>
			<input type="hidden" name="id" value="${id }" />
			<input name="name" type="text" value="${goodsType.name }" class="easyui-validatebox"  data-options="width: 180,required:'required'" />
			</td>
		</tr>
		<tr>
			<td>上级类型：</td>
			<td><input id="pid" name="pid" type="text" value="${goodsType.pid }" class="easyui-validatebox" required="required"/></td>
		</tr>
		<tr>
			<td>图片：</td>
			<td><input name="img" type="text" value="${goodsType.img}" class="easyui-validatebox"/></td>
		</tr>
	</table>
	</form>
</div>
<script type="text/javascript">
$(function(){
	
	//上级菜单
	$('#pid').combotree({
		width:180,
		method:'GET',
	    url: '${ctx}/shop/goodsType/json',
	    idField : 'id',
	    textFiled : 'name',
		parentField : 'pid',
	    animate:true
	});  
	
	$('#mainform').form({    
	    onSubmit: function(){    
	    	var isValid = $(this).form('validate');
			return isValid;	// 返回false终止表单提交
	    },    
	    success:function(data){   
	    	if(successTip(data,dg,d))
	    		dg.treegrid('reload');
	    }    
	}); 
});

</script>
</body>
</html>