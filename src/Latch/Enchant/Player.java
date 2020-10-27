package Latch.Enchant;

import java.util.List;

public class Player {
    protected String playerName;
    protected Boolean killerMessage;

    public Player() {};
    public Player(String playerName, Boolean killerMessage) {
        this.playerName = playerName;
        this.killerMessage = killerMessage;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean getKillerMessage() {
        return killerMessage;
    }

    public void setKillerMessage(Boolean killerMessage){
        this.killerMessage = killerMessage;
    }
}
