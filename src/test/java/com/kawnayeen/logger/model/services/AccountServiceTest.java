package com.kawnayeen.logger.model.services;

import com.kawnayeen.logger.AbstractTest;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.service.AccountService;
import com.kawnayeen.logger.model.service.exception.AccountNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by kawnayeen on 1/5/17.
 */
public class AccountServiceTest extends AbstractTest{
    @Autowired
    private AccountService accountService;

    @Test
    public void shouldReturnAccountForExistingUsername(){
        Account account = accountService.findByUsername("Anan");
        assertNotNull("Failure - Supposed to have account for Anan",account);
        assertEquals("Failure - Supposed to have one ROLE",1,account.getRoles().size());
    }

    @Test
    public void shouldReturnAccountForExistingId(){
        Long id = new Long(1);
        Account account = accountService.findOne(id);
        assertNotNull("Failure - Expected Not Null", account);
        assertEquals("Failure - Expected Id Attribute match",id,account.getId());
    }

    @Test
    public void notFoundForNonExistingId(){
        Long id = Long.MAX_VALUE;
        this.thrown.expect(AccountNotFoundException.class);
        accountService.findOne(id);
    }
}
