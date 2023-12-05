package  util;

import java.util.LinkedList;

/**
 * 用户高级搜索的参数记录
 * 
 */
public class SearchParamBean {

	private LinkedList<String> parmnames;// 参数名称
	private LinkedList<Object> parmvalues;// 参数值

	public LinkedList<String> getParmnames() {
		return parmnames;
	}

	public void setParmnames(LinkedList<String> parmnames) {
		this.parmnames = parmnames;
	}

	public LinkedList<Object> getParmvalues() {
		return parmvalues;
	}

	public void setParmvalues(LinkedList<Object> parmvalues) {
		this.parmvalues = parmvalues;
	}

}
