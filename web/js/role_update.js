$(function() {
    $("button[id*='updateBtn-']").each(function () {
        var  rid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            var title = $("#title-" + rid).val();
            var note = $("#note-" + rid).val();
            console.log("rid = " + rid + ", title = " + title + ", note = " + note);
            $.post("RoleActionAdmin!updatePre.action",{"role.rid":rid},
                function (data) {
                    $("#RoleTitleSpan").text(data.title);
                    $("#role\\.title").val(data.title);
                    $("#role\\.note").val(data.note);
                    $("#role\\.rid").val(data.rid);
                    $("#gidsDiv0").empty();
                    for (var x = 0; x < data.groups.length; x ++){
                        var str = "<div class='checkbox col-md-4'>"+
                            "<label><input type='checkbox' id='gids' name='gids' value='"+ data.groups[x].gid+ "'"+data.groups[x].ckd+ ">"+ data.groups[x].title+"</label>"+
                            "</div>"
                        $("#gidsDiv0").append(str);
                    }
                    $("#groupsInfo").modal("toggle");
                },"json");
        })
    })

    $("#role\\.title").on("blur",function() {//判断派件员名字是否为空
        validateEmpty("role\\.title") ;
        var rid = $("#role\\.rid").val();
        var title = $("#role\\.title").val();
        $.post("RoleActionAdmin!checkTitleUpdate.action",{"role.title":title,"role.rid":rid},
            function (data) {
                if (data == "false"){
                    $("#role\\.titleDiv").attr("class", "form-group has-error");//红色信息标注
                    $("#role\\.titleSpan").html("<p class='text-success'>该角色名已经存在！</p>");
                }
            },"text");
    }) ;

    $("#submitBtn").on("click",function() {
        var ugid = [];
        $('input:checkbox:checked').each(function (index, item) {
            ugid.push($(this).val());
        });
        if (ugid == ""){
            $("#gidsDiv").attr("class", "form-group has-error");//红色信息标注
            $("#gidsSpan").html("<p class='text-success'>请选择权限！</p>");
            return false;
        }
        $("#groupsInfo").modal("toggle");
        if (validateEmpty("role\\.title")) {
            var title = $("#role\\.title").val();
            var note = $("#role\\.note").val();
            var rid = $("#role\\.rid").val();
            console.log("rid = " + rid + ", title = " + title + ", note = " + note);
            $.post("RoleActionAdmin!update.action",{"role.rid":rid,"role.title":title,"role.note":note,"ugid":ugid.toString()},
                function (data) {
                    $("#title-"+ rid).text(title);
                    $("#note-"+ rid).text(note);
                    operateAllert(data == "true","权限组信息修改成功！","权限组信息修改失败！");
                },"text");
        }
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
