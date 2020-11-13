package Latch.Money4Mobs;

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
            usersCfg.set("users.user-" + userCount + ".language", user.language);
        }
        usersCfg.save(usersFile);
    }

    public void createUsersConfig(){
        try {
            usersFile.createNewFile();
            for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                userList.add(new UserModel(p.getName(), p.getUniqueId().toString(),"English"));
            }
            int i = 0;
            for (UserModel user : userList){
                i++;
                usersCfg.set("users.user-" + i + ".userName", user.userName);
                usersCfg.set("users.user-" + i + ".userId", user.userId);
                //usersCfg.set("users.user-" + i + ".showMessage", user.showMessage);
                usersCfg.set("users.user-" + i + ".language", user.language);
            }
            usersCfg.save(usersFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    public static List<UserModel> getUserList(){
        return userList;
    }

}
