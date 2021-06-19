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
            mobsCfg.save(mobsFile);
        }
    }

    public void createMobsConfig(FileConfiguration mobsCfg) {
        SetMobList sml = new SetMobList();
        List<MobModel> mobList = sml.getMobModel();
        try {
            mobsFile.createNewFile();
            mobsCfg.set("version", "1.4.2");
            mobsCfg.set("spawners", false);
            mobsCfg.set("spawneggs", false);
            mobsCfg.set("tamedWolvesGiveMoney", true);
            mobsCfg.set("defaultLanguage", "English");
            mobsCfg.set("customMessageOption.overrideKillMessage", false);
            mobsCfg.set("customMessageOption.customMessage", "%GREEN% Rewarded | %GOLD% $ %AMOUNT% | %GREEN% and | now | have | %GOLD% $ %BALANCE% %GREEN% .");
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
                mobsCfg.set("mobs." + mobName + ".worth.low", lowWorth);
                mobsCfg.set("mobs." + mobName + ".worth.high", highWorth);
                mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                mobsCfg.set("mobs." + mobName + ".customDrops", false);
                String air = "AIR";
                if (i == 1) {
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.name", air);
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.amount", 5);
                    mobsCfg.set("mobs." + mobName + ".drops.item-1.chance", 100);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.name", air);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.amount", 10);
                    mobsCfg.set("mobs." + mobName + ".drops.item-2.chance", 100);
                }
            }
            mobsCfg.set("mobs.Player.keepDefaultDrops", null);
            mobsCfg.set("mobs.Player.customDrops", null);
            mobsCfg.set("drops", null);
            mobsCfg.save(mobsFile);
        } catch (IOException e) {
            System.out.println(ChatColor.RED + "Could not create the mobs.yml file");
        }
    }

    // Sets mob list from mobs.yml file
    public static void setMobListFromConfig() throws IOException {
        Integer counter1 = 0;
        for (String path : Objects.requireNonNull(mobsCfg.getConfigurationSection("mobs")).getKeys(false)) {
            Double lowWorth = mobsCfg.getDouble("mobs." + path + ".worth.low");
            Double highWorth = mobsCfg.getDouble("mobs." + path + ".worth.high");
            Boolean customDrops = mobsCfg.getBoolean("mobs." + path + ".customDrops");
            Boolean keepDefaultDrops = mobsCfg.getBoolean("mobs." + path + ".keepDefaultDrops");
            Integer counter = 1;
            List<ItemModel> im = new ArrayList<ItemModel>();
            if (mobsCfg.getString("mobs." + path + ".drops.item-1.name") != null) {
                for (String items : mobsCfg.getConfigurationSection("mobs." + path + ".drops").getKeys(false)) {
                    String name = mobsCfg.getString("mobs." + path + ".drops.item-" + counter + ".name");
                    Integer amount = mobsCfg.getInt("mobs." + path + ".drops.item-" + counter + ".amount");
                    Integer chance = mobsCfg.getInt("mobs." + path + ".drops.item-" + counter + ".chance");
                    im.add(new ItemModel(name, amount, chance));
                    counter++;
                }
            }
            if (!mobsCfg.getBoolean("mobs.Player.ipBan")) {
                mobsCfg.set("mobs.Player.ipBanFarming", false);
            }
            mobsCfg.set("mobs.Player.keepDefaultDrops", null);
            mobsCfg.set("mobs.Player.customDrops", null);
            mobsCfg.set("drops", null);
            mobsCfg.save(mobsFile);
            mobListFromConfig.add(new MobModel(path, lowWorth, highWorth, keepDefaultDrops, customDrops, im));
            mobListFromConfig.get(counter1).setCustomDrops(customDrops);
            mobListFromConfig.get(counter1).setKeepDefaultDrops(keepDefaultDrops);
            mobListFromConfig.get(counter1).setItems(im);
            counter1++;
        }
        update137AddPiglin();
        update14Add117Mobs();
        updatePluginVersion();
        update142AddCustomizableMessages();

    }

    public static void updatePluginVersion() throws IOException {
        if (mobsCfg.getString("version") != "1.4.2") {
            mobsCfg.set("version", "1.4.2");
        }
        mobsCfg.save(mobsFile);
    }

    public static void update137AddPiglin() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Piglin.worth.low"))) {
            mobsCfg.set("mobs.Piglin.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Piglin.worth.high"))) {
            mobsCfg.set("mobs.Piglin.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Piglin.keepDefaultDrops"))) {
            mobsCfg.set("mobs.Piglin.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Piglin.customDrops"))) {
            mobsCfg.set("mobs.Piglin.customDrops", false);
        }
        mobsCfg.save(mobsFile);

    }

    public static void update14Add117Mobs() throws IOException {
        // Axolotl
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Axolotl.worth.low"))) {
            mobsCfg.set("mobs.Axolotl.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Axolotl.worth.high"))) {
            mobsCfg.set("mobs.Axolotl.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Axolotl.keepDefaultDrops"))) {
            mobsCfg.set("mobs.Axolotl.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Axolotl.customDrops"))) {
            mobsCfg.set("mobs.Axolotl.customDrops", false);
        }
        // Glow Squid
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.GlowSquid.worth.low"))) {
            mobsCfg.set("mobs.GlowSquid.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.GlowSquid.worth.high"))) {
            mobsCfg.set("mobs.GlowSquid.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.GlowSquid.keepDefaultDrops"))) {
            mobsCfg.set("mobs.GlowSquid.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.GlowSquid.customDrops"))) {
            mobsCfg.set("mobs.GlowSquid.customDrops", false);
        }
        // Goat
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Goat.worth.low"))) {
            mobsCfg.set("mobs.Goat.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Goat.worth.high"))) {
            mobsCfg.set("mobs.Goat.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Goat.keepDefaultDrops"))) {
            mobsCfg.set("mobs.Goat.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Goat.customDrops"))) {
            mobsCfg.set("mobs.Goat.customDrops", false);
        }
        // Zoglin
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Zoglin.worth.low"))) {
            mobsCfg.set("mobs.Zoglin.worth.low", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Zoglin.worth.high"))) {
            mobsCfg.set("mobs.Zoglin.worth.high", 25);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Zoglin.keepDefaultDrops"))) {
            mobsCfg.set("mobs.Zoglin.keepDefaultDrops", true);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet("mobs.Zoglin.customDrops"))) {
            mobsCfg.set("mobs.Zoglin.customDrops", false);
        }
        mobsCfg.save(mobsFile);

    }

    public static void update142AddCustomizableMessages() throws IOException {
        mobsCfg.set("defaultLanguage", null);
        mobsCfg.save(mobsFile);
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

}
