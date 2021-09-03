package Latch.DutchmanMiniGames;

import Latch.DutchmanMiniGames.Managers.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public class DutchmanMiniGames extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static ItemListManager ItemListConfig;
    private static UserManager UserCfgm;
    private static ConfigFileManager ConfigCfgm;
    private static File userFile = UserManager.usersFile;

    @Override
    public void onEnable() {

        loadUserConfigManager();
        try {
            loadConfigFileManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadItemConfigManager();
        getServer().getPluginManager().registerEvents(this, this);
        setupEconomy();
        ItemListConfig.createItemsConfig();
        UserCfgm.createUsersConfig();

        Objects.requireNonNull(this.getCommand("tickets")).setExecutor(new DutchmanCommand());
        Objects.requireNonNull(this.getCommand("tickets")).setTabCompleter(new DutchmanTabComplete());
        Objects.requireNonNull(this.getCommand("dutchman")).setExecutor(new DutchmanCommand());

    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent e) throws IOException {
        loadUserConfigManager();
        userFile = UserManager.usersFile;
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        try{
            int blockX = block.getX();
            int blockY = block.getY();
            int blockZ = block.getZ();

            for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
                int blockConfigX = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".xLocation");
                int blockConfigY = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".yLocation");
                int blockConfigZ = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".zLocation");

                EquipmentSlot slot = e.getHand(); //Get the hand of the event and set it to 'e'.
                if (slot.equals(EquipmentSlot.HAND)) { //If the event is fired by HAND (main hand)
                    //Do stuff
                    isTicketBlockValid(block, blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ);
                }
            }

            for(String teleportBlock : ConfigFileManager.configCfg.getConfigurationSection("teleportBlocks").getKeys(false)) {
                int blockConfigX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".xLocation");
                int blockConfigY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".yLocation");
                int blockConfigZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".zLocation");
                if (Boolean.TRUE.equals(isTeleportBlockValid(block, blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ))){
                    int teleportToX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.xLocation");
                    int teleportToY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.yLocation");
                    int teleportToZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.zLocation");
                    String teleportToWorld = ConfigFileManager.configCfg.getString("teleportBlocks." + teleportBlock + ".teleportTo.world");
                    assert teleportToWorld != null;
                    Location location = new Location(Bukkit.getWorld(teleportToWorld), teleportToX, teleportToY, teleportToZ);
                    String playerId = player.getUniqueId().toString();
                    int playerTickets = UserManager.usersCfg.getInt("users." + playerId + ".tickets");
                    int miniGameCost = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".ticketCost");
                    if (playerTickets >= miniGameCost){
                        UserManager.usersCfg.set("users." + playerId + ".tickets", playerTickets - miniGameCost);
                        player.teleport(location);
                        UserManager.usersCfg.save(userFile);
                    } else {
                        player.sendMessage(ChatColor.GRAY + "Not enough tickets. The mini game cost is " + ChatColor.GOLD + miniGameCost + ChatColor.GRAY  + " tickets. You have " + ChatColor.GOLD + playerTickets + ChatColor.GRAY + " tickets" );
                    }
                }
            }

            if (Boolean.TRUE.equals(ConfigFileManager.configCfg.getBoolean("logBlockClickedInformation"))) {
                if (e.getAction() != Action.LEFT_CLICK_BLOCK) {
                    return;
                }
                player.sendMessage(ChatColor.GRAY + "Block World: " + ChatColor.GOLD + block.getWorld().getName());
                player.sendMessage(ChatColor.GRAY + "Block Location: " + ChatColor.GREEN + "X" + ChatColor.GRAY + "=" + ChatColor.GOLD + block.getX() + ChatColor.GRAY + " | " +
                        ChatColor.GREEN + "Y" + ChatColor.GRAY + "=" + ChatColor.GOLD + block.getY() + ChatColor.GRAY + " | " + ChatColor.GREEN + "Z" + ChatColor.GRAY + "=" + ChatColor.GOLD + block.getZ());
                player.sendMessage(ChatColor.GRAY + "Block Type: " + ChatColor.GOLD + block.getType());
            }
        } catch (NullPointerException ignored){}
    }

    private static boolean isTicketBlockValid(Block block, int blockX, int blockY, int blockZ, int blockConfigX, int blockConfigY, int blockConfigZ) {
        return Boolean.TRUE.equals(isTicketBlock(block)) && Boolean.TRUE.equals(isTicketWorld(block)) && Boolean.TRUE.equals(isTicketBlockLocationValid(blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ));
    }

    private static boolean isTeleportBlockValid(Block block, int blockX, int blockY, int blockZ, int blockConfigX, int blockConfigY, int blockConfigZ) {
        return Boolean.TRUE.equals(isTeleportBlock(block)) && Boolean.TRUE.equals(isTeleportWorld(block)) && Boolean.TRUE.equals(isTeleportLocationValid(blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ));
    }


    @EventHandler
    public void protectTicketBlock(BlockBreakEvent e) {
        for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
            int blockConfigX = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".xLocation");
            int blockConfigY = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".yLocation");
            int blockConfigZ = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".zLocation");
            if (Boolean.TRUE.equals(isTicketBlockValid(e.getBlock(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), blockConfigX, blockConfigY, blockConfigZ))) {
                e.setCancelled(true);
            }
        }
        for(String teleportBlock : ConfigFileManager.configCfg.getConfigurationSection("teleportBlocks").getKeys(false)) {
            int blockConfigX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".xLocation");
            int blockConfigY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".yLocation");
            int blockConfigZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".zLocation");
            if (Boolean.TRUE.equals(isTeleportBlockValid(e.getBlock(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), blockConfigX, blockConfigY, blockConfigZ))) {
                e.setCancelled(true);
            }
        }
    }


    public static Boolean isTicketWorld(Block b){
        boolean isValid = false;
        for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
            if (b.getWorld().getName().equals(ConfigFileManager.configCfg.getString("ticketBlocks." + ticketBlock + ".world"))){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isTicketBlock(Block b){
        boolean isValid = false;
        for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
            if(b.getType().toString().equals(ConfigFileManager.configCfg.getString("ticketBlocks." + ticketBlock + ".type"))){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isTicketBlockLocationValid(int blockX, int blockY, int blockZ, int blockConfigX, int blockConfigY, int blockConfigZ){
        return blockX == blockConfigX && blockY == blockConfigY && blockZ == blockConfigZ;
    }

    public static Boolean isTeleportWorld(Block b){
        boolean isValid = false;
        for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
            if (b.getWorld().getName().equals(ConfigFileManager.configCfg.getString("ticketBlocks." + ticketBlock + ".world"))){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isTeleportBlock(Block b){
        boolean isValid = false;
        for(String teleportBlock : ConfigFileManager.configCfg.getConfigurationSection("teleportBlocks").getKeys(false)) {
            if(b.getType().toString().equals(ConfigFileManager.configCfg.getString("teleportBlocks." + teleportBlock + ".type"))){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isTeleportLocationValid(int blockX,int blockY, int blockZ, int blockConfigX, int blockConfigY, int blockConfigZ){
        return blockX == blockConfigX && blockY == blockConfigY && blockZ == blockConfigZ;
    }



    @EventHandler
    public void onLogin(PlayerJoinEvent event) throws IOException {
        if (!UserManager.usersCfg.isSet("users." + event.getPlayer().getUniqueId())){
            String playerId = event.getPlayer().getUniqueId().toString();
            UserManager.usersCfg.set("users." + playerId + ".userName", event.getPlayer().getName());
            UserManager.usersCfg.set("users." + playerId + ".userId", event.getPlayer().getUniqueId().toString());
            UserManager.usersCfg.set("users." + playerId + ".tickets", 0);
            UserManager.usersCfg.save(userFile);
        }
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

    public static void loadItemConfigManager() {
        ItemListConfig = new ItemListManager();
        ItemListConfig.setup();
    }

    static void loadUserConfigManager() {
        UserCfgm = new UserManager();
        UserCfgm.setup();
    }


    static void loadConfigFileManager() throws IOException {
        ConfigCfgm = new ConfigFileManager();
        ConfigCfgm.setup();
    }

    public static void reloadConfigFiles() throws IOException {
        loadItemConfigManager();
        loadUserConfigManager();
        loadConfigFileManager();
    }
}
