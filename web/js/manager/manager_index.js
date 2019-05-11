$(function() {
    $.post("NoticeActionManager!unReadNum.action",{},
        function (data) {
            console.log(data);
            $("#num").text(data);
        },"text");
}) ;
