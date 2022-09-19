package com.jxut.util;

public class PageUtil {
	private int pageSize=1;//ÿҳ��ʾ������
	private int totalSize;// ��������
	private int totalPage;// ��ҳ��
	private int currentPage;// ��ǰҳ
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalSize) {
		if(totalSize%pageSize==0){
			this.totalPage = totalSize/pageSize;
		}else{
			this.totalPage = totalSize/pageSize+1;
		}
		
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
