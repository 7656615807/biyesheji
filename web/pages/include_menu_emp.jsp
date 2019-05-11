<%@ page language="java" pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">协同办公管理系统</a>
    </div>
    <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
            <li><a tabindex="-1" href="/pages/jsp/emp/emp_index.jsp"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
            <c:if test="${emp != null}">
                <c:forEach items="${emp.role.groupses}" var="gup">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${gup.title}<span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${gup.actions}" var="act">
                                <li><a href="${act.url}">${act.title}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>
            </c:if>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i>${emp.name}
                <span class="glyphicon glyphicon-chevron-down"></span> </a>
                <ul class="dropdown-menu main-list">
                    <li><a href="/pages/jsp/emp/emp_password_edit.jsp"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
                    <li><a href="/EmpUpdateAction!updatePre.action"><i class="glyphicon glyphicon-cd">个人资料</i></a></li>
                    <li class="divider"></li>
                    <li><a href="UserLogout!logout.action"><i class="glyphicon glyphicon-off"></i>注销</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>