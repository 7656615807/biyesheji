$(function() {
    $("#user\\.userid").on("blur",function() {
        validateEmpty("user\\.userid") ;
        var userid = $("#user\\.userid").val();
        $.post("AdminActionAdmin!checkUserid.action",{"user.userid":userid},
            function (data) {
                if (data == "false"){
                    $("#user\\.useridDiv").attr("class", "form-group has-error");//红色信息标注
                    $("#user\\.useridSpan").html("<p class='text-success'>该登录名称已经存在！</p>");
                }
            },"text");
    }) ;
    $("#user\\.password").on("blur",function() {
        validateEmpty("user\\.password") ;
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

    $("#myform_admin_insert").on("submit",function() {
        var x = validateEmpty("user\\.userid")
            & validateEmpty("user\\.password")
            & validateEmpty("user\\.name")
            & validateRegex("user\\.email",/^\w+@\w+\.\w+$/)
            & validateRegex("user\\.phone",/^1[3|4|5|7|8][0-9]{9}$/);
        if (x == 0) {
            return false;
        } else {
            return true ;
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
function equalspw(ele) { //实现正则验证
        if ($("#" + ele).val() == ($("#newpw").val())) {  //验证通过
            $("#" + ele + "Div").attr("class", "form-group has-success");//绿色信息标注
            $("#" + ele + "Span").html("<p class='text-success'>该字段内容输入正确！</p>");
            return true;
        } else { //现在没有内容
            $("#" + ele + "Div").attr("class", "form-group has-error");//红色信息标注
            $("#" + ele + "Span").html("<p class='text-success'>两次密码输入不一样，请重新输入！</p>");
            return false;
        }
}