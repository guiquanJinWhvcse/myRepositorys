package com.sssg.utils;
import java.util.List;
public class Page<T> {   
	private int total;   //������    Ҫ����dao���ѯ
	private int page;     //��ǰҳ��     ��ǰ��ҳ�洫����
	private int size;     //ÿҳ��ʾ�Ŀͻ���Ϣ������ʹ�����Լ�����
	private List<T> rows; //��ҳ��Ľ����         Ҫ����dao���ѯ
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}  
}
