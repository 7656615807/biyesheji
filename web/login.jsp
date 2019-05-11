<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName()+":"+request.getServerPort()+path+"/";
    String insertUrl = basePath+"UserLogin!login.action";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>登录</title>
    <meta charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="container">
    <div style="height: 50px"></div>
    <form class="form-horizontal" action="<%=insertUrl%>" id="myform" method="post">
        <fieldset>
            <legend><label><span ></span>协同办公系统</label></legend>
            <div style="height: 80px"><h4><p class="text-center">${msg}</p> </h4></div>
            <div class="form-group" id="manageracountDiv">
                <label class="col-md-4 control-label" for="manageracount">账号:</label>
                <div class="col-md-4">
                    <input type="text" id="manageracount" name="user.userid" class="form-control" placeholder="请输入账号">
                </div>
                <div class="col-md-4" id="manageracountSpan">${fieldErrors['user.userid'][0]}</div>
            </div>
            <div class="form-group" id="managerpasswordDiv">
                <label class="col-md-4 control-label" for="managerpassword">密码:</label>
                <div class="col-md-4">
                    <input type="password" id="managerpassword" name="user.password" class="form-control" placeholder="请输入密码">
                </div>
                <div class="col-md-4" id="managerpasswordSpan">${fieldErrors['user.password'][0]}</div>
            </div>
            <div class="form-group">
                <div class="col-md-5 col-md-offset-5">
                    <button type="submit" class="btn btn-md btn-primary">登录</button>
                </div>
            </div>
        </fieldset>
    </form>

</div>
</body>
</html>
