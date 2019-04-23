<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"NoticeActionAdmin!insert.action";
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
    <script type="text/javascript" src="js/admin_notice_insert.js"></script>
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
                    <strong>发布系统公告</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_notive_insert" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="notice.titleDiv">
                                <label class="col-md-4 control-label" for="notice.title">公告标题:</label>
                                <div class="col-md-4">
                                    <input type="text" id="notice.title" name="notice.title" class="form-control" placeholder="公告标题"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="notice.titleSpan"></div>
                            </div>
                            <div class="form-group" id="notice.contentDiv">
                                <label class="col-md-4 control-label" for="notice.content">公告内容:</label>
                                <div class="col-md-4">
                                    <textarea id="notice.content" name="notice.content" class="form-control"></textarea>
                                </div>
                                <div class="col-md-4" id="notice.contentSpan"></div>
                            </div>

                            <div class="form-group" id="notice.levelDiv">
                                <label class="col-md-4 control-label" for="notice.level">包含权限:</label>
                                <div class="col-md-4">
                                    <select id="notice.level" class="form-control" name="notice.level">
                                        <option value="1">所有员工</option>
                                        <option value="0">项目经理</option>
                                    </select>
                                </div>
                                <div class="col-md-4" id="notice.levelSpan"></div>
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
