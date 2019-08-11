/*
 * @Author: lezhu
 * @Date:   2019-01-22
 * +----------------------------------------------------------------------
 * | jqadmin [ jq酷打造的一款懒人后台模板 ]
 * | Copyright (c) 2017 http://jqadmin.jqcool.net All rights reserved.
 * | Licensed ( http://jqadmin.jqcool.net/licenses/ )
 * | Author: Paco <admin@jqcool.net>
 * +----------------------------------------------------------------------
 */
//模块依赖其它模块，如：layui.define('layer', callback) 该模板依赖echarts 该名称在全局变量config中有引用 
layui.define(['echarts'], function (exports) {
    var echarts = layui.echarts,
        $ = layui.jquery;
        
    var fs=[];       //ph
    var tim = [];         //时间

    var myecharts = echarts.init(document.getElementById('echarts'));
    var option = {
        title: {
            text: '动态二氧化碳数据',
            subtext: '纯属虚构'
        },
        tooltip : {
            trigger: 'axis'
        },
        xAxis: {
            type: 'category',
            data: []
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '二氧化碳',
            type: 'line',
            symbol: 'emptycircle',    //设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形       
            data: []
        }]
    };

    $.ajax({
                type: 'post',
                async : true, 
                url: '/admin/wisdom/leer',  //数据传输的控制器方法
                success: function (result) {
                    var myDate = new Date();//获取系统当前时间
                    console.log("二氧化碳时间测试"+myDate);
                    if (result != 1  && result.length > 0) {
                        //判断data数据
                            for (var j = 0; j < result.length; j++) {
                                fs.push(result[j].value);
                                tim.push(result[j].timestamp);
                            }
                    }
                    else {
                        console.log("二氧化碳数据取出失败！");
                    }
                    myecharts.hideLoading();//隐藏加载动画
                    myecharts.setOption({
                        xAxis: {
                            data: tim
                        },
                        series: [
                            {
                                name: '二氧化碳',
                                data: fs
                            }]
                    });
                    console.log("二氧化碳数组长度"+fs.length);
                    console.log("二氧化碳数组：：");
                    console.log(fs);
                },
               
                error: function () {
                    alert("二氧化碳数据加载失败！");
                    myecharts.hideLoading();
                }
            });
            window.setInterval(function(){$.ajax({
                type: 'post',
                async : true, 
                url: '/admin/wisdom/index',  //数据传输的控制器方法
                success: function (result) {
                    var myDate = new Date();//获取系统当前时间
                    console.log("二氧化碳图标时间测试"+myDate);
                    if (result != 1  && result.length > 0) {
                        //判断data数据
                            for (var j = 0; j < result.length; j++) {
                                fs.push(result[j].value);
                                tim.push(result[j].timestamp);
                            }
                    }
                    else {
                        console.log("二氧化碳图标数据取出失败！");
                    }
                    myecharts.hideLoading();//隐藏加载动画
                    myecharts.setOption({
                        xAxis: {
                            data: tim
                        },
                        series: [
                            {
                                name: '二氧化碳',
                                data: fs
                            }]
                    });
                    console.log("二氧化碳数组长度"+fs.length);
                    console.log("二氧化碳数组：：");
                    console.log(fs);
                },
               
                error: function () {
                    alert("二氧化碳数据加载失败！");
                    myecharts.hideLoading();
                }
            }) },55000);
    myecharts.setOption(option);
    exports('echart', {});
});