package com.thiago.helpdesk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thiago.helpdesk.entity.ChangeStatus;

public interface ChangeStatusRepository extends MongoRepository<ChangeStatus, String> {

	Iterable<ChangeStatus> findByTicketIdOrderByDateChangeStatusDesc(String ticketId);

}