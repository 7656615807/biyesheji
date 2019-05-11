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
    <script type="text/javascript" src="js/user_updateLock.js"></script>

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
                    <strong>员工列表</strong>
                </div>
                <div class="panel-body">
                    <jsp:include page="/pages/search.jsp"></jsp:include>
                    <table class="table table-bordered table-hover table-striped"  id="newsTable">
                        <tr>
                            <td class="text-center"><input type="checkbox" id="selall" name="selall"> </td>
                            <td class="text-center"><strong>姓名</strong> </td>
                            <td class="text-center"><strong>电话</strong> </td>
                            <td class="text-center"><strong>邮箱</strong> </td>
                            <td class="text-center"><strong>角色</strong></td>
                            <td class="text-center"><strong>状态</strong> </td>
                            <td class="text-center"><strong>操作</strong></td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center"><input type="checkbox" name="nid" id="nid" value="${a.userid}" ></td>
                                    <td class="text-center"><a href="/UserActionAdmin!show.action?user.userid=${a.userid}"><span id="name-${a.userid}">${a.name}</span></a></td>
                                    <td class="text-center"><span id="phone-${a.userid}">${a.phone}</span></td>
                                    <td class="text-center"><span id="email-${a.userid}">${a.email}</span></td>
                                    <td class="text-center">
                                        <select id="role.rid-${a.userid}" class="form-control" name="role.rid-${a.userid}">
                                            <option  value="5" ${a.level== 3 ? "selected" : ""}>普通员工</option>
                                            <option  value="4" ${a.level== 2 ? "selected" : ""}>项目经理</option>
                                        </select>
                                    </td>
                                    <td class="text-center"><span id="lockflag-${a.userid}">${a.lockflag == 0 ? "激活" : "锁定"}</span></td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                                <span class="glyphicon glyphicon-list"></span>&nbsp;编辑
                                            </button>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a id="passwordBtn-${a.userid}" onmouseover="this.style.cursor='hand'">
                                                    <span class="glyphicon glyphicon-edit"></span>&nbsp;变更密码</a>
                                                </li>
                                                <li class="divider"></li>
                                                <li><a id="adminBtn-${a.userid}" onmouseover="this.style.cursor='hand'" >
                                                    <span class="glyphicon glyphicon-pencil"></span>&nbsp;员工信息</a>
                                                </li>
                                            </ul>
                                            <button id="updateBtn-${a.userid}" class="btn btn-info">&nbsp;修改角色</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <button class="btn btn-danger" id="delBtn">
                        激活员工
                    </button>
                    <a class="btn btn-success"href="/pages/jsp/admin/admin_user_insert.jsp"> 增加员工</a>
                    <a class="btn btn-success"href="/UserActionAdmin!listActive.action"> 活跃员工列表</a>
                    <div id="pageDiv" class="text-right">
                        <c:if test="${allRecorders != 0}">
                            <ul class="pagination pagination-sm" id="pagecontrol">
                                <c:if test="${currentPage == 1}">
                                    <li><a style='cursor:pointer;'>首页</a></li>
                                    <li><a style='cursor:pointer;'>上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage != 1}">
                                    <li><a style='cursor:pointer;' href="UserActionAdmin!listActive.action?cp=1&col=${column}&kw=${keyword}">首页</a></li>
                                    <li><a style='cursor:pointer;' href="UserActionAdmin!listActive.action?cp=${currentPage - 1}&col=${column}&kw=${keyword}">上一页</a></li>
                                </c:if>

                                <c:if test="${currentPage == allRecorders}">
                                    <li><a style='cursor:pointer;' >下一页</a></li>
                                    <li><a style='cursor:pointer;' >尾页</a></li>
                                </c:if>

                                <c:if test="${currentPage != allRecorders}">
                                    <li><a style='cursor:pointer;'  href="UserActionAdmin!listActive.action?cp=${currentPage + 1}&col=${column}&kw=${keyword}">下一页</a></li>
                                    <li><a style='cursor:pointer;'  href="UserActionAdmin!listActive.action?cp=${allRecorders}&col=${column}&kw=${keyword}">尾页</a></li>
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
<div class="modal" id="adminPassword">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_role_insert" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <div class="form-group" id="passwordDiv">
                            <label class="col-md-4 control-label" for="password">新密码:</label>
                            <div class="col-md-4">
                                <input type="text" id="password" name="password" class="form-control" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="passwordSpan"></div>
                        </div>
                        <input type="hidden" id="password.userid" name="password.userid" class="form-control" autocomplete="off">
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="passwordsubmitBtn" class="btn btn-md btn-primary">修改密码</button>
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
                <h4 class="modal-title">修改员工信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_admin_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <div class="form-group" id="user.useridDiv">
                            <label class="col-md-4 control-label" for="user.userid">登录名称:</label>
                            <div class="col-md-4">
                                <input type="text" id="user.userid" name="user.userid" class="form-control" readonly="readonly">
                            </div>
                            <div class="col-md-4" id="user.useridSpan"></div>
                        </div>
                        <div class="form-group" id="user.nameDiv">
                            <label class="col-md-4 control-label" for="user.name">真实姓名:</label>
                            <div class="col-md-4">
                                <input type="text" id="user.name" name="user.name" class="form-control"  autocomplete="off"></textarea>
                            </div>
                            <div class="col-md-4" id="user.nameSpan"></div>
                        </div>
                        <div class="form-group" id="user.phoneDiv">
                            <label class="col-md-4 control-label" for="user.phone">联系电话:</label>
                            <div class="col-md-4">
                                <input type="text" id="user.phone" name="user.phone" class="form-control"  autocomplete="off"></textarea>
                            </div>
                            <div class="col-md-4" id="user.phoneSpan"></div>
                        </div>
                        <div class="form-group" id="user.emailDiv">
                            <label class="col-md-4 control-label" for="user.email">联系邮箱:</label>
                            <div class="col-md-4">
                                <input type="text" id="user.email" name="user.email" class="form-control"  autocomplete="off"></textarea>
                            </div>
                            <div class="col-md-4" id="user.emailSpan"></div>
                        </div>
                        <div class="form-group" id="user.levelDiv">
                            <label class="col-md-4 control-label" for="user.level">包含权限:</label>
                            <div class="col-md-4">
                                <select id="user.level" class="form-control" name="user.level">

                                </select>
                            </div>
                            <div class="col-md-4" id="user.levelSpan"></div>
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
