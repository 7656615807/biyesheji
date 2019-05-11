<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"AdminActionAdmin!insert.action";
%>
<html>

<head>
    <base href="<%=basePath %>"/>
    <title>Title</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./styles.css">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/admin_insert.js"></script>
</head>

<body>
<div class="container">
    <div id="headDiv" class="row">
        <div class="col-md-12 col-xs-12">		<!-- 定义导航条 -->
            <jsp:include page="/pages/include_menu_admin.jsp"></jsp:include>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel panel-heading">
                    <strong>添加系统管理员</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_admin_insert" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="user.useridDiv">
                                <label class="col-md-4 control-label" for="user.userid">登录名称:</label>
                                <div class="col-md-4">
                                    <input type="text" id="user.userid" name="user.userid" class="form-control" placeholder="登录名称"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="user.useridSpan"></div>
                            </div>
                            <div class="form-group" id="user.passwordDiv">
                                <label class="col-md-4 control-label" for="user.password">登录密码:</label>
                                <div class="col-md-4">
                                    <input type="text" id="user.password" name="user.password" class="form-control" placeholder="登录密码" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="user.passwordSpan"></div>
                            </div>
                            <div class="form-group" id="user.nameDiv">
                                <label class="col-md-4 control-label" for="user.name">真实姓名:</label>
                                <div class="col-md-4">
                                    <input type="text" id="user.name" name="user.name" class="form-control" placeholder="真实姓名" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="user.nameSpan"></div>
                            </div>
                            <div class="form-group" id="user.phoneDiv">
                                <label class="col-md-4 control-label" for="user.phone">联系电话:</label>
                                <div class="col-md-4">
                                    <input type="text" id="user.phone" name="user.phone" class="form-control" placeholder="联系电话" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="user.phoneSpan"></div>
                            </div>
                            <div class="form-group" id="user.emailDiv">
                                <label class="col-md-4 control-label" for="user.email">联系邮箱:</label>
                                <div class="col-md-4">
                                    <input type="text" id="user.email" name="user.email" class="form-control" placeholder="联系邮箱" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="user.emailSpan"></div>
                            </div>
                            <div class="form-group" id="role.ridDiv">
                                <label class="col-md-4 control-label" for="role.rid">包含权限:</label>
                                <div class="col-md-4">
                                    <select id="role.rid" class="form-control" name="role.rid">
                                        <c:forEach items="${all}" var="role">
                                            <option value="${role.rid}">${role.title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4" id="role.ridSpan"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-4 col-md-offset-4">
                                    <button type="submit" class="btn btn-md btn-primary">提交</button>
                                    <button type="reset" class="btn btn-md btn-primary">重置</button>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
                <div class="panel-footer">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
