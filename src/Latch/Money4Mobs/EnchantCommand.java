package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("m4m.command.enc")) {
                // If the user only types '/enc'
                if(args.length == 0){
                    player.sendMessage(ChatColor.RED +("Error: ") + ChatColor.GRAY + ("Please use command like this -> " +ChatColor.DARK_GRAY + "/enc enchantment level"));
                }
                // If the user types '/enc [arg1]'
                else if (args.length == 1) {
                    Enchantment slip = Enchantment.getByKey(NamespacedKey.minecraft(args[0]));
                    // Sends player error message if they have improper enchant name or missing level parameter
                    try {
                        if(slip.getKey().getKey().equals(args[0])){
                            player.sendMessage(ChatColor.RED + ("Error - missing level: ") + ChatColor.GRAY + ("Please use command like this -> " + ChatColor.DARK_GRAY + "/enc enchantment level"));
                        }
                    }
                    catch (NullPointerException e) {
                        player.sendMessage(ChatColor.RED + ("Error - Enchantment not found: " + ChatColor.GRAY + ("Please use command like this -> " + ChatColor.DARK_GRAY + "/enc enchantment level")));
                    }
                }
                else if (args.length == 2){
                    Integer level = 0;
                    try {
                        level = Integer.parseInt(args[1]);
                        if (level <= 0 || level >= 32768){
                            player.sendMessage(ChatColor.RED + ("Error: ") + ChatColor.GRAY + ("Level may only be between 1 - 32767"));
                        }
                        else {
                            // Give the player our items (comma-separated list of all ItemStack)
                            player.getMainHand();
                            ItemStack is = player.getInventory().getItemInMainHand();
                            ItemMeta im = is.getItemMeta();
                            Enchantment slip = Enchantment.getByKey(NamespacedKey.minecraft(args[0]));
                            if (im != null) {
                                assert slip != null;
                                im.addEnchant(slip, level, true);
                                is.setItemMeta(im);
                            }
                            else {
                                player.sendMessage(ChatColor.RED +("Error: ") + ChatColor.GRAY + ("You must be holding an item in your main hand"));
                            }
                        }

                    } catch(NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + ("Error - incorrect level: ") + ChatColor.GRAY + ("level value must be a number"));
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED +("Error: ") + ChatColor.GRAY + ("Please use command like this -> " +ChatColor.DARK_GRAY + "/enc enchantment level"));
                    player.sendMessage(ChatColor.RED +("Do not give more than the 2 needed parameters"));
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have access to this command");
            }

        }

        return true;
    }


}
