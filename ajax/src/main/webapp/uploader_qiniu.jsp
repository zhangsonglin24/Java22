<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/12
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/js/webuploader/webuploader.css">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="picker">选择文件</div>
<div id="result"></div>

<script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/webuploader/webuploader.min.js"></script>
<script>
    $(function () {
        var upload = WebUploader.create({
            swf:"/static/js/webuploader/Uploader.swf",
            server:"http://up-z1.qiniu.com/",
            fileVal:"file",
            formData:{"token":"${token}","x:id":"${uid}"},
            pick:"#picker",
            auto:true
        });

        upload.on("uploadSuccess",function (file,data) {
            var img = $("#result").find("img");
            if(img[0]){
                img.remove();
            }
           var url = "http://oi0mgf04v.bkt.clouddn.com/" + data.key + "?imageView2/1/w/200/h/200";
            $("<img>").attr("src",url).addClass("img-circle").appendTo($("#result"));

            alert(data["x:id"]);
        });
        upload.on("uploadError",function (file) {
           alert("上传错误");
        });
        
    });
</script>
</body>
</html>
