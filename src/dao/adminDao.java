package dao;

import java.util.List;

import DButil.DButil;
import beans.admininfo;

public class adminDao {
public static admininfo  admin(String id,String password) {
	admininfo admin=DButil.getall("SELECT * FROM admininfo WHERE adminName=? and password=?  ",admininfo.class,id,password);
	return admin;
}
public static admininfo  admin(String id) {
	admininfo admin=DButil.getall("SELECT * FROM admininfo WHERE adminName=?  ",admininfo.class,id);
	return admin;
}
public static int adminup(admininfo admin) {
	int a=0;
	a=DButil.updata("INSERT INTO admininfo(adminName,PASSWORD,note) VALUES (?,?,?);", admin.getAdminName(),admin.getPassword(),admin.getNote());
	return a;
}
public static int pagecount() {
	int a=0;
	a=DButil.getcount("SELECT COUNT(id) FROM admininfo where state!=0 ;");
	return a;
}
public static List adminlimt(int page,int pagesize ) {
	
	List list=DButil.getList("SELECT * FROM admininfo where state!=0 LIMIT ?,?  ;",admininfo.class, page,pagesize);
	return list;
}
public static int admindelet(int id) {
	int a;
	a=DButil.updata("UPDATE admininfo SET state=0 WHERE id=?;", id);
	return a;
}
public static int adminsuoding(int id) {
	int a;
	a=DButil.updata("UPDATE admininfo SET state=2 WHERE id=?;", id);
	return a;
}
public static int adminjiesuo(int id) {
	int a;
	a=DButil.updata("UPDATE admininfo SET state=1 WHERE id=?;", id);
	return a;
}
}
