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
@Table(name = "t_line_station_distance")
public class LineStationDistance {
	private int		id;
	private Line	line;
	private Station	station1;
	private Station	station2;
	private double	dlen;

	public double getDlen() {
		return dlen;
	}

	public void setDlen(double dlen) {
		this.dlen = dlen;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "line_id")
	public Line getLine() {
		return line;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "station1_id")
	public Station getStation1() {
		return station1;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "station2_id")
	public Station getStation2() {
		return station2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setStation1(Station station1) {
		this.station1 = station1;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

}
