package Latch.Money4Mobs;

import Latch.Money4Mobs.Managers.ConfigFileManager;
import Latch.Money4Mobs.Managers.MessagesConfigManager;

import Latch.Money4Mobs.Managers.MobSpawnedReasonManager;
import Latch.Money4Mobs.Managers.MobConfigManager;
import Latch.Money4Mobs.Managers.UserManager;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public abstract class MobKiller implements CommandExecutor {

    private static final List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
    private static EntityDeathEvent ede;
    private static final Random rand = new Random();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static double money = 0;
    private static final List<MobSpawnedReason> msr = new ArrayList<>();
    private static Boolean giveMoney = true;
    private static String language = "";
    private static Boolean showMessage = true;
    private static final Random r = new Random();
    private static double percentageLost = 0;
    private static List<String> multiplierList = new ArrayList<>();
    private static double distance = 0;

    public static void rewardPlayerMoney(CommandSender pa, Entity e) throws IOException {
        Money4Mobs.loadConfigFileManager();
        setLanguage(pa);
        giveMoneyCheck(pa,e);
        Player player = null;
        if (pa instanceof Player){
            player = (Player) pa;
        }
        assert player != null;
        if (Boolean.TRUE.equals(giveMoney)){
            setDefaultDrops();
            setCustomDrops(e,pa);
            setRange(e, pa);
            displayKillMessage(pa);
            sendKillMessage(pa);
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

    public static void sendActionBar(Player player, String message) {
        CraftPlayer p = (CraftPlayer) player;
        IChatBaseComponent cbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
        PacketPlayOutChat ppoc = new PacketPlayOutChat(cbc, (byte) 2);
        p.getHandle().playerConnection.sendPacket(ppoc);
    }

    public static void sendKillMessage(CommandSender pa){
        Player player = null;
        if (pa instanceof Player) {
            player = (Player) pa;
        }
        int counter = 1;

        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        Economy econ = null;
        econ = rsp.getProvider();
        for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = UserManager.usersCfg.getString("users.user-" + counter + ".userId");
            assert userId != null;
            assert player != null;
            if (player.getUniqueId().toString().equals(userId)) {
                showMessage = UserManager.usersCfg.getBoolean("users.user-" + counter + ".showMessage");
                language = UserManager.usersCfg.getString("users.user-" + counter + ".language");
                if (Boolean.TRUE.equals(showMessage)) {
                    if (money != 0) {
                        Double balance = econ.getBalance(player);
                        df.format(balance);
                        if (Double.compare(money, 0.0) > 0.0) {
                            econ.depositPlayer(player, money);
                            balance = econ.getBalance(player);
                            String moneyRewardedMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneyRewardedMessage" + ".message");
                            String moneyRewardedMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneyRewardedMessage" + ".location");
                            assert moneyRewardedMessage != null;
                            MkCommand.convertMessage(moneyRewardedMessage, pa, null, null, null, Math.round(money * 100.0) / 100.0, null, null, Math.round(balance * 100.0) / 100.0, moneyRewardedMessageLocation, null);
                        } else {
                            econ.withdrawPlayer(player, Math.abs(money));
                            String moneySubtractedMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneySubtractedMessage" + ".message");
                            String moneySubtractedMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".moneySubtractedMessage" + ".location");
                            assert moneySubtractedMessage != null;
                            MkCommand.convertMessage(moneySubtractedMessage, pa, null, null, null, Math.round(Math.abs(money) * 100.0) / 100.0, null, null, Math.round(balance * 100.0) / 100.0, moneySubtractedMessageLocation, null);
                        }

                    }
                }
            }
            counter++;
        }

    }

    public static void setCustomDrops(Entity e, CommandSender p){
        getLootingLevel();
        int randomNumber = rand.nextInt(100);
        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            if (mobObject.equalsIgnoreCase(e.getName())) {
                String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                boolean customDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".customDrops");
                if (Boolean.TRUE.equals(customDrops)){
                    int numberOfDrops = 0;
                    for(String drop : MobConfigManager.mobsCfg.getConfigurationSection("mobs." + mobName + ".drops").getKeys(false)) {
                        numberOfDrops++;
                    }
                    int counter = 1;
                    for (int l = 0; l < numberOfDrops; l++) {
                        String itemName = MobConfigManager.mobsCfg.getString("mobs." + mobName + ".drops.item-" + counter + ".name");
                        int amount = MobConfigManager.mobsCfg.getInt("mobs." + mobName + ".drops.item-" + counter + ".amount");
                        double chance = MobConfigManager.mobsCfg.getDouble("mobs." + mobName + ".drops.item-" + counter + ".chance");
                        counter++;
                        if (chance == 0){
                            chance = 100;
                        }
                        if (randomNumber <= chance) {
                            Material m = Material.valueOf(itemName);
                            ede.getDrops().add(new ItemStack(m, amount));
                        }
                    }
                }
            }
        }
    }

    public static void setDefaultDrops() {
        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            if (mobObject.equalsIgnoreCase(ede.getEntity().getName())) {
                boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobObject + ".keepDefaultDrops");
                if (!Boolean.TRUE.equals(defaultDrops)) {
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

    public static void giveMoneyCheck(CommandSender pa, Entity e) throws IOException {
        int counter = 0;
        Player predator = null;
        if (pa instanceof Player) {
            predator = (Player) pa;
        }
        assert predator != null;
        String killerIP = predator.getAddress().getAddress().toString();
        if (pa.hasPermission("m4m.rewardMoney") || pa.isOp() || pa.hasPermission("m4m.rewardmoney")) {
            File configFile = new File(Money4Mobs.getPlugin(Money4Mobs.class).getDataFolder(), "config.yml");
            FileConfiguration configCfg = YamlConfiguration.loadConfiguration(configFile);
            if (Boolean.FALSE.equals(configCfg.getBoolean("oldSpawnReasonLogic"))) {
                int numberOfMobs = 1;
                FileConfiguration mobReasonCfg = MobSpawnedReasonManager.mobReasonsCfg;
                File mobReasonsFile = MobSpawnedReasonManager.mobReasonsFile;
                Money4Mobs.loadConfigFileManager();
                for (String mobUUID : mobReasonCfg.getConfigurationSection("spawnerMobs").getKeys(false)) {
                    if (mobUUID.equalsIgnoreCase(e.getUniqueId().toString())) {
                        if (Objects.requireNonNull(mobReasonCfg.getString("spawnerMobs." + mobUUID + ".reasonSpawned")).equalsIgnoreCase("SPAWNER_EGG")) {
                            Boolean spawnEggs = ConfigFileManager.configCfg.getBoolean("spawneggs");
                            giveMoney = Boolean.TRUE.equals(spawnEggs);
                        } else if (Objects.requireNonNull(mobReasonCfg.getString("spawnerMobs." + mobUUID + ".reasonSpawned")).equalsIgnoreCase("SPAWNER")) {
                            Boolean spawners = ConfigFileManager.configCfg.getBoolean("spawners");
                            giveMoney = Boolean.TRUE.equals(spawners);
                        }
                        MobSpawnedReasonManager.mobReasonsCfg.set("spawnerMobs." + mobUUID, null);
                        mobReasonCfg.save(mobReasonsFile);
                    }
                    numberOfMobs++;
                }
            } else {
                for (MobSpawnedReason mobSpawnedReason : msr) {
                    if (mobSpawnedReason.getUuid().equals(e.getUniqueId().toString())) {
                        counter = 1;
                        if (mobSpawnedReason.getMobSpawnReason().equalsIgnoreCase("SPAWNER_EGG")) {
                            Boolean spawnEggs = ConfigFileManager.configCfg.getBoolean("spawneggs");
                            giveMoney = Boolean.TRUE.equals(spawnEggs);
                        } else if (mobSpawnedReason.getMobSpawnReason().equalsIgnoreCase("SPAWNER")) {
                            Boolean spawners = ConfigFileManager.configCfg.getBoolean("spawners");
                            giveMoney = Boolean.TRUE.equals(spawners);
                        }
                    }
                }
                if(counter == 0){
                    giveMoney = true;
                }
            }
            List<Mobs4MoneyPlayer> pl = Money4Mobs.getPlayerList();
            for (int i = 0; i < Money4Mobs.getPlayerList().size(); i++){
                if (e.getName().equalsIgnoreCase(pl.get(i).playerName)) {
                    giveMoney = false;
                }
            }

            RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
            Economy econ = null;
            econ = rsp.getProvider();

            MobConfigManager.mobsCfg.getBoolean("mobs.Player.ipBan");
            if(e instanceof Player) {
                giveMoney = true;
                if (!pa.hasPermission("m4m.bypass.ipban")) {
                    if(MobConfigManager.mobsCfg.getBoolean("mobs.Player.ipBanFarming")) {
                        String entityIP = ((Player) e).getAddress().getAddress().toString();
                        if (killerIP.equals(entityIP)) {
                            giveMoney = false;
                        }
                    }
                } else {
                    if(MobConfigManager.mobsCfg.getBoolean("mobs.Player.enablePercentageDrop")){
                        giveMoney = false;
                        percentageLost = MobConfigManager.mobsCfg.getDouble("mobs.Player.percentageDropAmount");
                        Player prey = ((Player) e).getPlayer();
                        double preyBalance = econ.getBalance(prey);
                        double amountToSubtract = (preyBalance / 100) * percentageLost;
                        econ.withdrawPlayer(prey, amountToSubtract);
                        econ.depositPlayer(predator, amountToSubtract);
                        String playerKilledPlayerMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".playerKilledPlayerMessage" + ".message");
                        String playerKilledPlayerMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".playerKilledPlayerMessage" + ".location");
                        assert playerKilledPlayerMessage != null;
                        MkCommand.convertMessage(playerKilledPlayerMessage, predator, null, null, null, Math.round(amountToSubtract * 100.0) / 100.0, null, null, Math.round(econ.getBalance(predator) * 100.0) / 100.0, playerKilledPlayerMessageLocation, null);
                        String playerKilledByPlayerMessage = MessagesConfigManager.messagesCfg.getString("language." + language + ".playerKilledByPlayerMessage" + ".message");
                        String playerKilledByPlayerMessageLocation = MessagesConfigManager.messagesCfg.getString("language." + language + ".playerKilledByPlayerMessage" + ".location");
                        assert playerKilledByPlayerMessage != null;
                        MkCommand.convertMessage(playerKilledByPlayerMessage, prey, null, null, null, Math.round(amountToSubtract * 100.0) / 100.0, null, null, Math.round(econ.getBalance(prey) * 100.0) / 100.0, playerKilledByPlayerMessageLocation, null);
                    }
                }
            }

        } else {
            giveMoney = false;
        }
    }

    public static void setRange(Entity e, CommandSender pa) throws IOException {
        double levelMultiplier = 1;
        List<String> levelList = new ArrayList<>();
        Money4Mobs.loadMobConfigManager();
        Money4Mobs.loadConfigFileManager();
        levelList.addAll(ConfigFileManager.configCfg.getConfigurationSection("group-multiplier").getKeys(false));
        for (String level : levelList) {
            if ( pa.hasPermission("m4m.multiplier." + level)){
                levelMultiplier = ConfigFileManager.configCfg.getDouble("group-multiplier."+level);
            }
        }
        double operator = ConfigFileManager.configCfg.getDouble("group-multiplier.operator");

        if (pa.isOp()){
            levelMultiplier = operator;
        }
        String mobName = "";
        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            if (e.getName().replace(" ", "").toUpperCase().contains(mobObject.toUpperCase())){
                mobName = mobObject;
                double lowWorth = MobConfigManager.mobsCfg.getDouble("mobs." + mobObject + ".worth.low");
                double highWorth = MobConfigManager.mobsCfg.getDouble("mobs." + mobObject + ".worth.high");
                if (lowWorth == highWorth){
                    money = lowWorth;
                } else {
                    money = lowWorth + (highWorth - lowWorth) * r.nextDouble();
                    money = Math.round(money * 100.0) / 100.0;
                    
                }
            } else if (e instanceof Player) {
                double lowWorth = MobConfigManager.mobsCfg.getDouble("mobs.Player.worth.low");
                double highWorth = MobConfigManager.mobsCfg.getDouble("mobs.Player.worth.high");
                if (lowWorth == highWorth){
                    money = lowWorth;
                } else {
                    money = lowWorth + (highWorth - lowWorth) * r.nextDouble();
                    money = Math.round(money * 100.0) / 100.0;
                }
            }
        }

        double distance = getDistanceFromKiller(e, pa);

        boolean isMultiplierAggregate = ConfigFileManager.configCfg.getBoolean("actions-multipliers.isMultipliersAggregate");
        if (Boolean.TRUE.equals(isMultiplierAggregate)){
            isFallDamageMultiplier(money);
            isProjectileMultiplierPresent(money);
            isLongDistanceMultiplier(distance, money);
            isNoWeaponMultiplier(pa, money);
            isRidingHorseMultiplier(money, pa);
            isRidingDonkeyMultiplier(money, pa);
            isRidingMuleMultiplier(money, pa);
            isRidingPigMultiplier(money, pa);
            isRidingStriderMultiplier(money, pa);
            isPreyRidingMultiplier(money, pa, e);
        } else {
            getHighestPriority(e, pa);
        }
        money = money * levelMultiplier;
        String entityWorld = e.getWorld().getName();
        if (Boolean.TRUE.equals(ConfigFileManager.configCfg.getBoolean("disableMoneyReward")) || Boolean.FALSE.equals(MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".worlds." + entityWorld ))){
            money = 0;
        }
    }

    private static double getDistanceFromKiller(Entity e, CommandSender pa) {
        if (pa instanceof Player){
            Player player = (Player) pa;
            int predatorXLocation = player.getLocation().getBlockX();
            int predatorYLocation = player.getLocation().getBlockY();
            int predatorZLocation = player.getLocation().getBlockZ();
            int preyXLocation = e.getLocation().getBlockX();
            int preyYLocation = e.getLocation().getBlockY();
            int preyZLocation = e.getLocation().getBlockZ();
            distance = Math.sqrt(Math.pow(preyXLocation-predatorXLocation, 2) + Math.pow(preyYLocation-predatorYLocation, 2) + Math.pow(preyZLocation-predatorZLocation, 2));
        }
        return distance;
    }

    private static void isFallDamageMultiplier(double moneySaved) {
        double fallDamageMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.fallDamage.multiplier");
        Boolean isFallDamageActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.fallDamage.isActive");
        if (ede.getEntity().getLastDamageCause().getCause().toString().equals("FALL") && Boolean.TRUE.equals(isFallDamageActive)){
            money = money * fallDamageMultiplier;
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isProjectileMultiplierPresent(double moneySaved) {
        double projectileMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.projectile.multiplier");
        Boolean isProjectileActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.projectile.isActive");
        if (ede.getEntity().getLastDamageCause().getCause().toString().equals("PROJECTILE") && Boolean.TRUE.equals(isProjectileActive)){
            money = money * projectileMultiplier;
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isLongDistanceMultiplier(double distance, double moneySaved) {
        double distanceMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.longDistance.multiplier");
        Boolean isDistanceMultiplierActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.longDistance.isActive");
        double distanceLength = ConfigFileManager.configCfg.getDouble("actions-multipliers.longDistance.distance");
        if (Boolean.TRUE.equals(isDistanceMultiplierActive) && distance >= distanceLength){
            money = money * distanceMultiplier;
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isNoWeaponMultiplier(CommandSender pa, double moneySaved) {
        if (pa instanceof Player) {
            Player player = (Player) pa;
            if (ede.getEntity().getLastDamageCause().getCause().toString().equals("ENTITY_ATTACK")) {
                String itemInHand = player.getInventory().getItemInMainHand().getType().toString();
                if (!itemInHand.contains("HOE") && !itemInHand.contains("SWORD") && !itemInHand.contains("AXE") && !itemInHand.contains("SHOVEL") && !itemInHand.contains("SHIELD") && !itemInHand.contains("BOW") && !itemInHand.contains("TRIDENT") && !itemInHand.contains("CROSSBOW") && !itemInHand.contains("FISHING_ROD")){
                    double noWeaponMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.noWeapon.multiplier");
                    Boolean isNoWeaponMultiplierActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.noWeapon.isActive");
                    if (Boolean.TRUE.equals(isNoWeaponMultiplierActive)){
                        money = money * noWeaponMultiplier;
                        money = Math.round(money * 100.0) / 100.0;
                    }
                }
            }
        }
    }

    private static void isRidingHorseMultiplier(double moneySaved, CommandSender pa) {
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa)) && getVehicle(pa).equals("Horse")) {
            double ridingHorseMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.riding-horse.multiplier");
            Boolean isRidingHorseActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.riding-horse.isActive");
            if (Boolean.TRUE.equals(isRidingHorseActive)) {
                money = money * ridingHorseMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isRidingMuleMultiplier(double moneySaved, CommandSender pa) {
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa)) && getVehicle(pa).equals("Mule")) {
            double ridingMuleMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.riding-mule.multiplier");
            Boolean isRidingMuleActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.riding-mule.isActive");
            if (Boolean.TRUE.equals(isRidingMuleActive)) {
                money = money * ridingMuleMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isRidingDonkeyMultiplier(double moneySaved, CommandSender pa) {
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa)) && getVehicle(pa).equals("Donkey")){
            double ridingDonkeyMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.riding-donkey.multiplier");
            Boolean isRidingDonkeyActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.riding-donkey.isActive");
            if (Boolean.TRUE.equals(isRidingDonkeyActive)){
                money = money * ridingDonkeyMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isRidingStriderMultiplier(double moneySaved, CommandSender pa) {
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa)) &&
                getVehicle(pa).equals("Strider")){
            double ridingStriderMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.riding-strider.multiplier");
            Boolean isRidingStriderActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.riding-strider.isActive");
            if (Boolean.TRUE.equals(isRidingStriderActive)){
                money = money * ridingStriderMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isRidingPigMultiplier(double moneySaved, CommandSender pa) {
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa)) && getVehicle(pa).equals("Pig")){
            double ridingPigMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.riding-pig.multiplier");
            Boolean isRidingPigActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.riding-pig.isActive");
            if (Boolean.TRUE.equals(isRidingPigActive)){
                money = money * ridingPigMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static void isPreyRidingMultiplier(double moneySaved, CommandSender pa, Entity e) {
        if (Boolean.TRUE.equals(isPreyIsOnVehicle(e))){
            double preyRidingMultiplier = ConfigFileManager.configCfg.getDouble("actions-multipliers.mountedMob.multiplier");
            Boolean isPreyRidingActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.mountedMob.isActive");
            if (Boolean.TRUE.equals(isPreyRidingActive)){
                money = money * preyRidingMultiplier;
                money = Math.round(money * 100.0) / 100.0;
            }
        } else {
            money = Math.round(money * 100.0) / 100.0;
        }
    }

    private static boolean ifPlayerOnVehicle(CommandSender pa) {
        boolean isRiding = false;
        if (pa instanceof Player){
            Player player = (Player) pa;
            if (player.getVehicle() != null) {
                isRiding = true;
            }
        }
        return isRiding;
    }

    private static boolean isPreyIsOnVehicle(Entity e) {
        boolean isRiding = false;
        if (e.getVehicle() != null) {
            isRiding = true;
        }
        return isRiding;
    }

    private static String getVehicle(CommandSender pa) {
        String vehicle = null;
        if (pa instanceof Player){
            Player player = (Player) pa;
            if (player.getVehicle() != null) {
                vehicle = player.getVehicle().getName();
            }
        }
        return vehicle;
    }

    private static void multiplierList(Entity e, CommandSender pa){
        multiplierList = new ArrayList<>();
        Boolean isFallDamageActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.fallDamage.isActive");
        if (ede.getEntity().getLastDamageCause().getCause().toString().equals("FALL") && Boolean.TRUE.equals(isFallDamageActive)){
            multiplierList.add("fallDamage");
        }
        Boolean isProjectileActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.projectile.isActive");
        if (ede.getEntity().getLastDamageCause().getCause().toString().equals("PROJECTILE") && Boolean.TRUE.equals(isProjectileActive)){
            multiplierList.add("projectile");
        }
        Boolean isDistanceMultiplierActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.longDistance.isActive");
        double distanceLength = ConfigFileManager.configCfg.getDouble("actions-multipliers.longDistance.distance");
        if (Boolean.TRUE.equals(isDistanceMultiplierActive) && getDistanceFromKiller(e, pa) >= distanceLength) {
            multiplierList.add("longDistance");
        }
        if (pa instanceof Player) {
            Player player = (Player) pa;
            if (ede.getEntity().getLastDamageCause().getCause().toString().equals("ENTITY_ATTACK")) {
                String itemInHand = player.getInventory().getItemInMainHand().getType().toString();
                if (!itemInHand.contains("HOE") && !itemInHand.contains("SWORD") && !itemInHand.contains("AXE") && !itemInHand.contains("SHOVEL") && !itemInHand.contains("SHIELD") && !itemInHand.contains("BOW") && !itemInHand.contains("TRIDENT") && !itemInHand.contains("CROSSBOW") && !itemInHand.contains("FISHING_ROD")) {
                    Boolean isNoWeaponMultiplierActive = ConfigFileManager.configCfg.getBoolean("actions-multipliers.noWeapon.isActive");
                    if (Boolean.TRUE.equals(isNoWeaponMultiplierActive)) {
                        multiplierList.add("noWeapon");
                    }
                }
            }
        }
        if (Boolean.TRUE.equals(ifPlayerOnVehicle(pa))){
            String vehicle = getVehicle(pa).toLowerCase();
            multiplierList.add("riding-" + vehicle);
        }
        if (Boolean.TRUE.equals(isPreyIsOnVehicle(e))){
            multiplierList.add("mountedMob");
        }
    }

    private static double getHighestPriority(Entity e, CommandSender pa) {
        double multiplier = 1;
        int priority = 0;
        String actionMultiplierString = null;
        double moneySaved = money;
        multiplierList(e, pa);
        for (String actionMultiplier : ConfigFileManager.configCfg.getConfigurationSection("actions-multipliers").getKeys(false)) {
            for (int i = 0; i < multiplierList.size(); i++){
                if (multiplierList.get(i).equals(actionMultiplier)){
                    if (ConfigFileManager.configCfg.getInt("actions-multipliers." + actionMultiplier + ".priority") > priority && Boolean.TRUE.equals(ConfigFileManager.configCfg.getBoolean("actions-multipliers." + actionMultiplier + ".isActive"))){
                        priority = ConfigFileManager.configCfg.getInt("actions-multipliers." + actionMultiplier + ".priority");
                        multiplier = ConfigFileManager.configCfg.getInt("actions-multipliers." + actionMultiplier + ".multiplier");
                        actionMultiplierString = actionMultiplier;
                    }
                }
            }
        }
        try {
            switch (Objects.requireNonNull(actionMultiplierString)) {
                case "fallDamage":
                    isFallDamageMultiplier(moneySaved);
                    break;
                case "projectile":
                    isProjectileMultiplierPresent(moneySaved);
                    break;
                case "longDistance":
                    isLongDistanceMultiplier(getDistanceFromKiller(e, pa), moneySaved);
                    break;
                case "noWeapon":
                    isNoWeaponMultiplier(pa, moneySaved);
                    break;
                case "riding-horse":
                    isRidingHorseMultiplier(moneySaved, pa);
                    break;
                case "riding-mule":
                    isRidingMuleMultiplier(moneySaved, pa);
                    break;
                case "riding-donkey":
                    isRidingDonkeyMultiplier(moneySaved, pa);
                    break;
                case "riding-strider":
                    isRidingStriderMultiplier(moneySaved, pa);
                    break;
                case "riding-pig":
                    isRidingPigMultiplier(moneySaved, pa);
                    break;
                case "mountedMob":
                    isPreyRidingMultiplier(moneySaved, pa, e);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + Objects.requireNonNull(actionMultiplierString));
            }
        } catch (NullPointerException ignored) {

        }
        return multiplier;
    }


}
