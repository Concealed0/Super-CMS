layui.define('echarts', function(exports) {
    var echarts = layui.echarts,
        $ = layui.jquery;
    var free=[];  //空闲内存
    var used=[];//使用内存
    var tem=[];//时间
    var myecharts = echarts.init(document.getElementById('ledown'));
    var option = {
		title : {
			text : '动态系统JVM监测信息',
			subtext : ''
		},
		tooltip : {
			trigger : 'axis'
		},
	    legend: {
	        data:['初始JVM','已使用JVM'],
	      //  left:'right'
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
            name : '使用/M',
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
            name : '初始/M',
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
			name : '初始JVM',
			type : 'line',
			symbol : 'emptycircle', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		},{
			name : '已使用JVM',
			type : 'line',
		//	yAxisIndex: 1,
			symbol : 'emptyrect', // 设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
			data : []
		}]
	};

	$.ajax({
		type : 'post',
		async : true,
		url : '/SuperCMS/SystemMonitor/jvmInfo', // 数据传输的控制器方法
		success : function(result) {
			var myDate = new Date();// 获取系统当前时间
			console.log("JVM使用度测试" + myDate);
			console.log(result);
			if (result != null) {
				// 判断data数据				
				free.push(result.initjvm);
				used.push(result.usedjvm);
				tem.push(result.tem);
			} else {
				console.log("系统JVM情况 echarts图数据获取失败");
			}
			myecharts.hideLoading();// 隐藏加载动画
			myecharts.setOption({
				xAxis : {
					data : tem
				},
				series : [ {
					name : '初始JVM',
					data : free
				},{
					name : '已使用JVM',
					data : used
				} ]
			});
			console.log(result);
		},

		error : function() {
			alert("系统JVM  echarts图数据获取失败,请查看接口！");
			myecharts.hideLoading();
		}
	});
	window.setInterval(function() {
		$.ajax({
			type : 'post',
			async : true,
			url : '/SuperCMS/SystemMonitor/jvmInfo', // 数据传输的控制器方法
			success : function(result) {
				var myDate = new Date();// 获取系统当前时间
				console.log("JVM使用度测试" + myDate);
				console.log(result);
				if (result != null) {
					// 判断data数据				
					free.push(result.initjvm);
					used.push(result.usedjvm);
					tem.push(result.tem);
				} else {
					console.log("系统JVM情况 echarts图数据获取失败");
				}
				myecharts.hideLoading();// 隐藏加载动画
				myecharts.setOption({
					xAxis : {
						data : tem
					},
					series : [ {
						name : '初始JVM',
						data : free
					},{
						name : '已使用JVM',
						data : used
					} ]
				});
				console.log(result);
			},

			error : function() {
				alert("系统JVM  echarts图数据获取失败,请查看接口！");
				myecharts.hideLoading();
			}
		});
	}, 20000);
	myecharts.setOption(option);
	exports('ledown', {});
});