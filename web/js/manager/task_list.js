$(function() {
    $("button[id*='updateBtn-']").each(function () {
        var  userid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            var role = $("#role\\.rid-"+userid).val();
            console.log("userid = " + userid + ", role = " + role);
            $.post("UserActionAdmin!updateRole.action",{"user.userid":userid,"role.rid":role},
                function (data) {
                    operateAllert(data == "true","员工信息修改成功！","员工信息修改失败！");
                },"text");
        })
    });

    $("#canclesubmitBtn").on("click",function () {
        var tid = $("#cancle\\.tid").val();
        var cnote = $("#cnote").val();
        console.log("task.tid = " + tid + ", task.cnote = " + cnote);
        $.post("TaskActionManager!updateCancel.action",{"task.tid":tid,"task.cnote":cnote},
            function (data) {
                if (data == "true") {
                    $("#status-"+tid).empty();
                    $("#status-"+tid).text("取消");
                }
                operateAllert(data == "true","任务取消成功！","任务取消失败！");
                $("#adminCancel").modal("toggle");
            },"text");
    });

    $("a[id*='cancleBtn-']").each(function () {
        var  tid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $("#cancle\\.tid").val(tid);
            $("#adminCancel").modal("toggle");
        })
    });

    $("a[id*='adminBtn-']").each(function () {
        var  tid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $.post("TaskActionManager!updatePre.action",{"task.tid":tid},
                function (data) {
                console.log(data);
                    $("#task\\.note").val(data.note);
                    $("#task\\.title").val(data.title);
                    $("#task\\.tid").val(data.tid);
                    $("#task\\.project").val(data.project.title);
                    $("#task\\.priority").empty();
                    var str= "<option value='1' "+(data.priority==1?"selected":"")+">★☆☆</option>"+
                        "<option value='2' "+(data.priority==2?"selected":"")+">★★☆</option>"+
                        "<option value='3' "+(data.priority==3?"selected":"")+">★★★</option>";
                    $("#task\\.priority").append(str);

                    $("#task\\.status").empty();
                    var str2= "<option value='1' "+(data.status==1?"selected":"")+">进行中</option>"+
                        "<option value='0' "+(data.status==0?"selected":"")+">未开始</option>"+
                        "<option value='3' "+(data.status==3?"selected":"")+">已完成</option>";
                    $("#task\\.status").append(str2);
                    $('#task\\.expiredate').val(getLocalTime(data.expiredate));
                    $("#task\\.estimate").val(data.estimate);
                    $("#updateAdminInfo").modal("toggle");
                    $('#task\\.expiredate').val();
                },"json");
        })
    });

    $("#task\\.expiredate").datetimepicker({//选择年月日
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn:false,//清除按钮
        forceParse: true,
        startDate:'2019-01-01',
        endDate: '2022-01-01'
    });
    $('#task\\.expiredate').focus(function(){
        $(this).blur();//不可输入状态
    })


    $("#updatesubmitBtn").on("click",function() {
        var x = validateRegex("task\\.estimate",/^\d+$/)
            & validateEmpty("task\\.note") ;
        if (x == 0) {
            return false;
        }
        var tid = $("#task\\.tid").val();
        var note = $("#task\\.note").val();
        var priority = $("#task\\.priority").val();
        var status = $("#task\\.status").val();
        var expiredate = $("#task\\.expiredate").val();
        var estimate = $("#task\\.estimate").val();
        $.post("TaskActionManager!update.action",{"task.tid":tid,"task.note":note,"task.priority":priority,"task.status":status,"task.expiredate":expiredate,"task.estimate":estimate},
            function (data) {
                if (status == 0){
                    $("#status-"+tid).text("未开始");
                } else if (status == 1) {
                    $("#status-"+tid).text("进行中");
                }else if (status == 2) {
                    $("#status-"+tid).text("取消");
                }else {
                    $("#status-"+tid).text("已完成");
                }
                $("#lastupdatedate-"+tid).text(data.lastupdatedate);

                if (priority == 0){
                    $("#priority-"+tid).text("★☆☆");
                } else if (priority == 1) {
                    $("#priority-"+tid).text("★★☆");
                }else {
                    $("#priority-"+tid).text("★★★");
                }
                $("#expiredate-"+tid).text(getLocalTime(expiredate));
                operateAllert(data == "true","任务信息修改成功！","任务信息修改失败！");
                $("#updateAdminInfo").modal("hide");
            },"text");
    }) ;


    $("#task\\.estimate").on("blur",function() {
        validateRegex("task\\.estimate",/^\d+$/) ;
    }) ;
    $("#task\\.note").on("blur",function() {
        validateEmpty("task\\.note") ;
    }) ;



}) ;
function validateEmpty(ele){ //验证数据是否为空
    if ($("#" + ele).val() != ""){  //表示是否有数据
        $("#" + ele + "Div").attr("class","form-group has-success") ;//绿色信息标注
        $("#" + ele + "Span").html("<p class='text-success'>该字段内容输入正确！</p>") ;
        return true ;
    }else { //现在没有内容
        $("#" + ele + "Div").attr("class","form-group has-error") ;//红色信息标注
        $("#" + ele + "Span").html("<p class='text-success'>该字段内容不允许为空！</p>") ;
        return false ;
    }
}
function validateRegex(ele,regex) { //实现正则验证
    if (validateEmpty(ele)) { //保证有数据传过来
        if (regex.test($("#" + ele).val())) {  //验证通过
            $("#" + ele + "Div").attr("class", "form-group has-success");//绿色信息标注
            $("#" + ele + "Span").html("<p class='text-success'>该字段内容输入正确！</p>");
            return true;
        } else { //现在没有内容
            $("#" + ele + "Div").attr("class", "form-group has-error");//红色信息标注
            $("#" + ele + "Span").html("<p class='text-success'>数据输入有误，请重新输入！</p>");
            return false;
        }
    }
}