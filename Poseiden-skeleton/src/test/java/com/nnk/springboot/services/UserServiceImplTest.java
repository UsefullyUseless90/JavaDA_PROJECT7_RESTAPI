package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.User;
import com.nnk.springboot.domain.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.implementation.CustomUserDetailsService;
import com.nnk.springboot.services.implementation.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {
    private UserServiceImpl UserServiceImpl;

    @Mock
    private UserRepository uRepo;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private UserServiceImpl uService;

    private List<User> userList;
    private User user;
    private User user2;
    private User user3;

    private List<UserDTO> uDTOList;
    private UserDTO uDTO;
    private UserDTO uDTO2;
    private UserDTO uDTO3;

    @Before
    public void setUp() {
        //Assign DAO
        user = new User(1, "username", "Password56!!","user");
        user2 = new User(2, "username2", "password2","user");
        user3 = new User(3, "username3", "password3","user");
        userList = Arrays.asList(user, user2, user3);
        //Assign DTO
        uDTO = new UserDTO(user);
        uDTO2 = new UserDTO(user2);
        uDTO3 = new UserDTO(user3);
        uDTOList = Arrays.asList(uDTO, uDTO2, uDTO3);
        // Initialising Service layer
        UserServiceImpl = new UserServiceImpl(uRepo, customUserDetailsService);
    }

    @Test
    public void getAll() {
        Mockito.when(uRepo.findAll()).thenReturn(userList);
        Assert.assertEquals(userList, UserServiceImpl.findAll());
    }

    @Test
    public void getById() {
        Mockito.when(uRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
        Assert.assertEquals(user, UserServiceImpl.findById(1));
    }

    @Test
    public void save() {
        User userTest = new User(uDTO);
        userTest.setRole("user");
        uRepo.save(user);
        verify(uRepo, times(1)).save(userTest);
    }


    @Test
    public void update() {
        Mockito.when(uRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
        user.setFullname("James Bond");
        UserDTO userUpdateTest = new UserDTO(user);
        UserServiceImpl.update(userUpdateTest);
        verify(uRepo, times(1)).save(user);
    }

    @Test
    public void delete() {
        Mockito.when(uRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
        UserServiceImpl.delete(new UserDTO(user));
        verify(uRepo, times(1)).delete(user);
    }
}
