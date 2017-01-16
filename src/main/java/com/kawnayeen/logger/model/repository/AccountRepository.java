package com.kawnayeen.logger.model.repository;

import com.kawnayeen.logger.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kawnayeen on 1/2/17.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
}
