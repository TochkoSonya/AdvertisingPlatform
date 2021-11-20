//package com.tochko.advertising_platform.service;
//
//import com.tochko.advertising_platform.model.Role;
//import com.tochko.advertising_platform.model.User;
//import com.tochko.advertising_platform.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository=userRepository;
//    }
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//        return UserDetailsImpl.build(user);
//
////        User user = userRepository.findByUserName(username).get();
////        if (user == null) throw new UsernameNotFoundException(username);
////
////        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
////        for (Role role : user.getRoles()){
////            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
////        }
////
////        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
//
//    }
//
////    @Override
////    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
////
////        Optional<User> user = userRepository.findByUserName(userName);
////
////        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
////
////        return user.map(UserDetailsImpl::new).get();
////    }
//}
