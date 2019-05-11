<%@ page language="java" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    $("a[id*='project-']").each(function () {
        var  proid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $.post("ProjectActionCommon!showProject.action",{"project.proid":proid},
                function (data) {
                    console.log(data);
                    $("#project\\.proid").val(data.proid);
                    $("#project\\.title").val(data.title);
                    $("#project\\.note").val(data.note);
                    $("#user\\.userid").empty();
                    $("#documentUpdate").modal("toggle");
                },"json");
        })
    })
</script>
<div class="modal" id="documentUpdate">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">项目内容</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal"  id="myform_project_update" method="post">
                    <fieldset>
                        <!--<legend><label><span class=""></span></label></legend>-->
                        <input type="hidden" id="project.proid" name="project.proid">
                        <div class="form-group" id="project.titleDiv">
                            <label class="col-md-4 control-label" for="project.title">项目名称:</label>
                            <div class="col-md-4">
                                <input type="text" id="project.title" name="project.title" class="form-control" readonly="readonly">
                            </div>
                            <div class="col-md-4" id="project.titleSpan"></div>
                        </div>
                        <div class="form-group" id="project.noteDiv">
                            <label class="col-md-4 control-label" for="project.note">项目描述:</label>
                            <div class="col-md-4">
                                <textarea name="project.note" id="project.note" class="form-control" rows="5" readonly="readonly"></textarea>
                            </div>
                            <div class="col-md-4" id="project.noteSpan"></div>
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