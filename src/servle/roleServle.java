package servle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.pageinfo;
import dao.menuDao;
import dao.roleDao;
import utils.pageutlie;

/**
 * Servlet implementation class roleServle
 */
@WebServlet("/roleServle")
public class roleServle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("manage".equals(request.getParameter("msg"))){
		    int pageindex=Integer.parseInt(request.getParameter("pageindex")); 
		    if(pageindex==0) {
		    	pageindex=1;
		    }
			pageinfo page= pageutlie.getpageinfo(20, roleDao.getroleconten(), pageindex);
			request.setAttribute("page", page);
			request.setAttribute("role", roleDao.getlistrole(page.getBeginRow(), page.getPagesize()));
			request.getRequestDispatcher("role/role_menu.jsp").forward(request, response);
			
		}
		else
		if("rolemenu".equals(request.getParameter("msg"))) {
		int roleid=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("roled", roleDao.role(roleid));
		request.setAttribute("menu", menuDao.getrolemenus(0));
		request.setAttribute("rolemenuid", roleDao.roleidString(roleid));
		request.getRequestDispatcher("role/rolemenu_menu.jsp").forward(request, response);
		}
		else
		if("updata".equals(request.getParameter("msg"))){
		    int roleid=Integer.parseInt(request.getParameter("id"));
		    String[] menuid=request.getParameterValues("rolemenu");
		    if(roleDao.updata(roleid, menuid)) {
		    	request.setAttribute("roled", roleDao.role(roleid));
				request.setAttribute("menu", menuDao.getrolemenus(0));
				request.setAttribute("rolemenuid", roleDao.roleidString(roleid));
				request.setAttribute("tishi", "修改成功");
				request.getRequestDispatcher("role/rolemenu_menu.jsp").forward(request, response);
		    	
		    }else {
		    	request.setAttribute("roled", roleDao.role(roleid));
				request.setAttribute("menu", menuDao.getrolemenus(0));
				request.setAttribute("rolemenuid", roleDao.roleidString(roleid));
				request.setAttribute("tishi", "修改失败");
				request.getRequestDispatcher("role/rolemenu_menu.jsp").forward(request, response);
		    }
		}
		
	}

}
