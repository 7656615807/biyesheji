$(function() {

    $("button[id*='adminBtn-']").each(function () {
        var  did = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $.post("DocumentActionEmp!updatePre.action",{"document.did":did},
                function (data) {
                console.log(data);
                    $("#document\\.did").val(data.did);
                    $("#document\\.title").val(data.title);
                    $("#oldFile").val(data.file);
                    $("#document\\.content").val(data.content);
                    for (var x = 0; x < data.doctypes.length; x ++){
                        if (data.doctypes[x].dtid==data.dtid){
                            $("#doctype\\.dtid").val(data.doctypes[x].title);
                        }
                    }
                    $("#documentUpdate").modal("toggle");
                },"json");
        })
    })

}) ;
