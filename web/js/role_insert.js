$(function() {
    $("#role\\.title").on("blur",function() {//判断派件员名字是否为空
        validateEmpty("role\\.title") ;
        var title = $("#role\\.title").val();
        $.post("RoleActionAdmin!checkTitleInsert.action",{"role.title":title},
            function (data) {
                if (data == "false"){
                    $("#role\\.titleDiv").attr("class", "form-group has-error");//红色信息标注
                    $("#role\\.titleSpan").html("<p class='text-success'>该角色名已经存在！</p>");
                }
            },"text");
    }) ;

    $("#myform_role_insert").on("submit",function() {
        var vals = [];
             $('input:checkbox:checked').each(function (index, item) {
                     vals.push($(this).val());
                 });
        if (vals.length == 0){
            $("#gidsDiv").attr("class", "form-group has-error");//红色信息标注
            $("#gidsSpan").html("<p class='text-success'>请选择权限！</p>");
            return false;
        }
        return validateEmpty("role\\.title");
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