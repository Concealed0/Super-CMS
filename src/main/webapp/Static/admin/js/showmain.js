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
    $.ajax({
		type : 'post',
		async : true,
		url : '/SuperCMS/SystemMonitor/SystemInfo', // 数据传输的控制器方法
		success : function(result) {
				document.getElementById('cpu').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.cpu;
                document.getElementById('cpu').appendChild(p);
                
				document.getElementById('maxjvm').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.maxjvm;
                document.getElementById('maxjvm').appendChild(p);
		
				document.getElementById('initjvm').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.initjvm;
                document.getElementById('initjvm').appendChild(p);
			
				document.getElementById('usedjvm').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.usedjvm;
                document.getElementById('usedjvm').appendChild(p);
			
				document.getElementById('total').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.totalThread;
                document.getElementById('total').appendChild(p);
		
                document.getElementById('osname').innerHTML = '';    //清空
                var p = document.createElement('p');
                p.innerHTML=result.osName;
                document.getElementById('osname').appendChild(p);			 
		},
		error : function() {
			alert("show顶端系统信息 数据获取失败,请查看接口！");
		}
	});
    
    
	window.setInterval(function() {
		$.ajax({
			type : 'post',
			async : true,
			url : '/SuperCMS/SystemMonitor/SystemInfo', // 数据传输的控制器方法
			success : function(result) {
					document.getElementById('cpu').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.cpu;
	                document.getElementById('cpu').appendChild(p);
	                
					document.getElementById('maxjvm').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.maxjvm;
	                document.getElementById('maxjvm').appendChild(p);
			
					document.getElementById('initjvm').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.initjvm;
	                document.getElementById('initjvm').appendChild(p);
				
					document.getElementById('usedjvm').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.usedjvm;
	                document.getElementById('usedjvm').appendChild(p);
				
					document.getElementById('total').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.totalThread;
	                document.getElementById('total').appendChild(p);
			
	                document.getElementById('osname').innerHTML = '';    //清空
	                var p = document.createElement('p');
	                p.innerHTML=result.osName;
	                document.getElementById('osname').appendChild(p);			 
			},
			error : function() {
				alert("show顶端系统信息 数据获取失败,请查看接口！");
			}
		});
	}, 60000);
	exports('showmain', {});
});