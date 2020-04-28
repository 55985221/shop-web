package filtes;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.admininfo;

public class mylistener implements HttpSessionListener{
@Override
public void sessionCreated(HttpSessionEvent se) {
	admininfo admin=(admininfo) se.getSession().getAttribute("admininfo");
 	System.out.println("我创建了"+admin.getAdminName());
}
@Override
	public void sessionDestroyed(HttpSessionEvent se) {
	   
         	System.out.println("我被销毁了");
}
}
