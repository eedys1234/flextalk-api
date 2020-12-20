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
	private UserService userService;
	
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
		User user = new User();
		user.setUserKey(10L);
		user.setUserId("test");
		user.setUserPw("1234");
		user.setUserType(UserType.FLEXTALK_ID);

		//Then
		assertNotNull(user);
	}

	@Test
	@TestDescripter("입력값 테스트")
	public void TestRegExpUser() {

		//Given
		User user = new User();
		user.setUserKey(10L);
		user.setUserId("test");
		user.setUserPw("1!2sda3c4d");
		user.setUserEmail("eedys123@naver.com");
		user.setUserType(UserType.FLEXTALK_ID);

		//Then
		assertTrue(patternChecker.valid(RegExp.ID_REGEXP, user.getUserId()));
		assertTrue(patternChecker.valid(RegExp.PW_REGEXP, user.getUserPw()));
		assertTrue(patternChecker.valid(RegExp.EMAIL_REGEXP, user.getUserEmail()));

		//Given
		user.setUserId("te");
		user.setUserPw("1234");
		user.setUserEmail("eedys123@naver.ntewcomw");

		//Then
		assertFalse(patternChecker.valid(RegExp.ID_REGEXP, user.getUserId()));
		assertFalse(patternChecker.valid(RegExp.PW_REGEXP, user.getUserPw()));
		assertFalse(patternChecker.valid(RegExp.EMAIL_REGEXP, user.getUserEmail()));

	}

//	@Test
	@TestDescripter("사용자 생성 API 성공 테스트")
	public void User_Created() throws Exception {

		//Given
		UserVO.enrollmentRequest user = new UserVO.enrollmentRequest();
		user.setUserId("test");
		user.setUserPw("bool!7922");
		user.setUserType("0");
		user.setUserEmail("eedys123@naver.com");

		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/enrollment")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status").value(201))
		.andExpect(jsonPath("$.body.enrollYN").value(1));				
	}
	
	
	@Test
	@TestDescripter("사용자 생성 API 실패 테스트")
	public void User_Created_BAD_REQUEST() throws Exception {

		//Given
		UserVO.enrollmentRequest user = new UserVO.enrollmentRequest();
		user.setUserId("te");
		user.setUserPw("1234");
		user.setUserType("0");
		user.setUserEmail("eedys123@naver.ntewcomw3");

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
	@TestDescripter("사용자 로그인 API 성공 테스트")
	public void User_Login_OK() throws Exception {
		
		//Given
		User user = new User();
		user.setUserId("test");
		user.setUserPw("bool!7922");
		
		//Then
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(user)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.body.loginYN").value(1));		
	}
	
	@Test
	@TestDescripter("사용자 로그인 API 실패 테스트")
	public void User_Login_Bad_Request() throws Exception {
		
		//Given
		User user = User.builder()
				.userId("")
				.userPw("")
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
	@TestDescripter("사용자 아이디 중복 체크 API 성공 테스트")
	public void User_Overlap_Id_OK() throws Exception {
			
		//Then
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/id-overlap/test")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.body.overlapYN").value(1));
	}
	
}
