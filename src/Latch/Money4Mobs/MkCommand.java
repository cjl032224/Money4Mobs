package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MkCommand implements CommandExecutor {
    FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    File pFile = MobConfigManager.mobsFile;
    private static final Material[] materials = Material.values();
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        List<Mobs4MoneyPlayer> playerList = Money4Mobs.getPlayerList();
        Player player = (Player) commandSender;
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        for (Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (player.getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggleKM")) {
                        if (player.hasPermission("m4m.command.mk.toggleKM")) {
                            if (Boolean.TRUE.equals(mobs4MoneyPlayer.getKillerMessage())) {
                                player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + " off");
                                mobs4MoneyPlayer.setKillerMessage(false);
                            } else {
                                player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + " on");
                                mobs4MoneyPlayer.setKillerMessage(true);
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs")) {
                            boolean spawnEgg = MobConfigManager.mobsCfg.getBoolean("spawneggs");
                            if (Boolean.TRUE.equals(spawnEgg)) {
                                MobConfigManager.mobsCfg.set("spawneggs", false);
                                spawnEgg = false;
                            } else {
                                MobConfigManager.mobsCfg.set("spawneggs", true);
                                spawnEgg = true;
                            }
                            try {
                                player.sendMessage(ChatColor.GREEN + "Money rewarded from mobs spawned with eggs is set to " + ChatColor.GOLD + spawnEgg);
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawners")) {
                            boolean spawners = MobConfigManager.mobsCfg.getBoolean("spawners");
                            if (Boolean.TRUE.equals(spawners)) {
                                MobConfigManager.mobsCfg.set("spawners", false);
                                spawners = false;
                            } else {
                                MobConfigManager.mobsCfg.set("spawners", true);
                                spawners = true;
                            }
                            try {
                                player.sendMessage(ChatColor.GREEN + "Money rewarded from spawner mobs is set to " + ChatColor.GOLD + spawners);
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("worth")) {
                        if (player.hasPermission("m4m.command.mk.worth")) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    Integer lowWorth = mobModel.lowWorth;
                                    Integer highWorth = mobModel.highWorth;
                                    if (lowWorth.equals(highWorth)) {
                                        player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                " are worth " + ChatColor.GOLD + "$" + lowWorth.toString());
                                    } else {
                                        player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " are worth between "
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
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    if (Boolean.TRUE.equals(mobModel.getCustomDrops())) {
                                        if (mobModel.getItems().size() == 0) {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " don't have any custom drops set");
                                        }
                                        for (int l = 0; l < mobModel.getItems().size(); l++) {
                                            String itemName = mobModel.getItems().get(l).getItemName();
                                            Integer amount = mobModel.getItems().get(l).getAmount();
                                            Integer chance = mobModel.getItems().get(l).getChance();
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " have a " +
                                                    ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping " + ChatColor.GOLD + amount +
                                                    " " + itemName);
                                        }
                                    } else {
                                        player.sendMessage(ChatColor.GREEN + "Custom drops are not enabled for " + ChatColor.GOLD + mobName + "s.");
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleCustomDrops")) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    error = false;
                                    boolean customDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".customDrops");
                                    if (Boolean.TRUE.equals(customDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", false);
                                        mobModel.setCustomDrops(false);
                                        customDrops = false;
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mobModel.setCustomDrops(true);
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
                            if(Boolean.TRUE.equals(error)){
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleDefaultDrops")) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".keepDefaultDrops");
                                    if (Boolean.TRUE.equals(defaultDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", false);
                                        mobModel.setKeepDefaultDrops(false);
                                        defaultDrops = false;
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                                        mobModel.setKeepDefaultDrops(true);
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
                            if (Boolean.TRUE.equals(error)) {
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("setLowWorth")) {
                        if (player.hasPermission("m4m.command.mk.setLowWorth")) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    Integer highWorth = mobModel.getHighWorth();
                                    try {
                                        if (highWorth >= Integer.parseInt(args[2])) {
                                            mobModel.setHighWorth(Integer.parseInt(args[2]));
                                            MobConfigManager.mobsCfg.set("mobs." + mobName + ".worth.low", Integer.parseInt(args[2]));
                                            try {
                                                player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                                mobsCfg.save(pFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "High worth for " + mobName + "s is lower than the value you are setting");
                                        }
                                    } catch (NumberFormatException e){
                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setLowWorth [mobName] [amount]");
                                    }

                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("setHighWorth")) {
                        if (player.hasPermission("m4m.command.mk.setHighWorth")) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    Integer lowWorth = mobModel.getLowWorth();
                                    try {
                                        if (lowWorth <= Integer.parseInt(args[2])) {
                                            mobModel.setHighWorth(Integer.parseInt(args[2]));
                                            MobConfigManager.mobsCfg.set("mobs." + mobName + ".worth.high", Integer.parseInt(args[2]));
                                            try {
                                                player.sendMessage(ChatColor.GREEN + "High worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                                mobsCfg.save(pFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Low worth for " + mobName + "s is higher than the value you are setting");
                                        }
                                    } catch (NumberFormatException e){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setHighWorth [mobName] [amount]");
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("removeCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.removeCustomDrop")) {
                            List<ItemModel> itemList = new ArrayList<>();
                            boolean itemError = true;
                            boolean mobError = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    mobError = false;
                                    for (int k = 0; k < mobModel.getItems().size(); k++) {
                                        itemList.add(new ItemModel(mobModel.getItems().get(k).getItemName(), mobModel.getItems().get(k).getAmount(), mobModel.getItems().get(k).getChance()));
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".drops.item-" + (k + 1), null);
                                        try {
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    for (int k = 0; k < itemList.size(); k++) {
                                        if (args[2].equalsIgnoreCase(itemList.get(k).getItemName())) {
                                            itemError = false;
                                            itemList.remove(k);
                                        }
                                    }
                                    int counter = 1;
                                    for (ItemModel itemModel : itemList) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".name", itemModel.getItemName());
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".amount", itemModel.getAmount());
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".drops.item-" + counter + ".chance", itemModel.getChance());
                                        counter++;
                                    }
                                    mobModel.getItems().clear();
                                    mobModel.setItems(itemList);
                                    if (Boolean.TRUE.equals(itemError)) {
                                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GOLD + args[2] + ChatColor.GRAY + " drops do not exist for " + ChatColor.GOLD + mobName + "s ");
                                    } else {
                                        try {
                                            player.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " drops for " + ChatColor.GOLD + mobName + "s ");
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(mobError)) {
                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.addCustomDrop")) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    Material m = Material.valueOf(args[2]);
                                    for (Material material : materials) {
                                        int counter = 0;
                                        if (material.equals(m)) {
                                            counter++;
                                        }
                                        if (counter != 0) {
                                            try {
                                                int amount = Integer.parseInt(args[3]);
                                                int chance = Integer.parseInt(args[4]);
                                                int counter2 = 0;
                                                List<ItemModel> im = new ArrayList<>();
                                                for (int l = 0; l < mobModel.getItems().size(); l++) {
                                                    String currentItemName = mobModel.getItems().get(l).getItemName();
                                                    int currentItemAmount = mobModel.getItems().get(l).getAmount();
                                                    int currentItemChance = mobModel.getItems().get(l).getChance();
                                                    im.add(new ItemModel(currentItemName, currentItemAmount, currentItemChance));
                                                    counter2++;
                                                }
                                                im.add(new ItemModel(args[2], amount, chance));
                                                mobModel.setItems(im);
                                                counter2++;
                                                MobConfigManager.mobsCfg.set("mobs." + mobModel.getMobName() + ".drops.item-" + counter2 + ".name", args[2]);
                                                MobConfigManager.mobsCfg.set("mobs." + mobModel.getMobName() + ".drops.item-" + counter2 + ".amount", Integer.parseInt(args[3]));
                                                MobConfigManager.mobsCfg.set("mobs." + mobModel.getMobName() + ".drops.item-" + counter2 + ".chance", Integer.parseInt(args[4]));
                                                try {
                                                    player.sendMessage(ChatColor.GREEN + "Added " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " to " + ChatColor.GOLD +
                                                            mobModel.getMobName() + ChatColor.GREEN + " drops with a " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "of dropping.");
                                                    mobsCfg.save(pFile);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            } catch (NumberFormatException e) {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk addCustomDrop [mobName] [amount] [chance]");
                                            }

                                        }
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                        }
                    }
                }
            }

        }
        return true;
    }
}
