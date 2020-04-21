<%--
  Created by IntelliJ IDEA.
  User: 陈翔
  Date: 2020-02-21
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html>
<head>
    <title>手机进销存管理系统首页</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!--external css-->
    <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/gritter/css/jquery.gritter.css" />
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet">


</head>
<body>
    <!--内容区域-->
            <div class="row">
<%--                <div class="white-panel" style="height: 500px;">--%>
<%--                    <img src="${pageContext.request.contextPath}/img/main.jpg" alt="图片加载失败">--%>
<%--                </div>--%>
                
                
                <div class="white-panel" style="height: 500px;">
                    <!--  ALERTS EXAMPLES -->
                    <div class="showback" >
                        <div class="alert alert-success">
                            <h4><shiro:principal property="username"></shiro:principal>
                            ，欢迎使用手机进销存管理系统</h4>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6 col-sm-12">
                        <div class="col-lg-6">
                            <blockquote style="padding-left: 0px;" class="title">最新公告 </blockquote>
                            <table >
                                <tbody class="hot_news"></tbody>
                            </table>
                        </div>
                    </div>
                </div>


            </div>
            <!-- /row -->
    <!--内容区域-->


<!-- js placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery.scrollTo.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/jquery.nicescroll.js" type="text/javascript"></script>

<!--common script for all pages-->
<script src="${pageContext.request.contextPath}/lib/common-scripts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/gritter-conf.js"></script>


</body>
</html>
