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
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
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
                    <strong>${all.size() > 0?all.get(0).project.title:"xx"}项目任务列表</strong>
                </div>
                <div class="panel-body">
                    <!-- <jsp:include page="/pages/search.jsp"></jsp:include> -->
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><strong>任务标题</strong> </td>
                            <td class="text-center"><strong>创建者</strong> </td>
                            <td class="text-center"><strong>实施者</strong> </td>
                            <td class="text-center"><strong>状态</strong></td>
                            <td class="text-center"><strong>优先级</strong> </td>
                            <td class="text-center"><strong>截止日期</strong> </td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><a style='cursor:pointer;' id="taskInfo-${a.tid}"><span id="title-${a.tid}">${a.title}</span></a></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.userByCreator.userid}"><span id="creator-${a.tid}">${a.userByCreator.name}</span></a></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.userByReceiver.userid}"><span id="receiver-${a.tid}">${a.userByReceiver.name}</span></a></td>
                                    <td class="text-center"><span id="status-${a.tid}">
                                        <c:if test="${a.status == 0}">未开始</c:if>
                                        <c:if test="${a.status == 1}">进行中</c:if>
                                        <c:if test="${a.status == 2}">取消</c:if>
                                        <c:if test="${a.status == 3}">已完成</c:if>
                                    </span></td>
                                    <td class="text-center"><span id="priority-${a.tid}">
                                        <c:if test="${a.priority == 1}">★☆☆</c:if>
                                        <c:if test="${a.priority == 2}">★★☆</c:if>
                                        <c:if test="${a.priority == 3}">★★★</c:if>
                                    </span></td>
                                    <td class="text-center"><span id="expiredate-${a.tid}">${a.expiredate}</span></td>

                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <a class="btn btn-success glyphicon glyphicon-chevron-left"href="${backUrl}">项目列表</a>
                    <div id="pageDiv" class="text-right">
                        <c:if test="${allRecorders != 0}">
                            <ul class="pagination pagination-sm" id="pagecontrol">
                                <c:if test="${currentPage == 1}">
                                    <li><a style='cursor:pointer;'>首页</a></li>
                                    <li><a style='cursor:pointer;'>上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage != 1}">
                                    <li><a style='cursor:pointer;' href="TaskActionAdmin!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="TaskActionAdmin!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="TaskActionAdmin!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="TaskActionAdmin!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
<jsp:include page="/pages/include_task.jsp"></jsp:include>
</body>
</html>
