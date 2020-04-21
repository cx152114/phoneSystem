<%--
  Created by IntelliJ IDEA.
  User: 陈翔
  Date: 2020-03-17
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录日志</title>
    <!-- Favicons -->
    <link href="${pageContext.request.contextPath}/img/favicon.png" rel="icon">
    <link href="${pageContext.request.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/build.css"/>
    <!-- Bootstrap table core CSS -->
    <link href="${pageContext.request.contextPath}/lib/bootstrap-table/css/bootstrap-table.css" rel="stylesheet">
    <%--    引入时间选择控件--%>
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap-datepicker/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap-daterangepicker/daterangepicker.css" />

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet">

    <style type="text/css">
        th{
            text-align: center;
            vertical-align: middle;
        }

        td{
            text-align: center;
            vertical-align: middle;
            vertical-align: center;
        }

        .content-panel{
            padding-left: 15px;
            padding-right: 15px;
        }

        .rowSameHeight {
            white-space: nowrap;
            overflow: hidden;
        }

    </style>
</head>
<body>

<div class="row">
    <!-- /col-md-12 -->
    <div class="col-md-12 mt">
        <!-- 删除按钮 -->
        <%--                        <button style="width: 75px;" type="button" class="btn btn-danger" data-toggle="modal" data-backdrop="false" data-target="#removeProductModal">删除</button>--%>
        <a href="javascript:void(0)" style="width: 75px;" class="btn btn-danger" onclick="removeUser()">删除</a>

        <hr />
        <div class="content-panel" style="height: 650px;overflow: auto;"  >
<%--                <div class="card-header"><h4>查询条件</h4></div>--%>
<%--                <div>--%>
<%--                    <form class="form-inline" action="#" method="post" onsubmit="return false;">--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="sr-only" for="loginName">登录名称</label>--%>
<%--                            <input class="form-control" type="email" id="loginName" name="loginName" placeholder="请输入登录名称..">--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label class="sr-only" for="loginIp">登录地址</label>--%>
<%--                            <input class="form-control" type="text" id="loginIp" name="loginIp" placeholder="请输入登录地址">--%>
<%--                        </div>--%>

<%--                        <div class="form-group">--%>
<%--                            <label class="sr-only">时间</label>--%>
<%--                                <div class="input-group input-large" data-date="01/01/2014" data-date-format="mm/dd/yyyy">--%>
<%--                                    <input type="text" class="form-control dpd1" id="startTime" name="startTime" placeholder="请输入开始时间">--%>
<%--                                    <div class="input-group-btn">--%>
<%--                                        <button type="button" class="btn btn-theme02 date-reset"><i class="fa fa-times"></i></button>--%>
<%--                                        <button type="button" class="btn btn-theme date-set"><i class="fa fa-calendar"></i></button>--%>
<%--                                    </div>--%>
<%--                                    <span class="input-group-addon">到</span>--%>


<%--                                    <input type="text" class="form-control dpd2" id="endTime" name="endTime" placeholder="请输入结束时间">--%>
<%--                                </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <button class="btn btn-default" type="submit">搜索</button>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>

            <table class="table table-hover rowSameHeight"
                   data-toggle="table"
                   id="loginLogs"
                   data-search="true"
                   data-show-refresh="true"
                   data-show-toggle="true"
                   data-show-fullscreen="true"
                   data-show-columns="true"
                   data-show-columns-toggle-all="true"
                   data-show-export="true"
                   data-click-to-select="true"
                   data-single-select="true"
                   data-exportDataType ="basic"
                   data-show-pagination-switch="true"
                   data-pagination="true"
                   data-page-list="[5,10,25,50,100,all]"
                   data-side-pagination="client">
                <hr/>
            </table>
        </div>

    </div>
    <!-- /col-md-12 -->
</div>





<!-- 删除员工-->
<div class="modal fade" id="removeUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel" >
                    删除员工（此过程不可逆，谨慎操作）
                </h4>
            </div>
            <div class="modal-body">
                <h4 style="color: red;">是否移除选定员工？</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="remove()">移除</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery.scrollTo.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>
<!--common script for all pages-->
<script src="${pageContext.request.contextPath}/lib/common-scripts.js"></script>
<!--script for this page-->

<!--bootstrap-table-->
<script src="${pageContext.request.contextPath}/lib/bootstrap-table/js/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

<%--layui插件--%>
<script src="${pageContext.request.contextPath}/lib/layer/layer.js"></script>



<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script src="${pageContext.request.contextPath}/lib/advanced-form-components.js"></script>



<script>
    //var list_url = '${pageContext.request.contextPath}/sys/log-login/loadAllLoginfo';
    // 初始化表格数据
    // var dataTable = $('#loginLogs').bootstrapTable({
    //     //url: list_url,                      //  请求后台的URL
    //     method: "get",                      //  请求方式
    //     uniqueId: "id",                 //  每一行的唯一标识，一般为主键列
    //     cache: false,                       //  设置为 false 禁用 AJAX 数据缓存， 默认为true
    //     pagination: true,                   //  是否显示分页
    //     sidePagination: "client",           //  分页方式：client客户端分页，server服务端分页
    //     pageSize: 10,                       //  每页的记录行数
    //     columns: [{
    //         checkbox: true
    //     }, {
    //         field: 'id',
    //         title: '编号'
    //     }, {
    //         field: 'loginName',
    //         title: '登录名称'
    //     }, {
    //         field: 'loginIp',
    //         title: '登录地址'
    //     }, {
    //         field: 'loginTime',
    //         title: '登录时间'
    //     }]
    // });

    // 查询
    // $('#btn-search').bind('click', function () {
    //     refreshTable();
    // });

    // 刷新表格
    // function refreshTable() {
    //     dataTable.bootstrapTable('refresh', {
    //         url: list_url,
    //         pageSize: 10,
    //         pageNumber: 1
    //     });
    // }



</script>
</body>
</html>
