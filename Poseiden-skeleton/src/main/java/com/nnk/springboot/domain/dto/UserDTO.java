package com.nnk.springboot.domain.dto;

import com.nnk.springboot.domain.dao.User;
import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username= user.getUsername();
        this.password = user.getPassword();
    }
}
