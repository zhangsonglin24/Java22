<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/10
  Time: 17:23
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
<Script src="/static/js/handlebars-v4.0.5.js"></Script>
<script type="text/handlebars" id="temp1">
    <div class="entry">
        <h1>{{{title}}}</h1>
        <div class="body">
            {{{body}}}
        </div>
        <div>
            <ul>
             {{#each data}}
                   <li>{{name}}</li>
              {{/each}}
            </ul>
        </div>
        <div>
        {{#if vip}}
                <p>Welcome vip!!!!!!!!!!!!</p>
       {{/if}}
        </div>
</script>
<script>
    $(function () {
       $("#btn").click(function () {
           var source = $("#temp1").html();
           var template = Handlebars.compile(source);
           data = {
               "title":"<span style='color : yellow'>今天心情</span>",
               "body":"<span style='color : red'>还不错</span>",
               "data" : [
                   {"name":"tom"},
                   {"name":"rose"},
                   {"name":"jack"},
                   {"name":"alix"}
               ],
               "vip":true
           };
           var html = template(data);
           $(html).appendTo($("#result"));
       }) ;
    });
</script>


</body>
</html>
