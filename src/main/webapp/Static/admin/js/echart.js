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
layui.define([ 'echarts' ], function(exports) {
	var echarts = layui.echarts, $ = layui.jquery;

	var cSys = []; //cpu系统使用率
	var user=[];   //cpu用户使用率
	var iotwait=[];//cpu当前等待率
	var idle=[];   //cpu当前空闲率
	var tim = []; // 时间

	var myecharts = echarts.init(document.getElementById('echarts'));
	var option = {
		title : {
			text : '动态系统CPU监测信息',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
	    legend: {
	        data:['系统使用率','用户使用率','当前等待率','当前空闲率'],
	        left:'right'
	    },
		 color: ['#FF4949','#FFA74D','#FFEA51','#4BF0FF'],
		xAxis : {
            type: 'category',
            boundaryGap: false,
            data: [],
            splitLine: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#000'
                }
            }
        },   
		yAxis : [{
            type : 'value',
            name : '系统/用户/%',
            splitLine: {
               show: false
           },
            axisLine : {
                lineStyle: {
                   color: '#000'
               }
            }
        },
        {
           //第二个（右边）Y轴，yAxisIndex为1
            type : 'value',
            name : '等待/空闲/%',
            splitLine: {
               show: false
           },
           axisLine : {
                lineStyle: {
                   color: '#000'
               }
            }
        }],
		series : [ {
			name : '系统使用率',
			type : 'line',
			symbol : 'emptycircle', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		},{
			name : '用户使用率',
			type : 'line',
			symbol : 'emptyrect', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		},{
			name : '当前等待率',
			type : 'line',
		//	yAxisIndex: 1,
			symbol : 'circle', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		},{
			name : '当前空闲率',
			type : 'line',
			//yAxisIndex: 1,
			symbol : 'emptydiamond', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		}, ]
	};

	$.ajax({
		type : 'post',
		async : true,
		url : '/SuperCMS/SystemMonitor/SystemCpu', // 数据传输的控制器方法
		success : function(result) {
			var myDate = new Date();// 获取系统当前时间
			console.log("cpu测试" + myDate);
			console.log(result.cSys);
			if (result != null) {
				// 判断data数据				
				cSys.push(result.cSys);
				user.push(result.user);
				iotwait.push(result.iotwait);
				idle.push(result.idle);
				tim.push(result.tem);
			} else {
				console.log("系统CPU echarts图数据获取失败");
			}
			myecharts.hideLoading();// 隐藏加载动画
			myecharts.setOption({
				xAxis : {
					data : tim
				},
				series : [ {
					name : '系统使用率',
					data : cSys
				},{
					name : '用户使用率',
					data : user
				},{
					name : '当前等待率',
					data : iotwait
				},{
					name : '当前空闲率',
					data : idle
				} ]
			});
			console.log(result);
		},

		error : function() {
			alert("系统CPU echarts图数据获取失败,请查看接口！");
			myecharts.hideLoading();
		}
	});
	window.setInterval(function() {
		$.ajax({
			type : 'post',
			async : true,
			url : '/SuperCMS/SystemMonitor/SystemCpu', // 数据传输的控制器方法
			success : function(result) {
				var myDate = new Date();// 获取系统当前时间
				console.log("cpu测试" + myDate);
				console.log(result.cSys);
				console.log(result.length);
				if (result != null) {
					// 判断data数据				
					cSys.push(result.cSys);
					user.push(result.user);
					iotwait.push(result.iotwait);
					idle.push(result.idle);
					tim.push(result.tem);
				} else {
					console.log("系统CPU echarts图数据获取失败");
				}
				myecharts.hideLoading();// 隐藏加载动画
				myecharts.setOption({
					xAxis : {
						data : tim
					},
					series : [ {
						name : '系统使用率',
						data : cSys
					},{
						name : '用户使用率',
						data : user
					},{
						name : '当前等待率',
						data : iotwait
					},{
						name : '当前空闲率',
						data : idle
					} ]
				});
				console.log(result);
			},

			error : function() {
				alert("系统CPU echarts图数据获取失败,请查看接口！");
				myecharts.hideLoading();
			}
		});
	}, 20000);
	myecharts.setOption(option);
	exports('echart', {});
});