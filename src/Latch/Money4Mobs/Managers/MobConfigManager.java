package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.ItemModel;
import Latch.Money4Mobs.MobModel;
import Latch.Money4Mobs.Money4Mobs;
import Latch.Money4Mobs.SetMobList;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Mob;

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
        }
        update137AddPiglin();
        update14Add117Mobs();
        update155UpdateChecker();
        updateZombifiedPiglin169();
        updatePerWorldReward169();
        mobsCfg.save(mobsFile);
    }

    public void createMobsConfig(FileConfiguration mobsCfg) {
        SetMobList sml = new SetMobList();
        List<MobModel> mobList = sml.getMobModel();
        try {
            mobsFile.createNewFile();
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

    public static void update155UpdateChecker() throws IOException {
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Player.enablePercentageDrop"))) {
            mobsCfg.set(MOBS + "Player.enablePercentageDrop", false);
        }
        if (Boolean.FALSE.equals(mobsCfg.isSet(MOBS + "Player.percentageDropAmount"))) {
            mobsCfg.set(MOBS + "Player.percentageDropAmount", 0);
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

        mobsCfg.save(mobsFile);
    }

    public static List<MobModel> getMobModelFromConfig() {
        return mobListFromConfig;
    }

    public static void updatePerWorldReward169() throws IOException {
        for(String mob : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
            if (!mobsCfg.isSet("mobs." + mob + ".worlds.world")){
                mobsCfg.set("mobs." + mob + ".worlds.world", true);
            }
            if (!mobsCfg.isSet("mobs." + mob + ".worlds.world_nether")){
                mobsCfg.set("mobs." + mob + ".worlds.world_nether", true);
            }
            if (!mobsCfg.isSet("mobs." + mob + ".worlds.world_the_end")){
                mobsCfg.set("mobs." + mob + ".worlds.world_the_end", true);
            }
        }
        mobsCfg.save(mobsFile);
    }

    public static void updateZombifiedPiglin169() throws IOException {
        if (mobsCfg.isSet("mobs.PigZombie")){
            mobsCfg.set("mobs.ZombifiedPiglin.worth.low", mobsCfg.getDouble("mobs.PigZombie.worth.low"));
            mobsCfg.set("mobs.ZombifiedPiglin.worth.high", mobsCfg.getDouble("mobs.PigZombie.worth.high"));
            mobsCfg.set("mobs.ZombifiedPiglin.keepDefaultDrops", mobsCfg.getBoolean("mobs.PigZombie.keepDefaultDrops"));
            mobsCfg.set("mobs.ZombifiedPiglin.customDrops", mobsCfg.getBoolean("mobs.PigZombie.customDrops"));
        } else if (!mobsCfg.isSet("mobs.ZombifiedPiglin")){
            mobsCfg.set("mobs.ZombifiedPiglin.worth.low", 20);
            mobsCfg.set("mobs.ZombifiedPiglin.worth.high", 20);
            mobsCfg.set("mobs.ZombifiedPiglin.keepDefaultDrops", true);
            mobsCfg.set("mobs.ZombifiedPiglin.customDrops", false);
        }
        mobsCfg.set("mobs.PigZombie", null);
    }
}
