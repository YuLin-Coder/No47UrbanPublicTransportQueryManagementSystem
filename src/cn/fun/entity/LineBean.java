package cn.fun.entity;

import java.util.List;

public class LineBean {
	private Line			line1;
	private Station			huanStation;//换乘站点
	private Line			line2;
	private int				huan;		//换乘次数

	private double			distance;	//距离

	private int				stationSize;//站点数量

	private List<Station>	stationList;

	public String getPath() {
		String path = "";
		if (huan == 0) {
			path += "线路:" + line1.getSid() + "<br/>";
			for (Station s : stationList) {
				path += s.getName() + " > ";
			}
		}
		if (huan == 1) {
			path += "线路:" + line1.getSid() + "<br/>";
			for (Station s : stationList) {
				path += s.getName() + " > ";
				if (s.getId() == huanStation.getId()) {
					path += "<br/>换乘线路:" + line2.getSid() + "<br/>" + s.getName() + " > ";
				}
			}

		}
		path = path.substring(0, path.length() - 2);
		return path;
	}

	public List<Station> getStationList() {
		return stationList;
	}

	public void setStationList(List<Station> stationList) {
		this.stationList = stationList;
	}

	public Station getHuanStation() {
		return huanStation;
	}

	public int getStationSize() {
		return stationSize;
	}

	public void setHuanStation(Station huanStation) {
		this.huanStation = huanStation;
	}

	public void setStationSize(int stationSize) {
		this.stationSize = stationSize;
	}

	public Line getLine1() {
		return line1;
	}

	public Line getLine2() {
		return line2;
	}

	public int getHuan() {
		return huan;
	}

	public double getDistance() {
		return distance;
	}

	public void setLine1(Line line1) {
		this.line1 = line1;
	}

	public void setLine2(Line line2) {
		this.line2 = line2;
	}

	public void setHuan(int huan) {
		this.huan = huan;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
