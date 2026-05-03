package pl.edu.pwr.tkubik.ism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.tkubik.ism.aspect.LogExecutionTime;
import pl.edu.pwr.tkubik.ism.aspect.LogMethod;
import pl.edu.pwr.tkubik.ism.model.UserEntity;
import pl.edu.pwr.tkubik.ism.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // please note, that here we operate on Entity type (not DTO)
    @LogMethod
    @LogExecutionTime
    @Override
    public UserEntity addUser(UserEntity user) {
        return userRepository.save(user);
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public UserEntity deleteUserById(long id) {
        Optional<UserEntity> u = userRepository.findById(id);
        // u.ifPresent(User -> userRepository.deleteById(id));
        // What if there is no user ?
        if (u.isPresent()) {
            userRepository.deleteById(id);
            return u.get();
        }
        // return null if there is no user
        return null;
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public UserEntity updateUser(UserEntity user) {
        // save() run update if existed, insert if not
        return userRepository.save(user);
    }

    @LogMethod
    @LogExecutionTime
    @Override
    public UserEntity findUserById(long id) {
        // we return null if there is no user
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }
}