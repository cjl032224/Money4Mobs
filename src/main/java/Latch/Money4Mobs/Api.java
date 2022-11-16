package Latch.Money4Mobs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Api {

    public static File getConfigFile(String fileName) {
        return new File(Money4Mobs.getPlugin(Money4Mobs.class).getDataFolder(), fileName + ".yml");
    }

    public static FileConfiguration getFileConfiguration(String fileName){
        return YamlConfiguration.loadConfiguration(getConfigFile(fileName));
    }

    public static FileConfiguration loadConfig(String fileName) {
        return YamlConfiguration.loadConfiguration(getConfigFile(fileName));
    }
}
