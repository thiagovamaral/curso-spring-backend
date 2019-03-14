package com.thiago.helpdesk.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.thiago.helpdesk.entity.ChangeStatus;
import com.thiago.helpdesk.entity.Ticket;
import com.thiago.helpdesk.repository.ChangeStatusRepository;
import com.thiago.helpdesk.repository.TicketRepository;
import com.thiago.helpdesk.services.TicketService;

@Component
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ChangeStatusRepository changeStatusRepository;

	public Ticket createOrUpdate(Ticket ticket) {
		return this.ticketRepository.save(ticket);
	}

	public Ticket findById(String id) {
		return this.ticketRepository.findOne(id);
	}

	public void delete(String id) {
		this.ticketRepository.delete(id);
	}

	public Page<Ticket> listTicket(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findAll(pages);
	}

	public Iterable<Ticket> findAll() {
		return this.ticketRepository.findAll();
	}

	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByUserIdOrderByDateDesc(pages,userId);
	}

	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.changeStatusRepository.save(changeStatus);
	}

	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
		return this.changeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	public Page<Ticket> findByParameters(int page, int count,String title,String status,String priority) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.
				findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingOrderByDateDesc(
						title,status,priority,pages);
	}

	public Page<Ticket> findByParametersAndCurrentUser(int page, int count,String title,String status,
			String priority,String userId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.
				findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndUserIdOrderByDateDesc(
						title,status,priority,userId,pages);
	}

	public Page<Ticket> findByNumber(int page, int count,Integer number){
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.findByNumber(number, pages);
	}

	public Page<Ticket> findByParametersAndAssignedUser(int page, int count,String title,String status,
			String priority,String assignedUserId) {
		Pageable pages = new PageRequest(page, count);
		return this.ticketRepository.
				findByTitleIgnoreCaseContainingAndStatusContainingAndPriorityContainingAndAssignedUserIdOrderByDateDesc(
						title,status,priority,assignedUserId,pages);
	}

}