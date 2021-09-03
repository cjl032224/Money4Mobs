package Latch.DutchmanMiniGames.Managers;

import Latch.DutchmanMiniGames.DutchmanMiniGames;
import Latch.DutchmanMiniGames.UserModel;
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

    private final DutchmanMiniGames plugin = DutchmanMiniGames.getPlugin(DutchmanMiniGames.class);
    public static FileConfiguration usersCfg;
    public static File usersFile;

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Dutchman folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        usersFile = new File(plugin.getDataFolder(), "users.yml");
        //if the mobs.yml does not exist, create it
        createUsersConfig();

        usersCfg = YamlConfiguration.loadConfiguration(usersFile);
    }

    public void createUsersConfig(){
        usersCfg = YamlConfiguration.loadConfiguration(usersFile);
        usersFile = new File(plugin.getDataFolder(), "users.yml");
        try {
            if(!usersFile.exists()){
                usersFile.createNewFile();
                for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                    String playerId = p.getUniqueId().toString();
                    usersCfg.set("users." + playerId + ".userName", p.getName());
                    usersCfg.set("users." + playerId + ".userId", p.getUniqueId().toString());
                    System.out.println("adasd");
                    usersCfg.set("users." + playerId + ".tickets", 0);
                    usersCfg.save(usersFile);
                }
            }
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }


}
