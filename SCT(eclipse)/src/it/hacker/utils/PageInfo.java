package it.hacker.utils;

import java.util.List;

/*
 * 通用的页面分页管理
 */
public class PageInfo<T> {
	private Integer pageNo;
	private int pageSize = 15;
	private Long totalCount;
	private List<T> list;
	
	public PageInfo(Integer pageNo) {
		super();
		//当pageNo参数为空的时候，默认pageNo为1；
		if(pageNo == 0 || pageNo == null) {
			this.pageNo = 1;
		}else {
			this.pageNo = pageNo;
		}
	}

	//获取总页数
	public Long getTotalPage() {
		Long totalPage = totalCount/pageSize;
		if(totalPage == 0 | totalCount%pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}
	//获取下一页
	public int getNextPage() {
		Integer nextPage = 0;
		if(pageNo<this.getTotalPage()) {
			nextPage = pageNo+1;
		}else {
			nextPage = pageNo;
		}
		return nextPage;
	}
	//获取上一页
	public int getPrePage() {
		Integer prePage = 0;
		if(pageNo>1) {
			prePage = pageNo-1;
		}else {
			prePage = pageNo;
		}
		return prePage;
	}
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
}
