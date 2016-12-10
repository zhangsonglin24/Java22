<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/6
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="username">
<span id="loding" style="display: none"><img src="/static/img/loding.gif" alt=""></span>
<span id="text"></span>


<script src="/static/js/ajax.js"></script>
<script>
    (function () {
        document.querySelector("#username").onblur = function () {
          var name = document.querySelector("#username").value;
           ajax.sendPost({
               url : "/ajax",
               data : "name="+name+"&age=23",
               success : function (data) {
                   alert(data);
               },
               error : function () {
                   alert("服务器异常");
               }

           });
        };
    })();



   /* $(function () {
      $("#username").blur(function () {
         var name = $(this).val();

          $.post("/ajax",{"name":name}).done(function (data) {
              $("#text").text(data);
          }).error(function () {
             alert("服务器异常")
          });*/
/*
          $.ajax({
              url : "/ajax",
              type : "post",
              data : {"name":name},
              beforeSend : function () {
                 $("#loding").show();
                  $("#text").text("");
              },
              success : function (data) {
                  $("#text").text(data);
              },
              error : function () {
                  alert("服务器异常");
              },
              complete : function () {
                  $("#loding").hide();
              }
          });
      });
    });*/
</script>

</body>
</html>
