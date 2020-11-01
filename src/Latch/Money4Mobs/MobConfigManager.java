package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobConfigManager {

    private Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    private static List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    public static FileConfiguration mobsCfg;
    public File mobsFile;

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        mobsFile = new File(plugin.getDataFolder(), "mobs.yml");
        //if the mobs.yml does not exist, create it
        if(!mobsFile.exists()){
            try {
                mobsFile.createNewFile();
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
            }
        }
        mobsCfg = YamlConfiguration.loadConfiguration(mobsFile);
    }

    public void createMobsConfig(){
        SetMobList sml = new SetMobList();
        List<MobModel> mobList = sml.getMobModel();
        try {
            mobsFile.createNewFile();
            mobsCfg.set("version", "1.0.4");
            mobsCfg.set("spawners", false);
            mobsCfg.set("spawneggs", false);
            for (int i = 0; i < mobList.size(); i++){
                String mobName = mobList.get(i).mobName;
                Integer lowWorth = mobList.get(i).lowWorth;
                Integer highWorth = mobList.get(i).highWorth;
                mobsCfg.set("mobs." + mobName + ".worth.low", lowWorth);
                mobsCfg.set("mobs." + mobName + ".worth.high", highWorth);
            }
            mobsCfg.save(mobsFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    // Sets mob list from mobs.yml file
    public static void setMobListFromConfig(){
        for(String path : mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            int lowWorth = mobsCfg.getInt("mobs." + path + ".worth.low");
            int highWorth = mobsCfg.getInt("mobs." + path + ".worth.high");
            String mob = path;
            mobListFromConfig.add(new MobModel(mob,lowWorth, highWorth));
        }
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

}
