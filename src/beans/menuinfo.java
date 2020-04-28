package beans;

import java.util.List;

public class menuinfo {
	private int id;
	private String menuName; //菜单名称
	private String url;  //菜单对应的url
	private String icon;  //图标
	private String target; //弹出位置
	private int parentId; //父级ID
	private List<menuinfo> menuList;  //子菜单列表
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public List<menuinfo> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<menuinfo> menuList) {
		this.menuList = menuList;
	}
	
 
}
