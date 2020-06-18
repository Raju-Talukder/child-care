package com.child.principle;

import com.child.model.Account;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User not found with this email : " + email));
        if (account != null) {
            return UserPrinciple.create(account);
        }
        throw new UsernameNotFoundException("User '" + email + "' not found");
    }
}
