package jp.co.isopra.entryandexit.service;

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

	public Record registerRecord(Record entity) {

		entity = recordRepository.save(entity);

		return entity;
	}
}
