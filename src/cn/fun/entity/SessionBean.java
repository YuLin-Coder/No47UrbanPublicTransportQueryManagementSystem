package cn.fun.entity;

import java.io.Serializable;

public class SessionBean implements Serializable {
	private Object user;

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public String getRole() {
		if (user == null) {
			return "";
		}
		String ret = user.getClass().getSimpleName();
		//System.out.println(ret);
		return ret;

	}

}
