/*
 * @Author: lezhu
 * @Date:   2019-01-22
 * +----------------------------------------------------------------------
 * 
 * +----------------------------------------------------------------------
 */
layui.define(function(exports) {
        $ = layui.jquery;
        console.log("测试数据！");
        $.ajax({
            type: 'post',
            async : true, 
            url: '/admin/lehome/index',  //数据传输的控制器方法
            success: function (result) {
                var myDate = new Date();//获取系统当前时间
                console.log("----"+myDate);
                console.log("测试数据！----");
                console.log(result);
                console.log(result.length);
                console.log(result[0]);
                if (result != 1 && result.length > 0) {
                        for (var j = 0; j < result.length; j++) {
                            switch(result[j].device_pid){
                                case 24262647:    //平度A0气象站 温湿度光照度百叶窗
                                    if(result[j].property=="温度"){
                                        document.getElementById('lepkw').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"℃";
                                        document.getElementById('lepkw').appendChild(p);
                                        break;
                                    }else if(result[j].property=="湿度"){
                                        document.getElementById('lepks').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"%";
                                        document.getElementById('lepks').appendChild(p);
                                        break;
                                    }else{
                                        document.getElementById('lepgz').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"nm";
                                        document.getElementById('lepgz').appendChild(p);
                                        break;
                                    }
                                case 24602158:      //平度A0气象站 风速传感器
                                        document.getElementById('lepfs').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"m/s";
                                        document.getElementById('lepfs').appendChild(p);
                                        break;
                                case 81815068:      //平度A0气象站 二氧化碳传感器
                                        document.getElementById('lep2c').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"ppm";
                                        document.getElementById('lep2c').appendChild(p);
                                        break;
                                case 28144161:      //平度A0气象站 雨量计
                                        document.getElementById('lepyl').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"mm";
                                        document.getElementById('lepyl').appendChild(p);
                                        break;
                                case 30969455:       //平度A0 雨雪传感器加热型
                                        document.getElementById('lepyx').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"mm";
                                        document.getElementById('lepyx').appendChild(p);
                                        break;
                                case 30639194:           //平度A0 风向传感器
                                       var a;
                                       if(result[j].value==0){
                                           a='北';
                                       }
                                       else if(0<result[j].value<90){
                                           a='东北';
                                       }else if(result[j].value==90){
                                           a='东';
                                       }else if(90<result[j].value<180){
                                            a='东南';
                                       }else if(result[j].value==180){
                                            a='南';
                                       }else if(180<result[j].value<270){
                                            a='西南';
                                       }else if(result[j].value==270){
                                            a='西';
                                       }else if(270<result[j].value<360){
                                            a='西北';
                                       }
                                        document.getElementById('lepfx').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=a+"风";
                                        document.getElementById('lepfx').appendChild(p);
                                        break;
                                // case 37562632:       //平度A1 PH值传感器
                                //         document.getElementById('lepph').innerHTML = '';    //清空
                                //         var p = document.createElement('p');
                                //         p.innerHTML=result[j].value+"ph";
                                //         document.getElementById('lepph').appendChild(p);
                                //         break;
                                case 43683079:    //平度A1土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('leptw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('leptw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lepts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lepts').appendChild(p);
                                            break;
                                        }
                                case 59832192:    //平度A1土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep1tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep1tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep1ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep1ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep1dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep1dd').appendChild(p);
                                            break;
                                        }
                                case 71714686:          //平度A1 土壤盐分传感器
                                        document.getElementById('lep1yf').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"%";
                                        document.getElementById('lep1yf').appendChild(p);
                                        break;
                                case 68227322:    //平度A2土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep2tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep2tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep2ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep2ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep2dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep2dd').appendChild(p);
                                            break;
                                        }
                                case 79555602:    //平度A2土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep2tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep2tw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep2ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep2ts').appendChild(p);
                                            break;
                                        }
                                case 75761210:    //平度A3土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep3tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep3tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep3ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep3ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep3dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep3dd').appendChild(p);
                                            break;
                                        }
                                case 17679092:    //平度A3土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep3tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep3tw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep3ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep3ts').appendChild(p);
                                            break;
                                        }
                             
        
        
                                case 66903838:    //威海A0温湿度光照度百叶窗
                                        if(result[j].property=="温度"){
                                            document.getElementById('lewkw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lewkw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="湿度"){
                                            document.getElementById('lewks').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lewks').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lewgz').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"nm";
                                            document.getElementById('lewgz').appendChild(p);
                                            break;
                                        }
                                    case 21588177:  //威海A0 风速传感器
                                            document.getElementById('lewfs').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"m/s";
                                            document.getElementById('lewfs').appendChild(p);
                                            break;
                                    case 64424249:  //威海A0 二氧化碳传感器
                                            document.getElementById('lew2c').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ppm";
                                            document.getElementById('lew2c').appendChild(p);
                                            break;
                                    case 62638685:       //威海A0      雨量计
                                            document.getElementById('lewyl').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"mm";
                                            document.getElementById('lewyl').appendChild(p);
                                            break;
                                    case 80906243:            //威海A0 雨雪传感器加热型
                                            document.getElementById('lewyx').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"mm";
                                            document.getElementById('lewyx').appendChild(p);
                                            break;
                                    case 96735299:        //威海A0风向传感器
                                             var a;
                                       if(result[j].value==0){
                                           a='北';
                                       }
                                       else if(0<result[j].value<90){
                                           a='东北';
                                       }else if(result[j].value==90){
                                           a='东';
                                       }else if(90<result[j].value<180){
                                            a='东南';
                                       }else if(result[j].value==180){
                                            a='南';
                                       }else if(180<result[j].value<270){
                                            a='西南';
                                       }else if(result[j].value==270){
                                            a='西';
                                       }else if(270<result[j].value<360){
                                            a='西北';
                                       }
                                            document.getElementById('lewfx').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=a+"风";
                                            document.getElementById('lewfx').appendChild(p);
                                            break;
                                    case 82582446:     //威海A1 土壤温湿度电导率3合1
                                            if(result[j].property=="土壤温度"){
                                                document.getElementById('lewtw').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"℃";
                                                document.getElementById('lewtw').appendChild(p);
                                                break;
                                            }else if(result[j].property=="土壤湿度"){
                                                document.getElementById('lewts').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"%";
                                                document.getElementById('lewts').appendChild(p);
                                                break;
                                            }else{
                                                document.getElementById('lewdd').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"ph";
                                                document.getElementById('lewdd').appendChild(p);
                                                break;
                                            }
                                    case 92924446:        //威海A1土壤盐分传感器
                                            document.getElementById('lewyf').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lewyf').appendChild(p);
                                            break;
                                    // case 27102994:        //威海A1土壤ph传感器
                                    //         document.getElementById('lewph').innerHTML = '';    //清空
                                    //         var p = document.createElement('p');
                                    //         p.innerHTML=result[j].value+"ph";
                                    //         document.getElementById('lewph').appendChild(p);
                                    //         break;
                                    case 90594147:    //威海A1土壤温湿度
                                            if(result[j].property=="土壤温度"){
                                                document.getElementById('lew1tw').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"℃";
                                                document.getElementById('lew1tw').appendChild(p);
                                                break;
                                            }else{
                                                document.getElementById('lew1ts').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"%";
                                                document.getElementById('lew1ts').appendChild(p);
                                                break;
                                            }
                                default:
                                        console.log("lehome实时监测模块数据读取其他\n");
                            }
                        }
                }
                else {
                    console.log("此时没有数据传入！");
                }
               
                console.log("------------\n");
            },
           
            error: function () {
                console.log("页面实时监测数据加载失败！");
            }
        });

        window.setInterval(function(){$.ajax({
            type: 'post',
            async : true, 
            url: '/admin/lehome/index',  //数据传输的控制器方法
            success: function (result) {
                var myDate = new Date();//获取系统当前时间
                console.log("----"+myDate);
                console.log("轮询测试数据！----");
                console.log(result);
                console.log(result.length);
                console.log(result[0]);
                if (result != 1 && result.length > 0) {
                        for (var j = 0; j < result.length; j++) {
                            switch(result[j].device_pid){
                                case 24262647:    //平度A0气象站 温湿度光照度百叶窗
                                    if(result[j].property=="温度"){
                                        document.getElementById('lepkw').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"℃";
                                        document.getElementById('lepkw').appendChild(p);
                                        break;
                                    }else if(result[j].property=="湿度"){
                                        document.getElementById('lepks').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"%";
                                        document.getElementById('lepks').appendChild(p);
                                        break;
                                    }else{
                                        document.getElementById('lepgz').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"nm";
                                        document.getElementById('lepgz').appendChild(p);
                                        break;
                                    }
                                case 24602158:      //平度A0气象站 风速传感器
                                        document.getElementById('lepfs').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"m/s";
                                        document.getElementById('lepfs').appendChild(p);
                                        break;
                                case 81815068:      //平度A0气象站 二氧化碳传感器
                                        document.getElementById('lep2c').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"ppm";
                                        document.getElementById('lep2c').appendChild(p);
                                        break;
                                case 28144161:      //平度A0气象站 雨量计
                                        document.getElementById('lepyl').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"mm";
                                        document.getElementById('lepyl').appendChild(p);
                                        break;
                                case 30969455:       //平度A0 雨雪传感器加热型
                                        document.getElementById('lepyx').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"mm";
                                        document.getElementById('lepyx').appendChild(p);
                                        break;
                                case 30639194:           //平度A0 风向传感器
                                       var a;
                                       if(result[j].value==0){
                                           a='北';
                                       }
                                       else if(0<result[j].value<90){
                                           a='东北';
                                       }else if(result[j].value==90){
                                           a='东';
                                       }else if(90<result[j].value<180){
                                            a='东南';
                                       }else if(result[j].value==180){
                                            a='南';
                                       }else if(180<result[j].value<270){
                                            a='西南';
                                       }else if(result[j].value==270){
                                            a='西';
                                       }else if(270<result[j].value<360){
                                            a='西北';
                                       }
                                        document.getElementById('lepfx').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=a+"风";
                                        document.getElementById('lepfx').appendChild(p);
                                        break;
                                // case 37562632:       //平度A1 PH值传感器
                                //         document.getElementById('lepph').innerHTML = '';    //清空
                                //         var p = document.createElement('p');
                                //         p.innerHTML=result[j].value+"ph";
                                //         document.getElementById('lepph').appendChild(p);
                                //         break;
                                case 43683079:    //平度A1土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('leptw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('leptw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lepts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lepts').appendChild(p);
                                            break;
                                        }
                                case 59832192:    //平度A1土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep1tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep1tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep1ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep1ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep1dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep1dd').appendChild(p);
                                            break;
                                        }
                                case 71714686:          //平度A1 土壤盐分传感器
                                        document.getElementById('lep1yf').innerHTML = '';    //清空
                                        var p = document.createElement('p');
                                        p.innerHTML=result[j].value+"%";
                                        document.getElementById('lep1yf').appendChild(p);
                                        break;
                                case 68227322:    //平度A2土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep2tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep2tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep2ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep2ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep2dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep2dd').appendChild(p);
                                            break;
                                        }
                                case 79555602:    //平度A2土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep2tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep2tw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep2ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep2ts').appendChild(p);
                                            break;
                                        }
                                case 75761210:    //平度A3土壤温湿度电导率3合1
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep3tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep3tw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="土壤湿度"){
                                            document.getElementById('lep3ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep3ts').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep3dd').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ph";
                                            document.getElementById('lep3dd').appendChild(p);
                                            break;
                                        }
                                case 17679092:    //平度A3土壤温湿度
                                        if(result[j].property=="土壤温度"){
                                            document.getElementById('lep3tw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lep3tw').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lep3ts').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lep3ts').appendChild(p);
                                            break;
                                        }
                             
        
        
                                case 66903838:    //威海A0温湿度光照度百叶窗
                                        if(result[j].property=="温度"){
                                            document.getElementById('lewkw').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"℃";
                                            document.getElementById('lewkw').appendChild(p);
                                            break;
                                        }else if(result[j].property=="湿度"){
                                            document.getElementById('lewks').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lewks').appendChild(p);
                                            break;
                                        }else{
                                            document.getElementById('lewgz').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"nm";
                                            document.getElementById('lewgz').appendChild(p);
                                            break;
                                        }
                                    case 21588177:  //威海A0 风速传感器
                                            document.getElementById('lewfs').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"m/s";
                                            document.getElementById('lewfs').appendChild(p);
                                            break;
                                    case 64424249:  //威海A0 二氧化碳传感器
                                            document.getElementById('lew2c').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"ppm";
                                            document.getElementById('lew2c').appendChild(p);
                                            break;
                                    case 62638685:       //威海A0      雨量计
                                            document.getElementById('lewyl').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"mm";
                                            document.getElementById('lewyl').appendChild(p);
                                            break;
                                    case 80906243:            //威海A0 雨雪传感器加热型
                                            document.getElementById('lewyx').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"mm";
                                            document.getElementById('lewyx').appendChild(p);
                                            break;
                                    case 96735299:        //威海A0风向传感器
                                             var a;
                                       if(result[j].value==0){
                                           a='北';
                                       }
                                       else if(0<result[j].value<90){
                                           a='东北';
                                       }else if(result[j].value==90){
                                           a='东';
                                       }else if(90<result[j].value<180){
                                            a='东南';
                                       }else if(result[j].value==180){
                                            a='南';
                                       }else if(180<result[j].value<270){
                                            a='西南';
                                       }else if(result[j].value==270){
                                            a='西';
                                       }else if(270<result[j].value<360){
                                            a='西北';
                                       }
                                            document.getElementById('lewfx').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=a+"风";
                                            document.getElementById('lewfx').appendChild(p);
                                            break;
                                    case 82582446:     //威海A1 土壤温湿度电导率3合1
                                            if(result[j].property=="土壤温度"){
                                                document.getElementById('lewtw').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"℃";
                                                document.getElementById('lewtw').appendChild(p);
                                                break;
                                            }else if(result[j].property=="土壤湿度"){
                                                document.getElementById('lewts').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"%";
                                                document.getElementById('lewts').appendChild(p);
                                                break;
                                            }else{
                                                document.getElementById('lewdd').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"ph";
                                                document.getElementById('lewdd').appendChild(p);
                                                break;
                                            }
                                    case 92924446:        //威海A1土壤盐分传感器
                                            document.getElementById('lewyf').innerHTML = '';    //清空
                                            var p = document.createElement('p');
                                            p.innerHTML=result[j].value+"%";
                                            document.getElementById('lewyf').appendChild(p);
                                            break;
                                    // case 27102994:        //威海A1土壤ph传感器
                                    //         document.getElementById('lewph').innerHTML = '';    //清空
                                    //         var p = document.createElement('p');
                                    //         p.innerHTML=result[j].value+"ph";
                                    //         document.getElementById('lewph').appendChild(p);
                                    //         break;
                                    case 90594147:    //威海A1土壤温湿度
                                            if(result[j].property=="土壤温度"){
                                                document.getElementById('lew1tw').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"℃";
                                                document.getElementById('lew1tw').appendChild(p);
                                                break;
                                            }else{
                                                document.getElementById('lew1ts').innerHTML = '';    //清空
                                                var p = document.createElement('p');
                                                p.innerHTML=result[j].value+"%";
                                                document.getElementById('lew1ts').appendChild(p);
                                                break;
                                            }
                                default:
                                        console.log("lehome实时监测模块数据读取其他\n");
                            }
                        }
                }
                else {
                    console.log("此时没有数据传入！");
                }
               
                console.log("------------\n");
            },
           
            error: function () {
                console.log("页面实时监测数据加载失败！");
            }
        }) },55000);
 
    //输出模板接口le.js
    exports('lehome', {});
});