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
    <script type="text/javascript" src="js/emp/emp_index.js"></script>

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
                        <strong>普通员工页面</strong>
                    </div>
                    <div class="panel-body">
                        <table class="table table-bordered table-hover table-striped" id="newsTable">
                            <tr>
                                <td colspan="3"><h4>欢迎<strong>${emp.name}</strong>光临&nbsp;&nbsp;&nbsp;&nbsp;
                                    未读公告数量(<a href="/NoticeActionEmp!list.action?cp=1" id="UnreadNum"></a>)&nbsp;&nbsp;&nbsp;&nbsp;
                                    未开始任务数量(<a href="/TaskActionEmp!listbyEmp.action?cp=1" id="UnStartNum"></a>)&nbsp;&nbsp;&nbsp;&nbsp;
                                    未完成任务数量(<a href="/TaskActionEmp!listbyEmp.action?cp=1" id="UnfinishNum"></a>)</h4></td>
                            </tr>
                            <tr>
                                <td rowspan="4" style="width: 160px;">
                                    <img src="/upload/user/${emp.photo}" class="img-rounded" width="140px">
                                </td>
                            </tr>
                            <tr>
                                <td  style="width: 240px;">
                                    <strong>雇员级别：</strong>
                                </td>
                                <td><c:if test="${emp.level==3}">
                                    普通员工
                                </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td >
                                    <strong>上次登录日期</strong>
                                </td>
                                <td>
                                    ${emp.lastlogin}
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <a href="/pages/jsp/emp/emp_password_edit.jsp" class="btn btn-info">修改密码</a>
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
