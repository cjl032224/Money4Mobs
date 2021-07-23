package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Managers.MobConfigManager;
import Latch.Money4Mobs.Money4Mobs;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ItemListManager {

    private Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration itemsCfg;
    public File itemsFile;
    private static final Material[] m = Material.values();

    public static FileConfiguration configCfg;
    public static File configFile;

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        configCfg = YamlConfiguration.loadConfiguration(configFile);
        boolean createItemFile = false;
        if (!configCfg.getString("version").equals(ConfigFileManager.VERSION_NUMBER)) {
            createItemFile = true;
        }
        itemsFile = new File(plugin.getDataFolder(), "items.yml");
        //if the mobs.yml does not exist, create it
        if(!itemsFile.exists() || Boolean.TRUE.equals(createItemFile)){
            try {
                itemsFile.createNewFile();
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the items.yml file");
            }
        }
        itemsCfg = YamlConfiguration.loadConfiguration(itemsFile);
    }

    public void createItemsConfig(){
        try {
            itemsFile.createNewFile();
            for (int i = 0; i < m.length; i++){
                if (!m[i].toString().contains("CANDLE_CAKE")){
                    itemsCfg.set("items.name." + i, m[i].toString());
                }
            }
            itemsCfg.save(itemsFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the items.yml file");
        }
    }

}
