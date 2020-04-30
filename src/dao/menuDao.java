package dao;

import java.util.List;

import DButil.DButil;
import beans.menuinfo;
import beans.rolemenuinfo;

public class menuDao {
public static List<menuinfo> getmenu(int id) {
	List<menuinfo>  menu=DButil.getList("SELECT * FROM menuinfo WHERE parentId=?;", menuinfo.class,id);
			return menu;
}
public static List getrolemenu(int roleid) {
	List rolemenu=DButil.getList("SELECT * FROM rolemenu WHERE roleId=?;",rolemenuinfo.class,roleid);
	return rolemenu;
}
public static List getrolemenu(int menuid,int roleid) {
	String sql="select * from menuinfo where parentId=? and id in(select menuId from rolemenu where roleId=?);";
	List<menuinfo> lt=DButil.getList(sql, menuinfo.class,menuid,roleid);
	for(int i=0;i<lt.size();i++) {
		menuinfo m=lt.get(i);
		if(m.getParentId()==0&&m.getMenuList()==null) {
			m.setMenuList(getrolemenu(m.getId(), roleid));
			lt.set(i, m);
		}
	}
	return lt;
	
}
public static List getrolemenus(int menuid) {
	String sql="select * from menuinfo where parentId=? ;";
	List<menuinfo> lt=DButil.getList(sql, menuinfo.class,menuid);
	for(int i=0;i<lt.size();i++) {
		menuinfo m=lt.get(i);
		if(m.getParentId()==0&&m.getMenuList()==null) {
			m.setMenuList(getrolemenus(m.getId()));
			lt.set(i, m);
		}
	}
	return lt;
}	
}
