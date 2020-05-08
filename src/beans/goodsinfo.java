package beans;

import java.util.Date;

public class goodsinfo {
private int id;
private String goodsName;
private int bigCateId;
private String bigCateName;
private int smallCateId;
private String smallCateName;
private double price;
private String des;
private String unit;
private String producter;
private Date editDate;
private byte[] pictureData;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getGoodsName() {
	return goodsName;
}
public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}
public int getBigCateId() {
	return bigCateId;
}
public void setBigCateId(int bigCateId) {
	this.bigCateId = bigCateId;
}
public int getSmallCateId() {
	return smallCateId;
}
public void setSmallCateId(int smallCateId) {
	this.smallCateId = smallCateId;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getProducter() {
	return producter;
}
public void setProducter(String producter) {
	this.producter = producter;
}
public Date getEditDate() {
	return editDate;
}
public void setEditDate(Date editDate) {
	this.editDate = editDate;
}
public byte[] getPictureData() {
	return pictureData;
}
public void setPictureData(byte[] pictureData) {
	this.pictureData = pictureData;
} 
public String getBigCateName() {
	return bigCateName;
}
public void setBigCateName(String bigCateName) {
	this.bigCateName = bigCateName;
}
public String getSmallCateName() {
	return smallCateName;
}
public void setSmallCateName(String smallCateName) {
	this.smallCateName = smallCateName;
}
}
