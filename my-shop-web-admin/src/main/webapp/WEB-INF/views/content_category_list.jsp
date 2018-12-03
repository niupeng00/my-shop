<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>牛朋 | 内容管理</title>
    <jsp:include page="../includes/header.jsp" />
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small>内容管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i>主页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status == 200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}

                        </div>
                    </c:if>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>

                            <div class="box-body">
                                <a href="/content/category/form" type="button" class="btn btn-default btn-xs"><i class="fa fa-plus"> 新增</i></a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-download"> 导入</i></a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-upload"> 导出</i></a>&nbsp;&nbsp;&nbsp;
                            </div>

                            <div class="box-tools">
                                <form:form action="/user/search1" method="post">
                                    <div class="input-group input-group-sm" style="width: 150px;">
                                            <%--<form:input path="keyword" cssClass="form-control pull-right" placeholder="搜索..." />--%>
                                        <input type="text" name="keyword" class="form-control pull-right" placeholder="搜索...">

                                        <div class="input-group-btn">
                                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>名称</th>
                                        <th>排序</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${requestScope.list}" var="tbContentCategory">
                                        <tr id="${tbContentCategory.id}" pId="${tbContentCategory.parent.id}">
                                            <td>${tbContentCategory.id}</td>
                                            <td>${tbContentCategory.name}</td>
                                            <td>${tbContentCategory.sortOrder}</td>
                                            <td>
                                                <a href="/content/category/form?id=${tbContentCategory.id}" type="button" class="btn btn-primary btn-xs" ><i class="fa fa-edit"> 编辑</i></a>&nbsp;&nbsp;&nbsp;
                                                <button type="button" class="btn btn-danger  btn-xs" ><i class="fa fa-trash-o"> 删除</i></button>&nbsp;&nbsp;&nbsp;
                                                <a href="/content/category/form?parent.id=${tbContentCategory.id}&parent.name=${tbContentCategory.name}" type="button" class="btn btn-default  btn-xs" ><i class="fa fa-plus"> 新增下级菜单</i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp" />
</div>

<!-- /.modal -->
<jsp:include page="../includes/footer.jsp" />
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>

<!-- 自定义模态框<弹框> -->
<sys:modal />
<script>
    $(document).ready(function () {
        $("#treeTable").treeTable({
            expandLevel: 2,
            column: 1
        });
    });

</script>
</body>
</html>
