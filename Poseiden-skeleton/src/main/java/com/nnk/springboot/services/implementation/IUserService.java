package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.dao.User;
import com.nnk.springboot.domain.dto.UserDTO;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class IUserService implements UserService {

    @Autowired
    UserRepository uRepo;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    private static final Logger logger = LogManager.getLogger(IUserService.class);

    public List<User> findAll(){
        List<User> uList = uRepo.findAll();
        return uList;
    }
    public User findById(int id){
        User user = uRepo.findById(id).orElse(null);
        return user;
    }
    public User save(User user) {
        Optional<User> userEntityOptional = uRepo.findByUsername(user.getUsername());
        if (userEntityOptional.isPresent()) {
            logger.error("Invalid user with username :" + user.getUsername());
            throw new IllegalArgumentException("Invalid user with username :" + user.getUsername());
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            uRepo.save(user);
        }
        return user;
    }

    public User update(UserDTO userDTO){
        User user  = uRepo.findById(userDTO.getId()).orElse(null);
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        uRepo.save(user);
        return user;
    }
    public User delete(UserDTO userDTO){
        User user = uRepo.findById(userDTO.getId()).orElse(null);
        uRepo.delete(user);
        return user;
    }
}
