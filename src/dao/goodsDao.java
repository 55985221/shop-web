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
	int a=DButil.updata("INSERT INTO  goodsinfo(goodsName,bigCateId,smallCateId,price,des,unit,producter,pictureData) VALUE(?,?,?,?,?,?,?,?);",goods.getGoodsName(),goods.getBigCateId(),goods.getSmallCateId(),goods.getPrice(),goods.getDes(),goods.getUnit(),goods.getProducter(),goods.getPictureDate());
	return a;
}

}
