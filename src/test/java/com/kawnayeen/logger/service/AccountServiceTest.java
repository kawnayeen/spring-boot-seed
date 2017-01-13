package com.kawnayeen.logger.service;

import com.kawnayeen.logger.AbstractTest;
import com.kawnayeen.logger.model.entity.Account;
import com.kawnayeen.logger.model.service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kawnayeen on 1/5/17.
 */
public class AccountServiceTest extends AbstractTest{
    @Autowired
    private AccountService accountService;

    @Test
    public void shouldReturnAccountForExistingUsername(){
        Account account = accountService.findByUsername("Anan");
        Assert.assertNotNull("Failure - Supposed to have account for Anan",account);
        Assert.assertEquals("Failure - Supposed to have one ROLE",1,account.getRoles().size());
    }

    @Test
    public void shouldReturnAccountForExistingId(){
        Long id = new Long(1);
        Account account = accountService.findOne(id);
        Assert.assertNotNull("Failure - Expected Not Null", account);
        Assert.assertEquals("Failure - Expected Id Attribute match",id,account.getId());
    }

    @Test
    public void notFoundForNonExistingId(){
        Long id = Long.MAX_VALUE;
        Account account = accountService.findOne(id);
        Assert.assertNull("Failure - Expected Null",account);
    }

}
