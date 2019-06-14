package jp.co.isopra.entryandexit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record,Integer>{

	@Query("SELECT s FROM Record s ORDER BY s.record_date")
	Iterable<Record> findAllOrderByRecord_Date();
}
