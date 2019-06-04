package jp.co.isopra.entryandexit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int member_id;

	@Column
	@Max(100)
	private String person_id;

	@Column
	@NotNull
	@Max(100)
	private String name;

	public int getMenber_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id){
		this.person_id = person_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
