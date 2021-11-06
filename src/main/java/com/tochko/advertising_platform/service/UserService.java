package com.tochko.advertising_platform.service;

import com.tochko.advertising_platform.model.User;
import com.tochko.advertising_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
public class UserService implements CommonService<User>{
   private final UserRepository userRepository;

   @Autowired
   public UserService(UserRepository repository) {
       this.userRepository=repository;
   }

    public Optional<User> get(long id) {
        return userRepository.findById(id);
    }

    public Page<User> getAll(Pageable pageable) {
       return userRepository.findAll(pageable);
    }

    public void add(User user) {
       userRepository.save(user);
    }

    public User update(long id, User user) {
        Optional<User> updatedUser = get(id);
        if(updatedUser.isPresent()) {
            updatedUser.get().setFirstName(user.getFirstName());
            updatedUser.get().setLastName(user.getLastName());
            updatedUser.get().setPhone(user.getPhone());
            updatedUser.get().setUserName(user.getUserName());
            updatedUser.get().setPassword(user.getPassword());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setRole(user.getRole());
            return userRepository.save(updatedUser.get());
        }
        return null;
    }
    public void delete(long id) {
        Optional<User> deletedUser = get(id);
        deletedUser.ifPresent(userRepository::delete);
    }
}
