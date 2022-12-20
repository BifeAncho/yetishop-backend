package com.webproject.yetishop.service;

import com.webproject.yetishop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    //Cadastro Usuario
    User saveUser(User user);

    //Login Usuário
    User loginUser(String email, String password);

    //Deletar Usuario
    void deleteUser(User user);

    //Editar Usuario
    User updateUser(User user);

    //Listar Usuario por Email
    User findUserbyEmail(String email);

    //Listar Todos os Usuarios
    List<User> findAllUser();

    void validateExists(String email);

    void validateUser(String email);
}
