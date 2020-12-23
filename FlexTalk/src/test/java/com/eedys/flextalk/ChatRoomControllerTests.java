package com.eedys.flextalk;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
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
import com.flextalk.constants.XHeader;
import com.flextalk.exception.ExceptionAdvice;
import com.flextalk.room.ChatRoom;
import com.flextalk.room.ChatRoomController;
import com.flextalk.room.ChatRoomFactory;
import com.flextalk.room.ChatRoomHolder;
import com.flextalk.room.ChatRoomRepository;
import com.flextalk.room.ChatRoomService;
import com.flextalk.room.ChatRoomVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
public class ChatRoomControllerTests {

	protected MockMvc mockMvc;
	
	protected ObjectMapper objectMapper;
	
	@Autowired
	protected ChatRoomController chatRoomController;

	@Autowired	
	ChatRoomService chatRoomService;
	 
	@Autowired
	ChatRoomHolder chatRoomHolder;
	
	@Autowired
	ChatRoomRepository chatRoomRepository;
	
	@Before
	public void setUp() {
			objectMapper = new ObjectMapper();
			MockitoAnnotations.initMocks(this);
			this.mockMvc = MockMvcBuilders.standaloneSetup(chatRoomController)				   
		    		  .setControllerAdvice(new ExceptionAdvice())
		    		  .build();	
		   
	}
		
	@Test
	@TestDescripter("채팅방 생성 테스트")
	public void testCreatedChatRoom() {
		
		ChatRoomHolder chatRoomHolder = new ChatRoomHolder();
		ChatRoomFactory factory = chatRoomHolder.createFactory();
		ChatRoom room = factory.createRoom("0", "테스트 채팅방");
		
		assertNotNull(room);
	}
	
	@Test
	@TestDescripter("채팅방 생성 API 성공 테스트")
	public void ChatRoom_Created() throws Exception {
		
		ChatRoomVO.createReqeust chatRoom = new ChatRoomVO.createReqeust();
		chatRoom.setChatroom_name("테스트 채팅방1");
		chatRoom.setChatroom_type("0");		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/room")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header(XHeader.X_USER_ID, 1)
				.content(objectMapper.writeValueAsString(chatRoom)))
		.andDo(print())
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.status").value(201));
		
	}
	
	
	@Test
	@TestDescripter("채팅방 생성 API 실패 테스트")
	public void ChatRoom_Created_BAD_REQUEST() throws Exception {
		
		ChatRoomVO.createReqeust chatRoom = new ChatRoomVO.createReqeust();
		chatRoom.setChatroom_name("테스트 채팅방2");
		chatRoom.setChatroom_name("3");

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/room")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header(XHeader.X_USER_ID, 2)
				.content(objectMapper.writeValueAsString(chatRoom)))		
		.andDo(print())
		.andExpect(status().isBadRequest())
		;
	
	}
	
	@Test
	@TestDescripter("채팅방 리스트 가져오기 API 성공 테스트")
	public void ChatRoomList_OK() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rooms?pageNo=1&size=3")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header(XHeader.X_USER_ID, 1))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.body.chatRoom_list", Matchers.notNull()).isArray())		
		.andExpect(jsonPath("$.body.chatRoom_list[0].length()").value(7))
		;	
	}
	
	@Test
	@TestDescripter("채팅방 리스트 가져오기 API 실패 테스트")
	public void ChatRoomList_BAD_REQUEST() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rooms?pageNo=1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().isBadRequest())
		;
		
	}
	
//	@Test
	@TestDescripter("채팅방 나가기 API 성공 테스트")
	public void ChatRoom_Leave_OK() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/room/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header(XHeader.X_USER_ID, 1))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value(200))
		;
	}
}
