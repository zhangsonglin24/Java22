<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/28
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap/2.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/js/dist/sweetalert.css" rel="stylesheet">
    <style>
        .mt20 {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<%@include file="../include/adminNavbar.jsp"%>
<!--header-bar end-->
<div class="container-fluid mt20">
    <a href="newnode.html" class="btn btn-success">添加新节点</a>
    <table class="table">
        <thead>
        <tr>
            <th>节点名称</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="node">
            <tr>
                <td>${node.nodename}</td>
                <td>
                    <a href="/admin/nodeUpdate?nodeId=${node.id}">修改</a>
                    <a href="javascript:;" rel="${node.id}" class="delNode">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination pagination-mini pagination-centered">
        <ul id="pagination" style="margin-bottom:20px;"></ul>
    </div>
</div>
<!--container end-->
<script src="/static/js/jquery-1.11.1.js"></script>
<script src="/static/js/dist/sweetalert.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {

        $("#pagination").twbsPagination({
            totalPages:${page.totalPage},
            visiblePages:5,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href: '?p={{number}}'
        });

       $(".delNode").click(function () {
           var id = $(this).attr("rel");

           swal({
               title: "确定要删除该节点?",
               type: "warning",
               showCancelButton: true,
               confirmButtonColor: "#DD6B55",
               cancelButtonText:"取消",
               confirmButtonText: "确定",
               closeOnConfirm: false },
             function () {
                 $.ajax({
                     url:"/admin/delNode",
                     type:"post",
                     data:{"id":id},
                     success:function (data) {
                         if(data.state == "success"){
                             swal({title:"删除成功!"},function () {
                                 window.history.go(0);
                             });
                         }else{
                             swal(data.message);
                         }
                     },error:function () {
                         swal("服务器异常,删除失败");
                     }
                 });
             }      
           );

       });
    });
</script>
</body>
</html>
