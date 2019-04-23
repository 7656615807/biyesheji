$(function() {
    $("button[id*='updateBtn-']").each(function () {
        var  userid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            var role = $("#role\\.rid-"+userid).val();
            console.log("userid = " + userid + ", role = " + role);
            $.post("AdminActionAdmin!updateRole.action",{"user.userid":userid,"role.rid":role},
                function (data) {
                    operateAllert(data == "true","管理员信息修改成功！","管理员信息修改失败！");
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

    $("a[id*='adminBtn-']").each(function () {
        var  userid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $.post("AdminActionAdmin!updatePre.action",{"user.userid":userid},
                function (data) {
                console.log(data);
                    $("#user\\.userid").val(data.userid);
                    $("#user\\.name").val(data.name);
                    $("#user\\.phone").val(data.phone);
                    $("#user\\.email").val(data.email);
                    $("#role\\.rid").empty();
                    for (var x = 0; x < data.roles.length; x ++){
                        var str = "<option value='"+data.roles[x].rid+"' "+(data.roles[x].rid==data.rid?"selected":"")+">"+data.roles[x].title+"</option>";
                        $("#role\\.rid").append(str);
                    }
                    $("#updateAdminInfo").modal("toggle");
                },"json");
        })
    })



    $("#submitBtn").on("click",function() {
        var x =validateEmpty("user\\.name")
            & validateRegex("user\\.email",/^\w+@\w+\.\w+$/)
            & validateRegex("user\\.phone",/^1[3|4|5|7|8][0-9]{9}$/);
        if (x == 0) {
            return false;
        }
        var name = $("#user\\.name").val();
        var phone = $("#user\\.phone").val();
        var email = $("#user\\.email").val();
        var userid = $("#user\\.userid").val();
        var rid = $("#role\\.rid").val();
        console.log("rid = " + rid + ", name = " + name + ", phone = " + phone+",email = " + email + ", userid = " + userid);
        $.post("AdminActionAdmin!updateInfo.action",{"user.userid":userid,"user.name":name,"user.phone":phone,"user.email":email,"role.rid":rid},
            function (data) {
                $("#name-"+ userid).text(name);
                $("#phone-"+ userid).text(phone);
                $("#email-"+ userid).text(email);
                operateAllert(data == "true","权限组信息修改成功！","权限组信息修改失败！");
                $("#role\\.rid-"+userid+" option:selected").each(function (ind) {
                    console.log($(this).val()+","+$(this).text()+","+$(this).prop("selected"));
                    $(this).prop("selected",false);
                });
                $("#role\\.rid-"+userid+" option").each(function (ind) {
                    if ($(this).val() == rid){
                        $(this).prop("selected",true);
                    }
                })
                $("#updateAdminInfo").modal("hide");
            },"text");

    }) ;
    $("#user\\.name").on("blur",function() {
        validateEmpty("user\\.name") ;
    }) ;
    $("#user\\.email").on("blur",function() {  //邮箱格式
        validateRegex("user\\.email",/^\w+@\w+\.\w+$/) ;
    }) ;
    $("#user\\.phone").on("blur",function() {             //手机号
        validateRegex("user\\.phone",/^1[3|4|5|7|8][0-9]{9}$/) ;
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
                var url = "/AdminActionAdmin!delete.action?ids="+data;
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