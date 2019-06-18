package jp.co.isopra.entryandexit.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;

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

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.repositories.LocationRepository;

public class LocationServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	// Autowiredしている変数をMock化
	@Mock
	private LocationRepository locationRepository;

	@Mock
	private EntityManager EntityManager;

	//テスト対象のクラス
	@InjectMocks
	private LocationService target;

	//テスト実行前の準備
	@Before
	public void setup() {
		// Mockの初期化
		MockitoAnnotations.initMocks(this);
	}

	//registerMemberメソッドの正常系テスト
	@Test
	public void test_registerLocation_ok() {
		//テスト対象メソッドのパラメータになるオブジェクトを作成
		Location entity = new Location();
		entity.setLocation_id(1);
		entity.setName("hoge");

		//Mockの設定
		when(locationRepository.save(any())).thenAnswer(new Answer<Location>() {
			public Location answer(InvocationOnMock invocation) {
				return (Location)invocation.getArguments()[0];
			}
		});

		Location resultEntity = target.registerLocation(entity);

		verify(locationRepository, times(1)).save(resultEntity);

		assertThat(resultEntity.getLocation_id(), is(entity.getLocation_id()));
		assertThat(resultEntity.getName(), is(entity.getName()));
	}

}
