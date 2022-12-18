package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTest() {
        User user = new User(1, "Username", "UserPassword", "user");
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(user));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(userDetails.getUsername(),user.getUsername());
        assertThat(userDetails).isNotNull();

    }

    @Test
    public void loadUserByUsernameTest_whenUserDoesNotExist() {
        User user = new User(1, "Username", "UserPassword", "FullName");

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername(user.getUsername()));

    }
}
