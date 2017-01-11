<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/11
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>

        <a href="/user/new" class="btn btn-primary">添加</a>
        <table class="table">
            <tr>
                <th>姓名</th>
                <th>密码</th>
            </tr>
            <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.username}</td>
                <td>${account.password}</td>
            </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>
