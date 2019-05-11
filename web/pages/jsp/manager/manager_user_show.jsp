<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>Title</title>
    <meta charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="js/new-list.js" type="text/javascript"></script>

</head>
<body>
<div class="manback">
    <div class="container">
        <div id="headDiv" class="row">
            <div class="col-md-12 col-xs-12">		<!-- 定义导航条 -->
                <jsp:include page="/pages/include_menu_manager.jsp"></jsp:include>
            </div>
        </div>
        <div id="contenDiv" class="row">
            <div class="col-md-12 col-xs-12">
                <div class="panel panel-success">
                    <div class="panel panel-heading">
                        <strong>查看用户信息</strong>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover table-striped" id="newsTable">
                            <tr>
                                <td colspan="3"><span class="h1"> <span class="glyphicon glyphicon-user">&nbsp;查看“${user.name}”信息</span></span></td>
                            </tr>
                            <tr>
                                <td rowspan="10" style="width: 130px;">
                                    <img src="/upload/user/${user.photo}" class="img-rounded" width="128px" height="128px">
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 15%"><strong>用户id：</strong></td>
                                <td>${user.userid}</td>
                            </tr>
                            <tr>
                                <td><strong>真实姓名：</strong></td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td><strong>联系电话：</strong></td>
                                <td>${user.phone}</td>
                            </tr>
                            <tr>
                                <td><strong>邮箱：</strong></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td  style="width: 240px;">
                                    <strong>用户级别：</strong>
                                </td>
                                <td><c:if test="${user.level==0}">
                                    超级管理员
                                </c:if>
                                    <c:if test="${user.level==1}">
                                        管理员
                                    </c:if>
                                    <c:if test="${user.level>1}">
                                        雇员
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                        <div class="panel-footer">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
