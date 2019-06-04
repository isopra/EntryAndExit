package jp.co.isopra.entryandexit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
