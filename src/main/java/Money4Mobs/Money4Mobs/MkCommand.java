package Money4Mobs.Money4Mobs;

import Money4Mobs.Main;
import Money4Mobs.Configurations.ConfigFileManager;
import Money4Mobs.Configurations.MessagesConfigManager;
import Money4Mobs.Configurations.MobConfigManager;
import Money4Mobs.Configurations.MemberConfig;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MkCommand implements CommandExecutor {
    private static final FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    private static final File mobsFile = MobConfigManager.mobsFile;
    private static final FileConfiguration configCfg = ConfigFileManager.configCfg;
    private static final File configFile = ConfigFileManager.configFile;
    private static final File userFile = MemberConfig.membersFile;
    private static final Material[] materials = Material.values();
    private static List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
    private static Boolean showMessage = true;
    // Config file paths Constants
    private static final String SHOW_MESSAGE = ".showMessage";
    private static final String USERS_USER = "users.user-";
    private static final String ACCESS_DENIED_MESSAGE = ".accessDeniedMessage";
    private static final String SPAWN_EGGS = "spawneggs";
    private static final String SPAWNERS = "spawners";
    private static final String TAMED_WOLVES_GIVE_MONEY = "tamedWolvesGiveMoney";
    private static final String INVALID_MOB_ERROR_MESSAGE = ".invalidMobErrorMessage";
    private static final String MOBS = "mobs.";
    private static final String DROPS_ITEMS = ".drops.item-";
    private static final String CUSTOM_DROPS = ".customDrops";
    private static final String KEEP_DEFAULT_DROPS = ".keepDefaultDrops";
    private static final String LANGUAGE = "language.";
    private static final String LOCATION = ".location";
    private static final String MESSAGE = ".message";
    private static final String DEFAULT_LANGUAGE = configCfg.getString("defaultLanguage").toLowerCase();
    private static int userNumber;
    Logger logger = Logger.getLogger(MkCommand.class.getName());

    // Language Constants
    private static final String ENGLISH = "english";

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String language = null;
        Player player2 = null;
        player2 = getPlayer(commandSender, null);
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        int firstCounter = 1;
        for(String firstUsers : MemberConfig.membersCfg.getConfigurationSection("users").getKeys(false)) {
            firstCounter++;
        }
        ArrayList<String> testList = new ArrayList<>();
        for (String items : MessagesConfigManager.messagesCfg.getConfigurationSection("language").getKeys(false)){
            testList.add(items);
        }
        if (player2 == null){
            firstCounter = 1;
        }

        for(int i = 1; i < firstCounter+1; i++) {
            String firstUserId = MemberConfig.membersCfg.getString(USERS_USER + i + ".userId");
            assert firstUserId != null;
            if (commandSender instanceof ConsoleCommandSender || player2.getUniqueId().toString().equals(firstUserId)) {
                if (player2 != null) {
                    if (firstUserId.equalsIgnoreCase(player2.getUniqueId().toString())) {
                        showMessage = MemberConfig.membersCfg.getBoolean(USERS_USER + i + SHOW_MESSAGE);
                        language = MemberConfig.membersCfg.getString(USERS_USER + i + ".language");
                        userNumber = i;
                    }
                }
            }
        }
        if (!(player2 instanceof Player)){
            language = DEFAULT_LANGUAGE;
        }
        if (args.length == 0){
            commandErrorMessage(commandSender, language, ".incompleteCommandErrorMessage");
        }
        else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("worth")){
                commandErrorMessage(commandSender, language, ".worthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setHighWorth")){
                commandErrorMessage(commandSender, language, ".setHighWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setLowWorth")){
                commandErrorMessage(commandSender, language, ".setLowWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("drops")){
                commandErrorMessage(commandSender, language, ".dropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addCustomDrop")){
                commandErrorMessage(commandSender, language, ".addCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeCustomDrop")){
                commandErrorMessage(commandSender, language, ".removeCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleCustomDrops")){
                commandErrorMessage(commandSender, language, ".toggleCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")){
                commandErrorMessage(commandSender, language, ".toggleDefaultDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("language")){
                commandErrorMessage(commandSender, language, ".languageCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("mobRewardWorlds")){
                commandErrorMessage(commandSender, language, ".mobRewardWorldsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addWorld")){
                commandErrorMessage(commandSender, language, ".addWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeWorld")){
                commandErrorMessage(commandSender, language, ".removeWorldCommandErrorMessage");
            }
            else if (args[0].equalsIgnoreCase("toggleKM")) {
                if (player2 != null && (player2.hasPermission("m4m.command.mk.toggleKM") || commandSender.isOp())) {
                    if (language == null){
                        language = ENGLISH;
                    }
                    if (Boolean.TRUE.equals(showMessage)) {
                        String mobKillerOffMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language +  ".mobKillerOffMessage" + MESSAGE);
                        String mobKillerOffMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language +  ".mobKillerOffMessage" + LOCATION);
                        assert mobKillerOffMessage != null;
                        convertMessage(mobKillerOffMessage, commandSender, null, null, null, null, null, null, null, mobKillerOffMessageLocation, null);
                        MemberConfig.membersCfg.set(USERS_USER + userNumber + SHOW_MESSAGE, false);
                        try {
                            MemberConfig.membersCfg.save(userFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        String mobKillerOnMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobKillerOnMessage" + MESSAGE);
                        String mobKillerOnMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobKillerOnMessage" + LOCATION);
                        assert mobKillerOnMessage != null;
                        convertMessage(mobKillerOnMessage, commandSender, null, null, null, null, null, null, null, mobKillerOnMessageLocation, null);
                        MemberConfig.membersCfg.set(USERS_USER + userNumber + SHOW_MESSAGE, true);
                        try {
                            MemberConfig.membersCfg.save(userFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (player2 instanceof Player) {
                        String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                        String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                        assert accessDeniedMessage != null;
                        convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                    }
                }
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs") || commandSender.isOp()) {
                    boolean spawnEgg = configCfg.getBoolean(SPAWN_EGGS);
                    if (Boolean.TRUE.equals(spawnEgg)) {
                        configCfg.set(SPAWN_EGGS, false);
                        String eggSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".eggSpawnRewardFalseMessage" + MESSAGE);
                        String eggSpawnRewardFalseMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".eggSpawnRewardFalseMessage" + LOCATION);
                        assert eggSpawnRewardFalseMessage != null;
                        convertMessage(eggSpawnRewardFalseMessage, commandSender, null, null, null, null, null, null, null, eggSpawnRewardFalseMessageLocation, null);
                    } else {
                        configCfg.set(SPAWN_EGGS, true);
                        String eggSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".eggSpawnRewardTrueMessage" + MESSAGE);
                        String eggSpawnRewardTrueMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".eggSpawnRewardTrueMessage" + LOCATION);
                        assert eggSpawnRewardTrueMessage != null;
                        convertMessage(eggSpawnRewardTrueMessage, commandSender, null, null, null, null, null, null, null, eggSpawnRewardTrueMessageLocation, null);
                    }
                    try {
                        configCfg.save(configFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromSpawners") || commandSender.isOp()) {
                    boolean spawners = configCfg.getBoolean(SPAWNERS);
                    if (Boolean.TRUE.equals(spawners)) {
                        configCfg.set(SPAWNERS, false);
                        String spawnerSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".spawnerSpawnRewardFalseMessage" + MESSAGE);
                        String spawnerSpawnRewardFalseMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".spawnerSpawnRewardFalseMessage" + LOCATION);
                        assert spawnerSpawnRewardFalseMessage != null;
                        convertMessage(spawnerSpawnRewardFalseMessage, commandSender, null, null, null, null, null, null, null, spawnerSpawnRewardFalseMessageLocation, null);
                    } else {
                        configCfg.set(SPAWNERS, true);
                        String spawnerSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".spawnerSpawnRewardTrueMessage" + MESSAGE);
                        String spawnerSpawnRewardTrueMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".spawnerSpawnRewardTrueMessage" + LOCATION);
                        assert spawnerSpawnRewardTrueMessage != null;
                        convertMessage(spawnerSpawnRewardTrueMessage, commandSender, null, null, null, null, null, null, null, spawnerSpawnRewardTrueMessageLocation, null);
                    }
                    try {
                        configCfg.save(configFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            }
            else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")) {
                if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromTamedWolves") || commandSender.isOp()) {
                    boolean tamedWolvesGiveMoney = configCfg.getBoolean(TAMED_WOLVES_GIVE_MONEY);
                    if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                        configCfg.set(TAMED_WOLVES_GIVE_MONEY, false);
                        String tamedWolvesRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".tamedWolvesRewardFalseMessage" + MESSAGE);
                        String tamedWolvesRewardFalseMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".tamedWolvesRewardFalseMessage" + LOCATION);
                        assert tamedWolvesRewardFalseMessage != null;
                        convertMessage(tamedWolvesRewardFalseMessage, commandSender, null, null, null, null, null, null, null, tamedWolvesRewardFalseMessageLocation, null);
                    } else {
                        configCfg.set(TAMED_WOLVES_GIVE_MONEY, true);
                        String tamedWolvesRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".tamedWolvesRewardTrueMessage" + MESSAGE);
                        String tamedWolvesRewardTrueMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".tamedWolvesRewardTrueMessage" + LOCATION);
                        assert tamedWolvesRewardTrueMessage != null;
                        convertMessage(tamedWolvesRewardTrueMessage, commandSender, null, null, null, null, null, null, null, tamedWolvesRewardTrueMessageLocation, null);
                    }
                    try {
                        configCfg.save(configFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            }
            else if (args[0].equalsIgnoreCase("reload")) {
                if (commandSender.hasPermission("m4m.command.mk.reload") || commandSender.isOp()) {
                    try {
                        Main.reloadConfigFiles();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String reloadingMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".reloadingMessage" + MESSAGE);
                    String reloadingMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".reloadingMessage" + LOCATION);
                    assert reloadingMessage != null;
                    logger.log(Level.INFO, reloadingMessage.substring(2));
                    convertMessage(reloadingMessage, commandSender, null, null, null, null, null, null, null, reloadingMessageLocation, null);
                    mm.clear();
                    try {
                        Main.loadMobConfigManager();
                        Main.loadMemberCfg();
                        Main.loadLanguageConfigManager();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String reloadConfirmMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".reloadConfirmMessage" + MESSAGE);
                    String reloadConfirmMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".reloadConfirmMessage" + LOCATION);
                    assert reloadConfirmMessage != null;
                    logger.log(Level.INFO, reloadConfirmMessage.substring(2));
                    convertMessage(reloadConfirmMessage, commandSender, null, null, null, null, null, null, null, reloadConfirmMessageLocation, null);
                }
            } else {
                commandErrorMessage(commandSender, language, ".incompleteCommandErrorMessage");
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("setHighWorth")){
                commandErrorMessage(commandSender, language, ".setHighWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setLowWorth")){
                commandErrorMessage(commandSender, language, ".setLowWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addCustomDrop")){
                commandErrorMessage(commandSender, language, ".addCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeCustomDrop")){
                commandErrorMessage(commandSender, language, ".removeCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleKM")){
                commandErrorMessage(commandSender, language, ".toggleKMCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnEggsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnersCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("reload")){
                commandErrorMessage(commandSender, language, ".reloadCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromTamedWolvesCommandErrorMessage");
            }
            else if (args[0].equalsIgnoreCase("worth")) {
                if (commandSender.hasPermission("m4m.command.mk.worth") || commandSender.isOp()) {
                    boolean invalidMob = true;
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            invalidMob = false;
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            Double lowWorth = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + ".worth.low");
                            Double highWorth = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + ".worth.high");
                            assert language != null;
                            if (lowWorth.equals(highWorth)) {
                                String mobWorthMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobWorthMessage" + MESSAGE);
                                String mobWorthMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobWorthMessage" + LOCATION);
                                assert mobWorthMessage != null;
                                convertMessage(mobWorthMessage, commandSender, mobName, null, null, null, lowWorth.toString(), null, null, mobWorthMessageLocation, null);
                            } else {
                                String mobRangeWorthMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobRangeWorthMessage" + MESSAGE);
                                String mobRangeWorthMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobRangeWorthMessage" + LOCATION);
                                assert mobRangeWorthMessage != null;
                                convertMessage(mobRangeWorthMessage, commandSender, mobName, null, null, null, lowWorth.toString(), highWorth.toString(), null, mobRangeWorthMessageLocation, null);
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(invalidMob)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("addWorld")) {
                if (commandSender.hasPermission("m4m.command.mk.addWorld") || commandSender.isOp()) {
                    if (!MobConfigManager.mobsCfg.isSet("mobs.Creeper.worlds." + args[1])){
                        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                            MobConfigManager.mobsCfg.set("mobs." + mobObject + ".worlds." + args[1], true);
                        }
                        try {
                            MobConfigManager.mobsCfg.save(mobsFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    String addWorldSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addWorldSuccessMessage" + MESSAGE);
                    String addWorldSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addWorldSuccessMessage" + LOCATION);
                    assert addWorldSuccessMessage != null;
                    convertMessage(addWorldSuccessMessage, commandSender, null, null, null, null, null, null, null, addWorldSuccessMessageLocation, args[1]);
                }
            }
            else if (args[0].equalsIgnoreCase("removeWorld")) {
                if (commandSender.hasPermission("m4m.command.mk.removeWorld") || commandSender.isOp()) {
                    boolean isWorldPresent = false;
                    if (MobConfigManager.mobsCfg.isSet("mobs.Creeper.worlds." + args[1])){
                        isWorldPresent = true;
                        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                            MobConfigManager.mobsCfg.set("mobs." + mobObject + ".worlds." + args[1], null);
                        }
                        try {
                            MobConfigManager.mobsCfg.save(mobsFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String removeWorldSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeWorldSuccessMessage" + MESSAGE);
                        String removeWorldSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeWorldSuccessMessage" + LOCATION);
                        assert removeWorldSuccessMessage != null;
                        convertMessage(removeWorldSuccessMessage, commandSender, null, null, null, null, null, null, null, removeWorldSuccessMessageLocation, args[1]);
                    }
                    if (Boolean.FALSE.equals(isWorldPresent)){
                        String removeWorldFailureMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeWorldFailureMessage" + MESSAGE);
                        String removeWorldFailureMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeWorldFailureMessage" + LOCATION);
                        assert removeWorldFailureMessage != null;
                        convertMessage(removeWorldFailureMessage, commandSender, null, null, null, null, null, null, null, removeWorldFailureMessageLocation, args[1]);
                    }
                }
            } else if (args[0].equalsIgnoreCase("drops")) {
                if (commandSender.hasPermission("m4m.command.mk.drops") || commandSender.isOp()) {
                    boolean error = true;

                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            error = false;
                            boolean customDrops = MobConfigManager.mobsCfg.getBoolean(MOBS + mobName + ".customDrops");
                            if (Boolean.TRUE.equals(customDrops)) {
                                int numberOfDrops = 0;
                                try {
                                    for(String drop : MobConfigManager.mobsCfg.getConfigurationSection(MOBS + mobName + ".drops").getKeys(false)) {
                                        numberOfDrops++;
                                    }
                                } catch (NullPointerException ignored) {

                                }
                                if (numberOfDrops == 0) {
                                    String customDropsNotSetMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsNotSetMessage" + MESSAGE);
                                    String customDropsNotSetMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsNotSetMessage" + LOCATION);
                                    assert customDropsNotSetMessage != null;
                                    convertMessage(customDropsNotSetMessage, commandSender, mobName, null, null, null, null, null, null, customDropsNotSetMessageLocation, null);
                                } else {
                                    int counter = 1;
                                    for (int l = 0; l < numberOfDrops; l++) {
                                        String itemName = MobConfigManager.mobsCfg.getString(MOBS + mobName + DROPS_ITEMS + counter + ".name");
                                        Double amount = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + DROPS_ITEMS + counter + ".amount");
                                        double chance = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + DROPS_ITEMS + counter + ".chance");
                                        String mobDropInfoMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobDropInfoMessage" + MESSAGE);
                                        String mobDropInfoMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobDropInfoMessage" + LOCATION);
                                        assert mobDropInfoMessage != null;
                                        convertMessage(mobDropInfoMessage, commandSender, mobName, itemName, (int) chance, amount, null, null, null, mobDropInfoMessageLocation, null);
                                        counter++;

                                    }
                                }
                            } else {
                                String customDropsNotEnabledMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsNotEnabledMessage" + MESSAGE);
                                String customDropsNotEnabledMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsNotEnabledMessage" + LOCATION);
                                assert customDropsNotEnabledMessage != null;
                                convertMessage(customDropsNotEnabledMessage, commandSender, mobName, null, null, null, null, null, null, customDropsNotEnabledMessageLocation, null);
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(error)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                if (commandSender.hasPermission("m4m.command.mk.toggleCustomDrops") || commandSender.isOp()) {
                    boolean error = true;
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            error = false;
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            boolean customDrops = MobConfigManager.mobsCfg.getBoolean(MOBS + mobName + CUSTOM_DROPS);
                            if (Boolean.TRUE.equals(customDrops)) {
                                MobConfigManager.mobsCfg.set(MOBS + mobName + CUSTOM_DROPS, false);
                                String customDropsFalseMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsFalseMessage" + MESSAGE);
                                String customDropsFalseMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsFalseMessage" + LOCATION);
                                assert customDropsFalseMessage != null;
                                convertMessage(customDropsFalseMessage, commandSender, mobName, null, null, null, null, null, null, customDropsFalseMessageLocation, null);
                            } else {
                                MobConfigManager.mobsCfg.set(MOBS + mobName + CUSTOM_DROPS, true);
                                String customDropsTrueMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsTrueMessage" + MESSAGE);
                                String customDropsTrueMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsTrueMessage" + LOCATION);
                                assert customDropsTrueMessage != null;
                                convertMessage(customDropsTrueMessage, commandSender, mobName, null, null, null, null, null, null, customDropsTrueMessageLocation, null);
                            }
                            try {
                                MobConfigManager.mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(Boolean.TRUE.equals(error)){
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                if (commandSender.hasPermission("m4m.command.mk.toggleDefaultDrops") || commandSender.isOp()) {
                    boolean error = true;
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            error = false;
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean(MOBS + mobName + KEEP_DEFAULT_DROPS);
                            if (Boolean.TRUE.equals(defaultDrops)) {
                                MobConfigManager.mobsCfg.set(MOBS + mobName + KEEP_DEFAULT_DROPS, false);
                                String defaultDropsFalseMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".defaultDropsFalseMessage" + MESSAGE);
                                String defaultDropsFalseMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".defaultDropsFalseMessage" + LOCATION);
                                assert defaultDropsFalseMessage != null;
                                convertMessage(defaultDropsFalseMessage, commandSender, mobName, null, null, null, null, null, null, defaultDropsFalseMessageLocation, null);
                            } else {
                                MobConfigManager.mobsCfg.set(MOBS + mobName + KEEP_DEFAULT_DROPS, true);
                                String defaultDropsTrueMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".defaultDropsTrueMessage" + MESSAGE);
                                String defaultDropsTrueMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".defaultDropsTrueMessage" + LOCATION);
                                assert defaultDropsTrueMessage != null;
                                convertMessage(defaultDropsTrueMessage, commandSender, mobName, null, null, null, null, null, null, defaultDropsTrueMessageLocation, null);
                            }
                            try {
                                MobConfigManager.mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(error)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("language")) {
                if (player2 instanceof Player && (commandSender.hasPermission("m4m.command.mk.language") || commandSender.isOp())) {
                    boolean isValidLanguage = false;
                    for (String languageOption : testList) {
                        if (args[1].equalsIgnoreCase(languageOption)) {
                            int counter = 1;
                            for(String ignored : MemberConfig.membersCfg.getConfigurationSection("users").getKeys(false)) {
                                String userId = MemberConfig.membersCfg.getString(USERS_USER + counter + ".userId");
                                assert userId != null;
                                if (player2 != null){
                                    if(userId.equalsIgnoreCase(player2.getUniqueId().toString())){
                                        MemberConfig.membersCfg.set(USERS_USER + counter + ".language", args[1]);
                                        isValidLanguage = true;
                                        try {
                                            MemberConfig.membersCfg.save(userFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String languageChangeSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + args[1] + ".languageChangeSuccessMessage" + MESSAGE);
                                        String languageChangeSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + args[1] + ".languageChangeSuccessMessage" + LOCATION);
                                        assert languageChangeSuccessMessage != null;
                                        convertMessage(languageChangeSuccessMessage, commandSender, args[1], null, null, null, null, null, null, languageChangeSuccessMessageLocation, null);
                                        try {
                                            MemberConfig.updateUserDefaultLanguage(languageOption);
                                        } catch (IOException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                    counter++;
                                }
                            }
                        }
                    }
                    if (Boolean.FALSE.equals(isValidLanguage)) {
                        String invalidLanguageMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".invalidLanguageErrorMessage" + MESSAGE);
                        String invalidLanguageMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".invalidLanguageErrorMessage" + LOCATION);
                        assert invalidLanguageMessage != null;
                        convertMessage(invalidLanguageMessage, commandSender, args[1], null, null, null, null, null, null, invalidLanguageMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("mobRewardWorlds")) {
                if (player2 instanceof Player && (commandSender.hasPermission("m4m.command.mk.mobRewardWorlds") || commandSender.isOp())) {
                    String mobRewardWorldsMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobRewardWorldsMessage" + MESSAGE);
                    String invalidLanguageMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".mobRewardWorldsMessage" + LOCATION);
                    assert mobRewardWorldsMessage != null;
                    List<String> worlds = new ArrayList<>();
                    Boolean error = true;
                    if (Boolean.TRUE.equals(ConfigFileManager.configCfg.getBoolean("disableMoneyReward"))){
                        error = false;
                        worlds.add("");
                    } else {
                        for(String mob : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                            if (args[1].equalsIgnoreCase(mob)){
                                error = false;
                                for(String world : MobConfigManager.mobsCfg.getConfigurationSection("mobs."+ mob + ".worlds").getKeys(false)){
                                    if (Boolean.TRUE.equals(MobConfigManager.mobsCfg.getBoolean("mobs." + mob + ".worlds." + world))){
                                        worlds.add(world);
                                    }
                                }
                            }
                        }
                    }
                    String worldsString = "[";
                    int worldCount = worlds.size();
                    int counter = 0;
                    if (worldCount == 0){
                        worldsString = worldsString + "]";
                    }
                    for (String world : worlds){
                        counter++;
                        if (worldCount == 1){
                            worldsString = worldsString + world + "]";
                        } else {
                            if (counter < worldCount){
                                worldsString = worldsString + world + ", ";
                            } else {
                                worldsString = worldsString + world + "]";
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(error)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    } else {
                        convertMessage(mobRewardWorldsMessage, commandSender, args[1], null, null, null, null, null, null, invalidLanguageMessageLocation, worldsString);
                    }
                }
            } else {
                commandErrorMessage(commandSender, language, ".incompleteCommandErrorMessage");
            }
        }
        else if (args.length == 3) {
            boolean invalidMob = true;
            if (args[0].equalsIgnoreCase("addCustomDrop")){
                commandErrorMessage(commandSender, language, ".addCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleKM")){
                commandErrorMessage(commandSender, language, ".toggleKMCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("worth")){
                commandErrorMessage(commandSender, language, ".worthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("drops")){
                commandErrorMessage(commandSender, language, ".dropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("language")){
                commandErrorMessage(commandSender, language, ".languageCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnEggsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnersCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("reload")){
                commandErrorMessage(commandSender, language, ".reloadCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromTamedWolvesCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("mobRewardWorlds")){
                commandErrorMessage(commandSender, language, ".mobRewardWorldsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addWorld")){
                commandErrorMessage(commandSender, language, ".addWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeWorld")){
                commandErrorMessage(commandSender, language, ".removeWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setLowWorth")) {
                if (commandSender.hasPermission("m4m.command.mk.setLowWorth") || commandSender.isOp()) {
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            invalidMob = false;
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            double highWorth = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + ".worth.high");
                            try {
                                if (highWorth >= Double.parseDouble(args[2])) {
                                    MobConfigManager.mobsCfg.set(MOBS + mobName + ".worth.low", Double.parseDouble(args[2]));
                                    String setLowWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setLowWorthSuccessMessage" + MESSAGE);
                                    String setLowWorthSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setLowWorthSuccessMessage" + LOCATION);
                                    assert setLowWorthSuccessMessage != null;
                                    convertMessage(setLowWorthSuccessMessage, commandSender, mobName, null, null, null, args[2] , null, null, setLowWorthSuccessMessageLocation, null);
                                    MobConfigManager.mobsCfg.save(mobsFile);
                                } else {
                                    String setHighWorthTooLowErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthTooLowErrorMessage" + MESSAGE);
                                    String messageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthTooLowErrorMessage" + LOCATION);
                                    assert setHighWorthTooLowErrorMessage != null;
                                    convertMessage(setHighWorthTooLowErrorMessage, commandSender, mobName, null, null, null, null, null, null, messageLocation, null);
                                }
                            } catch (NumberFormatException | IOException e){
                                String setLowWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setLowWorthCommandErrorMessage" + MESSAGE );
                                assert setLowWorthCommandErrorMessage != null;
                                String messageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthTooLowErrorMessage" + LOCATION);
                                convertMessage(setLowWorthCommandErrorMessage, commandSender, null, null, null, null, null, null, null, messageLocation, null);
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(invalidMob)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            }
            else if (args[0].equalsIgnoreCase("setHighWorth")) {
                if (commandSender.hasPermission("m4m.command.mk.setHighWorth") || commandSender.isOp()) {
                    boolean error = true;
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            error = false;
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            double lowWorth = MobConfigManager.mobsCfg.getDouble(MOBS + mobName + ".worth.low");
                            try {
                                if (lowWorth <= Double.parseDouble(args[2])) {
                                    MobConfigManager.mobsCfg.set(MOBS + mobName + ".worth.high", Double.parseDouble(args[2]));
                                    MobConfigManager.mobsCfg.save(mobsFile);
                                    setHighWorthSuccessMessage(commandSender, args[2], mobName, language);
                                } else {
                                    String setLowWorthTooHighErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setLowWorthTooHighErrorMessage" + MESSAGE);
                                    String setLowWorthTooHighErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setLowWorthTooHighErrorMessage" + LOCATION);
                                    assert setLowWorthTooHighErrorMessage != null;
                                    convertMessage(setLowWorthTooHighErrorMessage, commandSender, mobName, null, null, null, null, null, null, setLowWorthTooHighErrorMessageLocation, null);
                                }
                            } catch (NumberFormatException | IOException e){
                                String setHighWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthCommandErrorMessage" + MESSAGE);
                                String setHighWorthCommandErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthCommandErrorMessage" + LOCATION);
                                assert setHighWorthCommandErrorMessage != null;
                                convertMessage(setHighWorthCommandErrorMessage, commandSender, null, null, null, null, null, null, null, setHighWorthCommandErrorMessageLocation, null);
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(error)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else if (args[0].equalsIgnoreCase("removeCustomDrop")) {
                if (commandSender.hasPermission("m4m.command.mk.removeCustomDrop") || commandSender.isOp()) {
                    boolean itemError = true;
                    boolean mobError = true;
                    for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                        if (mobObject.equalsIgnoreCase(args[1])) {
                            String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                            mobError = false;
                            if (MobConfigManager.mobsCfg.isSet(MOBS + mobName + ".drops")){
                                for(String drop : MobConfigManager.mobsCfg.getConfigurationSection(MOBS + mobName + ".drops").getKeys(false)) {
                                    if (MobConfigManager.mobsCfg.getString(MOBS + mobName + ".drops." +drop + ".name").equals(args[2])){
                                        itemError = false;
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + ".drops." +drop, null);
                                        try {
                                            MobConfigManager.mobsCfg.save(mobsFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                if (Boolean.TRUE.equals(itemError)) {
                                    String customDropsDoNotExistErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsDoNotExistErrorMessage" + MESSAGE);
                                    String customDropsDoNotExistErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".customDropsDoNotExistErrorMessage" + LOCATION);
                                    assert customDropsDoNotExistErrorMessage != null;
                                    convertMessage(customDropsDoNotExistErrorMessage, commandSender, args[1], null, null, null, null, null, null, customDropsDoNotExistErrorMessageLocation, null);
                                } else {
                                    try {
                                        String removeCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeCustomDropSuccessMessage" + MESSAGE);
                                        String removeCustomDropSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".removeCustomDropSuccessMessage" + LOCATION);
                                        assert removeCustomDropSuccessMessage != null;
                                        convertMessage(removeCustomDropSuccessMessage, commandSender, args[1], args[2], null, null, null, null, null, removeCustomDropSuccessMessageLocation, null);
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    if (Boolean.TRUE.equals(mobError)) {
                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                        assert addCustomDropInvalidMobErrorMessage != null;
                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            } else {
                commandErrorMessage(commandSender, language, ".incompleteCommandErrorMessage");
            }
        } else if (args.length == 5) {
            if (args[0].equalsIgnoreCase("toggleKM")){
                commandErrorMessage(commandSender, language, ".toggleKMCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("worth")){
                commandErrorMessage(commandSender, language, ".worthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("drops")){
                commandErrorMessage(commandSender, language, ".dropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("language")){
                commandErrorMessage(commandSender, language, ".languageCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnEggsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnersCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("reload")){
                commandErrorMessage(commandSender, language, ".reloadCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromTamedWolvesCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setHighWorth")){
                commandErrorMessage(commandSender, language, ".setHighWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setLowWorth")){
                commandErrorMessage(commandSender, language, ".setLowWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeCustomDrop")){
                commandErrorMessage(commandSender, language, ".removeCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleCustomDrops")){
                commandErrorMessage(commandSender, language, ".toggleCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")){
                commandErrorMessage(commandSender, language, ".toggleDefaultDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("mobRewardWorlds")){
                commandErrorMessage(commandSender, language, ".mobRewardWorldsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addWorld")){
                commandErrorMessage(commandSender, language, ".addWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeWorld")){
                commandErrorMessage(commandSender, language, ".removeWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addCustomDrop")) {
                if (commandSender.hasPermission("m4m.command.mk.addCustomDrop") || commandSender.isOp()) {
                    if (args[1].equalsIgnoreCase("Player")){
                        String addCustomDropsErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropsErrorMessage" + MESSAGE);
                        String addCustomDropsErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropsErrorMessage" + LOCATION);
                        assert addCustomDropsErrorMessage != null;
                        convertMessage(addCustomDropsErrorMessage, commandSender, null, null, null, null, null, null, null, addCustomDropsErrorMessageLocation, null);
                    }
                    else {
                        for(String mobObject : MobConfigManager.mobsCfg.getConfigurationSection("mobs").getKeys(false)) {
                            if (mobObject.equalsIgnoreCase(args[1])) {
                                String mobName = mobObject.substring(0, 1).toUpperCase() + mobObject.substring(1);
                                    try {
                                        int itemPresent = 0;
                                        try {
                                            for(String drop : MobConfigManager.mobsCfg.getConfigurationSection(MOBS + mobName + ".drops").getKeys(false)) {
                                                if (MobConfigManager.mobsCfg.getString(MOBS + mobName + ".drops." + drop + ".name").equals(args[2])) {
                                                    itemPresent = 1;
                                                    break;

                                                }
                                            }
                                        } catch (NullPointerException ignored){

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
                                                        counter2++;
                                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter2 + ".name", args[2]);
                                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter2 + ".amount", amount);
                                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter2 + ".chance", chance);
                                                        String addCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropSuccessMessage" + MESSAGE);
                                                        String addCustomDropSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropSuccessMessage" + LOCATION);
                                                        assert addCustomDropSuccessMessage != null;
                                                        convertMessage(addCustomDropSuccessMessage, commandSender, mobName, args[2], chance, (double) amount, null, null, null, addCustomDropSuccessMessageLocation, null);
                                                        MobConfigManager.mobsCfg.save(mobsFile);
                                                    } catch (NumberFormatException e) {
                                                        String addCustomDropsCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropsCommandErrorMessage" + MESSAGE);
                                                        String addCustomDropsCommandErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropsCommandErrorMessage" + LOCATION);
                                                        assert addCustomDropsCommandErrorMessage != null;
                                                        convertMessage(addCustomDropsCommandErrorMessage, commandSender, null, null, null, null, null, null, null, addCustomDropsCommandErrorMessageLocation, null);
                                                    } catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            String addCustomDropAlreadyPresentErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropAlreadyPresentErrorMessage" + MESSAGE);
                                            String addCustomDropAlreadyPresentErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".addCustomDropAlreadyPresentErrorMessage" + LOCATION);
                                            assert addCustomDropAlreadyPresentErrorMessage != null;
                                            convertMessage(addCustomDropAlreadyPresentErrorMessage, commandSender, null, args[2], null, null, null, null, null, addCustomDropAlreadyPresentErrorMessageLocation, null);
                                        }

                                    }
                                    catch (IllegalArgumentException e){
                                        String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + MESSAGE);
                                        String addCustomDropInvalidMobErrorMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + INVALID_MOB_ERROR_MESSAGE + LOCATION);
                                        assert addCustomDropInvalidMobErrorMessage != null;
                                        convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null, null, addCustomDropInvalidMobErrorMessageLocation, null);
                                    }
                                }
                            }
                    }
                } else {
                    String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + MESSAGE);
                    String accessDeniedMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ACCESS_DENIED_MESSAGE + LOCATION);
                    assert accessDeniedMessage != null;
                    convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null, null, accessDeniedMessageLocation, null);
                }
            }
        } else {
            if (args[0].equalsIgnoreCase("toggleKM")){
                commandErrorMessage(commandSender, language, ".toggleKMCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("worth")){
                commandErrorMessage(commandSender, language, ".worthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("drops")){
                commandErrorMessage(commandSender, language, ".dropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("language")){
                commandErrorMessage(commandSender, language, ".languageCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnEggsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromSpawnersCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("reload")){
                commandErrorMessage(commandSender, language, ".reloadCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")){
                commandErrorMessage(commandSender, language, ".toggleMoneyFromTamedWolvesCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setHighWorth")){
                commandErrorMessage(commandSender, language, ".setHighWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("setLowWorth")){
                commandErrorMessage(commandSender, language, ".setLowWorthCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeCustomDrop")){
                commandErrorMessage(commandSender, language, ".removeCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleCustomDrops")){
                commandErrorMessage(commandSender, language, ".toggleCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")){
                commandErrorMessage(commandSender, language, ".toggleDefaultDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addCustomDrop")){
                commandErrorMessage(commandSender, language, ".addCustomDropsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("mobRewardWorlds")){
                commandErrorMessage(commandSender, language, ".mobRewardWorldsCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("addWorld")){
                commandErrorMessage(commandSender, language, ".addWorldCommandErrorMessage");
            } else if (args[0].equalsIgnoreCase("removeWorld")){
                commandErrorMessage(commandSender, language, ".removeWorldCommandErrorMessage");
            } else {
                commandErrorMessage(commandSender, language, ".incompleteCommandErrorMessage");
            }
        }
     return true;
}

    private void commandErrorMessage(CommandSender commandSender, String language, String error) {
        String errorMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + error + MESSAGE);
        String errorLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + error + LOCATION);
        assert errorMessage != null;
        convertMessage(errorMessage, commandSender, null, null, null, null, null, null, null, errorLocation, null);
    }

    private void setHighWorthSuccessMessage(CommandSender commandSender, String arg, String mobName, String language) {
        try {
            String setHighWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthSuccessMessage" + MESSAGE);
            String setHighWorthSuccessMessageLocation = MessagesConfigManager.messagesCfg.getString(LANGUAGE + language + ".setHighWorthSuccessMessage" + LOCATION);
            assert setHighWorthSuccessMessage != null;
            convertMessage(setHighWorthSuccessMessage, commandSender, mobName, null, null, null, null, arg, null, setHighWorthSuccessMessageLocation, null);
            MobConfigManager.mobsCfg.save(mobsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player getPlayer(CommandSender commandSender, Player player) {
        if (commandSender instanceof Player ){
            player = (Player) commandSender;
        }
        return player;
    }

    private static void ifWordIsNotAColor(List<Object> object, boolean test, String s) {
        if (Boolean.FALSE.equals(test)){
            object.add(s);
        }
    }

    private static boolean iterateColorArray(List<String> colorArray, List<Object> object, boolean test, String s) {
        for (String color : colorArray){
            test = isColor(object, test, s, color);
        }
        return test;
    }

    private static boolean isColor(List<Object> object, boolean test, String s, String color) {
        if (s.contains(color)){
            test = true;
            String colorCode = s.substring(0,2);
            object.add(colorConverter(colorCode));
            List<String> words = Arrays.asList(s.split(colorCode));
            object.add(words.get(1));
        }
        return test;
    }

    public static ChatColor colorConverter(String color){
        ChatColor holder = null;
        switch (color) {
            case "&4":
                holder = ChatColor.valueOf("DARK_RED");
                break;
            case "&c":
                holder = ChatColor.valueOf("RED");
                break;
            case "&6":
                holder = ChatColor.valueOf("GOLD");
                break;
            case "&e":
                holder = ChatColor.valueOf("YELLOW");
                break;
            case "&2":
                holder = ChatColor.valueOf("DARK_GREEN");
                break;
            case "&a":
                holder = ChatColor.valueOf("GREEN");
                break;
            case "&b":
                holder = ChatColor.valueOf("AQUA");
                break;
            case "&3":
                holder = ChatColor.valueOf("DARK_AQUA");
                break;
            case "&1":
                holder = ChatColor.valueOf("DARK_BLUE");
                break;
            case "&9":
                holder = ChatColor.valueOf("BLUE");
                break;
            case "&d":
                holder = ChatColor.valueOf("LIGHT_PURPLE");
                break;
            case "&5":
                holder = ChatColor.valueOf("DARK_PURPLE");
                break;
            case "&7":
                holder = ChatColor.valueOf("GRAY");
                break;
            case "&8":
                holder = ChatColor.valueOf("DARK_GRAY");
                break;
            case "&0":
                holder = ChatColor.valueOf("BLACK");
                break;
            default:
                holder = ChatColor.valueOf("WHITE");
                break;
        }
        return holder;
    }

    public static void convertMessage(String message, CommandSender pa, String mobName, String itemName, Integer chance, Double amount, String lowWorth, String highWorth, Double balance, String messageLocation, String worlds) {
        String[] customArray = new String[0];
        try {
            customArray = message.split(" ");
            List<String> colorArray = new ArrayList<>();
            colorArray.add("&4");
            colorArray.add("&c");
            colorArray.add("&6");
            colorArray.add("&e");
            colorArray.add("&2");
            colorArray.add("&a");
            colorArray.add("&b");
            colorArray.add("&3");
            colorArray.add("&1");
            colorArray.add("&9");
            colorArray.add("&d");
            colorArray.add("&5");
            colorArray.add("&f");
            colorArray.add("&7");
            colorArray.add("&8");
            colorArray.add("&0");
            List<Object> object = new ArrayList<>();
            splitStringIntoArrayAndConvert(customArray, colorArray, object);
            StringBuilder d = new StringBuilder(object.get(0).toString());
            int count = 1;
            for (Object word: object){
                if (count > 1){
                    if (word.toString().equalsIgnoreCase("%mobName%")){
                        word = mobName;
                    }
                    if (word.toString().equalsIgnoreCase("%language%")){
                        word = mobName;
                    }
                    if (word.toString().equalsIgnoreCase("%itemName%")){
                        word = itemName;
                    }
                    if (word.toString().equalsIgnoreCase("%chance%")){
                        word = chance;
                    }
                    if (word.toString().equalsIgnoreCase("%amount%")){
                        word = amount;
                    }
                    if (word.toString().equalsIgnoreCase("%lowWorth%") || word.toString().equalsIgnoreCase("%worth%")){
                        word = lowWorth;
                    }
                    if (word.toString().equalsIgnoreCase("%highWorth%")){
                        word = highWorth;
                    }
                    if (word.toString().equalsIgnoreCase("%balance%")){
                        word = balance;
                    }
                    if (word.toString().equalsIgnoreCase("%worlds%")){
                        word = worlds;
                    }
                    if (word instanceof String && !word.toString().equals(".")) {
                        d.append(word).append(" ");
                    }
                    else {
                        d.append(word);
                    }
                }
                count++;
            }
            String completedMessage = d.toString();
            completedMessage = completedMessage.replace("$ ", "$");

            if (messageLocation.equalsIgnoreCase("ActionBar") && pa instanceof Player){
                Player player = (Player) pa;
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(completedMessage));
            } else if (messageLocation.equalsIgnoreCase("ChatMenu")) {
                pa.sendMessage(completedMessage);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            try {
                String language = ConfigFileManager.configCfg.getString("defaultLanguage");
                if (language.equalsIgnoreCase("french")){
                    pa.sendMessage(ChatColor.RED + "Erreur: Paramètres incorrects - Veuillez réessayer votre commande");
                } else if (language.equalsIgnoreCase("spanish")) {
                    pa.sendMessage(ChatColor.RED + "Parámetros incorrectos: vuelva a intentar su comando");
                } else if (language.equalsIgnoreCase("german")) {
                    pa.sendMessage(ChatColor.RED + "Error: Falsche Parameter - Bitte versuchen Sie Ihren Befehl erneut");
                } else if (language.equalsIgnoreCase("italian")) {
                    pa.sendMessage(ChatColor.RED + "Parametri errati - Riprova il comando");
                } else if (language.equalsIgnoreCase("hindi")) {
                    pa.sendMessage(ChatColor.RED + "रुटि: गलत पैरामीटर - कृपया अपने आदेश का पुन: प्रयास करें");
                } else if (language.equalsIgnoreCase("russian")) {
                    pa.sendMessage(ChatColor.RED + "Ошибка: Неправильные параметры - попробуйте команду еще раз");
                } else if (language.equalsIgnoreCase("chinese_simplified")) {
                    pa.sendMessage(ChatColor.RED + "错误: 不正确的参数 - 请重试您的命令");
                } else if (language.equalsIgnoreCase("chinese_traditional")) {
                    pa.sendMessage(ChatColor.RED + "錯誤: 不正確的參數 - 請重試您的命令");
                } else {
                    pa.sendMessage(ChatColor.RED + "Error: Incorrect Parameters - Please try your command again" + e);
                }
            } catch (NullPointerException i){
                pa.sendMessage(ChatColor.RED + "Error: Incorrect Parameters - Please try your command again");
            }
        }

    }

    private static void splitStringIntoArrayAndConvert(String[] customArray, List<String> colorArray, List<Object> object) {
        boolean test;
        for (String s: customArray){
            test = iterateColorArray(colorArray, object, false, s);
            ifWordIsNotAColor(object, test, s);
        }
    }
}
