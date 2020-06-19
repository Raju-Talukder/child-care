package com.child.principle;

import com.child.model.Account;
import com.child.repository.AcountRepository;
import com.child.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Component("userDetailsService")
@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private AcountRepository acountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = acountRepository.findByEmail(email);
        account.orElseThrow(() ->new UsernameNotFoundException("User not found with this email : " + email));
//        if (account != null) {
//            return UserPrinciple.create(account);
//        }
//        throw new UsernameNotFoundException("User '" + email + "' not found");
        return account.map(UserPrinciple::new).get();
    }
}
