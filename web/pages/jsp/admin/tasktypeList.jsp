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
                var  dtid = this.id.split("-")[1]; //分离出id的信息
                $(this).on("click",function () {
                    var title = $("#title-" + dtid).val();
                    console.log("dtid = " + dtid + ", title = " + title);
                    $.post("TasktypeActionAdmin!update.action",{"tasktype.ttid":dtid,"tasktype.title":title},
                    function (data) {
                        operateAllert(data == "true","任务类型修改成功！","任务类型修改失败！");
                    },"text");
                })
            })
            $("#insertBtn").on("click",function(){
                var title = $("#title").val(); //保存所有要删除的数据
                if(title == ""){ //现在没有选择要删除的内容
                    alert("您还没有输入任何的数据！");
                }else {
                    var url = "/TasktypeActionAdmin!insert.action?tasktype.title="+title;
                    window.location = url
                }
            });
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
                    <strong>任务类型列表</strong>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered table-hover table-striped" id="newsTable">
                        <tr>
                            <td class="text-center"><strong>编号</strong></td>
                            <td class="text-center"><strong>类型名称</strong> </td>
                            <td class="text-center"><strong>操作</strong> </td>
                        </tr>
                        <c:if test="${all != null}">
                            <c:forEach items="${all}" var="doc">
                                <tr>
                                    <td class="text-center">
                                        ${doc.ttid}
                                    </td>
                                    <td class="text-center">
                                        <input type="text" id="title-${doc.ttid}" name="title-${doc.ttid}" class="form-control input-sm" value="${doc.title}">
                                    </td>
                                    <td class="text-center">
                                        <button id="updateBtn-${doc.ttid}" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                    <button class="btn btn-success" data-toggle="modal" data-target="#insertTasktype">
                        增加任务类型
                    </button>
                </div>
                <div class="panel-footer">
                    <jsp:include page="/pages/include_foot.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal" id="insertTasktype">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">增加任务类型</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="" id="myform_role_insert" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <div class="form-group" id="titleDiv">
                            <label class="col-md-4 control-label" for="title">任务类型:</label>
                            <div class="col-md-4">
                                <input type="text" id="title" name="title" class="form-control" autocomplete="off">
                            </div>
                            <div class="col-md-4" id="titleSpan"></div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-4 col-md-offset-4">
                                <button type="button" id="insertBtn" class="btn btn-md btn-primary">增加</button>
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
