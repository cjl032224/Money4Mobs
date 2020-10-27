package Latch.Enchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MobWorthTabComplete implements TabCompleter {

    private static Set<String> worthList = new HashSet<>();
    private static List<String> strings;

    public List<String> getMobs() {
        List<String> mobArrayList = new ArrayList<>();
        SetMobList mobModelList = new SetMobList();
        List<MobModel> mobsList = mobModelList.getMobModel();
        for (int i = 0; i < mobsList.size(); i++){
            mobArrayList.add(i, mobsList.get(i).getMobName());
        }
        return mobArrayList;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return getMobs();
    }

    public void setWorthList(List<String> value) {
        this.strings = value;
    }

    public List<String> getWorthList() {
        return strings;
    }
}
