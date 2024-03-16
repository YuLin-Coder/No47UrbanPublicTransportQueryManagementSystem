package cn.fun.action;

import cn.fun.common.BaseAction;
import cn.fun.entity.*;
import cn.fun.entity.Question;
import cn.fun.service.BizService;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.*;

import java.util.*;
import java.util.Map.Entry;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class QuestionAction extends BaseAction implements ModelDriven<Question> {
	private String		actionname1		= "问题";
	private String		actionclass1	= "Question";
	@Autowired
	private BizService	service;

	private int			uid;
	private Question			bean			= new Question();

	@Action(value = "add2Question", results = { @Result(name = "add2", location = "/ahtml/addQuestion.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getQuestion", results = { @Result(name = "getOne", location = "/ahtml/modifyQuestion.jsp") })
	public String get() {
		try {
			Question temp = (Question) service.get(Question.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteQuestion")
	public String delete() {
		try {
			service.delete(Question.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryQuestion");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateQuestion")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryQuestion");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addQuestion", results = { @Result(name = "question", location = "/web/question.jsp") })
	public String add() {
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

			bean.setAddDate(DateUtil.getCurrentTime());

			if (StringUtil.notEmpty(bean.getContent())) {
				service.add(bean);
			}

			List<?> list = service.queryByHQL("from Question order by addDate desc");
			putRequestValue("questionList", list);
			return "question";
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	public Question getModel() {
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

	public Question getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Question bean) {
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
