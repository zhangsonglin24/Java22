<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/12/20
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>巅峰论坛${topic.title}</title>
    <link href="http://cdn.bootcss.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/css/styles/solarized-dark.css">
    <style>
        body{
            background-image: url(/static/img/bg.jpg);
        }
        .simditor .simditor-body {
            min-height: 100px;
        }
        .topic-body img {
            width: 200px;
        }
        pre {
            padding: 0px;
            border: 0px;
            background-color: transparent;
        }
    </style>
</head>
<body>
<%@include file="../include/navbar.jsp"%>
<!--header-bar end-->
<div class="container">
    <div class="box">
        <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
            <li><a href="#">首页</a> <span class="divider">/</span></li>
            <li class="active">${requestScope.topic.node.nodename}</li>
        </ul>
        <div class="topic-head">
            <img class="img-rounded avatar" src="${requestScope.topic.user.getAvatar()}?imageView2/1/w/60/h/60" alt="">
            <h3 class="title">${requestScope.topic.title}</h3>
            <p class="topic-msg muted"><a href="">${requestScope.topic.user.username}</a> · <span id="topicTime">${requestScope.topic.createtime}</span></p>
        </div>
        <div class="topic-body">
            ${requestScope.topic.content} </div>
        <div class="topic-toolbar">
         <c:if test="${not empty sessionScope.curr_user}">
            <ul class="unstyled inline pull-left">
                <c:choose>
                    <c:when test="${not empty fav}">
                <li><a href="javascript:;" id="favtopic">取消收藏</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:;" id="favtopic">加入收藏</a></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty thanks}">
                        <li><a href="javascript:;" id="thanks">取消感谢</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:;" id="thanks">感谢</a></li>
                    </c:otherwise>
                </c:choose>

                <c:if test="${sessionScope.curr_user.id == topic.userid and topic.edit}">
                <li><a href="/topicEdit?topicid=${topic.id}">编辑</a></li>
                </c:if>
            </ul>
         </c:if>
            <ul class="unstyled inline pull-right muted">
                <li>${topic.clicknum}次点击</li>
                <li><span id="favnum">${topic.favnum}</span>人收藏</li>
                <li><span id="thanksnum">${topic.thanksnum}</span>人感谢</li>
            </ul>
        </div>
    </div>
    <!--box end-->
    <c:if test="${not empty replyList}">
    <div class="box" style="margin-top:20px;">
        <div class="talk-item muted" style="font-size: 12px">
            ${fn:length(replyList)}个回复 | 直到<span id="lastreplytime">${topic.lastreplytime}</span>
        </div>
        <c:forEach items="${replyList}" var="reply" varStatus="vs">
        <div class="talk-item">
            <table class="talk-table">
                <tr>
                    <a name="reply${vs.count}"></a>
                    <td width="50">
                        <img class="avatar" src="${reply.user.avatar}?imageView2/1/w/40/h/40" alt="">
                    </td>
                    <td width="auto">
                        <a href="" style="font-size: 12px">${reply.user.username}</a> <span style="font-size: 12px" class="reply">${reply.createtime}</span>
                        <br>
                        <p style="font-size: 14px">${reply.content}</p>
                    </td>
                    <td width="70" align="right" style="font-size: 12px">
                        <a href="javascript:;" rel="${vs.count}" class="replyLink" title="回复"><i class="fa fa-reply"></i></a>&nbsp;
                        <span class="badge">${vs.count}</span>
                    </td>
                </tr>
            </table>
        </div>
        </c:forEach>
    </div>
    </c:if>
    <c:choose>
        <c:when test="${not empty sessionScope.curr_user}">
    <div class="box" style="margin:20px 0px;">
        <a name="reply"></a>
        <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
        <form action="newReply" method="post" id="replyForm" style="padding: 15px;margin-bottom:0px;">
            <input name="topicid" type="hidden" value="${topic.id}">
            <textarea name="content" id="editor"></textarea>
        </form>
        <div class="talk-item muted" style="text-align: right;font-size: 12px">
            <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
            <button id="replyBtn" class="btn btn-primary">发布</button>
        </div>
    </div>
        </c:when>
        <c:otherwise>
            <div class="box" style="margin:20px 0px;">
          <div class="talk-item muted" style="font-size: 20px"></i>请<span style="background-color: #0000ee"><a href="/login?redirect=topicDetail?topicid=${topic.id}#reply">登录</a></span>后再回复</div>
          </div>
        </c:otherwise>
    </c:choose>   

</div>
<!--container end-->
<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script src="/static/js/editer/scripts/module.min.js"></script>
<script src="/static/js/editer/scripts/hotkeys.min.js"></script>
<script src="/static/js/editer/scripts/uploader.min.js"></script>
<script src="/static/js/editer/scripts/simditor.min.js"></script>
<script src="/static/js/highlight.pack.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script src="/static/js/moment.js"></script>
<script>
    $(function(){
        <c:if test="${not empty sessionScope.curr_user}">
        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar:false
            //optional options
        });
        $(".replyLink").click(function(){
            var count = $(this).attr("rel");
            var html = "<a href='#reply"+count+"'>"+ count +"楼</a>";
            editor.setValue(html + editor.getValue());
            window.location.href="#reply";
        });
        </c:if>



        $("#replyBtn").click(function () {
            $("#replyForm").submit();
        });


        $("#favtopic").click(function () {
            var $this = $(this);
            var action = "";
            if($this.text() == "加入收藏"){
                action = "fav";
            }else{
                action = "unfav";
            }

            $.post("/topicFav",{"topicid":${topic.id},"action":action}).done(function (json) {
                if(json.state == "success"){
                    if(action == "fav"){
                        $this.text("取消收藏");
                    }else{
                        $this.text("加入收藏");
                    }
                    $("#favnum").text(json.data);
                }

            }).error(function () {
                alert("收藏失败");
            });

        });

        $("#thanks").click(function () {
            var $this = $(this);
            if($this.text() == "感谢"){
                action = "thanks";
            }else{
                action = "unthanks";
            }

            $.post("/topicThanks",{"topicid":${topic.id},"action":action}).done(function (json) {
                if(json.state == "success"){
                    if(action == "thanks"){
                        $this.text("取消感谢");
                    }else{
                        $this.text("感谢");
                    }
                    $("#thanksnum").text(json.data);
                }
            }).error(function () {
                alert("感谢失败");
            });
        });


       /* $("#replyForm").validate({
            errorElement:"span",
            errorClass:"text-error",
            rules:{
                content:{
                    required:true
                }
            },
            messages:{
                content:{
                    required:"回复不能为空"
                }
            }
        });
*/
        moment.locale("zh-cn");

        $("#topicTime").text(moment($("#topicTime").text()).fromNow());
        $("#lastreplytime").text(moment($("#lastreplytime").text()).format("YYYY年MM月DD日 hh:mm:ss"));
        $(".reply").text(function () {
            var time = $(this).text();
            return moment(time).fromNow();
        });

        hljs.initHighlightingOnLoad();

    });
</script>

</body>
</html>
