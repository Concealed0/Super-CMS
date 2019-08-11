package com.song.model;

public class AdminGroup {

	 private Integer groupId;

	    private String name;

	    private String basePurview;

	    private String menuPurview;

	    private Integer status;

//	    private static final long serialVersionUID = 1L;

	    public Integer getGroupId() {
	        return groupId;
	    }

	    public void setGroupId(Integer groupId) {
	        this.groupId = groupId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }

	    public String getBasePurview() {
	        return basePurview;
	    }

	    public void setBasePurview(String basePurview) {
	        this.basePurview = basePurview == null ? null : basePurview.trim();
	    }

	    public String getMenuPurview() {
	        return menuPurview;
	    }

	    public void setMenuPurview(String menuPurview) {
	        this.menuPurview = menuPurview == null ? null : menuPurview.trim();
	    }

	    public Integer getStatus() {
	        return status;
	    }

	    public void setStatus(Integer status) {
	        this.status = status;
	    }
}
