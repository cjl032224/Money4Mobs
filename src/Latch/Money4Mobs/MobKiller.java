package Latch.Money4Mobs;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Logger;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public abstract class MobKiller implements CommandExecutor {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static MobConfigManager cfgm;

    private static List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    private static List<MobModel> mm = cfgm.getMobModelFromConfig();
    private static EntityDeathEvent ede;
    private static Random rand = new Random();
    private static Integer lootingLevel;
    private static DecimalFormat df = new DecimalFormat("0.00");
    private static Integer money = 0;
    private static List<MobSpawnedReason> msr = new ArrayList<MobSpawnedReason>();
    private static Boolean giveMoney = true;
    private static Boolean spawners = false;
    private static Boolean spawnEggs = false;


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
        int chance = rand.nextInt(100);
        Integer multiplier = 1;
        if (lootingLevel == 2){
            multiplier = 2;
        } else if (lootingLevel == 3){
            multiplier = 3;
        } else {
            multiplier = 1;
        }

        if(es.equals("CraftCreeper")){
            ede.getDrops().clear();
            ede.getDrops().add(new ItemStack(Material.GUNPOWDER, 2 * multiplier));
            if(chance > 75){
                ede.getDrops().add(new ItemStack(Material.TNT, 1 * multiplier));
            }
        }
        if(es.equals("CraftShulker")){
            ede.getDrops().clear();
            ede.getDrops().add(new ItemStack(Material.SHULKER_SHELL, 2 * multiplier));
        }
        if(es.equals("CraftEnderman")){
            ede.getDrops().clear();
            ede.getDrops().add(new ItemStack(Material.ENDER_PEARL, 1 * multiplier));
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
        checkSpawners();
        checkSpawnEggs();
        for (int k = 0; k < msr.size(); k++){
            if(msr.get(k).getUuid().equals(e.getUniqueId().toString())){
                counter = 1;

                if(Boolean.FALSE.equals(spawners) && Boolean.TRUE.equals(spawnEggs)){
                    if (msr.get(k).getMobSpawnReason().equals("SPAWNER") ){
                        giveMoney = false;
                        msr.remove(k);
                    }
                }
                if(Boolean.TRUE.equals(spawners) && Boolean.FALSE.equals(spawnEggs)){
                    if (msr.get(k).getMobSpawnReason().equals("SPAWNER_EGG") ){
                        giveMoney = false;
                        msr.remove(k);
                    }
                }
                if(Boolean.FALSE.equals(spawners) && Boolean.FALSE.equals(spawnEggs)){
                    if (msr.get(k).getMobSpawnReason().equals("SPAWNER") || msr.get(k).getMobSpawnReason().equals("SPAWNER_EGG")){
                        giveMoney = false;
                        msr.remove(k);
                    }
                }

            }
        }
        if(counter == 0){
            giveMoney = true;
        }
        return giveMoney;
    }

    public static void checkSpawners(){
        spawners = cfgm.mobsCfg.getBoolean("spawners");
    }

    public static void checkSpawnEggs(){
        spawnEggs = cfgm.mobsCfg.getBoolean("spawneggs");
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
