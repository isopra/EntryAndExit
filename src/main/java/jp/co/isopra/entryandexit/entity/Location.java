package jp.co.isopra.entryandexit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;

@Entity
@Table(name="location")
public class Location {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int location_id;

	@Column
	@NotNull
	private String name;

	@Column
	@NotNull
	@Where(clause="delete_flag = 0")
	private boolean delete_flag;


	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}

}
