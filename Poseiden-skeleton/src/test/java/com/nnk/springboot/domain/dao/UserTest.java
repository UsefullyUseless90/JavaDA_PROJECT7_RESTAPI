package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class UserTest {

    @Test
    void testConstructor() {
        User actualUser = new User();
        assertNull(actualUser.getFullname());
        assertNull(actualUser.getUsername());
        assertNull(actualUser.getRole());
        assertNull(actualUser.getPassword());
        assertNull(actualUser.getId());
    }

    @Test
    void testConstructor2() {
        User actualUser = new User(1, "janedoe", "iloveyou","user");

        assertNull(actualUser.getFullname());
        assertEquals("janedoe", actualUser.getUsername());
        assertNull(actualUser.getRole());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals(1, actualUser.getId().intValue());
    }

    @Test
    void testConstructor3() {
        User actualUser = new User(new UserDTO(new User(1, "janedoe", "iloveyou","user")));
        assertEquals("janedoe", actualUser.getUsername());
        assertEquals("iloveyou", actualUser.getPassword());
        assertEquals(1, actualUser.getId().intValue());
    }

}

