<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/7
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<button id="btn">json data</button>
<table class="table">
    <theand>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
        </tr>
    </theand>
    <tbody>

    </tbody>
</table>
<script src="/static/js/jquery-1.11.3.min.js"></script>
<script>
    $(function () {
        $("#btn").click(function () {
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("get","/users.json");
            xmlHttp.onreadystatechange = function () {
              if(xmlHttp.readyState == 4){
                  if(xmlHttp.status == 200){
                      var $tbody = $("tbody");
                      var result = xmlHttp.responseText;
                      var json = JSON.parse(result);

                      for(var i = 0;i < json.length;i++){
                          var user = json[i];
                          var jsp = "<tr><td>"+user.id+"</td><td>"+user.username+"</td><td>"+user.address+"</td></tr>";
                          $(jsp).appendTo($tbody);
                      }


                  }else{
                      alert("服务器异常" + xmlHttp.status);
                  }
              }
            };
            xmlHttp.send();
        });

    });
</script>


</body>
</html>
