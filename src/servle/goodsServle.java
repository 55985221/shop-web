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

import DButil.DButil;
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
		request.setCharacterEncoding("UTF-8");
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
	if("goodsadd".equals(request.getParameter("msg"))) {
		 goodsinfo goods=goodsadd(request,response);
		if(goodsDao.goodsadd(goods)>0) {
			request.setAttribute("tishi", "添加成功");
		}else {
			request.setAttribute("tishi", "添加失败");
		}
		request.setAttribute("goods", goods);
		request.setAttribute("cate", goodsDao.getcate(0));
		request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
	}
	else
	if("manage".equals(request.getParameter("msg"))) {
		goodsmange(request, response);
	}
	else
	if("goodsimg".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("goodsimg"));
		goodsinfo img=goodsDao.getimg(id);
        response.setContentType("image/png;charset=utf8");
        if(img.getPictureData()!=null) {
		response.getOutputStream().write(img.getPictureData());}
	}
	else
	if("delet".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("id"));
	     if(goodsDao.deletgoods(id)>0) {
	    	 request.setAttribute("deletmsg", "删除成功");
	     }else {
	    	 request.setAttribute("deletmsg", "删除失败");
	     }
	     goodsmange(request, response);
		
	}
	else
	if("updata".equals(request.getParameter("msg"))) {
		int id=Integer.parseInt(request.getParameter("id"));
		request.setAttribute("goods",goodsDao.getimg(id));
		List cate=goodsDao.getcate();
		request.setAttribute("cate", cate);
		request.getRequestDispatcher("/goods/goods_updata.jsp").forward(request,response);
	}
	else
	if("goodsup".equals(request.getParameter("msg"))) {
		goodsinfo goods=goodsadd(request, response);
		goods.setId(Integer.parseInt(request.getParameter("goodid")));
		int id=goodsDao.goodsupdata(goods);
		if(id>0){
			request.setAttribute("upmsg", "修改成功");
		}else {
			request.setAttribute("upmsg", "修改失败");
		}
		request.setAttribute("goods",goodsDao.getimg(id));
		List cate=goodsDao.getcate();
		request.setAttribute("cate", cate);
		request.getRequestDispatcher("/goods/goods_updata.jsp").forward(request,response);
	}
	

		
	
	
	
	
	
	
	}
	public goodsinfo goodsadd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part pr= request.getPart("ims");
		InputStream img= pr.getInputStream();
		goodsinfo goods=new goodsinfo();
		if(img.available()>0){
		byte[] imgs=new byte[img.available()];
		img.read(imgs);
		goods.setPictureData(imgs);
		}
		goods.setGoodsName(request.getParameter("goodsName"));
		goods.setBigCateId(Integer.parseInt(request.getParameter("bigcateid")));
		goods.setSmallCateId(Integer.parseInt(request.getParameter("smallcateid")));
		goods.setUnit(request.getParameter("unit"));
		goods.setPrice(Double.parseDouble(request.getParameter("price")));
		goods.setProducter(request.getParameter("pd"));
		goods.setDes(request.getParameter("des"));
	    return	goods;
	}
	public void goodsmange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
