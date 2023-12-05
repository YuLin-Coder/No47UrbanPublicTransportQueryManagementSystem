package cn.fun.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.MD5;
import util.MessageUtil;
import util.StringUtil;
import cn.fun.common.BaseAction;
import cn.fun.entity.SessionBean;
import cn.fun.entity.SimpleUser;
import cn.fun.entity.SysUser;
import cn.fun.entity.User;
import cn.fun.service.BizService;

@ParentPackage("struts-default")
@Namespace("/com")
@Component
public class SignAction extends BaseAction {
	@Autowired
	private BizService service;
	private SimpleUser regbean;

	public SimpleUser getRegbean() {
		return regbean;
	}

	public void setRegbean(SimpleUser regbean) {
		this.regbean = regbean;
	}

	@Action(value = "/reg", results = { @Result(name = "register", type = "redirect", location = "/reg.jsp"), })
	public String reg() throws Exception {
		String msg = "";
		try {
			service.addSimpleUser(regbean);
			msg = "注册成功";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "注册失败";
		}
		getHttpSession().setAttribute("regErrorMessage", msg);
		return "register";
	}

	@Action(value = "/login", results = { @Result(name = "index", type = "redirect", location = "/index.jsp"),
			@Result(name = "main",type = "redirect", location = "/main.jsp") })
	public String login() throws Exception {
		log.info(loginid + " " + password + " " + logintype);
		String errorMessage = null;
		try {
			String code = (String) getHttpSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (code == null) {
				getHttpServletRequest().getSession(false).invalidate();
				return "index";
			} else {
			}
			checkcode = StringUtil.stringVerification(checkcode);
			if (code.toLowerCase().equals(checkcode.toLowerCase())) {
				loginid = StringUtil.stringVerification(loginid).toLowerCase();
				MD5 md = new MD5();
				password = md.getMD5ofStr(password);
				Object user = service.findUser(logintype, loginid, password);
				if (user != null) {

					SessionBean sb = new SessionBean();
					sb.setUser(user);

					getHttpSession().setAttribute(Constant.SESSION_BEAN, sb);

					log.info("登录成功:" + loginid);

				} else {
					errorMessage = "登录帐号或者密码错误";
				}
			} else {
				errorMessage = "验证码错误";
			}
			if (errorMessage == null) {
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (errorMessage == null) {
			errorMessage = "登录失败,请稍候重试";
		}
		getHttpSession().setAttribute("signErrorMessage", errorMessage);
		return "index";
	}

	@Action(value = "toSelf", results = { @Result(name = "modifySelf", location = "/ahtml/modifySelf.jsp") })
	public String toSelf() {
		return "modifySelf";
	}

	private User bean;

	@Action(value = "modifySelf")
	public String modifySelf() {
		try {
			User sessionUser = null;
			SessionBean sb = (SessionBean) getSessionValue(Constant.SESSION_BEAN);
			Object sbuser = sb.getUser();
			if (SysUser.class.getSimpleName().equals(sb.getRole())) {
				sessionUser = ((SysUser) sbuser).getUser();
			} else if (SimpleUser.class.getSimpleName().equals(sb.getRole())) {
				sessionUser = ((SimpleUser) sbuser).getUser();
			}
			bean.setUserId(sessionUser.getUserId());
			bean.setUname(sessionUser.getUname());
			if (StringUtil.notEmpty(bean.getUserPassword())) {
				MD5 md = new MD5();
				String password = md.getMD5ofStr(bean.getUserPassword());
				bean.setUserPassword(password);
			} else {
				bean.setUserPassword(sessionUser.getUserPassword());
			}
			service.update(bean);

			sessionUser.setUserAddress(bean.getUserAddress());
			sessionUser.setUserBirth(bean.getUserBirth());
			sessionUser.setUserEmail(bean.getUserEmail());
			sessionUser.setUserGender(bean.getUserGender());
			sessionUser.setUserName(bean.getUserName());
			sessionUser.setUserPhone(bean.getUserPhone());

			MessageUtil.addMessage(getHttpServletRequest(), "修改成功.");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getHttpServletRequest(), "修改失败.");
			return ERROR;
		}
	}

	@Action(value = "logout", results = { @Result(name = "logout", type = "redirect", location = "/exit.jsp") })
	public String logout() {
		getHttpSession().invalidate();
		return "logout";
	}

	public User getBean() {
		return bean;
	}

	public void setBean(User bean) {
		this.bean = bean;
	}

	private String loginid;
	private String password;
	private String logintype;
	private String checkcode;

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

}
