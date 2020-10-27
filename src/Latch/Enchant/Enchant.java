package Latch.Enchant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Enchant extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static List<Latch.Enchant.Player> playerList = new ArrayList<Latch.Enchant.Player>();
    private static SetMobList sml = new SetMobList();
    private MobConfigManager cfgm;
    @Override
    public void onEnable() {
        loadConfigManager();
        getServer().getPluginManager().registerEvents(this, this);
        setupEconomy();
        loadConfig();
        reloadConfig();
        if(cfgm.mobsCfg.getInt("mobs.Bee.worth") == 0){
            cfgm.createMobsConfig();
        };
        cfgm.setMobListFromConfig();
        for(OfflinePlayer p : getServer().getOfflinePlayers()) {
            playerList.add(new Latch.Enchant.Player(p.getName(), true ));
        }
        this.getCommand("enc").setExecutor(new EnchantCommand());
        this.getCommand("enc").setTabCompleter(new TabComplete());
        this.getCommand("mk").setExecutor(new ToggleMkMessage());
        this.getCommand("mk").setTabCompleter(new MobWorthTabComplete());
        sml.getMobModel();

    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        callRewardMobKiller(event);
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event){
        removePlayerOnLeave(event);
    }

    public Boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        setEconomy(econ);
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public void setEconomy(Economy value) {
        this.econ = value;
    }

    public void removePlayerOnLeave(PlayerQuitEvent event){
        Player pa = event.getPlayer();
        Latch.Enchant.Player player = new Latch.Enchant.Player();
        for (int i = 0; i < playerList.size(); i++) {
            if (pa.toString().equals(playerList.get(i).getPlayerName())) {
                playerList.remove(i);
            }
        }
    }

    public void callRewardMobKiller(EntityDeathEvent event){
        Player pa = event.getEntity().getKiller();
        Entity e = event.getEntity();
        Latch.Enchant.Player player = new Latch.Enchant.Player();
        if (pa != null && pa.hasPermission("enc.mk")) {
            MobKiller.rewardPlayerMoney(pa, e, econ);
        }
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public void loadConfigManager(){
        cfgm = new MobConfigManager();
        cfgm.setup();
    }

    public static List<Latch.Enchant.Player> getPlayerList(){
        return playerList;
    }
}
