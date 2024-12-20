package com.example.Project_1.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Project_1.entity.Ticket;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

    List<Ticket> findByPostedBy(Integer userId);
    List<Ticket> findByStatus(String status);
    List<Ticket> findByPostedByAndStatus(Integer userId, String status);
} 
