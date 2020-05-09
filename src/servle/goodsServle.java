package servle;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import beans.cateinfo;
import beans.goodsinfo;
import beans.pageinfo;
import dao.goodsDao;
import net.sf.json.JSONArray;
import utils.pageutlie;

/**
 * Servlet implementation class goodsServle
 */
@WebServlet("/goodsServle")
@MultipartConfig
public class goodsServle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if("goodsAdd".equals(request.getParameter("msg"))) {
		request.setAttribute("cate", goodsDao.getcate(0));
		request.getRequestDispatcher("goods/goods_add.jsp").forward(request, response);
	}
	else
	if("goodscatemenu".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("id"));
		List<cateinfo> lt=goodsDao.getcate(id);
		JSONArray json=JSONArray.fromObject(lt);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(json);
		
	}
	else
	if("goodsimg".equals("w")) {
			
		}
	else
	if("goodsadd".equals(request.getParameter("msg"))) {
		Part pr= request.getPart("ims");
		InputStream img= pr.getInputStream();
		byte[] imgs=new byte[img.available()];
		img.read(imgs);
		goodsinfo goods=new goodsinfo();
		goods.setGoodsName(request.getParameter("goodsName"));
		goods.setBigCateId(Integer.parseInt(request.getParameter("bigcateid")));
		goods.setSmallCateId(Integer.parseInt(request.getParameter("smallcateid")));
		goods.setUnit(request.getParameter("unit"));
		goods.setPrice(Double.parseDouble(request.getParameter("price")));
		goods.setProducter(request.getParameter("pd"));
		goods.setPictureData(imgs);
		goods.setDes(request.getParameter("des"));
		if(goodsDao.goodsadd(goods)>0) {
			request.setAttribute("goods", goods);
			request.setAttribute("tishi", "���ӳɹ�");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}else {
			request.setAttribute("goods", goods);
			request.setAttribute("tishi", "����ʧ��");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}
	}
	else
	if("manage".equals(request.getParameter("msg"))) {
		int index=Integer.parseInt(request.getParameter("pageindex")==null? "1":request.getParameter("pageindex"));
		int pagesize=20;
		int bigcateid=Integer.parseInt(request.getParameter("bigcateid")==null? "0":request.getParameter("bigcateid"));
		int smallcateid=Integer.parseInt(request.getParameter("smallcateid")==null? "0":request.getParameter("smallcateid"));;
		String goodsname=request.getParameter("goodsname");
		int pageconten=goodsDao.goodscount(bigcateid, smallcateid, goodsname);
		pageinfo page=pageutlie.getpageinfo(pagesize, pageconten, index);
		List lt=goodsDao.getgoods(bigcateid, smallcateid, goodsname, page.getBeginRow(), page.getPagesize());
		List cate=goodsDao.getcate();
		request.setAttribute("cate", cate);
		request.setAttribute("page", page);
		request.setAttribute("goodsinfo",lt );
		request.getRequestDispatcher("/goods/goods_mange.jsp").forward(request, response);
		
	}
	else
	if("goodsimg".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("goodsimg"));
		goodsinfo img=goodsDao.getimg(id);
        response.setContentType("image/png;charset=utf8");
		response.getOutputStream().write(img.getPictureData());
	}
	else
	if("delet".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("id"));
	     if(goodsDao.deletgoods(id)>0) {
	    	 request.setAttribute("deletmsg", "ɾ���ɹ�");
	    	 int index=Integer.parseInt(request.getParameter("pageindex")==null? "1":request.getParameter("pageindex"));
	 		int pagesize=20;
	 		int bigcateid=Integer.parseInt(request.getParameter("bigcateid")==null? "0":request.getParameter("bigcateid"));
	 		int smallcateid=Integer.parseInt(request.getParameter("smallcateid")==null? "0":request.getParameter("smallcateid"));;
	 		String goodsname=request.getParameter("goodsname");
	 		int pageconten=goodsDao.goodscount(bigcateid, smallcateid, goodsname);
	 		pageinfo page=pageutlie.getpageinfo(pagesize, pageconten, index);
	 		List lt=goodsDao.getgoods(bigcateid, smallcateid, goodsname, page.getBeginRow(), page.getPagesize());
	 		List cate=goodsDao.getcate();
	 		request.setAttribute("cate", cate);
	 		request.setAttribute("page", page);
	 		request.setAttribute("goodsinfo",lt );
	 		request.getRequestDispatcher("/goods/goods_mange.jsp").forward(request, response);
	     }else {
	    	 request.setAttribute("deletmsg", "ɾ��ʧ��");
	    	 int index=Integer.parseInt(request.getParameter("pageindex")==null? "1":request.getParameter("pageindex"));
	 		int pagesize=20;
	 		int bigcateid=Integer.parseInt(request.getParameter("bigcateid")==null? "0":request.getParameter("bigcateid"));
	 		int smallcateid=Integer.parseInt(request.getParameter("smallcateid")==null? "0":request.getParameter("smallcateid"));;
	 		String goodsname=request.getParameter("goodsname");
	 		int pageconten=goodsDao.goodscount(bigcateid, smallcateid, goodsname);
	 		pageinfo page=pageutlie.getpageinfo(pagesize, pageconten, index);
	 		List lt=goodsDao.getgoods(bigcateid, smallcateid, goodsname, page.getBeginRow(), page.getPagesize());
	 		List cate=goodsDao.getcate();
	 		request.setAttribute("cate", cate);
	 		request.setAttribute("page", page);
	 		request.setAttribute("goodsinfo",lt );
	 		request.getRequestDispatcher("/goods/goods_mange.jsp").forward(request, response);
	     }
		
	}

		
	
	
	
	
	
	
	}

}
