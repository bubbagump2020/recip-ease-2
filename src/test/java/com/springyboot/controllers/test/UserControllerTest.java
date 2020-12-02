package com.springyboot.controllers.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springyboot.controllers.UserController;
import com.springyboot.models.User;
import com.springyboot.services.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService us;
	
	@InjectMocks
	private UserController uc;
	
	private String mockUserJson = "{\"userId\":0, \"email\":\"a@a.net\", \"password\":\"p\"}";
	User user = new User(0, "a@a.net", "p");
	User user2 = new User(1, "a2@a.net", "p");
	
	
	/**
	 * @author Kevin
	 * Creates a mock servlet to use in the controller tests
	 */
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(uc).build();
	}
	
	/**
	 * @author Kevin
	 * Unit tests the controller successfully creating a new user object
	 */
	@Test
	void testCreateNewUserSuccess() throws Exception{
		Mockito.when(us.save(user)).thenReturn(true);
		this.mockMvc
			.perform(post("/user/new").contentType(MediaType.APPLICATION_JSON)
									  .content(mockUserJson)
									  .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("User created!")));
	}
	
}
