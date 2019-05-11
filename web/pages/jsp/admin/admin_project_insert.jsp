<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"ProjectActionAdmin!insert.action";
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
    <script type="text/javascript" src="js/admin_project_insert.js"></script>
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
                    <strong>添加新的项目信息</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_document_insert" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <input type="hidden" id="user.name" name="user.name">
                            <div class="form-group" id="project.titleDiv">
                                <label class="col-md-4 control-label" for="project.title">项目名称:</label>
                                <div class="col-md-4">
                                    <input type="text" id="project.title" name="project.title" class="form-control" placeholder="请输入项目名称"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="project.titleSpan"></div>
                            </div>
                            <div class="form-group" id="user.useridDiv">
                                <label class="col-md-4 control-label" for="user.userid">项目经理:</label>
                                <div class="col-md-4">
                                    <c:if test="${all != null}">
                                        <select id="user.userid" class="form-control" name="user.userid">
                                            <c:forEach items="${all}" var="user">
                                                <option value="${user.userid}">${user.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                                <div class="col-md-4" id="user.useridSpan"></div>
                            </div>
                            <div class="form-group" id="project.noteDiv">
                                <label class="col-md-4 control-label" for="project.note">项目描述:</label>
                                <div class="col-md-4">
                                    <textarea name="project.note" id="project.note" rows="5" class="form-control"></textarea>
                                </div>
                                <div class="col-md-4" id="project.noteSpan"></div>
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
