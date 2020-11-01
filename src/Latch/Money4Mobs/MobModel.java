package Latch.Money4Mobs;

public class MobModel {
    protected String mobName;
    protected Integer lowWorth;
    protected Integer highWorth;
    public MobModel() {};
    public MobModel(String mobName, Integer lowWorth, Integer highWorth) {
        this.mobName = mobName;
        this.lowWorth = lowWorth;
        this.highWorth = highWorth;
    }

    public String getMobName(){
        return mobName;
    }

    public void setMobName(String MobName) {
        this.mobName = mobName;
    }

    public Integer getLowWorth() {
        return lowWorth;
    }

    public void setLowWorth(Integer lowWorth){
        this.lowWorth = lowWorth;
    }

    public Integer getHighWorth() {
        return highWorth;
    }

    public void setHighWorth(Integer highWorth){
        this.highWorth = highWorth;
    }

}
