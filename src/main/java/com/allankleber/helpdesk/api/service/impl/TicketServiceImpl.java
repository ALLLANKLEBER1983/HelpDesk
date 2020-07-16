package com.allankleber.helpdesk.api.service.impl;

import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.allankleber.helpdesk.api.entity.ChangeStatus;
import com.allankleber.helpdesk.api.entity.Ticket;
import com.allankleber.helpdesk.api.repository.ChangeStatusRepository;
import com.allankleber.helpdesk.api.repository.TicketRepository;
import com.allankleber.helpdesk.api.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository TicketRepository;
	
	@Autowired
	private ChangeStatusRepository ChangeStatusRepository;

	@Override
	public Ticket createOrUpdate(Ticket ticket) {
		return this.TicketRepository.save(ticket);
	}

	@Override
	public Ticket findById(String id) {
		return this.TicketRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		this.TicketRepository.delete(id);
		
	}

	@Override
	public Page<Ticket> listTicket(int page, int count) {
		 Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findAll(pages);
	}

	@Override
	public ChangeStatus createChangeStatus(ChangeStatus changeStatus) {
		return this.ChangeStatusRepository.save(changeStatus);
	}

	@Override
	public Iterable<ChangeStatus> listChangeStatus(String ticketId) {
		return this.ChangeStatusRepository.findByTicketIdOrderByDateChangeStatusDesc(ticketId);
	}

	@Override
	public Page<Ticket> findByCurrentUser(int page, int count, String userId) {
		Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findByUserIdOrderByDateDesc(pages, userId);
	}

	@Override
	public Page<Ticket> findByParameters(int page, int count, String title, String status, String priority) {
		Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingOrderByDateDesc(title, status, priority, pages);
	}

	@Override
	public Page<Ticket> findByParametersAndCurrentUser(int page, int count, String title, String status,
			String priority, String userId) {
		Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingAndUserIdOrderByDateDesc(title, status, priority, userId, pages);
		
	}

	@Override
	public Page<Ticket> findByNumber(int page, int count, Integer number) {
		Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findByNumber(number, pages);
	}

	@Override
	public Iterable<Ticket> findAll() {
		return this.TicketRepository.findAll();
	}

	@Override
	public Page<Ticket> findByParameterAndAssignedUser(int page, int count, String title, String status,
			String priority, String assignedUser) {
		Pageable pages =  new PageRequest(page, count);
		return this.TicketRepository.findByTitleIgnoreCaseContainingAndStatusIgnoreCaseContainingAndPriorityIgnoreCaseContainingAndAssignedUserIdOrderByDateDesc(title, status, priority, assignedUser, pages);
	}
	
	

}
