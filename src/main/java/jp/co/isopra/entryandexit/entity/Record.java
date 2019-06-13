package jp.co.isopra.entryandexit.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="record")
@IdClass(RecordPK.class)
public class Record {

	@Id
	@Column
	private Date record_date;

	@Id
	@Column
	private int location_id;

	@Column
	private int entry_member_id;

	@Column
	private Timestamp entry_time;

	@Column
	private int exit_member_id;

	@Column
	private Timestamp exit_time;

	@NotNull
	@Column
	private Timestamp created_time;

	public Date getRecord_date() {
		return record_date;
	}

	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}

	public int getLocation_id(){
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getEntry_member_id() {
		return entry_member_id;
	}

	public void setEntry_member_id(int entry_member_id) {
		this.entry_member_id = entry_member_id;
	}

	public Timestamp getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Timestamp entry_time) {
		this.entry_time = entry_time;
	}

	public int getExit_member_id() {
		return exit_member_id;
	}

	public void setExit_member_id(int exit_member_id) {
		this.exit_member_id = exit_member_id;
	}

	public Timestamp getExit_time() {
		return exit_time;
	}

	public void setExit_time(Timestamp exit_time) {
		this.exit_time = exit_time;
	}

	public Timestamp getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}

}
