<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Head Begin-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>牛朋 | 登录</title>
    <!--Tell the browser to be responsive to screen width 响应式 移动端优先 -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="static/assets/bower_components/bootstrap/dist/css/bootstrap.min.css" />
    <!-- Font Awesome字体选择 -->
    <link rel="stylesheet" href="static/assets/bower_components/font-awesome/css/font-awesome.min.css" />
    <!-- Ionicons 字体-->
    <link rel="stylesheet" href="static/assets/bower_components/Ionicons/css/ionicons.min.css" />
    <!-- Theme style  主题-->
    <link rel="stylesheet" href="static/assets/css/AdminLTE.min.css" />
    <!-- iCheck 应该是选择框 蓝色的-->
    <link rel="stylesheet" href="static/assets/plugins/iCheck/square/blue.css" />

    <!--低于IE9调用下面两个链接 支持媒体查询<支持响应式,响应式基于媒体查询>-->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery 3 -->
    <script src="static/assets/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap 3.3.7 -->
    <script src="static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="static/assets/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' /* optional */
            });
        });
    </script>

</head>
<!-- ./Head End -->
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#">牛朋主页</a>
    </div>
    <!-- /.login-logo -->
    <%--登录体--%>
    <div class="login-box-body">
        <p class="login-box-msg">欢迎牛朋登录</p>
        <form action="login" method="post">

            <div class="alert alert-danger alert-dismissible" ${message == null ? "style=\"display: none\"":""}>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                ${message}
            </div>

            <div class="form-group has-feedback">
                <input name="email" type="email" class="form-control" placeholder="邮箱">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <input name="password" type="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <input type="checkbox"> 记住我
                        </label>
                        <label>
                            <input type="checkbox"> 自动登录
                        </label>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <a href="#">忘记密码?</a><br>
        <a href="register.html" class="text-center">注册</a>
    </div>
</div>
</body>
</html>
