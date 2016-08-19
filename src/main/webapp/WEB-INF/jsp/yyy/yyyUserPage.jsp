<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->
<meta content="yes" name="apple-mobile-web-app-capable" />
<!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<!-- 告诉设备忽略将页面中的数字识别为电话号码 -->
<meta content="telephone=no" name="format-detection" />
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/jquery-2.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/shake.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/yyy/yyyUser.js"></script>
<title>摇一摇</title>
</head>
<body>
<progress max="100" value="0" id="progress"></progress>
<input id="userinfo" type="hidden" value='{"openid":"${snsUserInfo.openid}","nickname":"${snsUserInfo.nickname}","headimgurl":"${snsUserInfo.headimgurl}","sex":"${snsUserInfo.sex}","city":"${snsUserInfo.city}"}'>
<div>你的openid是：<lable id="userId">${snsUserInfo.openid}</lable></div>
<div>你的昵称：${snsUserInfo.nickname}</div>
<div>你的性别：
<c:if test="${snsUserInfo.sex eq 0}"><c:out value="未知"></c:out></c:if>
<c:if test="${snsUserInfo.sex eq 1}"><c:out value="男"></c:out></c:if>
<c:if test="${snsUserInfo.sex eq 2}"><c:out value="女"></c:out></c:if>
</div>
<div>你所在的国家：${snsUserInfo.country}</div>
<div>你所在的省：${snsUserInfo.province}</div>
<div>你所在的城市：${snsUserInfo.city}</div>
<div>你的头像：<img src="${snsUserInfo.headimgurl}" height="50px"></img></div>
<div>权限：
<c:forEach items="${snsUserInfo.privilegeList}" var="ls">
<div>${ls}</div>
</c:forEach>
</div>
<div>
	<button id="start">点击开始摇一摇</button>
	<div id="tips">
		
	</div>
</div>

<p>用力摇一摇你手机</p>
</body>
</html>