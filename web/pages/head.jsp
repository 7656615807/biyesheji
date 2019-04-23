<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">		<!-- 定义导航条 -->
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
            <div class="navbar-header">
                <a class="navbar-brand" href="pages/order/index.jsp">小飞侠物流</a>
                <button type="button" class="navbar-toggle btn-success" data-toggle="collapse" data-target="#navbar-menu">
                    <span class="sr-only">切换导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="navbar-menu">
                <ul class="nav navbar-nav">
                    <li><a tabindex="-1" href="pages/order/index.jsp"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
                    <li><a tabindex="-1" href="pages/order/order_list.jsp"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单管理</a></li>
                    <li><a tabindex="-1" href="pages/car/car_list.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;车辆管理</a></li>
                    <li><a tabindex="-1" href="pages/givers/givers_list.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;派件员管理</a></li>
                    <li><a tabindex="-1" href="pages/order/money.jsp"><span class="glyphicon glyphicon-yen"></span>&nbsp;财务管理</a></li>
                    <li><a tabindex="-1" href="opera/zhuxiao.jsp"><span class="glyphicon glyphicon-off"></span>&nbsp;退出登录</a></li>
                </ul>
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" class="form-control"placeholder="请输入查询内容..." id="selectId">
                        <button type="button" class="btn btn-success" id="DeselectBtn">
                            搜索
                        </button>
                    </div>
                </form>
            </div>
        </nav>
    </div>
</div>
<div style="height: 100px">&nbsp;</div>