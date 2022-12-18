package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOTest {
    /**
     * Method under test: {@link UserDTO#UserDTO(User)}
     */
    @Test
    void testConstructor() {
        User user = new User();
        user.setFullname("Dr Jane Doe");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUsername("janedoe");
        UserDTO actualUserDTO = new UserDTO(user);
        assertEquals(1, actualUserDTO.getId());
        assertEquals("janedoe", actualUserDTO.getUsername());
        assertEquals("iloveyou", actualUserDTO.getPassword());
    }
}

