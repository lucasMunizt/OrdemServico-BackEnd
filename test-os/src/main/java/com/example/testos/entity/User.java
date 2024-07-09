package com.example.testos.entity;

import com.example.testos.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "USUARIO")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    private String senha;

    public User(UserDto userDto) {
        this.id =  userDto.id();
        this.email =  userDto.email();
        this.senha = userDto.senha();
    }
}