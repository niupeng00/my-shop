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

    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        //将选中的元素的 ID 放到数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        $("#modal-default").modal("show");

        if (_idArray.length === 0){
            $("#modal-message").html("您还没有选择任何数据项,至少选择一项!");
        } else {
            $("#modal-message").html("您确定要删除选择的数据项吗?删除后不可恢复!");
        }

        //绑定"确定"按钮事件
        $("#btnModal").bind("click", function () {
            del();
        })

        /**
         * 当前私有函数的私有函数,只能当前私有函数可以调用
         * 作用:删除数据
         */
        function del() {
            $("#modal-default").modal("hide");
            if (_idArray.length === 0){
                //.........

            } else {
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
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
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

    return {
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

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        showDetail: function (url) {
            handlerShowDetail(url)
        },

        initDataTables: function (url, columns) {
            return handerInitDataTables(url, columns);
        }
    }
}();

$(document).ready(function () {
    App.init();
});
