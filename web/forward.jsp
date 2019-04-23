<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path =    request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" +request.getServerPort()
            + path;
%>
<html>
<head>
    <title>协同办公管理系统</title>
</head>
<meta name="viewport" content="width=device-width,initial-scale=1"/>
<body>
<script type="text/javascript">
    alert("${msg}");
    window.location = "<%= basePath%>${url}";
</script>
</body>
</html>
