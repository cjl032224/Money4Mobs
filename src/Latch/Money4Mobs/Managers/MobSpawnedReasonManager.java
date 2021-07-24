package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Money4Mobs;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MobSpawnedReasonManager {

    private Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration mobReasonsCfg;
    public static File mobReasonsFile;


    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        mobReasonsFile = new File(plugin.getDataFolder(), "mobSpawnedReasons.yml");
        mobReasonsCfg = YamlConfiguration.loadConfiguration(mobReasonsFile);
        boolean createItemFile = false;

        //if the mobs.yml does not exist, create it
        if(!mobReasonsFile.exists() || Boolean.TRUE.equals(createItemFile)){
            try {
                mobReasonsFile.createNewFile();
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the mobSpawnedReasons.yml file");
            }
        }
        mobReasonsCfg = YamlConfiguration.loadConfiguration(mobReasonsFile);
    }

    public void createMobReasonsConfig(){
        try {
            mobReasonsFile.createNewFile();
            mobReasonsCfg.save(mobReasonsFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the items.yml file");
        }
    }

}
