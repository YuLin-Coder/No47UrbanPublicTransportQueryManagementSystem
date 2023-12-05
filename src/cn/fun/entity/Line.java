package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_line")
public class Line {
	private int		id;
	private String	sid;		//车次

	private String	iccard;		//IC卡

	private Station	startSta;	//起点终点
	private Station	endSta;

	private String	linetime;	//时间

	private String	stations;

	private String	distance;

	public String getStations() {
		return stations;
	}

	public void setStations(String stations) {
		this.stations = stations;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Column(unique = true)
	public String getSid() {
		return sid;
	}

	public String getIccard() {
		return iccard;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "start_id")
	public Station getStartSta() {
		return startSta;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "end_id")
	public Station getEndSta() {
		return endSta;
	}

	public String getLinetime() {
		return linetime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setIccard(String iccard) {
		this.iccard = iccard;
	}

	public void setStartSta(Station startSta) {
		this.startSta = startSta;
	}

	public void setEndSta(Station endSta) {
		this.endSta = endSta;
	}

	public void setLinetime(String linetime) {
		this.linetime = linetime;
	}

}
