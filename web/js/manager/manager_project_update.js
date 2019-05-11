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
            var url = "/TaskActionManager!insertPre.action?project.proid="+proid;
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
            Post("/TaskActionManager!list.action", parames);
            // var url = "/TaskActionManager!list.action?project.proid="+proid+"&backUrl=\""+backUrl+"\"";
            // window.location = url
        })
    })


    $("#submitBtn").on("click",function() {
        var x =validateEmpty("project\\.title")
        if (x == 0) {
            return false;
        }
        var title = $("#project\\.title").val();
        var content = $("#project\\.note").val();
        var proid = $("#project\\.proid").val();
        var userid = $("#user\\.userid").val();
        var name = $("#user\\.userid").find("option:selected").text();
        console.log(title+"--"+content+"--"+proid+"--"+userid+"--"+name);
        $.post("ProjectActionAdmin!update.action",{"project.title":title,"project.proid":proid,"project.note":content,"user.userid":userid,"user.name":name},
            function (data) {
                $("#title-"+ proid).text(name);
                $("#mgrName-"+ proid).text(name);
                $("#mgrHref-"+ proid).attr("href","/UserActionAdmin!show.action?user.userid="+userid);
                operateAllert(data == "true","项目修改成功！","项目修改失败！");

            },"text");

    }) ;
    $("#project\\.title").on("blur",function() {
        validateEmpty("project\\.title") ;
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
                var file = $("#updateBtn-"+this.value).attr('href');
                data =data+this.value+":"+file+ "," ;
            }
        });
        if(data == ""){ //现在没有选择要删除的内容
            alert("您还没有选择任何的数据，请先选择好数据！");
        }else {
            if(window.confirm("你确定删除这些文档吗？")){
                var url = "/DocumentActionAdmin!delete.action?ids="+data;
                window.location = url
            }
        }
    });

    /*$("#myform_docu").on("submit",function() {
        return validateEmpty("project\\.title");
    }) ;*/
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
