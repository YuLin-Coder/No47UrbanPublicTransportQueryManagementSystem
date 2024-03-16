package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_driver")
public class Driver {
	private int		id;
	private String	name;
	private String	cardno;
	private String	driverno;
	private String	gender;
	private int		age;
	private int		driverage;
	private String	health;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Column(unique = true)
	public String getCardno() {
		return cardno;
	}

	@Column(unique = true)
	public String getDriverno() {
		return driverno;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public void setDriverno(String driverno) {
		this.driverno = driverno;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDriverage() {
		return driverage;
	}

	public void setDriverage(int driverage) {
		this.driverage = driverage;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}
}
