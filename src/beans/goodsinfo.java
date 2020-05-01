package beans;

import java.util.Date;

public class goodsinfo {
private int id;
private String goodsName;
private int bigCateId;
private int smallCateId;
private double price;
private String des;
private String unit;
private String producter;
private Date editDate;
private byte[] pictureDate;
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
public byte[] getPictureDate() {
	return pictureDate;
}
public void setPictureDate(byte[] pictureDate) {
	this.pictureDate = pictureDate;
} 
}
