package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Money4Mobs;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigFileManager {

    private static final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration configCfg;
    public static File configFile;
    public static FileConfiguration mobsCfg;
    public static File mobsFile;
    public static final String VERSION_NUMBER = "1.6.4";
    private static final String VERSION = "version";

    // Set up mobs.yml configuration file
    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        configCfg = YamlConfiguration.loadConfiguration(configFile);
        mobsFile = new File(plugin.getDataFolder(), "mobs.yml");
        mobsCfg = YamlConfiguration.loadConfiguration(mobsFile);
        //if the mobs.yml does not exist, create it
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                copyInfoFromMobsConfig();
                configCfg.save(configFile);
            } catch (IOException e) {
                System.out.println(ChatColor.RED + "Could not create the config.yml file");
            }
        }
        copyInfoFromMobsConfig();
        update150ActionsMultiplier();
        configCfg.save(configFile);
    }

    public static void update150ActionsMultiplier() throws IOException {
        mobsCfg.set("customMessageOption.overrideKillMessage", null);
        mobsCfg.set("customMessageOption.customMessage", null);
        mobsCfg.save(mobsFile);
    }

    public static void copyInfoFromMobsConfig() throws IOException {
        mobsCfg = MobConfigManager.mobsCfg;
        if (Boolean.FALSE.equals(configCfg.isSet("version"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("version"))){
                configCfg.set("version", mobsCfg.getString("version"));
            } else {
                configCfg.set(VERSION, VERSION_NUMBER);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("checkForUpdate"))) {
            if (Boolean.TRUE.equals(mobsCfg.isSet("checkForUpdate"))){
                configCfg.set("checkForUpdate", mobsCfg.getBoolean("checkForUpdate"));
            } else {
                configCfg.set("checkForUpdate", true);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("spawneggs"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("spawneggs"))){
                configCfg.set("spawneggs", mobsCfg.getBoolean("spawneggs"));
            } else {
                configCfg.set("spawneggs", true);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("spawners"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("spawners"))){
                configCfg.set("spawners", mobsCfg.getBoolean("spawners"));
            } else {
                configCfg.set("spawners", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("tamedWolvesGiveMoney"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("tamedWolvesGiveMoney"))){
                configCfg.set("tamedWolvesGiveMoney", mobsCfg.getBoolean("tamedWolvesGiveMoney"));
            } else {
                configCfg.set("tamedWolvesGiveMoney", true);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("defaultLanguage"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("defaultLanguage"))){
                configCfg.set("defaultLanguage", mobsCfg.getString("defaultLanguage"));
            } else {
                configCfg.set("defaultLanguage", "english");
            }
        }
        int configGroupMultiplierCounter = 0;
        try {
            for (String multiplier : configCfg.getConfigurationSection("group-multiplier").getKeys(false)){
                configGroupMultiplierCounter++;
            }
        } catch (NullPointerException ignored){

        }
        if (configGroupMultiplierCounter == 0) {
            int mobsGroupMultiplierCounter = 0;
            try {
                for (String multiplier : mobsCfg.getConfigurationSection("group-multiplier").getKeys(false)){
                    mobsGroupMultiplierCounter++;
                }
            } catch (NullPointerException ignored){

            }
            if (mobsGroupMultiplierCounter == 0 && configGroupMultiplierCounter == 0){
                groupMultiplierSetter();
            }
            if (mobsGroupMultiplierCounter > 0 && configGroupMultiplierCounter == 0){
                for (String multiplier : mobsCfg.getConfigurationSection("group-multiplier").getKeys(false)){
                    configCfg.set("group-multiplier." + multiplier, mobsCfg.getDouble("group-multiplier." + multiplier));
                }
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.operator"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.operator"))){
                configCfg.set("group-multiplier.operator", mobsCfg.getDouble("group-multiplier.operator"));
            } else {
                configCfg.set("group-multiplier.operator", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-horse.multiplier"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-horse.multiplier"))){
                configCfg.set("actions-multipliers.riding-horse.multiplier", mobsCfg.getDouble("actions-multipliers.riding-horse.multiplier"));
            } else {
                configCfg.set("actions-multipliers.riding-horse.multiplier", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-horse.isActive"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-horse.isActive"))){
                configCfg.set("actions-multipliers.riding-horse.isActive", mobsCfg.getBoolean("actions-multipliers.riding-horse.isActive"));
            } else {
                configCfg.set("actions-multipliers.riding-horse.isActive", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-horse.priority"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-horse.priority"))){
                configCfg.set("actions-multipliers.riding-horse.priority", mobsCfg.getInt("actions-multipliers.riding-horse.priority"));
            } else {
                configCfg.set("actions-multipliers.riding-horse.priority", 1);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-mule.multiplier"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-mule.multiplier"))){
                configCfg.set("actions-multipliers.riding-mule.multiplier", mobsCfg.getDouble("actions-multipliers.riding-mule.multiplier"));
            } else {
                configCfg.set("actions-multipliers.riding-mule.multiplier", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-mule.isActive"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-mule.isActive"))){
                configCfg.set("actions-multipliers.riding-mule.isActive", mobsCfg.getBoolean("actions-multipliers.riding-mule.isActive"));
            } else {
                configCfg.set("actions-multipliers.riding-mule.isActive", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-mule.priority"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-mule.priority"))){
                configCfg.set("actions-multipliers.riding-mule.priority", mobsCfg.getInt("actions-multipliers.riding-mule.priority"));
            } else {
                configCfg.set("actions-multipliers.riding-mule.priority", 1);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-donkey.multiplier"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-donkey.multiplier"))){
                configCfg.set("actions-multipliers.riding-donkey.multiplier", mobsCfg.getDouble("actions-multipliers.riding-donkey.multiplier"));
            } else {
                configCfg.set("actions-multipliers.riding-donkey.multiplier", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-donkey.isActive"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-donkey.isActive"))){
                configCfg.set("actions-multipliers.riding-donkey.isActive", mobsCfg.getBoolean("actions-multipliers.riding-donkey.isActive"));
            } else {
                configCfg.set("actions-multipliers.riding-donkey.isActive", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-donkey.priority"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-donkey.priority"))){
                configCfg.set("actions-multipliers.riding-donkey.priority", mobsCfg.getInt("actions-multipliers.riding-donkey.priority"));
            } else {
                configCfg.set("actions-multipliers.riding-donkey.priority", 1);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-pig.multiplier"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-pig.multiplier"))){
                configCfg.set("actions-multipliers.riding-pig.multiplier", mobsCfg.getDouble("actions-multipliers.riding-pig.multiplier"));
            } else {
                configCfg.set("actions-multipliers.riding-pig.multiplier", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-pig.isActive"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-pig.isActive"))){
                configCfg.set("actions-multipliers.riding-pig.isActive", mobsCfg.getBoolean("actions-multipliers.riding-pig.isActive"));
            } else {
                configCfg.set("actions-multipliers.riding-pig.isActive", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-pig.priority"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-pig.priority"))){
                configCfg.set("actions-multipliers.riding-pig.priority", mobsCfg.getInt("actions-multipliers.riding-pig.priority"));
            } else {
                configCfg.set("actions-multipliers.riding-pig.priority", 1);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-strider.multiplier"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-strider.multiplier"))){
                configCfg.set("actions-multipliers.riding-strider.multiplier", mobsCfg.getDouble("actions-multipliers.riding-strider.multiplier"));
            } else {
                configCfg.set("actions-multipliers.riding-strider.multiplier", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-strider.isActive"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-strider.isActive"))){
                configCfg.set("actions-multipliers.riding-strider.isActive", mobsCfg.getBoolean("actions-multipliers.riding-strider.isActive"));
            } else {
                configCfg.set("actions-multipliers.riding-strider.isActive", false);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.riding-strider.priority"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("actions-multipliers.riding-strider.priority"))){
                configCfg.set("actions-multipliers.riding-strider.priority", mobsCfg.getInt("actions-multipliers.riding-strider.priority"));
            } else {
                configCfg.set("actions-multipliers.riding-strider.priority", 1);
            }
        }
        mobsCfg.set("version", null);
        mobsCfg.set("defaultLanguage", null);
        mobsCfg.set("tamedWolvesGiveMoney", null);
        mobsCfg.set("spawners", null);
        mobsCfg.set("spawneggs", null);
        mobsCfg.set("checkForUpdate", null);
        mobsCfg.set("actions-multipliers", null);
        mobsCfg.set("group-multiplier", null);
        mobsCfg.set("customMessageOption.overrideKillMessage", null);
        mobsCfg.set("customMessageOption.customMessage", null);
        mobsCfg.set("drops", null);

        actionMultiplierSetterV1();
        addMountedMobKilledMultiplier();
        configCfg.save(configFile);
        mobsCfg.save(mobsFile);
    }

    public static void groupMultiplierSetter() {
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.level-1"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.level-1"))){
                configCfg.set("group-multiplier.level-1", mobsCfg.getDouble("group-multiplier.level-1"));
            } else {
                configCfg.set("group-multiplier.level-1", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.level-2"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.level-2"))){
                configCfg.set("group-multiplier.level-2", mobsCfg.getDouble("group-multiplier.level-2"));
            } else {
                configCfg.set("group-multiplier.level-2", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.level-3"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.level-3"))){
                configCfg.set("group-multiplier.level-3", mobsCfg.getDouble("group-multiplier.level-3"));
            } else {
                configCfg.set("group-multiplier.level-3", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.level-4"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.level-4"))){
                configCfg.set("group-multiplier.level-4", mobsCfg.getDouble("group-multiplier.level-4"));
            } else {
                configCfg.set("group-multiplier.level-4", 1.0);
            }
        }
        if (Boolean.FALSE.equals(configCfg.isSet("group-multiplier.level-5"))){
            if (Boolean.TRUE.equals(mobsCfg.isSet("group-multiplier.level-5"))){
                configCfg.set("group-multiplier.level-5", mobsCfg.getDouble("group-multiplier.level-5"));
            } else {
                configCfg.set("group-multiplier.level-5", 1.0);
            }
        }
    }

    public static void actionMultiplierSetterV1() {
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.isMultipliersAggregate"))){
            configCfg.set("actions-multipliers.isMultipliersAggregate", false);
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.fallDamage.multiplier"))){
            configCfg.set("actions-multipliers.fallDamage.multiplier", 1.0);
            configCfg.set("actions-multipliers.fallDamage.isActive", false);
            configCfg.set("actions-multipliers.fallDamage.priority", 1);
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.longDistance.multiplier"))){
            configCfg.set("actions-multipliers.longDistance.distance", 1.0);
            configCfg.set("actions-multipliers.longDistance.multiplier", 1.0);
            configCfg.set("actions-multipliers.longDistance.isActive", false);
            configCfg.set("actions-multipliers.longDistance.priority", 1);
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.projectile.multiplier"))){
            configCfg.set("actions-multipliers.projectile.multiplier", 1.0);
            configCfg.set("actions-multipliers.projectile.isActive", false);
            configCfg.set("actions-multipliers.projectile.priority", 1);
        }
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.noWeapon.multiplier"))){
            configCfg.set("actions-multipliers.noWeapon.multiplier", 1.0);
            configCfg.set("actions-multipliers.noWeapon.isActive", false);
            configCfg.set("actions-multipliers.noWeapon.priority", 1);
        }
    }

    public static void addMountedMobKilledMultiplier() {
        if (Boolean.FALSE.equals(configCfg.isSet("actions-multipliers.mountedMob.multiplier"))){
            configCfg.set("actions-multipliers.mountedMob.multiplier", 1.0);
            configCfg.set("actions-multipliers.mountedMob.isActive", false);
            configCfg.set("actions-multipliers.mountedMob.priority", 1);
        }
    }
}
