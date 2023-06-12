package Money4Mobs.Money4Mobs;

public class MobSpawnedReason {
    protected String mobSpawnReason;
    protected String uuid;

    public MobSpawnedReason(String mobSpawnReason, String uuid) {
        this.mobSpawnReason = mobSpawnReason;
        this.uuid = uuid;
    }

    public String getMobSpawnReason(){
        return mobSpawnReason;
    }

    public void setMobSpawnReason(String MobName) {
        this.mobSpawnReason = mobSpawnReason;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

}