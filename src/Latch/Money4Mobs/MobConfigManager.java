package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobConfigManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    private static final List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    public static FileConfiguration mobsCfg;
    public static File mobsFile;

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
            mobsCfg.set("version", "1.2.14");
            mobsCfg.set("spawners", false);
            mobsCfg.set("spawneggs", false);
            mobsCfg.set("group-multiplier.level-1", 1);
            mobsCfg.set("group-multiplier.level-2", 1);
            mobsCfg.set("group-multiplier.level-3", 1);
            mobsCfg.set("group-multiplier.level-4", 1);
            mobsCfg.set("group-multiplier.level-5", 1);
            for (int i = 0; i < mobList.size(); i++){
                String mobName = mobList.get(i).mobName;
                Integer lowWorth = mobList.get(i).lowWorth;
                Integer highWorth = mobList.get(i).highWorth;
                mobsCfg.set("mobs." + mobName + ".worth.low", lowWorth);
                mobsCfg.set("mobs." + mobName + ".worth.high", highWorth);
                mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                mobsCfg.set("mobs." + mobName + ".customDrops", false);
                String air = "AIR";
                if(i == 1){
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.name", air);
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.amount", 5);
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.chance", 100);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.name", air);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.amount", 10);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.chance", 100);
                }
            }
            mobsCfg.save(mobsFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    // Sets mob list from mobs.yml file
    public static void setMobListFromConfig(){
        Integer counter1 = 0;
        for(String path : Objects.requireNonNull(mobsCfg.getConfigurationSection("mobs")).getKeys(false)) {
            int lowWorth = mobsCfg.getInt("mobs." + path + ".worth.low");
            int highWorth = mobsCfg.getInt("mobs." + path + ".worth.high");
            Boolean customDrops = mobsCfg.getBoolean("mobs." + path + ".customDrops");
            Boolean keepDefaultDrops = mobsCfg.getBoolean("mobs." + path + ".keepDefaultDrops");
            Integer counter = 1;
            List<ItemModel> im = new ArrayList<ItemModel>();
            if(mobsCfg.getString("mobs." + path + ".drops.item-1.name") != null){
                for(String items : mobsCfg.getConfigurationSection("mobs." + path + ".drops").getKeys(false)) {
                    String name = mobsCfg.getString("mobs." + path + ".drops.item-" + counter + ".name");
                    Integer amount = mobsCfg.getInt("mobs." + path + ".drops.item-" + counter + ".amount");
                    Integer chance = mobsCfg.getInt("mobs." + path + ".drops.item-" + counter + ".chance");
                    im.add(new ItemModel(name,amount,chance));
                    counter++;
                }
            }
            mobListFromConfig.add(new MobModel(path,lowWorth, highWorth, keepDefaultDrops, customDrops, im));
            mobListFromConfig.get(counter1).setCustomDrops(customDrops);
            mobListFromConfig.get(counter1).setKeepDefaultDrops(keepDefaultDrops);
            mobListFromConfig.get(counter1).setItems(im);
            counter1++;
        }
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

}
