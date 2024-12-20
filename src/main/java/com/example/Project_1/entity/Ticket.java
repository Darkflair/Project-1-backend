package com.example.Project_1.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets") 
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    private Integer ticketId;

    @Column(name="amount")
    private double amount;

    @Column(name="description") 
    private String description;

    @Column(name="status")
    private String status;

    @Column (name="postedBy")
    private Integer postedBy;

  
    public Ticket() {
    }

   
    public Ticket(double amount, String description, String status, Integer postedBy) {
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.postedBy = postedBy;
    }

    public Integer getId() {
        return ticketId;
    }

    public void setId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPostedBy(){
        return postedBy;
    }

    public void setPostedBy(Integer postedBy){
        this.postedBy = postedBy;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
