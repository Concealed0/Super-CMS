/*
 * @Author: lezhu
 * @Date:   2019-01-23
 * +----------------------------------------------------------------------
 * 
 * +----------------------------------------------------------------------
 */
layui.define(function (exports) {
  function lesubmit() {
        var name = document.lechange.name.value;
        var pwd1 = document.lechange.pwd1.value;
        var pwd2 = document.lechange.pwd2.value;
        var role = document.lechange.role.value;
        var title = document.lechange.title.value;
        var sex = document.lechange.sex.value;
        var tel = document.lechange.tel.value;
        var email = document.lechange.email.value;
        var status = document.lechange.switch.value;
        if (name == null || pwd1 == null || pwd2 == null) {
            alert('sdfad');
        }
        else
            document.lechange.submeit();
    }

    //输出test接口
    exports('lechange', {});
});    