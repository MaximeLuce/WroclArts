package pl.edu.pwr.tkubik.ism.service;

import pl.edu.pwr.tkubik.ism.model.UserEntity;

import java.util.List;

public interface UserService {
    public UserEntity addUser(UserEntity user);
    public UserEntity deleteUserById(long id);
    public List<UserEntity> findAllUsers();
    public UserEntity updateUser(UserEntity user);
    //@Cacheable ("users")
    public UserEntity findUserById(long id);
}