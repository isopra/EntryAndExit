package jp.co.isopra.entryandexit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecordPK implements Serializable {

	@Column
	private Date record_date;

	@Column
	private int location_id;

	public Date getRecord_date() {
		return record_date;
	}

	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
}
