<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/3
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/js/dist/sweetalert.css" rel="stylesheet">
</head>
<body>
<div class="navbar navbar-inverse  navbar-static-top">
    <div class="navbar-inner">
        <a class="brand" href="#">BBS 管理系统</a>
        <ul class="nav">
            <li><a href="#">首页</a></li>
            <li><a href="#">主题管理</a></li>
            <li class="active"><a href="#">节点管理</a></li>
            <li><a href="#">用户管理</a></li>
        </ul>
        <ul class="nav pull-right">
            <li><a href="">安全退出</a></li>
        </ul>
    </div>
</div>
<!--header-bar end-->
<div class="container-fluid" style="margin-top:20px">
    <form action="" id="saveForm">
        <legend>添加新节点</legend>
        <label>节点名称</label>
        <input type="text" name="newNode">
        <div class="form-actions">
            <button type="button" id="saveBtn" class="btn btn-primary">保存</button>
        </div>
    </form>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/dist/sweetalert.min.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        });


        $("#saveForm").validate({
            errorElement:"span",
            errorClass:"text-error",
            rules:{
                newNode:{
                    required:true,
                    remote:"/admin/newNodeValidate"
                }
            },
            messages:{
                newNode:{
                    required:"请输入节点名称",
                    remote:"节点已存在"
                }
            },
            submitHandler:function (form) {
                $.ajax({
                    url:"/admin/addNewNode",
                    type:"post",
                    data:$(form).serialize(),
                    success:function (json) {
                        if (json.state == "success"){
                            swal({title:"添加成功"},function () {
                                window.location.href = "/admin/node";
                            });
                        }else{
                            swal(json.message);
                        }

                    },error:function () {
                        swal("添加失败,服务器异常");
                    }
                });
            }
        })


    });
</script>
</body>
</html>
