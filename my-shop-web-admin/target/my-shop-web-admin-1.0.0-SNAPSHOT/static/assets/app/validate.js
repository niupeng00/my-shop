/**
 * 函数对象var Validate = function () {
 *         }(); 两个()所以叫函数对象
 * js的面向对象写法
 * 不直接写function,因为不安全,
 * js讲究闭包特性
 */
var Validate = function () {
    /**
     * 初始化 jquery validation<初始化校验规则>
     * 属于私有方法,jsp页面调用不到私有方法,所以需要创建公共方法
     */
    var handlerInitValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    /**
     * 表单校验
     */
    $(function () {
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    });

    /**
     * 公共方法,页面只能掉用公共方法,私有方法调用不到的
     */
    return {
        init: function () {
            handlerInitValidate();
        }
    }

}();

$(document).ready(function () {
    Validate.init();
});