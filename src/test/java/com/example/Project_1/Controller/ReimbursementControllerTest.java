package com.example.Project_1.Controller;

import com.example.Project_1.Service.TicketService;
import com.example.Project_1.Service.UserService;
import com.example.Project_1.entity.User;
import com.example.Project_1.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReimbursementControllerTest {

    @InjectMocks
    private ReimbursementController reimbursementController;

    @Mock
    private UserService userService;

    @Mock
    private TicketService ticketService;

    private User user;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("testuser");
        user.setPassword("password");

        ticket = new Ticket();
        ticket.setId(1);
        ticket.setPostedBy(1);
        ticket.setStatus("Pending");
    }

    @Test
    void testTicketDefaultConstructor() {
    Ticket defaultTicket = new Ticket();
    assertNotNull(defaultTicket);
    assertNull(defaultTicket.getId());
    assertEquals(0.0, defaultTicket.getAmount());
    assertNull(defaultTicket.getDescription());
    assertNull(defaultTicket.getStatus());
    assertNull(defaultTicket.getPostedBy());
}


    @Test
    void testRegisterUserSuccess() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = reimbursementController.registerUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testRegisterUserBadRequest() throws Exception {
        when(userService.registerUser(any(User.class))).thenThrow(new IllegalArgumentException("Invalid user data"));

        ResponseEntity<?> response = reimbursementController.registerUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid user data", response.getBody());
    }

    @Test
    void testLoginUserSuccess() throws Exception {
        when(userService.loginUser(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = reimbursementController.loginUser(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testLoginUserUnauthorized() throws Exception {
        when(userService.loginUser(any(User.class))).thenThrow(new Exception("Invalid credentials"));

        ResponseEntity<?> response = reimbursementController.loginUser(user);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void testCreateTicketSuccess() throws Exception {
        when(ticketService.createTicket(any(Ticket.class))).thenReturn(ticket);

        ResponseEntity<?> response = reimbursementController.createTicket(ticket);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testCreateTicketBadRequest() throws Exception {
        when(ticketService.createTicket(any(Ticket.class))).thenThrow(new IllegalArgumentException("Invalid ticket data"));

        ResponseEntity<?> response = reimbursementController.createTicket(ticket);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid ticket data", response.getBody());
    }

    @Test
    void testGetAllTicketsByAccountIdSuccess() {
        List<Ticket> tickets = Arrays.asList(ticket);
        when(ticketService.getAllMessagesByUserId(1)).thenReturn(tickets);

        ResponseEntity<?> response = reimbursementController.getAllTicketsByAccountId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
    }

    @Test
    void testGetAllTicketsByStatusSuccess() {
        List<Ticket> tickets = Arrays.asList(ticket);
        when(ticketService.getAllMessagesByStatus("Pending")).thenReturn(tickets);

        ResponseEntity<?> response = reimbursementController.getAllTicketsByStatus("Pending");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
    }

    @Test
    void testApproveTicketSuccess() throws Exception {
        when(ticketService.approveTicket(1)).thenReturn(ticket);

        ResponseEntity<?> response = reimbursementController.approveTicket(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testApproveTicketBadRequest() throws Exception {
        when(ticketService.approveTicket(1)).thenThrow(new IllegalArgumentException("Ticket can not be approved unless it is Pending"));

        ResponseEntity<?> response = reimbursementController.approveTicket(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Ticket can not be approved unless it is Pending", response.getBody());
    }

    @Test
    void testDeclineTicketSuccess() throws Exception {
        when(ticketService.declineTicket(1)).thenReturn(ticket);

        ResponseEntity<?> response = reimbursementController.declineTicket(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
    }

    @Test
    void testDeclineTicketBadRequest() throws Exception {
        when(ticketService.declineTicket(1)).thenThrow(new IllegalArgumentException("Ticket can not be declined unless it is Pending"));

        ResponseEntity<?> response = reimbursementController.declineTicket(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Ticket can not be declined unless it is Pending", response.getBody());
    }
}
