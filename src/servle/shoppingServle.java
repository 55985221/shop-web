package servle;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.admininfo;
import beans.goodsinfo;
import beans.pageinfo;
import beans.shopcartinfo;
import dao.goodsDao;
import utils.pageutlie;

/**
 * Servlet implementation class shoppingServle
 */
@WebServlet("/shoppingServle")
public class shoppingServle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingServle() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if("shoppingmange".equals(request.getParameter("msg"))) {
			getgoods(request,response);
			
			
		}
		else 
		if("shopcart".equals(request.getParameter("msg"))){
			int id=Integer.parseInt(request.getParameter("id"));
			double ab=0;
		    goodsinfo goods=goodsDao.getimg(id);
		    Map<Integer,shopcartinfo>  og= (Map)request.getSession().getAttribute("ordergoods");
		    if(og!=null) {
		      shopcartinfo sp=og.get(goods.getId());
		      if(sp!=null) {
		    	  sp.setGoodsNumber(sp.getGoodsNumber()+1);
		    	  sp.setGoodsAmount(sp.getGoodsAmount()+sp.getGoodsPrice());
		      }else {
		    	    sp=new shopcartinfo();
				     sp.setGoodsId(goods.getId());
				     sp.setGoodsName(goods.getGoodsName());
				     sp.setGoodsNumber(1);
				     admininfo admin=(admininfo)request.getSession().getAttribute("yonghu");
				     sp.setUserId(admin.getId());
				     
		      }
		      og.put(goods.getId(),sp);
		    }else {
		     og=new HashMap();
		     shopcartinfo si=new shopcartinfo();
		     si.setGoodsId(goods.getId());
		     si.setGoodsName(goods.getGoodsName());
		     si.setGoodsNumber(1);//没有做用户的那一块我直接用的管理员
		     si.setGoodsPrice(goods.getPrice());
		     si.setGoodsAmount(goods.getPrice());
		    
		     si.setUserId(1);
		     og.put(goods.getId(),si);
             
		    }
		    Iterator<shopcartinfo> It=og.values().iterator();
		    
		   while(It.hasNext()) {
			   shopcartinfo sh = It.next();
			   ab+=sh.getGoodsAmount();
		   }
		    System.err.println(request.getSession().getId());
		    request.setAttribute("ab", ab);
		    request.getSession().setAttribute("ordergoods", og);
		    request.getRequestDispatcher("/shopping/shopcart.jsp").forward(request, response);
		}
		else {
			
		}
	
	}
	public void getgoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int pageindex=Integer.parseInt(request.getParameter("pageindex")==null?"1":request.getParameter("pageindex"));
		int pagesize=40;
		int pagecount=goodsDao.goodscount(0, 0, null);
		pageinfo page=pageutlie.getpageinfo(pagesize, pagecount, pageindex);
		List lt=goodsDao.getgoods(0, 0, null, page.getBeginRow(), page.getPagesize());
	     request.setAttribute("page", page);
	     request.setAttribute("goods", lt);
	     request.getRequestDispatcher("/shopping/shopping_main.jsp").forward(request, response);
	}

}
