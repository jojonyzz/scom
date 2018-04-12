<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆页面</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css" />


    <script type="text/javascript">
        if (window.self != window.top) {
            window.top.location = window.location;
        }
    </script>
</head>
<body >
<div class="main-inner" id="mainCnt">

<div class="header">
    <div class="page-logo">
        <h2><strong>学生综合管理系统</strong></h2>
        <p>Student integrated management system</p>
    </div>
</div>
   <div id="loginBlock" class="login">
        <div class="loginFunc">
            <div id="lbNormal" class="loginFuncMobile">系统登录</div>
        </div>
        <div class="loginForm">
            <form id="loginform" name="loginform" method="post" class="niceform"
                  action="${pageContext.request.contextPath }/user_login.action">
                <div id="idInputLine" class="loginFormIpt showPlaceholder"
                     style="margin-top: 5px;">
                    <input id="loginform:idInput" type="text" name="account"
                           class="loginFormTdIpt" maxlength="50" /> <label for="idInput"
                                                                           class="placeholder" id="idPlaceholder">帐号：</label>
                </div>
                <div class="forgetPwdLine"></div>
                <div id="pwdInputLine" class="loginFormIpt showPlaceholder">
                    <input id="loginform:pwdInput" class="loginFormTdIpt"
                           type="password" name="pwd" value="" /> <label
                        for="pwdInput" class="placeholder" id="pwdPlaceholder">密码：</label>
                </div>
                <div class="loginFormIpt loginFormIptWiotTh"
                     style="margin-top:58px;">
                    <div id="codeInputLine" class="loginFormIpt showPlaceholder"
                         style="margin-left:0px;margin-top:-40px;width:50px;">
                        <input id="loginform:codeInput" class="loginFormTdIpt"
                               type="text" name="checkcode" title="请输入验证码" /> <img
                            id="loginform:vCode"
                            src="${pageContext.request.contextPath }/validatecode.jsp"
                            onclick="javascript:document.getElementById('loginform:vCode').src='${pageContext.request.contextPath }/validatecode.jsp?'+Math.random();" />
                    </div>
                    <a class="loginFormTdIpt_a" onclick="mySubmit();" id="loginform:j_id19"
                       name="loginform:j_id19"> <script type="text/javascript">
                        function mySubmit() {
                            $("#loginform").submit();
                        }
                    </script> <span id="loginform:loginBtn" class="btn btn-login"
                                    style="margin-top:-36px;">登录</span>
                    </a>
                    <div>
                        <font color="red"><s:actionerror /></font>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!--
<div style="width: 900px; height: 50px; position: absolute; text-align: left; left: 50%; top: 50%; margin-left: -450px;; margin-top: 220px;">
    <span style="color: #488ED5;">Powered By www.itcast.cn</span><span
        style="color: #488ED5;margin-left:10px;">推荐浏览器（右键链接-目标另存为）：<a
        href="http://download.firefox.com.cn/releases/full/23.0/zh-CN/Firefox-full-latest.exe">Firefox</a>
		</span><span style="float: right; color: #488ED5;">宅急送BOS系统</span>
</div>
-->
</body>
</html>