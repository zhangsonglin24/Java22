<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增设备租赁合同</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/aside.jsp">
        <jsp:param name="menu" value="business_work_out"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务派遣</h3>

                    <div class="box-tools pull-right">
                        <a href="/work/out" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>租赁公司</label>
                                <input type="text" class="form-control" id="companyName" tabindex="1">
                            </div>
                            <div class="form-group">
                                <label>地   址</label>
                                <input type="text" class="form-control" id="address" tabindex="4">
                            </div>
                            <div class="form-group">
                                <label>佣金金额</label>
                                <input type="text" class="form-control" id="wageNumber" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" id="linkMan" tabindex="2">
                            </div>
                            <div class="form-group">
                                <label>电   话</label>
                                <input type="text" class="form-control" id="tel" tabindex="5">
                            </div>
                            <div class="form-group">
                                <label>预付款</label>
                                <input type="text" class="form-control" id="preCost" tabindex="7" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司电话</label>
                                <input type="text" class="form-control" id="companyTel" tabindex="3">
                            </div>
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" id="cardNum" tabindex="6">
                            </div>
                            <div class="form-group">
                                <label>尾   款</label>
                                <input type="text" class="form-control" id="lastCost" readonly>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">工种列表</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>工种</th>
                            <th>单位</th>
                            <th>佣金</th>
                            <th>工种数量</th>
                            <th>小计</th>
                            <th>#</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="workArray.length == 0">
                            <td colspan="6">暂无数据</td>
                        </tr>
                        <tr v-for="work in workArray">
                            <td>{{work.name}}</td>
                            <td>{{work.unit}}</td>
                            <td>{{work.wage}}</td>
                            <td>{{work.num}}</td>
                            <td>{{work.total}}</td>
                            <td><a href="javascript:;" @click="remove(work)"><i class="fa fa-trash text-danger"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="box-footer" style="text-align: right">
                    总租赁费 {{total}} 元 预付款 {{preCost}} 元 尾款 {{lastCost}} 元
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                </div>
                <div class="box-body">
                    <div id="picker">选择文件</div>
                    <p>注意：上传合同扫描件要求清晰可见 合同必须公司法人签字盖章</p>
                    <ul id="fileList">
                    </ul>
                    <button class="btn btn-primary pull-right" type="button" @click="saveRent">保存合同</button>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <d4iv class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">选择设备</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="workName">
                            <label>工种名称</label>
                            <select id="workId" style="width: 300px;" class="form-control">
                                <option value="">选择工种</option>
                                <c:forEach items="${workList}" var="work">
                                    <option value="${work.id}">${work.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>剩余人数</label>
                            <input type="text" class="form-control" id="currNum" readonly>
                        </div>
                        <div class="form-group">
                            <label>单位</label>
                            <input type="text" class="form-control" id="unit" readonly>
                        </div>
                        <div class="form-group">
                            <label>佣金</label>
                            <input type="text" class="form-control" id="wage" readonly>
                        </div>
                        <div class="form-group">
                            <label>派遣数量</label>
                            <input type="text" class="form-control" id="outNum">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="addWork">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </d4iv><!-- /.modal -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>

    var fileArray = [];

    $(function () {
       $("#workId").select2();
        $("#workId").change(function () {
            var id = $(this).val();
            if(id){
                $.get("/work/out/work.json",{"id":id}).done(function (resp) {
                    if(resp.status == "success"){
                        var work = resp.data;
                        $("#currNum").val(work.currentNum);
                        $("#unit").val(work.unit);
                        $("#wage").val(work.wage);
                    }else {
                        layer.msg(resp.message);
                    }
                }).error(function () {
                    layer.msg("服务器忙，请稍后再试");
                })
            }
        })
    });

    var app = new Vue({
        el:"#app",
        data:{
            workArray:[]
        },
        methods:{
            addWork:function () {
                var id = $("#workId").val();
                //判断数组中是否存在当前工种，如果有则数量累加，更新佣金
                var flag = false;
                for(var i = 0;i < this.$data.workArray.length;i++){
                    var item = this.$data.workArray[i];
                    if(item.id == id) {
                        this.$data.workArray[i].num = this.$data.workArray[i].num + $("#outNum").val();
                        this.$data.workArray[i].total = this.$data.workArrayk[i].num * parseFloat($("#wage").val());
                        flag = true;
                        break;
                    }
                }
                //如果没有则添加新JSON对象
                if(!flag){
                    var json = {};
                    json.id = id;
                    json.workName = $("#workName").val();
                    json.unit = $("#unit").val();
                    json.wage = $("#wage").val();
                    json.outNum = $("#outNum").val();
                    json.total = parseFloat(json.wage) * json.outNum;

                    this.$data.workArray.push(json);
                }
            },
            remove:function(work){
                layer.confirm("确定要删除吗",function(index){
                    app.$data.workArray.splice(app.$data.workArray.indexOf(work),1);
                    layer.close(index);
                });
            },
            saveWork:function(){
                var json = {
                    workArray : app.$data.workArray,
                    fileArray : fileArray,
                    companyName : $("#companyName").val(),
                    tel : $("#tel").val(),
                    linkMan : $("#linkMan").val(),
                    cardNum : $("#cardNum").val(),
                    address : $("#address").val(),
                    wageNumber : $("#wageNumber").val(),
                    preCost : $("#preCost").val(),
                    companyTel : $("#companyTel").val(),
                    lastCost : $("#lastCost").val()
                };

                $.ajax({
                    url:"/work/out/new",
                    type:"post",
                    data: JSON.stringify(json),//将JSON转换成字符串
                    contentType: "application/json;charset=UTF-8",
                    success:function(data){
                        if(data.status == 'success') {
                            layer.confirm("保存成功",{btn:['继续添加','打印合同']},function(){
                                window.history.go(0);
                            },function(){
                                window.location.href = "/device/rent/"+data.data;
                            });
                        }else{
                            layer.msg(data.message);
                        }
                    },
                    error:function(){
                        layer.msg("服务器忙，请稍后");
                    }
                });

            }
        }
    });

</script>
</body>
</html>
