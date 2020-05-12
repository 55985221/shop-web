package beans;

import java.util.Date;

public class orderinfo {
private int id;
private int orderNo;
private int memerId;
private int postage;
private String postMethod;
private Date orderFate;
private String address;
private String orderState;
private double amount;
private Date sendDate;
private Date editDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getOrderNo() {
	return orderNo;
}
public void setOrderNo(int orderNo) {
	this.orderNo = orderNo;
}
public int getMemerId() {
	return memerId;
}
public void setMemerId(int memerId) {
	this.memerId = memerId;
}
public int getPostage() {
	return postage;
}
public void setPostage(int postage) {
	this.postage = postage;
}
public String getPostMethod() {
	return postMethod;
}
public void setPostMethod(String postMethod) {
	this.postMethod = postMethod;
}
public Date getOrderFate() {
	return orderFate;
}
public void setOrderFate(Date orderFate) {
	this.orderFate = orderFate;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getOrderState() {
	return orderState;
}
public void setOrderState(String orderState) {
	this.orderState = orderState;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getSendDate() {
	return sendDate;
}
public void setSendDate(Date sendDate) {
	this.sendDate = sendDate;
}
public Date getEditDate() {
	return editDate;
}
public void setEditDate(Date editDate) {
	this.editDate = editDate;
}

}
