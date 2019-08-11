package com.song.common;

import java.io.File;
import java.util.Locale;
 
import org.springframework.web.servlet.view.InternalResourceView;
 
/** 
 * @author hui 
 * @date 创建时间：2018年5月9日 下午5:26:55 吴清辉新建
 * @version 1.0 
 **/
public class HtmlResourceView extends InternalResourceView {
    @Override  
    public boolean checkResource(Locale locale) {  
     File file = new File(this.getServletContext().getRealPath("/") + getUrl());  
     return file.exists();// 判断该页面是否存在  
    }
}
