package Latch.Money4Mobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MkCommand implements CommandExecutor {
    private static final FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    private static final File mobsFile = MobConfigManager.mobsFile;
    private static final File userFile = UserManager.usersFile;
    private static final Material[] materials = Material.values();
    private static List<UserModel> um = UserManager.updateUsersOnReload();
    private static Boolean showMessage = true;
    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);

    // Config file paths Constants
    private static final String SHOW_MESSAGE = ".showMessage";
    private static final String USERS_USER = "users.user-";
    private static final String DEFAULT_LANGUAGE = "defaultLanguage";

    // Language Constants
    private static final String ENGLISH = "English";
    private static final String FRENCH = "French";
    private static final String SPANISH = "Spanish";
    private static final String CHINESE_SIMPLIFIED = "Chinese_Simplified";
    private static final String CHINESE_TRADITIONAL = "Chinese_Traditional";
    private static final String HINDI = "Hindi";
    private static final String ITALIAN = "Italian";
    private static final String GERMAN = "German";
    private static final String RUSSIAN = "Russian";


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        CommandSender player = commandSender;
        Player player2 = null;
        if (commandSender instanceof Player ){
            player2 = (Player) commandSender;
        }
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        int firstCounter = 1;
        for(String firstUsers : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            firstCounter++;
        }
        if (player2 == null){
            firstCounter = 1;
        }
        String language = MobConfigManager.mobsCfg.getString(DEFAULT_LANGUAGE);
        for(int i = 1; i < firstCounter+1; i++) {

            String firstUserId = UserManager.usersCfg.getString(USERS_USER + i + ".userId");
            assert firstUserId != null;
            if (commandSender instanceof ConsoleCommandSender || player2.getUniqueId().toString().equals(firstUserId)) {
                if (player2 != null){
                    if(firstUserId.equalsIgnoreCase(player2.getUniqueId().toString())){
                        showMessage = UserManager.usersCfg.getBoolean(USERS_USER + i + SHOW_MESSAGE);
                        language = UserManager.usersCfg.getString(USERS_USER + i + ".language");
                    }
                }
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("toggleKM")) {
                        if (player2.hasPermission("m4m.command.mk.toggleKM") || player.isOp()) {
                            if (language == null){
                                language = ENGLISH;
                            }
                            if (Boolean.TRUE.equals(showMessage)) {
                                if (language.equalsIgnoreCase(FRENCH)) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "désactivé.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "desactivado.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller 信息 " + ChatColor.GOLD + "关。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.GREEN + "擊殺訊息  關閉.");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller संदेश " + ChatColor.GOLD + "बंद।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.GREEN + "I messaggi di Mobkiller sono " + ChatColor.GOLD + "off.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller Nachricht" + ChatColor.GOLD + "aus.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Сообщения плагина Mobkiller" + ChatColor.GOLD + "отключены.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + "off.");
                                }
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, false);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (language.equalsIgnoreCase(FRENCH)) {
                                    player.sendMessage(ChatColor.GREEN + "Message MobKiller " + ChatColor.GOLD + "activé.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.GREEN + "Mensaje de Mobkiller " + ChatColor.GOLD + "en.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller 信息 " + ChatColor.GOLD + "上。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.GREEN + "擊殺訊息  開啟.");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller संदेश " + ChatColor.GOLD + "पर।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.GREEN + "I messaggi di Mobkiller sono " + ChatColor.GOLD + "on.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.GREEN + "Mobkiller Nachricht" + ChatColor.GOLD + "an.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Сообщения плагина Mobkiller" + ChatColor.GOLD + "отключены.");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "MobKiller message " + ChatColor.GOLD + "on.");
                                }
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, true);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl.");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "false";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "falso";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "假";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "असत्य";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "disattivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "falsch";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "выключен";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawneggs", true);
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "vrai";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "verdadero";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "真正";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "सच";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "attivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "wahr";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "включён";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules engendrées avec des œufs est défini sur " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.GREEN + "El dinero recompensado por las turbas generadas con huevos se establece en " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.GREEN + "从产卵的小怪那里获得的奖励被设置为 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.GREEN + "重生蛋生出的生物掉落金錢設置成 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.GREEN + "अंडे के साथ पैदा होने वाले मोब्स से पुरस्कृत धन निर्धारित है " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.GREEN + "I soldi che verranno ricevuti dalle uova sono " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.GREEN + "Geld, das von mit Eiern hervorgebrachten Mobs belohnt wird, wird auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Денежное вознаграждение за мобов заспавленных яйцами призыва " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
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
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "false";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "falso";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "假";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "असत्य";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "disattivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "falsch";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "выключен";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("spawners", true);
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "vrai";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "verdadero";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "真正";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "सच";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "attivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "wahr";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "включён";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par les foules de géniteurs est fixé à " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.GREEN +  "El dinero recompensado por las turbas generadoras se establece en " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                    player.sendMessage(ChatColor.GREEN + "产卵小怪奖励的钱被设置为 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                    player.sendMessage(ChatColor.GREEN + "生怪磚生出的生物掉落金錢設置成 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.GREEN + "स्पॉर्नर मॉब से पुरस्कृत धनराशि निर्धारित की जाती है " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.GREEN + "I soldi che verranno ricevuti dagli spawner sono " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.GREEN + "Geld, das von Spawner-Mobs belohnt wird, wird auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Денежная награда от создателей мобов установлена \u200B\u200Bв размере " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
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
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromTamedWolves") || player.isOp()) {
                            boolean tamedWolvesGiveMoney = MobConfigManager.mobsCfg.getBoolean("tamedWolvesGiveMoney");
                            if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                                MobConfigManager.mobsCfg.set("tamedWolvesGiveMoney", false);
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "false";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "falso";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "假";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "असत्य";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "disattivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "falsch";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "выключен";
                                }
                                else {
                                    bool = "false";
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("tamedWolvesGiveMoney", true);
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "vrai";
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "verdadero";
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "真正";
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "सच";
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "attivi";
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "wahr";
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "включён";
                                }
                                else {
                                    bool = "true";
                                }
                            }
                            try {
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.GREEN + "L'argent récompensé par des foules tuées par des loups apprivoisés" +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.GREEN +  "Dinero recompensado por turbas asesinadas por lobos domesticados " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                    player.sendMessage(ChatColor.GREEN + "从被驯服的狼杀死的小怪那里获得的奖励 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                    player.sendMessage(ChatColor.GREEN + "從被馴服的狼殺死的小怪中獲得的獎勵 " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.GREEN + "नामित भेड़ियों द्वारा मारे गए मॉब से पुरस्कृत धन को सेट किया गया " +
                                            ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Denaro ricompensato da mob uccisi da lupi addomesticati impostati " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.GREEN + "Geld, das von Mobs belohnt wird, die von gezähmten Wölfen getötet wurden " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.GREEN + "Деньги, полученные от мобов, убитых прирученными волками, установленными в " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                else {
                                    player.sendMessage(ChatColor.GREEN + "Money rewarded from mobs killed by tamed wolves set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                }
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("reload")) {
                        String bool = "";

                        if (player.hasPermission("m4m.command.mk.reload") || player.isOp()) {
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.AQUA + "Recharger Money4Mobs.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.AQUA + "Recarga de Money4Mobs.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.AQUA + "重新加载Money4Mobs。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.AQUA + "重新加載Money4Mobs。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.AQUA + "Money4Mobs को पुनः लोड करना।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.AQUA + "Ricaricamento di Money4Mobs.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.AQUA + "Money4Mobs neu laden.");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.AQUA + "Перезагрузка Money4Mobs.");
                            }
                            else {
                                player.sendMessage(ChatColor.AQUA + "Reloading M4M");
                            }
                            plugin.getPluginLoader().disablePlugin(plugin);
                            mm.clear();
                            mm = MobConfigManager.getMobModelFromConfig();
                            um.clear();
                            um = UserManager.updateUsersOnReload();
                            plugin.getPluginLoader().enablePlugin(plugin);
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.GOLD + "Le rechargement de Money4Mobs est terminé.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.GOLD + "Recarga de Money4Mobs completa.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.GOLD + "Money4Mobs重新加载完成。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.GOLD + "Money4Mobs重新加載完成。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.GOLD + "Money4Mobs पुनः लोड पूरा करें।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.GOLD + "Ricarica Money4Mobs completata.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.GOLD + "Money4Mobs Reload abgeschlossen.");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.GOLD + "Перезагрузка Money4Mobs завершена.");
                            }
                            else {
                                player.sendMessage(ChatColor.GOLD + "Money4Mobs Reload Complete.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleCustomKM")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleCustomKM") || player.isOp()) {
                            boolean customKillMessage = MobConfigManager.mobsCfg.getBoolean("customMessageOption.overrideKillMessage");
                            if (Boolean.TRUE.equals(customKillMessage)) {
                                MobConfigManager.mobsCfg.set("customMessageOption.overrideKillMessage", false);
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH) ){
                                    bool = "false";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage mis à " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "falso";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage ajustado a " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "假";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage 設置 " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "असत्य";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage करने के लिए सेट " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "disattivi";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage impostato " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "falsch";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage einstellen " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "выключен";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage установлен в " + ChatColor.GOLD + bool);
                                }
                                else {
                                    bool = "false";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage set to " + ChatColor.GOLD + bool);
                                }
                            } else {
                                MobConfigManager.mobsCfg.set("customMessageOption.overrideKillMessage", true);
                                if (language.equalsIgnoreCase(FRENCH)){
                                    bool = "vrai";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage mis à " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    bool = "verdadero";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage ajustado a " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) || language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    bool = "真正";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage 設置 " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    bool = "सच";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage करने के लिए सेट " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    bool = "attivi";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage impostato " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    bool = "wahr";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage einstellen " + ChatColor.GOLD + bool);
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    bool = "включён";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage установлен в " + ChatColor.GOLD + bool);
                                }
                                else {
                                    bool = "true";
                                    player.sendMessage(ChatColor.GREEN + "overrideKillMessage set to " + ChatColor.GOLD + bool);
                                }
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("worth")) {
                        if (player.hasPermission("m4m.command.mk.worth") || player.isOp()) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    Double lowWorth = mobModel.lowWorth;
                                    Double highWorth = mobModel.highWorth;
                                    assert language != null;
                                    if (lowWorth.equals(highWorth)) {
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + "." );
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 价值 " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "美元。" );
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 價值金額 " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "。" );
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ChatColor.GOLD + "$" +
                                                    lowWorth.toString() + ChatColor.GREEN + "लायक हैं।" );
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " ha un valore di " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "." );
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " sind " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "wert." );
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " стоят " + ChatColor.GOLD + lowWorth.toString() + ChatColor.GREEN + "." );
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " are worth " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                    } else {
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valent entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " et " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " valen entre " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " y " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 的价值在 " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " 到 " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "美元之间。");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    " 掉落金額 " + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " ~ " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN +
                                                    ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " और " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "के बीच का मूल्य है।");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ha un valore tra "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " e " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " sind zwischen "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " und " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + "wert.");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " стоят от "
                                                    + ChatColor.GOLD + "$" + lowWorth.toString() + ChatColor.GREEN + " до " +
                                                    ChatColor.GOLD + "$" + highWorth.toString() + ChatColor.GREEN + ".");
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
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                            if (language.equalsIgnoreCase(FRENCH)){
                                                player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " n'ont pas de set de gouttes personnalisé.");
                                            }
                                            else if (language.equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " no tiene ningún conjunto de gotas personalizado.");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 没有设置任何下落。");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                                player.sendMessage( ChatColor.GREEN + " 沒有設置自訂義掉落物品。");
                                            }
                                            else if (language.equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " में कोई ड्रॉप सेट नहीं है।");
                                            }
                                            else if (language.equalsIgnoreCase(ITALIAN)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " non ha un Drop presonalizzato.");
                                            }
                                            else if (language.equalsIgnoreCase(GERMAN)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " haben keine benutzerdefinierten Drops festgelegt.");
                                            }
                                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                                player.sendMessage(ChatColor.GREEN + "у " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " нет никакого Пользовательского дропа.");
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
                                            if (language.equalsIgnoreCase(FRENCH)){
                                                player.sendMessage(ChatColor.GREEN + "Les " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ont " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " de chances de perdre " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.GREEN + "Los " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " tienen un " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " de probabilidad de soltar " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 恶魔有 " + ChatColor.GOLD + chance + "%" +
                                                        ChatColor.GREEN + " 的机会掉落 " + ChatColor.GOLD + amount + ChatColor.GREEN + " 个 " + ChatColor.GOLD + itemName +
                                                        ChatColor.GREEN + "。");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 有 " + ChatColor.GOLD + chance + "%" +
                                                        ChatColor.GREEN + " 機率掉落 " + ChatColor.GOLD + amount + itemName +
                                                        ChatColor.GREEN + "。");
                                            }
                                            else if (language.equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " में " + ChatColor.GOLD + amount + " " +
                                                        itemName + ChatColor.GREEN + " छोड़ने का " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " मौका है।");
                                            }
                                            else if (language.equalsIgnoreCase(ITALIAN)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " ha il " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " di chance che droppi " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(GERMAN)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " haben eine Chance von " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + ", " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + "fallen zu lassen.");
                                            }
                                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " имееют " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + ", " + ChatColor.GOLD + amount +
                                                        " шанс уронить " + itemName + ChatColor.GREEN + ".");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " have a " +
                                                        ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping " + ChatColor.GOLD + amount +
                                                        " " + itemName + ChatColor.GREEN + ".");
                                            }
                                        }
                                    } else {
                                        assert language != null;
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.GREEN + "Les largages personnalisés sont activés pour les  " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.GREEN + "Las gotas personalizadas están habilitadas para " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 未启用自定义放置。");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " 沒有開啟自訂義物品掉落。");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.GREEN + "कस्टम ड्रॉप " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " के लिए सक्षम नहीं हैं।");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.GREEN + "I Drop customizzati per " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " non sono abilitati.");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.GREEN + "Benutzerdefinierte Drops sind für " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " nicht aktiviert.");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)) {
                                            player.sendMessage(ChatColor.GREEN + "ользовательский дроп не включен для " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops are not enabled for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                        if (language.equalsIgnoreCase(FRENCH) ){
                                            bool = "false";
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            bool = "falso";
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                                language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            bool = "假";
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            bool = "असत्य";
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            bool = "disattivi";
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            bool = "falsch";
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            bool = "выключен";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mobModel.setCustomDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase(FRENCH) ){
                                            bool = "vrai";
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            bool = "verdadero";
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                                language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            bool = "真正";
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            bool = "सच";
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            bool = "attivi";
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            bool = "wahr";
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            bool = "включён";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.GREEN + "Abandons personnalisés pour " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "définis sur " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.GREEN + "Gotas personalizadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "的自定义掉落设置为 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "自訂義掉落物已設置為 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "के लिए कस्टम ड्रॉप्स " + ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.GREEN + "I Drop customizzati per " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sono ora " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.GREEN + "Benutzerdefinierte Drops für " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "werden auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.GREEN + "Пользовательский дроп для " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "был " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Custom drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if(Boolean.TRUE.equals(error)){
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                        if (language.equalsIgnoreCase(FRENCH) ){
                                            bool = "false";
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            bool = "falso";
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                                language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            bool = "假";
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            bool = "असत्य";
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            bool = "disattivi";
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            bool = "falsch";
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            bool = "выключен";
                                        }
                                        else {
                                            bool = "false";
                                        }
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                                        mobModel.setKeepDefaultDrops(true);
                                        assert language != null;
                                        if (language.equalsIgnoreCase(FRENCH) ){
                                            bool = "vrai";
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            bool = "verdadero";
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                                language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            bool = "真正";
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            bool = "सच";
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            bool = "attivi";
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            bool = "wahr";
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            bool = "включён";
                                        }
                                        else {
                                            bool = "true";
                                        }
                                    }
                                    try {
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.GREEN + "Les abandons par défaut pour les  " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sont définis sur " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.GREEN + "Gotas predeterminadas para " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "configuradas en " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "的默认丢弃设置为 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "的默認刪除設置為 " + ChatColor.GOLD + bool + ChatColor.GREEN + "。");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.GOLD + mobName + "s " + ChatColor.GREEN + "के लिए डिफ़ॉल्ट बूँदें " +
                                                    ChatColor.GOLD + bool + ChatColor.GREEN + "।");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.GREEN + "I Drop normali per " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "sono ora " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.GREEN + "Standardabfälle für " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "auf " + ChatColor.GOLD + bool + ChatColor.GREEN + " gesetzt.");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.GREEN + "По умолчанию для " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "дроп  " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.GREEN + "Default drops for " + ChatColor.GOLD + mobName + "s " +
                                                    ChatColor.GREEN + "set to " + ChatColor.GOLD + bool + ChatColor.GREEN + ".");
                                        }
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase("language")) {
                        if (player.hasPermission("m4m.command.mk.language") || player.isOp()) {
                            boolean success = false;
                            if(args[1].equalsIgnoreCase(ENGLISH) || args[1].equalsIgnoreCase(FRENCH) || args[1].equalsIgnoreCase(SPANISH)
                            || args[1].equalsIgnoreCase(CHINESE_SIMPLIFIED) || args[1].equalsIgnoreCase(HINDI) || args[1].equalsIgnoreCase(ITALIAN)
                                    || args[1].equalsIgnoreCase(GERMAN) || args[1].equalsIgnoreCase(CHINESE_TRADITIONAL) || args[1].equalsIgnoreCase(RUSSIAN)){
                                int counter = 1;
                                for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                                    String userId = UserManager.usersCfg.getString(USERS_USER + counter + ".userId");
                                    assert userId != null;
                                    if (player2 != null){
                                        if(userId.equalsIgnoreCase(player2.getUniqueId().toString())){
                                            assert language != null;
                                            if (args[1].equalsIgnoreCase(FRENCH)){
                                                player.sendMessage(ChatColor.GREEN + "Changement de la langue de en " + ChatColor.GOLD + "Français.");
                                            }
                                            else if (args[1].equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.GREEN + "Se cambió el idioma de Money4Mobs al " + ChatColor.GOLD + "español.");
                                            }
                                            else if (args[1].equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.GREEN + "将Money4Mobs语言更改为 " + ChatColor.GOLD + "中文（简体).");
                                            }
                                            else if (args[1].equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                player.sendMessage(ChatColor.GREEN + "已將Money4Mobs消息更改為 " + ChatColor.GOLD + "中文（繁體).");
                                            }
                                            else if (args[1].equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.GREEN + "बदलने के लिए Money4Mobs भाषा " + ChatColor.GOLD + "हिंदी");
                                            }
                                            else if (args[1].equalsIgnoreCase(ITALIAN)){
                                                player.sendMessage(ChatColor.GREEN + "Cambiato Money4Mobs alla lingua " + ChatColor.GOLD + "italiana.");
                                            }
                                            else if (args[1].equalsIgnoreCase(GERMAN)){
                                                player.sendMessage(ChatColor.GREEN + "Money4Mobs - Nachrichten wurden in " + ChatColor.GOLD + "Deutsch" +
                                                        ChatColor.GREEN + " geändert.");
                                            }
                                            else if (args[1].equalsIgnoreCase(RUSSIAN)){
                                                player.sendMessage(ChatColor.GREEN + "Изменены сообщения Money4Mobs на " + ChatColor.GOLD + "русский язык" +
                                                        ChatColor.GREEN + ".");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GREEN + "Changed Money4Mobs messages to " + ChatColor.GOLD + ENGLISH);
                                            }
                                            success = true;
                                            UserManager.usersCfg.set(USERS_USER + counter + ".language", args[1]);
                                            try {
                                                UserManager.usersCfg.save(userFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        counter++;
                                    }
                                }
                                if (Boolean.TRUE.equals(success)){
                                    for (UserModel user : um){
                                        if (player2 != null){
                                            if (user.getUserId().equalsIgnoreCase(player2.getUniqueId().toString())) {
                                                user.setLanguage(args[1]);
                                            }
                                        }
                                    }
                                }
                            }

                        } else {
                            assert language != null;
                            if (args[1].equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (args[1].equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (args[1].equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (args[1].equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You do not have access to this command.");
                            }
                        }
                    }
                    else if (args[0].equalsIgnoreCase(DEFAULT_LANGUAGE)) {
                        if (player.hasPermission("m4m.command.mk." + DEFAULT_LANGUAGE) || player.isOp()) {
                            try {
                                if(args[1].equalsIgnoreCase(ENGLISH) || args[1].equalsIgnoreCase(FRENCH) ||
                                        args[1].equalsIgnoreCase(SPANISH) || args[1].equalsIgnoreCase(CHINESE_SIMPLIFIED) ||
                                        args[1].equalsIgnoreCase(HINDI) || args[1].equalsIgnoreCase(ITALIAN)  ||
                                        args[1].equalsIgnoreCase(GERMAN) || args[1].equalsIgnoreCase(CHINESE_TRADITIONAL) ||
                                        args[1].equalsIgnoreCase(RUSSIAN))
                                {
                                    UserManager.updateUserDefaultLanguage(args[1]);
                                    MobConfigManager.updateDefaultLanguage(args[1]);
                                    if (args[1].equalsIgnoreCase(FRENCH)){
                                        player.sendMessage(ChatColor.GREEN + "Changement de la langue par défaut des messages Money4Mobs en " + ChatColor.GOLD + "Français" + ChatColor.GREEN + ".");
                                    }
                                    else if (args[1].equalsIgnoreCase(SPANISH)){
                                        player.sendMessage(ChatColor.GREEN + "Se cambió el idioma predeterminado de los mensajes de Money4Mobs a " + ChatColor.GOLD + "español" + ChatColor.GREEN + ".");
                                    }
                                    else if (args[1].equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                        player.sendMessage(ChatColor.GREEN + "将Money4Mobs消息的默认语言更改为 " + ChatColor.GOLD + "中文（简体)");
                                    }
                                    else if (args[1].equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                        player.sendMessage(ChatColor.GREEN + "將Money4Mobs消息的默認語言更改為 " + ChatColor.GOLD + "中文（繁體).");
                                    }
                                    else if (args[1].equalsIgnoreCase(HINDI)){
                                        player.sendMessage(ChatColor.GREEN + "करने के लिए Money4Mobs संदेशों की डिफ़ॉल्ट भाषा बदल गई " + ChatColor.GOLD + "हिंदी");
                                    }
                                    else if (args[1].equalsIgnoreCase(ITALIAN)){
                                        player.sendMessage(ChatColor.GREEN + "Modificata la lingua predefinita dei messaggi di Money4Mobs in " + ChatColor.GOLD + "italiana" + ChatColor.GREEN + ".");
                                    }
                                    else if (args[1].equalsIgnoreCase(GERMAN)){
                                        player.sendMessage(ChatColor.GREEN + "Die Standardsprache von Money4Mobs-Nachrichten wurde in geändert " + ChatColor.GOLD + "Deutsch" +
                                                ChatColor.GREEN + ".");
                                    }
                                    else if (args[1].equalsIgnoreCase(RUSSIAN)){
                                        player.sendMessage(ChatColor.GREEN + "Изменен язык сообщений Money4Mobs по умолчанию на " + ChatColor.GOLD + "русский" +
                                                ChatColor.GREEN + ".");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.GREEN + "Changed default language of Money4Mobs messages to " + ChatColor.GOLD + ENGLISH + ChatColor.GREEN + ".");
                                    }
                                }
                                else {
                                    String defaultLanguage = MobConfigManager.mobsCfg.getString(DEFAULT_LANGUAGE);
                                    if (defaultLanguage.equalsIgnoreCase(FRENCH)){
                                        player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas une langue valide.");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(SPANISH)){
                                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es un idioma válido.");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                        player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的语言。");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                        player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + "不是有效的語言。");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(HINDI)){
                                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भाषा नहीं है।");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(ITALIAN)){
                                        player.sendMessage(ChatColor.RED + "ERRORE: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è una lingua valida.");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(GERMAN)){
                                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " ist keine gültige Sprache.");
                                    }
                                    else if (defaultLanguage.equalsIgnoreCase(RUSSIAN)){
                                        player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не допустимый язык.");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "ERROR: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid language.");
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
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
                                    Double highWorth = mobModel.getHighWorth();
                                    try {
                                        if (highWorth >= Double.parseDouble(args[2])) {
                                            mobModel.setLowWorth(Double.parseDouble(args[2]));
                                            MobConfigManager.mobsCfg.set("mobs." + mobName + ".worth.low", Double.parseDouble(args[2]));
                                            try {
                                                assert language != null;
                                                if (language.equalsIgnoreCase(FRENCH)){
                                                    player.sendMessage(ChatColor.GREEN + "La faible valeur des " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(SPANISH)) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor bajo para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 的低值设置为 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 最低掉落金額為 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase(HINDI)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " के लिए कम मूल्य " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "निर्धारित किया गया है।");
                                                }
                                                else if (language.equalsIgnoreCase(ITALIAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Il minor valore per " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " e'' stato settato a " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(GERMAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Der niedrige Wert für " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " wurde auf " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " festgelegt.");
                                                }
                                                else if (language.equalsIgnoreCase(RUSSIAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Минимальная значение для " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " установлено " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " долларов.");
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "Low worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                MobConfigManager.mobsCfg.save(mobsFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            assert language != null;
                                            if (language.equalsIgnoreCase(FRENCH)) {
                                                player.sendMessage(ChatColor.RED + "Errrur: " + ChatColor.GRAY + "La valeur élevée des " + mobName + "s est inférieure à la valeur que vous définissez.");
                                            }
                                            else if (language.equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY +
                                                        "El valor alto para " + mobName + "s es menor que el valor que está estableciendo.");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.RED + "错误 " + ChatColor.GRAY + mobName + "s 的高价值低于您设置的价值。");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                player.sendMessage(ChatColor.RED + "錯誤 " + ChatColor.GRAY + mobName + "s 的最高掉落金額低於你設定的最低掉落金額.。");
                                            }
                                            else if (language.equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + mobName + "s के लिए उच्च मूल्य आपके द्वारा निर्धारित मूल्य से कम है।");
                                            }
                                            else if (language.equalsIgnoreCase(ITALIAN)) {
                                                player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Il valore elevato per " + mobName + "s è inferiore al valore che stai impostando.");
                                            }
                                            else if (language.equalsIgnoreCase(GERMAN)) {
                                                player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Der hohe Wert für " + mobName + "s ist niedriger als der von Ihnen eingestellte Wert.");
                                            }
                                            else if (language.equalsIgnoreCase(RUSSIAN)) {
                                                player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GRAY + "Высокое значение для " + mobName + "s ниже, чем значение, которое вы устанавливаете.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "High worth for " + mobName + "s is lower than the value you are setting.");
                                            }
                                        }
                                    } catch (NumberFormatException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "請像這樣輸入命令 -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GRAY + "Введите команду следующим образом -> /mk setLowWorth [mobName] [amount]");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setLowWorth [mobName] [amount]");
                                        }
                                    }

                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                    Double lowWorth = mobModel.getLowWorth();
                                    try {
                                        if (lowWorth <= Double.parseDouble(args[2])) {
                                            mobModel.setHighWorth(Double.parseDouble(args[2]));
                                            MobConfigManager.mobsCfg.set("mobs." + mobName + ".worth.high", Double.parseDouble(args[2]));
                                            try {
                                                assert language != null;
                                                if (language.equalsIgnoreCase(FRENCH)){
                                                    player.sendMessage(ChatColor.GREEN + "La valeur élevée pour " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " a été fixée à " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(SPANISH)) {
                                                    player.sendMessage(ChatColor.GREEN + "El valor alto para " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " se ha establecido en " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 的高价值已设置为 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " 最高掉落金額為 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "。");
                                                }
                                                else if (language.equalsIgnoreCase(HINDI)) {
                                                    player.sendMessage(ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " के लिए उच्च मूल्य " + ChatColor.GOLD + args[2] + ChatColor.GREEN + "निर्धारित किया गया है।");
                                                }
                                                else if (language.equalsIgnoreCase(ITALIAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Il maggior valore per " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " e'' stato settato a " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                else if (language.equalsIgnoreCase(GERMAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Der hohe Wert für " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " wurde auf " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " festgelegt.");
                                                }
                                                else if (language.equalsIgnoreCase(RUSSIAN)) {
                                                    player.sendMessage(ChatColor.GREEN + "Максимальное значение для " + ChatColor.GOLD + mobName + "s" +
                                                            ChatColor.GREEN + " установлено " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " долларов.");
                                                }
                                                else {
                                                    player.sendMessage(ChatColor.GREEN + "High worth for " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " has been set to " + ChatColor.GOLD + args[2] + ChatColor.GREEN + ".");
                                                }
                                                MobConfigManager.mobsCfg.save(mobsFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            assert language != null;
                                            if (language.equalsIgnoreCase(FRENCH)) {
                                                player.sendMessage(ChatColor.RED + "Errrur: " + ChatColor.GRAY + "La valeur faible pour les " + mobName +
                                                        "s est supérieure à la valeur que vous définissez.");
                                            }
                                            else if (language.equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY +
                                                        "El valor bajo para " + mobName + "s es mayor que el valor que está estableciendo.");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + mobName + "s 的低价值高于您设置的值。");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GRAY + mobName + "s 的最低掉落金額高於你設定的最高掉落金額。");
                                            }
                                            else if (language.equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + mobName + "s के लिए कम मूल्य आपके द्वारा निर्धारित मूल्य से अधिक है।");
                                            }
                                            else if (language.equalsIgnoreCase(ITALIAN)) {
                                                player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Il valore basso per " + mobName + "s è superiore al valore che stai impostando.");
                                            }
                                            else if (language.equalsIgnoreCase(GERMAN)) {
                                                player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Der niedrige Wert für " + mobName + "s ist höher als der von Ihnen eingestellte Wert.");
                                            }
                                            else if (language.equalsIgnoreCase(RUSSIAN)) {
                                                player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GRAY + "Низкое значение для " + mobName + "s ниже, чем значение, которое вы устанавливаете.");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Low worth for " + mobName +
                                                        "s is higher than the value you are setting.");
                                            }
                                        }
                                    } catch (NumberFormatException e){
                                        assert language != null;
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GRAY + "請像這樣輸入命令 -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GRAY + "Введите команду следующим образом -> /mk setHighWorth [mobName] [amount]");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Enter command like this -> /mk setHighWorth [mobName] [amount]");
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                            MobConfigManager.mobsCfg.save(mobsFile);
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
                                        if (language.equalsIgnoreCase(FRENCH)){
                                            player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Les gouttes " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'existent pas pour les " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase(SPANISH)){
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Las gotas de " +  ChatColor.GOLD + args[1] + ChatColor.GRAY + " no existen para las " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                            player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不存在 " + ChatColor.GOLD + mobName + "s " + ChatColor.GRAY + "滴。");
                                        }
                                        else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                            player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + mobName + ChatColor.GRAY + " 沒有這個自訂義掉落物品 " + ChatColor.GOLD + args[1] + ChatColor.GREEN + ".");
                                        }
                                        else if (language.equalsIgnoreCase(HINDI)){
                                            player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " के लिए " + ChatColor.GOLD + mobName + "s " + ChatColor.GRAY + "ड्रॉप मौजूद नहीं है।");
                                        }
                                        else if (language.equalsIgnoreCase(ITALIAN)){
                                            player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " drop non esistono per " + ChatColor.GOLD + mobName + "s.");
                                        }
                                        else if (language.equalsIgnoreCase(GERMAN)){
                                            player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " -Drops sind für " + ChatColor.GOLD + mobName + " nicht vorhanden.");
                                        }
                                        else if (language.equalsIgnoreCase(RUSSIAN)){
                                            player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " -Drops sind für " + ChatColor.GOLD + mobName + " nicht vorhanden.");
                                        }
                                        else {
                                            player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[2] + ChatColor.GRAY + " капли не существуют для " + ChatColor.GOLD + mobName + "s.");
                                        }
                                    } else {
                                        try {
                                            assert language != null;
                                            if (language.equalsIgnoreCase(FRENCH)){
                                                player.sendMessage(ChatColor.GREEN + "Suppression des gouttes " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " pour " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(SPANISH)){
                                                player.sendMessage(ChatColor.GREEN + "Se eliminaron las gotas de " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " para " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                player.sendMessage(ChatColor.GREEN + "删除了 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " 的 " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + "滴。");
                                            }
                                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                player.sendMessage(ChatColor.GREEN + "移除了 " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " 的 " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + "掉落物。");
                                            }
                                            else if (language.equalsIgnoreCase(HINDI)){
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " को " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + "के लिए हटा दिया जाता है।");
                                            }
                                            else if (language.equalsIgnoreCase(ITALIAN)){
                                                player.sendMessage(ChatColor.GREEN + "Hai rimosso " + ChatColor.GOLD + args[2] + ChatColor.GREEN + " dai drop per " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else if (language.equalsIgnoreCase(GERMAN)){
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " für " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + " wurden entfernt.");
                                            }
                                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " капли удалены из " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            else {
                                                player.sendMessage(ChatColor.GOLD + args[2] + ChatColor.GREEN + " drops removed from " + ChatColor.GOLD + mobName + "s" + ChatColor.GREEN + ".");
                                            }
                                            MobConfigManager.mobsCfg.save(mobsFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(mobError)) {
                                assert language != null;
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " is not a valid mob.");
                                }
                            }
                        } else {
                            assert language != null;
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl..");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
                                if (language.equalsIgnoreCase(FRENCH)){
                                    player.sendMessage(ChatColor.RED + "Erreur: impossible d'ajouter des gouttes personnalisées aux lecteurs.");
                                }
                                else if (language.equalsIgnoreCase(SPANISH)){
                                    player.sendMessage(ChatColor.RED + "Error: no se pueden agregar gotas personalizadas a los jugadores.");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                    player.sendMessage(ChatColor.RED + "错误：无法向播放器添加自定义放置。");
                                }
                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                    player.sendMessage(ChatColor.RED + "錯誤：無法向播放器添加自定義放置。");
                                }
                                else if (language.equalsIgnoreCase(HINDI)){
                                    player.sendMessage(ChatColor.RED + "त्रुटि: खिलाड़ियों को कस्टम ड्रॉप्स नहीं जोड़ सकते।");
                                }
                                else if (language.equalsIgnoreCase(ITALIAN)){
                                    player.sendMessage(ChatColor.RED + "Errore: impossibile aggiungere drop personalizzati ai giocatori.");
                                }
                                else if (language.equalsIgnoreCase(GERMAN)){
                                    player.sendMessage(ChatColor.RED + "Fehler: Spieler können keine benutzerdefinierten Drops hinzufügen.");
                                }
                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                    player.sendMessage(ChatColor.RED + "Ошибка: Spieler können keine benutzerdefinierten Drops hinzufügen.");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "Error: Невозможно добавить игрокам индивидуальные дропы.");
                                }
                            }
                            else {
                                    for (MobModel mobModel : mm) {
                                        if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                            try {
                                                int itemPresent = 0;
                                                for (int t = 0; t < mobModel.getItems().size(); t++) {
                                                    if (mobModel.getItems().get(t).getItemName().equalsIgnoreCase(args[2])) {
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
                                                                    if (language.equalsIgnoreCase(FRENCH)){
                                                                        player.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.GOLD + amount + " " + args[2] + " " +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " aux gouttes avec " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "de chances de tomber.");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(SPANISH)){
                                                                        player.sendMessage(ChatColor.GREEN + "Se agregaron " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " a " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " gotas con un " + ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "de probabilidad de caer.");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                                        player.sendMessage(ChatColor.GREEN + "为" + ChatColor.GOLD + "" + args[2] + " 掉落增加了 " + ChatColor.GOLD + amount + mobModel.getMobName() +
                                                                                ChatColor.GREEN + ", 掉落几率为 " + ChatColor.GOLD + chance + "%。");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                                        player.sendMessage(ChatColor.GREEN + "添加了 " + ChatColor.GOLD + amount + ChatColor.GREEN + " 個 " + args[2] + " 給 " + ChatColor.GOLD + mobModel.getMobName() +
                                                                                ChatColor.GREEN + "並且有 " + ChatColor.GOLD + chance + "% 機率掉落。");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(HINDI)){
                                                                        player.sendMessage(ChatColor.GOLD + "" + amount + " " + args[2] + " " + mobModel.getMobName() + ChatColor.GREEN + " बूँदें छोड़ने के " +
                                                                                ChatColor.GOLD + chance + "% " + ChatColor.GREEN + "संभावना के साथ जोड़ा गया।");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(ITALIAN)){
                                                                        player.sendMessage(ChatColor.GREEN + "Aggiunto " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " per " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " con la chance di drop impostata a " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + ".");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(GERMAN)){
                                                                        player.sendMessage(ChatColor.GREEN + "Es wurden " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " zu " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + "-Drops hinzugefügt, mit einer " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " igen Chance zu fallen.");
                                                                    }
                                                                    else if (language.equalsIgnoreCase(RUSSIAN)){
                                                                        player.sendMessage(ChatColor.GREEN + "Добавлено " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " для выпадения " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " с " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " шансом выпадения.");
                                                                    }
                                                                    else {
                                                                        player.sendMessage(ChatColor.GREEN + "Added " + ChatColor.GOLD + amount + " " + args[2] + ChatColor.GREEN + " to " + ChatColor.GOLD +
                                                                                mobModel.getMobName() + ChatColor.GREEN + " drops with a " + ChatColor.GOLD + chance + "%" + ChatColor.GREEN + " chance of dropping.");                                                        }
                                                                    MobConfigManager.mobsCfg.save(mobsFile);
                                                                } catch (IOException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            } catch (NumberFormatException e) {
                                                                assert language != null;
                                                                if (language.equalsIgnoreCase(FRENCH)){
                                                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GRAY + "Entrez une commande comme celle-ci -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(SPANISH)){
                                                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GRAY + "Ingrese un comando como este -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GRAY + "像这样输入命令 -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GRAY + "請像這樣輸入命令 -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(HINDI)){
                                                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GRAY + "इस तरह कमांड दर्ज करें -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(ITALIAN)){
                                                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GRAY + "Inserisci il comando come questo -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(GERMAN)){
                                                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GRAY + "Geben Sie den Befehl so ein -> /mk addCustomDrop [mobName] [amount] [chance]");
                                                                }
                                                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GRAY + "Введите команду следующим образом -> /mk addCustomDrop [mobName] [amount] [chance]");
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
                                                    if (language.equalsIgnoreCase(FRENCH)) {
                                                        player.sendMessage(ChatColor.RED + "Erreur:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " ya está presente como una gota personalizada.");
                                                    }
                                                    else if (language.equalsIgnoreCase(SPANISH)) {
                                                        player.sendMessage(ChatColor.RED + "Error:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " est déjà présent en tant que drop personnalisé.");
                                                    }
                                                    else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)) {
                                                        player.sendMessage(ChatColor.RED + "错误:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " 已作为自定义放置出现。");
                                                    }
                                                    else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)) {
                                                        player.sendMessage(ChatColor.RED + "錯誤:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " 已作為自定義放置出現。");
                                                    }
                                                    else if (language.equalsIgnoreCase(HINDI)) {
                                                        player.sendMessage(ChatColor.RED + "त्रुटि:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " पहले से ही कस्टम ड्रॉप के रूप में मौजूद है।");
                                                    }
                                                    else if (language.equalsIgnoreCase(ITALIAN)) {
                                                        player.sendMessage(ChatColor.RED + "Errore:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " e'' gia'' presente come drop personalizzato.");
                                                    }
                                                    else if (language.equalsIgnoreCase(GERMAN)) {
                                                        player.sendMessage(ChatColor.RED + "Fehler:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " ist bereits als benutzerdefiniertes Drop vorhanden.");
                                                    }
                                                    else if (language.equalsIgnoreCase(RUSSIAN)) {
                                                        player.sendMessage(ChatColor.RED + "Ошибка:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " уже присутствует в виде кастомного дропа.");
                                                    }
                                                    else {
                                                        player.sendMessage(ChatColor.RED + "Error:" + ChatColor.GOLD + args[2] + ChatColor.GRAY + " is already present as a custom drop.");
                                                    }
                                                }

                                            }
                                            catch (IllegalArgumentException e){
                                                assert language != null;
                                                if (language.equalsIgnoreCase(FRENCH)){
                                                    player.sendMessage(ChatColor.RED + "Erreur: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " n'est pas un mob valide.");
                                                }
                                                else if (language.equalsIgnoreCase(SPANISH)){
                                                    player.sendMessage(ChatColor.RED + "Error: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " no es una mafia válida.");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                                    player.sendMessage(ChatColor.RED + "错误: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的暴民。");
                                                }
                                                else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                                    player.sendMessage(ChatColor.RED + "錯誤: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " 不是有效的生物。");
                                                }
                                                else if (language.equalsIgnoreCase(HINDI)){
                                                    player.sendMessage(ChatColor.RED + "त्रुटि: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " एक मान्य भीड़ नहीं है।");
                                                }
                                                else if (language.equalsIgnoreCase(ITALIAN)){
                                                    player.sendMessage(ChatColor.RED + "Errore: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " non è un Mob valido.");
                                                }
                                                else if (language.equalsIgnoreCase(GERMAN)){
                                                    player.sendMessage(ChatColor.RED + "Fehler: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " sind keine gültigen Mobs.");
                                                }
                                                else if (language.equalsIgnoreCase(RUSSIAN)){
                                                    player.sendMessage(ChatColor.RED + "Ошибка: " + ChatColor.GOLD + args[1] + ChatColor.GRAY + " не является допустимым мобом.");
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
                            if (language.equalsIgnoreCase(FRENCH)){
                                player.sendMessage(ChatColor.RED + "Vous n'avez pas accès à cette commande.");
                            }
                            else if (language.equalsIgnoreCase(SPANISH)){
                                player.sendMessage(ChatColor.RED + "No tiene acceso a este comando.");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_SIMPLIFIED)){
                                player.sendMessage(ChatColor.RED + "您无权访问此命令。");
                            }
                            else if (language.equalsIgnoreCase(CHINESE_TRADITIONAL)){
                                player.sendMessage(ChatColor.RED + "你沒有使用該指令的權限。");
                            }
                            else if (language.equalsIgnoreCase(HINDI)){
                                player.sendMessage(ChatColor.RED + "आपके पास इस आदेश तक पहुंच नहीं है।");
                            }
                            else if (language.equalsIgnoreCase(ITALIAN)){
                                player.sendMessage(ChatColor.RED + "Non hai accesso a quel comando.");
                            }
                            else if (language.equalsIgnoreCase(GERMAN)){
                                player.sendMessage(ChatColor.RED + "Sie haben keinen Zugriff auf diesen Befehl.");
                            }
                            else if (language.equalsIgnoreCase(RUSSIAN)){
                                player.sendMessage(ChatColor.RED + "У вас нет доступа к этой команде.");
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
