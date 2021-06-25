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
    public static final String VERSION_NUMBER = "1.5.6";
    private static final String VERSION = "version";
    private static final String MOBS = "mobs.";
    private static final String DROPS_ITEM = ".drops.item-";

    // Set up mobs.yml configuration file
    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        mobsFile = new File(plugin.getDataFolder(), "mobs.yml");
        mobsCfg = YamlConfiguration.loadConfiguration(mobsFile);
        //if the mobs.yml does not exist, create it
        if (!mobsFile.exists()) {
            try {
                mobsFile.createNewFile();
                createMobsConfig(mobsCfg);
                mobsCfg.save(mobsFile);
            } catch (IOException e) {
                System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
            }
        } else {
            if (mobsCfg.getString("defaultLanguage") == null) {
                mobsCfg.set("defaultLanguage", "English");
            }
        }
        update137AddPiglin();
        update14Add117Mobs();
        update150ActionsMultiplier();
        update153UpdateChecker();
        update155UpdateChecker();
        updatePluginVersion();
        mobsCfg.save(mobsFile);
    }

    public void createMobsConfig(FileConfiguration mobsCfg) {
        SetMobList sml = new SetMobList();
        List<MobModel> mobList = sml.getMobModel();
        try {
            mobsFile.createNewFile();
            mobsCfg.set(VERSION, VERSION_NUMBER);
            mobsCfg.set("spawners", false);
            mobsCfg.set("spawneggs", false);
            mobsCfg.set("tamedWolvesGiveMoney", true);
            mobsCfg.set("defaultLanguage", "english");
            mobsCfg.set("group-multiplier.level-1", 1);
            mobsCfg.set("group-multiplier.level-2", 1);
            mobsCfg.set("group-multiplier.level-3", 1);
            mobsCfg.set("group-multiplier.level-4", 1);
            mobsCfg.set("group-multiplier.level-5", 1);
            mobsCfg.set("group-multiplier.operator", 1);
            for (int i = 0; i < mobList.size(); i++) {
                String mobName = mobList.get(i).mobName;
                Double lowWorth = mobList.get(i).lowWorth;
                Double highWorth = mobList.get(i).highWorth;
                mobsCfg.set(MOBS + mobName + ".worth.low", lowWorth);
                mobsCfg.set(MOBS + mobName + ".worth.high", highWorth);
                mobsCfg.set(MOBS + mobName + ".keepDefaultDrops", true);
                mobsCfg.set(MOBS + mobName + ".customDrops", false);
                String air = "AIR";
                if (i == 1) {
                    mobsCfg.set(MOBS + mobName + ".drops.item-1.name", air);
                    mobsCfg.set(MOBS + mobName + ".drops.item-1.amount", 5);
                    mobsCfg.set(MOBS + mobName + ".drops.item-1.chance", 100);
                    mobsCfg.set(MOBS + mobName + ".drops.item-2.name", air);
                    mobsCfg.set(MOBS + mobName + ".drops.item-2.amount", 10);
                    mobsCfg.set(MOBS + mobName + ".drops.item-2.chance", 100);
                }
            }
            mobsCfg.set(MOBS + "Player.keepDefaultDrops", null);
            mobsCfg.set(MOBS + "Player.customDrops", null);
            mobsCfg.set("drops", null);
            mobsCfg.save(mobsFile);
        } catch (IOException e) {
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    // Sets mob list from mobs.yml file
    public static void setMobListFromConfig() throws IOException {
        int counter1 = 0;
        for (String path : Objects.requireNonNull(mobsCfg.getConfigurationSection("mobs")).getKeys(false)) {
            Double lowWorth = mobsCfg.getDouble(MOBS + path + ".worth.low");
            Double highWorth = mobsCfg.getDouble(MOBS + path + ".worth.high");
            Boolean customDrops = mobsCfg.getBoolean(MOBS + path + ".customDrops");
            Boolean keepDefaultDrops = mobsCfg.getBoolean(MOBS + path + ".keepDefaultDrops");
            int counter = 1;
            List<ItemModel> im = new ArrayList<ItemModel>();
            if (mobsCfg.getString("mobs." + path + ".drops.item-1.name") != null) {
                for (String items : mobsCfg.getConfigurationSection(MOBS + path + ".drops").getKeys(false)) {
                    String name = mobsCfg.getString(MOBS + path + DROPS_ITEM + counter + ".name");
                    Integer amount = mobsCfg.getInt(MOBS + path + DROPS_ITEM + counter + ".amount");
                    Integer chance = mobsCfg.getInt(MOBS + path + DROPS_ITEM + counter + ".chance");
                    im.add(new ItemModel(name, amount, chance));
                    counter++;
                }
            }
            if (!mobsCfg.getBoolean(MOBS + "Player.ipBan")) {
                mobsCfg.set(MOBS + "Player.ipBanFarming", false);
            }
            mobsCfg.set(MOBS + "Player.keepDefaultDrops", null);
            mobsCfg.set(MOBS + "Player.customDrops", null);
            mobsCfg.set("drops", null);
            mobsCfg.save(mobsFile);
            mobListFromConfig.add(new MobModel(path, lowWorth, highWorth, keepDefaultDrops, customDrops, im));
            mobListFromConfig.get(counter1).setCustomDrops(customDrops);
            mobListFromConfig.get(counter1).setKeepDefaultDrops(keepDefaultDrops);
            mobListFromConfig.get(counter1).setItems(im);
            counter1++;
        }


    }

    public static void updatePluginVersion() throws IOException {
        if (!Objects.equals(mobsCfg.getString(VERSION), VERSION_NUMBER)) {
            mobsCfg.set(VERSION, VERSION_NUMBER);
        }
        mobsCfg.save(mobsFile);
    }

    public static void update137AddPiglin() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Piglin.worth.low"))) {
            mobsCfg.set(MOBS + "Piglin.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Piglin.worth.high"))) {
            mobsCfg.set(MOBS + "Piglin.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Piglin.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "Piglin.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Piglin.customDrops"))) {
            mobsCfg.set(MOBS + "Piglin.customDrops", false);
        }
        mobsCfg.save(mobsFile);

    }

    public static void update14Add117Mobs() throws IOException {
        // Axolotl
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Axolotl.worth.low"))) {
            mobsCfg.set(MOBS + "Axolotl.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Axolotl.worth.high"))) {
            mobsCfg.set(MOBS + "Axolotl.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Axolotl.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "Axolotl.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Axolotl.customDrops"))) {
            mobsCfg.set(MOBS + "Axolotl.customDrops", false);
        }
        // Glow Squid
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "GlowSquid.worth.low"))) {
            mobsCfg.set(MOBS + "GlowSquid.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "GlowSquid.worth.high"))) {
            mobsCfg.set(MOBS + "GlowSquid.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "GlowSquid.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "GlowSquid.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "GlowSquid.customDrops"))) {
            mobsCfg.set(MOBS + "GlowSquid.customDrops", false);
        }
        // Goat
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Goat.worth.low"))) {
            mobsCfg.set(MOBS + "Goat.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Goat.worth.high"))) {
            mobsCfg.set(MOBS + "Goat.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Goat.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "Goat.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Goat.customDrops"))) {
            mobsCfg.set(MOBS + "Goat.customDrops", false);
        }
        // Zoglin
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Zoglin.worth.low"))) {
            mobsCfg.set(MOBS + "Zoglin.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Zoglin.worth.high"))) {
            mobsCfg.set(MOBS + "Zoglin.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Zoglin.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "Zoglin.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Zoglin.customDrops"))) {
            mobsCfg.set(MOBS + "Zoglin.customDrops", false);
        }
        mobsCfg.save(mobsFile);

    }

    public static void update150ActionsMultiplier() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet("actions-multipliers.riding-horse.multiplier"))) {
            mobsCfg.set("actions-multipliers.riding-horse.multiplier", 1.0);
            mobsCfg.set("actions-multipliers.riding-horse.isActive", false);
            mobsCfg.set("actions-multipliers.riding-mule.multiplier", 1.0);
            mobsCfg.set("actions-multipliers.riding-mule.isActive", false);
            mobsCfg.set("actions-multipliers.riding-donkey.multiplier", 1.0);
            mobsCfg.set("actions-multipliers.riding-donkey.isActive", false);
            mobsCfg.set("actions-multipliers.riding-strider.multiplier", 1.0);
            mobsCfg.set("actions-multipliers.riding-strider.isActive", false);
            mobsCfg.set("actions-multipliers.riding-pig.multiplier", 1.0);
            mobsCfg.set("actions-multipliers.riding-pig.isActive", false);
        }
        // Horse
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Horse.worth.low"))) {
            mobsCfg.set(MOBS + "Horse.worth.low", 20);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Horse.worth.high"))) {
            mobsCfg.set(MOBS + "Horse.worth.high", 20);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Horse.keepDefaultDrops"))) {
            mobsCfg.set(MOBS + "Horse.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Horse.customDrops"))) {
            mobsCfg.set(MOBS + "Horse.customDrops", false);
        }

        mobsCfg.set("customMessageOption.overrideKillMessage", null);
        mobsCfg.set("customMessageOption.customMessage", null);

        mobsCfg.save(mobsFile);
    }

    public static void update153UpdateChecker() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet("checkForUpdate"))) {
            mobsCfg.set("checkForUpdate", true);
        }
        mobsCfg.save(mobsFile);
    }

    public static void update155UpdateChecker() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Player.enablePercentageDrop"))) {
            mobsCfg.set(MOBS + "Player.enablePercentageDrop", false);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Player.percentageDropAmount"))) {
            mobsCfg.set(MOBS + "Player.percentageDropAmount", 0);
        }

        mobsCfg.save(mobsFile);
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

}
