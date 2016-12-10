<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/9
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="result"></div>
<script src="/static/js/jquery-1.11.3.min.js"></script>

<script>
    $(function () {
       var maxId = 0;

        function call() {
            $.get("/timeline",{"maxId":maxId}).done(function (data) {
                if(data.length){
                    for(var i = 0;i< data.length;i++){
                        var item = data[i];
                        var html = "<h3>"+item.message+"</h3>";
                        if(maxId == 0){
                            $(html).appendTo($("#result"));
                        }else{
                            $(html).prependTo($("#result"));
                        }
                    }
                }
                
            }).error(function () {
                alert("服务器错误");
                clearInterval(st);
                
            });
            
        }

    });
</script>

</body>
</html>
