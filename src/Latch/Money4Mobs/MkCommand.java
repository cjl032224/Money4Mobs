package Latch.Money4Mobs;

import Latch.Money4Mobs.Managers.MessagesConfigManager;
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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MkCommand implements CommandExecutor {
    private static final FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    private static final File mobsFile = MobConfigManager.mobsFile;
    private static final File userFile = UserManager.usersFile;
    private static final Material[] materials = Material.values();
    private static List<UserModel> um = UserManager.updateUsersOnReload();
    private static Boolean showMessage = true;
    private static final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    // Config file paths Constants
    private static final String SHOW_MESSAGE = ".showMessage";
    private static final String USERS_USER = "users.user-";
    private static final String DEFAULT_LANGUAGE = "defaultLanguage";
    private static final String ACCESS_DENIED_MESSAGE = ".accessDeniedMessage";
    private static final String MESSAGES = "messages.";
    private static final String SPAWN_EGGS = "spawneggs";
    private static final String SPAWNERS = "spawners";
    private static final String TAMED_WOLVES_GIVE_MONEY = "tamedWolvesGiveMoney";
    private static final String OVERRIDE_KILL_MESSAGE = "customMessageOption.overrideKillMessage";
    private static final String ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE = ".addCustomDropInvalidMobErrorMessage";
    private static final String MOBS = "mobs.";
    private static final String DROPS_ITEMS = ".drops.item-";
    private static final String CUSTOM_DROPS = ".customDrops";
    private static final String KEEP_DEFAULT_DROPS = ".keepDefaultDrops";
    Logger logger = Logger.getLogger(MkCommand.class.getName());
    private static String language;
    // Language Constants
    private static final String ENGLISH = "English";

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player2 = null;
        player2 = getPlayer(commandSender, player2);
        List<MobModel> mm = MobConfigManager.getMobModelFromConfig();
        int firstCounter = 1;
        for(String firstUsers : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
            firstCounter++;
        }
        ArrayList<String> testList = new ArrayList<>();
        for (String items : MessagesConfigManager.messagesCfg.getConfigurationSection("messages").getKeys(false)){
            testList.add(items);
        }
        if (player2 == null){
            firstCounter = 1;
        }
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
                        if (player2 != null && (player2.hasPermission("m4m.command.mk.toggleKM") || commandSender.isOp())) {
                            if (language == null){
                                language = ENGLISH;
                            }
                            if (Boolean.TRUE.equals(showMessage)) {
                                String mobKillerOffMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".mobKillerOffMessage");
                                convertMessage(mobKillerOffMessage, commandSender, null, null, null, null, null, null);
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, false);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String mobKillerOnMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES +  language + ".mobKillerOnMessage");
                                convertMessage(mobKillerOnMessage, commandSender, null, null, null, null, null, null);
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, true);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs") || commandSender.isOp()) {
                            boolean spawnEgg = MobConfigManager.mobsCfg.getBoolean(SPAWN_EGGS);
                            if (Boolean.TRUE.equals(spawnEgg)) {
                                MobConfigManager.mobsCfg.set(SPAWN_EGGS, false);
                                String eggSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".eggSpawnRewardFalseMessage");
                                convertMessage(eggSpawnRewardFalseMessage, commandSender, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set(SPAWN_EGGS, true);
                                String eggSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".eggSpawnRewardTrueMessage");
                                convertMessage(eggSpawnRewardTrueMessage, commandSender, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromSpawners") || commandSender.isOp()) {
                            boolean spawners = MobConfigManager.mobsCfg.getBoolean(SPAWNERS);
                            if (Boolean.TRUE.equals(spawners)) {
                                MobConfigManager.mobsCfg.set(SPAWNERS, false);
                                String spawnerSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".spawnerSpawnRewardFalseMessage");
                                convertMessage(spawnerSpawnRewardFalseMessage, commandSender, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set(SPAWNERS, true);
                                String spawnerSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".spawnerSpawnRewardTrueMessage");
                                convertMessage(spawnerSpawnRewardTrueMessage, commandSender, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleMoneyFromTamedWolves") || commandSender.isOp()) {
                            boolean tamedWolvesGiveMoney = MobConfigManager.mobsCfg.getBoolean(TAMED_WOLVES_GIVE_MONEY);
                            if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                                MobConfigManager.mobsCfg.set(TAMED_WOLVES_GIVE_MONEY, false);
                                String tamedWolvesRewardFalseMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".tamedWolvesRewardFalseMessage");
                                convertMessage(tamedWolvesRewardFalseMessage, commandSender, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set(TAMED_WOLVES_GIVE_MONEY, true);
                                String tamedWolvesRewardTrueMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".tamedWolvesRewardTrueMessage");
                                convertMessage(tamedWolvesRewardTrueMessage, commandSender, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("reload")) {
                        if (commandSender.hasPermission("m4m.command.mk.reload") || commandSender.isOp()) {
                            String reloadingMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".reloadingMessage");
                            logger.log(Level.INFO, reloadingMessage.substring(2));
                            convertMessage(reloadingMessage, commandSender, null, null, null, null, null, null);
                            plugin.getPluginLoader().disablePlugin(plugin);
                            mm.clear();
                            mm = MobConfigManager.getMobModelFromConfig();
                            um.clear();
                            um = UserManager.updateUsersOnReload();
                            plugin.getPluginLoader().enablePlugin(plugin);
                            String reloadConfirmMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".reloadConfirmMessage");
                            logger.log(Level.INFO, reloadConfirmMessage.substring(2));
                            convertMessage(reloadConfirmMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleCustomKM")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleCustomKM") || commandSender.isOp()) {
                            boolean customKillMessage = MobConfigManager.mobsCfg.getBoolean(OVERRIDE_KILL_MESSAGE);
                            if (Boolean.TRUE.equals(customKillMessage)) {
                                MobConfigManager.mobsCfg.set(OVERRIDE_KILL_MESSAGE, false);
                                String overrideKillMessageFalse = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".overrideKillMessageFalse");
                                convertMessage(overrideKillMessageFalse, commandSender, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set(OVERRIDE_KILL_MESSAGE, true);
                                String overrideKillMessageTrue = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".overrideKillMessageTrue");
                                convertMessage(overrideKillMessageTrue, commandSender, null, null, null, null, null, null);
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
                        if (commandSender.hasPermission("m4m.command.mk.worth") || commandSender.isOp()) {
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    Double lowWorth = mobModel.lowWorth;
                                    Double highWorth = mobModel.highWorth;
                                    assert language != null;
                                    if (lowWorth.equals(highWorth)) {
                                        String mobWorthMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".mobWorthMessage");
                                        convertMessage(mobWorthMessage, commandSender, mobName, null, null, null, lowWorth.toString(), null);
                                    } else {
                                        String mobRangeWorthMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".mobRangeWorthMessage");
                                        System.out.println("asda: " + highWorth.toString());
                                        convertMessage(mobRangeWorthMessage, commandSender, mobName, null, null, null, lowWorth.toString(), highWorth.toString());
                                    }
                                }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("drops")) {
                        if (commandSender.hasPermission("m4m.command.mk.drops") || commandSender.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    if (Boolean.TRUE.equals(mobModel.getCustomDrops())) {
                                        if (mobModel.getItems().isEmpty()) {
                                            String customDropsNotSetMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".customDropsNotSetMessage");
                                            convertMessage(customDropsNotSetMessage, commandSender, mobName, null, null, null, null, null);
                                        }
                                        for (int l = 0; l < mobModel.getItems().size(); l++) {
                                            String mobDropInfoMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".mobDropInfoMessage");
                                            convertMessage(mobDropInfoMessage, commandSender, mobName, mobModel.getItems().get(l).getItemName(), mobModel.getItems().get(l).getChance(), mobModel.getItems().get(l).getAmount(), null, null);
                                        }
                                    } else {
                                        String customDropsNotEnabledMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".customDropsNotEnabledMessage");
                                        convertMessage(customDropsNotEnabledMessage, commandSender, mobName, null, null, null, null, null);
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleCustomDrops") || commandSender.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    error = false;
                                    boolean customDrops = MobConfigManager.mobsCfg.getBoolean(MOBS + mobName + CUSTOM_DROPS);
                                    if (Boolean.TRUE.equals(customDrops)) {
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + CUSTOM_DROPS, false);
                                        mobModel.setCustomDrops(false);
                                        String customDropsFalseMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".customDropsFalseMessage");
                                        convertMessage(customDropsFalseMessage, commandSender, mobName, null, null, null, null, null);
                                    } else {
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + CUSTOM_DROPS, true);
                                        mobModel.setCustomDrops(true);
                                        String customDropsTrueMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".customDropsTrueMessage");
                                        convertMessage(customDropsTrueMessage, commandSender, mobName, null, null, null, null, null);
                                    }
                                    try {
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if(Boolean.TRUE.equals(error)){
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (commandSender.hasPermission("m4m.command.mk.toggleDefaultDrops") || commandSender.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean(MOBS + mobName + KEEP_DEFAULT_DROPS);
                                    if (Boolean.TRUE.equals(defaultDrops)) {
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + KEEP_DEFAULT_DROPS, false);
                                        mobModel.setKeepDefaultDrops(false);
                                        String defaultDropsFalseMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".defaultDropsFalseMessage");
                                        convertMessage(defaultDropsFalseMessage, commandSender, mobName, null, null, null, null, null);
                                    } else {
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + KEEP_DEFAULT_DROPS, true);
                                        mobModel.setKeepDefaultDrops(true);
                                        String defaultDropsTrueMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".defaultDropsTrueMessage");
                                        convertMessage(defaultDropsTrueMessage, commandSender, mobName, null, null, null, null, null);
                                    }
                                    try {
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("language")) {
                        if (commandSender.hasPermission("m4m.command.mk.language") || commandSender.isOp()) {
                            boolean success = false;
                            for (String languageOption : testList) {
                                if (args[1].equalsIgnoreCase(languageOption)) {
                                    int counter = 1;
                                    for(String users : UserManager.usersCfg.getConfigurationSection("users").getKeys(false)) {
                                        String userId = UserManager.usersCfg.getString(USERS_USER + counter + ".userId");
                                        assert userId != null;
                                        if (player2 != null){
                                            if(userId.equalsIgnoreCase(player2.getUniqueId().toString())){
                                                UserManager.usersCfg.set(USERS_USER + counter + ".language", args[1]);
                                                success = true;
                                                try {
                                                    UserManager.usersCfg.save(userFile);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                String languageChangeSuccessMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + args[1] + ".languageChangeSuccessMessage");
                                                convertMessage(languageChangeSuccessMessage, commandSender, args[1], null, null, null, null, null);
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
                                            counter++;
                                        }
                                    }
                                }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                }

            } else if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("setLowWorth")) {
                        if (commandSender.hasPermission("m4m.command.mk.setLowWorth") || commandSender.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    Double highWorth = mobModel.getHighWorth();
                                    try {
                                        if (highWorth >= Double.parseDouble(args[2])) {
                                            mobModel.setLowWorth(Double.parseDouble(args[2]));
                                            MobConfigManager.mobsCfg.set(MOBS + mobName + ".worth.low", Double.parseDouble(args[2]));
                                            setLowWorthSuccessMessage(commandSender, args[2], mobName);
                                        } else {
                                            String setHighWorthTooLowErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setHighWorthTooLowErrorMessage");
                                            convertMessage(setHighWorthTooLowErrorMessage, commandSender, mobName, null, null, null, null, null);
                                        }
                                    } catch (NumberFormatException e){
                                        String setLowWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setLowWorthCommandErrorMessage");
                                        convertMessage(setLowWorthCommandErrorMessage, commandSender, null, null, null, null, null, null);
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    if (args[0].equalsIgnoreCase("setHighWorth")) {
                        if (commandSender.hasPermission("m4m.command.mk.setHighWorth") || commandSender.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    Double lowWorth = mobModel.getLowWorth();
                                    try {
                                        if (lowWorth <= Double.parseDouble(args[2])) {
                                            mobModel.setHighWorth(Double.parseDouble(args[2]));
                                            MobConfigManager.mobsCfg.set(MOBS + mobName + ".worth.high", Double.parseDouble(args[2]));
                                            setHighWorthSuccessMessage(commandSender, args[2], mobName);
                                        } else {
                                            String setLowWorthTooHighErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setLowWorthTooHighErrorMessage");
                                            convertMessage(setLowWorthTooHighErrorMessage, commandSender, mobName, null, null, null, null, null);
                                        }
                                    } catch (NumberFormatException e){
                                        String setHighWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setHighWorthCommandErrorMessage");
                                        convertMessage(setHighWorthCommandErrorMessage, commandSender, null, null, null, null, null, null);
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                    if (args[0].equalsIgnoreCase("removeCustomDrop")) {
                        if (commandSender.hasPermission("m4m.command.mk.removeCustomDrop") || commandSender.isOp()) {
                            List<ItemModel> itemList = new ArrayList<>();
                            boolean itemError = true;
                            boolean mobError = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    mobError = false;
                                    for (int k = 0; k < mobModel.getItems().size(); k++) {
                                        itemList.add(new ItemModel(mobModel.getItems().get(k).getItemName(), mobModel.getItems().get(k).getAmount(), mobModel.getItems().get(k).getChance()));
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + (k + 1), null);
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
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter + ".name", itemModel.getItemName());
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter + ".amount", itemModel.getAmount());
                                        MobConfigManager.mobsCfg.set(MOBS + mobName + DROPS_ITEMS + counter + ".chance", itemModel.getChance());
                                        counter++;
                                    }
                                    mobModel.getItems().clear();
                                    mobModel.setItems(itemList);
                                    if (Boolean.TRUE.equals(itemError)) {
                                        String customDropsDoNotExistErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".customDropsDoNotExistErrorMessage");
                                        convertMessage(customDropsDoNotExistErrorMessage, commandSender, args[1], null, null, null, null, null);
                                    } else {
                                        try {
                                            String removeCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".removeCustomDropSuccessMessage");
                                            convertMessage(removeCustomDropSuccessMessage, commandSender, args[1], args[2], null, null, null, null);
                                            MobConfigManager.mobsCfg.save(mobsFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(mobError)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (commandSender.hasPermission("m4m.command.mk.addCustomDrop") || commandSender.isOp()) {
                            if (args[1].equalsIgnoreCase("Player")){
                                String addCustomDropsErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".addCustomDropsErrorMessage");
                                convertMessage(addCustomDropsErrorMessage, commandSender, null, null, null, null, null, null);
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
                                                                MobConfigManager.mobsCfg.set(MOBS + mobModel.getMobName() + DROPS_ITEMS + counter2 + ".name", args[2]);
                                                                MobConfigManager.mobsCfg.set(MOBS + mobModel.getMobName() + DROPS_ITEMS + counter2 + ".amount", amount);
                                                                MobConfigManager.mobsCfg.set(MOBS + mobModel.getMobName() + DROPS_ITEMS + counter2 + ".chance", chance);
                                                                String addCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".addCustomDropSuccessMessage");
                                                                convertMessage(addCustomDropSuccessMessage, commandSender, mobModel.getMobName(), args[2], chance, amount, null, null);
                                                                MobConfigManager.mobsCfg.save(mobsFile);
                                                            } catch (NumberFormatException e) {
                                                                String addCustomDropsCommandErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".addCustomDropsCommandErrorMessage");
                                                                convertMessage(addCustomDropsCommandErrorMessage, commandSender, null, null, null, null, null, null);
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    String addCustomDropAlreadyPresentErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".addCustomDropAlreadyPresentErrorMessage");
                                                    convertMessage(addCustomDropAlreadyPresentErrorMessage, commandSender, null, args[2], null, null, null, null);
                                                }

                                            }
                                            catch (IllegalArgumentException e){
                                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ADD_CUSTOM_DROP_INVALID_ERROR_MESSAGE);
                                                convertMessage(addCustomDropInvalidMobErrorMessage, commandSender, args[1], null, null, null, null, null);
                                            }
                                        }
                                    }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ACCESS_DENIED_MESSAGE);
                            convertMessage(accessDeniedMessage, commandSender, null, null, null, null, null, null);
                        }
                    }
                }
            }
        return true;
    }

    private void setLowWorthSuccessMessage(CommandSender commandSender, String arg, String mobName) {
        try {
            String setLowWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setLowWorthSuccessMessage");
            convertMessage(setLowWorthSuccessMessage, commandSender, mobName, null, null, null, arg, null);
            MobConfigManager.mobsCfg.save(mobsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setHighWorthSuccessMessage(CommandSender commandSender, String arg, String mobName) {
        try {
            String setHighWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString(MESSAGES + language + ".setHighWorthSuccessMessage");
            assert setHighWorthSuccessMessage != null;
            convertMessage(setHighWorthSuccessMessage, commandSender, mobName, null, null, null, null, arg);
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

    public static void convertMessage(String message, CommandSender pa, String mobName, String itemName, Integer chance, Integer amount, String lowWorth, String highWorth) {
        String[] customArray = message.split(" ");
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
        boolean test = false;
        splitStringIntoArrayAndConvert(customArray, colorArray, object);
        StringBuilder d = new StringBuilder(object.get(0).toString());
        int count = 1;
        for (Object word: object){

            if (count > 1){
                if (word.toString().equalsIgnoreCase("%mobName%")){
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
                if (word instanceof String && !word.toString().equals(".")) {
                    d.append(word).append(" ");
                }
                else {
                    d.append(word);
                }
            }
            count++;
        }
        pa.sendMessage(d.toString());
    }

    private static void splitStringIntoArrayAndConvert(String[] customArray, List<String> colorArray, List<Object> object) {
        boolean test;
        for (String s: customArray){
            test = false;
            test = iterateColorArray(colorArray, object, test, s);
            ifWordIsNotAColor(object, test, s);
        }
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

}
