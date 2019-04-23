<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"RoleActionAdmin!insert.action";
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
    <script type="text/javascript" src="js/role_insert.js"></script>
</head>

<body>
<div class="container">
    <div id="headDiv" class="row">
        <div class="col-md-12 col-xs-12">		<!-- 定义导航条 -->
            <jsp:include page="/pages/include_menu_emp.jsp"></jsp:include>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel panel-heading">
                    <strong>角色增加</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_role_insert" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="role.titleDiv">
                                <label class="col-md-4 control-label" for="role.title">角色名称:</label>
                                <div class="col-md-4">
                                    <input type="text" id="role.title" name="role.title" class="form-control" placeholder="角色名称"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="role.titleSpan"></div>
                            </div>
                            <div class="form-group" id="role.noteDiv">
                                <label class="col-md-4 control-label" for="role.note">角色描述:</label>
                                <div class="col-md-4">
                                    <input type="text" id="role.note" name="role.note" class="form-control" placeholder="角色描述" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="role.noteSpan"></div>
                            </div>
                            <div class="form-group" id="gidsDiv">
                                <label class="col-md-4 control-label" for="gids">包含权限:</label>
                                <div class="col-md-4">
                                    <c:if test="${all != null}">
                                        <c:forEach items="${all}" var="gup">
                                            <div class="checkbox col-md-4">
                                                <label><input type="checkbox" id="gids" name="gids" value="${gup.gid}">${gup.title}</label>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <div class="col-md-4" id="gidsSpan"></div>
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
