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
    <script type="text/javascript" src="js/manager/task_list.js"></script>
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
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
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><a style='cursor:pointer;' id="taskInfo-${a.tid}"><span id="title-${a.tid}">${a.title}</span></a></td>
                                    <td class="text-center"><a href="/UserActionManager!show.action?user.userid=${a.userByCreator.userid}"><span id="creator-${a.tid}">${a.userByCreator.name}</span></a></td>
                                    <td class="text-center"><a href="/UserActionManager!show.action?user.userid=${a.userByReceiver.userid}"><span id="receiver-${a.tid}">${a.userByReceiver.name}</span></a></td>
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

                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" <c:if test="${a.userByCreator.userid != manager.userid}"> disabled</c:if> >
                                                <span class="glyphicon glyphicon-list"></span>&nbsp;
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a id="adminBtn-${a.tid}" onmouseover="this.style.cursor='hand'" >
                                                    <span class="glyphicon glyphicon-edit"></span>&nbsp;修改</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a id="cancleBtn-${a.tid}" onmouseover="this.style.cursor='hand'" >
                                                    <span class="glyphicon glyphicon-remove"></span>&nbsp;取消</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </td>
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
                                    <li><a style='cursor:pointer;' href="TaskActionManager!list.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="TaskActionManager!list.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="TaskActionManager!list.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="TaskActionManager!list.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
<div class="modal" id="adminCancel">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">取消任务</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_role_insert" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <div class="form-group" id="cnoteDiv">
                            <label class="col-md-4 control-label" for="cnote">取消原因:</label>
                            <div class="col-md-4">
                                <textarea id="cnote" name="cnote" class="form-control" autocomplete="off" rows="5"></textarea>
                            </div>
                            <div class="col-md-4" id="cnoteSpan"></div>
                        </div>
                        <input type="hidden" id="cancle.tid" name="cancle.tid" class="form-control">
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="canclesubmitBtn" class="btn btn-md btn-primary">提交</button>
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
<div class="modal" id="updateAdminInfo">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改任务信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_admin_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="task.tid" name="task.tid" class="form-control">
                        <div class="form-group" id="task.projectDiv">
                            <label class="col-md-4 control-label" for="task.note">所属项目:</label>
                            <div class="col-md-4">
                                <input type="text" name="task.project" id="task.project" class="form-control" readonly="readonly"></textarea>
                            </div>
                            <div class="col-md-4" id="task.projectSpan"></div>
                        </div>
                        <div class="form-group" id="task.titleDiv">
                            <label class="col-md-4 control-label" for="task.note">任务标题:</label>
                            <div class="col-md-4">
                                <input type="text" name="task.title" id="task.title"class="form-control" readonly="readonly"></input>
                            </div>
                            <div class="col-md-4" id="task.titleSpan"></div>
                        </div>
                        <div class="form-group" id="task.noteDiv">
                            <label class="col-md-4 control-label" for="task.note">任务内容:</label>
                            <div class="col-md-4">
                                <textarea name="task.note" id="task.note" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="col-md-4" id="task.noteSpan"></div>
                        </div>
                        <div class="form-group" id="task.expiredateDiv">
                            <label class="col-md-4 control-label" for="task.expiredate">截止日期：</label>
                            <div class="col-md-4">
                                <input type="text" id="task.expiredate" name="task.expiredate" class="form-control" placeholder="请选择截止日期" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="task.expiredateSpan"></div>
                        </div>
                        <div class="form-group" id="task.priorityDiv">
                            <label class="col-md-4 control-label" for="task.priority">优先级:</label>
                            <div class="col-md-4">
                                <select id="task.priority" class="form-control" name="task.priority">

                                </select>
                            </div>
                            <div class="col-md-4" id="task.prioritySpan"></div>
                        </div>
                        <div class="form-group" id="task.estimateDiv">
                            <label class="col-md-4 control-label" for="task.estimate">预计工时:</label>
                            <div class="col-md-4">
                                <input type="number" id="task.estimate" name="task.estimate" class="form-control" min="0" max="999" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="task.estimateSpan"></div>
                        </div>
                        <div class="form-group" id="task.statusDiv">
                            <label class="col-md-4 control-label" for="task.status">状态:</label>
                            <div class="col-md-4">
                                <select id="task.status" class="form-control" name="task.status">

                                </select>
                            </div>
                            <div class="col-md-4" id="task.statusSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="updatesubmitBtn" class="btn btn-md btn-primary">提交</button>
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
<jsp:include page="/pages/include_task.jsp"></jsp:include>
</body>
</html>
