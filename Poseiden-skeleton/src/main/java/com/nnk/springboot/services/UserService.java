package com.nnk.springboot.services;

import com.nnk.springboot.domain.dao.User;
import com.nnk.springboot.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public User save(User user);

    public User update(UserDTO userDTO);

    public User delete(UserDTO userDTO);
}
