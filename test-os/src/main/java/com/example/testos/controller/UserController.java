package com.example.testos.controller;


import com.example.testos.dto.ResponseDTO;
import com.example.testos.dto.UserDto;
import com.example.testos.entity.User;
import com.example.testos.infra.security.TokenService;
import com.example.testos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(path = "/user")
    public ResponseEntity<List<User>> findAll(){
        ResponseEntity<List<User>> response = null;
        List<User> users = repository.findAll();
        if(users == null || users.isEmpty() ){
            response = ResponseEntity.noContent().build();
        }else {
            response = ResponseEntity.ok(users);
        }
        return response;
    }

    @PostMapping(path = "/user")
    public void salvar(@RequestBody UserDto userDto){
        repository.save(new User(userDto));
        System.out.println("deu certo"+ userDto.email());
    }

//    @PostMapping(path = "user/login")
//    public ResponseEntity<String> Verification(@RequestBody UserDto userDto){
//        UserDetails userDetails = repository.findByEmail(userDto.email());
//        if (userDetails != null && userDetails.getPassword().equals(userDto.senha())){
//            String token = tokenService.generateToken((User) userDetails);
//            return ResponseEntity.ok(token);
//        } else {
//            return ResponseEntity.status(401).body("Senha ou email incorretos");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto body){
        System.out.println("ola");
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()) {
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(body.senha()));
            newUser.setEmail(body.email());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getEmail(), token));
        }
        return ResponseEntity.badRequest().build();
    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.senha(), user.getSenha())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getEmail(),token));
        }
        return ResponseEntity.badRequest().build();
    }
}
