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

}
