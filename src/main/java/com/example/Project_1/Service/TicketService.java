package com.example.Project_1.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project_1.entity.Ticket;
import com.example.Project_1.repository.TicketRepository;


@Service
public class TicketService {
private final TicketRepository ticketRepository;

@Autowired
public TicketService(TicketRepository ticketRepository){
    this.ticketRepository = ticketRepository;
}

public Ticket createTicket(Ticket ticket) throws Exception{
    if(ticket.getDescription() == null || ticket.getAmount() <= 0){
        throw new IllegalArgumentException("Ticket must have an amount and/or Description.");
    }
    ticket.setStatus("Pending");
    return ticketRepository.save(ticket);
}

public List<Ticket> getAllMessagesByUserId(Integer userId){
    return ticketRepository.findByPostedBy(userId);
}

public List<Ticket> getAllMessagesByStatus(String status){
    return ticketRepository.findByStatus(status);
}

public Ticket approveTicket(Integer ticketId) throws Exception{
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException());
    if(ticket.getStatus() != "Pending"){
        throw new IllegalArgumentException("Tickets cannot be approved unless pending");
    }

    ticket.setStatus("Approved");
    return ticketRepository.save(ticket);
}

public Ticket declineTicket(Integer ticketId) throws Exception{
    Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalArgumentException());
    if(ticket.getStatus() != "Pending"){
        throw new IllegalArgumentException("Tickets cannot be approved unless pending");
    }

    ticket.setStatus("Decline");
    return ticketRepository.save(ticket);
}
}
