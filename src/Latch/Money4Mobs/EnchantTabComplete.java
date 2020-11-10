package Latch.Money4Mobs;

import java.util.*;
import java.util.stream.Collectors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.util.StringUtil;

public class EnchantTabComplete implements TabCompleter {
    //create a static array of values you want to return
    private static Set<Enchantment> enchantmentList = new HashSet<>();
    private static List<String> strings;
    protected List<String> ecList;

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

            for(Enchantment enchantment : Enchantment.values()) {
                enchantmentList.add(enchantment);
            }
            List<Enchantment> aList = enchantmentList.stream().collect(Collectors.toList());
            strings = aList.stream()
                    .map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
            for (int i = 0; i < strings.size(); i++) {

                String enchantment = strings.get(i);
                String[] enchantmentArray = enchantment.split(":");
                enchantmentArray = enchantmentArray[1].split(",");
                enchantment = enchantmentArray[0];
                strings.set(i, enchantment);
            }
            setEnchantmentList(strings);


        return (args.length > 0) ? StringUtil.copyPartialMatches(args[0], strings, new ArrayList<>()) : null;
    }

    public void setEnchantmentList(List<String> value) {
        this.ecList = value;
    }


}
