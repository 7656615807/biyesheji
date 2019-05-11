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
    <script type="text/javascript" src="js/document_update.js"></script>

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
                    <strong>文档列表</strong>
                </div>
                <div class="panel-body">
                    <jsp:include page="/pages/search.jsp"></jsp:include>
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><input type="checkbox" id="selall" name="selall"> </td>
                            <td class="text-center"><strong>标题</strong> </td>
                            <td class="text-center"><strong>发布者</strong> </td>
                            <td class="text-center"><strong>发布日期</strong> </td>
                            <td class="text-center"><strong>文档类型</strong></td>
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><input type="checkbox" name="nid" id="nid" value="${a.did}" ></td>
                                    <td class="text-center"><span id="title-${a.did}">${a.title}</span></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.user.userid}"><span id="name-${a.did}">${a.user.name}</span></a></td>
                                    <td class="text-center"><span id="pubdate-${a.pubdate}">${a.pubdate}</span></td>
                                    <td class="text-center"><span id="doctype-${a.did}">${a.doctype.title}</span></td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary" id="adminBtn-${a.did}">
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑文档
                                            </button>
                                            <c:if test="${not empty a.file}">
                                            <a id="updateBtn-${a.did}" class="btn btn-info" href="/upload/document/${a.file}" download="${a.file}">
                                                <span class="glyphicon glyphicon-download"></span>&nbsp;下载
                                            </a>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <button class="btn btn-danger" id="delBtn">
                        <span class="glyphicon glyphicon-trash"></span>&nbsp;
                        删除文档
                    </button>
                    <div id="pageDiv" class="text-right">
                        <c:if test="${allRecorders != 0}">
                            <ul class="pagination pagination-sm" id="pagecontrol">
                                <c:if test="${currentPage == 1}">
                                    <li><a style='cursor:pointer;'>首页</a></li>
                                    <li><a style='cursor:pointer;'>上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage != 1}">
                                    <li><a style='cursor:pointer;' href="DocumentActionAdmin!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="DocumentActionAdmin!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="DocumentActionAdmin!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="DocumentActionAdmin!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
                                </c:if>
                                <p>
                                    当前第${currentPage}页       总共${allRecorders}页
                                </p>
                            </ul>
                        </c:if>
                    </div>

                </div>
                <div class="panel-footer">
                    <jsp:include page="/pages/include_foot.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal" id="documentUpdate">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">编辑文档</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  action="<%=basePath%>DocumentActionAdmin!update.action" id="myform_document_update" method="post" enctype="multipart/form-data">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="document.did" name="document.did">
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
                                <select id="doctype.dtid" class="form-control" name="doctype.dtid">
                                </select>
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
                        <input type="hidden" id="oldFile" name="oldFile">
                        <div class="form-group" id="fileDiv">
                            <label class="col-md-4 control-label" for="file">附件:</label>
                            <div class="col-md-4">
                                <input id="file" name="file" type="file">
                            </div>
                            <div class="col-md-4" id="fileSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="submit" class="btn btn-md btn-primary" >修改</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-success" data-dismiss="modal">关闭界面</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
