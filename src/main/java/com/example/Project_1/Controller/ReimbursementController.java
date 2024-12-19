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
    private TicketService ticketSerive;

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

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody Ticket ticket){
        try{
            Ticket newTicket = ticketSerive.createTicket(ticket);
            return ResponseEntity.ok(newTicket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}/tickets")
    public ResponseEntity<?> getAllTicketsByAccountId(@PathVariable Integer userId){
        List<Ticket> tickets = ticketSerive.getAllMessagesByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/tickets/{status}")
    public ResponseEntity<?> getAllTicketsByStatus(@PathVariable String status){
        List<Ticket> tickets = ticketSerive.getAllMessagesByStatus(status);
        return ResponseEntity.ok(tickets);
    }


    @PutMapping("/tickets/{ticketId}/approve")
    public ResponseEntity<Ticket> approveTicket(@PathVariable Integer ticketId){
        try{
            Ticket ticket = ticketSerive.approveTicket(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/tickets/{ticketId}/decline")
    public ResponseEntity<Ticket> declineTicket(@PathVariable Integer ticketId){
        try{
            Ticket ticket = ticketSerive.declineTicket(ticketId);
            return ResponseEntity.ok(ticket);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
