package Latch.Money4Mobs;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MobWorthTabComplete implements TabCompleter {
    private static SetMobList mobModelList = new SetMobList();
    private static List<String> itemList = new ArrayList<>();
    private static Material[] m = Material.values();
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> mobArrayList = new ArrayList<>();
        List<String> firstArgumentList = new ArrayList<>();

        firstArgumentList.add(0, "addCustomDrop");
        firstArgumentList.add(1, "drops");
        firstArgumentList.add(2, "removeCustomDrop");
        firstArgumentList.add(3, "setMobDefaultDrops");
        firstArgumentList.add(4, "setMoneyFromSpawners");
        firstArgumentList.add(5, "setMoneyFromSpawnEggs");
        firstArgumentList.add(6, "setHighWorth");
        firstArgumentList.add(7, "setLowWorth");
        firstArgumentList.add(8, "toggleCustomDrops");
        firstArgumentList.add(9, "toggleKM");
        firstArgumentList.add(10, "worth");
        for (int i = 0; i < m.length; i++){
            itemList.add(i, m[i].toString());
        }
        try {
            if (args[0].equalsIgnoreCase("worth") || args[0].equalsIgnoreCase("drops")
                    || args[0].equalsIgnoreCase("setLowWorth") || args[0].equalsIgnoreCase("setHighWorth") ||
                    args[0].equalsIgnoreCase("toggleCustomDrops")){
                    List<MobModel> mobsList = mobModelList.getMobModel();
                    for (int i = 0; i < mobsList.size(); i++){
                        mobArrayList.add(i, mobsList.get(i).getMobName());
                    }
                    return (args.length > 0) ? StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>()) : null;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>()) : null;
        }
        if(args[0].equalsIgnoreCase("addCustomDrop")) {
            List<MobModel> mobsList = mobModelList.getMobModel();
            for (int i = 0; i < mobsList.size(); i++){
                mobArrayList.add(i, mobsList.get(i).getMobName());
            }
            if (args.length > 1) {
                if (StringUtils.isNotBlank(args[0]) && StringUtils.isBlank(args[1])) {
                    return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
                }
                else if (StringUtils.isNotBlank(args[1])) {
                    try {
                        return StringUtil.copyPartialMatches(args[2], itemList, new ArrayList<>());
                    }
                    catch (ArrayIndexOutOfBoundsException ignored) {

                    }
                }
            }

        }

        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>()) : null;
    }
}
