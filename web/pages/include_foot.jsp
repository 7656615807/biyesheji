<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    function operateAllert(flag,suctext,faltext) {
        if (flag){
            $("#alertDiv").attr("class","alert alert-success");
            $("#alertText").text(suctext);
        }else{
            $("#alertDiv").attr("class","alert alert-danger");
            $("#alertText").text(faltext);
        }
        $("#alertDiv").fadeIn(1000,function () {
            $("#alertDiv").fadeOut(3000) ;
        });
    }
</script>

<div class="alert alert-success" id="alertDiv" style="display: none;">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <span id="alertText"></span>
</div>