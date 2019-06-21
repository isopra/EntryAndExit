package jp.co.isopra.entryandexit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int member_id;

	@Column
	@Length(max=100)
	private String person_id;

	@Column
	@NotNull
	@Length(max=100)
	private String name;

	@Column
	@NotNull
	@Where(clause="delete_flag = false")
	private boolean delete_flag;

	public int getMember_id() {
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

	public boolean isDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(boolean delete_flag) {
		this.delete_flag = delete_flag;
	}



}
