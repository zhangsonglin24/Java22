<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/7
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">load json</button>
<span id="text"></span>

<script>

    (function () {
        document.querySelector("#btn").onclick = function () {
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("get","/data.json");
            xmlHttp.onreadystatechange = function () {
                if(xmlHttp.readyState == 4){
                    if(xmlHttp.status == 200){
                        var result = xmlHttp.responseText;
                        var json = JSON.parse(result);
                        for(var i = 0;i < json.length;i++){
                            var user = json[i];
                            alert(user.id + " -> " + user.username + " -> " + user.address);
                        }



                    }else {
                        alert("服务器异常" + xmlHttp.status);
                    }
                }
            };
            xmlHttp.send();
        };

    })();
</script>

</body>
</html>
