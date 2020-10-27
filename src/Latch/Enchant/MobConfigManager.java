package Latch.Enchant;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobConfigManager {

    private Enchant plugin = Enchant.getPlugin(Enchant.class);
    private static List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    public static FileConfiguration mobsCfg;
    public File mobsFile;

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        mobsFile = new File(plugin.getDataFolder(), "mobs.yml");
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
            for (int i = 0; i < mobList.size(); i++){
                String mobName = mobList.get(i).mobName;
                Integer worth = mobList.get(i).worth;
                mobsCfg.set("mobs." + mobName + ".worth", worth );
            }
            mobsCfg.save(mobsFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    public static void setMobListFromConfig(){
        for(String path : mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            int worth = mobsCfg.getInt("mobs." + path + ".worth");
            String mob = path;
            mobListFromConfig.add(new MobModel(mob,worth));
        }
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

}
