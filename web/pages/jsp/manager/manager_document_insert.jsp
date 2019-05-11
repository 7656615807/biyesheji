<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"DocumentActionManager!insert.action";
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
    <script type="text/javascript" src="js/document_insert.js"></script>
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
                    <strong>添加新的文档信息</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_document_insert" method="post" enctype="multipart/form-data">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <div class="form-group" id="document.titleDiv">
                                <label class="col-md-4 control-label" for="document.title">文档标题:</label>
                                <div class="col-md-4">
                                    <input type="text" id="document.title" name="document.title" class="form-control" placeholder="文档标题"autocomplete="off">
                                </div>
                                <div class="col-md-4" id="document.titleSpan"></div>
                            </div>
                            <div class="form-group" id="doctype.dtidDiv">
                                <label class="col-md-4 control-label" for="doctype.dtid">文档类型:</label>
                                <div class="col-md-4">
                                    <c:if test="${all != null}">
                                        <select id="doctype.dtid" class="form-control" name="doctype.dtid">
                                            <c:forEach items="${all}" var="doc">
                                                <option value="${doc.dtid}">${doc.title}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                                <div class="col-md-4" id="doctype.dtidSpan"></div>
                            </div>
                            <div class="form-group" id="document.contentDiv">
                                <label class="col-md-4 control-label" for="document.content">文档简介:</label>
                                <div class="col-md-4">
                                    <textarea name="document.content" id="document.content" rows="5" class="form-control"></textarea>
                                </div>
                                <div class="col-md-4" id="document.contentSpan"></div>
                            </div>
                            <div class="form-group" id="fileDiv">
                                <label class="col-md-4 control-label" for="file">文档简介:</label>
                                <div class="col-md-4">
                                    <input id="file" name="file" type="file">
                                </div>
                                <div class="col-md-4" id="fileSpan"></div>
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
