<%--
  Created by IntelliJ IDEA.
  User: 陈翔
  Date: 2020-03-05
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>员工管理</title>
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
    <%--引入bootstrap用于表单验证的插件--%>
    <link href="${pageContext.request.contextPath}/lib/bootstrapValidator/css/bootstrapValidator.css" rel="stylesheet">

    <link  href="${pageContext.request.contextPath}/lib/ruoyi/css/ry-ui.css" rel="stylesheet" />


    <style type="text/css">
        th{
            text-align: center;
            vertical-align: middle;
            font-size: 15px;
        }

        td{
            text-align: center;
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
                    <a class="btn btn-success" data-toggle="modal" data-backdrop="false" data-target="#addUserModal" ><i class="fa fa-plus"></i> 新增</a>

                    <a href="javascript:void(0)" class="btn btn-primary" onclick="alterUser()"><i class="fa fa-edit"></i> 修改</a>

                    <a href="javascript:void(0)" class="btn btn-danger" onclick="removeUser()"><i class="fa fa-remove"></i> 删除</a>
                    <a href="javascript:void(0)" class="btn btn-info" id="btn-assign-role"><i class="fa fa-chain"></i> 分配角色</a>
                </div>

                <!-- /col-md-12 -->
                <div class="col-md-12 mt">
                    <div class="col-sm-12 search-collapse">
                        <form id="complex-form" >
                            <div class="select-list">
                                <ul>
                                    <li>
                                        <label style="width: 80px">员工编号：</label>
                                        <input type="text" style="width: 80px;" id="userId2" name="userId"/>
                                    </li>
                                    <li>
                                        <label style="width: 80px">员工名称：</label>
                                        <input type="text" style="width: 80px;" id="phoneName2" name="phoneName"/>
                                    </li>



                                    <li>
                                        <label style="width: 80px">所属部门：</label>
                                        <select id="phoneType2" style="width: 100px;"  name="phoneType">
                                            <option value=""></option>
                                        </select>
                                    </li>

                                    <li class="select-time">
                                        <label>创建时间： </label>
                                        <input type="text" class="time-input" id="startTime" placeholder="开始时间" name="params[beginTime]"/>
                                        <span>-</span>
                                        <input type="text" class="time-input" id="endTime" placeholder="结束时间" name="params[endTime]"/>
                                    </li>



                                    <li>
                                        <label style="width: 40px">状态：</label>
                                        <select id="phoneState2" style="width: 80px;" name="phoneState">
                                            <option value="">所有</option>
                                            <option value="0">正常</option>
                                            <option value="1">冻结</option>
                                            <option value="1">休假</option>
                                            <option value="1">离职</option>
                                        </select>
                                    </li>

                                    <li>
                                        <a class="btn btn-primary btn-rounded btn-sm" id="btn-search"><i class="fa fa-search"></i>&nbsp;搜索</a>
                                        <a class="btn btn-warning btn-rounded btn-sm" onclick="resetForm('#complex-form')"><i class="fa fa-refresh"></i>&nbsp;重置</a>
                                    </li>
                                </ul>
                            </div>
                        </form>
                    </div>


                    <div class="content-panel" style="height: 650px;overflow: auto;"  >
                        <table class="table table-hover rowSameHeight"
                               data-toggle="table"
                               id="users"
                               data-toolbar="#toolbar"
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
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true">#</th>
                                <th data-field="userId">编号</th>
                                <th data-field="username">姓名</th>
                                <th data-field="userEmail">邮箱</th>
                                <th data-field="userPhone">手机号</th>
                                <th data-field="deptId">部门</th>
                                <th data-field="userTime">创建时间</th>
                                <th data-field="userStatus">状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${userList}" var="employee">
                                <tr style="text-align: center;vertical-align: middle">
                                    <td style="text-align: center;vertical-align: middle">
                                    </td>
                                    <td style="text-align: center;vertical-align: middle;display: none">${employee.userId}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.username}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.userEmail}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.userPhone}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.deptId}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.createTimeStr}</td>
                                    <td style="text-align: center;vertical-align: middle">${employee.userStatusStr}</td>
                                    <td>分配角色</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                </div>
                <!-- /col-md-12 -->
            </div>

            <!-- 添加员工 -->
            <div class="modal inmodal" id="addUserModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">

                        <div class="panel  form-panel "  style="margin:0 auto; width:100%; border:1px solid #F00; ">
                            <div class="panel-heading" style="text-align: center;">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="mb" style="margin: 0px">新增员工</h3>
                            </div>
                            <div><p></p><p></p></div>
                            <form class="form-horizontal style-form" id="addUserForm" action="/user/addUser" method="post">
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">姓名：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" name="username" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">邮箱：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" name="userEmail" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">手机号：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" name="userPhone" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">密码：</label>
                                    <div class="col-lg-5">
                                        <input type="password" class="form-control" name="password" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">确认密码：</label>
                                    <div class="col-lg-5">
                                        <input type="password" class="form-control" name="confirmPassword" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">状态：</label>
                                    <div class="col-lg-5">
                                        <label class="radio-inline" >
                                            <input type="radio"  name="userStatus" checked="checked"  value="0">
                                            正常
                                        </label>
                                        <label class="radio-inline" >
                                            <input type="radio"   name="userStatus" value="1">
                                            禁用
                                        </label>
                                        <label class="radio-inline" >
                                            <input type="radio"   name="userStatus" value="2">
                                           锁定
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">部门：</label>
                                    <div class="col-lg-5">
                                        <select class="form-control"  style="width: 100px;" id="deptId" name="deptId">
                                            <option style='display: none'></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button type="reset" id="resetBtn" class="btn btn-warning">重置</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        </div>
                    </div>
                    <small class="font-bold"></small>
                </div>
                <small class="font-bold"></small>
            </div>


            <!-- 修改部门信息 -->
            <div class="modal inmodal" id="alterUserModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                        <div class="panel  form-panel "  style="margin:0 auto; width:100%; border:1px solid #F00; ">
                            <div class="panel-heading" style="text-align: center;">
                                <h3 class="mb" style="margin: 0px">修改员工信息</h3>
                            </div>

                            <div><p></p><p></p></div>
                            <form class="form-horizontal style-form" id="editUserForm" action="/user/editUser" method="post">
                                <div class="form-group" style="display: none">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">编号：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" id="userId" name="userId" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">姓名：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" id="username" name="username" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">邮箱：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" id="userEmail" name="userEmail" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">手机号：</label>
                                    <div class="col-lg-5">
                                        <input type="text" class="form-control" id="userPhone" name="userPhone" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">密码：</label>
                                    <div class="col-lg-5">
                                        <input type="password" class="form-control"  name="password" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">确认密码：</label>
                                    <div class="col-lg-5">
                                        <input type="password" class="form-control"   name="confirmPassword" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">状态：</label>
                                    <div class="col-lg-5">
                                        <label class="radio-inline" >
                                            <input type="radio" id="userStatus1"  name="userStatus"  value="0">
                                            正常
                                        </label>
                                        <label class="radio-inline" >
                                            <input type="radio" id="userStatus2"  name="userStatus" value="1">
                                            禁用
                                        </label>
                                        <label class="radio-inline" >
                                            <input type="radio" id="userStatus3"   name="userStatus" value="2">
                                            锁定
                                        </label>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-lg-3 control-label" style="font-size:19px;margin-bottom: 3px;margin-left: 5px;text-align: right">部门：</label>
                                    <div class="col-lg-5">
                                        <select class="form-control"  style="width: 100px;" id="deptId2" name="deptId">
                                            <option style='display: none'></option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <button type="submit" class="btn btn-primary">提交</button>
                                        <button type="reset"  class="btn btn-warning">重置</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <small class="font-bold"></small>
                </div>
                <small class="font-bold"></small>
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


            <div class="modal inmodal" id="allocationRoleModal" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">

                        <div class="panel  form-panel "  style="margin:0 auto; width:100%; border:1px solid #F00; ">
                            <div class="panel-heading" style="text-align: center;">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="mb" style="margin: 0px">分配角色</h3>
                            </div>
                            <div><p></p><p></p></div>
                            <form class="form-horizontal style-form"  id="allocationRole" onsubmit="return false">
                                    <div class="form-group" >
                                        <input type="hidden" name="userId" id="targetUserId" value="${userId}">
                                        <div class="col-lg-9 col-lg-offset-3"id="roleList">

                                        </div>
                                    </div>
                                <div class="form-group">
                                    <div class="col-lg-9 col-lg-offset-3">
                                        <button type="submit" id="btn-submit" class="btn btn-primary">提交</button>
                                        <button type="reset"  class="btn btn-warning">重置</button>
                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <small class="font-bold"></small>
            </div>
            <small class="font-bold"></small>
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
<%--<script  type="text/javascript" src="${pageContext.request.contextPath}/lib/tableExport/bootstrap-table-export.min.js"></script>--%>



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


<%--引入bootstrap用于表单验证的插件--%>
<script src="${pageContext.request.contextPath}/lib/bootstrapValidator/js/bootstrapValidator.js"></script>

<script>


    /**
     * 设置导出文件的属性
     */
    $.extend($.fn.bootstrapTable.defaults, {
        showExport: false,
        exportDataType: 'basic', // basic, all, selected
        // 'json', 'xml', 'png', 'csv', 'txt', 'sql', 'doc', 'excel', 'powerpoint', 'pdf'
        exportTypes: ['json', 'xml', 'csv', 'txt', 'sql','doc', 'excel','pdf'],
        exportOptions: {
            // 导出数据去除第一列出现"on"
            ignoreColumn: [0]
        }
    });

    $(document).ready(function() {
        $('#addUserForm').bootstrapValidator({
            message: '请填写正确的用户信息',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空 '
                        },
                        different: {
                            field: 'password,confirmPassword',
                            message: 'The username and password cannot be the same as each other'
                        }
                    }
                },
                userEmail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空 '
                        },
                        emailAddress: {
                            message: '这不是一个正确的邮箱'
                        }
                    }
                },
                userPhone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^1\d{10}$/,
                            message: '手机号格式错误'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'confirmPassword',
                            message: '两次填写的密码不一致，请重新确认'
                        },
                        different: {
                            field: 'username',
                            message: '请不要使用用户名作为密码'
                        }
                    }
                },
                confirmPassword: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'password',
                            message: '两次填写的密码不一致'
                        },
                        different: {
                            field: 'username',
                            message: '请不要使用用户名作为密码'
                        }
                    }
                },
            }
        });

        // Validate the form manually
        $('#validateBtn').click(function() {
            $('#defaultForm').bootstrapValidator('validate');
        });

        $('#resetBtn').click(function() {
            $('#addUserForm').data('bootstrapValidator').resetForm(true);
        });
    });

    $(document).ready(function() {
        $('#editUserForm').bootstrapValidator({
            message: '请填写正确的用户信息',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空 '
                        },
                        different: {
                            field: 'password,confirmPassword',
                            message: 'The username and password cannot be the same as each other'
                        }
                    }
                },
                userEmail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空 '
                        },
                        emailAddress: {
                            message: '这不是一个正确的邮箱'
                        }
                    }
                },
                userPhone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^1\d{10}$/,
                            message: '手机号格式错误'
                        }
                    }
                },
                password: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'confirmPassword',
                            message: '两次填写的密码不一致，请重新确认'
                        },
                        different: {
                            field: 'username',
                            message: '请不要使用用户名作为密码'
                        }
                    }
                },
                confirmPassword: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {
                            field: 'password',
                            message: '两次填写的密码不一致'
                        },
                        different: {
                            field: 'username',
                            message: '请不要使用用户名作为密码'
                        }
                    }
                },
            }
        });

        // Validate the form manually
        $('#validateBtn').click(function() {
            $('#defaultForm').bootstrapValidator('validate');
        });

        $('#resetBtn').click(function() {
            $('#editUserForm').data('bootstrapValidator').resetForm(true);
        });
    });

    /**
     * 关闭模态框之后对模态框进行重置
     */
    $(document).ready(function() {
        $('#addUserModal').on('hidden.bs.modal', function () {
            $('#addUserForm').data('bootstrapValidator').resetForm(true);
        });

        $('#alterUserModal').on('hidden.bs.modal', function () {
            $('#editUserForm').data('bootstrapValidator').resetForm(true);
        });

        $('#allocationRoleModal').on('hidden.bs.modal', function () {
            $("#roleList").empty();
        });


    });


    /**
     * 加载部门
     */
    $(document).ready(function() {
            $.ajax({
                url: '../dept/getAllDept',
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        var depts = data.depts;
                        $.each(depts, function (i, item) {
                            <!-- 向商品详情表中进行数据注入 -->
                            $("#deptId").append("<option value='" + item.deptId + "'>" + item.deptName + "</option>");
                            $("#deptId2").append("<option value='" + item.deptId + "'>" + item.deptName + "</option>");
                            i++;
                        });
                    } else {
                        layer.alert(data.msg, {icon: 5, offset: '0px'});
                    }
                }
            });
    });



    /**
     * 打开修改窗口
     */
    function alterUser(){
        var $table = $('#users');
        var user= $table.bootstrapTable('getSelections');

        var userId=user[0].userId;
        var username=user[0].username;
        var userEmail=user[0].userEmail;
        var userPhone=user[0].userPhone;
        var userStatusStr=user[0].userStatus;
        var deptId=user[0].deptId;

        $("#alterUserModal").modal('show');

        $("#userId").val(userId);
        $("#username").val(username);
        $("#userEmail").val(userEmail);
        $("#userPhone").val(userPhone);

        $(" select option[value='"+deptId+"']").attr("selected","selected");

        var userStatus;
        if (userStatusStr=="正常"){
            $("#userStatus1").attr("checked","checked");
        }else if (userStatusStr=="禁用"){
            $("#userStatus2").attr("checked","checked");
        }else if (userStatusStr=="锁定"){
            $("#userStatus3").attr("checked","checked");
        }
    }

    /**
     * 打开删除窗口
     */
    function removeUser() {
        var $table = $('#users');
        var user= $table.bootstrapTable('getSelections');

        if (JSON.stringify(user)=="[]"){
            layer.alert("请先选择要进行修改的记录", {icon: 5, offset: '0px'});
        }else {
            $("#removeUserModal").modal('show');
        }
    }

    function remove(){
        var userId;
        var tbodyObj = document.getElementById('users');
        $("table :checkbox").each(function(key,value){
            if($(value).prop('checked')){
                userId=tbodyObj.rows[key+1].cells[1].innerHTML;
                $.ajax({
                    url:'../user/removeUser',
                    dataType:'json',
                    type:'post',
                    data:{userId:userId},
                    success:function(data){
                        if (data.code == 0) {
                            layer.msg(data.msg, {icon: 1, time: 1000, offset: '0px'});
                            $("#removeUserModal").modal('hide');
                            window.location.reload();
                        } else {
                            layer.alert(data.msg, {icon: 5, offset: '0px'});
                        }
                    }
                });
            }
        });
    }

    $(document).ready(function() {
        $('#btn-assign-role').click(function () {
            var $table = $('#users');
            var user= $table.bootstrapTable('getSelections');
            var userId=user[0].userId;
            $.ajax({
                url: '../user/getRoleList',
                data:{userId:userId},
                dataType: 'json',
                type: 'post',
                success: function (data) {
                    console.log(data);
                    if (data.code == 0) {
                        var userRoleList = data.userRoleList;
                        var  targetUserId=data.userId;
                        $("#targetUserId").val(targetUserId);
                        $.each(userRoleList, function (i, item) {
                            if (item.userId!=null){
                                <!-- 向商品详情表中进行数据注入 -->
                                $("#roleList").append("<div class='chexkbox' style='padding-left: 30px;'></div>");
                                $("#roleList").append("<label>");
                                $("#roleList").append("<input type='checkbox' name='roleId' value='"+item.roleId+"' checked=checked >"+item.roleName+"");

                                $("#roleList").append("</label>");
                                $("#roleList").append("</div>");
                            }else {
                                <!-- 向商品详情表中进行数据注入 -->
                                $("#roleList").append("<div class='chexkbox' style='padding-left: 30px;'></div>");
                                $("#roleList").append("<label>");
                                $("#roleList").append("<input type='checkbox' name='roleId' value='"+item.roleId+"'>"+item.roleName+"");

                                $("#roleList").append("</label>");
                                $("#roleList").append("</div>");
                            }
                            i++;
                        });
                    } else {
                        layer.alert(data.msg, {icon: 5, offset: '0px'});
                    }
                }
            });
        });

    });



    // 分配角色
    $('#btn-assign-role').click(function () {
        var rows = $('#users').bootstrapTable('getSelections');
        if (rows.length == 0) {
            window.parent.layer.msg("请选择数据行!", {icon: 2, time: 1000, offset: 't'})
        }else{
            $("#allocationRoleModal").modal('show');
        }
    });


    $('#btn-submit').click(function () {
        $.ajax({
            url: '/user/assignRole',
            type: 'post',
            data: $("#allocationRole").serialize(),
            dataType: 'json',
            success: function (response) {
                if (response.code == 0) {
                    window.parent.layer.msg(response.msg, {icon: 1, time: 1000, offset: '0px'});
                    window.location.href = '/user/showAllUser';
                } else {
                    window.parent.layer.alert(response.msg, {icon: 5, offset: '0px'});
                }
            }
        })
    });

</script>
</body>
</html>
