package Latch.Money4Mobs;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import Latch.Money4Mobs.Managers.MessagesConfigManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
    private static double money = 0;
    private static final List<MobSpawnedReason> msr = new ArrayList<>();
    private static Boolean giveMoney = false;
    private static String language = "";
    private static Boolean showMessage = true;

    public static void rewardPlayerMoney(CommandSender pa, Entity e, Economy econ) {
        setLanguage(pa);
        giveMoneyCheck(pa,e);
        Player player = null;
        if (pa instanceof Player){
            player = (Player) pa;
        }
        boolean samePlayer = player.getUniqueId().toString().equals(e.getUniqueId().toString());
        if (Boolean.TRUE.equals(samePlayer)) {
            giveMoney = false;
        }
        if (Boolean.TRUE.equals(giveMoney)){
            setRange(e, pa);
            setDefaultDrops();
            setCustomDrops(e, pa);
            displayKillMessage(pa);
            sendKillMessage(pa, econ);
        }
    }

    public static void setEvent(EntityDeathEvent e) {
        ede = e;
    }

    public static void setLanguage(CommandSender pa){
        int counter = 1;
        for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = UserManager.usersCfg.getString("users.user-" + counter + ".userId");
            assert userId != null;
            if (pa instanceof Player){
                Player player = (Player) pa;
                if(userId.equalsIgnoreCase(player.getUniqueId().toString())){
                    language = UserManager.usersCfg.getString("users.user-" + counter + ".language");
                }
            }
            counter++;
        }
    }

    public static void displayKillMessage(CommandSender pa){
        int counter = 1;
        for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = UserManager.usersCfg.getString("users.user-" + counter + ".userId");
            assert userId != null;
            if (pa instanceof Player) {
                Player player = (Player) pa;
                if (userId.equalsIgnoreCase(player.getUniqueId().toString())) {
                    showMessage = UserManager.usersCfg.getBoolean("users.user-" + counter + ".showMessage");
                }
            }
            counter++;
        }
    }

    public static void sendKillMessage(CommandSender pa, Economy econ){
        EconomyResponse r = null;
        Player player = null;
        if (pa instanceof Player) {
            player = (Player) pa;
        }
        if (Double.compare(money, 0.0) > 0.0) {
            r = econ.depositPlayer(player, money);
        } else if (Double.compare(money, 0.0) < 0.0) {
            r = econ.withdrawPlayer(player, Math.abs(money));
        }
        int counter = 1;
        for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = UserManager.usersCfg.getString("users.user-" + counter + ".userId");
            assert userId != null;
            if (player.getUniqueId().toString().equals(userId)) {
                showMessage = UserManager.usersCfg.getBoolean("users.user-" + counter + ".showMessage");
                language = UserManager.usersCfg.getString("users.user-" + counter + ".language");
                if (Boolean.TRUE.equals(showMessage)) {
                    if (r.amount != 0) {
                        if (r.transactionSuccess()) {
                            Double balance = r.balance;
                            df.format(balance);
                            if (Double.compare(money, 0.0) > 0.0) {
                                String moneyRewardedMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneyRewardedMessage" + ".message");
                                String moneyRewardedMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneyRewardedMessage" + ".location");
                                assert moneyRewardedMessage != null;
                                MkCommand.convertMessage(moneyRewardedMessage, pa, null, null, null, Math.round(r.amount * 100.0) / 100.0, null, null, Math.round(balance * 100.0) / 100.0, moneyRewardedMessageLocation);
                            } else {
                                String moneySubtractedMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneySubtractedMessage" + ".message");
                                String moneySubtractedMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneySubtractedMessage" + ".location");
                                assert moneySubtractedMessage != null;
                                MkCommand.convertMessage(moneySubtractedMessage, pa, null, null, null, Math.round(r.amount * 100.0) / 100.0, null, null, Math.round(balance * 100.0) / 100.0, moneySubtractedMessageLocation);
                            }
                        }
                    }
                }
            }
            counter++;
        }

    }

    public static void setCustomDrops(Entity e, CommandSender p){
        getLootingLevel();
        String es = e.toString();
        int randomNumber = rand.nextInt(100);

        MobConfigManager.mobsCfg.getBoolean("spawneggs");
        String[] name = es.split("Craft");

        for (MobModel mobModel : mm) {
            if (Boolean.TRUE.equals(mobModel.getCustomDrops())) {
                if (mobModel.getMobName().equals(name[1])) {
                    for (int j = 0; j < mobModel.getItems().size(); j++) {
                        int chance;
                        if (mobModel.getItems().get(j).getChance() == 0) {
                            chance = 100;
                        } else {
                            chance = mobModel.getItems().get(j).getChance();
                        }
                        if (randomNumber <= chance) {
                            String itemName = mobModel.getItems().get(j).getItemName();
                            Material m = Material.valueOf(itemName);
                            Integer amount = mobModel.getItems().get(j).getAmount();
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

    public static void giveMoneyCheck(CommandSender pa, Entity e){
        int counter = 0;
        Player player = null;
        if (pa instanceof Player) {
            player = (Player) pa;
        }
        String killerIP = player.getAddress().getAddress().toString();
        if (pa.hasPermission("m4m.rewardMoney") || pa.isOp() || pa.hasPermission("m4m.rewardmoney")) {
            for (MobSpawnedReason mobSpawnedReason : msr) {
                if (mobSpawnedReason.getUuid().equals(e.getUniqueId().toString())) {
                    counter = 1;
                    if (mobSpawnedReason.getMobSpawnReason().equalsIgnoreCase("SPAWNER_EGG")) {
                        Boolean spawnEggs = MobConfigManager.mobsCfg.getBoolean("spawneggs");
                        if (Boolean.TRUE.equals(spawnEggs)) {
                            giveMoney = true;
                        } else {
                            giveMoney = false;
                        }
                    } else if (mobSpawnedReason.getMobSpawnReason().equalsIgnoreCase("SPAWNER")) {
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
            List<Mobs4MoneyPlayer> pl = Money4Mobs.getPlayerList();
            for (int i = 0; i < Money4Mobs.getPlayerList().size(); i++){
                if (e.getName().equalsIgnoreCase(pl.get(i).playerName)) {
                    giveMoney = false;
                }
            }

            MobConfigManager.mobsCfg.getBoolean("mobs.Player.ipBan");
            if(e instanceof Player) {
                giveMoney = true;
                if (!pa.hasPermission("m4m.bypass.ipban")) {
                    if(MobConfigManager.mobsCfg.getBoolean("mobs.Player.ipBanFarming")) {
                        String entityIP = ((Player) e).getAddress().getAddress().toString();
                        if (killerIP.equals(entityIP)) {
                            giveMoney = false;
                        }
                    };
                }
            }

        } else {
            giveMoney = false;
        }
    }

    public static void setRange(Entity e, CommandSender pa){
        double level1 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-1");
        double level2 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-2");
        double level3 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-3");
        double level4 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-4");
        double level5 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-5");
        double level6 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-6");
        double level7 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-7");
        double level8 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-8");
        double level9 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-9");
        double level10 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-10");
        double level11 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-11");
        double level12 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-12");
        double level13 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-13");
        double level14 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-14");
        double level15 = MobConfigManager.mobsCfg.getDouble("group-multiplier.level-15");
        double operator = MobConfigManager.mobsCfg.getDouble("group-multiplier.operator");
        double multiplier = 1;
        if (pa.hasPermission("m4m.multiplier.level-15")) {
            multiplier = level15;
        }
        else if (pa.hasPermission("m4m.multiplier.level-14")) {
            multiplier = level14;
        }
        else if (pa.hasPermission("m4m.multiplier.level-13")) {
            multiplier = level13;
        }
        else if (pa.hasPermission("m4m.multiplier.level-12")) {
            multiplier = level12;
        }
        else if (pa.hasPermission("m4m.multiplier.level-11")) {
            multiplier = level11;
        }
        else if (pa.hasPermission("m4m.multiplier.level-10")) {
            multiplier = level10;
        }
        else if (pa.hasPermission("m4m.multiplier.level-9")) {
            multiplier = level9;
        }
        else if (pa.hasPermission("m4m.multiplier.level-8")) {
            multiplier = level8;
        }
        else if (pa.hasPermission("m4m.multiplier.level-7")) {
            multiplier = level7;
        }
        else if (pa.hasPermission("m4m.multiplier.level-6")) {
            multiplier = level6;
        }
        else if (pa.hasPermission("m4m.multiplier.level-5")) {
            multiplier = level5;
        }
        else if (pa.hasPermission("m4m.multiplier.level-4")) {
            multiplier = level4;
        }
        else if (pa.hasPermission("m4m.multiplier.level-3")) {
            multiplier = level3;
        }
        else if (pa.hasPermission("m4m.multiplier.level-2")) {
            multiplier = level2;
        }
        else if (pa.hasPermission("m4m.multiplier.level-1")) {
            multiplier = level1;
        }
        else {
            multiplier = 1;
        }
        if (pa.isOp()){
            multiplier = operator;
        }
        for (MobModel mobModel : mm) {
            String entity = "Craft" + mobModel.getMobName();
            String es = e.toString();
            if(e instanceof Player) {
                es = "CraftPlayer";
            }

            Double lowWorth = mobModel.getLowWorth();
            Double highWorth = mobModel.getHighWorth();
            if (es.equals(entity)) {
                money = mobModel.getHighWorth();
                Random r = new Random();
                money = lowWorth + (highWorth - lowWorth) * r.nextDouble();
                money = money * multiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        }
        double ridingHorseMultiplier = MobConfigManager.mobsCfg.getDouble("actions-multipliers.riding-horse.multiplier");
        Boolean isRidingHorseActive = MobConfigManager.mobsCfg.getBoolean("actions-multipliers.riding-horse.isActive");
        double ridingMuleMultiplier = MobConfigManager.mobsCfg.getDouble("actions-multipliers.riding-mule.multiplier");
        Boolean isRidingMuleActive = MobConfigManager.mobsCfg.getBoolean("actions-multipliers.riding-mule.isActive");
        double ridingDonkeyMultiplier = MobConfigManager.mobsCfg.getDouble("actions-multipliers.riding-donkey.multiplier");
        Boolean isRidingDonkeyActive = MobConfigManager.mobsCfg.getBoolean("actions-multipliers.riding-donkey.isActive");
        double ridingStriderMultiplier = MobConfigManager.mobsCfg.getDouble("actions-multipliers.riding-strider.multiplier");
        Boolean isRidingStriderActive = MobConfigManager.mobsCfg.getBoolean("actions-multipliers.riding-strider.isActive");
        double ridingPigMultiplier = MobConfigManager.mobsCfg.getDouble("actions-multipliers.riding-pig.multiplier");
        Boolean isRidingPigActive = MobConfigManager.mobsCfg.getBoolean("actions-multipliers.riding-pig.isActive");

        if (pa instanceof Player){
            Player player = (Player) pa;
            if (player.getVehicle() != null) {
                if (player.getVehicle().getName().equalsIgnoreCase("Horse") && Boolean.TRUE.equals(isRidingHorseActive)){
                    money = money * ridingHorseMultiplier;
                }
                if (player.getVehicle().getName().equalsIgnoreCase("Mule") && Boolean.TRUE.equals(isRidingMuleActive)){
                    money = money * ridingMuleMultiplier;
                }
                if (player.getVehicle().getName().equalsIgnoreCase("Donkey") && Boolean.TRUE.equals(isRidingDonkeyActive)){
                    money = money * ridingDonkeyMultiplier;
                }
                if (player.getVehicle().getName().equalsIgnoreCase("Strider") && Boolean.TRUE.equals(isRidingStriderActive)){
                    money = money * ridingStriderMultiplier;
                }
                if (player.getVehicle().getName().equalsIgnoreCase("Pig") && Boolean.TRUE.equals(isRidingPigActive)){
                    money = money * ridingPigMultiplier;
                }
                money = Math.round(money * 100.0) / 100.0;
            }
        }
    }


}
