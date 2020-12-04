package com.springyboot.controllers.test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springyboot.controllers.UserController;
import com.springyboot.models.User;
import com.springyboot.repos.UserRepo;
import com.springyboot.services.UserService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService us;
	
	// Needed to squish failed to load ApplicationContext error
	@MockBean
	private UserRepo ur;
	
	@InjectMocks
	private UserController uc;
	
	private String mockUserJson = "{\"userId\":0, \"email\":\"a@a.net\", \"username\":\"a\", \"password\":\"p\"}";
	User user = new User(0, "a@a.net", "a", "p");
	User user2 = new User(1, "a2@a.net", "a2", "p");
	User user3 = new User(2, "a3@a.net", "a3","p");
	
	/**
	 * Creates a mock servlet to use in the controller tests
	 */
	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(uc).build();
	}
	
	/**
	 * Unit tests the controller successfully creating a new user object
	 */
	@Test
	public void testCreateNewUserSuccess() throws Exception{
		Mockito.when(us.save(user3)).thenReturn(true);
		this.mockMvc
			.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mockUserJson)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(content().string(containsString("User created!"))
		);
	}
	
	@Test
	public void testFindAll() throws Exception{
		String pwHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		userList.add(user2);
		userList.add(user3);
		Mockito.when(us.findAll()).thenReturn(userList);
		this.mockMvc
			.perform(get("/users")
				.accept(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk()
		);
							
	}
	
}
