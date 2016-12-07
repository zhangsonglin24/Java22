
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="" enctype="application/x-www-form-urlencoded"></form>
<input type="text" id="name">
<button id="btn">SendRequest</button>
<div id="result"></div>

<script>
    (function () {
     //创建XMLHttpRequest对象
        function createXmlHttp() {
            var xmlHttp = null;
            if(window.ActiveXObject){
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xmlHttp = new XMLHttpRequest();
            }
                return xmlHttp;
        }
            document.querySelector("#btn").onclick = function () {
                var name = document.querySelector("#name").value;
              // sendGet(name);
                sendPost(name);
            }
            function sendGet(name) {
                //获取Ajax引擎
                var xmlHttp = createXmlHttp();
                //指定请求方式和请求地址
                xmlHttp.open("get","/ajax?name="+name);
                //发送请求
                xmlHttp.send();
            }
            function sendPost(name) {
                //获取引擎
                var xmlHttp = createXmlHttp();
                //指定请求方式和地址
                xmlHttp.open("post","/ajax")
                xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
                //设置回调函数
                xmlHttp.onreadystatechange = function () {
                    var state = xmlHttp.readyState;
                    if(state == 4){
                        var httpState = xmlHttp.status;
                        if(httpState == 200){
                            var result = xmlHttp.responseText;
                            var div = document.querySelector("#result");
                            if(result == "可以用"){
                                div.innerText = "账号可以使用";
                            }else{
                                div.innerText = "账号已被占用"
                            }

                        }else{
                            alert("服务器错误" + httpState);
                        }
                    }
                }
                //发送请求
                xmlHttp.send("name="+name);

            }

    })();
</script>



</body>
</html>
