package dao;

import java.util.List;

import DButil.DButil;
import beans.roleinfo;

public class roleDao {
public static List getlistrole(int pagero,int pagesize) {
	List lt=DButil.getList("SELECT * FROM roleinfo LIMIT ?,?;", roleinfo.class,pagero,pagesize);
	return lt;
}
public static int getroleconten() {
	int a=DButil.getcount("SELECT COUNT(id) FROM roleinfo;");
	return a;
}
}
