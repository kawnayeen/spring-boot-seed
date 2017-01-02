package com.kawnayeen.logger.repository;

import com.kawnayeen.logger.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kawnayeen on 1/2/17.
 */
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
}
