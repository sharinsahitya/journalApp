package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntryUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void deleteByIdUser(ObjectId id){
        userRepository.deleteById(id);
    }
}
