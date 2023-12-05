package cn.fun.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import cn.fun.entity.Station;
import cn.fun.service.BizService;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class StationAction extends BaseAction implements ModelDriven<Station> {
	private String		actionname1		= "站点";
	private String		actionclass1	= "Station";
	@Autowired
	private BizService	service;

	private int			uid;
	private Station		bean			= new Station();

	@Action(value = "add2Station", results = { @Result(name = "add2", location = "/ahtml/addStation.jsp") })
	public String add2() {
		//		String ss = "会沟村 侯家寨 侯家寨东 化客头 化客头中学 化章堡北 化章街口 化肥厂 化肥厂小区 化肥小区 化肥小区南 华宇购物广场 华玺生活家居广场 华能电厂 后北屯 后所营 后水峪 后沟村 后湾 后王村 后王街 后营街 呼延村 呼延村南 呼延村招呼站 和信摩尔 和平公园 和平公园 和平北路三给街口 和平北路北中环口 和平北路北中环街口 和平北路北排洪渠口 和平北路漪汾街口 和平北路玉河街口 和平北路瓦窑街口 和平北路西矿街口 和平北路选煤街口 和平北路金桥西街口 和平南路南内环口 和平南路南内环街口 和平南路南内环西街口 和平南路小井峪街口 和平南路长风街口 和平南路长风西街口 恒山路建工街口 恒山路文明街口 恒山路新店街口 旱西关 旱西关街金刚堰路口 旱西门 横岭村 横渠 横渠西 汉庭酒店 汉庭酒店汇通北路店 河北里 河底招呼站 河滩村 河里头 河龙湾 河龙湾商场 河龙湾招呼站 海天汽配城 海天汽配城 海棠大厦 火车南站 火车站东 环兴路口 环湖东路健康北街口 环湖东路健康南街口 皇后园 红寺村 红杉药业 红杉药业 红楼站 红沟路五龙口街口 红沟路大东关街口 红沟路府东街口 红沟路白龙庙街口 花园后街 花塔 花塔村 虎胜街 豪景老年公寓 郝家沟 郝庄中学 郝村 韩武 韩武堡 黄坡烈士陵园 黄楼村 黄河京都大酒店 黄花园 黑城营 黑驼村";
		//		String[] ary = ss.split(" ");
		//		for (String a : ary) {
		//			try {
		//				Station s = new Station();
		//				s.setName(a.trim());
		//				service.add(s);
		//			} catch (Exception e) {
		//				e.printStackTrace();
		//			}
		//		}
		return "add2";
	}

	@Action(value = "getStation", results = { @Result(name = "getOne", location = "/ahtml/modifyStation.jsp") })
	public String get() {
		try {
			Station temp = (Station) service.get(Station.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteStation")
	public String delete() {
		try {
			service.delete(Station.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryStation");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateStation")
	public String update() {
		try {
			bean.setName(bean.getName().trim());
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryStation");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addStation")
	public String add() {
		try {
			bean.setName(bean.getName().trim());
			service.add(bean);
			MessageUtil.addRelMessage(getHttpServletRequest(), "添加成功.", "mainqueryStation");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	@Action(value = "queryStation", results = { @Result(name = "queryList", location = "/ahtml/listStation.jsp") })
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
						parmvalues.add(FieldUtil.format(Station.class, name, fieldValue));
						textmap.put(name.replaceAll("\\.", ""), fieldValue);
					}
				}
			}

			SearchParamBean sbean = new SearchParamBean();
			sbean.setParmnames(parmnames);
			sbean.setParmvalues(parmvalues);

			p.setConditonObject(sbean);

			Page page = null;
			page = service.find(p, Station.class);

			putRequestValue("textmap", textmap);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public Station getModel() {
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

	public Station getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Station bean) {
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
