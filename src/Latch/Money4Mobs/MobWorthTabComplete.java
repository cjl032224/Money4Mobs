package Latch.Money4Mobs;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
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

        firstArgumentList.add("addCustomDrop");
        firstArgumentList.add("defaultLanguage");
        firstArgumentList.add("drops");
        firstArgumentList.add("language");
        firstArgumentList.add("reload");
        firstArgumentList.add("removeCustomDrop");
        firstArgumentList.add("setHighWorth");
        firstArgumentList.add("setLowWorth");
        firstArgumentList.add("toggleCustomDrops");
        firstArgumentList.add("toggleDefaultDrops");
        firstArgumentList.add("toggleKM");
        firstArgumentList.add("toggleMoneyFromSpawnEggs");
        firstArgumentList.add("toggleMoneyFromSpawners");
        firstArgumentList.add("toggleCustomKM");
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
            if(!pa.hasPermission("m4m.command.mk.defaultLanguage")){
                firstArgumentList.remove("defaultLanguage");
            }
            if(!pa.hasPermission("m4m.command.mk.reload")){
                firstArgumentList.remove("reload");
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
                    args[0].equalsIgnoreCase("toggleCustomDrops") || args[0].equalsIgnoreCase("toggleDefaultDrops")){
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
            if (args.length <= 2) {
                if (StringUtils.isNotBlank(args[0])) {
                    try {
                        return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
                    } catch (ArrayIndexOutOfBoundsException e){
                        //
                    }
                }
            }
            else {
                for (MobModel mobModel : mobsList) {
                    if (mobModel.getMobName().equalsIgnoreCase(args[1])) {
                        if (StringUtils.isNotBlank(args[1])) {
                            try {
                                return StringUtil.copyPartialMatches(args[2], itemList, new ArrayList<>());
                            } catch (ArrayIndexOutOfBoundsException ignored) {
                                //
                            }
                        }
                    }
                }
            }

        }
        if(args[0].equalsIgnoreCase("removeCustomDrop")) {
            List<MobModel> mobsList = mobModelList.getMobModel();
            for (int i = 0; i < mobsList.size(); i++){
                mobArrayList.add(i, mobsList.get(i).getMobName());
            }
            if (args.length <= 2) {
                if (StringUtils.isNotBlank(args[0])) {
                    try {
                        return StringUtil.copyPartialMatches(args[1], mobArrayList, new ArrayList<>());
                    } catch (ArrayIndexOutOfBoundsException e ){
                        //
                    }
                }
            }
            else {
                List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
                for (MobModel mobModel : mm) {
                    if (mobModel.getMobName().equalsIgnoreCase(args[1])) {
                        itemList.clear();
                        for (int k = 0; k < mobModel.getItems().size(); k++) {
                            itemList.add(mobModel.getItems().get(k).getItemName());
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
            List<String> languageList = new ArrayList<>();
            languageList.add("Chinese_Simplified");
            languageList.add("Chinese_Traditional");
            languageList.add("English");
            languageList.add("French");
            languageList.add("German");
            languageList.add("Hindi");
            languageList.add("Italian");
            languageList.add("Spanish");
            try {
                return StringUtil.copyPartialMatches(args[1], languageList, new ArrayList<>());
            } catch (ArrayIndexOutOfBoundsException ignore){

            }
        }
        return StringUtil.copyPartialMatches(args[0], firstArgumentList, new ArrayList<>());
    }
}
