<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"AdminUpdateAction!updatePassword.action";
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
            <jsp:include page="/pages/include_menu_admin.jsp"></jsp:include>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel panel-heading">
                    <strong>密码修改</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_order" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="oldpasswordDiv">
                                <label class="col-md-4 control-label" for="oldpassword">原密码:</label>
                                <div class="col-md-4">
                                    <input type="password" id="oldpassword" name="oldpassword" class="form-control" placeholder="原密码"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="oldpasswordSpan"></div>
                            </div>
                            <div class="form-group" id="contentDiv">
                                <label class="col-md-4 control-label" for="content">新密码:</label>
                                <div class="col-md-4">
                                    <input type="password" id="content" name="content" class="form-control" placeholder="新密码" autocomplete="off"></textarea>
                                </div>
                                <div class="col-md-4" id="contentSpan"></div>
                            </div>
                            <div class="form-group" id="newpasswordDiv">
                                <label class="col-md-4 control-label" for="newpassword">确认密码:</label>
                                <div class="col-md-4">
                                    <input class="form-control" name="newpassword" id="newpassword" type="password" placeholder="确认密码" autocomplete="off" /><s></s>
                                </div>
                                <div class="col-md-4" id="newpasswordSpan"></div>
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
