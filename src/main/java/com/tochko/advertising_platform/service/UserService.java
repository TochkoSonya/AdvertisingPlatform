package com.tochko.advertising_platform.service;

import com.tochko.advertising_platform.model.ERole;
import com.tochko.advertising_platform.model.Role;
import com.tochko.advertising_platform.model.User;
import com.tochko.advertising_platform.repository.RoleRepository;
import com.tochko.advertising_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements CommonService<User>{
   private final UserRepository userRepository;
  // private final BCryptPasswordEncoder bCryptPasswordEncoder;
   private final RoleRepository roleRepository;

   @Autowired
   public UserService(UserRepository repository,
                      RoleRepository roleRepository
                      /*BCryptPasswordEncoder encoder*/) {
       this.userRepository=repository;
       this.roleRepository=roleRepository;
      // this.bCryptPasswordEncoder=encoder;
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

    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      //  user.setRoles(new HashSet<>(roleRepository.findAll()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User update(long id, User user) {
        Optional<User> optUser = get(id);
        if(optUser.isPresent()) {
            User updatedUser = User.builder()
                    .id(id)
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .phone(user.getPhone())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    //.role(user.getRole())
                    .build();
            return userRepository.save(updatedUser);
        }
        return null;
    }
    public void delete(long id) {
        Optional<User> deletedUser = get(id);
        deletedUser.ifPresent(userRepository::delete);
    }
}
