package Money4Mobs.Money4Mobs;

import Money4Mobs.Configurations.MessagesConfigManager;
import Money4Mobs.Configurations.MobConfigManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MobWorthTabComplete implements TabCompleter {
    private static List<String> itemList = new ArrayList<>();
    private static List<String> worldList = new ArrayList<>();
    private static Material[] m = Material.values();
    private static List<String> mobArrayList = new ArrayList<>();
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> firstArgumentList = new ArrayList<>();
        firstArgumentList.add("addCustomDrop");
        firstArgumentList.add("addWorld");
        firstArgumentList.add("drops");
        firstArgumentList.add("language");
        firstArgumentList.add("mobRewardWorlds");
        firstArgumentList.add("reload");
        firstArgumentList.add("removeCustomDrop");
        firstArgumentList.add("removeWorld");
        firstArgumentList.add("setHighWorth");
        firstArgumentList.add("setLowWorth");
        firstArgumentList.add("toggleCustomDrops");
        firstArgumentList.add("toggleDefaultDrops");
        firstArgumentList.add("toggleKM");
        firstArgumentList.add("toggleMoneyFromSpawnEggs");
        firstArgumentList.add("toggleMoneyFromSpawners");
        firstArgumentList.add("toggleMoneyFromTamedWolves");
        firstArgumentList.add("worth");

        Player pa = (Player) sender;

        if (!pa.isOp()){
            if(!pa.hasPermission("m4m.command.mk.toggleKM")){
                firstArgumentList.remove("toggleKM");
            }
            if(!pa.hasPermission("m4m.command.mk.worth")){
                firstArgumentList.remove("worth");
            }
            if(!pa.hasPermission("m4m.command.mk.setHighWorth")){
                firstArgumentList.remove("setHighWorth");
            }
            if(!pa.hasPermission("m4m.command.mk.setLowWorth")){
                firstArgumentList.remove("setLowWorth");
            }
            if(!pa.hasPermission("m4m.command.mk.drops")){
                firstArgumentList.remove("drops");
            }
            if(!pa.hasPermission("m4m.command.mk.addCustomDrop")){
                firstArgumentList.remove("addCustomDrop");
            }
            if(!pa.hasPermission("m4m.command.mk.removeCustomDrop")){
                firstArgumentList.remove("removeCustomDrop");
            }
            if(!pa.hasPermission("m4m.command.mk.toggleCustomDrops")){
                firstArgumentList.remove("toggleCustomDrops");
            }
            if(!pa.hasPermission("m4m.command.mk.toggleDefaultDrops")){
                firstArgumentList.remove("toggleDefaultDrops");
            }
            if(!pa.hasPermission("m4m.command.mk.spawneggs")){
                firstArgumentList.remove("toggleMoneyFromSpawnEggs");
            }
            if(!pa.hasPermission("m4m.command.mk.spawners")){
                firstArgumentList.remove("toggleMoneyFromSpawners");
            }
            if(!pa.hasPermission("m4m.command.mk.language")){
                firstArgumentList.remove("language");
            }
            if(!pa.hasPermission("m4m.command.mk.toggleMoneyFromTamedWolves")){
                firstArgumentList.remove("toggleMoneyFromTamedWolves");
            }
            if(!pa.hasPermission("m4m.command.mk.reload")){
                firstArgumentList.remove("reload");
            }
            if(!pa.hasPermission("m4m.command.mk.mobRewardWorlds")){
                firstArgumentList.remove("mobRewardWorlds");
            }
            if(!pa.hasPermission("m4m.command.mk.addWorld")){
                firstArgumentList.remove("addWorld");
            }
            if(!pa.hasPermission("m4m.command.mk.removeWorld")){
                firstArgumentList.remove("removeWorld");
            }
            if(firstArgumentList.size() == 0 ){
                firstArgumentList.add(0, ChatColor.RED + "You do not have access to this command.");
            }
        }

        for (int i = 0; i < m.length; i++){
            itemList.add(i, m[i].toString());
        }
        try {
            if (args[0].equalsIgnoreCase("worth") || args[0].equalsIgnoreCase("drops")
                    || args[0].equalsIgnoreCase("setLowWorth") || args[0].equalsIgnoreCase("setHighWorth") ||
                    args[0].equalsIgnoreCase("toggleCustomDrops") || args[0].equalsIgnoreCase("toggleDefaultDrops") ||
                    args[0].equalsIgnoreCase("mobRewardWorlds")){
                    setMobList();
                    return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
            }
            if (args[0].equalsIgnoreCase("removeWorld")){
                List<String> worldList = new ArrayList<>(MobConfigManager.mobsCfg.getConfigurationSection("mobs.Zombie.worlds").getKeys(false));
                return StringUtil.copyPartialMatches(args[1], worldList, new ArrayList<>());
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>());
        }
        if(args[0].equalsIgnoreCase("addCustomDrop")) {
            setMobList();
            if (args.length <= 2) {
                if (StringUtils.isNotBlank(args[0])) {
                    try {
                        return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
                    } catch (ArrayIndexOutOfBoundsException ignored){
                    }
                }
            }
            else {
                for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                    if (StringUtils.isNotBlank(args[1]) && mobObject.equalsIgnoreCase(args[1])) {
                            try {
                                return StringUtil.copyPartialMatches(args[2], itemList, new ArrayList<>());
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                                //
                            }
                    }
                }
            }

        }
        if(args[0].equalsIgnoreCase("removeCustomDrop")) {
            setMobList();
            if (args.length <= 2) {
                if (StringUtils.isNotBlank(args[0])) {
                    try {
                        return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
                    } catch (ArrayIndexOutOfBoundsException ignored){
                    }
                }
            }
            else {
                String mobName = args[1].substring(0, 1).toUpperCase() + args[1].substring(1);
                for(String mobObject : Objects.requireNonNull(MobConfigManager.mobsCfg.getConfigurationSection("mobs")).getKeys(false)) {
                    if (mobObject.equalsIgnoreCase(args[1])) {
                        itemList.clear();
                        try {
                            for(String drop : MobConfigManager.mobsCfg.getConfigurationSection("mobs." + mobName + ".drops").getKeys(false)) {
                                itemList.add(MobConfigManager.mobsCfg.getString("mobs." + mobName + ".drops." + drop + ".name"));
                            }
                        } catch (NullPointerException ignored){
                        }
                    }
                }
                try {
                    return StringUtil.copyPartialMatches(args[2], itemList, new ArrayList<>());
                }
                catch (ArrayIndexOutOfBoundsException ignored) {
                    //
                }
            }
        }
        if(args[0].equalsIgnoreCase("language") || args[0].equalsIgnoreCase("defaultLanguage") ) {
            List<String> languageList = new ArrayList<>(MessagesConfigManager.messagesCfg.getConfigurationSection("language").getKeys(false));
            try {
                return StringUtil.copyPartialMatches(args[1], languageList, new ArrayList<>());
            } catch (ArrayIndexOutOfBoundsException ignored){
            }
        }
        return StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>());
    }

    public static void setMobList() {
        mobArrayList.addAll(MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false));
    }
}
