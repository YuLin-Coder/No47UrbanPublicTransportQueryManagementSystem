package cn.fun.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.fun.common.BaseAction;
import cn.fun.entity.LineBean;
import cn.fun.entity.SessionBean;
import cn.fun.entity.SimpleUser;
import cn.fun.entity.Station;
import cn.fun.entity.User;
import cn.fun.service.BizService;
import util.Constant;
import util.FieldUtil;
import util.MD5;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;

@ParentPackage("json-default")
@Namespace("/com")
@Component
public class WebAction extends BaseAction {
	@Autowired
	private BizService	service;

	private int			uid;
	private String		type;
	private String		name;
	private String		password;

	@Action(value = "gongjiao", results = { @Result(name = "gongjiao", location = "/web/gongjiao.jsp"), })
	public String gongjiao() {
		List<?> staList = service.queryByHQL("from Station order by name");
		putRequestValue("staList", staList);
		String station1 = getHttpServletRequest().getParameter("station1");
		String station2 = getHttpServletRequest().getParameter("station2");
		if (StringUtils.isBlank(station1) || StringUtils.isBlank(station2)) {
			return "gongjiao";
		}
		Station s1 = service.unique("from Station where name=?", station1.trim());
		Station s2 = service.unique("from Station where name=?", station2.trim());

		if (s1 == null || s2 == null) {
			return "gongjiao";
		}

		List<LineBean> beans = service.findPath(s1, s2);

		putRequestValue("list", beans);
		putRequestValue("station1", station1);
		putRequestValue("station2", station2);
		return "gongjiao";

	}

	@Action(value = "xianlu", results = { @Result(name = "queryList", location = "/web/xianlus.jsp"), })
	public String xianlu() {
		try {
			List<?> staList = service.queryByHQL("from Station order by name");
			putRequestValue("staList", staList);
			String sid = getHttpServletRequest().getParameter("sid");
			String station = getHttpServletRequest().getParameter("station");
			String hql = "from Line where 1=1";
			if (StringUtils.isNotBlank(sid)) {
				hql += " and sid like '%" + sid.trim() + "%'";
			}
			if (StringUtils.isNotBlank(station)) {
				hql += " and stations like '%>" + station.trim() + ">%'";
			}
			List list = service.queryByHQL(hql);
			putRequestValue("list", list);
			putRequestValue("sid", sid);
			putRequestValue("station", station);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}

	}

	@Action(value = "user", results = { @Result(name = "main", type = "redirect", location = "/main.jsp"),
			@Result(name = "index", type = "redirect", location = "/com/index.action") })
	public String touser() {
		SimpleUser su = (SimpleUser) getHttpSession().getAttribute("SimpleUser");
		if (su == null) {
			return "index";
		}
		SessionBean sb = new SessionBean();
		sb.setUser(su);

		getHttpSession().setAttribute(Constant.SESSION_BEAN, sb);
		return "main";

	}

	private String checkcode;

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	@Action(value = "userLogin")
	public String userLogin() {
		String msg = "用户名或者密码错误";
		String code = (String) getHttpSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (code == null) {
			getHttpServletRequest().getSession(false).invalidate();
			return "index";
		} else {
		}
		checkcode = StringUtil.stringVerification(checkcode);
		if (!code.toLowerCase().equals(checkcode.toLowerCase())) {
			msg = "验证码错误";
		} else {
			try {
				MD5 md = new MD5();
				password = md.getMD5ofStr(password);
				SimpleUser user = (SimpleUser) service.findUser(SimpleUser.class.getSimpleName(), name, password);
				if (user != null) {
					getHttpSession().setAttribute("SimpleUser", user);
					msg = "成功";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String retmsg = "{\"msg\":\"" + msg + "\"}";
		try {
			getHttpServletResponse().getWriter().write(retmsg);
			getHttpServletResponse().getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SimpleUser regbean;

	@Action(value = "userReg")
	public String userReg() {
		String msg = "";
		String code = (String) getHttpSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (code == null) {
			getHttpServletRequest().getSession(false).invalidate();
			return "index";
		} else {
		}
		checkcode = StringUtil.stringVerification(checkcode);
		if (!code.toLowerCase().equals(checkcode.toLowerCase())) {
			msg = "验证码错误";
		} else {
			try {
				User user = service.findUser(regbean.getUser().getUname());
				if (user != null) {
					msg = "注册失败,账号已经被使用";
				} else {
					service.addSimpleUser(regbean);
					msg = "成功";
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg = "注册失败";
			}
		}
		//return "{\"msg\":\"" + msg + "\"}";
		String retmsg = "{\"msg\":\"" + msg + "\"}";
		try {
			getHttpServletResponse().setCharacterEncoding("UTF-8");
			getHttpServletResponse().getWriter().write(retmsg);
			getHttpServletResponse().getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Action(value = "index", results = { @Result(name = "index", location = "/web/zhanDian.jsp") })
	public String index() {
		try {
			List<?> list = service.queryByHQL("from Station order by name");
			putRequestValue("staList", list);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "userLogout", results = { @Result(name = "index", location = "/web/zhanDian.jsp") })
	public String userLogout() {
		try {
			getHttpSession().invalidate();
			List<?> list = service.queryByHQL("from Station order by name");
			putRequestValue("staList", list);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "line", results = { @Result(name = "index", location = "/web/xialu.jsp") })
	public String line() {
		try {
			List<?> list = service.queryByHQL("from Line");
			putRequestValue("lineList", list);
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public BizService getService() {
		return service;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public int getUid() {
		return uid;
	}

	public String getType() {
		return type;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public SimpleUser getRegbean() {
		return regbean;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegbean(SimpleUser regbean) {
		this.regbean = regbean;
	}

}
