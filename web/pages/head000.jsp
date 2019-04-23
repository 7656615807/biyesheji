<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">		<!-- 定义导航条 -->
        <nav class="navbar navbar-default navbar-static-top navbar-inverse" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">小飞侠物流</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a tabindex="-1" href="#"><span class="glyphicon glyphicon-home"></span>&nbsp;首页</a></li>
                    <li><a tabindex="-1" href="pages/order/order_list.jsp"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;订单管理</a></li>
                    <li><a tabindex="-1" href="pages/car/car_list.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;车辆管理</a></li>
                    <li><a tabindex="-1" href="pages/givers/givers_list.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;派件员管理</a></li>
                    <li><a tabindex="-1" href="pages/givers/givers_list.jsp"><span class="glyphicon glyphicon-yen"></span>&nbsp;财务管理</a></li>
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
<div style="height: 60px">&nbsp;</div>