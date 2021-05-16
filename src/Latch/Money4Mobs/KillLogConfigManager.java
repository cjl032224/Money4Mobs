package Latch.Money4Mobs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KillLogConfigManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    //private static final List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    public static FileConfiguration KillLogCfg;
    public static File KillLogFile;
    private static List<UserModel> userList = new ArrayList<>();

    // Set up mobs.yml configuration file
    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        KillLogFile = new File(plugin.getDataFolder(), "killLog.yml");
        KillLogCfg = YamlConfiguration.loadConfiguration(KillLogFile);
        //if the mobs.yml does not exist, create it
        if(!KillLogFile.exists()){
            try {
                KillLogFile.createNewFile();
                createKillLogConfig(KillLogCfg);
                KillLogCfg.save(KillLogFile);
            }
            catch(IOException e){
                System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
            }
        } else {
            if (KillLogCfg.getString("defaultLanguage") == null) {
                KillLogCfg.set("defaultLanguage", "English");
            }
            KillLogCfg.save(KillLogFile);
        }
    }

    public void updateKillLogIP(){

    }

    public void createKillLogConfig(FileConfiguration mobsCfg){
        SetMobList sml = new SetMobList();
        List<MobModel> mobList = sml.getMobModel();
        try {
            KillLogFile.createNewFile();
            for(OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                userList.add(new UserModel(p.getName(), p.getUniqueId().toString(), true, null, null));
            }
            int i = 0;
            for (UserModel user : userList){
                i++;
                KillLogCfg.set("users.user-" + i + ".userName", user.userName);
                KillLogCfg.set("users.user-" + i + ".userId", user.userId);
                KillLogCfg.set("users.user-" + i + ".ipAddress", user.ipAddress);
            }
            mobsCfg.save(KillLogFile);
        }
        catch(IOException e){
            System.out.println(ChatColor.RED + "Could not create the killLog.yml file");
        }
    }
}
