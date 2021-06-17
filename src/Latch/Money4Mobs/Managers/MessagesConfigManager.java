package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Money4Mobs;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfigManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration messagesCfg;
    public static File messagesFile;

    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
        //if the mobs.yml does not exist, create it
        if(!messagesFile.exists()){
            messagesFile.createNewFile();
            messagesCfg.set("messages.mobKillerOnMessage", "&aMobKiller message &6on");
            messagesCfg.set("messages.mobKillerOffMessage", "&aMobKiller message &6off");
            messagesCfg.set("messages.accessDeniedMessage", "&cYou do not have access to this command");
            messagesCfg.set("messages.eggSpawnRewardTrueMessage", "&aMoney rewarded from mobs spawned with eggs is set to &6true");
            messagesCfg.set("messages.eggSpawnRewardFalseMessage", "&aMoney rewarded from mobs spawned with eggs is set to &6false");
            messagesCfg.set("messages.spawnerSpawnRewardTrueMessage", "&aMoney rewarded from spawner mobs is set to &true");
            messagesCfg.set("messages.spawnerSpawnRewardFalseMessage", "&aMoney rewarded from spawner mobs is set to &false");
            messagesCfg.set("messages.tamedWolvesRewardTrueMessage", "&aMoney rewarded from mobs killed by tamed wolves set to &6true");
            messagesCfg.set("messages.tamedWolvesRewardFalseMessage", "&aMoney rewarded from mobs killed by tamed wolves set to &false");
            messagesCfg.set("messages.reloadingMessage", "&bReloading Money4Mobs");
            messagesCfg.set("messages.reloadConfirmMessage", "&6Money4Mobs Reload Complete");
            messagesCfg.set("messages.overrideKillMessageTrue", "&aoverrideKillMessage set to &6true");
            messagesCfg.set("messages.overrideKillMessageFalse", "&aoverrideKillMessage set to &6false");
            messagesCfg.set("messages.setLowWorthCommandErrorMessage", "&cError: &7Enter command like this -> /mk setLowWorth [mobName] [amount]");
            messagesCfg.set("messages.setHighWorthCommandErrorMessage", "&cError: &7Enter command like this -> /mk setHighWorth [mobName] [amount]");
            messagesCfg.set("messages.addCustomDropsErrorMessage", "&cError: Custom drops cannot be added to players");
            messagesCfg.set("messages.addCustomDropsCommandErrorMessage", "&cError: &7Enter command like this -> /mk addCustomDrop [mobName] [amount] [chance]");
            messagesCfg.set("messages.addCustomDropInvalidMobErrorMessage", "&cError: &6%mobName% &7is not a valid mob");
            messagesCfg.set("messages.addCustomDropAlreadyPresentErrorMessage", "&cError: &6%itemName% &7is already present as a custom drop");
            messagesCfg.set("messages.addCustomDropSuccessMessage", "&aAdded &6%amount%  %itemName% &ato &6%mobName% &adrops with a &6%chance% % &achance of dropping");
            messagesCfg.set("messages.removeCustomDropSuccessMessage", "&6%itemName%  &adrops removed from &6%mobName% &amobs");
            messagesCfg.set("messages.customDropsDoNotExistErrorMessage", "&cError: &7Drops do not exist for &6%mobName% &amobs");
            messagesCfg.set("messages.customDropsNotEnabledMessage", "&aCustom drops are not enabled for &6%mobName% &amobs");
            messagesCfg.set("messages.setLowWorthTooHighErrorMessage", "&cError: &7Low worth for &6%mobName% %7mobs is higher than the value you are setting");
            messagesCfg.set("messages.setHighWorthTooLowErrorMessage", "&cError: &7High worth for &6%mobName% &7mobs is lower than the value you are setting");
            messagesCfg.set("messages.mobDropInfoMessage", "&6%mobName% &amobs have a &6%chance% % &achance of dropping &6%amount%  %itemName%");
            messagesCfg.set("messages.customDropsNotSetMessage", "&6%mobName% &amobs don't have any custom drops set");
            messagesCfg.set("messages.mobWorthMessage", "&6%mobName% &amobs are worth &6%lowWorth% &adollars");
            messagesCfg.set("messages.mobRangeWorthMessage", "&6%mobName% &amobs are worth between &6%lowWorth% &aand &6%highWorth% &adollars");
            messagesCfg.set("messages.setLowWorthSuccessMessage", "&aLow worth for &6%mobName% &amobs has been set to &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.setHighWorthSuccessMessage", "&aHigh worth for &6%mobName% &amobs has been set to &6%highWorth% &adollar(s)");
            messagesCfg.set("messages.defaultDropsTrueMessage", "&aDefault drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("messages.defaultDropsFalseMessage", "&aDefault drops for &6%mobName% &amobs set to &6false");
            messagesCfg.set("messages.customDropsTrueMessage", "&aCustom drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("messages.customDropsFalseMessage", "&aCustom drops for &6%mobName% &amobs set to &6false");
            messagesCfg.save(messagesFile);

        }
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
