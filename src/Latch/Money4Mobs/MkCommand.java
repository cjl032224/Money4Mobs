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

public class MkCommand implements CommandExecutor {
    private static final FileConfiguration mobsCfg = MobConfigManager.mobsCfg;
    private static final File mobsFile = MobConfigManager.mobsFile;
    private static final File userFile = UserManager.usersFile;
    private static final Material[] materials = Material.values();
    private static List<UserModel> um = UserManager.updateUsersOnReload();
    private static Boolean showMessage = true;
    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    private static final FileConfiguration messagesCfg = MessagesConfigManager.messagesCfg;
    private static final File languageFile = MessagesConfigManager.messagesFile;

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
                                String mobKillerOffMessage = MessagesConfigManager.messagesCfg.getString("messages.mobKillerOffMessage");
                                convertMessage(mobKillerOffMessage, player, null, null, null, null, null, null);
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, false);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String mobKillerOnMessage = MessagesConfigManager.messagesCfg.getString("messages.mobKillerOnMessage");
                                convertMessage(mobKillerOnMessage, player, null, null, null, null, null, null);
                                UserManager.usersCfg.set(USERS_USER + i + SHOW_MESSAGE, true);
                                try {
                                    UserManager.usersCfg.save(userFile);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawnEggs")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawnEggs") || player.isOp()) {
                            boolean spawnEgg = MobConfigManager.mobsCfg.getBoolean("spawneggs");
                            if (Boolean.TRUE.equals(spawnEgg)) {
                                MobConfigManager.mobsCfg.set("spawneggs", false);
                                String eggSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString("messages.eggSpawnRewardFalseMessage");
                                convertMessage(eggSpawnRewardFalseMessage, player, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set("spawneggs", true);
                                String eggSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString("messages.eggSpawnRewardTrueMessage");
                                convertMessage(eggSpawnRewardTrueMessage, player, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleMoneyFromSpawners")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromSpawners") || player.isOp()) {
                            boolean spawners = MobConfigManager.mobsCfg.getBoolean("spawners");
                            if (Boolean.TRUE.equals(spawners)) {
                                MobConfigManager.mobsCfg.set("spawners", false);
                                String spawnerSpawnRewardFalseMessage = MessagesConfigManager.messagesCfg.getString("messages.spawnerSpawnRewardFalseMessage");
                                convertMessage(spawnerSpawnRewardFalseMessage, player, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set("spawners", true);
                                String spawnerSpawnRewardTrueMessage = MessagesConfigManager.messagesCfg.getString("messages.spawnerSpawnRewardTrueMessage");
                                convertMessage(spawnerSpawnRewardTrueMessage, player, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleMoneyFromTamedWolves")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleMoneyFromTamedWolves") || player.isOp()) {
                            boolean tamedWolvesGiveMoney = MobConfigManager.mobsCfg.getBoolean("tamedWolvesGiveMoney");
                            if (Boolean.TRUE.equals(tamedWolvesGiveMoney)) {
                                MobConfigManager.mobsCfg.set("tamedWolvesGiveMoney", false);
                                String tamedWolvesRewardFalseMessage = MessagesConfigManager.messagesCfg.getString("messages.tamedWolvesRewardFalseMessage");
                                convertMessage(tamedWolvesRewardFalseMessage, player, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set("tamedWolvesGiveMoney", true);
                                String tamedWolvesRewardTrueMessage = MessagesConfigManager.messagesCfg.getString("messages.tamedWolvesRewardTrueMessage");
                                convertMessage(tamedWolvesRewardTrueMessage, player, null, null, null, null, null, null);
                            }
                            try {
                                mobsCfg.save(mobsFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("reload")) {
                        String bool = "";

                        if (player.hasPermission("m4m.command.mk.reload") || player.isOp()) {
                            String reloadingMessage = MessagesConfigManager.messagesCfg.getString("messages.reloadingMessage");
                            convertMessage(reloadingMessage, player, null, null, null, null, null, null);
                            plugin.getPluginLoader().disablePlugin(plugin);
                            mm.clear();
                            mm = MobConfigManager.getMobModelFromConfig();
                            um.clear();
                            um = UserManager.updateUsersOnReload();
                            plugin.getPluginLoader().enablePlugin(plugin);
                            String reloadConfirmMessage = MessagesConfigManager.messagesCfg.getString("messages.reloadConfirmMessage");
                            convertMessage(reloadConfirmMessage, player, null, null, null, null, null, null);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("toggleCustomKM")) {
                        String bool = "";
                        if (player.hasPermission("m4m.command.mk.toggleCustomKM") || player.isOp()) {
                            boolean customKillMessage = MobConfigManager.mobsCfg.getBoolean("customMessageOption.overrideKillMessage");
                            if (Boolean.TRUE.equals(customKillMessage)) {
                                MobConfigManager.mobsCfg.set("customMessageOption.overrideKillMessage", false);
                                String overrideKillMessageFalse = MessagesConfigManager.messagesCfg.getString("messages.overrideKillMessageFalse");
                                convertMessage(overrideKillMessageFalse, player, null, null, null, null, null, null);
                            } else {
                                MobConfigManager.mobsCfg.set("customMessageOption.overrideKillMessage", true);
                                String overrideKillMessageTrue = MessagesConfigManager.messagesCfg.getString("messages.overrideKillMessageTrue");
                                convertMessage(overrideKillMessageTrue, player, null, null, null, null, null, null);
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
                                        String mobWorthMessage = MessagesConfigManager.messagesCfg.getString("messages.mobWorthMessage");
                                        convertMessage(mobWorthMessage, player, mobName, null, null, null, lowWorth.toString(), null);
                                    } else {
                                        String mobRangeWorthMessage = MessagesConfigManager.messagesCfg.getString("messages.mobRangeWorthMessage");
                                        convertMessage(mobRangeWorthMessage, player, mobName, null, null, null, lowWorth.toString(), highWorth.toString());
                                    }
                                }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
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
                                            String customDropsNotSetMessage = MessagesConfigManager.messagesCfg.getString("messages.customDropsNotSetMessage");
                                            convertMessage(customDropsNotSetMessage, player, mobName, null, null, null, null, null);
                                        }
                                        for (int l = 0; l < mobModel.getItems().size(); l++) {
                                            String mobDropInfoMessage = MessagesConfigManager.messagesCfg.getString("messages.mobDropInfoMessage");
                                            convertMessage(mobDropInfoMessage, player, mobName, mobModel.getItems().get(l).getItemName(), mobModel.getItems().get(l).getChance(), mobModel.getItems().get(l).getAmount(), null, null);
                                        }
                                    } else {
                                        String customDropsNotEnabledMessage = MessagesConfigManager.messagesCfg.getString("messages.customDropsNotEnabledMessage");
                                        convertMessage(customDropsNotEnabledMessage, player, mobName, null, null, null, null, null);
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleCustomDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleCustomDrops") || player.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    String mobName = mobModel.mobName;
                                    error = false;
                                    boolean customDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".customDrops");
                                    if (Boolean.TRUE.equals(customDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", false);
                                        mobModel.setCustomDrops(false);
                                        String customDropsFalseMessage = MessagesConfigManager.messagesCfg.getString("messages.customDropsFalseMessage");
                                        convertMessage(customDropsFalseMessage, player, mobName, null, null, null, null, null);
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".customDrops", true);
                                        mobModel.setCustomDrops(true);
                                        String customDropsTrueMessage = MessagesConfigManager.messagesCfg.getString("messages.customDropsTrueMessage");
                                        convertMessage(customDropsTrueMessage, player, mobName, null, null, null, null, null);
                                    }
                                    try {
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if(Boolean.TRUE.equals(error)){
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    } else if (args[0].equalsIgnoreCase("toggleDefaultDrops")) {
                        if (player.hasPermission("m4m.command.mk.toggleDefaultDrops") || player.isOp()) {
                            boolean error = true;
                            for (MobModel mobModel : mm) {
                                if (args[1].equalsIgnoreCase(mobModel.mobName)) {
                                    error = false;
                                    String mobName = mobModel.mobName;
                                    boolean defaultDrops = MobConfigManager.mobsCfg.getBoolean("mobs." + mobName + ".keepDefaultDrops");
                                    if (Boolean.TRUE.equals(defaultDrops)) {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", false);
                                        mobModel.setKeepDefaultDrops(false);
                                        String defaultDropsFalseMessage = MessagesConfigManager.messagesCfg.getString("messages.defaultDropsFalseMessage");
                                        convertMessage(defaultDropsFalseMessage, player, mobName, null, null, null, null, null);
                                    } else {
                                        MobConfigManager.mobsCfg.set("mobs." + mobName + ".keepDefaultDrops", true);
                                        mobModel.setKeepDefaultDrops(true);
                                        String defaultDropsTrueMessage = MessagesConfigManager.messagesCfg.getString("messages.defaultDropsTrueMessage");
                                        convertMessage(defaultDropsTrueMessage, player, mobName, null, null, null, null, null);
                                    }
                                    try {
                                        MobConfigManager.mobsCfg.save(mobsFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
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
                                                String setLowWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString("messages.setLowWorthSuccessMessage");
                                                convertMessage(setLowWorthSuccessMessage, player, mobName, null, null, null, args[2], null);
                                                MobConfigManager.mobsCfg.save(mobsFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            String setHighWorthTooLowErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.setHighWorthTooLowErrorMessage");
                                            convertMessage(setHighWorthTooLowErrorMessage, player, mobName, null, null, null, null, null);
                                        }
                                    } catch (NumberFormatException e){
                                        String setLowWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.setLowWorthCommandErrorMessage");
                                        convertMessage(setLowWorthCommandErrorMessage, player, null, null, null, null, null, null);
                                    }

                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
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
                                                String setHighWorthSuccessMessage = MessagesConfigManager.messagesCfg.getString("messages.setHighWorthSuccessMessage");
                                                convertMessage(setHighWorthSuccessMessage, player, mobName, null, null, null, args[2], null);
                                                MobConfigManager.mobsCfg.save(mobsFile);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        } else {
                                            String setLowWorthTooHighErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.setLowWorthTooHighErrorMessage");
                                            convertMessage(setLowWorthTooHighErrorMessage, player, mobName, null, null, null, null, null);
                                        }
                                    } catch (NumberFormatException e){
                                        String setHighWorthCommandErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.setHighWorthCommandErrorMessage");
                                        convertMessage(setHighWorthCommandErrorMessage, player, null, null, null, null, null, null);
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(error)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
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
                                        String customDropsDoNotExistErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.customDropsDoNotExistErrorMessage");
                                        convertMessage(customDropsDoNotExistErrorMessage, player, args[1], null, null, null, null, null);
                                    } else {
                                        try {
                                            String removeCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString("messages.removeCustomDropSuccessMessage");
                                            convertMessage(removeCustomDropSuccessMessage, player, args[1], args[2], null, null, null, null);
                                            MobConfigManager.mobsCfg.save(mobsFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (Boolean.TRUE.equals(mobError)) {
                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    }
                } else if (args.length == 5) {
                    if (args[0].equalsIgnoreCase("addCustomDrop")) {
                        if (player.hasPermission("m4m.command.mk.addCustomDrop") || player.isOp()) {
                            if (args[1].equalsIgnoreCase("Player")){
                                String addCustomDropsErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropsErrorMessage");
                                convertMessage(addCustomDropsErrorMessage, player, null, null, null, null, null, null);
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
                                                                String addCustomDropSuccessMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropSuccessMessage");
                                                                convertMessage(addCustomDropSuccessMessage, player, mobModel.getMobName(), args[2], chance, amount, null, null);
                                                                MobConfigManager.mobsCfg.save(mobsFile);
                                                            } catch (NumberFormatException e) {
                                                                String addCustomDropsCommandErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropsCommandErrorMessage");
                                                                convertMessage(addCustomDropsCommandErrorMessage, player, null, null, null, null, null, null);
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    String addCustomDropAlreadyPresentErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropAlreadyPresentErrorMessage");
                                                    convertMessage(addCustomDropAlreadyPresentErrorMessage, player, null, args[2], null, null, null, null);
                                                }

                                            }
                                            catch (IllegalArgumentException e){
                                                String addCustomDropInvalidMobErrorMessage = MessagesConfigManager.messagesCfg.getString("messages.addCustomDropInvalidMobErrorMessage");
                                                convertMessage(addCustomDropInvalidMobErrorMessage, player, args[1], null, null, null, null, null);
                                            }
                                        }
                                    }
                            }
                        } else {
                            String accessDeniedMessage = MessagesConfigManager.messagesCfg.getString("messages.accessDeniedMessage");
                            convertMessage(accessDeniedMessage, player, null, null, null, null, null, null);
                        }
                    }
                }
            }

        }
        return true;
    }

    public static void convertMessage(String message, CommandSender pa, String mobName, String itemName, Integer chance, Integer amount, String lowWorth, String highWorth) {
        String customMessageString = message;
        List<String> customArray = Arrays.asList(customMessageString.split(" "));
        List<String> colorArray = new ArrayList<>();
        System.out.println("chance: " + chance);
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
        List<Object> object = new ArrayList<Object>();
        boolean test = false;
        for (String s: customArray){
            test = false;
            for (String color : colorArray){
                if (s.contains(color)){
                    test = true;
                    String colorCode = s.substring(0,2);
                    object.add(colorConverter(colorCode));
                    List<String> words = Arrays.asList(s.split(colorCode));
                    object.add(words.get(1));
                }
            }
            if (Boolean.FALSE.equals(test)){
                object.add(s);
            }

        }
        String d = object.get(0).toString();
        int count = 1;
        System.out.println("wow: " + object);
        for (Object wow: object){

            if (count > 1){
                if (wow.toString().equalsIgnoreCase("%mobName%")){
                    wow = mobName;
                }
                if (wow.toString().equalsIgnoreCase("%itemName%")){
                    wow = itemName;
                }
                if (wow.toString().equalsIgnoreCase("%chance%")){
                    wow = chance;
                }
                if (wow.toString().equalsIgnoreCase("%amount%")){
                    wow = amount;
                }
                if (wow.toString().equalsIgnoreCase("%lowWorth%") || wow.toString().equalsIgnoreCase("%worth%")){
                    wow = lowWorth;
                }
                if (wow.toString().equalsIgnoreCase("%highWorth%")){
                    wow = highWorth;
                }
                if (wow instanceof String && !wow.toString().equals(".")) {
                    d = new StringBuilder(d).append(wow).append(" ").toString();
                }
                else {
                    d = new StringBuilder(d).append(wow).toString();
                }
            }
            count++;
        }
        pa.sendMessage(d);
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
            case "&f":
                holder = ChatColor.valueOf("WHITE");
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
