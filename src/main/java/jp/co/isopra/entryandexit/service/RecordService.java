package jp.co.isopra.entryandexit.service;

import java.util.List;

import javax.persistence.EntityManager;
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

	@Autowired
	EntityManager entityManager;

	public Record registerRecord(Record entity) {

		entity = recordRepository.save(entity);

		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<Record> searchEntry(String record_date) {
		return (List<Record>)entityManager
				.createQuery("from Record where record_date = \'" + record_date + "\'")
				.getResultList();
		}

}
