package com.webproject.yetishop.service.impl;

import com.webproject.yetishop.exception.RegraNegocioException;
import com.webproject.yetishop.model.User;
import com.webproject.yetishop.model.repository.UserRepository;
import com.webproject.yetishop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public User saveUser(User user) {
        validateUser(user.getEmail());
        return repository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        validateExists(email);
        User user = repository.findByEmail(email);
        if(!password.equals(user.getPassword())){
            throw new RuntimeException("erro ao logar, senha invalida");
        }
        System.out.println("logado com sucesso");
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        validateExists(user.getEmail());
        repository.delete(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional
    public User findUserbyEmail(String email) {
        validateExists(email);
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> findAllUser() {
        return repository.findAll();
    }

    @Override
    public void validateExists(String email){
        boolean exists = repository.existsByEmail(email);

        if(!exists){
            throw new RegraNegocioException("Não existe um usuário cadastrado com este E-mail");
        }
    }

    @Override
    public void validateUser(String email){
        boolean exists = repository.existsByEmail(email);

        if(exists){
            throw new RegraNegocioException("Ja existe um usuário cadastrado com este E-mail");
        }
    }
}
