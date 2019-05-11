<%@ page language="java" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
    $(function() {
        $("a[id*='taskInfo-']").each(function () {
            var tid = this.id.split("-")[1]; //分离出id的信息
            $(this).on("click", function () {
                $.post("ProjectActionCommon!showTask.action", {"task.tid": tid},
                    function (data) {
                        console.log(data);
                        $("#projecntame").text(data.project.title);
                        $("#taskTypee").text(data.tasktype==null?"":data.tasktype.title);
                        $("#tasktitle").text(data.title);
                        $("#userByCreatorname").text(data.userByCreator==null?"":data.userByCreator.name);
                        $("#userByReceivername").text(data.userByReceiver==null?"":data.userByReceiver.name);
                        $("#createdate").text(data.createdate==null?"":getLocalTime(data.createdate));
                        $("#expiredate").text(data.expiredate==null?"":getLocalTime(data.expiredate));
                        $("#finishdate").text(data.finishdate==null?"":getLocalTime(data.finishdate));
                        if (data.status == 0){
                            $("#status").text("未开始");
                        } else if (data.status == 1) {
                            $("#status").text("进行中");
                        }else if (data.status == 2) {
                            $("#status").text("取消");
                        }else {
                            $("#status").text("已完成");
                        }

                        $("#lastupdatedate").text(data.lastupdatedate);

                        if (data.priority == 0){
                            $("#priority").text("★☆☆");
                        } else if (data.priority == 1) {
                            $("#priority").text("★★☆");
                        }else {
                            $("#priority").text("★★★");
                        }
                        $("#extimate").text(data.extimate);
                        $("#expend").text(data.expend);
                        $("#note").text(data.note);
                        $("#rnote").text(data.rnote);
                        $("#userByCanceler").text(data.userByCanceler==null?"":data.userByCanceler.name);
                        $("#cnote").text(data.cnote);
                        $("#taskInfo").modal("toggle");
                    }, "json");
            })
        })
    })
    function getLocalTime(nS) {
        var now = new Date(nS);
        var year=now.getFullYear();
        var month=now.getMonth()+1;
        var date=now.getDate();
        // var hour=now.getHours();
        // var minute=now.getMinutes();
        return year+"-"+month+"-"+date;
    }
    // return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' ');
    // }
</script>
<div class="modal" id="taskInfo">
    <div class="modal-dialog" style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">任务信息</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-hover table-striped" id="newsTable">
                    <tr>
                        <td style="width: 15%"><strong>所属项目：</strong></td>
                        <td id="projecntame"></td>
                    </tr>
                    <tr>
                        <td><strong>任务类型：</strong></td>
                        <td id="taskTypee"></td>
                    </tr>
                    <tr>
                        <td><strong>任务标题：</strong></td>
                        <td id="tasktitle"></td>
                    </tr>
                    <tr>
                        <td><strong>任务创建者：</strong></td>
                        <td id="userByCreatorname"></td>
                    </tr>
                    <tr>
                        <td><strong>任务执行者：</strong></td>
                        <td id="userByReceivername"></td>
                    </tr>
                    <tr>
                        <td><strong>创建日期：</strong></td>
                        <td id="createdate"></td>
                    </tr>
                    <tr>
                        <td><strong>截止日期：</strong></td>
                        <td id="expiredate"></td>
                    </tr>
                    <tr>
                        <td><strong>任务状态：</strong></td>
                        <td id="status"></td>
                    </tr>
                    <tr>
                        <td><strong>最后修改日期：</strong></td>
                        <td id="lastupdatedate"></td>
                    </tr>
                    <tr>
                        <td><strong>优先级别：</strong></td>
                        <td id="priority"></td>
                    </tr>
                    <tr>
                        <td><strong>预计工时：</strong></td>
                        <td id="extimate"></td>
                    </tr>
                    <tr>
                        <td><strong>任务内容：</strong></td>
                        <td id="note"></td>
                    </tr>
                    <tr>
                        <td><strong>耗费工时：</strong></td>
                        <td id="expend"></td>
                    </tr>
                    <tr>
                        <td><strong>完成日期：</strong></td>
                        <td id="finishdate"></td>
                    </tr>

                    <tr>
                        <td  style="width: 240px;">
                            <strong>任务总结：</strong>
                        </td>
                        <td id="rnote">

                        </td>
                    </tr>
                    <tr>
                        <td><strong>任务取消者：</strong></td>
                        <td id="userByCanceler"></td>
                    </tr>
                    <tr>
                        <td><strong>取消原因：</strong></td>
                        <td id="cnote"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-success" data-dismiss="modal">关闭界面</button>
            </div>
        </div>
    </div>
</div>