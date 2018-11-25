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
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/basic.css" />
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" />
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
                <small></small>
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
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id" />
                                        <input class="form-control required" id="categoryName" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default" value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="title" placeholder="标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="subTitle" placeholder="子标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="titleDesc" placeholder="标题描述"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="url" placeholder="链接"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="pic" placeholder="图片1"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>
                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="pic2" placeholder="图片2"/>
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">详情</label>
                                    <div class="col-sm-10">
                                        <form:hidden path="content" />
                                        <div id="editor">${tbContent.content}</div>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right" id="btnSubmit">提交</button>
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
<script src="/static/assets/plugins/dropzone/dropzone.js"></script>
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
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
        });
        //初始化富文本编辑器
        initWangEditor();
    });

    function initWangEditor () {
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload';
        //上传图片时,自定义 filename
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();
        $("#btnSubmit").bind("click", function () {
            var contentHtml = editor.txt.html();
            $("#content").val(contentHtml);
        });
    }

    //不可以放到 jquery 中
    App.initDropzone({
        id: "#dropz",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName);
            });
        }
    });

    //不可以放到 jquery 中
    App.initDropzone({
        id: "#dropz2",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic2").val(data.fileName);
            });
        }
    });
    /*Dropzone.options.dropz = {
        url: "upload",
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        paramName: "dropzFile", // 传到后台的参数名称
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $("#pic").val(data.fileName);
            });
        }
    }*/



</SCRIPT>
</body>
</html>
