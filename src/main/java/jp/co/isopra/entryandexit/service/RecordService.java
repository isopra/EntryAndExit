package jp.co.isopra.entryandexit.service;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.RecordRepository;

@Service
@Transactional
public class RecordService {

	@Autowired
	private RecordRepository recordRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public Record registerRecord(Record entity) {

		entity = recordRepository.save(entity);

		return entity;
	}

	public int update(String record_date, int location_id, int exit_member_id, Timestamp exit_time,Timestamp created_time) {
		return entityManager
				.createQuery("update Record SET exit_member_id = \'"+ exit_member_id +
								"\',"+ "exit_time = \'"+ exit_time +
								"\'," + "created_time = \'"+ created_time +
								"\' Where record_date = \'"+ record_date +"\' AND "+
								"location_id="+ location_id)
				.executeUpdate();
	}


}
