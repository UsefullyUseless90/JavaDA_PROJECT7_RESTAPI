package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.dao.User;
import com.nnk.springboot.domain.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.implementation.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mvc;
    @MockBean
    private IUserService uService;
    @MockBean
    private UserRepository uRepo;
    @Autowired
    private WebApplicationContext context;

    private User user;
    private User user2;
    private User user3;
    private List<User> userList = new ArrayList<>();

    private UserDTO uDTO;
    private UserDTO uDTO2;
    private UserDTO uDTO3;
    private List<UserDTO> dtoList = new ArrayList<>();

    @BeforeEach
    void setup() {
        //Assign DAO
        user = new User(007 , "JamesBond","Password007!",  "ADMIN");
        user2 = new User(46 ,  "TheDoctor","password2", "user");
        user3 = new User(74 ,  "SnowMan","password3", "user");
        userList = Arrays.asList(user, user2, user3);
        //Assign DTO
        uDTO = new UserDTO(user);
        uDTO2 = new UserDTO(user2);
        uDTO3 = new UserDTO(user3);
        dtoList = Arrays.asList(uDTO, uDTO2,uDTO3);
        //Assign MVC
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllUserControllerTest() throws Exception {
        when(uService.findAll()).thenReturn(userList);
        mvc.perform(get("/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("users"))
        .andExpect(view().name("user/list"));
    }

    @Test
    void addUserFormControllerTest() throws Exception {
        mvc.perform(get("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("user/add"));
    }

    @Test
    void validateUserWithErrorControllerTest() throws Exception {
        when(uRepo.save(user)).thenReturn(user);
        mvc.perform(post("/user/validate")
                        .sessionAttr("user", user)
                        .param("id", "")
                        .param("username", "")
                        .param("password", "")
                        .param("fullname", "")
                        .param("role", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("user/add"));
    }

    @Test
    void validateUserControllerTest() throws Exception {
        when(uService.save(user)).thenReturn(user);
        user.setFullname("James Bond");
        mvc.perform(post("/user/validate")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    void UpdateUserControllerTest() throws Exception {
        when(uService.save(user)).thenReturn(user);
        user.setFullname("James Bond");
        mvc.perform(post("/user/update/1")
                        .sessionAttr("user", user)
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("fullname", user.getFullname())
                        .param("role", user.getRole())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    void updateUserErrorControllerTest() throws Exception {
        when(uService.save(user)).thenReturn(user);
        mvc.perform(post("/user/update/1")
                        //.sessionAttr("user", user)
                        .param("id", "")
                        .param("username", "")
                        .param("password", "password")
                        .param("role", "user.getRole()")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("user/update"));
    }

    @Test
    void updateFormUserControllerTest() throws Exception {
        when(uRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        mvc.perform(get("/user/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("user"))
        .andExpect(view().name("user/update"));
    }

    @Test
    void deleteUserControllerTest() throws Exception {
        when(uRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(uRepo).delete(user);
        mvc.perform(get("/user/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/user/list"));
    }
}
