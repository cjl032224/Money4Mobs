package Latch.Money4Mobs;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public abstract class MobKiller implements CommandExecutor {

    private static MobConfigManager MobCfgm;
    private static List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    private static List<MobModel> mm = MobCfgm.getMobModelFromConfig();
    private static EntityDeathEvent ede;
    private static Random rand = new Random();
    private static Integer lootingLevel;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static Integer money = 0;
    private static List<MobSpawnedReason> msr = new ArrayList<MobSpawnedReason>();
    private static Boolean giveMoney = false;

    public static void rewardPlayerMoney(Player pa, Entity e, Economy econ) {
        giveMoneyCheck(pa,e);
        if (Boolean.TRUE.equals(giveMoney)){
            setRange(e);
            setCustomDrops(e, pa);
            sendKillMessage(pa, econ);
        }
    }
    public static void setEvent(EntityDeathEvent e) {
        ede = e;
    }


    public static void sendKillMessage(Player pa, Economy econ){
        EconomyResponse r = econ.depositPlayer(pa, money);
        List<Mobs4MoneyPlayer> playerList = Money4Mobs.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            if (pa.getName().equals(playerList.get(i).getPlayerName())) {
                Boolean displayMessage = playerList.get(i).getKillerMessage();
                if(displayMessage == true) {
                    if (r.amount != 0) {
                        if(r.transactionSuccess()) {
                            Double balance = r.balance;
                            df.format(balance);
                            df.setRoundingMode(RoundingMode.UP);
                            pa.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                                    ChatColor.GRAY + "You were given " + ChatColor.GREEN + "$" + Math.round(r.amount) + ChatColor.GRAY + " and now have " + ChatColor.GREEN + "$" + df.format(balance)));
                        } else {
                            pa.sendMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }
                }
            }
        }
    }

    public static void setCustomDrops(Entity e, Player p){
        lootingLevel = 1;
        getLootingLevel();
        String es = e.toString();
        int randomNumber = rand.nextInt(100);
        Integer multiplier = 1;
        if (lootingLevel == 2){
            multiplier = 2;
        } else if (lootingLevel == 3){
            multiplier = 3;
        } else {
            multiplier = 1;
        }

        MobCfgm.mobsCfg.getBoolean("spawneggs");
        String[] name = es.split("Craft");
        for (Integer i = 0; i < mm.size(); i++){
            if(Boolean.TRUE.equals(mm.get(i).getCustomDrops())){
                if (mm.get(i).getMobName().equals(name[1])) {
                    if(!Boolean.TRUE.equals(mm.get(i).getKeepDefaultDrops())){
                        ede.getDrops().clear();
                    }
                    for (Integer j = 0; j < mm.get(i).getItems().size(); j++){
                        int chance;
                        if (mm.get(i).getItems().get(j).getChance() == 0){
                            chance = 100;
                        } else {
                            chance = mm.get(i).getItems().get(j).getChance();
                        }
                        if (randomNumber <= chance) {
                            String itemName = mm.get(i).getItems().get(j).getItemName();
                            Material m = Material.valueOf(itemName);
                            Integer amount = mm.get(i).getItems().get(j).getAmount();
                            ede.getDrops().add(new ItemStack(m, amount));
                        }
                    }
                }
            }

        }
    }

    public static void getSpawnReason(CreatureSpawnEvent e) {
        msr.add(new MobSpawnedReason(e.getSpawnReason().toString(), e.getEntity().getUniqueId().toString()));
    }

    public static void getLootingLevel(){
        Map<Enchantment, Integer> s = ede.getEntity().getKiller().getInventory().getItemInMainHand().getEnchantments();
        for (Map.Entry<Enchantment,Integer> entry : s.entrySet()){
            if (entry.getKey().toString().contains("looting")){
                lootingLevel = entry.getValue();
            }
        }
    }

    public static Boolean giveMoneyCheck(Player pa, Entity e){
        Integer counter = 0;
        for (int k = 0; k < msr.size(); k++){
            if(msr.get(k).getUuid().equals(e.getUniqueId().toString())){
                counter = 1;
                if(msr.get(k).getMobSpawnReason().equalsIgnoreCase("SPAWNER_EGG")){
                    Boolean spawnEggs = MobCfgm.mobsCfg.getBoolean("spawneggs");
                    if (Boolean.TRUE.equals(spawnEggs)) {
                        giveMoney = true;
                    } else {
                        giveMoney = false;
                    }
                }
                else if(msr.get(k).getMobSpawnReason().equalsIgnoreCase("SPAWNER")){
                    Boolean spawners = MobCfgm.mobsCfg.getBoolean("spawners");
                    if (Boolean.TRUE.equals(spawners)) {
                        giveMoney = true;
                    } else {
                        giveMoney = false;
                    }
                }
            }
        }
        if(counter == 0){
            giveMoney = true;
        }
        return giveMoney;
    }

    public static void setRange(Entity e){
        for(int i = 0; i < mm.size(); i++){
            String entity = "Craft"+mm.get(i).getMobName();
            String es = e.toString();
            Integer lowWorth = mm.get(i).getLowWorth();
            Integer highWorth = mm.get(i).getHighWorth();
            if(es.equals(entity)){
                money = mm.get(i).getHighWorth();
                money = (int)(Math.random() * (highWorth - lowWorth + 1) + lowWorth);

            }
        }
    }


}
