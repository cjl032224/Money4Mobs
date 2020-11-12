package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
        String language = MobConfigManager.mobsCfg.getString("language");
        for (Mobs4MoneyPlayer mobs4MoneyPlayer : playerList) {
            if (player.getName().equals(mobs4MoneyPlayer.getPlayerName())) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggleKM")) {
                        if (player.hasPermission("m4m.command.mk.toggleKM")) {
                            assert language != null;
                            if (Boolean.TRUE.equals(mobs4MoneyPlayer.getKillerMessage())) {
                                if (language.equalsIgnoreCase("French")) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "désactivé");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "desactivado");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + "off");
                                }
                                mobs4MoneyPlayer.setKillerMessage(false);
                            } else {
                                if (language.equalsIgnoreCase("French")) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "activé");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "en");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + " on");
                                }
                                mobs4MoneyPlayer.setKillerMessage(true);
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs")) {
                            boolean spawnEgg = MobConfigManager.mobsCfg.getBoolean("spawneggs");
                            if (Boolean.TRUE.equals(spawnEgg)) {
                                MobConfigManager.mobsCfg.set("spawneggs", false);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "false";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "falso";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawneggs", true);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "true";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "verdadero";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules engendrées avec des œufs est défini sur " +
                                            ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "El dinero recompensado por las turbas generadas con huevos se establece en " +
                                            ChatColor.GOLD + bool);
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "Money rewarded from mobs spawned with eggs is set to " + ChatColor.GOLD + bool);
                                }
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawners")) {
                            boolean spawners = MobConfigManager.mobsCfg.getBoolean("spawners");
                            if (Boolean.TRUE.equals(spawners)) {
                                MobConfigManager.mobsCfg.set("spawners", false);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "false";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "falso";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawners", true);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "true";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "verdadero";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules de géniteurs est fixé à " +
                                            ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN +  "El dinero recompensado por las turbas generadoras se establece en " +
                                            ChatColor.GOLD + bool);
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "Money rewarded from spawner mobs is set to " + ChatColor.GOLD + bool);
                                }
                                mobsCfg.save(pFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
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
                                    assert language != null;
                                    if (lowWorth.equals(highWorth)) {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent " + ChatColor.GOLD + "$" + lowWorth.toString());
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen " + ChatColor.GOLD + "$" + lowWorth.toString());
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " are worth " + ChatColor.GOLD + "$" + lowWorth.toString());
                                        }
                                    } else {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " et " +
                                                    ChatColor.GOLD + "$" + highWorth.toString());
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " y " +
                                                    ChatColor.GOLD + "$" + highWorth.toString());
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " are worth between "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " and " +
                                                    ChatColor.GOLD + "$" + highWorth.toString());
                                        }
                                    }
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
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
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")){
                                                player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " n'ont pas de set de gouttes personnalisé.");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " no tiene ningún conjunto de gotas personalizado.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " don't have any custom drops set.");
                                            }
                                        }
                                        for (int l = 0; l < mobModel.getItems().size(); l++) {
                                            String itemName = mobModel.getItems().get(l).getItemName();
                                            Integer amount = mobModel.getItems().get(l).getAmount();
                                            Integer chance = mobModel.getItems().get(l).getChance();
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")){
                                                player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ont " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " de chances de perdre " + ChatColor.GOLD + amount +
                                                        " " + itemName);
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " tienen un " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " de probabilidad de soltar " + ChatColor.GOLD + amount +
                                                        " " + itemName);
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " have a " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping " + ChatColor.GOLD + amount +
                                                        " " + itemName);
                                            }
                                        }
                                    } else {
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les largages personnalisés sont activés pour les  " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Las gotas personalizadas están habilitadas para " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops are not enabled for " + ChatColor.GOLD + mobName + "s.");
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleCustomDrops")) {
                            boolean error = true;
                            String bool = "";
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    error = false;
                                    boolean customDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".customDrops");
                                    if (Boolean.TRUE.equals(customDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", false);
                                        mobModel.setCustomDrops(false);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "false";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "falso";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mobModel.setCustomDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "true";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "verdadero";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Abandons personnalisés pour " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "définis sur " + ChatColor.GOLD + bool);
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Gotas personalizadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool);
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool);
                                        }
                                        mobsCfg.save(pFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if(Boolean.TRUE.equals(error)){
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleDefaultDrops")) {
                            boolean error = true;
                            String bool = "";
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".keepDefaultDrops");
                                    if (Boolean.TRUE.equals(defaultDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", false);
                                        mobModel.setKeepDefaultDrops(false);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "false";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "falso";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                                        mobModel.setKeepDefaultDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "true";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "verdadero";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les abandons par défaut pour les  " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sont définis sur " + ChatColor.GOLD + bool);
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Gotas predeterminadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool);
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Default drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool);
                                        }
                                        mobsCfg.save(pFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
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
                                            mobModel.setLowWorth(Integer.parseInt(args[2]));
                                            MobConfigManager.mobsCfg.set("mobs." + mobName + ".worth.low", Integer.parseInt(args[2]));
                                            try {
                                                assert language != null;
                                                if (language.equalsIgnoreCase("French")){
                                                    player.sendMessage(ChatColor.GREEN + "La faible valeur des " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2]);
                                                }
                                                else if (language.equalsIgnoreCase("Spanish")) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor bajo para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2]);
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                                }
                                                mobsCfg.save(pFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")) {
                                                player.sendMessage(ChatColor.RED + "Errrur: " + ChatColor.GRAY + "La valeur élevée des " + mobName + "s est inférieure à la valeur que vous définissez.");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY +
                                                        "El valor alto para " + mobName + "s es menor que el valor que está estableciendo.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "High worth for " + mobName + "s is lower than the value you are setting.");
                                            }
                                        }
                                    } catch (NumberFormatException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setLowWorth [mobName] [amount]");
                                        }
                                    }

                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
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
                                                assert language != null;
                                                if (language.equalsIgnoreCase("French")){
                                                    player.sendMessage(ChatColor.GREEN + "La valeur élevée pour " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2]);
                                                }
                                                else if (language.equalsIgnoreCase("Spanish")) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor alto para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2]);
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2]);
                                                }
                                                mobsCfg.save(pFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")) {
                                                player.sendMessage(ChatColor.RED + "Errrur: " + ChatColor.GRAY + "La valeur faible pour les " + mobName +
                                                        "s est supérieure à la valeur que vous définissez.");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY +
                                                        "El valor bajo para " + mobName + "s es mayor que el valor que está estableciendo.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Low worth for " + mobName +
                                                        "s is higher than the value you are setting.");
                                            }
                                        }
                                    } catch (NumberFormatException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setHighWorth [mobName] [amount]");
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
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
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Les gouttes " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'existent pas pour les " + ChatColor.GOLD + mobName + "s ");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Las gotas de " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " no existen para las " + ChatColor.GOLD + mobName + "s ");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[2] + ChatColor.GRAY + " drops do not exist for " + ChatColor.GOLD + mobName + "s ");
                                        }
                                    } else {
                                        try {
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")){
                                                player.sendMessage(ChatColor.GREEN + "Suppression des gouttes " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " pour " + ChatColor.GOLD + mobName + "s ");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.GREEN + "Se eliminaron las gotas de " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " para " + ChatColor.GOLD + mobName + "s ");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GREEN + "Removed " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " drops for " + ChatColor.GOLD + mobName + "s ");
                                            }
                                            mobsCfg.save(pFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(mobError)) {
                                assert language != null;
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.addCustomDrop")) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    try {
                                        Material m = Material.valueOf(args[2].toUpperCase());
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
                                                        assert language != null;
                                                        if (language.equalsIgnoreCase("French")){
                                                            player.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.GOLD + amount + " " + args[2] + " " +
                                                                    mobModel.getMobName() + ChatColor.GREEN + " aux gouttes asdasd avec " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + " de chances de tomber.");
                                                        }
                                                        else if (language.equalsIgnoreCase("Spanish")){
                                                            player.sendMessage(ChatColor.GREEN + "Se agregaron " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " a " + ChatColor.GOLD +
                                                                    mobModel.getMobName() + ChatColor.GREEN + " gotas con un " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + " de probabilidad de caer.");
                                                        }
                                                        else {
                                                            player.sendMessage(ChatColor.GREEN + "Added " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " to " + ChatColor.GOLD +
                                                                    mobModel.getMobName() + ChatColor.GREEN + " drops with a " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + " chance of dropping.");                                                        }
                                                        mobsCfg.save(pFile);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                } catch (NumberFormatException e) {
                                                    assert language != null;
                                                    if (language.equalsIgnoreCase("French")){
                                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                    }
                                                    else if (language.equalsIgnoreCase("Spanish")){
                                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                    }
                                                    else {
                                                        player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    catch (IllegalArgumentException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                        }
                                    }
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                }
            }

        }
        return true;
    }
}
