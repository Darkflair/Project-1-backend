package Model;

public class Ticket{
    private static int counter = 0;
    private final int ticket_id;
    private double amount;
    private String description;
    private String status;


    public Ticket(){
        this.ticket_id = ++counter;
    }

    public int getId(){
        return ticket_id;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String toString(){
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                "amount=" + amount + '\'' +
                "description" + description + '\'' +
                "status" + status + '\'' +
                '}';
    }
}