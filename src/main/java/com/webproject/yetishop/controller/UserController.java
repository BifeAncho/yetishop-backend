package com.webproject.yetishop.controller;

import com.webproject.yetishop.exception.RegraNegocioException;
import com.webproject.yetishop.model.User;
import com.webproject.yetishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping()
    public ResponseEntity register(@RequestBody User userData){
        User user = User.builder()
                    .email(userData.getEmail())
                    .password(userData.getPassword())
                    .cpf(userData.getCpf())
                    .phone(userData.getPhone())
                    .sex(userData.isSex())
                    .birthDate(userData.getBirthDate())
                    .build();
        try {
            User userSaved = service.saveUser(user);
            return new ResponseEntity(userSaved, HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{email}")
    public ResponseEntity delete(@PathVariable("email") String email){

        try{
            User user = service.findUserbyEmail(email);
            service.deleteUser(user);
            return ResponseEntity.ok("Usuário deletado com sucesso");
        }
        catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity login(@RequestBody User userData){

        try{
            User userAuth = service.loginUser(userData.getEmail(), userData.getPassword());
            return ResponseEntity.ok("Usuário " + userAuth.getEmail() + " logado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{email}")
    public ResponseEntity update(@RequestBody User userData, @PathVariable("email") String email){

        try{
            User userDTO =  service.findUserbyEmail(email);
            User userATT = User.builder()
                            .email(userDTO.getEmail())
                            .password(userData.getPassword())
                            .cpf(userData.getCpf())
                            .phone(userData.getPhone())
                            .sex(userData.isSex())
                            .birthDate(userData.getBirthDate())
                            .build();
            service.updateUser(userATT);

            return ResponseEntity.ok("Usuário " + userATT.getEmail() + " atualizado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


}
