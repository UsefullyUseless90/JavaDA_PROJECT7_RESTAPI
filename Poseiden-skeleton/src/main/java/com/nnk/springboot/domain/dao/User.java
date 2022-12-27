package com.nnk.springboot.domain.dao;

import com.nnk.springboot.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Pattern(regexp = "^[A-Za-z]*$")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,70}$", message = "The password must contain at least an uppercase letter, a number and a special character")
    private String password;
    @Pattern(regexp = "^[A-Za-z\\s]*$")
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }

    public User(Integer id, String username, String password, String role) {
    this.id = id;
    this.password = password;
    this.username = username;
    this.role = role;
    }

    public User() {

    }
}
