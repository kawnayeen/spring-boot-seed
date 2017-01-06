package com.kawnayeen.logger.security.basic.auth;

import com.kawnayeen.logger.model.LoggerUser;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by kawnayeen on 1/2/17.
 */
@Service
public class AccountUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByUsername(username);
        if(account==null)
            throw new UsernameNotFoundException("No user found with username: "+username);
        return new LoggerUser(account,false);
    }
}
