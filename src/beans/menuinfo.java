package beans;

import java.util.List;

public class menuinfo {
	private int id;
	private String menuName; //�˵�����
	private String url;  //�˵���Ӧ��url
	private String icon;  //ͼ��
	private String target; //����λ��
	private int parentId; //����ID
	private List<menuinfo> menuList;  //�Ӳ˵��б�
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
