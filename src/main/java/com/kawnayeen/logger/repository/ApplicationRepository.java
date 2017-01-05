package com.kawnayeen.logger.repository;

import com.kawnayeen.logger.model.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Repository
public interface ApplicationRepository extends JpaRepository<Application,Long>{
    Application findByApplicationId(String applicationId);
}
