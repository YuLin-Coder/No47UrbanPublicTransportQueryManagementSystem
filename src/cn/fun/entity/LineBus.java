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

//车次
@Entity
@Table(name = "t_line_bus")
public class LineBus {
	private int		id;
	private Line	line;
	private Bus		bus;
	private Driver	driver;
	private String	chu;	//出车时间
	private String	shou;	//收车时间

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
	@JoinColumn(name = "bus_id")
	public Bus getBus() {
		return bus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id")
	public Driver getDriver() {
		return driver;
	}

	public String getChu() {
		return chu;
	}

	public String getShou() {
		return shou;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public void setChu(String chu) {
		this.chu = chu;
	}

	public void setShou(String shou) {
		this.shou = shou;
	}

}
