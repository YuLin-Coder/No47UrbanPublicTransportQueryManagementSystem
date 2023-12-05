package cn.fun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_bus")
public class Bus {
	private int		id;
	private String	pai;
	private int		wei;
	private String	note;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Column(unique = true)
	public String getPai() {
		return pai;
	}

	public int getWei() {
		return wei;
	}

	public String getNote() {
		return note;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public void setWei(int wei) {
		this.wei = wei;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
