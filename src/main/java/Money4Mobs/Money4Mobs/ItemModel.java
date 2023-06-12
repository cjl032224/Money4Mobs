package Money4Mobs.Money4Mobs;

public class ItemModel {
    protected String itemName;
    protected Integer amount;
    protected Integer chance;

    public ItemModel(String itemName, Integer amount, Integer chance) {
        this.itemName = itemName;
        this.amount = amount;
        this.chance = chance;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getChance(){
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

}
