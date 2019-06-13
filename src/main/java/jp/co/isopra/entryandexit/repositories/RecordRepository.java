package jp.co.isopra.entryandexit.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.entity.RecordPK;

@Repository
public interface RecordRepository extends JpaRepository<Record,RecordPK> {

	//public Optional<Record> findByDate(Date record_date);
	@Query("SELECT r FROM Record r ORDER BY r.record_date")
	Iterable<Record> findAllOrderByDate();

}
