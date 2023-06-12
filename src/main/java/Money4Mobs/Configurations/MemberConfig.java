package Money4Mobs.Configurations;

import Money4Mobs.Api.Api;
import Money4Mobs.Constants;
import Money4Mobs.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class MemberConfig {

    private final Main plugin = Main.getPlugin(Main.class);
    public static FileConfiguration membersCfg;
    public static File membersFile;
    private static final Material[] m = Material.values();

    // Set up mobs.yml configuration file
    public void setup(){
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        FileConfiguration pluginConfigCfg;
        File pluginConfigFile;
        // If the plugin folder does not exist, create the plugin folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        pluginConfigFile = new File(plugin.getDataFolder(),  Constants.PLUGIN_MEMBERS_FILE_NAME + ".yml");
        pluginConfigCfg = YamlConfiguration.loadConfiguration(pluginConfigFile);
        //if the config.yml does not exist, create it
        if(!pluginConfigFile.exists()){
            try {
                pluginConfigCfg.save(pluginConfigFile);
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the " + Constants.PLUGIN_MEMBERS_FILE_NAME + ".yml file");
            }
        }
    }

    public static void addMemberToCfg(Player player) throws IOException {
        FileConfiguration membersCfg = Api.loadConfig(Constants.PLUGIN_MEMBERS_FILE_NAME);
        FileConfiguration configCfg = Api.loadConfig(Constants.PLUGIN_CONFIG_FILE_NAME);
        membersCfg.set(Constants.PLAYERS_YML_CONSTANT + player.getUniqueId().toString() + ".minecraftName", player.getName());
        membersCfg.set(Constants.PLAYERS_YML_CONSTANT + player.getUniqueId().toString() + ".minecraftId", player.getUniqueId().toString());
        membersCfg.set(Constants.PLAYERS_YML_CONSTANT + player.getUniqueId().toString() + ".showMessage", true);
        String defaultLanguage = "english";
        if (configCfg.getString("defaultLanguage") != null){
            defaultLanguage = configCfg.getString("defaultLanguage");
        }
        membersCfg.set(Constants.PLAYERS_YML_CONSTANT + player.getUniqueId().toString() + ".language", defaultLanguage);
        membersCfg.save(Api.getConfigFile(Constants.PLUGIN_MEMBERS_FILE_NAME));
    }

    public void createMembersConfig(){
        try {
            membersCfg.save(membersFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the members.yml file");
        }
    }

    public static void updateUserDefaultLanguage(String language) throws IOException {
        FileConfiguration membersCfg = Api.loadConfig(Constants.PLUGIN_MEMBERS_FILE_NAME);
        for(String users : Objects.requireNonNull(MemberConfig.membersCfg.getConfigurationSection("players")).getKeys(false)) {
            membersCfg.set(Constants.PLAYERS_YML_CONSTANT + users + ".language",language);
        }
        membersCfg.save(Api.getConfigFile(Constants.PLUGIN_MEMBERS_FILE_NAME));
    }


}
