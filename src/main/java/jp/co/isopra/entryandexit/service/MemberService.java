package jp.co.isopra.entryandexit.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.MemberRepository;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Member> getAll() {
		return (List<Member>) entityManager
				.createQuery("from Member").getResultList();
	}




	//MemberServiceTest用
	public Member registerMember(Member entity) {
		entity = memberRepository.save(entity);
		return entity;
	}

}
