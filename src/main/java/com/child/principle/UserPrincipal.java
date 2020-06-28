//package com.child.principle;
//
//import com.child.model.Account;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//public class UserPrincipal implements UserDetails {
//
//    private Long id;
//
//    @JsonIgnore
//    private String email;
//
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserPrincipal() {
//        super();
//    }
//
//    public UserPrincipal(Long id, String email, String password,Collection<? extends GrantedAuthority> authorities) {
//        super();
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserDetails create(Account m){
//        List<GrantedAuthority> authorities = m.getRoles().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        return new UserPrincipal(
//                m.getId(),
//                m.getEmail(),
//                m.getPassword(),
//                authorities
//        );
//    }
//
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
//    public String getUsername() {
//        return email;
//    }
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
//        return true;
//    }
//}
