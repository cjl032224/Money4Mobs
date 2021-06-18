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
            // English
            messagesCfg.set("messages.english.mobKillerOnMessage", "&aMobKiller message &6on");
            messagesCfg.set("messages.english.mobKillerOffMessage", "&aMobKiller message &6off");
            messagesCfg.set("messages.english.accessDeniedMessage", "&cYou do not have access to this command");
            messagesCfg.set("messages.english.eggSpawnRewardTrueMessage", "&aMoney rewarded from mobs spawned with eggs is set to &6true");
            messagesCfg.set("messages.english.eggSpawnRewardFalseMessage", "&aMoney rewarded from mobs spawned with eggs is set to &6false");
            messagesCfg.set("messages.english.spawnerSpawnRewardTrueMessage", "&aMoney rewarded from spawner mobs is set to &6true");
            messagesCfg.set("messages.english.spawnerSpawnRewardFalseMessage", "&aMoney rewarded from spawner mobs is set to &6false");
            messagesCfg.set("messages.english.tamedWolvesRewardTrueMessage", "&aMoney rewarded from mobs killed by tamed wolves set to &6true");
            messagesCfg.set("messages.english.tamedWolvesRewardFalseMessage", "&aMoney rewarded from mobs killed by tamed wolves set to &6false");
            messagesCfg.set("messages.english.reloadingMessage", "&bReloading Money4Mobs");
            messagesCfg.set("messages.english.reloadConfirmMessage", "&6Money4Mobs Reload Complete");
            messagesCfg.set("messages.english.overrideKillMessageTrue", "&aoverrideKillMessage set to &6true");
            messagesCfg.set("messages.english.overrideKillMessageFalse", "&aoverrideKillMessage set to &6false");
            messagesCfg.set("messages.english.setLowWorthCommandErrorMessage", "&cError: &7Enter command like this -> /mk setLowWorth [mobName] [amount]");
            messagesCfg.set("messages.english.setHighWorthCommandErrorMessage", "&cError: &7Enter command like this -> /mk setHighWorth [mobName] [amount]");
            messagesCfg.set("messages.english.addCustomDropsErrorMessage", "&cError: Custom drops cannot be added to players");
            messagesCfg.set("messages.english.addCustomDropsCommandErrorMessage", "&cError: &7Enter command like this -> /mk addCustomDrop [mobName] [amount] [chance]");
            messagesCfg.set("messages.english.addCustomDropInvalidMobErrorMessage", "&cError: &6%mobName% &7is not a valid mob");
            messagesCfg.set("messages.english.addCustomDropAlreadyPresentErrorMessage", "&cError: &6%itemName% &7is already present as a custom drop");
            messagesCfg.set("messages.english.addCustomDropSuccessMessage", "&aAdded &6%amount%  %itemName% &ato &6%mobName% &adrops with a &6%chance% % &achance of dropping");
            messagesCfg.set("messages.english.removeCustomDropSuccessMessage", "&6%itemName%  &adrops removed from &6%mobName% &amobs");
            messagesCfg.set("messages.english.customDropsDoNotExistErrorMessage", "&cError: &7Drops do not exist for &6%mobName% &amobs");
            messagesCfg.set("messages.english.customDropsNotEnabledMessage", "&aCustom drops are not enabled for &6%mobName% &amobs");
            messagesCfg.set("messages.english.setLowWorthTooHighErrorMessage", "&cError: &7Low worth for &6%mobName% %7mobs is higher than the value you are setting");
            messagesCfg.set("messages.english.setHighWorthTooLowErrorMessage", "&cError: &7High worth for &6%mobName% &7mobs is lower than the value you are setting");
            messagesCfg.set("messages.english.mobDropInfoMessage", "&6%mobName% &amobs have a &6%chance% % &achance of dropping &6%amount%  %itemName%");
            messagesCfg.set("messages.english.customDropsNotSetMessage", "&6%mobName% &amobs don't have any custom drops set");
            messagesCfg.set("messages.english.mobWorthMessage", "&6%mobName% &amobs are worth &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.english.mobRangeWorthMessage", "&6%mobName% &amobs are worth between &6%lowWorth% &aand &6%highWorth% &adollars");
            messagesCfg.set("messages.english.setLowWorthSuccessMessage", "&aLow worth for &6%mobName% &amobs has been set to &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.english.setHighWorthSuccessMessage", "&aHigh worth for &6%mobName% &amobs has been set to &6%highWorth% &adollar(s)");
            messagesCfg.set("messages.english.defaultDropsTrueMessage", "&aDefault drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("messages.english.defaultDropsFalseMessage", "&aDefault drops for &6%mobName% &amobs set to &6false");
            messagesCfg.set("messages.english.customDropsTrueMessage", "&aCustom drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("messages.english.customDropsFalseMessage", "&aCustom drops for &6%mobName% &amobs set to &6false");

            // French
            messagesCfg.set("messages.french.mobKillerOnMessage", "&aMessage MobKiller &6activé");
            messagesCfg.set("messages.french.mobKillerOffMessage", "&aMessage MobKiller &6désactivé");
            messagesCfg.set("messages.french.accessDeniedMessage", "&cVous n'avez pas accès à cette commande");
            messagesCfg.set("messages.french.eggSpawnRewardTrueMessage", "&aL'argent attribué par les monstres engendrés avec des œufs est défini sur &6vrai");
            messagesCfg.set("messages.french.eggSpawnRewardFalseMessage", "&aL'argent attribué par les monstres engendrés avec des œufs est défini sur &6faux");
            messagesCfg.set("messages.french.spawnerSpawnRewardTrueMessage", "&aMoney récompensé par les spawner mobs est défini sur &6vrai");
            messagesCfg.set("messages.french.spawnerSpawnRewardFalseMessage", "&aL'argent attribué par les spawner mobs est défini sur &6faux");
            messagesCfg.set("messages.french.tamedWolvesRewardTrueMessage", "&aL'argent récompensé des foules tuées par des loups apprivoisés est réglé sur &6vrai");
            messagesCfg.set("messages.french.tamedWolvesRewardFalseMessage", "&aMoney récompensé par des foules tuées par des loups apprivoisés réglé sur &6faux");
            messagesCfg.set("messages.french.reloadingMessage", "&bRecharger Money4Mobs");
            messagesCfg.set("messages.french.reloadConfirmMessage", "&6Money4Mobs Rechargement terminé");
            messagesCfg.set("messages.french.overrideKillMessageTrue", "&aoverrideKillMessage défini sur &6vrai");
            messagesCfg.set("messages.french.overrideKillMessageFalse", "&aoverrideKillMessage défini sur &6faux");
            messagesCfg.set("messages.french.setLowWorthCommandErrorMessage", "&cErreur: &7Entrez la commande comme celle-ci -> /mk setLowWorth [nom_mob] [montant]");
            messagesCfg.set("messages.french.setHighWorthCommandErrorMessage", "&cErreur: &7Entrez la commande comme celle-ci -> /mk setHighWorth [nom_mob] [montant]");
            messagesCfg.set("messages.french.addCustomDropsErrorMessage", "&cErreur: Les drops personnalisés ne peuvent pas être ajoutés aux joueurs");
            messagesCfg.set("messages.french.addCustomDropsCommandErrorMessage", "&cErreur: &7Entrez la commande comme celle-ci -> /mk addCustomDrop [nom_mob] [montant] [chance]");
            messagesCfg.set("messages.french.addCustomDropInvalidMobErrorMessage", "&cErreur: &6%mobName% &7n'est pas un mob valide");
            messagesCfg.set("messages.french.addCustomDropAlreadyPresentErrorMessage", "&cErreur: &6%itemName% &7est déjà présent en tant que drop personnalisé");
            messagesCfg.set("messages.french.addCustomDropSuccessMessage", "&aAjout de &6%amount% %itemName% &aà &6%mobName% &agouttes avec une &6%chance% % &ade chance de tomber");
            messagesCfg.set("messages.french.removeCustomDropSuccessMessage", "&6%itemName% &agouttes supprimées de &6%mobName% &amobs");
            messagesCfg.set("messages.french.customDropsDoNotExistErrorMessage", "&cErreur: &7Drops n''existent pas pour &6%mobName% &amobs");
            messagesCfg.set("messages.french.customDropsNotEnabledMessage", "&aLes largages personnalisés ne sont pas activés pour &6%mobName% &amobs");
            messagesCfg.set("messages.french.setLowWorthTooHighErrorMessage", "&cErreur: &7La valeur faible pour &6%mobName% &7mobs est supérieure à la valeur que vous définissez");
            messagesCfg.set("messages.french.setHighWorthTooLowErrorMessage", "&cErreur: &7Valeur élevée pour &6%mobName% &7mobs est inférieure à la valeur que vous définissez");
            messagesCfg.set("messages.french.mobDropInfoMessage", "'&6%mobName% &amobs ont &6%chance% % &achance de laisser tomber &6%amount%  %itemName%");
            messagesCfg.set("messages.french.customDropsNotSetMessage", "&6%mobName% &amobs n''ont pas de drop personnalisé défini");
            messagesCfg.set("messages.french.mobWorthMessage", "&6%mobName% &amobs valent &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.french.mobRangeWorthMessage", "&6%mobName% &amobs valent entre &6%lowWorth% &aet &6%highWorth% &adollars");
            messagesCfg.set("messages.french.setLowWorthSuccessMessage", "&aFaible valeur pour &6%mobName% &amobs a été défini sur &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.french.setHighWorthSuccessMessage", "&aLa valeur élevée pour &6%mobName% &amobs a été définie sur &6%highWorth% &adollar(s)");
            messagesCfg.set("messages.french.defaultDropsTrueMessage", "&aLes baisses par défaut pour &6% mob Name% et les mobs définis sur &6vrai");
            messagesCfg.set("messages.french.defaultDropsFalseMessage", "&aLa valeur par défaut diminue pour &6%mobName% &amobs défini sur &6faux");
            messagesCfg.set("messages.french.customDropsTrueMessage", "&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6vrai");
            messagesCfg.set("messages.french.customDropsFalseMessage", "&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6faux");

            messagesCfg.save(messagesFile);

        }
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
