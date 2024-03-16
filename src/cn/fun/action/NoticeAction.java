package cn.fun.action;

import cn.fun.common.BaseAction;
import cn.fun.entity.Notice;
import cn.fun.service.BizService;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class NoticeAction extends BaseAction implements ModelDriven<Notice> {
	private String		actionname1		= "公告";
	private String		actionclass1	= "Notice";
	@Autowired
	private BizService	service;

	private int			uid;
	private Notice			bean			= new Notice();

	@Action(value = "add2Notice", results = { @Result(name = "add2", location = "/ahtml/addNotice.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getNotice", results = { @Result(name = "getOne", location = "/ahtml/modifyNotice.jsp") })
	public String get() {
		try {
			Notice temp = (Notice) service.get(Notice.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteNotice")
	public String delete() {
		try {
			service.delete(Notice.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryNotice");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateNotice")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryNotice");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addNotice")
	public String add() {
		try {
			bean.setAddDate(DateUtil.getCurrentTime());
			service.add(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainqueryNotice");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "queryNotice", results = { @Result(name = "queryList", location = "/ahtml/listNotice.jsp") })
	public String query() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageCurrent");
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

			// 字段名称集合
			LinkedList<String> parmnames = new LinkedList<String>();
			// 字段值集合
			LinkedList<Object> parmvalues = new LinkedList<Object>();
			// 页面参数集合
			Set valueset = getHttpServletRequest().getParameterMap().entrySet();
			for (Object o : valueset) {
				Entry<String, Object> e = (Entry<String, Object>) o;
				String name = e.getKey();// 页面字段名称
				if (name.startsWith("s_")) {
					String fieldValue = getHttpServletRequest().getParameter(name);// 页面字段值
					if (StringUtil.notEmpty(fieldValue)) {
						name = name.substring(2, name.length());// 实体字段名称
						parmnames.add(name);
						parmvalues.add(FieldUtil.format(Notice.class, name, fieldValue));
						textmap.put(name.replaceAll("\\.", ""), fieldValue);
					}
				}
			}

			SearchParamBean sbean = new SearchParamBean();
			sbean.setParmnames(parmnames);
			sbean.setParmvalues(parmvalues);

			p.setConditonObject(sbean);

			Page page = null;
			page = service.find(p, Notice.class);

			putRequestValue("textmap", textmap);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Override
	public Notice getModel() {
		return bean;
	}
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public BizService getService() {
		return service;
	}

	public Notice getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Notice bean) {
		this.bean = bean;
	}

	public String getActionname1() {
		return actionname1;
	}

	public void setActionname1(String actionname1) {
		this.actionname1 = actionname1;
	}

	public String getActionclass1() {
		return actionclass1;
	}

	public void setActionclass1(String actionclass1) {
		this.actionclass1 = actionclass1;
	}

}
