$(function() {
    $("button[id*='updateBtn-']").each(function () {
        var  snid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            var level = $("#level-"+snid).val();
            console.log("snid = " + snid + ", level = " + level);
            $.post("NoticeActionAdmin!updateLevel.action",{"notice.snid":snid,"notice.level":level},
                function (data) {
                    operateAllert(data == "true","公告级别修改成功！","公告级别修改失败！");
                },"text");
        })
    });

    $("#passwordsubmitBtn").on("click",function () {
        var userid = $("#password\\.userid").val();
        var password = $("#password").val();
        console.log("userid = " + userid + ", password = " + password);
        $.post("AdminActionAdmin!updatePassword.action",{"user.userid":userid,"user.password":password},
            function (data) {
                operateAllert(data == "true","管理员密码修改成功！","管理员密码修改失败！");
                $("#adminPassword").modal("toggle");
            },"text");
    });

    $("a[id*='passwordBtn-']").each(function () {
        var  userid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $("#password\\.userid").val(userid);
            $("#adminPassword").modal("toggle");
        })
    });

    $("button[id*='adminBtn-']").each(function () {
        var  snid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            console.log("snid = " + snid);
            $.post("NoticeActionAdmin!updatePre.action",{"notice.snid":snid},
                function (data) {
                console.log(data);
                    $("#notice\\.snid").val(data.snid);
                    $("#notice\\.title").val(data.title);
                    $("#notice\\.content").val(data.content);
                    $("#notice\\.level").empty();
                    var str = "<option value='0' "+(data.level==0?"selected":"")+">项目经理</option>"
                        +"<option value='1' "+(data.level==1?"selected":"")+">所有员工</option>";
                    $("#notice\\.level").append(str);
                    $("#updateNoticeInfo").modal("toggle");
                },"json");
        })
    })



    $("#submitBtn").on("click",function() {
        var x =validateEmpty("notice\\.title")
            & validateEmpty("notice\\.content");
        if (x == 0) {
            return false;
        }
        var title = $("#notice\\.title").val();
        var content = $("#notice\\.content").val();
        var level = $("#notice\\.level").val();
        var snid = $("#notice\\.snid").val();
        $.post("NoticeActionAdmin!update.action",{"notice.title":title,"notice.content":content,"notice.level":level,"notice.snid":snid},
            function (data) {
                $("#title-"+ snid).text(title);
                $("#content-"+ snid).text(content);
                $("#level-"+ snid).text(level);
                operateAllert(data == "true","公告信息修改成功！","公告信息修改失败！");
                $("#level-"+snid).empty();
                var str = "<option value='0' "+(level==0?"selected":"")+">项目经理</option>"
                    +"<option value='1' "+(level==1?"selected":"")+">所有员工</option>";
                $("#level-"+snid).append(str);
                $("#updateNoticeInfo").modal("hide");
            },"text");

    }) ;
    $("#notice\\.title").on("blur",function() {
        validateEmpty("notice\\.title") ;
    }) ;
    $("#notice\\.content").on("blur",function() {
        validateEmpty("notice\\.content") ;
    }) ;

    $("#selall").on("click",function(){
        $("input[id='nid']").each(function(){
            this.checked = $("#selall").prop("checked");
        });
    });

    $("#delBtn").on("click",function(){
        //alert("点击")
        var data = ""; //保存所有要删除的数据
        $("input[id='nid']").each(function(){
            if(this.checked){
                data +=this.value +"," ;
            }
        });
        if(data == ""){ //现在没有选择要删除的内容
            alert("您还没有选择任何的数据，请先选择好数据！");
        }else {
            if(window.confirm("确定要删除这些吗")){
                var url = "/NoticeActionAdmin!delete.action?ids="+data;
                window.location = url
            }
        }
    });

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