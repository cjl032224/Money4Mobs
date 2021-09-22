package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Money4Mobs;
import Latch.Money4Mobs.UserModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobLogConfigManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration mobLogCfg;
    public static File mobLogFile;
    private static List<UserModel> userList = new ArrayList<>();

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        mobLogFile = new File(plugin.getDataFolder(), "mobLogFile.yml");
        //if the mobs.yml does not exist, create it
        if(!mobLogFile.exists()){
            try {
                mobLogFile.createNewFile();
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the mobLogFile.yml file");
            }
        }
        mobLogCfg = YamlConfiguration.loadConfiguration(mobLogFile);
    }

    public static void addUserToList(UserModel user) throws IOException {
        int i = 0;
        int counter = 0;
        int userCount = 1;
        for(String users : mobLogCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = mobLogCfg.getString("users.user-" + userCount + ".userId");
            if(user.userId.equalsIgnoreCase(userId)){
                counter = 1;
            }
            userCount++;
        }
        if (counter == 0){
            mobLogCfg.set("users."+ user.userId + ".userName", user.userName);
        }
        mobLogCfg.save(mobLogFile);
    }

//    public static List<UserModel> updateUsersOnReload() {
//        int counter = 1;
//        List<UserModel> um = new ArrayList<>();
//        mobLogCfg = YamlConfiguration.loadConfiguration(mobLogFile);
//        if (mobLogCfg.isSet("users")){
//            for(String users : mobLogCfg.getConfigurationSection("users").getKeys(false)) {
//                String userID = mobLogCfg.getString("users.user-" + counter + ".userId");
//                String language = mobLogCfg.getString("users.user-" + counter + ".language");
//                boolean showMessage = mobLogCfg.getBoolean("users.user-" + counter + ".showMessage");
//                String userName = mobLogCfg.getString("users.user-" + counter + ".userName");
//                if (mobLogCfg.getString("users.user-" + counter + ".userName") != null){
//                    String ipAddress = mobLogCfg.getString("users.user-" + counter + ".ipAddress");
//                    um.add(new UserModel(userName, userID, showMessage, language, ipAddress));
//                }
//                else {
//                    um.add(new UserModel(userName, userID, showMessage, language, null));
//                }
//                counter++;
//            }
//        }
//        return um;
//    }
    public void createMobLogConfig(){
        try {
            mobLogFile.createNewFile();
            mobLogCfg.set("displayMaxRewardMessage",true);
            for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                userList.add(new UserModel(p.getName(), p.getUniqueId().toString(), true, null, null));
            }
            for(String mob : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                mobLogCfg.set("mobs." + mob + ".timer", 0);
                mobLogCfg.set("mobs." + mob + ".maxReward", 0);
                mobLogCfg.set("mobs." + mob + ".isTimerActive", false);
            }
            for (UserModel user : userList){
                mobLogCfg.set("users." + user.userId + ".userName", user.userName);
            }
            mobLogCfg.save(mobLogFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobLogFile.yml file");
        }
    }

}
