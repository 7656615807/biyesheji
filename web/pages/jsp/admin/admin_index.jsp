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
                <jsp:include page="/pages/include_menu_emp.jsp"></jsp:include>
            </div>
        </div>
        <div id="contenDiv" class="row">
            <div class="col-md-12 col-xs-12">
                <div class="panel panel-success">
                    <div class="panel panel-heading">
                        <strong>超级管理员页面</strong>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover table-striped" id="newsTable">
                            <tr>
                                <td colspan="3">欢迎<strong>${admin.name}</strong>光临</td>
                            </tr>
                            <tr>
                                <td rowspan="4" style="width: 160px;">
                                    <img src="/upload/user/${admin.photo}" class="img-rounded" width="140px">
                                </td>
                            </tr>
                            <tr>
                                <td  style="width: 240px;">
                                    <strong>雇员级别：</strong>
                                </td>
                                <td><c:if test="${admin.level==0}">
                                    超级管理员
                                </c:if>
                                    <c:if test="${admin.level==1}">
                                    管理员
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td >
                                    <strong>上次登录日期</strong>
                                </td>
                                <td>
                                    ${admin.lastlogin}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <a href="/pages/jsp/admin/admin_password_edit.jsp" class="btn btn-info">修改密码</a>
                                    <a href="AdminUpdateAction!updatePre.action" class="btn btn-info">完善个人资料</a>
                                    <a href="UserLogout!logout.action" class="btn btn-danger">登录注销</a>
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
