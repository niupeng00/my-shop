<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>牛朋 | 分类管理</title>
    <jsp:include page="../includes/header.jsp" />
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" />
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
                分类管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i>主页</a></li>
                <li class="active">分类管理</li>
            </ol>
        </section>



        <!-- Main content -->
        <section class="content">

            <!-- /.row -->
            <div class="row">
                <div class="col-xs-12">
                        <div class="alert alert-${baseResult == null?"info":baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            ${baseResult == null?"提示信息用户注意事项":baseResult.message}
                        </div>
                    <form:errors path="*"></form:errors>
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id == null ? "新增" : "编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContentCategory">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="parentId" class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <form:hidden path="parentId" />
                                        <input class="form-control required" id="name" name="name" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">分类名称</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="name"  placeholder="分类名称"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="sortOrder" class="col-sm-2 control-label">分类排序</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required digits" path="sortOrder" placeholder="分类排序"/>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>    
                    </div>
                    <!-- /.box -->
                    <!-- general form elements disabled -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp" />
</div>
<jsp:include page="../includes/footer.jsp" />
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.js"></script>
<!-- 自定义标签 -->
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"/>

<SCRIPT type="text/javascript">

    /*第一种写法
    $(document).ready(function () {
        var url = "../content/category/tree/data";
        var autoParam = ["id"];
        App.initZTree(url, autoParam, node(modes));
    });

    function node(nodes) {
        var node = nodes[0];
        $("#categoryId").val(node.id);
        $("#categoryName").val(node.name);
        $("#modal-default").modal("hide");
    }*/
    //第二种写法
    $(document).ready(function () {
        App.initZTree("/content/category/tree/data", ["id"], function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        })
    });

</SCRIPT>
</body>
</html>
