package Latch.DutchmanMiniGames.Managers;

import Latch.DutchmanMiniGames.DutchmanMiniGames;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ConfigFileManager {

    private static final DutchmanMiniGames plugin = DutchmanMiniGames.getPlugin(DutchmanMiniGames.class);
    public static FileConfiguration configCfg;
    public static File configFile;
    public static final String VERSION_NUMBER = "0.0.1";
    private static final String VERSION = "version";

    // Set up mobs.yml configuration file
    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        configCfg = YamlConfiguration.loadConfiguration(configFile);

        //if the mobs.yml does not exist, create it
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                configCfg.set("version", VERSION_NUMBER);
                configCfg.set("logBlockClickedInformation", true);
                configCfg.set("ticketBlocks.ticketBlock-1.name", "Ticket Block #1");
                configCfg.set("ticketBlocks.ticketBlock-1.type", "DIAMOND_BLOCK");
                configCfg.set("ticketBlocks.ticketBlock-1.world", "world");
                configCfg.set("ticketBlocks.ticketBlock-1.xLocation", 0);
                configCfg.set("ticketBlocks.ticketBlock-1.yLocation", 0);
                configCfg.set("ticketBlocks.ticketBlock-1.zLocation", 0);
                configCfg.save(configFile);
            } catch (IOException e) {
                System.out.println(ChatColor.RED + "Could not create the config.yml file");
            }
        } else {
            if (!configCfg.isSet(VERSION) || !Objects.equals(configCfg.getString(VERSION), VERSION_NUMBER)) {
                configCfg.set(VERSION, VERSION_NUMBER);
            }
        }

        configCfg.save(configFile);
    }

}
