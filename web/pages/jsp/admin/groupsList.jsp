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
    <script type="text/javascript">
        $(function() {
            $("button[id*='updateBtn-']").each(function () {
                var  gid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click",function () {
                    var title = $("#title-" + gid).val();
                    var note = $("#note-" + gid).val();
                    console.log("gid = " + gid + ", title = " + title + ", note = " + note);
                    $.post("GroupsActionAdmin!update.action",{"groups.gid":gid,"groups.title":title,"groups.note":note},
                    function (data) {
                        operateAllert(data == "true","权限组信息修改成功！","权限组信息修改失败！");
                    },"text");
                })
            })

            $("button[id*='showBtn-']").each(function () {
                var  gid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click",function () {
                    console.log("gid = " + gid);
                    $.post("GroupsActionAdmin!show.action",{"groups.gid":gid},
                        function (data) {
                            $("#actionsTab tr:gt(0)").remove();
                            console.log(data);
                            $("#groupsTitleSpan").text(data.title);
                            for (var x = 0; x < data.actions.length; x ++ ){
                                var tr = "<tr><td>"+data.actions[x].actid+"</td><td>"+data.actions[x].title+"</td><td>"+data.actions[x].url+"</td></tr>"
                                $("#actionsTab").append($(tr));
                            }
                            $("#groupsInfo").modal("toggle");
                        },"json");
                })
            })
        });
    </script>

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
                    <strong>权限组列表</strong>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered table-hover table-striped" id="newsTable">
                        <tr>
                            <td style="width: 5%;" class="text-center"><strong>编号</strong></td>
                            <td style="width: 20%;" class="text-center"><strong>权限组名称</strong> </td>
                            <td style="width: 50%;" class="text-center"><strong>权限组描述</strong> </td>
                            <td style="width: 25%;" class="text-center"><strong>操作</strong> </td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="a">
                                <tr>
                                    <td class="text-center">
                                        ${a.gid}
                                    </td>
                                    <td class="text-center">
                                        <input type="text" id="title-${a.gid}" name="title-${a.gid}" class="form-control input-sm" value="${a.title}">
                                    </td>
                                    <td class="text-center">
                                        <input type="text" id="note-${a.gid}" name="note-${a.gid}" class="form-control input-sm" value="${a.note}">
                                    </td>
                                    <td class="text-center">
                                    <button id="updateBtn-${a.gid}" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
                                    <button id="showBtn-${a.gid}" class="btn btn-info" ><span class="glyphicon glyphicon-list"></span>&nbsp;包含权限</button>
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
                                <li><a style='cursor:pointer;' href="GroupsActionAdmin!list.action?cp=1">首页</a></li>
                                <li><a style='cursor:pointer;' href="GroupsActionAdmin!list.action?cp=${currentPage - 1}">上一页</a></li>
                            </c:if>

                            <c:if test="${currentPage == allRecorders}">
                            <li><a style='cursor:pointer;' >下一页</a></li>
                                <li><a style='cursor:pointer;' >尾页</a></li>
                            </c:if>

                            <c:if test="${currentPage != allRecorders}">
                            <li><a style='cursor:pointer;'  href="GroupsActionAdmin!list.action?cp=${currentPage + 1}">下一页</a></li>
                                <li><a style='cursor:pointer;'  href="GroupsActionAdmin!list.action?cp=${allRecorders}">尾页</a></li>
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
                <h4 class="modal-title">查看“<span id="groupsTitleSpan"></span>”信息</h4>
            </div>
            <div class="modal-body">
                <table id="actionsTab" class="table table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <td style="width: 10%;" class="text-center"><strong>编号</strong></td>
                        <td style="width: 30%;" class="text-center"><strong>权限名称</strong></td>
                        <td style="width: 60%;" class="text-center"><strong>访问路径</strong></td>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-success" data-dismiss="modal">关闭界面</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
