package Latch.DutchmanMiniGames;

import Latch.DutchmanMiniGames.Managers.*;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.logging.Logger;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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

        if (e.getAction().equals(Action.PHYSICAL)){
            if (isPressurePlate(e.getClickedBlock()) != null) {
                World world =  Bukkit.getWorld(ConfigFileManager.configCfg.getString("doorOpenBlocks." + isPressurePlate(e.getClickedBlock()) + ".door.world"));
                int x = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + isPressurePlate(e.getClickedBlock()) + ".door.xLocation");
                int y = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + isPressurePlate(e.getClickedBlock()) + ".door.yLocation");
                int z = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + isPressurePlate(e.getClickedBlock()) + ".door.zLocation");
                Location location = new Location(world, x,y,z);
                Block b = location.getBlock();
                if (Boolean.FALSE.equals(isDoorOpen(b))){
                    e.setCancelled(true);
                }

            }
        }
        loadUserConfigManager();
        userFile = UserManager.usersFile;
        Player player = e.getPlayer();
        Block block = e.getClickedBlock();
        try{
            int blockX = block.getX();
            int blockY = block.getY();
            int blockZ = block.getZ();
            String playerId = player.getUniqueId().toString();
            int playerTickets = UserManager.usersCfg.getInt("users." + playerId + ".tickets");
            double playerBalance = econ.getBalance(player);
            for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
                int blockConfigX = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".xLocation");
                int blockConfigY = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".yLocation");
                int blockConfigZ = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".zLocation");

                EquipmentSlot slot = e.getHand(); //Get the hand of the event and set it to 'e'.
                assert slot != null;
                if (slot.equals(EquipmentSlot.HAND) && Boolean.TRUE.equals(isTicketBlockValid(block, blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ))) { //If the event is fired by HAND (main hand)
                    //Do stuff
                    int ticketCost = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".cost");
                    int tickets = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".tickets");
                    String currencySymbol = ConfigFileManager.configCfg.getString("currencySymbol");
                    if (playerBalance >= ticketCost){
                        econ.withdrawPlayer(player, ticketCost);
                        UserManager.usersCfg.set("users." + playerId + ".tickets", playerTickets + tickets);
                        UserManager.usersCfg.save(userFile);
                        loadUserConfigManager();
                        int ticketsAfter = UserManager.usersCfg.getInt("users." + playerId + ".tickets");
                        DecimalFormat df = new DecimalFormat("####0.00");
                        player.sendMessage(ChatColor.GREEN + "You bought " + ChatColor.GOLD + tickets + ChatColor.GREEN + " tickets for " + ChatColor.GOLD + currencySymbol + ticketCost);
                        player.sendMessage(ChatColor.GREEN + "You now have " + ChatColor.GOLD + currencySymbol + df.format(econ.getBalance(player)) + ChatColor.GREEN + " and " + ChatColor.GOLD + ticketsAfter + ChatColor.GREEN + " tickets");

                    } else {
                        player.sendMessage(ChatColor.RED + "You do not have enough money to buy tickets");
                    }
                }
                if (Boolean.TRUE.equals(isDoorValid(block))){
                    e.setCancelled(true);
                }
            }

//            for(String teleportBlock : ConfigFileManager.configCfg.getConfigurationSection("teleportBlocks").getKeys(false)) {
//                int blockConfigX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".xLocation");
//                int blockConfigY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".yLocation");
//                int blockConfigZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".zLocation");
//                if (Boolean.TRUE.equals(isTeleportBlockValid(block, blockX, blockY, blockZ, blockConfigX, blockConfigY, blockConfigZ))){
//                    int teleportToX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.xLocation");
//                    int teleportToY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.yLocation");
//                    int teleportToZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".teleportTo.zLocation");
//                    String teleportToWorld = ConfigFileManager.configCfg.getString("teleportBlocks." + teleportBlock + ".teleportTo.world");
//                    assert teleportToWorld != null;
//                    Location location = new Location(Bukkit.getWorld(teleportToWorld), teleportToX, teleportToY, teleportToZ);
//                    String playerId = player.getUniqueId().toString();
//                    int playerTickets = UserManager.usersCfg.getInt("users." + playerId + ".tickets");
//                    int miniGameCost = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".ticketCost");
//                    if (playerTickets >= miniGameCost){
//                        UserManager.usersCfg.set("users." + playerId + ".tickets", playerTickets - miniGameCost);
//                        player.teleport(location);
//                        UserManager.usersCfg.save(userFile);
//                    } else {
//                        player.sendMessage(ChatColor.GRAY + "Not enough tickets. The mini game cost is " + ChatColor.GOLD + miniGameCost + ChatColor.GRAY  + " tickets. You have " + ChatColor.GOLD + playerTickets + ChatColor.GRAY + " tickets" );
//                    }
//                }
//            }

            if (Boolean.TRUE.equals(isDoorOpenBlockValid(block))){
                openDoor(block, e);
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
    public static String isPressurePlate(Block block){
        int counter = 0;
        String doorBlock = null;
        for(String doorOpenBlock : ConfigFileManager.configCfg.getConfigurationSection("doorOpenBlocks").getKeys(false)) {

            String doorBlockWorld = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".door.pressurePlate.world");
            if (block.getWorld().getName().equals(doorBlockWorld)){
                counter++;
            }

            int doorBlockX = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.pressurePlate.xLocation");
            int doorBlockY = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.pressurePlate.yLocation");
            int doorBlockZ = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.pressurePlate.zLocation");
//            System.out.println("x: " + doorBlockX);
//            System.out.println("x: " + block.getX());
//            System.out.println("y: " + doorBlockY);
//            System.out.println("y: " + block.getY());
//            System.out.println("yTop: " + topDoorY);
//            System.out.println("z: " + doorBlockZ);
//            System.out.println("z: " + block.getZ());


            if (block.getX() == doorBlockX && block.getY() == doorBlockY && block.getZ() == doorBlockZ){
                counter++;
            }
            if (block.getType().toString().contains("PRESSURE_PLATE")){
                counter++;
            }
            if (counter == 3){
                doorBlock = doorOpenBlock;
            }
        }
        return doorBlock;
    }

    public static boolean isDoorOpen(Block b){
        Door door = (Door) b.getBlockData();
        return door.isOpen();
    }

    public static void openDoor(Block b, PlayerInteractEvent e) throws IOException {
        boolean isValid = false;
        int counter = 0;
        String doorOpenString = "";
        for(String doorOpenBlock : ConfigFileManager.configCfg.getConfigurationSection("doorOpenBlocks").getKeys(false)) {
            String doorBlockWorld = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".world");
            if (b.getWorld().getName().equals(doorBlockWorld)){
                counter++;
            }

            int doorBlockX = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".xLocation");
            int doorBlockY = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".yLocation");
            int doorBlockZ = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".zLocation");

            if (b.getX() == doorBlockX && b.getY() == doorBlockY && b.getZ() == doorBlockZ){
                counter++;
            }
            String doorBlockType = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".type");
            if (b.getType().toString().equals(doorBlockType)){
                counter++;
            }
            if (counter == 3){
                isValid = true;
                doorOpenString = doorOpenBlock;
            }
        }
        if (Boolean.TRUE.equals(isValid)){
            int doorOpenX = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenString + ".door.xLocation");
            int doorOpenY = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenString + ".door.yLocation");
            int doorOpenZ = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenString + ".door.zLocation");
            String doorWorld = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenString + ".door.world");
            assert doorWorld != null;

            EquipmentSlot slot = e.getHand(); //Get the hand of the event and set it to 'e'.
            if (slot.equals(EquipmentSlot.HAND)) { //If the event is fired by HAND (main hand)
                //Do stuff
                Location location = new Location(Bukkit.getWorld(doorWorld), doorOpenX, doorOpenY, doorOpenZ);
                Block doorBlock = location.getBlock();
                if (doorBlock.getType().name().contains("DOOR")){
                    int ticketsToOpenDoor = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenString + ".ticketCost");
                    String playerId = e.getPlayer().getUniqueId().toString();
                    int playerTickets = UserManager.usersCfg.getInt("users." + playerId + ".tickets");
                    if (playerTickets >= ticketsToOpenDoor){
                        UserManager.usersCfg.set("users." + playerId + ".tickets", playerTickets - ticketsToOpenDoor);
                        Door door = (Door) doorBlock.getBlockData();
                        door.setOpen(true);
                        doorBlock.setBlockData(door);
                        UserManager.usersCfg.save(userFile);
                    } else {
                        e.getPlayer().sendMessage(ChatColor.GRAY + "Not enough tickets. The mini game cost is " + ChatColor.GOLD + ticketsToOpenDoor + ChatColor.GRAY  + " tickets. You have " + ChatColor.GOLD + playerTickets + ChatColor.GRAY + " tickets" );
                    }

                }
            }
        }
    }


    public static Boolean isDoorOpenBlockValid(Block block){
        boolean isValid = false;
        int counter = 0;
        for(String doorOpenBlock : ConfigFileManager.configCfg.getConfigurationSection("doorOpenBlocks").getKeys(false)) {

            String doorBlockWorld = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".world");
            if (block.getWorld().getName().equals(doorBlockWorld)){
                counter++;
            }

            int doorBlockX = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".xLocation");
            int doorBlockY = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".yLocation");
            int doorBlockZ = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".zLocation");

            if (block.getX() == doorBlockX && block.getY() == doorBlockY && block.getZ() == doorBlockZ){
                counter++;
            }
            String doorBlockType = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".type");
            if (block.getType().toString().equals(doorBlockType)){
                counter++;
            }
            if (counter == 3){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isDoorValid(Block block){
        boolean isValid = false;
        int counter = 0;
        for(String doorOpenBlock : ConfigFileManager.configCfg.getConfigurationSection("doorOpenBlocks").getKeys(false)) {

            String doorBlockWorld = ConfigFileManager.configCfg.getString("doorOpenBlocks." + doorOpenBlock + ".door.world");
            if (block.getWorld().getName().equals(doorBlockWorld)){
                counter++;
            }

            int doorBlockX = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.xLocation");
            int doorBlockY = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.yLocation");
            int doorBlockZ = ConfigFileManager.configCfg.getInt("doorOpenBlocks." + doorOpenBlock + ".door.zLocation");
            int topDoorY = doorBlockY + 1;
//            System.out.println("x: " + doorBlockX);
//            System.out.println("x: " + block.getX());
//            System.out.println("y: " + doorBlockY);
//            System.out.println("y: " + block.getY());
//            System.out.println("yTop: " + topDoorY);
//            System.out.println("z: " + doorBlockZ);
//            System.out.println("z: " + block.getZ());


            if (block.getX() == doorBlockX && (block.getY() == doorBlockY || block.getY() == topDoorY) && block.getZ() == doorBlockZ){
                counter++;
            }
            if (block.getType().toString().contains("DOOR")){
                counter++;
            }
            if (counter == 3){
                isValid = true;
            }
        }
        return isValid;
    }

    public static Boolean isProtectedBlock(Block block){
        int counter = 0;
        boolean isValid = false;
        for(String protectedBlock : ConfigFileManager.configCfg.getConfigurationSection("protectedBlocks").getKeys(false)) {

            String protectedBlockWorld = ConfigFileManager.configCfg.getString("protectedBlocks." + protectedBlock + ".world");
            if (block.getWorld().getName().equals(protectedBlockWorld)){
                counter++;
            }

            int doorBlockX = ConfigFileManager.configCfg.getInt("protectedBlocks." + protectedBlock + ".xLocation");
            int doorBlockY = ConfigFileManager.configCfg.getInt("protectedBlocks." + protectedBlock + ".yLocation");
            int doorBlockZ = ConfigFileManager.configCfg.getInt("protectedBlocks." + protectedBlock + ".zLocation");
//            System.out.println("x: " + doorBlockX);
//            System.out.println("x: " + block.getX());
//            System.out.println("y: " + doorBlockY);
//            System.out.println("y: " + block.getY());
//x            System.out.println("z: " + doorBlockZ);
//            System.out.println("z: " + block.getZ());

            if (block.getX() == doorBlockX && block.getY() == doorBlockY && block.getZ() == doorBlockZ){
                counter++;
            }
            if (block.getType().toString().equals(ConfigFileManager.configCfg.getString("protectedBlocks." + protectedBlock + ".type"))){
                counter++;
            }
            if (counter == 3){
                isValid = true;
            }
        }
        return isValid;
    }

    @EventHandler
    public void protectTicketBlock(BlockBreakEvent e) {
        int blockX = e.getBlock().getX();
        int blockY = e.getBlock().getY();
        int blockZ = e.getBlock().getY();
        if (Boolean.TRUE.equals(isProtectedBlock(e.getBlock()))){
            e.setCancelled(true);
        }
        if (isPressurePlate(e.getBlock()) != null) {
            e.setCancelled(true);
        }
        for(String ticketBlock : ConfigFileManager.configCfg.getConfigurationSection("ticketBlocks").getKeys(false)) {
            int blockConfigX = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".xLocation");
            int blockConfigY = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".yLocation");
            int blockConfigZ = ConfigFileManager.configCfg.getInt("ticketBlocks." + ticketBlock + ".zLocation");
            if (Boolean.TRUE.equals(isTicketBlockValid(e.getBlock(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), blockConfigX, blockConfigY, blockConfigZ))) {
                e.setCancelled(true);
            }
        }
//        for(String teleportBlock : ConfigFileManager.configCfg.getConfigurationSection("teleportBlocks").getKeys(false)) {
//            int blockConfigX = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".xLocation");
//            int blockConfigY = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".yLocation");
//            int blockConfigZ = ConfigFileManager.configCfg.getInt("teleportBlocks." + teleportBlock + ".zLocation");
//            if (Boolean.TRUE.equals(isTeleportBlockValid(e.getBlock(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ(), blockConfigX, blockConfigY, blockConfigZ))) {
//                e.setCancelled(true);
//            }
//        }

        if (Boolean.TRUE.equals(isDoorOpenBlockValid(e.getBlock()))){
            e.setCancelled(true);
        }

        if (Boolean.TRUE.equals(isDoorValid(e.getBlock()))){

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
