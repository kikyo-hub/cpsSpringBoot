package cn.nrzt.cps.web;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class PageResult<T> {
	private int pageSize=0;
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	private int pageNo =0;
	private long totalCount=0;
	private int totalPage=0;
	private List<T> data =new ArrayList<T>();	
	public PageResult(List<T> list) {
		PageInfo<T> page =new PageInfo<T>(list);
		this.data =page.getList();
		this.pageNo=page.getPageNum();
		this.pageSize=page.getPageSize();
		this.totalCount=page.getTotal();
		this.totalPage=page.getPages();
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
