<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-inline">
    <div class="form-group">
        <label for="col">列名</label>
        <select id="col" class="form-control" name="col">
        </select>
    </div>
    <div class="form-group">
        <label for="kw">关键字</label>
        <input type="text" class="form-control" id="kw" placeholder="请输入搜索内容">
    </div>
    <input type="hidden" value="${url}" id="searchUrl">
    <button type="button" class="btn btn-success" onclick="searchKw()">搜索</button>
</form>
<script>
    var defaultCol = '<%=request.getAttribute("column")%>';
    var columnData = '<%=request.getAttribute("columnData")%>';
    var columnDatas = columnData.split("|");
    for (var i = 0 ; i < columnDatas.length; i ++){
        var cotents = columnDatas[i].split(":");
        var str = "<option value='" +cotents[1]+"' "+(cotents[1]==defaultCol?"selected":"")+">"+cotents[0]+"</option>";
        $("#col").append(str);
    }
    function searchKw() {
        var col = $("#col").val();
        var kw = $("#kw").val();
        var url = $("#searchUrl").val();
        console.log("col="+col+",kw="+kw+",url="+url);
        window.location = url + "?col="+col+"&kw="+kw;
    }
</script>