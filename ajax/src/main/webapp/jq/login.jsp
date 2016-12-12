<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/8
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <form id="loginForm">
        <div class="form-group">
            <label>账号</label>
            <input type="text" class="form-control" name="username" id="username">
        </div>
        <div class="form-group">
            <label>密码</label>
            <input type="password" class="form-control" name="password" id="password">
        </div>
        <div class="form-group">
            <button class="btn btn-primary" type="button" id="loginBtn">登录 </button>
        </div>
    </form>
</div>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function () {


        $("#loginBtn").click(function(){
            $("#loginForm").submit();
        });

        $("#loginForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{
                username:{
                    required:true
                },
                password:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"请输入账号"
                },
                password:{
                    required:"请输入密码"
                }
            },
            submitHandler:function(){
                $.ajax({
                    url : "/login",
                    type : "post",
                    data : $("#loginForm").serialize(),
                    beforeSend:function(){
                        $("#loginBtn").text("登录中。。。。。。。").attr("disabled","disabled");
                    },
                    complete:function(){
                        $("#loginBtn").html("登录").removeAttr("disabled");
                    },
                    success : function(data) {
                        if(data.state == "error") {
                            alert(data.message);
                        } else {
                            window.location.href = "/jq/demo1.jsp";
                        }
                    },
                    error : function() {
                        alert("服务器错误");
                    }
                });
            }
        });






        /*
               $("#loginBtn").click(function () {
                   var username = $("#username").val();
                   var password = $("#password").val();
                   $.post("/login",$("#loginForm").serialize()).done(function(data){
                        if(data.state == "error"){
                            alert(data.message);
                        }else{
                            window.location.href = "/jq/demo1.jsp";
                        }

                   }).error(function () {
                      alert("服务器异常");
                   });
               }) ;*/
    });
</script>
</body>
</html>
