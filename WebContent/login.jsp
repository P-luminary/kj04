<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>系统登录 - 20软件4班课件管理系统</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body class="login_bg">
    <section class="loginBox">
        <header class="loginHeader">
            <h1>20软件4班课件管理系统</h1>
        </header>
        <section class="loginCont"> <!-- [方法的分发]//这个页面调用的登录功能 ，"?"后面是键+值-->
            <form class="loginForm" action="use?method=login" method="post">
                <div class="inputbox">
                    <label for="username">用户名：</label> <!-- for的作用可以直接点击跳到那个按钮框 -->
                    <input id="username" type="text" name="username" placeholder="请输入用户名" required/>
                </div>
                <div class="inputbox">
                    <label for="password">密码：</label>
                    <input id="password" type="password" name="password" placeholder="请输入密码" required/>
                </div>
                <div class="subBtn">
                    <input type="submit" value="登录" />
                    <input type="reset" value="重置"/>
                </div>

            </form>
        </section>
    </section>

</body>
</html>