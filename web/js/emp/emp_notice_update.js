$(function() {
    $("button[id*='adminBtn-']").each(function () {
        var snid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click", function () {
            var rnum = $("#rnum-"+snid).text();
            console.log("snid = " + snid+",rnum="+rnum);
            $.post("NoticeActionEmp!show.action", {"notice.snid": snid},
                function (data) {
                    console.log(data);
                    $("#notice\\.snid").val(data.snid);
                    $("#notice\\.title").val(data.title);
                    $("#notice\\.content").val(data.content);
                    if (data.isFirstRead) {
                        $("#rnum-"+snid).empty();
                        var num = parseInt(rnum)+1;
                        $("#rnum-"+snid).append(num);
                        $("#status-"+snid).empty();
                        $("#status-"+snid).append("已读");
                    }

                    $("#updateNoticeInfo").modal("toggle");
                }, "json");
        });
    });

});