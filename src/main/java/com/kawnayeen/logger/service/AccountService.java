package com.kawnayeen.logger.service;

import com.kawnayeen.logger.model.entity.Account;

/**
 * Created by kawnayeen on 1/2/17.
 */
public interface AccountService {
    Account findByUsername(String username);
    Account findOne(Long id);
}




