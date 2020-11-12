package Latch.Money4Mobs;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public abstract class MobKiller implements CommandExecutor {

    private static final List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
    private static EntityDeathEvent ede;
    private static final Random rand = new Random();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static Integer money = 0;
    private static final List<MobSpawnedReason> msr = new ArrayList<>();
    private static Boolean giveMoney = false;

    public static void rewardPlayerMoney(Player pa, Entity e, Economy econ) {
        giveMoneyCheck(pa,e);
        if (Boolean.TRUE.equals(giveMoney)){
            setRange(e);
            setDefaultDrops();
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
        for (Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (pa.getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                boolean displayMessage = mobs4MoneyPlayer.getKillerMessage();
                if (displayMessage) {
                    if (r.amount != 0) {
                        if (r.transactionSuccess()) {
                            Double balance = r.balance;
                            df.format(balance);
                            df.setRoundingMode(RoundingMode.UP);
                            String language = MobConfigManager.mobsCfg.getString("language");
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                pa.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                                        ChatColor.WHITE + "Vous avez reçu " + ChatColor.GREEN + Math.round(r.amount) + "$" +
                                        ChatColor.WHITE + " et vous avez maintenant " + ChatColor.GREEN + df.format(balance) + "$"));
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                pa.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                                        ChatColor.WHITE + "Te dieron " + ChatColor.GREEN + "$" + Math.round(r.amount) +
                                                ChatColor.WHITE + " y ahora tienes " + ChatColor.GREEN + "$" + df.format(balance)));
                            } else {

                                pa.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                                        ChatColor.WHITE + "You were given " + ChatColor.GREEN + "$" + Math.round(r.amount) +
                                                ChatColor.WHITE + " and now have " + ChatColor.GREEN + "$" + df.format(balance)));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void setCustomDrops(Entity e, Player p){
        getLootingLevel();
        String es = e.toString();
        int randomNumber = rand.nextInt(100);

        MobConfigManager.mobsCfg.getBoolean("spawneggs");
        String[] name = es.split("Craft");

        for (int i = 0; i < mm.size(); i++){
            if(Boolean.TRUE.equals(mm.get(i).getCustomDrops())){
                if (mm.get(i).getMobName().equals(name[1])) {
                    for (int j = 0; j < mm.get(i).getItems().size(); j++){
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

    public static void setDefaultDrops() {
        for (MobModel mobModel : mm) {
            if (ede.getEntity().getName().equalsIgnoreCase(mobModel.mobName)) {
                if (!Boolean.TRUE.equals(mobModel.getKeepDefaultDrops())) {
                    ede.getDrops().clear();
                }
            }
        }
    }

    public static void getSpawnReason(CreatureSpawnEvent e) {
        msr.add(new MobSpawnedReason(e.getSpawnReason().toString(), e.getEntity().getUniqueId().toString()));
    }

    public static void getLootingLevel(){
        Map<Enchantment, Integer> s = Objects.requireNonNull(ede.getEntity().getKiller()).getInventory().getItemInMainHand().getEnchantments();
        for (Map.Entry<Enchantment,Integer> entry : s.entrySet()){
            if (entry.getKey().toString().contains("looting")){
                Integer lootingLevel = entry.getValue();
            }
        }
    }

    public static void giveMoneyCheck(Player pa, Entity e){
        int counter = 0;
        if (pa.hasPermission("m4m.rewardMoney")) {
            for (int k = 0; k < msr.size(); k++){
                if(msr.get(k).getUuid().equals(e.getUniqueId().toString())){
                    counter = 1;
                    if(msr.get(k).getMobSpawnReason().equalsIgnoreCase("SPAWNER_EGG")){
                        Boolean spawnEggs = MobConfigManager.mobsCfg.getBoolean("spawneggs");
                        if (Boolean.TRUE.equals(spawnEggs)) {
                            giveMoney = true;
                        } else {
                            giveMoney = false;
                        }
                    }
                    else if(msr.get(k).getMobSpawnReason().equalsIgnoreCase("SPAWNER")){
                        Boolean spawners = MobConfigManager.mobsCfg.getBoolean("spawners");
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
        } else {
            giveMoney = false;
        }
    }

    public static void setRange(Entity e){
        for (MobModel mobModel : mm) {
            String entity = "Craft" + mobModel.getMobName();
            String es = e.toString();
            Integer lowWorth = mobModel.getLowWorth();
            Integer highWorth = mobModel.getHighWorth();
            if (es.equals(entity)) {
                money = mobModel.getHighWorth();
                money = (int) (Math.random() * (highWorth - lowWorth + 1) + lowWorth);
            }
        }
    }


}
