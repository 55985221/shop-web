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
		
		 Object obj=request.getSession().getAttribute("session_admin");
		 
		 System.out.println(obj);
		 
		 if(1==1) {
			 return;
		 }
		
		
		
		
		if ("menu".equals(request.getParameter("msg"))) {
			// admininfo admin=(admininfo)
			// request.getSession().getAttribute("ceshi");//�����п��ܳ�����Ϊǰ����һ��bugû�д���
			admininfo admin = (admininfo) request.getSession().getAttribute("yonghu");// ��������
			// ͨ��sessionȡ�õ�¼�û������� ��ȡ��ɫ�� ͨ����ɫ����л�ȡ�˵� Ȼ�����װ�أ�
			if (admin == null) {
				System.out.println("����Ϊ��");
			}
			int roleid = admin.getRoleId();// �������ʾ����ʲô��ɫ�Ļ��Ҿ��ÿ���ֱ�Ӵӽ�ɫȨ�޲˵�����ȡ������
			List<rolemenuinfo> rolemenu = menuDao.getrolemenu(roleid);// ȡ�ñ�Ľ�ɫȨ�޲˵���
			List<menuinfo> menu = menuDao.getmenu(0);// �Բ˵����б���
			for (int i = 0; i < rolemenu.size(); i++) {
				for (int ii = 0; ii < menu.size(); ii++) {
					if (rolemenu.get(i).getMenuId() != menu.get(ii).getId()) {// ɸѡһ���˵�
						menu.remove(ii);// �Ӳ˵���ɾ��û���õ�
						--ii;
					} else {
						rolemenu.remove(i);// ɾ����ƥ��
						--i;
					}
				}
			}

			for (int i = 0; i < menu.size(); i++) {
				menuinfo mn = menu.get(i);

				List<menuinfo> mmenu = menuDao.getmenu(mn.getId());
				for (int iii = 0; iii < mmenu.size(); iii++) {
					for (int ii = 0; ii < rolemenu.size(); ii++) {
						if (rolemenu.get(ii).getMenuId() == mmenu.get(iii).getId()) {
							rolemenu.remove(ii);
							--ii;
						} else {
							mmenu.remove(iii);
							--iii;

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
