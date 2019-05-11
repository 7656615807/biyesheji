$(function() {
    $.post("UserActionEmp!Num.action",{},
        function (data) {
            console.log(data);
            $("#UnreadNum").text(data.UnreadNum);
            $("#UnStartNum").text(data.UstartNum);
            $("#UnfinishNum").text(data.DoingNum);
        },"json");
}) ;
