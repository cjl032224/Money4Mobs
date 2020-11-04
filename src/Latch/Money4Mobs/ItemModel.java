package Latch.Money4Mobs;

public class ItemModel {
    protected String itemName;
    protected Integer amount;
    public ItemModel() {};
    public ItemModel(String itemName, Integer amount) {
        this.itemName = itemName;
        this.amount = amount;
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

}
