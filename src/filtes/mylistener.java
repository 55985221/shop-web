package filtes;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.admininfo;

public class mylistener implements HttpSessionListener{
@Override
public void sessionCreated(HttpSessionEvent se) {
	admininfo admin=(admininfo) se.getSession().getAttribute("admininfo");
 	System.out.println("�Ҵ�����"+admin.getAdminName());
}
@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	   
         	System.out.println("�ұ�������");
}
}
