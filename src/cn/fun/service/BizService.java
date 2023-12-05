package cn.fun.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import util.MD5;
import util.Page;
import cn.fun.common.BaseService;
import cn.fun.dao.ISysDao;
import cn.fun.entity.Line;
import cn.fun.entity.LineBean;
import cn.fun.entity.LineStation;
import cn.fun.entity.LineStationDistance;
import cn.fun.entity.SimpleUser;
import cn.fun.entity.Station;
import cn.fun.entity.SysUser;
import cn.fun.entity.User;

@Service("bizService")
@Repository
public class BizService extends BaseService {

	@Autowired
	private ISysDao dao;

	public List queryByHQL(String hql, Object... values) {
		return dao.queryByHQL(hql, values);
	}

	public void deleteSysUser(Class<SysUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SysUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void deleteSimpleUser(Class<SimpleUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SimpleUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void addSimpleUser(SimpleUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSimpleUser(SimpleUser obj) {
		SimpleUser temp = (SimpleUser) dao.get(SimpleUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	/**
	 * 添加对象
	 * 
	 * @param obj
	 */
	public void add(Object obj) {
		dao.save(obj);
	}

	/**
	 * 修改对象
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		dao.merge(obj);
	}

	/**
	 * 根据id获取对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Object get(Class clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	public void deleteUser(String ids) {
		dao.deleteByIds(User.class, ids);
	}

	public void delete(Class clazz, String ids) {
		dao.deleteByIds(clazz, ids);
	}

	public Object findUser(String type, String userid, String pwd) {
		return dao.queryUser(type, userid, pwd);
	}

	public User findUser(String userid) {
		return dao.queryUser(userid);
	}

	public Page findUser(Page page) {
		return dao.list(page, "User");
	}

	public Page find(Page page, Class clazz) {
		return dao.list(page, clazz.getSimpleName());
	}

	public List findAll(Class clazz) {
		return dao.queryByHQL("from " + clazz.getSimpleName());
	}

	public void addSysUser(SysUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSysUser(SysUser obj) {
		SysUser temp = (SysUser) dao.get(SysUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	public List findAll(Class clazz, Map<String, Object> params) {
		return dao.findAll(clazz, params);
	}

	public <T> T unique(final String hql, final Object... paramlist) {
		return dao.unique(hql, paramlist);
	}

	public void addLine(Line bean, List<LineStation> list) {
		bean.setDistance("未配置");
		dao.save(bean);
		for (LineStation ls : list) {
			ls.setLine(bean);
			dao.save(ls);
		}
	}

	public void updateLine(Line bean, List<LineStation> list) {
		bean.setDistance("未配置");
		dao.merge(bean);
		dao.updateByHQL("delete LineStation where line.id=?", bean.getId());
		dao.updateByHQL("delete LineStationDistance where line.id=?", bean.getId());
		for (LineStation ls : list) {
			ls.setLine(bean);
			dao.save(ls);
		}
	}

	public void updateLineDistance(Line temp, List<LineStationDistance> dlist) {
		temp.setDistance("已配置");
		dao.merge(temp);
		dao.updateByHQL("delete LineStationDistance where line.id=?", temp.getId());
		for (LineStationDistance d : dlist) {
			dao.save(d);
		}
	}

	/**
	 * 
	 * @param s1
	 *            出发站点
	 * @param s2
	 *            抵达站点
	 * @return
	 */
	public List<LineBean> findPath(Station s1, Station s2) {
		List<LineBean> retList = new ArrayList<LineBean>();
		//直达线路
		List<Line> list1 = dao.queryByHQL("from Line where distance='已配置' and stations like '%>" + s1.getName()
				+ ">%' and stations like '%>" + s2.getName() + ">%'");

		for (Line line : list1) {
			LineStation ls1 = dao.unique("from LineStation where station.id=? and line.id=?", s1.getId(), line.getId());
			LineStation ls2 = dao.unique("from LineStation where station.id=? and line.id=?", s2.getId(), line.getId());
			List<LineStation> lsList = null;
			if (ls1.getId() < ls2.getId()) {
				lsList = dao.queryByHQL("from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex",
						line.getId(), ls1.getStaindex(), ls2.getStaindex());
			} else {
				lsList = dao.queryByHQL("from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex desc",
						line.getId(), ls2.getStaindex(), ls1.getStaindex());
			}
			int stationSize = lsList.size();
			double distance = 0d;
			List<Station> stationList = new ArrayList<Station>();
			for (int i = 0; i < stationSize - 1; i++) {
				LineStation tempSta1 = lsList.get(i);
				LineStation tempSta2 = lsList.get(i + 1);
				LineStationDistance tempDis = dao.unique(
						"from LineStationDistance where line.id=? and ((station1.id=? and station2.id=?) or (station1.id=? and station2.id=?))",
						line.getId(), tempSta1.getStation().getId(), tempSta2.getStation().getId(), tempSta2.getStation().getId(),
						tempSta1.getStation().getId());
				distance += tempDis.getDlen();
				stationList.add(tempSta1.getStation());
			}
			stationList.add(s2);
			LineBean bean = new LineBean();
			bean.setDistance(distance);
			bean.setHuan(0);
			bean.setLine1(line);
			bean.setStationSize(stationSize);
			bean.setStationList(stationList);
			retList.add(bean);
		}

		//一次换乘

		//路过起始站点,但是不到达终点的站点的线路
		List<Line> list2 = dao.queryByHQL("from Line where distance='已配置' and stations like '%>" + s1.getName()
				+ ">%' and stations not like '%>" + s2.getName() + ">%'");

		for (Line line : list2) {
			LineStation ls1 = dao.unique("from LineStation where station.id=? and line.id=?", s1.getId(), line.getId());//第一线路的起点站
			List<LineStation> elseStaList = dao.queryByHQL("from LineStation where line.id=? and station.id!=?", line.getId(),
					s1.getId());
			for (LineStation ls2 : elseStaList) {//ls2 换乘站点, 第一线路的下车换乘站
				List<Line> list22 = dao.queryByHQL("from Line where distance='已配置' and stations like '%>" + ls2.getStation().getName()
						+ ">%' and stations like '%>" + s2.getName() + ">%'");
				if (list22 == null || list22.size() == 0)
					continue;

				List<LineStation> lsList1 = null;
				double distance = 0d;
				if (ls1.getId() < ls2.getId()) {
					lsList1 = dao.queryByHQL("from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex",
							line.getId(), ls1.getStaindex(), ls2.getStaindex());
				} else {
					lsList1 = dao.queryByHQL("from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex desc",
							line.getId(), ls2.getStaindex(), ls1.getStaindex());
				}
				int stationSize = lsList1.size();
				List<Station> stationList = new ArrayList<Station>();
				for (int i = 0; i < stationSize - 1; i++) {
					LineStation tempSta1 = lsList1.get(i);
					LineStation tempSta2 = lsList1.get(i + 1);
					LineStationDistance tempDis = dao.unique(
							"from LineStationDistance where line.id=? and ((station1.id=? and station2.id=?) or (station1.id=? and station2.id=?))",
							line.getId(), tempSta1.getStation().getId(), tempSta2.getStation().getId(), tempSta2.getStation().getId(),
							tempSta1.getStation().getId());
					distance += tempDis.getDlen();
					stationList.add(tempSta1.getStation());
				}
				
//				double distance = 0d;
//				List<Station> stationList = new ArrayList<Station>();
//				int stationSize = 0;

				for (Line line22 : list22) {//能到达终点的第二条线路
					LineStation ls122 = dao.unique("from LineStation where station.id=? and line.id=?", ls2.getStation().getId(),
							line22.getId());
					LineStation ls222 = dao.unique("from LineStation where station.id=? and line.id=?", s2.getId(), line22.getId());
					List<LineStation> lsList = null;
					if (ls122.getId() < ls222.getId()) {
						lsList = dao.queryByHQL("from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex",
								line22.getId(), ls122.getStaindex(), ls222.getStaindex());
					} else {
						lsList = dao.queryByHQL(
								"from LineStation where line.id=? and staindex>=? and staindex<=? order by staindex desc",
								line22.getId(), ls222.getStaindex(), ls122.getStaindex());
					}
					stationSize += lsList.size();
					for (int i = 0; i < lsList.size() - 1; i++) {
						LineStation tempSta1 = lsList.get(i);
						LineStation tempSta2 = lsList.get(i + 1);
						LineStationDistance tempDis = dao.unique(
								"from LineStationDistance where line.id=? and ((station1.id=? and station2.id=?) or (station1.id=? and station2.id=?))",
								line22.getId(), tempSta1.getStation().getId(), tempSta2.getStation().getId(),
								tempSta2.getStation().getId(), tempSta1.getStation().getId());
						distance += tempDis.getDlen();
						stationList.add(tempSta1.getStation());
					}

					stationList.add(s2);

					LineBean bean = new LineBean();
					bean.setDistance(distance);
					bean.setHuan(1);
					bean.setHuanStation(ls2.getStation());
					bean.setLine1(line);
					bean.setLine2(line22);
					bean.setStationSize(stationSize);
					bean.setStationList(stationList);
					retList.add(bean);
					break;
				}

			}
		}

		return retList;
	}

}
