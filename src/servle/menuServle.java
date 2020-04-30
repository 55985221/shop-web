package servle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.admininfo;
import beans.menuinfo;
import beans.rolemenuinfo;
import dao.menuDao;

/**
 * Servlet implementation class menuServle
 */
@WebServlet("/menuServle")
public class menuServle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public menuServle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		
		if ("menu".equals(request.getParameter("msg"))) {
			// admininfo admin=(admininfo)
			// request.getSession().getAttribute("ceshi");//这里有可能出错因为前面有一个bug没有处理
			admininfo admin = (admininfo) request.getSession().getAttribute("yonghu");// 这块出错了
			// 通过session取得登录用户的数据 获取角色表 通过角色表进行获取菜单 然后进行装载；
			
			int roleid = admin.getRoleId();// 如果不显示他是什么角色的话我觉得可以直接从角色权限菜单表中取得数据
			List<rolemenuinfo> rolemenu = menuDao.getrolemenu(roleid);// 取得表的角色权限菜单表
			
			List<menuinfo> menu = menuDao.getmenu(0);// 对菜单进行遍历
			
				for (int ii = 0; ii < menu.size(); ii++) {
					for (int i = 0; i < rolemenu.size(); i++) {
					if (rolemenu.get(i).getMenuId() != menu.get(ii).getId()) {// 筛选一级菜单
						if(i==rolemenu.size()-1) {
						menu.remove(ii);// 从菜单中删除没有用的
						
						}
						
					} else {
						rolemenu.remove(i);// 删除被匹配
						--i;
						break;
					}
				}
			}
			
			for (int i = 0; i < menu.size(); i++) {
				menuinfo mn = menu.get(i);

				List<menuinfo> mmenu = menuDao.getmenu(mn.getId());
				for (int iii = 0; iii < mmenu.size(); iii++) {
					for (int ii = 0; ii < rolemenu.size(); ii++) {
						if (rolemenu.get(ii).getMenuId() != mmenu.get(iii).getId()) {
							if(ii==rolemenu.size()-1) {
								mmenu.remove(iii);
							}

						}else {
							break ;
						}
					}
				}
				mn.setMenuList(mmenu);
				menu.set(i, mn);
			}
			request.setAttribute("menu", menu);
			request.getRequestDispatcher("/left.jsp").forward(request, response);
		}

	}
}
