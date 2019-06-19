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

import jp.co.isopra.entryandexit.entity.Location;
import jp.co.isopra.entryandexit.service.LocationService;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocationControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

	private MockMvc mockMvc;

	//Autowiredしている変数をMock化
	@Mock
	private LocationService service;

	//テスト対象のクラス
	@InjectMocks
	private LocationController controller;

	//テスト実行前の準備
	@Before
	public void before() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/html/view/");
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).setViewResolvers(viewResolver).build();
	}

	//locationのコントローラーの正常系テスト
	@Test
	public void test_registerLocation_ok() throws Exception {

		Location dataOptional = service.get(1);

		//Mockの設定
		when(service.registerLocation(any())).thenAnswer(new Answer<Location>() {
			public Location answer(InvocationOnMock invocation) {
				return (Location)invocation.getArguments()[0];
			}
		});

		this.mockMvc.perform(MockMvcRequestBuilders.get("/location"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("location"));


		this.mockMvc.perform(MockMvcRequestBuilders.get("/locationNew"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("locationEdit"))
				.andExpect(MockMvcResultMatchers.model().attribute("check", true))
				.andExpect(MockMvcResultMatchers.model().attribute("msg", "新規登録"));

		this.mockMvc.perform(MockMvcRequestBuilders.post("/locationNew")
					.param("name", "hoge"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/location"));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/locationEdit")
					.param("location", String.valueOf(1)))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("locationEdit"))
				.andExpect(MockMvcResultMatchers.model().attribute("check", false))
				.andExpect(MockMvcResultMatchers.model().attribute("msg", 1));

		this.mockMvc.perform(MockMvcRequestBuilders.post("/locationEdit")
					.param("location_id", String.valueOf(1)).param("name", "hoge"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.model().hasNoErrors())
				.andExpect(MockMvcResultMatchers.view().name("redirect:/location"));
	}

}
