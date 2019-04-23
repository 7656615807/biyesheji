<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"RoleActionAdmin!update.action";
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
    <script type="text/javascript" src="js/role_update.js"></script>
    <script type="text/javascript">

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
                    <strong>订单列表</strong>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered table-hover table-striped" id="newsTable">
                        <tr>
                            <td style="width: 5%;" class="text-center"><strong>编号</strong></td>
                            <td style="width: 20%;" class="text-center"><strong>角色名称</strong> </td>
                            <td style="width: 50%;" class="text-center"><strong>角色描述</strong> </td>
                            <td style="width: 25%;" class="text-center"><strong>操作</strong> </td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center">
                                        ${a.rid}
                                    </td>
                                    <td class="text-center">
                                        <span id="title-${a.rid}">${a.title}</span>
                                    </td>
                                    <td class="text-center">
                                        <span id="note-${a.rid}">${a.note}</span>
                                    </td>
                                    <td class="text-center">
                                        <button id="updateBtn-${a.rid}" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
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
                                <li><a style='cursor:pointer;' href="RoleActionAdmin!list.action?cp=1">首页</a></li>
                                <li><a style='cursor:pointer;' href="RoleActionAdmin!list.action?cp=${currentPage - 1}">上一页</a></li>
                            </c:if>

                            <c:if test="${currentPage == allRecorders}">
                            <li><a style='cursor:pointer;' >下一页</a></li>
                                <li><a style='cursor:pointer;' >尾页</a></li>
                            </c:if>

                            <c:if test="${currentPage != allRecorders}">
                            <li><a style='cursor:pointer;'  href="RoleActionAdmin!list.action?cp=${currentPage + 1}">下一页</a></li>
                                <li><a style='cursor:pointer;'  href="RoleActionAdmin!list.action?cp=${allRecorders}">尾页</a></li>
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
<div class="modal" id="groupsInfo">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改“<span id="RoleTitleSpan"></span>”角色信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_role_insert" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <div class="form-group" id="role.titleDiv">
                            <label class="col-md-4 control-label" for="role.title">角色名称:</label>
                            <div class="col-md-4">
                                <input type="text" id="role.title" name="role.title" class="form-control" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="role.titleSpan"></div>
                        </div>
                        <div class="form-group" id="role.noteDiv">
                            <label class="col-md-4 control-label" for="role.note">角色描述:</label>
                            <div class="col-md-4">
                                <input type="text" id="role.note" name="role.note" class="form-control"  autocomplete="off"></textarea>
                                <input type="hidden" id="role.rid" name="role.rid"></textarea>
                            </div>
                            <div class="col-md-4" id="role.noteSpan"></div>
                        </div>
                        <div class="form-group" id="gidsDiv">
                            <label class="col-md-4 control-label" for="gids">包含权限:</label>
                            <div class="col-md-4" id="gidsDiv0">

                            </div>
                            <div class="col-md-4" id="gidsSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="submitBtn" class="btn btn-md btn-primary">提交</button>
                                <button type="reset" class="btn btn-md btn-primary">重置</button>
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
