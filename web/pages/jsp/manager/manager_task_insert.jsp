<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"TaskActionManager!insert.action";
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
    <script type="text/javascript" src="js/manager/task_insert.js"></script>

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
                    <strong>添加新的任务</strong>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="<%=insertUrl%>" id="myform_task_insert" method="post">
                        <fieldset>
                            <!--<legend><label><span class=""></span></label></legend>-->
                            <input type="hidden" id="project.proid" name="project.proid" value="${project.proid}">
                            <div class="form-group" id="project.titleDiv">
                                <label class="col-md-4 control-label" for="project.title">所属项目:</label>
                                <div class="col-md-4">
                                    <input type="text" id="project.title" name="project.title" value="${project.title}" class="form-control" readonly="readonly">
                                </div>
                                <div class="col-md-4" id="project.titleSpan"></div>
                            </div>
                            <div class="form-group" id="tasktype.ttidDiv">
                                <label class="col-md-4 control-label" for="tasktype.ttid">任务类型:</label>
                                <div class="col-md-4">
                                    <c:if test="${allTaskType != null}">
                                        <select id="tasktype.ttid" class="form-control" name="tasktype.ttid">
                                            <c:forEach items="${allTaskType}" var="task">
                                                <option value="${task.ttid}">${task.title}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                                <div class="col-md-4" id="tasktype.ttidSpan"></div>
                            </div>
                            <div class="form-group" id="task.titleDiv">
                                <label class="col-md-4 control-label" for="task.title">任务标题:</label>
                                <div class="col-md-4">
                                    <input type="text" id="task.title" name="task.title" class="form-control" autocomplete="off">
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
                            <div class="form-group" id="user.useridDiv">
                                <label class="col-md-4 control-label" for="user.userid">指派员工:</label>
                                <div class="col-md-4">
                                    <c:if test="${allEmp != null}">
                                        <select id="user.userid" class="form-control" name="user.userid">
                                            <c:forEach items="${allEmp}" var="user">
                                                <option value="${user.userid}">${user.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                                <div class="col-md-4" id="user.useridSpan"></div>
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
                                        <option value="1" selected>★☆☆</option>
                                        <option value="2"  >★★☆</option>
                                        <option value="3"  >★★★</option>
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
