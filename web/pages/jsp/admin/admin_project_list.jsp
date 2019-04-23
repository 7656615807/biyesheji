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
    <script type="text/javascript" src="js/admin_project_update.js"></script>

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
                    <strong>员工列表</strong>
                </div>
                <div class="panel-body">
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
                                    <td class="text-center"><span id="title-${a.proid}">${a.title}</span></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.userByCreid.userid}"><span id="creidName-${a.proid}">${a.userByCreid.name}</span></a></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.userByMgr.userid}" id="mgrHref-${a.proid}"><span id="mgrName-${a.proid}">${a.userByMgr.name}</span></a></td>
                                    <td class="text-center"><span id="pubdate-${a.pubdate}">${a.pubdate}</span></td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary" id="adminBtn-${a.proid}">
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;编辑项目
                                            </button>
                                            <button type="button" class="btn btn-info" id="dfgghhh-${a.proid}">
                                                <span class="glyphicon glyphicon-edit"></span>&nbsp;项目任务
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>

                    <div id="pageDiv" class="text-right">
                        <ul class="pagination pagination-sm" id="pagecontrol">
                            <c:if test="${currentPage == 1}">
                                <li><a style='cursor:pointer;'>首页</a></li>
                                <li><a style='cursor:pointer;'>上一页</a></li>
                            </c:if>

                            <c:if test="${currentPage != 1}">
                                <li><a style='cursor:pointer;' href="ProjectActionAdmin!list.action?cp=1">首页</a></li>
                                <li><a style='cursor:pointer;' href="ProjectActionAdmin!list.action?cp=${currentPage - 1}">上一页</a></li>
                            </c:if>

                            <c:if test="${currentPage == allRecorders}">
                            <li><a style='cursor:pointer;' >下一页</a></li>
                                <li><a style='cursor:pointer;' >尾页</a></li>
                            </c:if>

                            <c:if test="${currentPage != allRecorders}">
                            <li><a style='cursor:pointer;'  href="ProjectActionAdmin!list.action?cp=${currentPage + 1}">下一页</a></li>
                                <li><a style='cursor:pointer;'  href="ProjectActionAdmin!list.action?cp=${allRecorders}">尾页</a></li>
                            </c:if>
                            <p>
                                当前第${currentPage}页       总共${allRecorders}页
                            </p>
                        </ul>
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
                <h4 class="modal-title">编辑项目</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  id="myform_project_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="project.proid" name="project.proid">
                        <div class="form-group" id="project.titleDiv">
                            <label class="col-md-4 control-label" for="project.title">项目名称:</label>
                            <div class="col-md-4">
                                <input type="text" id="project.title" name="project.title" class="form-control" placeholder="请输入项目名称"autocomplete="off">
                            </div>
                            <div class="col-md-4" id="project.titleSpan"></div>
                        </div>
                        <div class="form-group" id="user.useridDiv">
                            <label class="col-md-4 control-label" for="user.userid">项目经理:</label>
                            <div class="col-md-4">
                                <c:if test="${all != null}">
                                    <select id="user.userid" class="form-control" name="user.userid">

                                    </select>
                                </c:if>
                            </div>
                            <div class="col-md-4" id="user.useridSpan"></div>
                        </div>
                        <div class="form-group" id="project.noteDiv">
                            <label class="col-md-4 control-label" for="project.note">项目描述:</label>
                            <div class="col-md-4">
                                <textarea name="project.note" id="project.note" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="col-md-4" id="project.noteSpan"></div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="submit" class="btn btn-md btn-primary" id="submitBtn">修改</button>
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
