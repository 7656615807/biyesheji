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
    <script type="text/javascript" src="js/admin_notice_update.js"></script>

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
                    <strong>公告列表</strong>
                </div>
                <div class="panel-body">
                    <jsp:include page="/pages/search.jsp"></jsp:include>
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><input type="checkbox" id="selall" name="selall"> </td>
                            <td class="text-center"><strong>标题</strong> </td>
                            <td class="text-center"><strong>发布者</strong> </td>
                            <td class="text-center"><strong>发布日期</strong> </td>
                            <td class="text-center"><strong>阅读量</strong></td>
                            <td class="text-center"><strong>阅读级别</strong> </td>
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><input type="checkbox" name="nid" id="nid" value="${a.snid}" ></td>
                                    <td class="text-center"><span id="title-${a.snid}">${a.title}</span></td>
                                    <td class="text-center"><a href="/AdminActionAdmin!show.action?user.userid=${a.user.userid}"><span id="name-${a.snid}">${a.user.name}</span></a></td>

                                    <td class="text-center"><span id="pubdate-${a.snid}">${a.pubdate}</span></td>
                                    <td class="text-center"><span id="rnum-${a.snid}">${a.rnum}</span></td>
                                    <td class="text-center">
                                        <select id="level-${a.snid}" class="form-control" name="level-${a.snid}">
                                            <option value="1" ${a.level == 1? "selected" : ""}>所有员工</option>
                                            <option value="0" ${a.level == 0? "selected" : ""}>项目经理</option>
                                        </select>
                                    </td>

                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary" id="updateBtn-${a.snid}">
                                                <span class="glyphicon glyphicon-"></span>&nbsp;修改级别
                                            </button>
                                            <button id="adminBtn-${a.snid}" class="btn btn-info">&nbsp;编辑公告</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <button class="btn btn-danger" id="delBtn">
                        删除公告
                    </button>
                    <a class="btn btn-success"href="/NoticeActionAdmin!insertPre.action"> 增加公告</a>

                    <div id="pageDiv" class="text-right">
                        <c:if test="${allRecorders != 0}">
                            <ul class="pagination pagination-sm" id="pagecontrol">
                                <c:if test="${currentPage == 1}">
                                    <li><a style='cursor:pointer;'>首页</a></li>
                                    <li><a style='cursor:pointer;'>上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage != 1}">
                                    <li><a style='cursor:pointer;' href="NoticeActionAdmin!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="NoticeActionAdmin!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="NoticeActionAdmin!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="NoticeActionAdmin!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
                <h4 class="modal-title">修改公告信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_admin_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="notice.snid" name="notice.snid">
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
                                </select>
                            </div>
                            <div class="col-md-4" id="notice.levelSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="submitBtn" class="btn btn-md btn-primary">提交</button>
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
