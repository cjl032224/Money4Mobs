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

public class UserManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration usersCfg;
    public static File usersFile;
    private static final Material[] m = Material.values();
    private static List<UserModel> userList = new ArrayList<>();

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        usersFile = new File(plugin.getDataFolder(), "users.yml");
        //if the mobs.yml does not exist, create it
        if(!usersFile.exists()){
            try {
                usersFile.createNewFile();
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the items.yml file");
            }
        }
        usersCfg = YamlConfiguration.loadConfiguration(usersFile);
    }

    public static void addUserToList(UserModel user) throws IOException {
        int i = 0;
        int counter = 0;
        int userCount = 1;
        for(String users : usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userId = usersCfg.getString("users.user-" + userCount + ".userId");
            if(user.userId.equalsIgnoreCase(userId)){
                counter = 1;
            }
            userCount++;
        }
        if (counter == 0){
            usersCfg.set("users.user-" + userCount + ".userName", user.userName);
            usersCfg.set("users.user-" + userCount + ".userId", user.userId);
            usersCfg.set("users.user-" + userCount + ".showMessage", user.showMessage);
            usersCfg.set("users.user-" + userCount + ".language", user.language);
        }
        usersCfg.save(usersFile);
    }

    public static List<UserModel> updateUsersOnReload() {
        int counter = 1;
        List<UserModel> um = new ArrayList<>();
        usersCfg = YamlConfiguration.loadConfiguration(usersFile);
        for(String users : usersCfg.getConfigurationSection("users").getKeys(false)) {
            String userID = usersCfg.getString("users.user-" + counter + ".userId");
            String language = usersCfg.getString("users.user-" + counter + ".language");
            boolean showMessage = usersCfg.getBoolean("users.user-" + counter + ".showMessage");
            String userName = usersCfg.getString("users.user-" + counter + ".userName");
            if (usersCfg.getString("users.user-" + counter + ".userName") != null){
                String ipAddress = usersCfg.getString("users.user-" + counter + ".ipAddress");
                um.add(new UserModel(userName, userID, showMessage, language, ipAddress));
            }
            else {
                um.add(new UserModel(userName, userID, showMessage, language, null));
            }
            counter++;
        }
        return um;
    }
    public void createUsersConfig(){
        try {
            usersFile.createNewFile();
            String defaultLanguage;
            if (MobConfigManager.mobsCfg.getString("defaultLanguage") == null) {
                defaultLanguage = "english";
            } else {
                defaultLanguage = ConfigFileManager.configCfg.getString("defaultLanguage");
            }
            for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                userList.add(new UserModel(p.getName(), p.getUniqueId().toString(), true, defaultLanguage, null));
            }
            int i = 0;
            for (UserModel user : userList){
                i++;
                usersCfg.set("users.user-" + i + ".userName", user.userName);
                usersCfg.set("users.user-" + i + ".userId", user.userId);
                usersCfg.set("users.user-" + i + ".showMessage", user.showMessage);
                usersCfg.set("users.user-" + i + ".language", user.language);
            }
            usersCfg.save(usersFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    public static void updateUserDefaultLanguage(String language) throws IOException {
        int i = 1;
        for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            usersCfg.set("users.user-" + i + ".language",language);
            i++;
        }
        usersCfg.save(usersFile);
    }


}
