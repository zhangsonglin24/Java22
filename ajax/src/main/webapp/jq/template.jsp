<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/10
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">Click</button>
<div id="result"></div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script type="text/templet" id="template">
    <div id="num-{{id}}">
    <h1>{{title}}</h1>
    <p>{{content}}</p>

</div>
</script>
<script>
    $(function () {
       $("#btn").click(function () {
           var html = $("#template").html();
           html = html.replace("{{id}}",Math.random());
           html = html.replace("{{title}}",new Date().getTime());
           html = html.replace("{{content}}",new Date().getTime());
           $(html).appendTo($("#result"));
       }) ;
    });

</script>

</body>
</html>
