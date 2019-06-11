package jp.co.isopra.entryandexit.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import jp.co.isopra.entryandexit.entity.Member;
import jp.co.isopra.entryandexit.repositories.MemberRepository;


public class MemberServiceTest {

	//Autowiredしている変数をMock化
	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	@Mock
	private MemberRepository memberRepository;

	//テスト対象のクラス
	@InjectMocks
	private MemberService target;


	//テスト実行前の準備

	@Before
	public void setup() {
		//Mockの初期化
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_registerMember_ok() {
		Member entity = new Member();

		//Mockの設定
		when(memberRepository.save(any())).thenAnswer(new Answer<Member>() {
			public Member answer(InvocationOnMock invocation) {
				return (Member)invocation.getArguments()[0];
			}
		});

		//テスト対象のメソッド実行
		Member resuitEntity = target.registerMember(entity);

		//Mockのメソッドが指定の引数で実行されたことを確認
		verify(memberRepository, times(1)).save(resuitEntity);
	}
}
