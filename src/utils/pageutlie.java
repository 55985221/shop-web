package utils;

import beans.pageinfo;

public class pageutlie {
public static pageinfo getpageinfo(int pagesize,int pagerowcount,int pageindex) {
	pageinfo page=new pageinfo();
	page.setPagesize(pagesize==0?10:pagesize);
	page.setRowCount(pagerowcount);
	page.setPageIndex(pageindex);
	page.setPageCount((page.getRowCount()+page.getPagesize()-1)/page.getPagesize());
	page.setBeginRow(page.getPagesize()*(page.getPageIndex()-1));
	page.setHasNext(page.getPageIndex()<page.getPageCount());
	page.setHasPre(page.getPageIndex()>1);
	
 return page;
}
}
