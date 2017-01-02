package com.kawnayeen.logger.model;

import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kawnayeen on 1/2/17.
 */
public class LoggerUser extends Account implements UserDetails{

    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public LoggerUser(Account account) {
        super(account);
        for (Role role:getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialExpired();
    }
}
