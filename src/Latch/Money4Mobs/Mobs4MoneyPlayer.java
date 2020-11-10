package Latch.Money4Mobs;

public class Mobs4MoneyPlayer {
    protected String playerName;
    protected Boolean killerMessage;

    public Mobs4MoneyPlayer() {};

    public Mobs4MoneyPlayer(String playerName, Boolean killerMessage) {
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
