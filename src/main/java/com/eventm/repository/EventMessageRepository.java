package com.eventm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.eventm.domain.EventMessageModel;

import java.util.List;

/**
 * @author visweswarar
 */
public interface EventMessageRepository extends MongoRepository<EventMessageModel, String> {
	
	
    List<EventMessageModel> findAllByOrderByCreateDateAsc();
}
