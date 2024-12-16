package Model;

public class User{

    public String role;
    public String username;
    public String password;

    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;

    }

    public User(String role, String username, String password){
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "User{" +
                "role= " + role + ' ' +
                "username= " + username + ' ' +
                "password= " + password + ' ' +
                '}';
    }
}

