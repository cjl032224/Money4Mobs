package Latch.Money4Mobs;

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
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> mobArrayList = new ArrayList<>();
        List<String> firstArgumentList = new ArrayList<>();
        List<String> setWorthList = new ArrayList<>();

        firstArgumentList.add(0, "drops");
        firstArgumentList.add(1, "setHighWorth");
        firstArgumentList.add(2, "setLowWorth");
        firstArgumentList.add(3, "toggleKM");
        firstArgumentList.add(4, "worth");
        try {
            if (args[0].equalsIgnoreCase("worth") || args[0].equalsIgnoreCase("drops")
                    || args[0].equalsIgnoreCase("setLowWorth") || args[0].equalsIgnoreCase("setHighWorth")){
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
        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>()) : null;
    }
}
