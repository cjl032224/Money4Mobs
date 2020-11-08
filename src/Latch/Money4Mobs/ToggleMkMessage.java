package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class ToggleMkMessage implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        List<Mobs4MoneyPlayer> playerList = Money4Mobs.getPlayerList();
        org.bukkit.entity.Player player = (org.bukkit.entity.Player) commandSender;
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        for (int i = 0; i < playerList.size(); i++){
            if (player.getName().equals(playerList.get(i).getPlayerName())){
                if (args.length == 1) {
                    if (player.hasPermission("m4m.command.mk.toggleKM")){
                        if(args[0].equalsIgnoreCase("toggleKM")){
                            if(Boolean.TRUE.equals(playerList.get(i).getKillerMessage())){
                                player.sendMessage(ChatColor.GREEN + "MobKiller message off");
                                playerList.get(i).setKillerMessage(false);
                            }
                            else {
                                player.sendMessage(ChatColor.GREEN + "MobKiller message on");
                                playerList.get(i).setKillerMessage(true);
                            }
                        }
                    }
                } else if(args.length == 2) {
                    if(args[0].equals("worth")){
                        if (player.hasPermission("m4m.command.mk.worth")){
                            for (int j = 0; j < mm.size(); j++) {
                                if (args[1].equalsIgnoreCase(mm.get(j).mobName)) {
                                    String mobName = mm.get(j).mobName;
                                    Integer lowWorth = mm.get(j).lowWorth;
                                    Integer highWorth = mm.get(j).highWorth;
                                    if (lowWorth == highWorth) {
                                        player.sendMessage(mobName + "s are worth $" + lowWorth.toString());
                                    } else {
                                        player.sendMessage(mobName + "s are worth between $" + lowWorth.toString() + " and $" + highWorth.toString());
                                    }
                                }
                            }
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command");
                        }
                    }
                    if (args[0].equals("drops")){
                        if (player.hasPermission("m4m.command.mk.drops")){
                            ItemModel im = new ItemModel();
                            for (int k = 0; k < mm.size(); k++){
                                if(args[1].equalsIgnoreCase(mm.get(k).mobName)){
                                    String mobName = mm.get(k).mobName;
                                    if (Boolean.TRUE.equals(mm.get(k).getCustomDrops())){
                                        if (mm.get(k).getItems().size() == 0){
                                            player.sendMessage(mobName + "s don't have any custom drops set");
                                        }
                                        for (int l = 0; l < mm.get(k).getItems().size(); l++){
                                            String itemName = mm.get(k).getItems().get(l).getItemName();
                                            Integer amount = mm.get(k).getItems().get(l).getAmount();
                                            Integer chance = mm.get(k).getItems().get(l).getChance();
                                            player.sendMessage(mobName + "s have a " + chance + "% of dropping " + itemName);
                                        }
                                    }
                                    else {
                                        player.sendMessage("Custom drops are not enabled for " + mobName + "s.");
                                    }
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have access to this command");
                        }

                    }
                }
                else {
                    player.sendMessage(ChatColor.RED +("Error: ") + ChatColor.GRAY + ("Please use command like this -> " +ChatColor.DARK_GRAY + "/mk [on/off/worth]"));
                }
            }
        }
        return true;
    }
}
