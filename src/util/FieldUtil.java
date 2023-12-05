package util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class FieldUtil {
	/**
	 * 把fieldValue转换为c类中fieldName属性对应的类型
	 * 
	 * @param c
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static Object format(Class c, String fieldName, String fieldValue) throws Exception {
		Field f = null;
		if (fieldName.contains(".")) {
			String[] ary = fieldName.split("\\.");
			Class tempclass = c;
			for (int i = 0; i < ary.length - 1; i++) {
				f = tempclass.getDeclaredField(ary[i]);
				tempclass = f.getType();
			}
			f = tempclass.getDeclaredField(ary[ary.length - 1]);
		} else {
			f = c.getDeclaredField(fieldName);
		}

		String type = f.getType().getSimpleName();
		if (type.equals("int") || type.equals("Integer")) {
			return Integer.valueOf(fieldValue);
		} else if (type.equals("long") || type.equals("Long")) {
			return Long.valueOf(fieldValue);
		} else if (type.equals("float") || type.equals("Float")) {
			return Float.valueOf(fieldValue);
		} else if (fieldName.endsWith("Time") || fieldName.endsWith("Date")) {
			return fieldValue;
		} else {
			return "%" + fieldValue + "%";
		}
	} 

	public static Page createPage(HttpServletRequest request, Class c, LinkedList<String> parmnames, LinkedList<Object> parmvalues)
			throws Exception {
		int pageNum = 0;
		String t = request.getParameter("pageCurrent");
		if (StringUtil.notEmpty(t)) {
			pageNum = Integer.valueOf(t);
		}
		Page p = new Page();
		//if (pageNum == 1 || p == null) {
		p = new Page();
		p.setCurrentPageNumber(pageNum);
		p.setTotalNumber(0l);
		p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);

		Map<String, String> textmap = new HashMap<String, String>();

		// 页面参数集合
		Set valueset = request.getParameterMap().entrySet();
		for (Object o : valueset) {
			Entry<String, Object> e = (Entry<String, Object>) o;
			String name = e.getKey();// 页面字段名称
			if (name.startsWith("s_")) {
				String fieldValue = request.getParameter(name);// 页面字段值
				if (StringUtil.notEmpty(fieldValue)) {
					name = name.substring(2, name.length());// 实体字段名称
					parmnames.add(name);
					parmvalues.add(FieldUtil.format(c, name, fieldValue));
					textmap.put(name.replaceAll("\\.", ""), fieldValue);
				}
			}
		}
		SearchParamBean sbean = new SearchParamBean();
		sbean.setParmnames(parmnames);
		sbean.setParmvalues(parmvalues);

		p.setConditonObject(sbean);
		request.setAttribute("textmap", textmap);
		return p;
	}

	//	@Action(value = "queryApply", results = { @Result(name = "queryList", location = "/ahtml/listApply.jsp") })
	//	public String query() {
	//		try {
	//			int pageNum = 0;
	//			String t = getHttpServletRequest().getParameter("pageCurrent");
	//			if (StringUtil.notEmpty(t)) {
	//				pageNum = Integer.valueOf(t);
	//			}
	//			Page p = new Page();
	//			//if (pageNum == 1 || p == null) {
	//			p = new Page();
	//			p.setCurrentPageNumber(pageNum);
	//			p.setTotalNumber(0l);
	//			p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);
	//
	//			Map<String, String> textmap = new HashMap<String, String>();
	//
	//			// 字段名称集合
	//			LinkedList<String> parmnames = new LinkedList<String>();
	//			// 字段值集合
	//			LinkedList<Object> parmvalues = new LinkedList<Object>();
	//			// 页面参数集合
	//			Set valueset = getHttpServletRequest().getParameterMap().entrySet();
	//			for (Object o : valueset) {
	//				Entry<String, Object> e = (Entry<String, Object>) o;
	//				String name = e.getKey();// 页面字段名称
	//				if (name.startsWith("s_")) {
	//					String fieldValue = getHttpServletRequest().getParameter(name);// 页面字段值
	//					if (StringUtil.notEmpty(fieldValue)) {
	//						name = name.substring(2, name.length());// 实体字段名称
	//						parmnames.add(name);
	//						parmvalues.add(FieldUtil.format(Apply.class, name, fieldValue));
	//						textmap.put(name.replaceAll("\\.", ""), fieldValue);
	//					}
	//				}
	//			}
	//			parmnames.add("student.id");
	//			parmvalues.add(getSimpleUser().getId());
	//
	//			SearchParamBean sbean = new SearchParamBean();
	//			sbean.setParmnames(parmnames);
	//			sbean.setParmvalues(parmvalues);
	//
	//			p.setConditonObject(sbean);
	//
	//			Page page = null;
	//			page = service.find(p, Apply.class);
	//
	//			putRequestValue("textmap", textmap);
	//
	//			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
	//			return "queryList";
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//			return ERROR;
	//		}
	//	}

}
