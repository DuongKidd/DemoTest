package io.github.tubean.myspringcrud.controller;

import io.github.tubean.myspringcrud.entity.User;
import io.github.tubean.myspringcrud.repository.UserRepository;
import io.github.tubean.myspringcrud.service.UserService;
import io.github.tubean.myspringcrud.service.impl.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
@WebAppConfiguration
public class UserControllerTest extends TestCase {

    @Autowired
    private WebApplicationContext wac;

    @Mock
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();  //Construct MockMVC
    }

    @Test
    public void testGetAll() throws Exception {
        User employee = new User("Duong", "Kidd", "0123019283");
        List<User> users = Arrays.asList(employee);

        when(userService.getAllUser()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetById() throws Exception {
        User employee = new User("Duong", "Kidd", "0123019283");

        when(userService.findUserById(1L)).thenReturn(java.util.Optional.of(employee));

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(employee));
    }

    @Test
    public void testCreateUser() throws Exception {

        mockMvc.perform(post("/user/create").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Duong\",\n" +
                "        \"email\": \"abc@gmail.com\",\n" +
                "        \"phone\": \"Kidd\"\n" +
                "    }"))

                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteByIdSuccess() throws Exception {
        User user = new User("Duong", "Kidd", "0123019283");

        when(userService.deleteUser((long) 1)).thenReturn(true);

        mockMvc.perform(get("/user/delete/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteByIdFail() throws Exception {
        User user = new User("Duong", "Kidd", "0123019283");

        when(userService.deleteUser((long) 1)).thenReturn(false);

        mockMvc.perform(get("/user/delete/{id}", 1))
                .andExpect(status().isOk());
    }
}
