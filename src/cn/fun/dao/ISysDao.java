package cn.fun.dao;

import java.util.List;

import cn.fun.common.BaseDao;
import cn.fun.entity.User;

public interface ISysDao extends BaseDao {
	public void saveUser(User user);

	public Object queryUser(String type, String userid, String pwd);

	public User queryUser(String userid);

	public List queryByHQLLimit(final String hql, final int start, final int max);

	public <T> T unique(final String hql, final Object... paramlist);

	public List queryByHQLLimit(final String hql, final Object[] parms, final int start, final int max);
}
