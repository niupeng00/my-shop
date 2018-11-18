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
                <li class="active">内容管理</li>
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

                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>

                        <div class="box-body">
                            <div class="row form-horizontal">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-4 control-label">标题</label>
                                        <div class="col-sm-8">
                                            <input id="title" name="title" class="form-control" placeholder="标题..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="subTitle" class="col-sm-4 control-label">子标题</label>
                                        <div class="col-sm-8">
                                            <input id="subTitle" name="subTitle" class="form-control" placeholder="子标题..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>
                                        <div class="col-sm-8">
                                            <input id="titleDesc" name="titleDesc" class="form-control" placeholder="标题描述..."/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="button" class="btn btn-info pull-right" onclick="search();">搜索</button>
                        </div>
                        <!-- /.box-footer -->

                    </div>

                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>

                            <div class="box-body">
                                <a href="/content/form" type="button" class="btn btn-default btn-xs"><i class="fa fa-plus"> 新增</i></a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default btn-xs" onclick="App.deleteMulti('/content/delete')"><i class="fa fa-trash-o"> 删除</i></button>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-download"> 导入</i></a>&nbsp;&nbsp;&nbsp;
                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-upload"> 导出</i></a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-primary btn-xs" onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"><i class="fa fa-search"> 搜索</i></button>
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
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="flat-red icheck_master" /></th>
                                    <th>ID</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>

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

<!-- 自定义模态框<弹框> -->
<sys:modal />
<script>

    var _dataTable;
    $(document).ready(function () {
        var _columns = [
            {
                'data': function (row, type, val, meta) {
                    return '<input id="'+row.id+'" type="checkbox" class="flat-red" />';
                }
            },
            { 'data': 'id' },
            { 'data': 'title' },
            { 'data': 'subTitle' },
            { 'data': 'titleDesc' },
            { 'data': function (row, type, val, meta){
                    if (row.url == null) {
                        return "";
                    }
                    return '<a href="'+row.url+'" target="_blank">查看</a>';
                }},
            { 'data': function (row, type, val, meta) {
                    if (row.pic == null) {
                        return "";
                    }
                    return '<a href="'+row.pic+'" target="_blank">查看</a>';
                }},
            { 'data': function (row, type, val, meta) {
                    if (row.pic2 == null) {
                        return "";
                    }
                    return '<a href="'+row.pic2+'" target="_blank">查看</a>';
                }},
            { 'data': 'updated' },
            {
                'data': function (row, type, val, meta) {
                    var detailURL = "/content/detail?id="+row.id;
                    return '<button type="button" class="btn btn-default btn-xs" onclick="App.showDetail(\''+detailURL+'\')"><i class="fa fa-search"> 查看</i></a>&nbsp;&nbsp;&nbsp;'+
                        '<a href="/content/form?id='+row.id+'" type="button" class="btn btn-primary btn-xs" ><i class="fa fa-edit"> 编辑</i></a>&nbsp;&nbsp;&nbsp;'+
                        '<button type="button" class="btn btn-danger  btn-xs"><i class="fa fa-trash-o" onclick="App.deleteMulti(\'/content/delete\')"> 删除</i></a>'
                }
            },
        ];
        _dataTable = App.initDataTables("/content/page", _columns);
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    });

    function search() {
        var title = $("#title").val();
        var subTitle= $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();
        var param = {
            'title':title,
            'subTitle': subTitle,
            'titleDesc': titleDesc

        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>

</body>
</html>
