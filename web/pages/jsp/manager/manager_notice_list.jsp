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
    <script type="text/javascript" src="js/manager/manager_notice_update.js"></script>

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
                    <strong>公告列表</strong>
                </div>
                <div class="panel-body">
                    <jsp:include page="/pages/search.jsp"></jsp:include>
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><strong>标题</strong> </td>
                            <td class="text-center"><strong>发布者</strong> </td>
                            <td class="text-center"><strong>发布日期</strong> </td>
                            <td class="text-center"><strong>阅读量</strong></td>
                            <td class="text-center"><strong>阅读状态</strong> </td>
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <input type="hidden" name="nid" id="nid" value="${a.snid}" >
                                    <td class="text-center"><span id="title-${a.snid}">${a.title}</span></td>
                                    <td class="text-center"><a href="/UserActionManager!show.action?user.userid=${a.user.userid}"><span id="name-${a.snid}">${a.user.name}</span></a></td>
                                    <td class="text-center"><span id="pubdate-${a.snid}">${a.pubdate}</span></td>
                                    <td class="text-center"><span id="rnum-${a.snid}">${a.rnum}</span></td>
                                    <td class="text-center">
                                        <span id="status-${a.snid}">
                                            <c:choose>
                                                <c:when test="${! empty unread.get(a.snid) && ! unread.get(a.snid)}">
                                                    未读
                                                </c:when>
                                                <c:otherwise>
                                                    已读
                                                </c:otherwise>
                                            </c:choose>
                                        </span></td>
                                    </td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button id="adminBtn-${a.snid}" class="btn btn-info">&nbsp;查看公告</button>
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
                                    <li><a style='cursor:pointer;' href="NoticeActionManager!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="NoticeActionManager!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="NoticeActionManager!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="NoticeActionManager!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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

<div class="modal" id="updateNoticeInfo">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">查看公告信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_admin_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="notice.snid" name="notice.snid">
                        <div class="form-group" id="notice.titleDiv">
                            <label class="col-md-4 control-label" for="notice.title">公告标题:</label>
                            <div class="col-md-4">
                                <input type="text" id="notice.title" name="notice.title" class="form-control" readonly="readonly">
                            </div>
                            <div class="col-md-4" id="notice.titleSpan"></div>
                        </div>
                        <div class="form-group" id="notice.contentDiv">
                            <label class="col-md-4 control-label" for="notice.content">公告内容:</label>
                            <div class="col-md-4">
                                <textarea id="notice.content" name="notice.content" class="form-control" readonly="readonly"></textarea>
                            </div>
                            <div class="col-md-4" id="notice.contentSpan"></div>
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
