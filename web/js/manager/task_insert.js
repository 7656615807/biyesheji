$(function() {
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
        forceParse: 0,
        startDate: '2019-01-01',
        endDate: '2022-01-01'
    });
    $('#task\\.expiredate').focus(function(){
        $(this).blur();//不可输入状态
    })


    $("#task\\.title").on("blur",function() {
        validateEmpty("task\\.title") ;
    }) ;

    $("#task\\.estimate").on("blur",function() {
        validateRegex("task\\.estimate",/^\d+$/) ;
    }) ;
    $("#task\\.note").on("blur",function() {
        validateEmpty("task\\.note") ;
    }) ;

    $("#myform_task_insert").on("submit",function() {
         var x = validateEmpty("task\\.title") &
            validateEmpty("task\\.note") &
            validateEmpty("task\\.expiredate") &
            validateRegex("task\\.estimate",/^\d+$/) ;
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
