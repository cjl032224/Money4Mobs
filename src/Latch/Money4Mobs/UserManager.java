package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserManager {

    private Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration usersCfg;
    public File usersFile;
    private static final Material[] m = Material.values();

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

    public void createItemsConfig(){
        try {
            usersFile.createNewFile();
            //List<UserModel> um = Money4Mobs.getUserList();
            int i = 0;
//            for (UserModel user : um){
//                usersCfg.set("users.userName", user.userName);
//                usersCfg.set("users.userId", user.userId);
//                usersCfg.set("users.showMessage", user.showMessage);
//                usersCfg.set("users.language", user.language);
//                i++;
//            }
            usersCfg.save(usersFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

}
