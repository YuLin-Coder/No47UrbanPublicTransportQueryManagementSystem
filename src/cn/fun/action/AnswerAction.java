package cn.fun.action;

import cn.fun.common.BaseAction;
import cn.fun.entity.*;
import cn.fun.service.BizService;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.Constant;
import util.DateUtil;
import util.MessageUtil;
import util.StringUtil;

import java.util.List;

@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class AnswerAction extends BaseAction implements ModelDriven<Answer> {
	private String		actionname1		= "回答";
	private String		actionclass1	= "Answer";
	@Autowired
	private BizService	service;

	private int			uid;
	private Answer			bean			= new Answer();

	@Action(value = "add2Answer", results = { @Result(name = "add2", location = "/ahtml/addAnswer.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getAnswer", results = { @Result(name = "getOne", location = "/ahtml/modifyAnswer.jsp") })
	public String get() {
		try {
			Answer temp = (Answer) service.get(Answer.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteAnswer")
	public String delete() {
		try {
			service.delete(Answer.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "删除信息成功.", "mainqueryAnswer");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "删除失败");
			return ERROR;
		}
	}

	@Action(value = "updateAnswer")
	public String update() {
		try {
			service.update(bean);
			MessageUtil.addCloseMessage(getHttpServletRequest(), "更新成功.", "mainqueryAnswer");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "更新失败");
			return ERROR;
		}
	}

	@Action(value = "addAnswer", results = { @Result(name = "answer", location = "/web/answer.jsp") })
	public String add() {
		Integer questionId = Integer.parseInt(getHttpServletRequest().getParameter("questionId"));
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

			bean.setQuestionId(questionId);

			if (StringUtil.notEmpty(bean.getContent())) {
				service.add(bean);
			}

			List<?> list = service.queryByHQL("from Answer where questionId=? order by addDate desc", questionId);
			putRequestValue("answerList", list);
			return "answer";
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "添加失败");
			return ERROR;
		}
	}

	public Answer getModel() {
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

	public Answer getBean() {
		return bean;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public void setBean(Answer bean) {
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
