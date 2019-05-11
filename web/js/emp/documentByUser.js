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
                    $("#doctype\\.dtid").empty();
                    for (var x = 0; x < data.doctypes.length; x ++){
                        var str = "<option value='"+data.doctypes[x].dtid+"' "+(data.doctypes[x].dtid==data.dtid?"selected":"")+">"+data.doctypes[x].title+"</option>";
                        $("#doctype\\.dtid").append(str);
                    }
                    $("#documentUpdate").modal("toggle");
                },"json");
        })
    })



    $("#submitBtn").on("click",function() {
        var x =validateEmpty("document\\.title")
        if (x == 0) {
            return false;
        }
        var title = $("#document\\.title").val();
        var oldFile = $("#oldFile").val();
        var content = $("#document\\.content").val();
        var did = $("#document\\.did").val();
        var dtid = $("#doctype\\.dtid").val();
        var dttitle = $("#doctype\\.dtid").text();
        var formdata = new FormData();
        formdata.append("file",files[0])
        alert(formdata);
        $.post("DocumentActionEmp!update.action",{"document.title":title,"document.did":did,"document.content":content,"oldFile":oldFile,"file":formdata,"doctype.dtid":dtid},
            function (data) {
                $("#title-"+ did).text(name);
                $("#doctype-"+ did).text(dttitle);
                operateAllert(data == "true","文档修改成功！","文档修改失败！");
                $("#documentUpdate").modal("hide");
            },"text");

    }) ;
    $("#document\\.title").on("blur",function() {
        validateEmpty("document\\.title") ;
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
                var url = "/DocumentActionEmp!delete.action?ids="+data;
                window.location = url
            }
        }
    });

    $("#myform_document_update").on("submit",function() {
        return validateEmpty("document\\.title");
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
