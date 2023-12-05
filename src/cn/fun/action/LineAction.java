package cn.fun.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;
import cn.fun.common.BaseAction;
import cn.fun.entity.Line;
import cn.fun.entity.LineStation;
import cn.fun.entity.LineStationDistance;
import cn.fun.entity.Station;
import cn.fun.service.BizService;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class LineAction extends BaseAction implements ModelDriven<Line> {
	private String		actionname1		= "线路";
	private String		actionclass1	= "Line";
	@Autowired
	private BizService	service;

	private int			uid;
	private Line		bean			= new Line();

	@Action(value = "getLineDistance", results = { @Result(name = "add2", location = "/ahtml/getLineDistance.jsp") })
	public String getLineDistance() {
		Line temp = (Line) service.get(Line.class, uid);
		putRequestValue("modifybean", temp);

		List<LineStation> list = service.queryByHQL("from LineStation where line.id=? order by staindex", uid);
		List<LineStationDistance> dlist = new ArrayList<LineStationDistance>();
		for (int i = 0; i < list.size() - 1; i++) {
			Station s1 = list.get(i).getStation();
			Station s2 = list.get(i + 1).getStation();
			LineStationDistance d = service.unique("from LineStationDistance where station1.id=? and station2.id=? and line.id=?", s1.getId(),
					s2.getId(),uid);
			if (d == null) {
				d = new LineStationDistance();
				d.setStation1(s1);
				d.setStation2(s2);
			}
			dlist.add(d);
		}

		putRequestValue("list", dlist);
		return "add2";
	}

	@SuppressWarnings("unchecked")
	@Action(value = "updateLineStation")
	public String updateLineStation() {
		try {
			Line temp = (Line) service.get(Line.class, uid);
			putRequestValue("modifybean", temp);

			List<LineStation> list = service.queryByHQL("from LineStation where line.id=? order by staindex", uid);
			List<LineStationDistance> dlist = new ArrayList<LineStationDistance>();
			for (int i = 0; i < list.size() - 1; i++) {
				Station s1 = list.get(i).getStation();
				Station s2 = list.get(i + 1).getStation();
				LineStationDistance d = new LineStationDistance();
				d.setStation1(s1);
				d.setStation2(s2);
				d.setDlen(Double.valueOf(getHttpServletRequest().getParameter("dlen_" + s1.getId() + "_" + s2.getId())));
				d.setLine(temp);
				dlist.add(d);
			}
			service.updateLineDistance(temp,dlist);
			MessageUtil.addMessage(getHttpServletRequest(), "更新成功");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "add2Line", results = { @Result(name = "add2", location = "/ahtml/addLine.jsp") })
	public String add2() {
		List<?> list = service.queryByHQL("from Station order by name");
		putRequestValue("list", list);
		return "add2";
	}

	@Action(value = "getLine", results = { @Result(name = "getOne", location = "/ahtml/modifyLine.jsp") })
	public String get() {
		try {
			Line temp = (Line) service.get(Line.class, uid);
			putRequestValue("modifybean", temp);

			List<LineStation> list = service.queryByHQL("from LineStation where line.id=? order by staindex", uid);
			String ls = "";
			for (LineStation l : list) {
				ls += l.getStation().getName() + ", ";
			}
			putRequestValue("lineStationList", ls);

			List<?> list1 = service.queryByHQL("from Station order by name");
			putRequestValue("list", list1);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteLine")
	public String delete() {
		try {
			service.delete(Line.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryLine");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateLine")
	public String update() {
		try {
			String lineStationList = getHttpServletRequest().getParameter("lineStationList");
			String ary[] = lineStationList.split(",");
			List<LineStation> list = new ArrayList<LineStation>();
			int index = 1;
			String stations = ">";
			for (String a : ary) {
				if (StringUtils.isBlank(a)) {
					continue;
				}
				for (LineStation s : list) {
					if (s.getStation().getName().equals(a.trim())) {
						MessageUtil.addMessage(getRequest(), "添加失败,站点重复: " + a);
						return ERROR;
					}
				}
				Station station = service.unique("from Station where name=?", a.trim());
				if (station == null) {
					MessageUtil.addMessage(getRequest(), "添加失败,站点未找到: " + a);
					return ERROR;
				}
				LineStation ls = new LineStation();
				ls.setStaindex(index++);
				ls.setStation(station);
				list.add(ls);
				stations += a.trim() + ">";
			}
			if (list.size() < 2) {
				MessageUtil.addMessage(getRequest(), "添加失败,站点数量太少");
				return ERROR;
			}
			bean.setStations(stations);
			bean.setStartSta(list.get(0).getStation());
			bean.setEndSta(list.get(list.size() - 1).getStation());
			service.updateLine(bean, list);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.请补充线路站点的距离信息", "mainqueryLine");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addLine")
	public String add() {
		try {
			String lineStationList = getHttpServletRequest().getParameter("lineStationList");
			String ary[] = lineStationList.split(",");
			List<LineStation> list = new ArrayList<LineStation>();
			int index = 1;
			String stations = ">";
			for (String a : ary) {
				if (StringUtils.isBlank(a)) {
					continue;
				}
				for (LineStation s : list) {
					if (s.getStation().getName().equals(a.trim())) {
						MessageUtil.addMessage(getRequest(), "添加失败,站点重复: " + a);
						return ERROR;
					}
				}
				Station station = service.unique("from Station where name=?", a.trim());
				if (station == null) {
					MessageUtil.addMessage(getRequest(), "添加失败,站点未找到: " + a);
					return ERROR;
				}
				LineStation ls = new LineStation();
				ls.setStaindex(index++);
				ls.setStation(station);
				list.add(ls);
				stations += a.trim() + ">";
			}
			if (list.size() < 2) {
				MessageUtil.addMessage(getRequest(), "添加失败,站点数量太少");
				return ERROR;
			}
			bean.setStations(stations);
			bean.setStartSta(list.get(0).getStation());
			bean.setEndSta(list.get(list.size() - 1).getStation());
			service.addLine(bean, list);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.请补充线路站点的距离信息", "mainqueryLine");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "queryLine", results = { @Result(name = "queryList", location = "/ahtml/listLine.jsp") })
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
						parmvalues.add(FieldUtil.format(Line.class, name, fieldValue));
						textmap.put(name.replaceAll("\\.", ""), fieldValue);
					}
				}
			}

			SearchParamBean sbean = new SearchParamBean();
			sbean.setParmnames(parmnames);
			sbean.setParmvalues(parmvalues);

			p.setConditonObject(sbean);

			Page page = null;
			page = service.find(p, Line.class);

			putRequestValue("textmap", textmap);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Line getModel() {
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

	public Line getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Line bean) {
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
