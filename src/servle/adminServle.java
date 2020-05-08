package servle;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.admininfo;
import beans.pageinfo;
import dao.adminDao;
import net.sf.json.JSONObject;
import utils.pageutlie;

/**
 * Servlet implementation class adminServle
 */
@WebServlet("/adminServle")
public class adminServle extends HttpServlet {
	// public admininfo adm;
	private static final long serialVersionUID = 1L;

	public adminServle() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("login".equals(request.getParameter("msg"))) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			admininfo admin = adminDao.admin(id, password);
			if (admin != null) {
					request.getSession().setAttribute("yonghu", admin);
					response.getWriter().write("0");
				}
			 else {
				response.getWriter().write("1");

			}
		}
		else if ("adminadd".equals(request.getParameter("msg"))) {
			String id = request.getParameter("id");
			admininfo admin = adminDao.admin(id);
			if (admin == null) {
				response.getWriter().write("0");
			} else {
				response.getWriter().write("1");
				;
			}
		}
		else if ("adminaddd".equals(request.getParameter("msg"))) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String pwdconfirm = request.getParameter("pwdconfirm");
			String note = request.getParameter("note");
			admininfo admin = new admininfo();
			if (id.matches("^(\\w){4,15}$")) {
				if (password.matches("^(\\w){6,20}$")) {
					if (password.equals(pwdconfirm)) {
						admin.setAdminName(id);
						admin.setPassword(password);
						admin.setNote(note);
						admin.setState(1);
						if (adminDao.adminup(admin) > 0) {
							request.setAttribute("msg", "添加成功");
							request.setAttribute("adminadd", admin);
							request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
						} else {
							request.setAttribute("msg", "添加失败");
							request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
						}
					}
				} else {
					request.setAttribute("msg", "添加失败");
					request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("msg", "添加失败");
				request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
			}
			;

		}
		else if ("manage".equals(request.getParameter("msg"))) {
			manage(request, response);
		}

		else if ("delet".equals(request.getParameter("msg"))) {
			int id = Integer.parseInt(request.getParameter("id"));
			// admininfo admin=(admininfo) request.getSession().getAttribute("yonghu");
			// //就是在这里出的错取不出来
			// System.out.println(admin.getAdminName());
			// if(admin.getId()!=id) {
			id = adminDao.admindelet(id);
			if (id > 0) {
				request.setAttribute("delet", "删除成功");
			} else {
				request.setAttribute("delet", "删除失败");
			}
			// }else {
			// request.setAttribute("delet", "不能删除自己");
			// }
			manage(request, response);
		}

		else if ("delets".equals(request.getParameter("msg"))) {
			String[] id = request.getParameterValues("ck_id");
			int a = 0;

			for (int i = 0; i < id.length; i++) {
				a = a + adminDao.admindelet(Integer.parseInt(id[i]));
			}
			request.setAttribute("delet", "成功删除" + a + "条数据" + "未成功" + (id.length - a) + "条");
			manage(request, response);

		}

		else if ("sd".equals(request.getParameter("msg"))) {
			if (0 < adminDao.adminsuoding(Integer.parseInt(request.getParameter("id")))) {
				request.setAttribute("delet", "锁定成功");
				manage(request, response);
			} else {
				request.setAttribute("delet", "锁定失败");
				manage(request, response);
			}
		}
		else if ("js".equals(request.getParameter("msg"))) {
			if (0 < adminDao.adminjiesuo(Integer.parseInt(request.getParameter("id")))) {
				request.setAttribute("delet", "解锁成功");
				manage(request, response);
			} else {
				request.setAttribute("delet", "解锁失败");
				manage(request, response);
			}
		}

	}

	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageindex = Integer.parseInt(request.getParameter("pageindex"));
		int pagecount = adminDao.pagecount();
		int pagesize = 20;
		pageinfo page = pageutlie.getpageinfo(pagesize, pagecount, pageindex);
		request.setAttribute("admin", adminDao.adminlimt(page.getBeginRow(), page.getPagesize()));
		request.setAttribute("pageinfo", page);
		request.getRequestDispatcher("/admin/admin_manage.jsp").forward(request, response);
	}

}
