<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/8
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">load json</button>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
       $("#btn").click(function () {
           $.get("/data.json").done(function (data) {
               for(var i = 0;i < data.length;i++){
                   var user = data[i];
                   alert(user.username + "->" + user.address);
               }
           }).error(function () {
               alert("服务器异常");
           });
       }) ;
    });

</script>
</body>
</html>
