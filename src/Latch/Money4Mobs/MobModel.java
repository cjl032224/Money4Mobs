package Latch.Money4Mobs;

import java.util.List;

public class MobModel {
    protected String mobName;
    protected Double lowWorth;
    protected Double highWorth;
    protected Boolean customDrops;
    protected List<ItemModel> im;
    protected Boolean keepDefaultDrops;
    public MobModel(String mobName, Double lowWorth, Double highWorth, Boolean keepDefaultDrops, Boolean customDrops, List<ItemModel> im) {
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

    public Double getLowWorth() {
        return lowWorth;
    }

    public void setLowWorth(Double lowWorth){
        this.lowWorth = lowWorth;
    }

    public Double getHighWorth() {
        return highWorth;
    }

    public void setHighWorth(Double highWorth){
        this.highWorth = highWorth;
    }

    public Boolean getKeepDefaultDrops() {
        return keepDefaultDrops;
    }

    public void setKeepDefaultDrops(Boolean keepDefaultDrops){
        this.keepDefaultDrops = keepDefaultDrops;
    }

    public Boolean getCustomDrops() {
        return customDrops;
    }

    public void setCustomDrops(Boolean customDrops){
        this.customDrops = customDrops;
    }

    public List<ItemModel> getItems() {
        return im;
    }

    public void setItems(List<ItemModel> im){
        this.im = im;
    }

}
