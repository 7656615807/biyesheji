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
                var  actid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click",function () {
                    var title = $("#title-" + actid).val();
                    var url = $("#url-" + actid).val();
                    console.log("actid = " + actid + ", title = " + title + ", url = " + url);
                    $.post("ActionActionAdmin!update.action",{"action.actid":actid,"action.title":title,"action.url":url},
                    function (data) {
                        operateAllert(data == "true","权限信息修改成功！","权限信息修改失败！");
                    },"text");
                })
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
                    <strong>权限列表</strong>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered table-hover table-striped" id="newsTable">
                        <tr>
                            <td class="text-center"><strong>编号</strong></td>
                            <td class="text-center"><strong>权限名称</strong> </td>
                            <td class="text-center"><strong>访问路径</strong> </td>
                            <td class="text-center"><strong>操作</strong> </td>
                        </tr>
                        <c:if test="${allActions != null}">
                            <c:forEach items="${allActions}" var="act">
                                <tr>
                                    <td class="text-center">
                                        ${act.actid}
                                    </td>
                                    <td class="text-center">
                                        <input type="text" id="title-${act.actid}" name="title-${act.actid}" class="form-control input-sm" value="${act.title}">
                                    </td>
                                    <td class="text-center">
                                        <input type="text" id="url-${act.actid}" name="url-${act.actid}" class="form-control input-sm" value="${act.url}">
                                    </td>
                                    <td class="text-center">
                                        <button id="updateBtn-${act.actid}" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
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
                                <li><a style='cursor:pointer;' href="ActionActionAdmin!list.action?cp=1">首页</a></li>
                                <li><a style='cursor:pointer;' href="ActionActionAdmin!list.action?cp=${currentPage - 1}">上一页</a></li>
                            </c:if>

                            <c:if test="${currentPage == allRecorders}">
                            <li><a style='cursor:pointer;' >下一页</a></li>
                                <li><a style='cursor:pointer;' >尾页</a></li>
                            </c:if>

                            <c:if test="${currentPage != allRecorders}">
                            <li><a style='cursor:pointer;'  href="ActionActionAdmin!list.action?cp=${currentPage + 1}">下一页</a></li>
                                <li><a style='cursor:pointer;'  href="ActionActionAdmin!list.action?cp=${allRecorders}">尾页</a></li>
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

</body>
</html>
