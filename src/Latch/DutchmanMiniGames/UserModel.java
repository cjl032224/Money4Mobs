package Latch.DutchmanMiniGames;
import java.util.UUID;

public class UserModel {
    public String userName;
    public String userId;
    public int tickets;
    public UserModel(String userName, String userId, int tickets) {
        this.userName = userName;
        this.userId = userId;
        this.tickets = tickets;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }


    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets){
        this.tickets = tickets;
    }


}
