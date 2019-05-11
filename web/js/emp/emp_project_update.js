$(function() {

    /*$("a[id*='project-']").each(function () {
        var  proid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            $.post("ProjectActionManager!show.action",{"project.proid":proid},
                function (data) {
                console.log(data);
                    $("#project\\.proid").val(data.proid);
                    $("#project\\.title").val(data.title);
                    $("#project\\.note").val(data.note);
                    $("#user\\.userid").empty();
                    $("#documentUpdate").modal("toggle");
                },"json");
        })
    })*/


    $("button[id*='adminBtn-']").each(function () {
        var  proid = this.id.split("-")[1]; //分离出id的信息
        $(this).on("click",function () {
            var url = "/TaskActionEmp!insertPre.action?project.proid="+proid;
            window.location = url
        })
    })

    $("button[id*='taskList-']").each(function () {
        var  proid = this.id.split("-")[1]; //分离出id的信息
        var backUrl = $("#backUrl").val();
        $(this).on("click",function () {
            var parames = new Array();
            parames.push({ name: "backUrl", value: backUrl});
            parames.push({ name: "project.proid", value: proid});
            Post("/TaskActionEmp!list.action", parames);
            // var url = "/TaskActionManager!list.action?project.proid="+proid+"&backUrl=\""+backUrl+"\"";
            // window.location = url
        })
    })


}) ;

function Post(URL, PARAMTERS) {
    //创建form表单
    var temp_form = document.createElement("form");
    temp_form.action = URL;
    //如需打开新窗口，form的target属性要设置为'_blank'
    temp_form.target = "_self";
    temp_form.method = "post";
    temp_form.style.display = "none";
    //添加参数
    for (var item in PARAMTERS) {
        var opt = document.createElement("textarea");
        opt.name = PARAMTERS[item].name;
        opt.value = PARAMTERS[item].value;
        temp_form.appendChild(opt);
    }
    document.body.appendChild(temp_form);
    //提交数据
    temp_form.submit();
}
