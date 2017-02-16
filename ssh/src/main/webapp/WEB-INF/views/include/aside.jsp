<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/12
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cogs"></i> <span>系统设置</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="${param.menu == 'sys_accounts' ? 'active' : ''}">
                        <a href="/user"><i class="fa fa-circle-o"></i> 账户管理</a>
                    </li>
                    <li class="${param.menu == 'sys_device' ? 'active' : ''}">
                        <a href="/setting/device"><i class="fa fa-circle-o"></i> 设备管理</a>
                    </li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>