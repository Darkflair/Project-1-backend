package com.example.Project_1.Service;

import com.example.Project_1.entity.Ticket;
import com.example.Project_1.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTicket_ShouldSaveTicketWithPendingStatus() throws Exception {
        
        Ticket ticket = new Ticket();
        ticket.setDescription("Test Ticket");
        ticket.setAmount(100.0);

        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        
        Ticket createdTicket = ticketService.createTicket(ticket);

        
        assertEquals("Pending", createdTicket.getStatus());
        verify(ticketRepository).save(ticket);
    }

    @Test
    void createTicket_ShouldThrowException_WhenDescriptionIsNull() {
        
        Ticket ticket = new Ticket();
        ticket.setAmount(100.0);

        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.createTicket(ticket);
        });

        assertEquals("Ticket must have an amount and/or Description.", exception.getMessage());
    }

    @Test
    void getAllMessagesByUserId_ShouldReturnTicketsForUser() {
        
        int userId = 1;
        List<Ticket> tickets = List.of(new Ticket(), new Ticket());
        when(ticketRepository.findByPostedBy(userId)).thenReturn(tickets);

        
        List<Ticket> result = ticketService.getAllMessagesByUserId(userId);

        
        assertEquals(2, result.size());
        verify(ticketRepository).findByPostedBy(userId);
    }

    @Test
    void approveTicket_ShouldUpdateTicketStatusToApproved() throws Exception {
        
        int ticketId = 1;
        Ticket ticket = new Ticket();
        ticket.setStatus("Pending");
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        
        Ticket approvedTicket = ticketService.approveTicket(ticketId);

        
        assertEquals("Approved", approvedTicket.getStatus());
        verify(ticketRepository).save(ticket);
    }

    @Test
    void approveTicket_ShouldThrowException_WhenTicketNotPending() {
        
        int ticketId = 1;
        Ticket ticket = new Ticket();
        ticket.setStatus("Approved");
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.approveTicket(ticketId);
        });

        assertEquals("Tickets cannot be approved unless pending", exception.getMessage());
    }

    @Test
    void declineTicket_ShouldUpdateTicketStatusToDeclined() throws Exception {
        
        int ticketId = 1;
        Ticket ticket = new Ticket();
        ticket.setStatus("Pending");
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        
        Ticket declinedTicket = ticketService.declineTicket(ticketId);

        
        assertEquals("Declined", declinedTicket.getStatus());
        verify(ticketRepository).save(ticket);
    }

    @Test
    void declineTicket_ShouldThrowException_WhenTicketNotPending() {
        
        int ticketId = 1;
        Ticket ticket = new Ticket();
        ticket.setStatus("Approved");
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.declineTicket(ticketId);
        });

        assertEquals("Tickets cannot be declined unless pending", exception.getMessage());
    }
}
