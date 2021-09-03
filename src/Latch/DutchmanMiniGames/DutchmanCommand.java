package Latch.DutchmanMiniGames;

import Latch.DutchmanMiniGames.Managers.UserManager;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class DutchmanCommand implements CommandExecutor {

    private static final File userFile = UserManager.usersFile;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = getPlayer(commandSender, null);

        try {
            if (command.getName().equalsIgnoreCase("tickets")){
                if (args.length == 0) {
                    for(String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (player != null){
                            if (player.getUniqueId().toString().equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userId"))){
                                player.sendMessage(ChatColor.GREEN + "Your ticket balance is " + ChatColor.GOLD + UserManager.usersCfg.getInt("users." + user + ".tickets"));
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("balance")) {
                    for (String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (args[1].equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userName"))) {
                            if (player != null) {
                                player.sendMessage(ChatColor.GOLD + UserManager.usersCfg.getString("users." + user + ".userName") + ChatColor.GREEN + " has " + ChatColor.GOLD + UserManager.usersCfg.getInt("users." + user + ".tickets") + ChatColor.GREEN + " tickets");
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("reset")){
                    for(String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (args[1].equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userName"))){
                            UserManager.usersCfg.set("users." + user + ".tickets", 0);
                            if (player != null) {
                                player.sendMessage(ChatColor.GREEN + "Reset " + ChatColor.GOLD +  UserManager.usersCfg.getString("users." + user + ".userName") + "'s " + ChatColor.GREEN + "ticket balance");
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("take")) {
                    for (String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (args[1].equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userName"))) {
                            int tickets = UserManager.usersCfg.getInt("users." + user + ".tickets");
                            if (Integer.parseInt(args[2]) <= tickets) {
                                tickets = tickets - Integer.parseInt(args[2]);
                                UserManager.usersCfg.set("users." + user + ".tickets", tickets);
                                if (player != null) {
                                    player.sendMessage(ChatColor.GREEN + "Took " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " tickets from " + ChatColor.GOLD + args[1]);
                                }
                            } else {
                                if (player != null) {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " does not have enough tickets to take");
                                }
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("give")){
                    for(String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (args[1].equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userName"))) {
                            int tickets = UserManager.usersCfg.getInt("users." + user + ".tickets");
                            tickets = tickets + Integer.parseInt(args[2]);
                            UserManager.usersCfg.set("users." + user + ".tickets", tickets);
                            if (player != null) {
                                player.sendMessage(ChatColor.GREEN + "Gave " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " tickets to " + ChatColor.GOLD + args[1]);
                            }
                        }
                    }
                } else if (args[0].equalsIgnoreCase("set")){
                    for(String user : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                        if (args[1].equalsIgnoreCase(UserManager.usersCfg.getString("users." + user + ".userName"))) {
                            UserManager.usersCfg.set("users." + user + ".tickets", Integer.parseInt(args[2]));
                            if (player != null) {
                                player.sendMessage(ChatColor.GREEN + "Set " + ChatColor.GOLD + UserManager.usersCfg.getString("users." + user + ".userName") + "'s " + ChatColor.GREEN + "ticket balance to " + args[2]);
                            }
                        }
                    }
                }
                UserManager.usersCfg.save(userFile);
            }

        }
        catch ( NullPointerException e ) {
            player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GRAY + "Player does not exist or name does not match capitalization" );
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | IOException e) {
            player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GRAY + "Ticket command expects parameters. Example-> " + ChatColor.AQUA + "/tickets [balance/give/take/reset/set]" );

        }

        if (command.getName().equalsIgnoreCase("dutchman") && args[0].equalsIgnoreCase("reload")){
            try {
                DutchmanMiniGames.loadUserConfigManager();
                DutchmanMiniGames.loadConfigFileManager();
                commandSender.sendMessage( ChatColor.GREEN + "Reloaded DutchmanMiniGames");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private Player getPlayer(CommandSender commandSender, Player player) {
        if (commandSender instanceof Player ){
            player = (Player) commandSender;
        }
        return player;
    }
}
