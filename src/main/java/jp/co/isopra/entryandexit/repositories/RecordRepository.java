package jp.co.isopra.entryandexit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.entity.RecordPK;

@Repository
public interface RecordRepository extends JpaRepository<Record, RecordPK> {

}
