package Money4Mobs;

import Money4Mobs.Configurations.MemberConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;


public class Main extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static final List<Money4Mobs.Money4Mobs.Mobs4MoneyPlayer> playerList = new ArrayList<>();
    private static final Money4Mobs.Money4Mobs.SetMobList sml = new Money4Mobs.Money4Mobs.SetMobList();
    private static Money4Mobs.Configurations.MobConfigManager MobCfgm;
    private static Money4Mobs.Configurations.ItemListManager ItemCfgm;
    private static MemberConfig UserCfgm;
    private static Money4Mobs.Configurations.MobSpawnedReasonManager MobReasonCfgm;
    private static Money4Mobs.Configurations.ConfigFileManager ConfigCfgm;
    private static int entityId;
    private static Money4Mobs.Configurations.MessagesConfigManager MessagesCfgm;
    Boolean isUpdateAvailable = false;
    Boolean checkForUpdate = true;

    @Override
    public void onEnable() {

        try {
            loadMobConfigManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadMemberCfg();
        loadMobReasonConfigManager();
        try {
            loadLanguageConfigManager();
            loadConfigFileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadItemConfigManager();
        getServer().getPluginManager().registerEvents(this, this);
        setupEconomy();
        ItemCfgm.createItemsConfig();
        if (!MemberConfig.membersCfg.getBoolean("users.user-1.showMessage")) {
            UserCfgm.createMembersConfig();
        }
        try {
            Money4Mobs.Configurations.MobConfigManager.setMobListFromConfig();
            loadMobConfigManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int pluginId = 9484; // <-- Replace with the id of your plugin!
        Money4Mobs.Money4Mobs.Metrics metrics = new Money4Mobs.Money4Mobs.Metrics(this, pluginId);

        // Optional: Add custom charts
        metrics.addCustomChart(new Money4Mobs.Money4Mobs.Metrics.SimplePie("chart_id", () -> "My value"));


        Objects.requireNonNull(this.getCommand("mk")).setExecutor(new Money4Mobs.Money4Mobs.MkCommand());
        Objects.requireNonNull(this.getCommand("mk")).setTabCompleter(new Money4Mobs.Money4Mobs.MobWorthTabComplete());

        if (Boolean.TRUE.equals(Money4Mobs.Configurations.MobConfigManager.mobsCfg.isSet("checkForUpdate"))) {
            checkForUpdate = Money4Mobs.Configurations.MobConfigManager.mobsCfg.getBoolean("checkForUpdate");
        }

        new Money4Mobs.Money4Mobs.UpdateChecker(this, 85373).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                System.out.println("[Money4Mobs] No new version available");
            } else {
                System.out.println("[Money4Mobs] New version available -> https://www.spigotmc.org/resources/money4mobs.85373");
                isUpdateAvailable = true;
            }
        });

        sml.getMobModel();
    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) throws IOException {
        File configFile = new File(Main.getPlugin(Main.class).getDataFolder(), "config.yml");
        FileConfiguration configCfg = YamlConfiguration.loadConfiguration(configFile);
        if (Boolean.FALSE.equals(configCfg.getBoolean("oldSpawnReasonLogic"))) {
            try {
                Money4Mobs.Money4Mobs.MobKiller.setEvent(event);
                callRewardMobKiller(event);
            } catch (RuntimeException | NoClassDefFoundError | IOException ignore) {
            }
            if (Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsCfg.isSet("spawnerMobs." + event.getEntity().getUniqueId())){
                Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsCfg.set("spawnerMobs." + event.getEntity().getUniqueId(), null);
                Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsCfg.save(Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsFile);
            }
        } else {
            try {
                Money4Mobs.Money4Mobs.MobKiller.setEvent(event);
                callRewardMobKiller(event);
            } catch (RuntimeException | NoClassDefFoundError | IOException ignore) {
            }
        }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) throws IOException {
        int count = 0;
        for (Money4Mobs.Money4Mobs.Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (event.getPlayer().getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                count = 1;
            }
        }
        String language = Money4Mobs.Configurations.MobConfigManager.mobsCfg.getString("defaultLanguage");
        if (language == null){
            language = "english";
        }
        MemberConfig.addMemberToCfg(event.getPlayer());
        if (count == 0) {
            playerList.add(new Money4Mobs.Money4Mobs.Mobs4MoneyPlayer((event.getPlayer().getName()), true));
        }
        if (Boolean.TRUE.equals(checkForUpdate)){
            if (event.getPlayer().isOp() && Boolean.TRUE.equals(isUpdateAvailable)) {
                event.getPlayer().sendMessage(ChatColor.RED + "[" + ChatColor.GOLD + "Money4Mobs" + ChatColor.RED + "] Update available -> " + ChatColor.AQUA + "https://www.spigotmc.org/resources/money4mobs.85373/.");
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (!event.getSpawnReason().toString().equals("NATURAL")) {
            try {
                File configFile = new File(Main.getPlugin(Main.class).getDataFolder(), "config.yml");
                FileConfiguration configCfg = YamlConfiguration.loadConfiguration(configFile);
                if (Boolean.FALSE.equals(configCfg.getBoolean("oldSpawnReasonLogic"))) {
                    File mobReasonsFile = Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsFile;
                    FileConfiguration mobReasonCfg = Money4Mobs.Configurations.MobSpawnedReasonManager.mobReasonsCfg;
                    if (mobReasonCfg.isSet("spawnerMobs")){
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".reasonSpawned", event.getSpawnReason().toString());
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".mobName", event.getEntity().getName());
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".location", event.getLocation().toString());
                    } else {
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".reasonSpawned", event.getSpawnReason().toString());
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".mobName", event.getEntity().getName());
                        mobReasonCfg.set("spawnerMobs." + event.getEntity().getUniqueId() + ".location", event.getLocation().toString());
                    }
                    mobReasonCfg.save(mobReasonsFile);
                }
                Money4Mobs.Money4Mobs.MobKiller.getSpawnReason(event);
            } catch (NoClassDefFoundError | NullPointerException | IllegalStateException | IOException e ) {
                System.out.println(ChatColor.YELLOW + "Warning: " + ChatColor.WHITE + "Couldn't get the spawn reason for the entity killed.");
                System.out.println(ChatColor.YELLOW + "Warning: " + ChatColor.WHITE + "If this continues and money is not rewarded, please restart server.");
                System.out.println(ChatColor.YELLOW + "Warning: " + ChatColor.WHITE + "This issue may occur after reloading the server or Money4Mobs");
            }
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        removePlayerOnLeave(event);
    }

    public void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
        setEconomy(econ);

    }

    public void setEconomy(Economy value) {
        econ = value;
    }

    public void removePlayerOnLeave(PlayerQuitEvent event) {
        Player pa = event.getPlayer();
        for (int i = 0; i < playerList.size(); i++) {
            if (pa.toString().equals(playerList.get(i).getPlayerName())) {
                playerList.remove(i);
            }
        }
    }

    @EventHandler
    public void test(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Wolf) {
            setIsTamedWolf(event.getEntity().getEntityId());
        }
    }

    public int getIsTamedWolf() {
        return entityId;
    }

    public void setIsTamedWolf(int entityId) {
        Main.entityId = entityId;
    }

    public void callRewardMobKiller(EntityDeathEvent event) throws IOException {
        Player pa = event.getEntity().getKiller();
        Entity e = event.getEntity();
        File configFile = new File(Main.getPlugin(Main.class).getDataFolder(), "config.yml");
        FileConfiguration configCfg = YamlConfiguration.loadConfiguration(configFile);
        if (pa != null && pa.hasPermission("m4m.rewardMoney") || pa.isOp() || pa.hasPermission("m4m.rewardmoney")) {
            boolean tamedWolvesGiveMoney = configCfg.getBoolean("tamedWolvesGiveMoney");
            if (getIsTamedWolf() == 0) {
                Money4Mobs.Money4Mobs.MobKiller.rewardPlayerMoney(pa, e);
            } else {
                if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                    Money4Mobs.Money4Mobs.MobKiller.rewardPlayerMoney(pa, e);
                }
            }
        }
        setIsTamedWolf(0);
    }

    public static void loadMobConfigManager() throws IOException {
        MobCfgm = new Money4Mobs.Configurations.MobConfigManager();
        MobCfgm.setup();
    }

    public static void loadItemConfigManager() {
        ItemCfgm = new Money4Mobs.Configurations.ItemListManager();
        ItemCfgm.setup();
    }

    public static void loadMemberCfg() {
        UserCfgm = new MemberConfig();
        UserCfgm.setup();
    }

    public static void loadLanguageConfigManager() throws IOException {
        MessagesCfgm = new Money4Mobs.Configurations.MessagesConfigManager();
        MessagesCfgm.setup();
    }
    private static void loadMobReasonConfigManager() {
        MobReasonCfgm = new Money4Mobs.Configurations.MobSpawnedReasonManager();
        MobReasonCfgm.setup();
    }

    public static void loadConfigFileManager() throws IOException {
        ConfigCfgm = new Money4Mobs.Configurations.ConfigFileManager();
        ConfigCfgm.setup();
    }

    public static List<Money4Mobs.Money4Mobs.Mobs4MoneyPlayer> getPlayerList() {
        return playerList;
    }

    public static void reloadConfigFiles() throws IOException {
        loadMobConfigManager();
        loadItemConfigManager();
        loadMemberCfg();
        loadLanguageConfigManager();
        loadMobReasonConfigManager();
        loadConfigFileManager();
    }
}
