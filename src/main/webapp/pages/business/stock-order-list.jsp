<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 陈翔
  Date: 2020-02-25
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>进货管理</title>
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

    <link  href="${pageContext.request.contextPath}/lib/ruoyi/css/ry-ui.css" rel="stylesheet" />


    <style type="text/css">
        th{
            text-align: center;
            vertical-align: middle;
            font-size: 15px;
        }

        td{
            text-align: center;
            vertical-align: middle;
            vertical-align: center;
            font-size: 14px;
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
                <div class="btn-group-sm" id="toolbar" role="group">
                    <a class="btn btn-success" data-toggle="modal" data-backdrop="false" data-target="#addCustomerModal" ><i class="fa fa-plus"></i> 新增</a>
                    <a href="javascript:void(0)" class="btn btn-primary" onclick="alterCustomer()"><i class="fa fa-edit"></i> 修改</a>
                    <a href="javascript:void(0)" class="btn btn-danger" onclick="removeCustomer()"><i class="fa fa-remove"></i> 删除</a>
                </div>
                <!-- /col-md-12 -->
                <div class="col-md-12 mt">

                    <div class="col-sm-12 search-collapse">
                        <form id="complex-form" >
                            <div class="select-list">
                                <ul>
                                    <li>
                                        <label style="width: 60px">订单号：</label>
                                        <input type="text" id="stoId" name="stoId"/>
                                    </li>
                                    <li>
                                        <label style="width: 60px">总数量：</label>
                                        <input type="number"  id="minNumber" placeholder="最小数量" name="params[minNumber]"/>
                                        <span>-</span>
                                        <input type="number"  id="maxNumber" placeholder="最大数量" name="params[maxNumber]"/>
                                    </li>


                                    <li class="select-time">
                                        <label>创建时间： </label>
                                        <input type="text" class="time-input" id="startTime" placeholder="开始时间" name="params[beginTime]"/>
                                        <span>-</span>
                                        <input type="text" class="time-input" id="endTime" placeholder="结束时间" name="params[endTime]"/>
                                    </li>

                                    <li>
                                        <label style="width: 60px">总金额：</label>
                                        <input type="number"  id="minAccount" placeholder="最小金额" name="params[minAccount]"/>
                                        <span>-</span>
                                        <input type="number"  id="maxAccount" placeholder="最大金额" name="params[maxAccount]"/>
                                    </li>

                                    <li>
                                        <label style="width: 60px">经手人：</label>
                                        <select id="selectUserId"  name="userId">
                                            <option value="">所有</option>
                                        </select>
                                    </li>


                                    <li>
                                        <label style="width: 80px">支付方式：</label>
                                        <select id="payType"  name="payType">
                                            <option value="">所有</option>
                                            <option value="0">现金</option>
                                            <option value="1">银行转账</option>
                                            <option value="2">微信</option>
                                            <option value="3">支付宝</option>
                                            <option value="4">支票</option>
                                        </select>
                                    </li>
                                    <li>
                                        <label style="width: 80px">订单状态：</label>
                                        <select id="stoStatus" name="stoStatus">
                                            <option value="">所有</option>
                                            <option value="0">已完成</option>
                                            <option value="1">未完成</option>
                                        </select>
                                    </li>

                                    <li>
                                        <a class="btn btn-primary btn-rounded btn-sm" id="btn-search" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                                        <a class="btn btn-warning btn-rounded btn-sm" onclick="resetForm('#complex-form')"><i class="fa fa-refresh"></i>&nbsp;重置</a>
                                    </li>
                                </ul>
                            </div>
                        </form>
                    </div>

                    <div class="content-panel" style="height: 650px;overflow: auto;">
                        <table class="table table-hover rowSameHeight"
                               id="stockOrders"
                               data-toolbar="#toolbar"
                               data-toggle="table"
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
                            <hr>
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true">#</th>
                                <th data-field="stoId">订单号</th>
                                <th data-field="stoNumber">商品总数量</th>
                                <th data-field="orderTime">订单时间</th>
                                <th data-field="totalMoney">订单总金额</th>
                                <th data-field="payType" data-visible="false" style="display: none;">支付方式</th>
                                <th data-field="payTypeStr">支付方式</th>
                                <th data-field="userId" data-visible="false" style="display: none;">经手人</th>
                                <th data-field="userName">经手人</th>
                                <th data-field="stoStatus" data-visible="false" style="display: none;">订单状态</th>
                                <th data-field="stoStatusStr">订单状态</th>
                                <th data-field="stoRemark">备注</th>
                                <th >操作</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${stockOrderList}" var="stockOrder">
                                <tr style="text-align: center;vertical-align: middle">
                                    <td style="text-align: center;vertical-align: middle">
                                    </td>
                                    <td style="text-align: center;vertical-align: middle;">${stockOrder.stoId}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.stoNumber}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.orderTimeStr}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.totalMoney}</td>
                                    <td style="text-align: center;vertical-align: middle;display: none;">${stockOrder.payType}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.payTypeStr}</td>
                                    <td style="text-align: center;vertical-align: middle;display: none;">${stockOrder.user.userId}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.user.username}</td>
                                    <td style="text-align: center;vertical-align: middle;display: none;">${stockOrder.stoStatus}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.stoStatusStr}</td>
                                    <td style="text-align: center;vertical-align: middle">${stockOrder.stoRemark}</td>
                                    <td style="text-align: center;vertical-align: middle">
                                        <button type="button" class="btn btn-info btn-rounded btn-xs" onclick="getOrderDetail(this)">详情</button>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>




                 </div>
                <!-- /col-md-12 -->
            </div>

            <!--  打开订单详情窗口-->
            <div  class="modal fade bs-example-modal-lg" id="orderDetailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="exampleModalLabel">订单详情</h4>
                        </div>
                        <div class="modal-body">
                            <div class="content-panel">
                                <table class="table table-hover rowSameHeight"
                                       id="orderDetail">
                                    <h4><i class="fa fa-angle-right"></i> 采购单信息</h4>
                                    <hr>
                                    <thead>
                                    <tr>
                                        <th>总订单号</th>
                                        <th>商品编号</th>
                                        <th>商品名称</th>
                                        <th>数量</th>
                                        <th>单价</th>
                                        <th>总价格</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
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
<script src="${pageContext.request.contextPath}/lib/tableExport/tableExport.js"></script>
<%--<script src="https://unpkg.com/tableexport.jquery.plugin/tableExport.min.js"></script>--%>
<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table-locale-all.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.16.0/dist/extensions/export/bootstrap-table-export.min.js"></script>


<!-- 数据导出 -->
<script src="${pageContext.request.contextPath}/lib/tableExport/FileSaver.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/tableExport/xlsx.core.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/tableExport/jspdf.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/tableExport/jspdf.plugin.autotable.js"></script>
<script src="${pageContext.request.contextPath}/lib/tableExport/es6-promise.auto.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/tableExport/html2canvas.min.js"></script>

<script src="${pageContext.request.contextPath}/lib/bootstrap-table/js/bootstrap-table-zh-CN.js"></script>

<%--layui插件--%>
<script src="${pageContext.request.contextPath}/lib/layer/layer.js"></script>

<script>

    /**
     * 获取所需要的员工信息
     */
    $(document).ready(function(){
        $.ajax({
            url:'/user/getTargetUsers',
            dataType:'json',
            type:'post',
            success:function(data){
                if(data.code==0){
                    var userList=data.userList;
                    $.each(userList,function(i,item){
                        <!-- 向商品详情表中进行数据注入 -->
                        $("#selectUserId").append("<option value='"+item.userId+"'>"+item.username+"</option>");
                        i++;
                    });
                }else{
                    layer.alert(data.msg, {icon: 5, offset: '0px'});
                }

            }
        });
    });





    /**
     * 获取订单详情
     * @param data1
     */
    function getOrderDetail(data1) {
        var value = $(data1).parent().parent().find("td");
        var stoId=value.eq(1).text().toString().trim();

        $.ajax({
            url:'/business/stockOrder/getOrderDetailById',
            dataType:'json',
            type:'post',
            data:{stoId:stoId},
            success:function(data){
                //alert(11111);
                //alert(data.type);
                if(data.code==0){
                    var list=data.orderDetails;
                    $("#orderDetail tbody tr").eq(0).nextAll().remove();
                    $.each(list,function(i,item){
                        <!-- 向商品详情表中进行数据注入 -->
                        $("#orderDetail tbody").append('<tr>');
                        //$("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.stodId+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.stoId+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.phoneInfo.phoneId+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.phoneInfo.phoneName+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.productNumber+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.unitPrice+'</td>');
                        $("#orderDetail tbody").append('<td style="text-align: center;vertical-align: middle;">'+item.money+'</td>');
                        $("#orderDetail tbody").append('</tr>');
                        i++;
                    });
                    $("#orderDetailModal").modal('show');
                }else{
                    layer.alert(data.msg, {icon: 5, offset: '0px'});
                }

            }
        });



        //$("#orderDetailModal").modal('show');

    }


    $(function(){
        $("#btn1").click(function(){
            $("#orderStockOrderModal").modal("hide");
            $("body").addClass("modal-open");
        });

    });

</script>


<%--日期选择--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laydate/laydate.js"></script>
<script type="text/javascript">



    function resetForm(data) {
        $(data)[0].reset();
    }




    var startDate = laydate.render({
        elem: '#startTime',
        max: $('#endTime').val(),
        theme: 'molv',
        trigger: 'click',
        done: function(value, date) {
            // 结束时间大于开始时间
            if (value !== '') {
                endDate.config.min.year = date.year;
                endDate.config.min.month = date.month - 1;
                endDate.config.min.date = date.date;
            } else {
                endDate.config.min.year = '';
                endDate.config.min.month = '';
                endDate.config.min.date = '';
            }
        }
    });

    var endDate = laydate.render({
        elem: '#endTime',
        min: $('#startTime').val(),
        theme: 'molv',
        trigger: 'click',
        done: function(value, date) {
            // 开始时间小于结束时间
            if (value !== '') {
                startDate.config.max.year = date.year;
                startDate.config.max.month = date.month - 1;
                startDate.config.max.date = date.date;
            } else {
                startDate.config.max.year = '';
                startDate.config.max.month = '';
                startDate.config.max.date = '';
            }
        }
    });
</script>

</body>
</html>
