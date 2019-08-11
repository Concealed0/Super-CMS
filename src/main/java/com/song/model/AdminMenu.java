package com.song.model;

import java.io.Serializable;
import java.util.List;

public class AdminMenu implements Serializable{

	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private String act;

    private String iconfont;

    private Integer sort;

    private Integer langId;

    private Integer status;
    
    private String cname;
    
    private List<AdminMenu> children;

//    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getAct() {
        return act;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
    public String getCname() {
        return cname;
    }

    public void setAct(String act) {
        this.act = act == null ? null : act.trim();
    }

    public String getIconfont() {
        return iconfont;
    }

    public void setIconfont(String iconfont) {
        this.iconfont = iconfont == null ? null : iconfont.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getLangId() {
        return langId;
    }

    public void setLangId(Integer langId) {
        this.langId = langId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public List<AdminMenu> getChildren() {
		return children;
	}
	public void setChildren(List<AdminMenu> children) {
		this.children = children;
	}
}
