package Latch.Money4Mobs;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MkCommand implements CommandExecutor {
    private MobConfigManager MobCfgm;
    FileConfiguration mobsCfg = MobCfgm.mobsCfg;
    File pFile = MobConfigManager.mobsFile;
    private static Material[] materials = Material.values();
    private static List<MobModel> mobList = new ArrayList<MobModel>();

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        List<Mobs4MoneyPlayer> playerList = Money4Mobs.getPlayerList();
        Player player = (Player) commandSender;
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        Material[] materialList = ItemListManager.getItemModelFromConfig();

        for (int i = 0; i < playerList.size(); i++) {
            if (player.getName().equals(playerList.get(i).getPlayerName())) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggleKM")) {
                        if (player.hasPermission("m4m.command.mk.toggleKM")) {
                            if (Boolean.TRUE.equals(playerList.get(i).getKillerMessage())) {
                                player.sendMessage(ChatColor.GREEN + "MobKiller message off");
                                playerList.get(i).setKillerMessage(false);
                            } else {
                                player.sendMessage(ChatColor.GREEN + "MobKiller message on");
                                playerList.get(i).setKillerMessage(true);
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs")) {
                            Boolean spawnEgg = MobCfgm.mobsCfg.getBoolean("spawneggs");
                            if (Boolean.TRUE.equals(spawnEgg)){
                                MobCfgm.mobsCfg.set("spawneggs", false);
                                spawnEgg = false;
                            } else {
                                MobCfgm.mobsCfg.set("spawneggs", true);
                                spawnEgg = true;
                            }
                            try {
                                player.sendMessage(ChatColor.GREEN + "Money rewarded from mobs spawned with eggs is set to " + ChatColor.GOLD + spawnEgg);
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawners")) {
                            Boolean spawners = MobCfgm.mobsCfg.getBoolean("spawners");
                            if (Boolean.TRUE.equals(spawners)){
                                MobCfgm.mobsCfg.set("spawners", false);
                                spawners = false;
                            } else {
                                MobCfgm.mobsCfg.set("spawners", true);
                                spawners = true;
                            }
                            try {
                                player.sendMessage(ChatColor.GREEN + "Money rewarded from spawner mobs is set to " + ChatColor.GOLD + spawners);
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("worth")) {
                        if (player.hasPermission("m4m.command.mk.worth")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Integer lowWorth = mm.get(j).lowWorth;
                                    Integer highWorth = mm.get(j).highWorth;
                                    if (lowWorth.equals(highWorth)) {
                                        player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                " are worth " + ChatColor.GOLD + "$" + lowWorth.toString());
                                    } else {
                                        player.sendMessage(ChatColor.GOLD +mobName + "s" + ChatColor.GREEN + " are worth between "
                                                + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " and " +
                                                ChatColor.GOLD + "$" + highWorth.toString());
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    } else if (args[0].equalsIgnoreCase("drops")) {
                        if (player.hasPermission("m4m.command.mk.drops")) {
                            ItemModel im = new ItemModel();
                            for (int k = 0; k < mm.size(); k++) {
                                if (args[1].equalsIgnoreCase(mm.get(k).mobName)) {
                                    String mobName = mm.get(k).mobName;
                                    if (Boolean.TRUE.equals(mm.get(k).getCustomDrops())) {
                                        if (mm.get(k).getItems().size() == 0) {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " don't have any custom drops set");
                                        }
                                        for (int l = 0; l < mm.get(k).getItems().size(); l++) {
                                            String itemName = mm.get(k).getItems().get(l).getItemName();
                                            Integer amount = mm.get(k).getItems().get(l).getAmount();
                                            Integer chance = mm.get(k).getItems().get(l).getChance();
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " have a " +
                                                    ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " of dropping " + ChatColor.GOLD + amount +
                                                    " " + itemName);
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.GREEN + "Custom drops are not enabled for " + ChatColor.GOLD + mobName + "s.");
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleCustomDrops")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Boolean customDrops = MobCfgm.mobsCfg.getBoolean("mobs." + mobName + ".customDrops");
                                    if (Boolean.TRUE.equals(customDrops)){
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".customDrops", false);
                                        mm.get(j).setCustomDrops(false);
                                        customDrops = false;
                                    }
                                    else {
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mm.get(j).setCustomDrops(true);
                                        customDrops = true;
                                    }
                                    try {
                                        player.sendMessage(ChatColor.GREEN + "Custom drops for " + ChatColor.GOLD + mobName + "s " + ChatColor.GREEN + "set to " + ChatColor.GOLD + customDrops);
                                        mobsCfg.save(pFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleDefaultDrops")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Boolean defaultDrops = MobCfgm.mobsCfg.getBoolean("mobs." + mobName + ".defaultDrops");
                                    if (Boolean.TRUE.equals(defaultDrops)){
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".defaultDrops", false);
                                        mm.get(j).setCustomDrops(false);
                                        defaultDrops = false;
                                    }
                                    else {
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".defaultDrops", true);
                                        mm.get(j).setCustomDrops(true);
                                        defaultDrops = true;
                                    }
                                    try {
                                        player.sendMessage(ChatColor.GREEN + "Default drops for " + ChatColor.GOLD + mobName + "s " + ChatColor.GREEN + "set to " + ChatColor.GOLD + defaultDrops);
                                        mobsCfg.save(pFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("setLowWorth")) {
                        if (player.hasPermission("m4m.command.mk.setLowWorth")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Integer highWorth = mm.get(j).getHighWorth();
                                    if (highWorth >= Integer.parseInt(args[2])) {
                                        mm.get(j).setHighWorth(Integer.parseInt(args[2]));
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".worth.low", Integer.parseInt(args[2]));
                                        try {
                                            player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "High worth for " + mobName + "s is lower than the value you are setting");
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("setHighWorth")) {
                        if (player.hasPermission("m4m.command.mk.setHighWorth")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Integer lowWorth = mm.get(j).getLowWorth();
                                    if (lowWorth <= Integer.parseInt(args[2])) {
                                        mm.get(j).setHighWorth(Integer.parseInt(args[2]));
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".worth.high", Integer.parseInt(args[2]));

                                        try {
                                            player.sendMessage(ChatColor.GREEN + "High worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Low worth for " + mobName + "s is higher than the value you are setting");
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("removeCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.removeCustomDrop")) {
                            ItemModel item = new ItemModel();
                            List<ItemModel> itemList = new ArrayList<>();
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    for (int k = 0; k < mm.get(j).getItems().size(); k++) {
                                        itemList.add(new ItemModel(mm.get(j).getItems().get(k).getItemName(),mm.get(j).getItems().get(k).getAmount(), mm.get(j).getItems().get(k).getChance() ));
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".drops.item-" + (k + 1), null);
                                        try {
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    for (int k = 0; k < itemList.size(); k++){
                                        if(args[2].equalsIgnoreCase(itemList.get(k).getItemName())) {
                                            itemList.remove(k);
                                        }
                                    }
                                    int counter = 1;
                                    for (int k = 0; k < itemList.size(); k++){
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".name", itemList.get(k).getItemName());
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".amount", itemList.get(k).getAmount());
                                        MobCfgm.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".chance", itemList.get(k).getChance());
                                        counter++;
                                    }
                                    mm.get(j).getItems().clear();
                                    mm.get(j).setItems(itemList);

                                    try {
                                        player.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " drops for " + ChatColor.GOLD + mobName + "s ");
                                        mobsCfg.save(pFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.addCustomDrop")) {
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    Material m = Material.valueOf(args[2]);
                                    for (int k = 0; k < materials.length; k++){
                                        int counter = 0;
                                        if(materials[k].equals(m)){
                                            counter++;
                                        }
                                        if (counter != 0) {
                                            int amount = Integer.parseInt(args[3]);
                                            int chance = Integer.parseInt(args[4]);
                                            int counter2 = 0;
                                            List<ItemModel> im = new ArrayList<ItemModel>();
                                            List<ItemModel> mobItems;
                                            for (int l = 0; l < mm.get(j).getItems().size(); l++){
                                                String currentItemName = mm.get(j).getItems().get(l).getItemName();
                                                int currentItemAmount = mm.get(j).getItems().get(l).getAmount();
                                                int currentItemChance = mm.get(j).getItems().get(l).getChance();
                                                im.add(new ItemModel(currentItemName, currentItemAmount, currentItemChance));
                                                counter2++;
                                            }
                                            im.add(new ItemModel(args[2], amount, chance));
                                            mm.get(j).setItems(im);
                                            counter2++;
                                            MobCfgm.mobsCfg.set("mobs." + mm.get(j).getMobName() + ".drops.item-" + counter2 + ".name", args[2]);
                                            MobCfgm.mobsCfg.set("mobs." + mm.get(j).getMobName() + ".drops.item-" + counter2 + ".amount", Integer.parseInt(args[3]));
                                            MobCfgm.mobsCfg.set("mobs." + mm.get(j).getMobName() + ".drops.item-" + counter2 + ".chance", Integer.parseInt(args[4]));
                                            try {
                                                player.sendMessage(ChatColor.GREEN + "Added " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " to " + ChatColor.GOLD +
                                                        mm.get(j).getMobName() + ChatColor.GREEN + " drops with a " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "of dropping." );
                                                mobsCfg.save(pFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                }
            }

        }
        return true;
    }
}
