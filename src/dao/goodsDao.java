package dao;

import java.util.List;

import DButil.DButil;
import beans.cateinfo;
import beans.goodsinfo;


public class goodsDao {
public static List getcate(int id) {
	List<cateinfo> lt=DButil.getList("SELECT * FROM cateinfo WHERE parentId=?;", cateinfo.class, id);
	return lt;
}
public static int goodsadd(goodsinfo goods) {
	
	int a=0;
	if(goods.getPictureData()!=null&&goods.getPictureData().length!=0) {
	a=DButil.updata("INSERT INTO  goodsinfo(goodsName,bigCateId,smallCateId,price,des,unit,producter,pictureData) VALUE(?,?,?,?,?,?,?,?);",goods.getGoodsName(),goods.getBigCateId(),goods.getSmallCateId(),goods.getPrice(),goods.getDes(),goods.getUnit(),goods.getProducter(),goods.getPictureData());
	}else {
		a=DButil.updata("INSERT INTO  goodsinfo(goodsName,bigCateId,smallCateId,price,des,unit,producter) VALUE(?,?,?,?,?,?,?);",goods.getGoodsName(),goods.getBigCateId(),goods.getSmallCateId(),goods.getPrice(),goods.getDes(),goods.getUnit(),goods.getProducter());
		
	}
	return a;
}
public static List getgoods(int bigCateId,int smallCateId,String goodsName,int beginrow,int pagesize) {
	String sql= " select temp.*,b.cateName as smallCateName from "
			+ "(select a.*,b.cateName as bigCateName from goodsInfo a left join cateInfo b on a.bigcateId=b.id) temp "
			+ " left join cateInfo b on temp.smallcateId=b.id  where 1=1 " ; 
	  
	if(bigCateId!=0){
		sql+=" and bigCateId="+bigCateId;
	}

	if(bigCateId!=0){
		sql+=" and smallCateId="+smallCateId;
	}
	
	if(goodsName!=null&&!goodsName.equals("")){
		sql+=" and goodsName like '%"+goodsName+"%'";
	}
	
	sql+="  limit ?,?";
	
	return DButil.getList(sql, goodsinfo.class,beginrow,pagesize);	
}
public static List getcate() {
	List<cateinfo> lt=DButil.getList("SELECT * FROM cateinfo ;", cateinfo.class);
	return lt;
}
public static goodsinfo getimg(int id) {
	goodsinfo bt=DButil.getobj("SELECT * FROM goodsinfo where id=?;", goodsinfo.class,id);
	return bt;
}
public static int goodscount(int bigCateId,int smallCateId,String goodsName) {
	int a=0;
	String sql= "select count(*) from goodsInfo where 1=1 " ;
	
	if(bigCateId!=0){
		sql+=" and bigCateId="+bigCateId;
	}

	if(bigCateId!=0){
		sql+=" and smallCateId="+smallCateId;
	}
	
	if(goodsName!=null&&!goodsName.equals("")){
		sql+=" and goodsName =\""+goodsName+"\"";
	}
	a=DButil.getcount(sql);
	return a;
}

public static int  deletgoods(int id) {
int a;
a=DButil.updata("DELETE FROM goodsinfo WHERE id=?;", id);
return a;
}
public static int goodsupdata(goodsinfo goods) {
	if(goods.getPictureData()==null||goods.getPictureData().length==0) {
		goods.setPictureData(getimg(goods.getId()).getPictureData());
	}
	int a=deletgoods(goods.getId());
	if(a>0) {
		a=DButil.addWithId("INSERT INTO  goodsinfo(goodsName,bigCateId,smallCateId,price,des,unit,producter,pictureData) VALUE(?,?,?,?,?,?,?,?);",goods.getGoodsName(),goods.getBigCateId(),goods.getSmallCateId(),goods.getPrice(),goods.getDes(),goods.getUnit(),goods.getProducter(),goods.getPictureData());
		if(a>0) {
			return a;
		}else {
			return a;
		}
	}else {
		return a;
	}
	
}
}
