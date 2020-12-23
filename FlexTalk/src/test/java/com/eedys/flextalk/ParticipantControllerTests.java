package com.eedys.flextalk;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.stream.Collectors;

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
import com.flextalk.exception.ExceptionAdvice;
import com.flextalk.participant.ParticipantController;
import com.flextalk.participant.ParticipantVO;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
})
public class ParticipantControllerTests {

	MockMvc mockMvc;

	ObjectMapper objectMapper;
	
	@Autowired
	ParticipantController participantController;
	
	@Before
	public void setUp() {
			objectMapper = new ObjectMapper();
			MockitoAnnotations.initMocks(this);
			this.mockMvc = MockMvcBuilders.standaloneSetup(participantController)				   
		    		  .setControllerAdvice(new ExceptionAdvice())
		    		  .build();	
		   
	}
	
	@Test
	@TestDescripter("참여자 초대 API 성공 테스트")
	public void Participant_Invite_OK() throws Exception {
		
		long[] user_keys = {2L};
		long chatroom_key = 33L;
		
		ParticipantVO.inviteRequest request = ParticipantVO.inviteRequest.builder()
																		.user_keys(Arrays.stream(user_keys).boxed().collect(Collectors.toList()))
																		.chatroom_key(chatroom_key)
																		.build();
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/participant")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(request)))
		.andDo(print())
		.andExpect(status().isCreated())
		;
		
	}
}
