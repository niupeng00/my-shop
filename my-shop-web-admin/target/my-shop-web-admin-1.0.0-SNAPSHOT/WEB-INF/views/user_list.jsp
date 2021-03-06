<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>牛朋 | 用户管理</title>
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
                用户管理
                <small>用户管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i>主页</a></li>
                <li class="active">用户管理</li>
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
                                        <label for="username" class="col-sm-4 control-label">姓名</label>
                                        <div class="col-sm-8">
                                            <input id="username" name="username" class="form-control" placeholder="姓名..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="email" class="col-sm-4 control-label">邮箱</label>
                                        <div class="col-sm-8">
                                            <input id="email" name="email" class="form-control" placeholder="邮箱..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="phone" class="col-sm-4 control-label">手机</label>
                                        <div class="col-sm-8">
                                            <input id="phone" name="phone" class="form-control" placeholder="手机..."/>
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
                            <h3 class="box-title">用户列表</h3>

                            <div class="box-body">
                                <a href="/user/form" type="button" class="btn btn-default btn-xs"><i class="fa fa-plus"> 新增</i></a>&nbsp;&nbsp;&nbsp;
                                <button type="button" class="btn btn-default btn-xs" onclick="App.deleteMulti('/user/delete')"><i class="fa fa-trash-o"> 删除</i></button>&nbsp;&nbsp;&nbsp;
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
                                        <th>用户名</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                        <th>更新时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%--<c:forEach items="${tbUsers}" var="user">
                                        <tr>
                                            <td><input id="${user.id}" type="checkbox" class="flat-red" /></td>
                                            <td>${user.id}</td>
                                            <td>${user.username}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.email}</td>
                                            <td><fmt:formatDate value="${user.updated}" pattern="yyyy-MM-dd HH:mm:ss" /> </td>
                                            <td>
                                                <a href="#" type="button" class="btn btn-default btn-xs"><i class="fa fa-search"> 查看</i></a>&nbsp;&nbsp;&nbsp;
                                                <a href="#" type="button" class="btn btn-primary btn-xs"><i class="fa fa-edit"> 编辑</i></a>&nbsp;&nbsp;&nbsp;
                                                <a href="#" type="button" class="btn btn-danger  btn-xs"><i class="fa fa-trash-o"> 删除</i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>--%>
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
            { 'data': 'username' },
            { 'data': 'phone' },
            { 'data': 'email' },
            { 'data': 'updated' },
            {
                'data': function (row, type, val, meta) {
                    var detailURL = "/user/detail?id="+row.id;
                    var deleteUrl = "/user/delete";
                    return '<button type="button" class="btn btn-default btn-xs" onclick="App.showDetail(\''+detailURL+'\')"><i class="fa fa-search"> 查看</i></a>&nbsp;&nbsp;&nbsp;'+
                        '<a href="/user/form?id='+row.id+'" type="button" class="btn btn-primary btn-xs" ><i class="fa fa-edit"> 编辑</i></a>&nbsp;&nbsp;&nbsp;'+
                        '<button type="button" class="btn btn-danger  btn-xs"><i class="fa fa-trash-o" onclick="App.deleteSingle(\''+deleteUrl+'\',\''+row.id+'\')"> 删除</i></a>'
                }
            },
        ];
        _dataTable = App.initDataTables("/user/page", _columns);
        /*_dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();*/
    });

    function search() {
        var username = $("#username").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        var param = {
            'username':username,
            'email': email,
            'phone': phone

        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }
</script>
<%--<script>

    //定义一个 ID 的数组
    var idArray = new Array();

    /**
     * 批量删除
     */
    function deleteMulti() {
        //将选中的元素的 ID 放到数组中
        var _checkbox = App.getCheckbox();
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")){
                idArray.push(_id);
            }
        });

        $("#modal-default").modal("show");

        if (idArray.length === 0){
            $("#modal-message").html("您还没有选择任何数据项,至少选择一项!");
        } else {
            $("#modal-message").html("您确定要删除选择的数据项吗?删除后不可恢复!");
        }
        console.log(idArray);
    }

    $(document).ready(function () {
        $("#btnModal").bind("click", function () {
            del(idArray, "/user/delete");
        })
        
        function del(idArray, url) {
            $("#modal-default").modal("hide");
            if (idArray.length > 0){
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        console.log(data);
                    }
                });
            }
        }
    });
</script>--%>
</body>
</html>
