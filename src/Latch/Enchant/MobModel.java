package Latch.Enchant;

public class MobModel {
    protected String mobName;
    protected Integer worth;

    public MobModel() {};
    public MobModel(String mobName, Integer worth) {
        this.mobName = mobName;
        this.worth = worth;
    }

    public String getMobName(){
        return mobName;
    }

    public void setMobName(String MobName) {
        this.mobName = mobName;
    }

    public Integer getWorth() {
        return worth;
    }

    public void setWorth(Integer worth){
        this.worth = worth;
    }

}
