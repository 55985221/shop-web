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

import dao.goodsDao;
import net.sf.json.JSONArray;

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
		goods.setPictureDate(imgs);
		goods.setDes(request.getParameter("des"));
		if(goodsDao.goodsadd(goods)>0) {
			request.setAttribute("goods", goods);
			request.setAttribute("tishi", "添加成功");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}else {
			request.setAttribute("goods", goods);
			request.setAttribute("tishi", "添加成功");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}
	}
	
	}

}
