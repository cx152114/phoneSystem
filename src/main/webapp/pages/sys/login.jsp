<%--
  Created by IntelliJ IDEA.
  User: 陈翔
  Date: 2020-02-22
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>手机进销存管理系统</title>
    <!-- Favicons -->
    <link href="../img/favicon.png" rel="icon">
    <link href="../img/apple-touch-icon.png" rel="apple-touch-icon">

    <!-- Bootstrap core CSS -->
    <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="../lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/style-responsive.css" rel="stylesheet">
</head>
<body>
<!-- **********************************************************************************************************************************************************
  MAIN CONTENT
  *********************************************************************************************************************************************************** -->
<div id="login-page">
    <div class="container">
        <div class="form-login">
            <h2 class="form-login-heading">手机进销存管理系统</h2>
            <div class="login-wrap">
                <input type="text" class="form-control" id="employeeName" name="employeeName" placeholder="用户名" autofocus>
                <br>
                <input type="password" class="form-control" id="employeePwd" name="employeePwd" placeholder="密码">

                <label class="checkbox">
                    <!--<input type="checkbox" value="remember-me"> 记住密码-->
                    <span class="pull-right">
            <a data-toggle="modal" href="login.html#myModal"> 忘记密码？</a>
            </span>
                </label>
                <button class="btn btn-theme btn-block" id="btn-login"  type="submit"><i class="fa fa-lock"></i>登录</button>
                <hr>
            </div>
        </div>
    </div>

    <!-- 忘记密码页面 -->
    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">重置密码</h4>
                </div>
                <div class="modal-body">
                    <p>在下面输入您的电子邮件地址以重置您的密码。</p>
                    <input type="text" name="email" placeholder="Email" autocomplete="off" class="form-control placeholder-no-fix">
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-theme" type="button">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal -->
</div>
<!-- js placed at the end of the document so the pages load faster -->
<script src="../lib/jquery/jquery.min.js"></script>
<script src="../lib/bootstrap/js/bootstrap.min.js"></script>
<!--BACKSTRETCH-->
<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
<script type="text/javascript" src="../lib/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("/img/login-bg.jpg", {
        speed: 500
    });

    document.querySelector("#btn-login").onclick = function(){
        var employeeName = $("#employeeName").val();
        var employeePwd = $("#employeePwd").val();
        if(employeeName == '' || employeeName == 'undefined'){
            alert("请填写用户名！");

            return;
        }
        if(employeePwd == '' || employeePwd == 'undefined'){
            alert("请填写密码！");
            return;
        }
        $.ajax({
            url:'/login/login',
            data:{employeeName:employeeName,employeePwd:employeePwd},
            type:'post',
            dataType:'json',
            success:function(response){
                console.log(response)
                if (response.code == 0) {
                    layer.msg(response.msg, {icon: 1, time: 1000, offset: '0px'});
                    window.location.href = '/login/index';
                } else {
                    layer.alert(response.msg, {icon: 5, offset: '0px'});
                }
            }
        });
    }

</script>


</body>
</html>
