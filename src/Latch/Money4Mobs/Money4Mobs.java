package Latch.Money4Mobs;

import Latch.Money4Mobs.Managers.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
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


public class Money4Mobs extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static final List<Mobs4MoneyPlayer> playerList = new ArrayList<>();
    private static final List<UserModel> userList = new ArrayList<>();
    private static final SetMobList sml = new SetMobList();
    private static MobConfigManager MobCfgm;
    private static ItemListManager ItemCfgm;
    private static UserManager UserCfgm;
    private static ConfigFileManager ConfigCfgm;
    private static int entityId;
    private static MessagesConfigManager MessagesCfgm;
    Boolean isUpdateAvailable = false;
    Boolean checkForUpdate = true;

    @Override
    public void onEnable() {

        try {
            loadMobConfigManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadUserConfigManager();
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
        if (!UserManager.usersCfg.getBoolean("users.user-1.showMessage")) {
            UserCfgm.createUsersConfig();
        }
        try {
            MobConfigManager.setMobListFromConfig();
            loadMobConfigManager();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (OfflinePlayer p : getServer().getOfflinePlayers()) {
            String language = ConfigFileManager.configCfg.getString("defaultLanguage");
            if (language == null){
                language = "english";
            }
            userList.add(new UserModel(p.getName(), p.getUniqueId().toString(), true, language, null));
            playerList.add(new Mobs4MoneyPlayer(p.getName(), true));

        }

        int pluginId = 9484; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));


        Objects.requireNonNull(this.getCommand("mk")).setExecutor(new MkCommand());
        Objects.requireNonNull(this.getCommand("mk")).setTabCompleter(new MobWorthTabComplete());

        if (Boolean.TRUE.equals(MobConfigManager.mobsCfg.isSet("checkForUpdate"))) {
            checkForUpdate = MobConfigManager.mobsCfg.getBoolean("checkForUpdate");
        }

        new UpdateChecker(this, 85373).getVersion(version -> {
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
        File configFile = new File(Money4Mobs.getPlugin(Money4Mobs.class).getDataFolder(), "config.yml");
            try {
                MobKiller.setEvent(event);
                callRewardMobKiller(event);
            } catch (RuntimeException | NoClassDefFoundError | IOException ignore) {
            }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) throws IOException {
        int count = 0;
        for (Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (event.getPlayer().getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                count = 1;
            }
        }
        String language = MobConfigManager.mobsCfg.getString("defaultLanguage");
        if (language == null){
            language = "english";
        }
        UserModel um = new UserModel(event.getPlayer().getName(), event.getPlayer().getUniqueId().toString(), true, language, event.getPlayer().getAddress().toString());
        UserManager.addUserToList(um);
        if (count == 0) {
            playerList.add(new Mobs4MoneyPlayer((event.getPlayer().getName()), true));
        }
        if (Boolean.TRUE.equals(checkForUpdate)){
            if (event.getPlayer().isOp() && Boolean.TRUE.equals(isUpdateAvailable)) {
                event.getPlayer().sendMessage(ChatColor.RED + "[" + ChatColor.GOLD + "Money4Mobs" + ChatColor.RED + "] Update available -> " + ChatColor.AQUA + "https://www.spigotmc.org/resources/money4mobs.85373/.");
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
        Money4Mobs.entityId = entityId;
    }

    public void callRewardMobKiller(EntityDeathEvent event) throws IOException {
        Player pa = event.getEntity().getKiller();
        Entity e = event.getEntity();
        File configFile = new File(Money4Mobs.getPlugin(Money4Mobs.class).getDataFolder(), "config.yml");
        FileConfiguration configCfg = YamlConfiguration.loadConfiguration(configFile);
        if (pa != null && pa.hasPermission("m4m.rewardMoney") || pa.isOp() || pa.hasPermission("m4m.rewardmoney")) {
            boolean tamedWolvesGiveMoney = configCfg.getBoolean("tamedWolvesGiveMoney");
            if (getIsTamedWolf() == 0) {
                MobKiller.rewardPlayerMoney(pa, e);
            } else {
                if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                    MobKiller.rewardPlayerMoney(pa, e);
                }
            }
        }
        setIsTamedWolf(0);
    }

    public static void loadMobConfigManager() throws IOException {
        MobCfgm = new MobConfigManager();
        MobCfgm.setup();
    }

    public static void loadItemConfigManager() {
        ItemCfgm = new ItemListManager();
        ItemCfgm.setup();
    }

    static void loadUserConfigManager() {
        UserCfgm = new UserManager();
        UserCfgm.setup();
    }

    static void loadLanguageConfigManager() throws IOException {
        MessagesCfgm = new MessagesConfigManager();
        MessagesCfgm.setup();
    }
    static void loadConfigFileManager() throws IOException {
        ConfigCfgm = new ConfigFileManager();
        ConfigCfgm.setup();
    }

    public static List<Mobs4MoneyPlayer> getPlayerList() {
        return playerList;
    }

    public static List<UserModel> getUserList() {
        return userList;
    }

    public static void reloadConfigFiles() throws IOException {
        loadMobConfigManager();
        loadItemConfigManager();
        loadUserConfigManager();
        loadLanguageConfigManager();
        loadConfigFileManager();
    }
}
