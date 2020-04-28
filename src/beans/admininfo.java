package beans;

import java.util.Date;

public class admininfo {
private int id;
private String note;
private String password;
private String adminName;
private int state;
private Date editDate;
private int roleId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNote() {
	return note;
}
public void setNote(String note) {
	this.note = note;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAdminName() {
	return adminName;
}
public void setAdminName(String adminName) {
	this.adminName = adminName;
}
public int getState() {
	return state;
}
public void setState(int state) {
	this.state = state;
}
public Date getEditDate() {
	return editDate;
}
public void setEditDate(Date editDate) {
	this.editDate = editDate;
}
@Override
public String toString() {
	return "admininfo [id=" + id + ", note=" + note + ", password=" + password + ", adminName=" + adminName + ", state="
			+ state + ", editDate=" + editDate + "]";
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}

}
