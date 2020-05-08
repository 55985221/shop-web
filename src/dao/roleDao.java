package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public static roleinfo role(int roleid){
	roleinfo role= DButil.getobj("SELECT * FROM roleinfo where id=?;",roleinfo.class,roleid)  ;
			return  role;
}
public static String  roleidString(int roleId) {
	Connection con=null;
	PreparedStatement st=null;
	ResultSet rs=null;
	String str=new String();
	try {
		con=DButil.getcon();
		st=con.prepareStatement("select menuId from rolemenu where roleId=?");
		st.setObject(1, roleId);
		rs=st.executeQuery();
		while(rs.next()) {
			str+=rs.getString(1)+",";
		}
		str=str.substring(0, str.length()-1<0?0:str.length()-1);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return str;

}
public static boolean updata(int roleid,String[] rolemenu) {
	DButil.updata("DELETE FROM rolemenu WHERE roleId=?;", roleid);
	for(int i=0;rolemenu!=null&&i<rolemenu.length;i++) {
		DButil.updata("INSERT INTO rolemenu (roleId,menuId) VALUES (?,? )",roleid,rolemenu[i]);
	}
		
	
	
	return true;
}
}
