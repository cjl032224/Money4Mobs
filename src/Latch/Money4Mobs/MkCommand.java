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
    private static FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    private static File mobsFile = MobConfigManager.mobsFile;
    private static FileConfiguration userCfg = UserManager.usersCfg;
    private static File userFile = UserManager.usersFile;
    private static final Material[] materials = Material.values();
    private static String language = "";
    private static List<UserModel> um = UserManager.getUserList();
    private static Boolean showMessage = true;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        int firstCounter = 1;
        for(String firstUsers : userCfg.getConfigurationSection("users").getKeys(false)) {
            String firstUserId = userCfg.getString("users.user-" + firstCounter + ".userId");
            assert firstUserId != null;
            if(firstUserId.equalsIgnoreCase(player.getUniqueId().toString())){
                showMessage = userCfg.getBoolean("users.user-" + firstCounter + ".showMessage");
                language = userCfg.getString("users.user-" + firstCounter + ".language");
            }
            firstCounter++;
            if (player.getUniqueId().toString().equals(firstUserId)) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggleKM")) {
                        if (player.hasPermission("m4m.command.mk.toggleKM") || player.isOp()) {
                            assert language != null;
                            if (Boolean.TRUE.equals(showMessage)) {
                                if (language.equalsIgnoreCase("French")) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "désactivé.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "desactivado.");
                                }
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller 信息 " + ChatColor.GOLD + "关。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller संदेश " + ChatColor.GOLD + "बंद।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.GREEN + "I messaggi di Mobkiller sono " + ChatColor.GOLD + "off.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller Nachricht" + ChatColor.GOLD + "aus.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + "off.");
                                }
                                userCfg.set("users.user-" + (firstCounter - 1) + ".showMessage", false);
                                try {
                                    userCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (language.equalsIgnoreCase("French")) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "activé.");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "en.");
                                }
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller 信息 " + ChatColor.GOLD + "上。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller संदेश " + ChatColor.GOLD + "पर।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.GREEN + "I messaggi di Mobkiller sono " + ChatColor.GOLD + "on.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller Nachricht" + ChatColor.GOLD + "an.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + "on.");
                                }
                                userCfg.set("users.user-" + (firstCounter - 1) + ".showMessage", true);
                                try {
                                    userCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs") || player.isOp()) {
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    bool = "假";
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    bool = "असत्य";
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    bool = "disattivi";
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    bool = "falsch";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawneggs", true);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "vrai";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "verdadero";
                                }
                                else if (language.equalsIgnoreCase("Chinese")){
                                    bool = "真正";
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    bool = "सच";
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    bool = "attivi";
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    bool = "wahr";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules engendrées avec des œufs est défini sur " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN + "El dinero recompensado por las turbas generadas con huevos se establece en " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.GREEN + "从产卵的小怪那里获得的奖励被设置为 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.GREEN + "अंडे के साथ पैदा होने वाले मोब्स से पुरस्कृत धन निर्धारित है " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.GREEN + "I soldi che verranno ricevuti dalle uova sono " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.GREEN + "Geld, das von mit Eiern hervorgebrachten Mobs belohnt wird, wird auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "Money rewarded from mobs spawned with eggs is set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                mobsCfg.save(mobsFile);
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawners") || player.isOp()) {
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    bool = "假";
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    bool = "असत्य";
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    bool = "disattivi";
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    bool = "falsch";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawners", true);
                                assert language != null;
                                if (language.equalsIgnoreCase("French") ){
                                    bool = "vrai";
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    bool = "verdadero";
                                }
                                else if (language.equalsIgnoreCase("Chinese")){
                                    bool = "真正";
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    bool = "सच";
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    bool = "attivi";
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    bool = "wahr";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase("French")){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules de géniteurs est fixé à " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("Spanish")){
                                    player.sendMessage(ChatColor.GREEN +  "El dinero recompensado por las turbas generadoras se establece en " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("Chinese")) {
                                    player.sendMessage(ChatColor.GREEN + "产卵小怪奖励的钱被设置为 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.GREEN + "स्पॉर्नर मॉब से पुरस्कृत धनराशि निर्धारित की जाती है " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.GREEN + "I soldi che verranno ricevuti dagli spawner sono " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.GREEN + "Geld, das von Spawner-Mobs belohnt wird, wird auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "Money rewarded from spawner mobs is set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                mobsCfg.save(mobsFile);
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("worth")) {
                        if (player.hasPermission("m4m.command.mk.worth") || player.isOp()) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    Integer lowWorth = mobModel.lowWorth;
                                    Integer highWorth = mobModel.highWorth;
                                    assert language != null;
                                    if (lowWorth.equals(highWorth)) {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + "." );
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 价值 " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "美元。" );
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ChatColor.GOLD + "$" +
                                                    lowWorth.toString() + ChatColor.GREEN + "लायक हैं।" );
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " ha un valore di " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "." );
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " sind " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "wert." );
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " are worth " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                    } else {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " et " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " y " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 的价值在 " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " 到 " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "美元之间。");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " और " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "के बीच का मूल्य है।");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ha un valore tra "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " e " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " sind zwischen "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " und " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "wert.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " are worth between "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " and " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("drops")) {
                        if (player.hasPermission("m4m.command.mk.drops") || player.isOp()) {
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
                                            else if (language.equalsIgnoreCase("Chinese")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 没有设置任何下落。");
                                            }
                                            else if (language.equalsIgnoreCase("Hindi")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " में कोई ड्रॉप सेट नहीं है।");
                                            }
                                            else if (language.equalsIgnoreCase("Italian")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " non ha un Drop presonalizzato.");
                                            }
                                            else if (language.equalsIgnoreCase("German")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " haben keine benutzerdefinierten Drops festgelegt.");
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
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " tienen un " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " de probabilidad de soltar " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("Chinese")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 恶魔有 " + ChatColor.GOLD + chance + "%" +
                                                        ChatColor.GREEN + " 的机会掉落 " + ChatColor.GOLD + amount + ChatColor.GREEN + " 个 " + ChatColor.GOLD + itemName +
                                                        ChatColor.GREEN + "。");
                                            }
                                            else if (language.equalsIgnoreCase("Hindi")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " में " + ChatColor.GOLD + amount + " " +
                                                        itemName + ChatColor.GREEN + " छोड़ने का " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " मौका है।");
                                            }
                                            else if (language.equalsIgnoreCase("Italian")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ha il " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " di chance che droppi " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("German")){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " haben eine Chance von " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + ", " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + "fallen zu lassen.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " have a " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                        }
                                    } else {
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les largages personnalisés sont activés pour les  " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Las gotas personalizadas están habilitadas para " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 未启用自定义放置。");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GREEN + "कस्टम ड्रॉप " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " के लिए सक्षम नहीं हैं।");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GREEN + "I Drop customizzati per " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " non sono abilitati.");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GREEN + "Benutzerdefinierte Drops sind für " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " nicht aktiviert.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops are not enabled for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleCustomDrops") || player.isOp()) {
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
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            bool = "假";
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            bool = "असत्य";
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            bool = "disattivi";
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            bool = "falsch";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mobModel.setCustomDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "vrai";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "verdadero";
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            bool = "真正";
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            bool = "सच";
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            bool = "attivi";
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            bool = "wahr";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Abandons personnalisés pour " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "définis sur " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Gotas personalizadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")) {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "的自定义掉落设置为 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "के लिए कस्टम ड्रॉप्स " + ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GREEN + "I Drop customizzati per " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sono ora " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GREEN + "Benutzerdefinierte Drops für " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "werden auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        mobsCfg.save(mobsFile);
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleDefaultDrops") || player.isOp()) {
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
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            bool = "假";
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            bool = "असत्य";
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            bool = "disattivi";
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            bool = "falsch";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                                        mobModel.setKeepDefaultDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French") ){
                                            bool = "vrai";
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            bool = "verdadero";
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            bool = "真正";
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            bool = "सच";
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            bool = "attivi";
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            bool = "wahr";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Les abandons par défaut pour les  " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sont définis sur " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Gotas predeterminadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "的默认丢弃设置为 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " + ChatColor.GREEN + "के लिए डिफ़ॉल्ट बूँदें " +
                                                    ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GREEN + "I Drop normali per " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sono ora " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GREEN + "Standardabfälle für " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Default drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        mobsCfg.save(mobsFile);
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("language")) {
                        if (player.hasPermission("m4m.command.mk.language") || player.isOp()) {
                            boolean success = false;
                            if(args[1].equalsIgnoreCase("English") || args[1].equalsIgnoreCase("French") || args[1].equalsIgnoreCase("Spanish")
                            || args[1].equalsIgnoreCase("Chinese") || args[1].equalsIgnoreCase("Hindi") || args[1].equalsIgnoreCase("Italian")
                                    || args[1].equalsIgnoreCase("German") ){
                                int counter = 1;
                                for(String users : userCfg.getConfigurationSection("users").getKeys(false)) {
                                    String userId = userCfg.getString("users.user-" + counter + ".userId");
                                    assert userId != null;
                                    if(userId.equalsIgnoreCase(player.getUniqueId().toString())){
                                        assert language != null;
                                        if (args[1].equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.GREEN + "Changement de la langue de en " + ChatColor.GOLD + "Français.");
                                        }
                                        else if (args[1].equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.GREEN + "Se cambió el idioma de Money4Mobs al " + ChatColor.GOLD + "español.");
                                        }
                                        else if (args[1].equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.GREEN + "将Money4Mobs语言更改为 " + ChatColor.GOLD + "中文");
                                        }
                                        else if (args[1].equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.GREEN + "बदलने के लिए Money4Mobs भाषा " + ChatColor.GOLD + "हिंदी");
                                        }
                                        else if (args[1].equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.GREEN + "Cambiato Money4Mobs alla lingua " + ChatColor.GOLD + "italiana.");
                                        }
                                        else if (args[1].equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.GREEN + "Money4Mobs - Nachrichten wurden in " + ChatColor.GOLD + "Deutsch" +
                                                    ChatColor.GREEN + " geändert.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Changed Money4Mobs messages to " + ChatColor.GOLD + "English");
                                        }
                                        success = true;
                                        userCfg.set("users.user-" + counter + ".language", args[1]);
                                        try {
                                            userCfg.save(userFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    counter++;
                                }
                                if (Boolean.TRUE.equals(success)){
                                    for (UserModel user : um){
                                        if (user.getUserId().equalsIgnoreCase(player.getUniqueId().toString())) {
                                            user.setLanguage(args[1]);
                                        }
                                    }
                                }
                            }

                        } else {
                            assert language != null;
                            if (args[1].equalsIgnoreCase("French")){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (args[1].equalsIgnoreCase("Spanish")){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (args[1].equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (args[1].equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("setLowWorth")) {
                        if (player.hasPermission("m4m.command.mk.setLowWorth") || player.isOp()) {
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
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("Spanish")) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor bajo para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("Chinese")) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 的低值设置为 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase("Hindi")) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " के लिए कम मूल्य " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "निर्धारित किया गया है।");
                                                }
                                                else if (language.equalsIgnoreCase("Italian")) {
                                                    player.sendMessage(ChatColor.GREEN + "Il minor valore per " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " e'' stato settato a " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("German")) {
                                                    player.sendMessage(ChatColor.GREEN + "Der niedrige Wert für " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " wurde auf " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " festgelegt.");
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                mobsCfg.save(mobsFile);
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
                                            else if (language.equalsIgnoreCase("Chinese")){
                                                player.sendMessage(ChatColor.RED + "错误 " + ChatColor.GRAY + mobName + "s 的高价值低于您设置的价值。");
                                            }
                                            else if (language.equalsIgnoreCase("Hindi")){
                                                player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + mobName + "s के लिए उच्च मूल्य आपके द्वारा निर्धारित मूल्य से कम है।");
                                            }
                                            else if (language.equalsIgnoreCase("Italian")) {
                                                player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Il valore elevato per " + mobName + "s è inferiore al valore che stai impostando.");

                                            }
                                            else if (language.equalsIgnoreCase("German")) {
                                                player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Der hohe Wert für " + mobName + "s ist niedriger als der von Ihnen eingestellte Wert.");

                                            }
                                            else {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "High worth for " + mobName + "s is lower than the value you are setting.");
                                            }
                                        }
                                    } catch (NumberFormatException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase("French")){
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk setLowWorth [mobName] [amount]");
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("setHighWorth")) {
                        if (player.hasPermission("m4m.command.mk.setHighWorth") || player.isOp()) {
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
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("Spanish")) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor alto para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("Chinese")) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 的高价值已设置为 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase("Hindi")) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " के लिए उच्च मूल्य " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "निर्धारित किया गया है।");
                                                }
                                                else if (language.equalsIgnoreCase("Italian")) {
                                                    player.sendMessage(ChatColor.GREEN + "Il maggior valore per " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " e'' stato settato a " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase("German")) {
                                                    player.sendMessage(ChatColor.GREEN + "Der hohe Wert für " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " wurde auf " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " festgelegt.");
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "High worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                mobsCfg.save(mobsFile);
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
                                            else if (language.equalsIgnoreCase("Chinese")){
                                                player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + mobName + "s 的低价值高于您设置的值。");
                                            }
                                            else if (language.equalsIgnoreCase("Hindi")){
                                                player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + mobName + "s के लिए कम मूल्य आपके द्वारा निर्धारित मूल्य से अधिक है।");
                                            }
                                            else if (language.equalsIgnoreCase("Italian")) {
                                                player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Il valore basso per " + mobName + "s è superiore al valore che stai impostando.");

                                            }
                                            else if (language.equalsIgnoreCase("German")) {
                                                player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Der niedrige Wert für " + mobName + "s ist höher als der von Ihnen eingestellte Wert.");

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
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk setHighWorth [mobName] [amount]");
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("removeCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.removeCustomDrop") || player.isOp()) {
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
                                            mobsCfg.save(mobsFile);
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
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Les gouttes " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'existent pas pour les " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase("Spanish")){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Las gotas de " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " no existen para las " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase("Chinese")){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不存在 " + ChatColor.GOLD + mobName + "s " + ChatColor.GRAY + "滴。");
                                        }
                                        else if (language.equalsIgnoreCase("Hindi")){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " के लिए " + ChatColor.GOLD + mobName + "s " + ChatColor.GRAY + "ड्रॉप मौजूद नहीं है।");
                                        }
                                        else if (language.equalsIgnoreCase("Italian")){
                                            player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " drop non esistono per " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase("German")){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " -Drops sind für " + ChatColor.GOLD + mobName + " nicht vorhanden.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[2] + ChatColor.GRAY + " drops do not exist for " + ChatColor.GOLD + mobName + "s.");
                                        }
                                    } else {
                                        try {
                                            assert language != null;
                                            if (language.equalsIgnoreCase("French")){
                                                player.sendMessage(ChatColor.GREEN + "Suppression des gouttes " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " pour " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("Spanish")){
                                                player.sendMessage(ChatColor.GREEN + "Se eliminaron las gotas de " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " para " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("Chinese")){
                                                player.sendMessage(ChatColor.GREEN + "删除了 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " 的 " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + "滴。");                                            }
                                            else if (language.equalsIgnoreCase("Hindi")){
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " को " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + "के लिए हटा दिया जाता है।");
                                            }
                                            else if (language.equalsIgnoreCase("Italian")){
                                                player.sendMessage(ChatColor.GREEN + "Hai rimosso " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " dai drop per " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase("German")){
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " für " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " wurden entfernt..");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " drops removed from " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            mobsCfg.save(mobsFile);
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
                                else if (language.equalsIgnoreCase("Chinese")){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase("Hindi")){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase("Italian")){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase("German")){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.addCustomDrop") || player.isOp()) {
                            if (args[1].equalsIgnoreCase("Player")){
                                player.sendMessage(ChatColor.RED + "Error: Cannot add Custom Drops to Players.");
                            }
                            else {
                                    for (MobModel mobModel : mm) {
                                        if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                            try {
                                                int itemPresent = 0;
                                                for (int i = 0; i < mobModel.getItems().size(); i++) {
                                                    if (mobModel.getItems().get(i).getItemName().equalsIgnoreCase(args[2])) {
                                                        itemPresent = 1;
                                                        break;
                                                    }
                                                }
                                                if (itemPresent == 0){
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
                                                                if(chance > 100){
                                                                    chance = 100;
                                                                }
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
                                                                MobConfigManager.mobsCfg.set("mobs." + mobModel.getMobName() + ".drops.item-" + counter2 + ".amount", amount);
                                                                MobConfigManager.mobsCfg.set("mobs." + mobModel.getMobName() + ".drops.item-" + counter2 + ".chance", chance);
                                                                try {
                                                                    assert language != null;
                                                                    if (language.equalsIgnoreCase("French")){
                                                                        player.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.GOLD + amount + " " + args[2] + " " +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " aux gouttes avec " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "de chances de tomber.");
                                                                    }
                                                                    else if (language.equalsIgnoreCase("Spanish")){
                                                                        player.sendMessage(ChatColor.GREEN + "Se agregaron " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " a " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " gotas con un " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "de probabilidad de caer.");
                                                                    }
                                                                    else if (language.equalsIgnoreCase("Chinese")){
                                                                        player.sendMessage(ChatColor.GREEN + "为" + ChatColor.GOLD + "" + args[2] + " 掉落增加了 " + ChatColor.GOLD + amount + mobModel.getMobName() +
                                                                                ChatColor.GREEN + ", 掉落几率为 " + ChatColor.GOLD + chance + "%。");
                                                                    }
                                                                    else if (language.equalsIgnoreCase("Hindi")){
                                                                        player.sendMessage(ChatColor.GOLD + "" + amount + " " + args[2] + " " + mobModel.getMobName() + ChatColor.GREEN + " बूँदें छोड़ने के " +
                                                                                ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "संभावना के साथ जोड़ा गया।");
                                                                    }
                                                                    else if (language.equalsIgnoreCase("Italian")){
                                                                        player.sendMessage(ChatColor.GREEN + "Aggiunto " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " per " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " con la chance di drop impostata a " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + ".");
                                                                    }
                                                                    else if (language.equalsIgnoreCase("German")){
                                                                        player.sendMessage(ChatColor.GREEN + "Es wurden " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " zu " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + "-Drops hinzugefügt, mit einer " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " igen Chance zu fallen.");
                                                                    }
                                                                    else {
                                                                        player.sendMessage(ChatColor.GREEN + "Added " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " to " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " drops with a " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping.");                                                        }
                                                                    mobsCfg.save(mobsFile);
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            } catch (NumberFormatException e) {
                                                                assert language != null;
                                                                if (language.equalsIgnoreCase("French")){
                                                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase("Spanish")){
                                                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase("Chinese")){
                                                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase("Hindi")){
                                                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase("Italian")){
                                                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase("German")){
                                                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else {
                                                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    assert language != null;
                                                    if (language.equalsIgnoreCase("French")) {
                                                        player.sendMessage(ChatColor.RED + "Erreur:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " ya está presente como una gota personalizada.");
                                                    }
                                                    else if (language.equalsIgnoreCase("Spanish")) {
                                                        player.sendMessage(ChatColor.RED + "Error:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " est déjà présent en tant que drop personnalisé.");
                                                    }
                                                    else if (language.equalsIgnoreCase("Chinese")) {
                                                        player.sendMessage(ChatColor.RED + "错误:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " 已作为自定义放置出现。");
                                                    }
                                                    else if (language.equalsIgnoreCase("Hindi")) {
                                                        player.sendMessage(ChatColor.RED + "त्रुटि:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " पहले से ही कस्टम ड्रॉप के रूप में मौजूद है।");
                                                    }
                                                    else if (language.equalsIgnoreCase("Italian")) {
                                                        player.sendMessage(ChatColor.RED + "Errore:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " e'' gia'' presente come drop personalizzato.");
                                                    }
                                                    else if (language.equalsIgnoreCase("German")) {
                                                        player.sendMessage(ChatColor.RED + "Fehler:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " ist bereits als benutzerdefiniertes Drop vorhanden.");
                                                    }
                                                    else {
                                                        player.sendMessage(ChatColor.RED + "Error:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " is already present as a custom drop.");
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
                                                else if (language.equalsIgnoreCase("Chinese")){
                                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                                }
                                                else if (language.equalsIgnoreCase("Hindi")){
                                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                                }
                                                else if (language.equalsIgnoreCase("Italian")){
                                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                                }
                                                else if (language.equalsIgnoreCase("German")){
                                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                                }
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
                            else if (language.equalsIgnoreCase("Chinese")){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase("Hindi")){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase("Italian")){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase("German")){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
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
