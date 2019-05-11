<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"ManagerUpdateAction!update.action";
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
    <!--<script type="text/javascript" src="js/form-rule.js"></script>-->
</head>

<body>
<div class="container">
    <div id="headDiv" class="row">
        <div class="col-md-12 col-xs-12">		<!-- 定义导航条 -->
            <jsp:include page="/pages/include_menu_manager.jsp"></jsp:include>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel panel-heading">
                    <strong>个人信息修改</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_order" method="post" enctype="multipart/form-data">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="useridDiv">
                                <label class="col-md-4 control-label" for="name">登录名称:</label>
                                <div class="col-md-4">
                                    <input type="text" id="userid" name="user.userid" class="form-control" readonly="readonly" value="${user.userid}">
                                </div>
                                <div class="col-md-4" id="useridSpan"></div>
                            </div>
                            <div class="form-group" id="nameDiv">
                                <label class="col-md-4 control-label" for="name">真实姓名:</label>
                                <div class="col-md-4">
                                    <input type="text" id="name" name="user.name" class="form-control" value="${user.name}" autocomplete="off">
                                </div>
                                <div class="col-md-4" id="nameSpan"></div>
                            </div>
                            <div class="form-group" id="phoneDiv">
                                <label class="col-md-4 control-label" for="phone">联系电话:</label>
                                <div class="col-md-4">
                                    <input type="text" id="phone" name="user.phone" class="form-control" value="${user.phone}" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="phoneSpan"></div>
                            </div>
                            <div class="form-group" id="emailDiv">
                                <label class="col-md-4 control-label" for="email">联系邮箱:</label>
                                <div class="col-md-4">
                                    <input class="form-control" name="user.email" id="email" type="text" value="${user.email}" autocomplete="off" /><s></s>
                                </div>
                                <div class="col-md-4" id="emailSpan"></div>
                            </div>
                            <div class="form-group" id="photodDiv">
                                <label class="col-md-4 control-label" for="photo">个人照片:</label>
                                <div class="col-md-4">
                                    <input class="form-control" name="photo" id="photo" type="file" autocomplete="off" /><s></s>
                                </div>
                                <div class="col-md-4" id="photoSpan"></div>
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
