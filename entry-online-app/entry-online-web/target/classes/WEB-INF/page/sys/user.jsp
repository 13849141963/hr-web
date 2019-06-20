<%@ page language="java" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%String ctx=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
	<title>用户列表</title>
	<jsp:include page="header.jsp" />
</head>
<body>
<div id="rrapp">
	<div class="grid-btn">
		<shiro:hasPermission name="sys:user:save">  
			<a class="btn btn-primary  abbBtn btn-sm" href="user_add.html"><i class="fa fa-plus-square"></i>&nbsp;&nbsp;新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:update">  
			<button type="button"  class="btn btn-primary btn-sm btn-toggledisabled" @click="update"><i class="fa fa-edit"></i>&nbsp;&nbsp;修改</button>
		</shiro:hasPermission>
		<shiro:hasPermission name="sys:user:delete">
			<button type="button" class="btn btn-primary btn-sm btn-toggledisabled deleteBtn" @click="del"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;删除</button>
		</shiro:hasPermission>
	</div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
</div>
<script src="<%=ctx%>/js/sys/user.js?_<%=System.currentTimeMillis()%>"></script>
</body>
</html>