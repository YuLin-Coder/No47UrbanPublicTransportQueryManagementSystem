package cn.fun.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.fun.common.BaseHibernateDao;
import cn.fun.dao.ISysDao;
import cn.fun.entity.User;

@Repository
public class SysDaoImpl extends BaseHibernateDao implements ISysDao {
	public void saveUser(User user) {
		save(user);
	}

	public Object queryUser(String type, String userid, String pwd) {
		return unique("from " + type + " where user.uname='" + userid + "' and user.userPassword='" + pwd + "'");
	}

	public User queryUser(String userid) {
		return unique("from User where uname=?", userid);
	}

	public List queryByHQLLimit(final String hql, final int start, final int max) {
		try {
			List resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
					Query query = arg0.createQuery(hql);
					query.setFirstResult(start);
					query.setMaxResults(max);
					return query.list();
				}
			});
			return resultList;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public List queryByHQLLimit(final String hql, final Object[] parms, final int start, final int max) {
		try {
			List resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session arg0) throws HibernateException, SQLException {
					Query query = arg0.createQuery(hql);
					if (parms != null) {
						for (int i = 0; i < parms.length; i++) {
							query.setParameter(i, parms[i]);
						}
					}
					query.setFirstResult(start);
					query.setMaxResults(max);
					return query.list();
				}
			});
			return resultList;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
