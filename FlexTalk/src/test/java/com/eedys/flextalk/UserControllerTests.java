package com.eedys.flextalk;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flextalk.common.TestDescripter;
import com.flextalk.constants.RegExp;
import com.flextalk.exception.ExceptionAdvice;
import com.flextalk.pattern.PatternChecker;
import com.flextalk.user.User;
import com.flextalk.user.User.UserType;
import com.flextalk.user.UserController;
import com.flextalk.user.UserService;
import com.flextalk.user.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
public class UserControllerTests {

	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserController userController;
		
	@Autowired
	private PatternChecker patternChecker;
	
	@Before
	public void setup() {
		objectMapper = new ObjectMapper();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController)				   
	    		  .setControllerAdvice(new ExceptionAdvice())
	    		  .build();		   
	}
	
	@Test
	public void TestUser() {
		
		//Given
		User user = User.builder()
						.user_key(10L)
						.user_id("test")
						.user_pw("1234")
						.user_type(UserType.FLEXTALK_ID)
						.build();
		//Then
		assertNotNull(user);
	}

	@Test
	@TestDescripter("�Է°� �׽�Ʈ")
	public void TestRegExpUser() {

		//Given
		User user = User.builder()
						.user_key(10L)
						.user_id("test1")
						.user_pw("1!2sda3c4d")
						.user_email("eedys123@naver.com")
						.user_type(UserType.FLEXTALK_ID)
						.build();

		//Then
		assertTrue(patternChecker.valid(RegExp.ID_REGEXP, user.getUser_id()));
		assertTrue(patternChecker.valid(RegExp.PW_REGEXP, user.getUser_pw()));
		assertTrue(patternChecker.valid(RegExp.EMAIL_REGEXP, user.getUser_email()));

		//Given
		user.setUser_id("te");
		user.setUser_pw("!1");
		user.setUser_email("eedys123@naver.ntewcomw");

		//Then
		assertFalse(patternChecker.valid(RegExp.ID_REGEXP, user.getUser_id()));
		assertFalse(patternChecker.valid(RegExp.PW_REGEXP, user.getUser_pw()));
		assertFalse(patternChecker.valid(RegExp.EMAIL_REGEXP, user.getUser_email()));

	}

//	@Test
	@TestDescripter("����� ���� API ���� �׽�Ʈ")
	public void User_Created() throws Exception {

		//Given
		UserVO.enrollmentRequest user = new UserVO.enrollmentRequest();
		user.setUser_id("test");
		user.setUser_pw("1!2sda3c4d");
		user.setUser_type("0");
		user.setUser_email("eedys123@naver.com");

		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/enrollment")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status").value(201))
		.andExpect(jsonPath("$.body.enroll_YN").value(1));				
	}
	
	
	@Test
	@TestDescripter("����� ���� API ���� �׽�Ʈ")
	public void User_Created_BAD_REQUEST() throws Exception {

		//Given
		UserVO.enrollmentRequest user = new UserVO.enrollmentRequest();
		user.setUser_id("te");
		user.setUser_pw("1234");
		user.setUser_type("0");
		user.setUser_email("eedys123@naver.ntewcomw3");

		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/enrollment")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.status").value(400));
	}
	
	@Test
	@TestDescripter("����� �α��� API ���� �׽�Ʈ")
	public void User_Login_OK() throws Exception {
		
		//Given
		User user = User.builder()
						.user_id("test")
						.user_pw("1!2sda3c4d")
						.build();
		
		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.body.login_YN").value(1));		
	}
	
	@Test
	@TestDescripter("����� �α��� API ���� �׽�Ʈ")
	public void User_Login_Bad_Request() throws Exception {
		
		//Given
		User user = User.builder()
				.user_id("test")
				.user_pw("!1")
				.build();

		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	@Test
	@TestDescripter("����� ���̵� �ߺ� üũ API ���� �׽�Ʈ")
	public void User_Overlap_Id_OK() throws Exception {
			
		//Then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/id-overlap/test")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.body.overlap_YN").value(1));
	}
	
}
