package Latch.Money4Mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Money4Mobs extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static final List<Mobs4MoneyPlayer> playerList = new ArrayList<>();
    private static final SetMobList sml = new SetMobList();
    private MobConfigManager MobCfgm;
    private ItemListManager ItemCfgm;
    @Override
    public void onEnable() {
        loadMobConfigManager();
        loadItemConfigManager();
        getServer().getPluginManager().registerEvents(this, this);
        setupEconomy();
        loadConfig();
        reloadConfig();
        if (MobConfigManager.mobsCfg.getInt("mobs.Bee.worth.low") == 0){
            MobCfgm.createMobsConfig();
        };
        ItemCfgm.createItemsConfig();
        MobConfigManager.setMobListFromConfig();

        for(OfflinePlayer p : getServer().getOfflinePlayers()) {
            playerList.add(new Mobs4MoneyPlayer(p.getName(), true ));
        }

        Objects.requireNonNull(this.getCommand("mk")).setExecutor(new MkCommand());
        Objects.requireNonNull(this.getCommand("mk")).setTabCompleter(new MobWorthTabComplete());
        Objects.requireNonNull(this.getCommand("enc")).setExecutor(new EnchantCommand());
        Objects.requireNonNull(this.getCommand("enc")).setTabCompleter(new EnchantTabComplete());

        sml.getMobModel();
    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        MobKiller.setEvent(event);
        callRewardMobKiller(event);
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        int count = 0;
        for (Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (event.getPlayer().getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                count = 1;
            }
        }
        if (count == 0){
            playerList.add(new Mobs4MoneyPlayer((event.getPlayer().getName()), true ));
        }
    }

    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if(!event.getSpawnReason().toString().equals("NATURAL")){
            MobKiller.getSpawnReason(event);
        }
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event){
        removePlayerOnLeave(event);
    }

    public void setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
        setEconomy(econ);
    }

    public void setEconomy(Economy value) {
        econ = value;
    }

    public void removePlayerOnLeave(PlayerQuitEvent event){
        Player pa = event.getPlayer();
        for (int i = 0; i < playerList.size(); i++) {
            if (pa.toString().equals(playerList.get(i).getPlayerName())) {
                playerList.remove(i);
            }
        }
    }

    public void callRewardMobKiller(EntityDeathEvent event){
        Player pa = event.getEntity().getKiller();
        Entity e = event.getEntity();
        if (pa != null && pa.hasPermission("m4m.rewardMoney")) {
            loadConfig();
            MobKiller.rewardPlayerMoney(pa, e, econ);
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void loadMobConfigManager(){
        MobCfgm = new MobConfigManager();
        MobCfgm.setup();
    }

    public void loadItemConfigManager(){
        ItemCfgm = new ItemListManager();
        ItemCfgm.setup();
    }

    public static List<Mobs4MoneyPlayer> getPlayerList(){
        return playerList;
    }
}
