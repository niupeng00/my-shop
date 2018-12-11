/**
 * 函数对象
 */
var App = function () {

    /**
     * 私有属性
     */
    //iCheck
    var _masterCheckbox;
    var _checkbox;

    //用于存放 ID 的数组
    var _idArray;

    /**
     * 默认的 Dropzone 参数
     * @type {{}}
     */
    var defaultDropzoneOpts = {
        url: "",
        paramName: "dropzFile", // 传到后台的参数名称
        method: "post",  // 也可用put
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 10, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" +this.maxFiles+ "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };
    /**
     * 私有方法,初始化 ICheck
     */
    var handlerCheckbox = function () {
        //此插件需要激活,才可使用
        $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
            checkboxClass: 'icheckbox_flat-green',
            radioClass   : 'iradio_flat-green'
        });

        /**
         * 获取控制端 Checkbox
         */
        _masterCheckbox =  $('input[type="checkbox"].flat-red.icheck_master');

        /**
         * 获取全部 Checkbox 集合
         */
        _checkbox = $('input[type="checkbox"].flat-red');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            //返回true 表示未选中
            if (e.target.checked){
                _checkbox.iCheck("uncheck");
            } else { //false表示选中
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 删除一条数据
     * @param url 删除链接
     * @param id  删除数据的 ID
     * @param msg
     */
    var handlerDeleteSingle = function (url, id, msg) {
        //可选参数
        if (!msg) msg = null;
        //将 ID 放入数组中, 以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null ? "您确定要删除数据项吗?" : msg)
        $("#modal-default").modal("show");
        //绑定删除事件
        $("#btnModal").bind("click", function () {
            handlerDeleteData(url);
        });
    }
    /**
     * 批量删除
     * @param url
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        //将选中的元素的 ID 放到数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项,至少选择一项!");
        } else {
            $("#modal-message").html("您确定要删除选择的数据项吗?删除后不可恢复!");
        }

        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项则调用删除方法
        $("#btnModal").bind("click", function () {
            handlerDeleteData();
        })
    };
    /**
     * AJAX 异步删除
     * @param url
     */
    function handlerDeleteData() {
        $("#modal-default").modal("hide");
        if (_idArray.length > 0){
            setTimeout(function () {
                // AJAX 异步删除操作
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        //请求成功后, 无论是成功还是失败都需要弹出模态框进行提示, 所以这里需要先解绑原来的 click 事件
                        $("#btnModal").unbind("click");
                        //删除成功
                        if (data.status === 200) {
                            //刷新页面
                            $("#btnModal").bind("click", function () {
                                window.location.reload();
                            });
                        }
                        //删除失败
                        else {
                            //确定无论如何都需要提示信息, 所以这里的模态框是必须调用的
                            $("#btnModal").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        //确定无论如何都需要提示信息, 所以这里的模态框是必须调用的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    },
                });
            }, 500)
        }
    }

    /*function dataMessage(data) {
        $("#btnModal").unbind("click");

        $("#modal-message").html(data.message);
        $("#modal-default").modal("show");

        $("#btnModal").bind("click", function () {
            $("#modal-default").modal("hide");
        })
    }*/

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        //通过 Ajax 请求 html 的方式将 jsp 装载进模态框中
        $.ajax({
            url: url,
            type: "GET",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    /**
     * 初始化 DataTables
     */
    var handerInitDataTables = function (url,columns) {
        var _dataTable = $("#dataTable").DataTable({
            'paging': true,
            'autoWidth': false,
            'info': true,
            'ordering': false,
            'lengthChange': false,
            'searching': false,
            'ordering': false,
            'processing': true,
            'serverSide': true,
            'deferRender': true,
            'ajax': {
                'url': url,
                /*'data': {
                    'username': 'test01'
                },*/
            },
            'columns': columns,
            'language': {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handlerCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 初始化zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },

            async: {
                enable: true,
                url: url,
                autoParam: autoParam,
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModal").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            } else {
                callback(nodes);
            }
        });
    };

    /**
     * 初始化 Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        //关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false;
        // defaultDropzoneOpts 继承 opts , defaultDropzoneOpts 拥有 opts 动态的所有属性,也包括自己本身的属性, 此方法是 jquery 的数组继承
        $.extend(defaultDropzoneOpts, opts);
        var myDropzone = new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
        /*{
            init: function () {
                this.on("success", function (file, data) {
                    // 上传成功触发的事件
                    defaultDropzoneOpts.success(file, data);
                });
                /!*this.on("addedfile", function (file) {
                    // 上传文件时触发的事件
                    $("#pic").val(data.fileName);
                });
                this.on("success", function (file, data) {
                    // 上传成功触发的事件
                });
                this.on("error", function (file, data) {
                    // 上传失败触发的事件
                });
                this.on("removedfile", function (file) {
                    // 删除文件时触发的方法
                });*!/
            }
        });*/
    };
    return {
        /**
         * 初始化
         */
        init: function () {
            handlerCheckbox();
            handlerCheckboxAll();
        },
        /**
         *  getCheckbox 页面需要_checkbox集合的值, _checkbox属于私有属性,jsp页面调取不到.需重新构造公共 开发的方法,把 _checkbox返回
         * @returns {*}
         */
        getCheckbox: function () {
            return _checkbox;
        },

        /**
         * 删除一条信息
         * @param url
         * @param id
         */
        deleteSingle: function (url, id, msg) {
            handlerDeleteSingle(url, id ,msg);
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 查看详情
         * @param url
         */
        showDetail: function (url) {
            handlerShowDetail(url)
        },

        /**
         * 初始化DataTables
         * @param url
         * @param columns
         * @returns {*|jQuery}
         */
        initDataTables: function (url, columns) {
            return handerInitDataTables(url, columns);
        },

        /**
         * 初始化zTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback) {
            handlerInitZTree(url, autoParam, callback);
        },

        /**
         * 初始化 Dropzone
         * @param opts
         */
        initDropzone: function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();

$(document).ready(function () {
    App.init();
});


/**
 * 以前方法 可以使用
 */
/*var handlerDeleteMulti = function (url) {
    _idArray = new Array();

    //将选中的元素的 ID 放到数组中
    _checkbox.each(function () {
        var _id = $(this).attr("id");
        if (_id != null && _id != "undefine" && $(this).is(":checked")) {
            _idArray.push(_id);
        }
    });

    $("#modal-default").modal("show");

    //判断用户是否选择了数据项
    if (_idArray.length === 0) {
        $("#modal-message").html("您还没有选择任何数据项,至少选择一项!");
    } else {
        $("#modal-message").html("您确定要删除选择的数据项吗?删除后不可恢复!");
    }

    //绑定"确定"按钮事件
    $("#btnModal").bind("click", function () {
        handlerDeleteData();
    })

    /!**
     * 当前私有函数的私有函数,只能当前私有函数可以调用
     * 作用:删除数据
     *!/
    function handlerDeleteData() {
        $("#modal-default").modal("hide");
        if (_idArray.length === 0){
            //.........
            /!* setTimeout(function () {
                 $.ajax({
                     "url": url,
                     "type": "POST",
                     "data": {"ids": _idArray.toString()},
                     "dataType": "JSON",
                     "success": function (data) {
                         //请求成功后, 无论是成功还是失败都需要弹出模态框进行提示, 所以这里需要先解绑原来的 click 事件
                         $("#btnModal").unbind("click");
                         //删除成功
                         if (data.status === 200){
                             //刷新页面
                             $("#btnModal").bind("click", function () {
                                 window.location.reload();
                             });
                         }
                         //删除失败
                         else {
                             //确定无论如何都需要提示信息, 所以这里的模态框是必须调用的
                             $("#btnModal").bind("click", function () {
                                 $("#modal-default").modal("hide");
                             });
                         }
                         //确定无论如何都需要提示信息, 所以这里的模态框是必须调用的
                         $("#modal-message").html(data.message);
                         $("#modal-default").modal("show");
                     },
                 });
             },500);*!/

        } else {
            //如果这里有毛病请使用上面的方法
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        //删除成功
                        if (data.status === 200){
                            dataMessage(data);
                            setTimeout(function () {
                                window.location.reload();
                            },500)
                        }
                        //删除失败
                        else {
                            dataMessage(data);
                        }
                    },
                });
            },500);

        }
    }

    function dataMessage(data) {
        $("#btnModal").unbind("click");

        $("#modal-message").html(data.message);
        $("#modal-default").modal("show");

        $("#btnModal").bind("click", function () {
            $("#modal-default").modal("hide");
        })
    }
};*/
