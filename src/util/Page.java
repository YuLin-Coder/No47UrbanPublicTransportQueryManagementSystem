package  util;

import java.util.Collection;

/**
 * 分页工具类
 * 
 */
public class Page {

	private int currentPageNumber = 1; // 当前页面数,当前是第几页

	/* private int Countitem; */
	private int totalPage; // 总的页面数

	private long totalNumber; // 总的记录数

	private int itemInPage; // 当前页面的第一条记录数（编号）

	private int itemsPerPage; // 每一页的记录数

	private boolean next; // 是否还有下一页

	private boolean previous; // 是否还有上一页

	private Collection<?> list; // 当前页面记录集合

	private Object conditonObject; // 查询条件对象

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public void setItemInPage(int itemInPage) {
		this.itemInPage = itemInPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public void setConditonObject(Object conditonObject) {
		this.conditonObject = conditonObject;
	}

	public Object getConditonObject() {
		return conditonObject;
	}
	public Page(){
		
	}
	public Page(int totalNumber, int itemPerPage) {
		this.totalNumber = totalNumber;
		this.itemsPerPage = itemPerPage;
		this.totalPage = (totalNumber + itemPerPage - 1) / itemPerPage;
		// if (totalNumber == 0)
		// this.currentPageNumber = 0;
		flush();
	}

	/**
	 * 刷新页面属性：刷新上一页和下一页的状态
	 */
	private void flush() {
		this.setNext();
		this.setPrevious();
		this.itemInPage = (currentPageNumber - 1) * itemsPerPage;
	}

	private void setNext() {

		if (this.currentPageNumber >= this.totalPage) {
			this.next = false;
		} else {
			this.next = true;
		}
	}

	private void setPrevious() {
		if (currentPageNumber == 1 || currentPageNumber == 0) {
			this.previous = false;
		} else {
			this.previous = true;
		}
	}

	/**
	 * 返回中的记录数
	 * 
	 * @return
	 */
	public long getTotalNumber() {
		return totalNumber;
	}

	// public void setTotalNumber(int totalNumber) {
	// this.totalNumber = totalNumber;
	// }

	public int getItemInPage() {
		return itemInPage;
	}

	// public void setItemInPage(int itemInPage) {
	// this.itemInPage = itemInPage;
	// }

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	// public void setItemsPerPage(int itemsPerPage) {
	// this.itemsPerPage = itemsPerPage;
	// }

	/**
	 * 是否有下一页
	 * 
	 * @return 如果有下一页，返回true，否则返回false
	 */
	public boolean isNext() {
		return next;
	}

	/**
	 * 是否有上一页
	 * 
	 * @return 如果有上一页，返回true，否则返回false
	 */
	public boolean isPrevious() {
		return previous;
	}

	/**
	 * 得到结果集
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Collection getList() {
		return list;
	}

	/**
	 * 设置结果集
	 * 
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public void setList(Collection list) {
		this.list = list;
	}

	public int getTotalPage() {
		this.totalPage = (int) ((totalNumber + this.itemsPerPage - 1) / this.itemsPerPage);
		return totalPage;
	}

	// public void setTotalPage(int totalPage) {
	// this.totalPage = totalPage;
	// }

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * 设置当前要查找的页面值
	 * 
	 * @param currentPageNumber
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		// 如果设置的页面值<=0，则将当前页面值设置为1
		if (currentPageNumber <= 0) {
			this.currentPageNumber = 1;
			flush();
			return;
		}
		// 如果设置的页面值比总的页数值大，则将当前页面设置为最大页面值（总的页面值）
		if (currentPageNumber >= totalPage) {
			if (totalPage > 0) {
				this.currentPageNumber = this.totalPage;
				flush();
				return;
			} else {
				this.currentPageNumber = 1;
			}
		}

		this.currentPageNumber = currentPageNumber;
		flush();
	}

}
