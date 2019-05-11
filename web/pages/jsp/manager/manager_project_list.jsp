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
    <script type="text/javascript" src="js/manager/manager_project_update.js"></script>

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
                    <strong>项目列表</strong>
                </div>
                <div class="panel-body">
                    <jsp:include page="/pages/search.jsp"></jsp:include>
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><strong>项目名称</strong> </td>
                            <td class="text-center"><strong>发布者</strong> </td>
                            <td class="text-center"><strong>项目负责人</strong> </td>
                            <td class="text-center"><strong>发布日期</strong></td>
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><a style='cursor:pointer;' id="project-${a.proid}"><span id="title-${a.proid}">${a.title}</span></a></td>
                                    <td class="text-center"><a href="/UserActionManager!show.action?user.userid=${a.userByCreid.userid}"><span id="creidName-${a.proid}">${a.userByCreid.name}</span></a></td>
                                    <td class="text-center"><a href="/UserActionManager!show.action?user.userid=${a.userByMgr.userid}" id="mgrHref-${a.proid}"><span id="mgrName-${a.proid}">${a.userByMgr.name}</span></a></td>
                                    <td class="text-center"><span id="pubdate-${a.pubdate}">${a.pubdate}</span></td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <input type="hidden" id="backUrl" name="backUrl" value="${wholeUrl}">
                                            <button type="button" class="btn btn-primary" id="adminBtn-${a.proid}" <c:if test="${a.userByMgr.userid != manager.userid}"> disabled</c:if> >
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;添加任务
                                            </button>

                                            <button type="button" class="btn btn-info" id="taskList-${a.proid}">
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;任务列表
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <div id="pageDiv" class="text-right">
                        <c:if test="${allRecorders != 0}">
                            <ul class="pagination pagination-sm" id="pagecontrol">
                                <c:if test="${currentPage == 1}">
                                    <li><a style='cursor:pointer;'>首页</a></li>
                                    <li><a style='cursor:pointer;'>上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage != 1}">
                                    <li><a style='cursor:pointer;' href="ProjectActionManager!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="ProjectActionManager!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="ProjectActionManager!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="ProjectActionManager!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
<jsp:include page="/pages/include_project.jsp"></jsp:include>

</body>
</html>
