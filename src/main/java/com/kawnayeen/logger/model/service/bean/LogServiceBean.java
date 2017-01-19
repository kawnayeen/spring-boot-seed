package com.kawnayeen.logger.model.service.bean;

import com.kawnayeen.logger.model.entity.Log;
import com.kawnayeen.logger.model.service.LogService;
import com.kawnayeen.logger.model.repository.LogRepository;
import com.kawnayeen.logger.model.service.exception.InvalidLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kawnayeen on 1/5/17.
 */
@Service
public class LogServiceBean implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Log create(Log log) {
        if(log.getId()!=null)
            throw new InvalidLogException("cannot preset log id");
        try {
            return logRepository.save(log);
        }catch (Exception e){
            throw new InvalidLogException();
        }
    }

    @Override
    @Cacheable(value = "log", key = "#id")
    public Log findOne(Long id) {
        return logRepository.findOne(id);
    }
}
