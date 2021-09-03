package Latch.DutchmanMiniGames;

import Latch.DutchmanMiniGames.Managers.UserManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class DutchmanTabComplete implements TabCompleter {


    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> firstArgumentList = new ArrayList<>();
        List<String> userList = new ArrayList<>();

        firstArgumentList.add("balance");
        firstArgumentList.add("give");
        firstArgumentList.add("reset");
        firstArgumentList.add("set");
        firstArgumentList.add("take");

        Player pa = (Player) sender;

        if (!pa.isOp()) {
            if (!pa.hasPermission("dutchman.command.tickets.balance")) {
                firstArgumentList.remove("balance");
            }
            if (!pa.hasPermission("dutchman.command.tickets.give")) {
                firstArgumentList.remove("give");
            }
            if (!pa.hasPermission("dutchman.command.tickets.reset")) {
                firstArgumentList.remove("reset");
            }
            if (!pa.hasPermission("dutchman.command.tickets.take")) {
                firstArgumentList.remove("take");
            }
            if (!pa.hasPermission("dutchman.command.tickets.set")) {
                firstArgumentList.remove("set");
            }
            if (firstArgumentList.size() == 0) {
                firstArgumentList.add(0, ChatColor.RED + "You do not have access to this command.");
            }
        }


        if(command.getName().equalsIgnoreCase("tickets")) {
            if (args.length <= 1) {
                if (StringUtils.isNotBlank(args[0])) {
                    try {
                        return StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>());
                    } catch (ArrayIndexOutOfBoundsException ignored){
                    }
                }
            }
            else {
                for(String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                    userList.add(UserManager.usersCfg.getString("users." + user + ".userName"));
                }
                try {
                    return StringUtil.copyPartialMatches(args[1], userList, new ArrayList<>());
                }
                catch (ArrayIndexOutOfBoundsException ignored) {
                    //
                }
            }
        }
        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>()) : null;
    }

}