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

    <script >
        $(function() {
            $("button[id*='startBtn-']").each(function () {
                var tid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click", function () {
                    $.post("TaskActionEmp!updateStart.action", {"task.tid": tid},
                        function (data) {
                            operateAllert(data == "true", "操作成功！", "操作失败！");
                        }, "text");
                })
            });

            $("button[id*='finishBtn-']").each(function () {
                var tid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click", function () {
                    $("#task\\.tid").val(tid);
                    $("#finishTask").modal("toggle");
                })
            });

            $("#finishTaskBtn").on("click", function () {
                var rnote = $("#task\\.rnote").val()
                var tid = $("#task\\.tid").val()
                var expend = $("#task\\.expend").val()
                $.post("TaskActionEmp!updateFinish.action", {"task.tid": tid,"task.rnote": rnote,"task.expend": expend},
                    function (data) {
                        operateAllert(data == "true", "操作成功！", "操作失败！");
                        $("#finishTask").modal("toggle");
                    }, "text");
            })


        });
    </script>

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
                    <strong>我的任务列表</strong>
                </div>
                <div class="panel-body">
                    <!-- <jsp:include page="/pages/search.jsp"></jsp:include> -->
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><strong>项目标题</strong> </td>
                            <td class="text-center"><strong>任务标题</strong> </td>
                            <td class="text-center"><strong>创建者</strong> </td>
                            <td class="text-center"><strong>状态</strong></td>
                            <td class="text-center"><strong>优先级</strong> </td>
                            <td class="text-center"><strong>截止日期</strong> </td>
                            <td class="text-center"><strong>更新日期</strong> </td>
                            <td class="text-center"><strong>操作</strong> </td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><a style='cursor:pointer;' id="project-${a.project.proid}"><span id="title-${a.project.proid}">${a.project.title}</span></a></td>
                                    <td class="text-center"><a style='cursor:pointer;' id="taskInfo-${a.tid}"><span id="title-${a.tid}">${a.title}</span></a></td>
                                    <td class="text-center"><a href="/UserActionEmp!show.action?user.userid=${a.userByCreator.userid}"><span id="creator-${a.tid}">${a.userByCreator.name}</span></a></td>
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
                                    <td class="text-center"><span id="lastupdatedate-${a.tid}">${a.lastupdatedate}</span></td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary" id="startBtn-${a.tid}" <c:if test="${!(a.status==0)}"> disabled</c:if> >
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;开始任务
                                            </button>

                                            <button type="button" class="btn btn-info" id="finishBtn-${a.tid}"  <c:if test="${a.status==2 || a.status==3}"> disabled</c:if> >
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;完成任务
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
                                    <li><a style='cursor:pointer;' href="TaskActionEmp!listbyEmp.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="TaskActionEmp!listbyEmp.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="TaskActionEmp!listbyEmp.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="TaskActionEmp!listbyEmp.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
<div class="modal" id="finishTask">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">完成任务</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_admin_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="task.tid" name="task.tid" >
                        <div class="form-group" id="task.expendDiv">
                            <label class="col-md-4 control-label" for="task.expend">费时工时:</label>
                            <div class="col-md-4">
                                <input type="number" id="task.expend" name="task.expend" class="form-control" min="0" max="999" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="task.expendSpan"></div>
                        </div>
                        <div class="form-group" id="task.rnoteDiv">
                            <label class="col-md-4 control-label" for="task.rnote">任务总结:</label>
                            <div class="col-md-4">
                                <textarea id="task.rnote" name="task.rnote" class="form-control" ></textarea>
                            </div>
                            <div class="col-md-4" id="task.rnoteSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="finishTaskBtn" class="btn btn-md btn-primary">提交</button>
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
<jsp:include page="/pages/include_project.jsp"></jsp:include>
<jsp:include page="/pages/include_task.jsp"></jsp:include>
</body>
</html>
