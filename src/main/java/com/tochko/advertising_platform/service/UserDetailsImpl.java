//package com.tochko.advertising_platform.service;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.tochko.advertising_platform.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private static final long serialVersionUID = 1L;
//
//    private Long id;
//    private String userName;
//    private String email;
//    @JsonIgnore
//    private String password;
//    private boolean isActive;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Long id, String username, String email, String password,
//                           Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.userName = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
////    public UserDetailsImpl(User user) {
////        this.userName = user.getUserName();
////        this.password = user.getPassword();
////        this.isActive = user.isActive();
////        this.authorities = Arrays.stream(user.getRoles())
////                .map(SimpleGrantedAuthority::new)
////                .collect(Collectors.toList());
//////        SimpleGrantedAuthority simple = new SimpleGrantedAuthority();
//////Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString()));
////    }
//
//    public static UserDetailsImpl build(User user) {
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
//                .collect(Collectors.toList());
//
//        return new UserDetailsImpl(
//                user.getId(),
//                user.getUserName(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities);
//    }
//
//    public UserDetailsImpl() {}
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() { return userName;}
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isActive;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}
