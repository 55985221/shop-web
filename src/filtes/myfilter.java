package filtes;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class myfilter implements Filter {
@Override
public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException {
	  //    HttpServletRequest req=(HttpServletRequest)arg0;
	  //    if(req.getSession().getAttribute("admininfo")!=null) {
	    	  arg2.doFilter(arg0, arg1);
	   //  }else {
	   // 	  arg0.setAttribute("msg", "Ã»ÓÐµÇÂ¼");
	   // 	  arg0.getRequestDispatcher("/login.jsp").forward(arg0, arg1);
	   //   }
	
}
}
