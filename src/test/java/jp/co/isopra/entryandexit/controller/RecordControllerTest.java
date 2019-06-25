package jp.co.isopra.entryandexit.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jp.co.isopra.entryandexit.entity.Record;
import jp.co.isopra.entryandexit.repositories.LocationRepository;
import jp.co.isopra.entryandexit.repositories.MemberRepository;
import jp.co.isopra.entryandexit.repositories.RecordRepository;
import jp.co.isopra.entryandexit.service.RecordService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RecordControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	private MockMvc mockMvc;

	@Mock
	private MemberRepository memberRepository;

	@Mock
	private LocationRepository locationRepository;

	@Mock
	private RecordRepository recordRepository;

	@Mock
	private RecordService recordService;

	@InjectMocks
	private RecordController controller;

	@Before
	public void before() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/html/view/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).setViewResolvers(viewResolver).build();
	}

	@Test
	public void test_registerRecord_ok() throws Exception {

		//Mockの設定
		when(recordService.registerRecord(any())).thenAnswer(new Answer<Record>() {
			public Record answer(InvocationOnMock invocation) {
				return (Record)invocation.getArguments()[0];
			}
		});

		this.mockMvc.perform(MockMvcRequestBuilders.get("/recordDetail/regist/1")
					.param("entry_time", "2019-06-01 10:10:10").param("location_id", String.valueOf(1))
					.param("created_time", "2019-06-01 10:10:10").param("entry_member_id", String.valueOf(1)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/"));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/recordDetail/regist/2")
				.param("record_date", "2019-06-01").param("location_id", String.valueOf(1))
				.param("exit_time", "2019-06-01 10:10:10").param("exit_member_id", String.valueOf(1))
				.param("exit_time_s", "2019-06-01").param("created_time", "2019-06-01 10:10:10"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isFound())
			.andExpect(MockMvcResultMatchers.model().hasNoErrors())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/"));
	}

	@Test
	public void test_record_ok() throws Exception{

		this.mockMvc.perform(MockMvcRequestBuilders.get("/record"))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.model().hasNoErrors())
		.andExpect(MockMvcResultMatchers.view().name("record"));
	}

}
