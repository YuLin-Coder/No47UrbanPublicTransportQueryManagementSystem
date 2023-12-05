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
@Table(name = "t_line_station")
public class LineStation {
	private int		id;
	private Line	line;
	private Station	station;
	private int		staindex;

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
	@JoinColumn(name = "station_id")
	public Station getStation() {
		return station;
	}

	public int getStaindex() {
		return staindex;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public void setStaindex(int staindex) {
		this.staindex = staindex;
	}

}
