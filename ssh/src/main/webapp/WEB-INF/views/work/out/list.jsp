<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>设备租赁</title>
    <%@include file="../../include/css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="/static/plugins/datatables/extensions/FixedHeader/css/dataTables.fixedHeader.min.css">
    <link rel="stylesheet" href="/static/Font-Awesome/css/font-awesome.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <jsp:include page="../../include/aside.jsp">
        <jsp:param name="menu" value="business_work_out"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">劳务派遣</h3>

                    <div class="box-tools pull-right">
                        <a href="/work/out/add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>流水号</th>
                            <th>用人单位</th>
                            <th>派遣日期</th>
                            <th>归来日期</th>
                            <th>状态</th>
                            <th>查询</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp"%>
<script src="/static/plugins/datatables/jquery.dataTables.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/datatables/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function(){
        var table = $(".table").DataTable({
            "lengthChange": false,
            "pageLength": 25,
            "serverSide": true,
            "ajax":{
                "url":"/device/rent/load",
                "type":"get"
            },
            "searching":false,//不使用自带的搜索
            "order":[[0,'desc']],//默认排序方式,
            "ordering": false,
            "columns":[
                {"data":"id","name":"id"},
                {"data":function(row){
                    if(row.serialNumber) {
                        return "<a href='/device/rent/" + row.serialNumber + "'>" + row.serialNumber + "</a>";
                    } else {
                        return "";
                    }
                }},
                {"data":"companyName"},
                {"data":"tel"},
                {"data":"rentDate"},
                {"data":"backDate"},
                {"data":"state"},
                {"data":"totalPrice"},
                {"data":function(row){
                    if(row.state) {
                        return "";
                    } else {
                        return "<a href='javascript:;' rel='" + row.id + "' class=' icon-ok-sign checkBtn'> <i class='fa fa-check'></i> 完成</a>";
                    }
                }}
            ],
            "columnDefs":[
                {targets:[0],visible: false}
            ],
            "language":{ //定义中文
                "search": "搜索:",
                "zeroRecords":    "没有匹配的数据",
                "lengthMenu":     "显示 _MENU_ 条数据",
                "info":           "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中过滤得来)",
                "loadingRecords": "加载中...",
                "processing":     "处理中...",
                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }
        });
        new $.fn.dataTable.FixedHeader(table);

        //将合同变为完成状态
        $(document).delegate(".checkBtn","click",function(){
            var id = $(this).attr("rel");
            layer.confirm("确定要将合同修改为已完成吗",function(index){
                $.post("/device/rent/state/change",{"id":id}).done(function(resp){
                    if(resp.status == 'success') {
                        table.ajax.reload();
                    }
                }).error(function () {
                    layer.msg("服务器忙，请稍后再试");
                });
                layer.close(index);
            });
        });
    });
</script>
</body>
</html>
