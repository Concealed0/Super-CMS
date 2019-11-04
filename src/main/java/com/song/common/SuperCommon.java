package com.song.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class SuperCommon {

	private static Logger logger = Logger.getLogger(SuperCommon.class);
	
	/* //时间戳转换日期 */
	public String stampToTime(Long stamp) {
		String sd = "";
		Date date = new Date(stamp);//新建一个时间对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//你要转换成的时间格式,大小写不要变
		sd = sdf.format(date); // 时间戳转换日期
		return sd;
		}

	  /* //日期转换为时间戳 */
	 public long timeToStamp(String timers) {
		 Date d = new Date();
		 long timeStemp = 0;
		 try {
			 SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			 d = sf.parse(timers);// 日期转换为时间戳
			 } catch (ParseException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
				 }
		 timeStemp = d.getTime();
		 return timeStemp;
		 }
	
	/**
	 * 将字符串的编码格式转换为utf-8
	 * 
	 * @param str
	 * @return Name = new
	 * String(Name.getBytes("ISO-8859-1"), "utf-8");
	 */
	public static String toUTF8(String str) {
		if (isEmpty(str)) {
			return "";
		}
		try {
			if (str.equals(new String(str.getBytes("GB2312"), "GB2312"))) {
				str = new String(str.getBytes("GB2312"), "utf-8");
				return str;
			}
		} catch (Exception exception) {
		}
		try {
			if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
				str = new String(str.getBytes("ISO-8859-1"), "utf-8");
				return str;
			}
		} catch (Exception exception1) {
		}
		try {
			if (str.equals(new String(str.getBytes("GBK"), "GBK"))) {
				str = new String(str.getBytes("GBK"), "utf-8");
				return str;
			}
		} catch (Exception exception3) {
		}
		return str;
	}
	 
	/**
	 * 判断是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		// 如果字符串不为null，去除空格后值不与空字符串相等的话，证明字符串有实质性的内容
		if (str != null && !str.trim().isEmpty()) {
			return false;// 不为空
		}
		return true;// 为空
	}

	
	
	/**   
	* @Function: SuperCommon.java
	* @Description: 返回字符串格式为
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年7月18日 下午10:17:42 
	*/
	public String ajaxReturn(int status,String msg,String url) {
		Map<String, Object> dataone = new HashMap<String, Object>();
		dataone.put("status",status);
		dataone.put("msg",msg);
		dataone.put("url",url);
		dataone.put("rander",true);
		dataone.put("data","ange");
		return JSON.toJSONString(dataone);
		
	}
	
	
	
	//标准返回数组
	public String SuperJson(List<?> list,String res){
		Map<String, Object> datatwo = new HashMap<String, Object>();
		if(list!=null) {
			datatwo.put("data", list);
			datatwo.put("status", 200);
			datatwo.put("msg","true");
			datatwo.put("res",res);
			datatwo.put("name","ange");
			return JSON.toJSONString(datatwo);
		}else {
			datatwo.put("data", list);
			datatwo.put("status", 400);
			datatwo.put("msg","flase");
			datatwo.put("res",res);
			datatwo.put("name","ange");
			return JSON.toJSONString(datatwo);
		}
		
	}
	//标准返回数组
		public String SuperJsonMap(Map<String,Object> map,String res){
			Map<String, Object> datatwo = new HashMap<String, Object>();
			if(map!=null) {
				datatwo.put("data", map);
				datatwo.put("status", 200);
				datatwo.put("msg","true");
				datatwo.put("res",res);
				datatwo.put("name","ange");
				return JSON.toJSONString(datatwo);
			}else {
				datatwo.put("data", map);
				datatwo.put("status", 400);
				datatwo.put("msg","flase");
				datatwo.put("res",res);
				datatwo.put("name","ange");
				return JSON.toJSONString(datatwo);
			}
			
		}
	   
    /**   
    * @Function: SuperCommon.java
    * @Description: 将有逗号的字符串转换为无字符串的数组形式
    *
    * @param:描述1描述
    * @return：返回结果描述
    * @throws：异常描述
    *
    * @version: v1.0.0
    * @author: dongsong
    * @date: 2019年6月16日 上午11:36:01 
    */
    public String[] convertStrToArray(String str){   
        String[] strArray = null;   
        strArray = str.split(","); //拆分字符为"," ,然后把结果交给数组strArray 
        for (String string : strArray){
            System.out.println(string);
        }
        return strArray;
    }    
	
	
    /**   
    * @Function: SuperCommon.java
    * @Description: 获取list对象中的某一属性值
    *
    * @param:o list对象       c 实体类型         field 获取的字段
    * @return：返回查找的属性值
    * @throws：异常描述
    *
    * @version: v1.0.0
    * @author: dongsong
    * @date: 2019年6月16日 下午10:01:03 
    */
    public String getFieldValueListByName(List<?> o, Class<?> c, String field) {
        StringBuffer result = new StringBuffer();
        if (StringUtils.isNoneBlank(field)) {
            Field[] fields = c.getDeclaredFields();
            int pos;
            for (pos = 0; pos < fields.length; pos++) {
                if (field.equals(fields[pos].getName())) {
                    break;
                }
            }
            for (Object o1 : o) {
                try {
                    fields[pos].setAccessible(true);
                    result.append(fields[pos].get(o1) + ",");
                } catch (Exception e) {
                    System.out.println("error--------" + "Reason is:" + e.getMessage());
                }
            }
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }
    
    /**   
    * @Function: SuperCommon.java
    * @Description: 根据list对象获取多个list中的所有属性值
    *
    * @param:描述1描述
    * @return：返回结果描述
    * @throws：异常描述
    *
    * @version: v1.0.0
    * @author: dongsong
    * @date: 2019年6月17日 下午4:04:40 
    */
    public void getFieldValueList(List<?> list) {
        if (list == null || list.size() == 0)
            return;
        for (Object o : list) {
            if (o != null){
                Field[] fields = o.getClass().getDeclaredFields();
                try {
                    for (Field f : fields) {
                        f.setAccessible(true);
                        String name = f.getName();
                        Object value = f.get(o);
                        System.out.println(name + "--" + value.toString());
                    }
                } catch (IllegalAccessException e) {

                }
            }
        }
    }
	
    
    /**
     * 获取属性名数组
     * */
     public String[][][] getFiledListName(List<?> list, Class<?> c) {
 
    	 Field[] fields=c.getDeclaredFields();
    	 String[][][] fieldNames = new String[list.size()][fields.length][fields.length];
    	 int i=0;
    	 for(Object o : list) {  		
    		for(int j=0,x=0;j<fields.length;j++,x++) {		
    			    fields[x].setAccessible(true);
                    fieldNames[i][j][0] = fields[x].getName();
                    try {
                    	fieldNames[i][j][1] = fields[x].get(o).toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
    		}
    		i++;
    	}
         return fieldNames;
     }
    /**
         * 获取属性名数组
         * */
         public String[] getFiledName(Object o) {
             Field[] fields = o.getClass().getDeclaredFields();
             String[] fieldNames = new String[fields.length];
             for (int i = 0; i < fields.length; i++) {
                 System.out.println(fields[i].getType());
                 fieldNames[i] = fields[i].getName();
             }
             return fieldNames;
         }
	
         /**
          * 获取对象的所有属性值，返回一个对象数组
          * */
          public Object[] getFiledValues(Object o) {
              String[] fieldNames = this.getFiledName(o);
              Object[] value = new Object[fieldNames.length];
              for (int i = 0; i < fieldNames.length; i++) {
                  value[i] = this.getFieldValueByName(fieldNames[i], o);
              }
              return value;
          }
    /**   
    * @Function: SuperCommon.java
    * @Description: object对象根据属性名获取属性值
    *
    * @param:fieldName 字段名
    * @param:o object 对象
    * @return：返回object类型
    *
    * @version: v1.0.0
    * @author: dongsong
    * @date: 2019年6月16日 下午9:40:50 
    */
    public Object getFieldValueByName(String fieldName, Object o) {
        try {  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            logger.error(e.getMessage(),e);  
            return null;  
        }  
    } 
    
    
    

    /**   
	* @Function: Login.java
	* @Description: 前端绘出验证码
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年5月24日 上午11:07:38 
	*/
	public String drawCodeImg(ByteArrayOutputStream output) {
		String code = "";
		for (int i = 0; i < 5; i++) {
			code += randomChar();
		}
		
		int width = 70;
		int height = 36;
		// 生成随机数,用于后面的验证码输出
		Random ran = new Random();
		// 图像buffer
		//创建BufferedImage对象
		BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR); 
		//使用BufferedImage对象得到Graphics对象
		Graphics2D graphics = bImage.createGraphics();     
		//设置画布背景色
	//	graphics.setColor(new Color(255, 0, 0));										
		// 设置字体类型、字体大小、字体样式
		Font font = new Font("Times New Roman", Font.PLAIN, 20);
		graphics.setFont(font);
		// 将随机得到的字体颜色绘制内容
		//随机得到颜色用于绘制内容
		graphics.setColor(new Color(ran.nextInt(255)+1,ran.nextInt(255)+1,ran.nextInt(255)+1));		
	    //graphics.setColor(new Color(66,2,82));
		//绘制背景的代码
		graphics.setBackground(Color.WHITE);
		//擦除一个由参数指定的矩形块的着色。通过使用当前绘图表面的背景色进行填充来清除指定的矩形,解决返回图像背景黑色问题。
		graphics.clearRect(0, 0, width, height);
		//绘制干扰线。i为干扰线条数
		for (int i = 0; i<(ran.nextInt(5)+8); i++) {
		    //设置干扰线的颜色
		    graphics.setColor(new Color(ran.nextInt(255)+1,ran.nextInt(255)+1,ran.nextInt(255)+1));	
			//设置干扰线的坐标
			graphics.drawLine(ran.nextInt(70),ran.nextInt(36),ran.nextInt(70),ran.nextInt(36));				
			}
		//获取一个字符串在屏幕上的尺寸
		FontRenderContext context = graphics.getFontRenderContext();
		Rectangle2D bounds = font.getStringBounds(code, context);
		double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        //此步为在x坐标为（(int) x）y坐标为(int) baseY的地方绘制内容为code的字符图。
        graphics.drawString(code, (int) x, (int) baseY);
        //dispose()作用是销毁程序中指定的图形界面资源，如果在使用了graphics获得windows一些图形资源，而不进行关闭的话，由于后期多人使用就会造成内存溢出的情况的，导致程序卡死。
        //类似java 连接数据库时使用conn.close方法，也是为了关闭数据库连接资源。
        graphics.dispose();
       //如果是网站则可以把图片保存到项目下面然后页面上取出来即可
        try {
			ImageIO.write(bImage, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
	/**   
	* @Function: Login.java
	* @Description: 返回一个随机字符
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: dongsong
	* @date: 2019年5月24日 上午11:10:26 
	*/
	private char randomChar() {
		//由Random生成随机数
		Random r = new Random();
		//定义一个字符串（A-Z，a-z，0-9）即62位；
		String str = "ABDEFGHJLMNQRTUYabdefghjmnqrtuy23456789";
		//charAt() 方法返回指定索引处的char值。
		return str.charAt(r.nextInt(str.length()));
	}
	


	   /**   
	    * @Function: Login.java
	    * @Description: 该函数的功能描述
	    *
	    * @param:获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
	    * @param 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 
	    * @return：ip
	    * @throws：异常描述
	    *
	    * @version: v1.0.0
	    * @author: dongsong
	    * @date: 2019年6月11日 下午4:52:31 
	    */
	    public String getIpAddr(HttpServletRequest request) {
	        String ip = request.getHeader("x-forwarded-for"); 
	        System.out.println("x-forwarded-for ip: " + ip);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
	            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
	            if( ip.indexOf(",")!=-1 ){
	                ip = ip.split(",")[0];
	            }
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	            System.out.println("Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	            System.out.println("WL-Proxy-Client-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	            System.out.println("HTTP_CLIENT_IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	            System.out.println("HTTP_X_FORWARDED_FOR ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("X-Real-IP");  
	            System.out.println("X-Real-IP ip: " + ip);
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	            System.out.println("getRemoteAddr ip: " + ip);
	        } 
	        System.out.println("获取客户端ip: " + ip);
	        return ip;  
	    }

	    
	    /**   
	    * @Function: SuperCommon.java
	    * @Description: 获取后台session值
	    *
	    * @param:描述1描述
	    * @return：返回结果描述
	    * @throws：异常描述
	    *
	    * @version: v1.0.0
	    * @author: dongsong
	    * @date: 2019年6月15日 下午12:11:24 
	    */
	    public void traverse(HttpSession session) {
			// 获取session中所有的键值
	    	System.out.println("获取session中所有的键值");
			Enumeration<?> enumeration = session.getAttributeNames();
			// 遍历enumeration中的
			while (enumeration.hasMoreElements()) {
			    // 获取session键值
			    String name = enumeration.nextElement().toString();
			    // 根据键值取session中的值
			    Object value = session.getAttribute(name);
			    // 打印结果
				System.out.println("<B>" + name + "</B>=" + value + "<br>/n");
			}
	    }
	    
	    
//		//查看session中有什么值
//		//获取session  
//		// 获取session中所有的键值
//		Enumeration<?> enumeration = session.getAttributeNames();
//		// 遍历enumeration中的
//		while (enumeration.hasMoreElements()) {
//		    // 获取session键值
//		    String name = enumeration.nextElement().toString();
//		    // 根据键值取session中的值
//		    Object value = session.getAttribute(name);
//		    // 打印结果
//			System.out.println("<B>" + name + "</B>=" + value + "<br>/n");
//		}
//		String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
//		String localName = request.getLocalName();//获取WEB服务器的主机名
//		System.out.println("WEB服务器的主机名："+localAddr+"<br/>");
//		System.out.println("WEB服务器使用的端口号："+localName+"<br/>");
//		//获取header头
//        String userAgent = request.getHeader("user-agent");
//        System.out.println("来访者使用的浏览器："+userAgent+"<br/>");  
//        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
//        String remoteHost = request.getRemoteHost();
//        int remotePort = request.getRemotePort();
//        String remoteUser = request.getRemoteUser();
//        System.out.println("来访者的IP地址："+remoteAddr+"<br/>");
//        System.out.println("来访者的主机名："+remoteHost+"<br/>");
//        System.out.println("使用的端口号："+remotePort+"<br/>");
//        System.out.println("remoteUser："+remoteUser+"<br/>");
//        //获取IP
//        String ip=getIpAddr(request);
//        System.out.println(ip);
	    
	    
	    
	    
	    
//	    public String doLogin(@RequestParam(defaultValue="") String username,
//				@RequestParam(defaultValue="") String password,
//				@RequestParam(defaultValue="0") int userType,
//				@RequestParam(defaultValue="") String verifyCode,HttpServletRequest request, HttpSession session) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException {
//			
//			int group_id=0;
//	        //获取客户端登陆的ip地址
//			String ip=songcom.getIpAddr(request);
//			//获取客户端采用的浏览器方式
//			String userAgent = request.getHeader("user-agent");
//			
//			//比较验证码，toUpperCase()的意思是将所有的英文字符转换为大写字母
//			//StrUtil.CODE_ERROR+"松"+sessionVerifyCode+"松"+verifyCode;
//			String sessionVerifyCode = (String) session.getAttribute(StrUtil.VERIFY_CODE);
//			if (sessionVerifyCode == null || !sessionVerifyCode.equals(verifyCode)) {
//				Object le =adminlogservice.adminlogValidate(userType,username,ip,userAgent,StrUtil.CODE_ERROR);
//				System.out.println(le);
//				return StrUtil.CODE_ERROR;
//			}
//			
//			if (userType == 1) {
//				//使用Jackson返回格式
//				ObjectMapper mapper = new ObjectMapper();
//				List<User> song = usermapper.userlist();			
//				String json = mapper.writeValueAsString(song);
//				return json;
//				//return JSON.toJSONString(song);
//			}else {
//					Object user=userService.loginValidate(username, password.toUpperCase());//获得验证后user对象
//					System.out.println(user);			
//					if(user!=null) {
//						//System.out.println(songcom.getFieldValueByName("username",user));
//						group_id=(int) songcom.getFieldValueByName("username",user);
//						adminlogservice.adminlogValidate(group_id,username,ip,userAgent,StrUtil.RESULT_TRUE);
//					}else {
//						adminlogservice.adminlogValidate(group_id,username,ip,userAgent,StrUtil.RESULT_FALSE);
//					}
//					//获取登陆账号权限
//					//int selectgroup=userService.groupValidate(username);
//				    //System.out.println(selectgroup);
//				    
//					return JSON.toJSONString(user);	
//				
//			}
//		}

}
