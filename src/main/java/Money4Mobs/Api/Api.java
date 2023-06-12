package Money4Mobs.Api;

import Money4Mobs.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class Api {

    public static final Main plugin = getPlugin(Main.class);
    public static File getConfigFile(String fileName){
        return new File(plugin.getDataFolder(), fileName + ".yml");
    }

    public static FileConfiguration getFileConfiguration(File file){
        return YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration loadConfig(String fileName) {
        return YamlConfiguration.loadConfiguration(getConfigFile(fileName));
    }
}
