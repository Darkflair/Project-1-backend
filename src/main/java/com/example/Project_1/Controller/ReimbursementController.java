package com.example.Project_1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Project_1.Service.TicketService;
import com.example.Project_1.Service.UserService;
import com.example.Project_1.entity.User;
import com.example.Project_1.entity.Ticket;;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ReimbursementController {

    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User existingUser = userService.loginUser(user);
            return ResponseEntity.ok(existingUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/tickets/createTicket")
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket){
        try{
            Ticket newTicket = ticketService.createTicket(ticket);
            return ResponseEntity.ok(newTicket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/tickets/user/{userId}")
    public ResponseEntity<?> getAllTicketsByAccountId(@PathVariable Integer userId){
        List<Ticket> tickets = ticketService.getAllMessagesByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/status/{status}")
    public ResponseEntity<?> getAllTicketsByStatus(@PathVariable String status){
        List<Ticket> tickets = ticketService.getAllMessagesByStatus(status);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/user/{userId}/{status}")
    public List<Ticket> getTicketsByUserIdAndStatus(@PathVariable Integer userId, @PathVariable String status) {
        return ticketService.findByPostedByAndStatus(userId, status);
    }


    @PutMapping("/tickets/{ticketId}/approve")
    public ResponseEntity<?> approveTicket(@PathVariable Integer ticketId){
        try{
            Ticket ticket = ticketService.approveTicket(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Ticket can not be approved unless it is Pending");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/tickets/{ticketId}/decline")
    public ResponseEntity<?> declineTicket(@PathVariable Integer ticketId){
        try{
            Ticket ticket = ticketService.declineTicket(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Ticket can not be declined unless it is Pending");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
