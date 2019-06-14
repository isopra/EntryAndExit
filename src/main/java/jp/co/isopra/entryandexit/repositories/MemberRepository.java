package jp.co.isopra.entryandexit.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.isopra.entryandexit.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	public Optional<Member> findById(int id);
	@Query("SELECT d FROM Member d ORDER BY d.member_id")
	List<Member> findAllOrderById();
}
