/*
 * @Author: lezhu
 * @Date:   2019-01-22
 * +----------------------------------------------------------------------
 * 
 * +----------------------------------------------------------------------
 */
layui.define('echarts', function(exports) {
    var echarts = layui.echarts,
        $ = layui.jquery;
    var free=[];  //空闲内存
    var used=[];//使用内存
    var tem=[];//时间
    
    var myecharts = echarts.init(document.getElementById('le_show'));
    var option = {
		title : {
			text : '动态系统物理内存8G监测信息',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
	    legend: {
	        data:['内存使用率','当前空闲率'],
	        bottom:'bottom'
	    },
		color: ['#FF4949','#FFA74D']
		,
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
            name : '使用/%',
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
            name : '空闲/%',
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
			name : '内存使用率',
			type : 'line',
			symbol : 'emptycircle', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		},{
			name : '当前空闲率',
			type : 'line',
		//	yAxisIndex: 1,
			symbol : 'emptyrect', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		}]
	};

	$.ajax({
		type : 'post',
		async : true,
		url : '/SuperCMS/SystemMonitor/SystemMemory', // 数据传输的控制器方法
		success : function(result) {
			var myDate = new Date();// 获取系统当前时间
			console.log("物理内存测试" + myDate);
			console.log(result);
			if (result != null) {
				// 判断data数据				
				free.push(result.free);
				used.push(result.used);
				tem.push(result.tem);
			} else {
				console.log("系统物理内存 echarts图数据获取失败");
			}
			myecharts.hideLoading();// 隐藏加载动画
			myecharts.setOption({
				xAxis : {
					data : tem
				},
				series : [ {
					name : '内存使用率',
					data : used
				},{
					name : '当前空闲率',
					data : free
				} ]
			});
			console.log(result);
		},

		error : function() {
			alert("系统物理内存 echarts图数据获取失败,请查看接口！");
			myecharts.hideLoading();
		}
	});
	window.setInterval(function() {
		$.ajax({
			type : 'post',
			async : true,
			url : '/SuperCMS/SystemMonitor/SystemMemory', // 数据传输的控制器方法
			success : function(result) {
				var myDate = new Date();// 获取系统当前时间
				console.log("物理内存测试" + myDate);
				console.log(result);
				if (result != null) {
					// 判断data数据				
					free.push(result.free);
					used.push(result.used);
					tem.push(result.tem);
				} else {
					console.log("系统物理内存 echarts图数据获取失败");
				}
				myecharts.hideLoading();// 隐藏加载动画
				myecharts.setOption({
					xAxis : {
						data : tem
					},
					series : [ {
						name : '内存使用率',
						data : used
					},{
						name : '当前空闲率',
						data : free
					} ]
				});
				console.log(result);
			},

			error : function() {
				alert("系统物理内存 echarts图数据获取失败,请查看接口！");
				myecharts.hideLoading();
			}
		});
	}, 20000);
	myecharts.setOption(option);
	exports('le', {});
});