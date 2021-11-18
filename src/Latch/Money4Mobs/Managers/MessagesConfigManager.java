package Latch.Money4Mobs.Managers;

import Latch.Money4Mobs.Money4Mobs;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessagesConfigManager {

    private final Money4Mobs plugin = Money4Mobs.getPlugin(Money4Mobs.class);
    public static FileConfiguration messagesCfg;
    public static File messagesFile;
    private static final String CHAT_MENU = "ChatMenu";
    private static final String ACTION_BAR = "ActionBar";

    public void setup() throws IOException {
        // if the Mobs4Money folder does not exist, create the Mobs4Money folder
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
        //if the messages.yml does not exist, create it
        if(!messagesFile.exists()){
            messagesFile.createNewFile();
            // English Messages
            messagesCfg.set("language.english.mobKillerOnMessage.message","&aMobKiller message &6on");
            messagesCfg.set("language.english.mobKillerOffMessage.message","&aMobKiller message &6off");
            messagesCfg.set("language.english.accessDeniedMessage.message","&cYou do not have access to this command");
            messagesCfg.set("language.english.eggSpawnRewardTrueMessage.message","&aMoney rewarded from mobs spawned with eggs is set to &6true");
            messagesCfg.set("language.english.eggSpawnRewardFalseMessage.message","&aMoney rewarded from mobs spawned with eggs is set to &6false");
            messagesCfg.set("language.english.spawnerSpawnRewardTrueMessage.message","&aMoney rewarded from spawner mobs is set to &6true");
            messagesCfg.set("language.english.spawnerSpawnRewardFalseMessage.message","&aMoney rewarded from spawner mobs is set to &6false");
            messagesCfg.set("language.english.tamedWolvesRewardTrueMessage.message","&aMoney rewarded from mobs killed by tamed wolves set to &6true");
            messagesCfg.set("language.english.tamedWolvesRewardFalseMessage.message","&aMoney rewarded from mobs killed by tamed wolves set to &6false");
            messagesCfg.set("language.english.reloadingMessage.message","&bReloading Money4Mobs");
            messagesCfg.set("language.english.reloadConfirmMessage.message","&6Money4Mobs Reload Complete");
            messagesCfg.set("language.english.setLowWorthCommandErrorMessage.message","&cError: &7Enter command like this -> /mk setLowWorth [mobName] [amount]");
            messagesCfg.set("language.english.setHighWorthCommandErrorMessage.message","&cError: &7Enter command like this -> /mk setHighWorth [mobName] [amount]");
            messagesCfg.set("language.english.addCustomDropsErrorMessage.message","&cError: Custom drops cannot be added to players");
            messagesCfg.set("language.english.addCustomDropsCommandErrorMessage.message","&cError: &7Enter command like this -> /mk addCustomDrop [mobName] [itemName] [amount] [chance]");
            messagesCfg.set("language.english.addCustomDropAlreadyPresentErrorMessage.message","&cError: &6%itemName% &7is already present as a custom drop");
            messagesCfg.set("language.english.addCustomDropSuccessMessage.message","&aAdded &6%amount%  %itemName% &ato &6%mobName% &adrops with a &6%chance% % &achance of dropping");
            messagesCfg.set("language.english.removeCustomDropSuccessMessage.message","&6%itemName% &adrops removed from &6%mobName% &amobs");
            messagesCfg.set("language.english.customDropsDoNotExistErrorMessage.message","&cError: &7Drops do not exist for &6%mobName% &amobs");
            messagesCfg.set("language.english.customDropsNotEnabledMessage.message","&aCustom drops are not enabled for &6%mobName% &amobs");
            messagesCfg.set("language.english.setLowWorthTooHighErrorMessage.message","&cError: &7Low worth for &6%mobName% &7mobs is higher than the value you are setting");
            messagesCfg.set("language.english.setHighWorthTooLowErrorMessage.message","&cError: &7High worth for &6%mobName% &7mobs is lower than the value you are setting");
            messagesCfg.set("language.english.mobDropInfoMessage.message","&6%mobName% &amobs have a &6%chance% % &achance of dropping &6%amount%  %itemName%");
            messagesCfg.set("language.english.customDropsNotSetMessage.message","&6%mobName% &amobs don't have any custom drops set");
            messagesCfg.set("language.english.mobWorthMessage.message","&6%mobName% &amobs are worth &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.english.mobRangeWorthMessage.message","&6%mobName% &amobs are worth between &6%lowWorth% &aand &6%highWorth% &adollars");
            messagesCfg.set("language.english.setLowWorthSuccessMessage.message","&aLow worth for &6%mobName% &amobs has been set to &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.english.setHighWorthSuccessMessage.message","&aHigh worth for &6%mobName% &amobs has been set to &6%highWorth% &adollar(s)");
            messagesCfg.set("language.english.defaultDropsTrueMessage.message","&aDefault drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("language.english.defaultDropsFalseMessage.message","&aDefault drops for &6%mobName% &amobs set to &6false");
            messagesCfg.set("language.english.customDropsTrueMessage.message","&aCustom drops for &6%mobName% &amobs set to &6true");
            messagesCfg.set("language.english.customDropsFalseMessage.message","&aCustom drops for &6%mobName% &amobs set to &6false");
            messagesCfg.set("language.english.languageChangeSuccessMessage.message","&aChanged Money4Mobs messages to &6English");
            messagesCfg.set("language.english.moneyRewardedMessage.message","&aYou were given &6$ %amount%  &aand now have &6$ %balance%");
            messagesCfg.set("language.english.moneySubtractedMessage.message","&6$ %amount%  &awas taken and you now have &6$ %balance%");
            // English Message Location
            messagesCfg.set("language.english.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.english.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.english.moneySubtractedMessage.location",ACTION_BAR);

            // French Messages
            messagesCfg.set("language.french.mobKillerOnMessage.message","&aMessage MobKiller &6activé");
            messagesCfg.set("language.french.mobKillerOffMessage.message","&aMessage MobKiller &6désactivé");
            messagesCfg.set("language.french.accessDeniedMessage.message","&cVous n'avez pas accès à cette commande");
            messagesCfg.set("language.french.eggSpawnRewardTrueMessage.message","&aL'argent attribué par les monstres engendrés avec des œufs est défini sur &6vrai");
            messagesCfg.set("language.french.eggSpawnRewardFalseMessage.message","&aL'argent attribué par les monstres engendrés avec des œufs est défini sur &6faux");
            messagesCfg.set("language.french.spawnerSpawnRewardTrueMessage.message","&aMoney récompensé par les spawner mobs est défini sur &6vrai");
            messagesCfg.set("language.french.spawnerSpawnRewardFalseMessage.message","&aL'argent attribué par les spawner mobs est défini sur &6faux");
            messagesCfg.set("language.french.tamedWolvesRewardTrueMessage.message","&aL'argent récompensé des foules tuées par des loups apprivoisés est réglé sur &6vrai");
            messagesCfg.set("language.french.tamedWolvesRewardFalseMessage.message","&aMoney récompensé par des foules tuées par des loups apprivoisés réglé sur &6faux");
            messagesCfg.set("language.french.reloadingMessage.message","&bRecharger Money4Mobs");
            messagesCfg.set("language.french.reloadConfirmMessage.message","&6Money4Mobs Rechargement terminé");
            messagesCfg.set("language.french.setLowWorthCommandErrorMessage.message","&cErreur: &7Entrez la commande comme celle-ci -> /mk setLowWorth [nom_mob] [montant]");
            messagesCfg.set("language.french.setHighWorthCommandErrorMessage.message","&cErreur: &7Entrez la commande comme celle-ci -> /mk setHighWorth [nom_mob] [montant]");
            messagesCfg.set("language.french.addCustomDropsErrorMessage.message","&cErreur: Les drops personnalisés ne peuvent pas être ajoutés aux joueurs");
            messagesCfg.set("language.french.addCustomDropsCommandErrorMessage.message","&cErreur: &7Entrez la commande comme celle-ci -> /mk addCustomDrop [nom_mob] [object-nom] [montant] [chance]");
            messagesCfg.set("language.french.addCustomDropAlreadyPresentErrorMessage.message","&cErreur: &6%itemName% &7est déjà présent en tant que drop personnalisé");
            messagesCfg.set("language.french.addCustomDropSuccessMessage.message","&aAjout de &6%amount%  %itemName% &aà &6%mobName% &agouttes avec une &6%chance% % &ade chance de tomber");
            messagesCfg.set("language.french.removeCustomDropSuccessMessage.message","&6%itemName% &agouttes supprimées de &6%mobName% &amobs");
            messagesCfg.set("language.french.customDropsDoNotExistErrorMessage.message","&cErreur: &7Drops n'existent pas pour &6%mobName% &amobs");
            messagesCfg.set("language.french.customDropsNotEnabledMessage.message","&aLes largages personnalisés ne sont pas activés pour &6%mobName% &amobs");
            messagesCfg.set("language.french.setLowWorthTooHighErrorMessage.message","&cErreur: &7La valeur faible pour &6%mobName% &7mobs est supérieure à la valeur que vous définissez");
            messagesCfg.set("language.french.setHighWorthTooLowErrorMessage.message","&cErreur: &7Valeur élevée pour &6%mobName% &7mobs est inférieure à la valeur que vous définissez");
            messagesCfg.set("language.french.mobDropInfoMessage.message","&6%mobName% &amobs ont &6%chance% % &achance de laisser tomber &6%amount%  %itemName%");
            messagesCfg.set("language.french.customDropsNotSetMessage.message","&6%mobName% &amobs n'ont pas de drop personnalisé défini");
            messagesCfg.set("language.french.mobWorthMessage.message","&6%mobName% &amobs valent &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.french.mobRangeWorthMessage.message","&6%mobName% &amobs valent entre &6%lowWorth% &aet &6%highWorth% &adollars");
            messagesCfg.set("language.french.setLowWorthSuccessMessage.message","&aFaible valeur pour &6%mobName% &amobs a été défini sur &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.french.setHighWorthSuccessMessage.message","&aLa valeur élevée pour &6%mobName% &amobs a été définie sur &6%highWorth% &adollar(s)");
            messagesCfg.set("language.french.defaultDropsTrueMessage.message","&aLes baisses par défaut pour &6% mob Name% et les mobs définis sur &6vrai");
            messagesCfg.set("language.french.defaultDropsFalseMessage.message","&aLa valeur par défaut diminue pour &6%mobName% &amobs défini sur &6faux");
            messagesCfg.set("language.french.customDropsTrueMessage.message","&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6vrai");
            messagesCfg.set("language.french.customDropsFalseMessage.message","&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6faux");
            messagesCfg.set("language.french.languageChangeSuccessMessage.message","&aMessages Money4Mobs modifiés en &6Français");
            messagesCfg.set("language.french.moneyRewardedMessage.message","&aVous avez reçu &6$ %amount% &aet vous avez maintenant &6$ %balance%");
            messagesCfg.set("language.french.moneySubtractedMessage.message","&6$ %amount%  &aa été prise et vous avez maintenant &6$ %balance%");
            // French Message Location
            messagesCfg.set("language.french.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.french.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.french.moneySubtractedMessage.location", ACTION_BAR);

            // Spanish
            messagesCfg.set("language.spanish.mobKillerOnMessage.message","&aMensaje de MobKiller &6en");
            messagesCfg.set("language.spanish.mobKillerOffMessage.message","&aMensaje de MobKiller &6desactivado");
            messagesCfg.set("language.spanish.accessDeniedMessage.message","&cNo tienes acceso a este comando");
            messagesCfg.set("language.spanish.eggSpawnRewardTrueMessage.message","&aEl dinero recompensado por las turbas generadas con huevos se establece en &6verdadero");
            messagesCfg.set("language.spanish.eggSpawnRewardFalseMessage.message","&aEl dinero recompensado por las turbas generadas con huevos se establece en &6falso");
            messagesCfg.set("language.spanish.spawnerSpawnRewardTrueMessage.message","&aEl dinero recompensado por las turbas generadoras se establece en &6verdadero");
            messagesCfg.set("language.spanish.spawnerSpawnRewardFalseMessage.message","&aEl dinero recompensado por las turbas generadoras se establece en &6falso");
            messagesCfg.set("language.spanish.tamedWolvesRewardTrueMessage.message","&aDinero recompensado por turbas asesinadas por lobos domesticados establecido en &6verdadero");
            messagesCfg.set("language.spanish.tamedWolvesRewardFalseMessage.message","&aDinero otorgado por turbas asesinadas por lobos domesticados establecido en &6falso");
            messagesCfg.set("language.spanish.reloadingMessage.message","&bRecarga de Money4Mobs");
            messagesCfg.set("language.spanish.reloadConfirmMessage.message","&6Recarga completa de Money4Mobs");
            messagesCfg.set("language.spanish.setLowWorthCommandErrorMessage.message","&cError: &7ingrese un comando como este -> / mk setLowWorth  [nombre de mob] [monto]");
            messagesCfg.set("language.spanish.setHighWorthCommandErrorMessage.message","&cError: &7ingrese un comando como este -> /mk setHighWorth [nombre de mob] [monto]");
            messagesCfg.set("language.spanish.addCustomDropsErrorMessage.message","&cError: No se pueden agregar caídas personalizadas a las jugadoras");
            messagesCfg.set("language.spanish.addCustomDropsCommandErrorMessage.message","&cError: &7ingrese un comando como este -> /mk addCustomDrop [nombre de mob] [articulo-nombre] [monto] [oportunidad]");
            messagesCfg.set("language.spanish.addCustomDropAlreadyPresentErrorMessage.message","&cError: &6%itemName% &7ya está presente como una gota personalizada");
            messagesCfg.set("language.spanish.addCustomDropSuccessMessage.message","&aSe agregó &6%amount% %itemName% &aa &6%mobName% &agotas con un &6%chance% &ade posibilidad de caer");
            messagesCfg.set("language.spanish.removeCustomDropSuccessMessage.message","&6%itemName% &agotas eliminadas de &6%mobName% &amobs");
            messagesCfg.set("language.spanish.customDropsDoNotExistErrorMessage.message","&cError: &7Las gotas no existen para &6%mobName% &amobs");
            messagesCfg.set("language.spanish.customDropsNotEnabledMessage.message","&aLas gotas personalizadas no están habilitadas para &6%mobName% &amobs");
            messagesCfg.set("language.spanish.setLowWorthTooHighErrorMessage.message","&cError: &7Baja valor para &6%mobName% &7mobs es más alta que el valor que está configurando");
            messagesCfg.set("language.spanish.setHighWorthTooLowErrorMessage.message","&7Alto valor para &6%mobName% &7mobs es menor que el valor que está estableciendo");
            messagesCfg.set("language.spanish.mobDropInfoMessage.message","&6%mobName% &amobs tener un &6%chance% % &aposibilidad de caer &6%amount%  %itemName%");
            messagesCfg.set("language.spanish.customDropsNotSetMessage.message","&6%mobName% &amobs no tengo ningún conjunto de gotas personalizado");
            messagesCfg.set("language.spanish.mobWorthMessage.message","&6%mobName% &amobs valen &6%lowWorth% &aadólar(s)");
            messagesCfg.set("language.spanish.mobRangeWorthMessage.message","&6%mobName% &amobs valen entre &6%lowWorth% &ay &6%highWorth% &adolares");
            messagesCfg.set("language.spanish.setLowWorthSuccessMessage.message","&aBaja valor para &6%mobName% &amobs se ha establecido en &6%lowWorth% &adólar(s)");
            messagesCfg.set("language.spanish.setHighWorthSuccessMessage.message","&aAlto valor para &6%mobName% &amobs se ha establecido en &6%highWorth% &adólar(s)");
            messagesCfg.set("language.spanish.defaultDropsTrueMessage.message","&aGotas predeterminadas para &6%mobName% &amobs ajustado a &6verdadero");
            messagesCfg.set("language.spanish.defaultDropsFalseMessage.message","&aGotas predeterminadas para &6%mobName% &amobs ajustado a &6falso");
            messagesCfg.set("language.spanish.customDropsTrueMessage.message","&aGotas personalizadas para &6%mobName% &amobs ajustado a &6verdadero");
            messagesCfg.set("language.spanish.customDropsFalseMessage.message","&aGotas personalizadas para &6%mobName% &amobs ajustado a &6falso");
            messagesCfg.set("language.spanish.languageChangeSuccessMessage.message","&aSe cambiaron los mensajes de Money4Mobs a &6Español");
            messagesCfg.set("language.spanish.moneyRewardedMessage.message","&aLe dieron &6$ %amount%  &ay ahora tengo &6$ %balance%");
            messagesCfg.set("language.spanish.moneySubtractedMessage.message","&6$ %amount%  &afue tomado y ahora tienes &6$ %balance%");
            // Spanish Message Location
            messagesCfg.set("language.spanish.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.spanish.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.spanish.moneySubtractedMessage.location", ACTION_BAR);

            // German
            messagesCfg.set("language.german.mobKillerOnMessage.message","&aMobKiller nachricht &6am");
            messagesCfg.set("language.german.mobKillerOffMessage.message","&aMobKiller nachricht &6aus");
            messagesCfg.set("language.german.accessDeniedMessage.message","&cSie haben keinen Zugriff auf diesen Befehl");
            messagesCfg.set("language.german.eggSpawnRewardTrueMessage.message","&aGeld, das von Mobs, die mit Eiern gespawnt wurden, belohnt wird, wird auf &6wahr gesetzt");
            messagesCfg.set("language.german.eggSpawnRewardFalseMessage.message","&aGeld, das von Mobs, die mit Eiern gespawnt wurden, belohnt wird, wird auf &6falsch gesetzt");
            messagesCfg.set("language.german.spawnerSpawnRewardTrueMessage.message","&aGeld, das von Spawner-Mobs belohnt wird, ist eingestellt auf &6wahr");
            messagesCfg.set("language.german.spawnerSpawnRewardFalseMessage.message","&aGeld, das von Spawner-Mobs belohnt wird, ist eingestellt auf &6falsch");
            messagesCfg.set("language.german.tamedWolvesRewardTrueMessage.message","&aGeld, das von Mobs belohnt wird, die von gezähmten Wölfen getötet wurden, setzen auf &6wahr");
            messagesCfg.set("language.german.tamedWolvesRewardFalseMessage.message","&aGeld, das von Mobs belohnt wird, die von gezähmten Wölfen getötet wurden, setzen auf &6falsch");
            messagesCfg.set("language.german.reloadingMessage.message","&bMoney4Mobs Neuladen");
            messagesCfg.set("language.german.reloadConfirmMessage.message","&6Money4Mobs Nachladen abgeschlossen");
            messagesCfg.set("language.german.setLowWorthCommandErrorMessage.message","&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk setLowWorth [mobName] [menge]");
            messagesCfg.set("language.german.setHighWorthCommandErrorMessage.message","&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk setHighWorth [mobName] [menge]");
            messagesCfg.set("language.german.addCustomDropsErrorMessage.message","&cError: Benutzerdefinierte Drops können Spielern nicht hinzugefügt werden");
            messagesCfg.set("language.german.addCustomDropsCommandErrorMessage.message","&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk addCustomDrop [mobName] [artikelname] [menge] [chance]");
            messagesCfg.set("language.german.addCustomDropAlreadyPresentErrorMessage.message","&cError: &6%itemName% &7ist bereits als benutzerdefinierter Tropfen vorhanden");
            messagesCfg.set("language.german.addCustomDropSuccessMessage.message","&aAdded &6%amount%  %itemName% &azu &6%mobName% &a fällt mit a&6%chance% % &achance zu fallen");
            messagesCfg.set("language.german.removeCustomDropSuccessMessage.message","&6%itemName%  &atropfen entfernt von &6%mobName% &amobs");
            messagesCfg.set("language.german.customDropsDoNotExistErrorMessage.message","&cError: &7Tropfen gibt es nicht für &6%mobName% &amobs");
            messagesCfg.set("language.german.customDropsNotEnabledMessage.message","&aBenutzerdefinierte Drops sind nicht aktiviert für &6%mobName% &amobs");
            messagesCfg.set("language.german.setLowWorthTooHighErrorMessage.message","&cError: &7Niedriger Wert für &6%mobName% &7mobs ist höher als der von dir eingestellte Wert");
            messagesCfg.set("language.german.setHighWorthTooLowErrorMessage.message","&cError: &7Hoher Wert für &6%mobName% &7mobs ist niedriger als der von Ihnen eingestellte Wert");
            messagesCfg.set("language.german.mobDropInfoMessage.message","&6%mobName% &amobs habe einen &6%chance% % &achance fallen zu lassen &6%amount%  %itemName%");
            messagesCfg.set("language.german.customDropsNotSetMessage.message","&6%mobName% &amobs habe keine benutzerdefinierten Drops");
            messagesCfg.set("language.german.mobWorthMessage.message","&6%mobName% &amobs sind es wert &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.german.mobRangeWorthMessage.message","&6%mobName% &amobs sind es wert zwischen &6%lowWorth% &aund &6%highWorth% &adollars");
            messagesCfg.set("language.german.setLowWorthSuccessMessage.message","&aNiedriger Wert für &6%mobName% &amobs wurde eingestellt auf &6%lowWorth% &adollar(s)");
            messagesCfg.set("language.german.setHighWorthSuccessMessage.message","&aHoher Wert für &6%mobName% &amobs wurde eingestellt auf &6%highWorth% &adollar(s)");
            messagesCfg.set("language.german.defaultDropsTrueMessage.message","&aStandardtropfen für &6%mobName% &amobs einstellen &6wahr");
            messagesCfg.set("language.german.defaultDropsFalseMessage.message","&aStandardtropfen für &6%mobName% &amobs einstellen &6falsch");
            messagesCfg.set("language.german.customDropsTrueMessage.message","&aBenutzerdefinierte Tropfen für &6%mobName% &amobs einstellen &6wahr");
            messagesCfg.set("language.german.customDropsFalseMessage.message","&aBenutzerdefinierte Tropfen für &6%mobName% &amobs einstellen &6falsch");
            messagesCfg.set("language.german.languageChangeSuccessMessage.message","&aMoney4Mobs-Nachrichten auf &6Deutsch &ageändert");
            messagesCfg.set("language.german.moneyRewardedMessage.message","&aSie wurden gegeben &6$ %amount%  &aund jetzt habe &6$ %balance%");
            messagesCfg.set("language.german.moneySubtractedMessage.message","&6$ %amount%  &awurde genommen und du hast jetzt &6$ %balance%");
            // German Message Location
            messagesCfg.set("language.german.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.german.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.german.moneySubtractedMessage.location", ACTION_BAR);

            // Italian
            messagesCfg.set("language.italian.mobKillerOnMessage.message","&aMessaggio MobKiller &6attivo");
            messagesCfg.set("language.italian.mobKillerOffMessage.message","&aMessaggio MobKiller &6disattivato");
            messagesCfg.set("language.italian.accessDeniedMessage.message","&cNon hai accesso a questo comando");
            messagesCfg.set("language.italian.eggSpawnRewardTrueMessage.message","&aIl denaro ricompensato dai mob generati con le uova è impostato su &6vero");
            messagesCfg.set("language.italian.eggSpawnRewardFalseMessage.message","&aIl denaro ricompensato dai mob generati con le uova è impostato su &6falso");
            messagesCfg.set("language.italian.spawnerSpawnRewardTrueMessage.message","&aIl denaro ricompensato dai mob generatori è impostato su &6vero");
            messagesCfg.set("language.italian.spawnerSpawnRewardFalseMessage.message","&aIl denaro ricompensato dai mob generatori è impostato su &6falso");
            messagesCfg.set("language.italian.tamedWolvesRewardTrueMessage.message","&aDenaro ricompensato da mob uccisi da lupi addomesticati impostati su &6vero");
            messagesCfg.set("language.italian.tamedWolvesRewardFalseMessage.message","&aDenaro ricompensato da mob uccisi da lupi addomesticati impostati su &6falso");
            messagesCfg.set("language.italian.reloadingMessage.message","&bRicaricare Money4Mobs");
            messagesCfg.set("language.italian.reloadConfirmMessage.message","&6Money4Mobs Ricarica completata");
            messagesCfg.set("language.italian.setLowWorthCommandErrorMessage.message","&cErrore: &7Inserisci il comando in questo modo -> /mk setLowWorth [mobNome] [quantità]");
            messagesCfg.set("language.italian.setHighWorthCommandErrorMessage.message","&cErrore: &7Inserisci il comando in questo modo -> /mk setHighWorth [mobNome] [quantità]");
            messagesCfg.set("language.italian.addCustomDropsErrorMessage.message","&cErrore: Custom drops cannot be added to players");
            messagesCfg.set("language.italian.addCustomDropsCommandErrorMessage.message","&cErrore: &7Inserisci il comando in questo modo -> /mk addCustomDrop [mobNome] [nome dell'elemento] [quantità] [opportunità]");
            messagesCfg.set("language.italian.addCustomDropAlreadyPresentErrorMessage.message","&cErrore: &6%itemName% &7è già presente come drop personalizzato");
            messagesCfg.set("language.italian.addCustomDropSuccessMessage.message","&aAggiunto &6%amount%  %itemName% &aper &6%mobName% &agocce con a &6%chance% % &apossibilità di cadere");
            messagesCfg.set("language.italian.removeCustomDropSuccessMessage.message","&6%itemName%  &agocce rimosse da &6%mobName% &amobs");
            messagesCfg.set("language.italian.customDropsDoNotExistErrorMessage.message","&cErrore: &7Le gocce non esistono per &6%mobName% &amobs");
            messagesCfg.set("language.italian.customDropsNotEnabledMessage.message","&aI drop personalizzati non sono abilitati per &6%mobName% &amobs");
            messagesCfg.set("language.italian.setLowWorthTooHighErrorMessage.message","&cErrore: &7Valore basso per &6%mobName% &7mobs è superiore al valore che stai impostando");
            messagesCfg.set("language.italian.setHighWorthTooLowErrorMessage.message","&cErrore: &7Grande valore per &6%mobName% &7mobs è inferiore al valore che stai impostando");
            messagesCfg.set("language.italian.mobDropInfoMessage.message","&6%mobName% &amobs avere un &6%chance% % &apossibilità di cadere &6%amount%  %itemName%");
            messagesCfg.set("language.italian.customDropsNotSetMessage.message","&6%mobName% &amobs non hai set di drop personalizzati");
            messagesCfg.set("language.italian.mobWorthMessage.message","&6%mobName% &amobs valgono &6%lowWorth% &adollar(o/i)");
            messagesCfg.set("language.italian.mobRangeWorthMessage.message","&6%mobName% &amobs valgono tra &6%lowWorth% &ae &6%highWorth% &adollari");
            messagesCfg.set("language.italian.setLowWorthSuccessMessage.message","&aValore basso per &6%mobName% &amobs è stato impostato su &6%lowWorth% &adollar(o/i)");
            messagesCfg.set("language.italian.setHighWorthSuccessMessage.message","&aGrande valore per &6%mobName% &amobs è stato impostato su &6%highWorth% &adollar(o/i)");
            messagesCfg.set("language.italian.defaultDropsTrueMessage.message","&aGocce predefinite per &6%mobName% &amobs impostato &6vero");
            messagesCfg.set("language.italian.defaultDropsFalseMessage.message","&aGocce predefinite per &6%mobName% &amobs impostato &6falso");
            messagesCfg.set("language.italian.customDropsTrueMessage.message","&aDrop personalizzati per &6%mobName% &amobs impostato &6vero");
            messagesCfg.set("language.italian.customDropsFalseMessage.message","&aDrop personalizzati per &6%mobName% &amobs impostato &6falso");
            messagesCfg.set("language.italian.languageChangeSuccessMessage.message","&aMessaggi di Money4Mobs modificati in &6Italiano");
            messagesCfg.set("language.italian.moneyRewardedMessage.message","&ati è stato dato &6$ %amount%  &ae ora ho &6$ %balance%");
            messagesCfg.set("language.italian.moneySubtractedMessage.message","&6$ %amount%  &aè stato preso e ora hai &6$ %balance%");
            // Italian Message Location
            messagesCfg.set("language.italian.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.italian.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.italian.moneySubtractedMessage.location",ACTION_BAR);

            // Russian
            messagesCfg.set("language.russian.mobKillerOnMessage.message","&aСообщение MobKiller &6включен");
            messagesCfg.set("language.russian.mobKillerOffMessage.message","&aСообщение MobKiller &6выключенный");
            messagesCfg.set("language.russian.accessDeniedMessage.message","&cУ вас нет доступа к этой команде");
            messagesCfg.set("language.russian.eggSpawnRewardTrueMessage.message","&aДенежная награда от мобов, порожденных яйцами, установлена в размере &6правда");
            messagesCfg.set("language.russian.eggSpawnRewardFalseMessage.message","&aДенежная награда от мобов, порожденных яйцами, установлена в размере &6ложный");
            messagesCfg.set("language.russian.spawnerSpawnRewardTrueMessage.message","&aДенежная награда от создателей мобов установлена в размере &6правда");
            messagesCfg.set("language.russian.spawnerSpawnRewardFalseMessage.message","&aДенежная награда от создателей мобов установлена в размере &6ложный");
            messagesCfg.set("language.russian.tamedWolvesRewardTrueMessage.message","&aДеньги, полученные от мобов, убитых прирученными волками, установленными в &6правда");
            messagesCfg.set("language.russian.tamedWolvesRewardFalseMessage.message","&aДеньги, полученные от мобов, убитых прирученными волками, установленными в &6ложный");
            messagesCfg.set("language.russian.reloadingMessage.message","&bПополнение Money4Mobs");
            messagesCfg.set("language.russian.reloadConfirmMessage.message","&6Money4Mobs Перезагрузка завершена");
            messagesCfg.set("language.russian.setLowWorthCommandErrorMessage.message","&cОшибка: &7Введите команду, подобную этой -> /mk setLowWorth [mob-название] [количество]");
            messagesCfg.set("language.russian.setHighWorthCommandErrorMessage.message","&cОшибка: &7Введите команду, подобную этой -> /mk setHighWorth [mob-название] [количество]");
            messagesCfg.set("language.russian.addCustomDropsErrorMessage.message","&cОшибка: Пользовательские дропы не могут быть добавлены игрокам");
            messagesCfg.set("language.russian.addCustomDropsCommandErrorMessage.message","&cОшибка: &7Введите команду, подобную этой -> /mk addCustomDrop [mob-название] [название предмета] [количество] [шанс]");
            messagesCfg.set("language.russian.addCustomDropAlreadyPresentErrorMessage.message","&cОшибка: &6%itemName% &7уже присутствует в виде кастомного дропа");
            messagesCfg.set("language.russian.addCustomDropSuccessMessage.message","&aДобавлен &6%amount%  %itemName% &aк &6%mobName% &aпадает с &6%chance% % &aшанс упасть");
            messagesCfg.set("language.russian.removeCustomDropSuccessMessage.message","&6%itemName%  &aкапли удалены из &6%mobName% &amobs");
            messagesCfg.set("language.russian.customDropsDoNotExistErrorMessage.message","&cОшибка: &7Капли не существуют для &6%mobName% &amobs");
            messagesCfg.set("language.russian.customDropsNotEnabledMessage.message","&aПользовательские сбросы не включены для &6%mobName% &amobs");
            messagesCfg.set("language.russian.setLowWorthTooHighErrorMessage.message","&cОшибка: &7Низкая ценность для &6%mobName% &7mobs выше, чем значение, которое вы устанавливаете");
            messagesCfg.set("language.russian.setHighWorthTooLowErrorMessage.message","&cОшибка: &7Высокая ценность для &6%mobName% &7mobs ниже установленного вами значения");
            messagesCfg.set("language.russian.mobDropInfoMessage.message","&6%mobName% &amobs есть &6%chance% % &aшанс упасть &6%amount%  %itemName%");
            messagesCfg.set("language.russian.customDropsNotSetMessage.message","&6%mobName% &amobs нет никаких настраиваемых дропов");
            messagesCfg.set("language.russian.mobWorthMessage.message","&6%mobName% &amobs стоит &6%lowWorth% &aдоллар(ы)");
            messagesCfg.set("language.russian.mobRangeWorthMessage.message","&6%mobName% &amobs стоят между &6%lowWorth% &aand &6%highWorth% &adollars");
            messagesCfg.set("language.russian.setLowWorthSuccessMessage.message","&aНизкая ценность для &6%mobName% &amobs был установлен включен &6%lowWorth% &aдоллар(ы)");
            messagesCfg.set("language.russian.setHighWorthSuccessMessage.message","&aВысокая ценность для &6%mobName% &amobs был установлен включен &6%highWorth% &aдоллар(ы)");
            messagesCfg.set("language.russian.defaultDropsTrueMessage.message","&aПо умолчанию выпадает для &6%mobName% &amobs установлен в &6правда");
            messagesCfg.set("language.russian.defaultDropsFalseMessage.message","&aПо умолчанию выпадает для &6%mobName% &amobs установлен в &6ложный");
            messagesCfg.set("language.russian.customDropsTrueMessage.message","&aПользовательские дропы для &6%mobName% &amobs установлен в &6правда");
            messagesCfg.set("language.russian.customDropsFalseMessage.message","&aПользовательские дропы для &6%mobName% &amobs установлен в &6ложный");
            messagesCfg.set("language.russian.languageChangeSuccessMessage.message","&aИзменены сообщения Money4Mobs включен &6русский &aязык");
            messagesCfg.set("language.russian.moneyRewardedMessage.message","&aВам дали &6$ %amount%  &aи теперь есть &6$ %balance%");
            messagesCfg.set("language.russian.moneySubtractedMessage.message","&6$ %amount%  &aбыл взят, и теперь у вас есть &6$ %balance%");
            // Russian Message Location
            messagesCfg.set("language.russian.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.russian.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.russian.moneySubtractedMessage.location",ACTION_BAR);

            // Hindi
            messagesCfg.set("language.hindi.mobKillerOnMessage.message","&aMobKiller संदेश &6पर");
            messagesCfg.set("language.hindi.mobKillerOffMessage.message","&aMobKiller संदेश &6बंद");
            messagesCfg.set("language.hindi.accessDeniedMessage.message","&cआपके पास इस आदेश तक पहुंच नहीं है");
            messagesCfg.set("language.hindi.eggSpawnRewardTrueMessage.message","&aअंडे से पैदा हुई भीड़ से मिलने वाला इनाम इस पर सेट है &6सच");
            messagesCfg.set("language.hindi.eggSpawnRewardFalseMessage.message","&aअंडे से पैदा हुई भीड़ से मिलने वाला इनाम इस पर सेट है &6असत्य");
            messagesCfg.set("language.hindi.spawnerSpawnRewardTrueMessage.message","&aस्पॉनर मॉब से मिलने वाला इनाम इस पर सेट है &6सच");
            messagesCfg.set("language.hindi.spawnerSpawnRewardFalseMessage.message","&aस्पॉनर मॉब से मिलने वाला इनाम इस पर सेट है &6असत्य");
            messagesCfg.set("language.hindi.tamedWolvesRewardTrueMessage.message","&aपालतू भेड़ियों द्वारा मारे गए भीड़ से इनाम के तौर पर दिया जाने वाला पैसा &6सच");
            messagesCfg.set("language.hindi.tamedWolvesRewardFalseMessage.message","&aपालतू भेड़ियों द्वारा मारे गए भीड़ से इनाम के तौर पर दिया जाने वाला पैसा &6असत्य");
            messagesCfg.set("language.hindi.reloadingMessage.message","&bMoney4Mobs को पुनः लोड करना");
            messagesCfg.set("language.hindi.reloadConfirmMessage.message","&6Money4Mobs पुनः लोड पूर्ण");
            messagesCfg.set("language.hindi.setLowWorthCommandErrorMessage.message","&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk setLowWorth [mob-नाम] [रकम]");
            messagesCfg.set("language.hindi.setHighWorthCommandErrorMessage.message","&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk setHighWorth [mob-नाम] [रकम]");
            messagesCfg.set("language.hindi.addCustomDropsErrorMessage.message","&cत्रुटि: खिलाड़ियों में कस्टम ड्रॉप्स नहीं जोड़े जा सकते");
            messagesCfg.set("language.hindi.addCustomDropsCommandErrorMessage.message","&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk addCustomDrop [mob-नाम] [वस्तु का नाम] [रकम] [मोका]");
            messagesCfg.set("language.hindi.addCustomDropAlreadyPresentErrorMessage.message","&cत्रुटि: &6%itemName% &7कस्टम ड्रॉप के रूप में पहले से मौजूद है");
            messagesCfg.set("language.hindi.addCustomDropSuccessMessage.message","&aजोड़ा &6%amount%  %itemName% &aसेवा मेरे &6%mobName% &aए के साथ बूँदें &6%chance% % &aगिरने की संभावना");
            messagesCfg.set("language.hindi.removeCustomDropSuccessMessage.message","&6%itemName%  &aसे हटाई गई बूंदें &6%mobName% &amobs");
            messagesCfg.set("language.hindi.customDropsDoNotExistErrorMessage.message","&cत्रुटि: &7बूंदों के लिए मौजूद नहीं है &6%mobName% &amobs");
            messagesCfg.set("language.hindi.customDropsNotEnabledMessage.message","&aइसके लिए कस्टम ड्रॉप सक्षम नहीं हैं &6%mobName% &amobs");
            messagesCfg.set("language.hindi.setLowWorthTooHighErrorMessage.message","&cत्रुटि: &7कम मूल्य &6%mobName% &7mobs आपके द्वारा सेट किए जा रहे मान से अधिक है");
            messagesCfg.set("language.hindi.setHighWorthTooLowErrorMessage.message","&cत्रुटि: &7उच्च मूल्य के लिए &6%mobName% &7mobs आपके द्वारा सेट किए जा रहे मान से कम है");
            messagesCfg.set("language.hindi.mobDropInfoMessage.message","&6%mobName% &amobs लीजिये &6%chance% % &aगिरने की संभावना &6%amount%  %itemName%");
            messagesCfg.set("language.hindi.customDropsNotSetMessage.message","&6%mobName% &amobs कोई कस्टम ड्रॉप सेट नहीं है");
            messagesCfg.set("language.hindi.mobWorthMessage.message","&6%mobName% &amobs लायक हैं&6%lowWorth% &aडॉलर");
            messagesCfg.set("language.hindi.mobRangeWorthMessage.message","&6%mobName% &amobs के बीच लायक हैं &6%lowWorth% &aand &6%highWorth% &aडॉलर");
            messagesCfg.set("language.hindi.setLowWorthSuccessMessage.message","&aकम मूल्य &6%mobName% &amobs पर सेट किया गया है &6%lowWorth% &aडॉलर");
            messagesCfg.set("language.hindi.setHighWorthSuccessMessage.message","&aउच्च मूल्य के लिए &6%mobName% &amobs पर सेट किया गया है &6%highWorth% &aडॉलर");
            messagesCfg.set("language.hindi.defaultDropsTrueMessage.message","&aके लिए डिफ़ॉल्ट बूँदें &6%mobName% &amobs करने के लिए सेट &6सच");
            messagesCfg.set("language.hindi.defaultDropsFalseMessage.message","&aके लिए डिफ़ॉल्ट बूँदें &6%mobName% &amobs करने के लिए सेट &6असत्य");
            messagesCfg.set("language.hindi.customDropsTrueMessage.message","&aके लिए कस्टम बूँदें &6%mobName% &amobs करने के लिए सेट &6सच");
            messagesCfg.set("language.hindi.customDropsFalseMessage.message","&aके लिए कस्टम बूँदें &6%mobName% &amobs करने के लिए सेट &6असत्य");
            messagesCfg.set("language.hindi.languageChangeSuccessMessage.message","&aMoney4Mobs संदेशों को &6हिंदी &aमें बदल दिया");
            messagesCfg.set("language.hindi.moneyRewardedMessage.message","&aतुमको दिया गया था &6$ %amount%  &aऔर अब है &6$ %balance%");
            messagesCfg.set("language.hindi.moneySubtractedMessage.message","&6$ %amount%  &aलिया गया था और अब आपके पास है &6$ %balance%");
            // Hindi Message Location
            messagesCfg.set("language.hindi.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.hindi.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.hindi.moneySubtractedMessage.location",ACTION_BAR);

            // Chinese Traditional
            messagesCfg.set("language.chinese_traditional.mobKillerOnMessage.message","&6已開啟 &aMobKiller。");
            messagesCfg.set("language.chinese_traditional.mobKillerOffMessage.message","&6已關閉 &aMobKiller。 ");
            messagesCfg.set("language.chinese_traditional.accessDeniedMessage.message","&c你沒有使用該指令的權限。");
            messagesCfg.set("language.chinese_traditional.eggSpawnRewardTrueMessage.message","&a使用生怪蛋生成的生物殺死獲得金錢設定為 &6true。");
            messagesCfg.set("language.chinese_traditional.eggSpawnRewardFalseMessage.message","&a使用生怪蛋生成的生物殺死獲得金錢設定為 &6false。");
            messagesCfg.set("language.chinese_traditional.spawnerSpawnRewardTrueMessage.message","&a使用生怪磚生成的生物殺死獲得金錢設定為 &6true。");
            messagesCfg.set("language.chinese_traditional.spawnerSpawnRewardFalseMessage.message","&a使用生怪磚生成的生物殺死獲得金錢設定為 &6false。");
            messagesCfg.set("language.chinese_traditional.tamedWolvesRewardTrueMessage.message","&a馴服的狼殺死生物獲得金錢設定為 &6true。");
            messagesCfg.set("language.chinese_traditional.tamedWolvesRewardFalseMessage.message","&a馴服的狼殺死生物獲得金錢設定為 &6false。");
            messagesCfg.set("language.chinese_traditional.reloadingMessage.message","&bMoney4Mobs 正在重新載入。");
            messagesCfg.set("language.chinese_traditional.reloadConfirmMessage.message","&6Money4Mobs 已經重新載入。");
            messagesCfg.set("language.chinese_traditional.setLowWorthCommandErrorMessage.message","&c錯誤 ： &7用法說明 -> /mk setLowWorth [生物名稱] [數量]。");
            messagesCfg.set("language.chinese_traditional.setHighWorthCommandErrorMessage.message","&c錯誤 ： &7用法說明 -> /mk setHighWorth [生物名稱] [數量]。");
            messagesCfg.set("language.chinese_traditional.addCustomDropsErrorMessage.message","&c錯誤 ： 無法新增玩家自訂義掉落物。");
            messagesCfg.set("language.chinese_traditional.addCustomDropsCommandErrorMessage.message","&c錯誤 ： &7用法說明 -> /mk addCustomDrop [生物名稱] [物品名稱] [數量] [機率]。");
            messagesCfg.set("language.chinese_traditional.addCustomDropAlreadyPresentErrorMessage.message","&c錯誤 ： &6%itemName% &7已經設定自定義掉落。");
            messagesCfg.set("language.chinese_traditional.addCustomDropSuccessMessage.message","&a新增 &6%chance% % &a的機率讓生物 &6%mobName% &a掉落 &6%amount% 個 %itemName% &a。");
            messagesCfg.set("language.chinese_traditional.removeCustomDropSuccessMessage.message","&a刪除生物 &6%mobName% &a掉落物 &6%itemName% &a。");
            messagesCfg.set("language.chinese_traditional.customDropsDoNotExistErrorMessage.message","&c錯誤 ： &7生物 &6%mobName% &7不存在掉落物。");
            messagesCfg.set("language.chinese_traditional.customDropsNotEnabledMessage.message","&a未開啟生物 &6%mobName% &a的自訂義掉落。");
            messagesCfg.set("language.chinese_traditional.setLowWorthTooHighErrorMessage.message","&c錯誤 ： &7生物 &6%mobName% &7的最低價值高於你設定的價值。");
            messagesCfg.set("language.chinese_traditional.setHighWorthTooLowErrorMessage.message","&c錯誤 ： &7生物 &6%mobName% &7的最高價值低於你設定的價值。");
            messagesCfg.set("language.chinese_traditional.mobDropInfoMessage.message","&a生物 &6%mobName% &a有 &6%chance% % &a的機率掉落 &6%amount% 個 %itemName% &a。");
            messagesCfg.set("language.chinese_traditional.customDropsNotSetMessage.message","&a生物 &6%mobName% &a沒有任何自定義掉落設定。");
            messagesCfg.set("language.chinese_traditional.mobWorthMessage.message","&a生物 &6%mobName% &a價值 &6%lowWorth% 元 &a。");
            messagesCfg.set("language.chinese_traditional.mobRangeWorthMessage.message","&a生物 &6%mobName% &a價值介於 &6%lowWorth% &a到 &6%highWorth% 元 &a之間。");
            messagesCfg.set("language.chinese_traditional.setLowWorthSuccessMessage.message","&a已設定生物 &6%mobName% &a的最低價值為 &6%lowWorth% 元 &a。");
            messagesCfg.set("language.chinese_traditional.setHighWorthSuccessMessage.message","&a已設定生物 &6%mobName% &a的最高價值為 &6%highWorth% 元 &a。");
            messagesCfg.set("language.chinese_traditional.defaultDropsTrueMessage.message","&a生物 &6%mobName% &a的預設掉落物調整成 &6true。");
            messagesCfg.set("language.chinese_traditional.defaultDropsFalseMessage.message","&a生物 &6%mobName% &a的預設掉落物調整成 &6false。");
            messagesCfg.set("language.chinese_traditional.customDropsTrueMessage.message","&a生物 &6%mobName% &a的自訂義掉落物調整成 &6true。");
            messagesCfg.set("language.chinese_traditional.customDropsFalseMessage.message","&a生物 &6%mobName% &a的自訂義掉落物調整成 &6false。");
            messagesCfg.set("language.chinese_traditional.languageChangeSuccessMessage.message","&a已設定 Money4Mobs 的語言為 &6繁體中文");
            messagesCfg.set("language.chinese_traditional.moneyRewardedMessage.message","&a你被給予 &6$ %amount% 元 &a，現在餘額 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_traditional.moneySubtractedMessage.message","&a你被拿走 &6$ %amount% 元 &a，現在餘額 &6$ %balance% 元 &a。");
            // Chinese Traditional Message Location
            messagesCfg.set("language.chinese_traditional.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_traditional.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_traditional.moneySubtractedMessage.location",ACTION_BAR);

            // Chinese Simplified
            messagesCfg.set("language.chinese_simplified.mobKillerOnMessage.message","&6已开启 &aMobKiller。");
            messagesCfg.set("language.chinese_simplified.mobKillerOffMessage.message","&6已关闭 &aMobKiller。");
            messagesCfg.set("language.chinese_simplified.accessDeniedMessage.message","&c你没有使用该命令的权限。");
            messagesCfg.set("language.chinese_simplified.eggSpawnRewardTrueMessage.message","&a使用生怪蛋生成的生物杀死获取金钱设定为 &6true。");
            messagesCfg.set("language.chinese_simplified.eggSpawnRewardFalseMessage.message","&a使用生怪蛋生成的生物杀死获取金钱设定为 &6false。");
            messagesCfg.set("language.chinese_simplified.spawnerSpawnRewardTrueMessage.message","&a使用生怪砖生成的生物杀死获取金钱设定为 &6true。");
            messagesCfg.set("language.chinese_simplified.spawnerSpawnRewardFalseMessage.message","&a使用生怪砖生成的生物杀死获取金钱设定为 &6false。");
            messagesCfg.set("language.chinese_simplified.tamedWolvesRewardTrueMessage.message","&a驯服的狼杀死生物获取金钱设定为 &6true。");
            messagesCfg.set("language.chinese_simplified.tamedWolvesRewardFalseMessage.message","&a驯服的狼杀死生物获取金钱设定为 &6false。");
            messagesCfg.set("language.chinese_simplified.reloadingMessage.message","&bMoney4Mobs 正在重新载入。");
            messagesCfg.set("language.chinese_simplified.reloadConfirmMessage.message","&6Money4Mobs 已经重新载入。");
            messagesCfg.set("language.chinese_simplified.setLowWorthCommandErrorMessage.message","&c错误 ： &7用法说明 -> /mk setLowWorth [生物名称] [数量]。");
            messagesCfg.set("language.chinese_simplified.setHighWorthCommandErrorMessage.message","&c错误 ： &7用法说明 -> /mk setHighWorth [生物名称] [数量]。");
            messagesCfg.set("language.chinese_simplified.addCustomDropsErrorMessage.message","&c错误 ： 无法新增玩家自订义掉落物。");
            messagesCfg.set("language.chinese_simplified.addCustomDropsCommandErrorMessage.message","&c错误 ： &7用法说明 -> /mk addCustomDrop [生物名称] [物品名称] [数量] [概率]。");
            messagesCfg.set("language.chinese_simplified.addCustomDropAlreadyPresentErrorMessage.message","&c错误 ： &6%itemName% &7已经设定自定义掉落。");
            messagesCfg.set("language.chinese_simplified.addCustomDropSuccessMessage.message","&a新增 &6%chance% % &a的概率让生物 &6%mobName% &a掉落 &6%amount% 个 %itemName% &a。");
            messagesCfg.set("language.chinese_simplified.removeCustomDropSuccessMessage.message","&a删除生物 &6%mobName% &a掉落物 &6%itemName% &a。");
            messagesCfg.set("language.chinese_simplified.customDropsDoNotExistErrorMessage.message","&c错误 ： &7生物 &6%mobName% &7不存在掉落物。");
            messagesCfg.set("language.chinese_simplified.customDropsNotEnabledMessage.message","&a未开启生物 &6%mobName% &a的自订义掉落。");
            messagesCfg.set("language.chinese_simplified.setLowWorthTooHighErrorMessage.message","&c错误 ： &7生物 &6%mobName% &7的最低价值高于你设定的价值。");
            messagesCfg.set("language.chinese_simplified.setHighWorthTooLowErrorMessage.message","&c错误 ： &7生物 &6%mobName% &7的最高价值低于你设定的价值。");
            messagesCfg.set("language.chinese_simplified.mobDropInfoMessage.message","&a生物 &6%mobName% &a有 &6%chance% % &a的概率掉落 &6%amount% 个 %itemName% &a。");
            messagesCfg.set("language.chinese_simplified.customDropsNotSetMessage.message","&a生物 &6%mobName% &a没有任何自定义掉落设定。");
            messagesCfg.set("language.chinese_simplified.mobWorthMessage.message","&a生物 &6%mobName% &a价值 &6%lowWorth% 元 &a。");
            messagesCfg.set("language.chinese_simplified.mobRangeWorthMessage.message","&a生物 &6%mobName% &a价值介于 &6%lowWorth% &a到 &6%highWorth% 元 &a之间。");
            messagesCfg.set("language.chinese_simplified.setLowWorthSuccessMessage.message","&a已设定生物 &6%mobName% &a的最低价值为 &6%lowWorth% 元 &a。");
            messagesCfg.set("language.chinese_simplified.setHighWorthSuccessMessage.message","&a已设定生物 &6%mobName% &a的最高价值为 &6%highWorth% 元 &a。");
            messagesCfg.set("language.chinese_simplified.defaultDropsTrueMessage.message","&a生物 &6%mobName% &a的预设掉落物调整成 &6true。");
            messagesCfg.set("language.chinese_simplified.defaultDropsFalseMessage.message","&a生物 &6%mobName% &a的预设掉落物调整成 &6false。");
            messagesCfg.set("language.chinese_simplified.customDropsTrueMessage.message","&a生物 &6%mobName% &a的自订义掉落物调整成 &6true。");
            messagesCfg.set("language.chinese_simplified.customDropsFalseMessage.message","&a生物 &6%mobName% &a的自订义掉落物调整成 &6false。");
            messagesCfg.set("language.chinese_simplified.languageChangeSuccessMessage.message","&a已设定 Money4Mobs 的语言为 &6簡体中文");
            messagesCfg.set("language.chinese_simplified.moneyRewardedMessage.message","&a你被给予 &6$ %amount% 元 &a，现在余额 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_simplified.moneySubtractedMessage.message","&a你被拿走 &6$ %amount% 元 &a，现在余额 &6$ %balance% 元 &a。");
            // Chinese Simplified Message Location
            messagesCfg.set("language.chinese_simplified.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.chinese_simplified.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_simplified.moneySubtractedMessage.location",ACTION_BAR);
            
            // Polish Messages
            messagesCfg.set("language.polish.mobKillerOnMessage.message","&aWiadomości MobKiller &6włączone");
            messagesCfg.set("language.polish.mobKillerOffMessage.message","&aWiadomości MobKiller &6wyłączone");
            messagesCfg.set("language.polish.accessDeniedMessage.message","&cNie masz dostępu do tej komendy");
            messagesCfg.set("language.polish.eggSpawnRewardTrueMessage.message","&aNagrody pieniężne za zabicie stworzeń powstałych z jajek zostały &6włączone");
            messagesCfg.set("language.polish.eggSpawnRewardFalseMessage.message","&aNagrody pieniężne za zabicie stworzeń powstałych z jajek zostały &6wyłączone");
            messagesCfg.set("language.polish.spawnerSpawnRewardTrueMessage.message","&aNagrody pieniężne za zabicie stworzeń powstałych z generatorów zostały &6włączone");
            messagesCfg.set("language.polish.spawnerSpawnRewardFalseMessage.message","&aNagrody pieniężne za zabicie stworzeń powstałych z generatorów zostały &6wyłączone");
            messagesCfg.set("language.polish.tamedWolvesRewardTrueMessage.message","&aNagrody pieniężne za zabicie stworzeń przez oswojone wilki zostały &6włączone");
            messagesCfg.set("language.polish.tamedWolvesRewardFalseMessage.message","&aNagrody pieniężne za zabicie stworzeń przez oswojone wilki zostały &6wyłączone");
            messagesCfg.set("language.polish.reloadingMessage.message","&bPrzeładowywanie Money4Mobs");
            messagesCfg.set("language.polish.reloadConfirmMessage.message","&6Money4Mobs przeładowywanie zakończone");
            messagesCfg.set("language.polish.setLowWorthCommandErrorMessage.message","&cError: &7Wpisz komende w ten sposób -> /mk setLowWorth [nazwaStorzenia] [ilość]");
            messagesCfg.set("language.polish.setHighWorthCommandErrorMessage.message","&cError: &7Wpisz komende w ten sposób -> /mk setHighWorth [nazwaStorzenia] [ilość]");
            messagesCfg.set("language.polish.addCustomDropsErrorMessage.message","&cError: Niestandardowe dropy nie mogą być dodawane do graczy");
            messagesCfg.set("language.polish.addCustomDropsCommandErrorMessage.message","&cError: &7Wpisz komende w ten sposób -> /mk addCustomDrop [nazwaStworzenia] [nazwaPrzedmiotu] [ilość] [szansa]");
            messagesCfg.set("language.polish.addCustomDropAlreadyPresentErrorMessage.message","&cError: &6%itemName% &7just już ustawiony jako niestandardowy drop");
            messagesCfg.set("language.polish.addCustomDropSuccessMessage.message","&aDodano &6%amount%  %itemName% &ado &6%mobName% &az szansą &6%chance% % &ana dropa");
            messagesCfg.set("language.polish.removeCustomDropSuccessMessage.message","&6%itemName% &ausunięte z listy dropów &6%mobName% &atego stworzenia");
            messagesCfg.set("language.polish.customDropsDoNotExistErrorMessage.message","&cError: &7Drop nie istnieje dla &astworzenia &6%mobName%");
            messagesCfg.set("language.polish.customDropsNotEnabledMessage.message","&aNiestandardowe dropy nie są włączone dla &6%mobName% &astworzenia");
            messagesCfg.set("language.polish.setLowWorthTooHighErrorMessage.message","&cError: &7Niższa wartość dla &6%mobName% &7stworzenia jest wyższa niż granica którą ustawiłeś");
            messagesCfg.set("language.polish.setHighWorthTooLowErrorMessage.message","&cError: &7Wyższa wartość dla &6%mobName% &7stworzenia jest niższa niż wartość którą ustawiłeś");
            messagesCfg.set("language.polish.mobDropInfoMessage.message","&6%mobName% &astworzenie ma &6%chance% % &aszansy na wydropienie &6%amount%  %itemName%");
            messagesCfg.set("language.polish.customDropsNotSetMessage.message","&6%mobName% &astworzenia nie ma ustawionego nistandardowego dropu");
            messagesCfg.set("language.polish.mobWorthMessage.message","&6%mobName% &astworzenia jest warte &6%lowWorth% &adolar(ów)");
            messagesCfg.set("language.polish.mobRangeWorthMessage.message","&6%mobName% &astworzenia jest warte pomiędzy &6%lowWorth% &aa &6%highWorth% &adolarów");
            messagesCfg.set("language.polish.setLowWorthSuccessMessage.message","&aNiższa wartość dla &6%mobName% &astworzenia została ustawiona na &6%lowWorth% &adolar(ów)");
            messagesCfg.set("language.polish.setHighWorthSuccessMessage.message","&aWyższa wartość dla &6%mobName% &astworzenia została ustawiona na &6%highWorth% &adolar(ów)");
            messagesCfg.set("language.polish.defaultDropsTrueMessage.message","&aDomyślne dropy za &6%mobName% &astworzenia są &6włączone");
            messagesCfg.set("language.polish.defaultDropsFalseMessage.message","&aDomyślne dropy za &6%mobName% &astworzenia są &6wyłączone");
            messagesCfg.set("language.polish.customDropsTrueMessage.message","&aNiestandardowe dropy za &6%mobName% &astworzenia są &6włączone");
            messagesCfg.set("language.polish.customDropsFalseMessage.message","&aNiestandardowe dropy za &6%mobName% &astworzenia są &6wyłączone");
            messagesCfg.set("language.polish.languageChangeSuccessMessage.message","&aZmieniono wiadomości dla Money4Mobs na &6Polskie");
            messagesCfg.set("language.polish.moneyRewardedMessage.message","&aOtrzymałeś &6$ %amount%  &ai masz obecnie &6$ %balance%");
            messagesCfg.set("language.polish.moneySubtractedMessage.message","&6$ %amount%  &azostało zabrane i masz obecnie &6$ %balance%");
            // Polish Message Location
            messagesCfg.set("language.polish.mobKillerOnMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.mobKillerOffMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.accessDeniedMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.eggSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.eggSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.spawnerSpawnRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.spawnerSpawnRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.tamedWolvesRewardTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.tamedWolvesRewardFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.reloadingMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.reloadConfirmMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setLowWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setHighWorthCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.addCustomDropsErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.addCustomDropsCommandErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.addCustomDropAlreadyPresentErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.addCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.removeCustomDropSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.customDropsDoNotExistErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.customDropsNotEnabledMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setLowWorthTooHighErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setHighWorthTooLowErrorMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.mobDropInfoMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.customDropsNotSetMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.mobWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.mobRangeWorthMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setLowWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.setHighWorthSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.defaultDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.defaultDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.customDropsTrueMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.customDropsFalseMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.languageChangeSuccessMessage.location",CHAT_MENU);
            messagesCfg.set("language.polish.moneyRewardedMessage.location",ACTION_BAR);
            messagesCfg.set("language.polish.moneySubtractedMessage.location",ACTION_BAR);

            messagesCfg.save(messagesFile);

        }

        update155PercentageLostPlayerKillPlayer();
        add163CommandErrorMessages();
        add165CommandErrorMessages();
        add169MobWorldRewardMessages();
        add170MobWorldAddRemoveMessages();
        messagesCfg.save(messagesFile);
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public void update155PercentageLostPlayerKillPlayer() throws IOException {
        if (Boolean.FALSE.equals(messagesCfg.isSet("language.english.playerKilledPlayerMessage.message"))) {
            messagesCfg.set("language.english.playerKilledPlayerMessage.message", "&aYou were given &6$ %amount%  &aand now have &6$ %balance%");
            messagesCfg.set("language.english.playerKilledByPlayerMessage.message","&6$ %amount%  &awas taken and you now have &6$ %balance%");
            messagesCfg.set("language.english.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.english.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.french.playerKilledPlayerMessage.message","&aVous avez reçu &6$ %amount% &aet vous avez maintenant &6$ %balance%");
            messagesCfg.set("language.french.playerKilledByPlayerMessage.message","&6$ %amount%  &aa été prise et vous avez maintenant &6$ %balance%");
            messagesCfg.set("language.french.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.french.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.spanish.playerKilledPlayerMessage.message","&aLe dieron &6$ %amount%  &ay ahora tengo &6$ %balance%");
            messagesCfg.set("language.spanish.playerKilledByPlayerMessage.message","&6$ %amount%  &afue tomado y ahora tienes &6$ %balance%");
            messagesCfg.set("language.spanish.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.spanish.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.german.playerKilledPlayerMessage.message","&aSie wurden gegeben &6$ %amount%  &aund jetzt habe &6$ %balance%");
            messagesCfg.set("language.german.playerKilledByPlayerMessage.message","&6$ %amount%  &awurde genommen und du hast jetzt &6$ %balance%");
            messagesCfg.set("language.german.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.german.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.russian.playerKilledPlayerMessage.message","&aВам дали &6$ %amount%  &aи теперь есть &6$ %balance%");
            messagesCfg.set("language.russian.playerKilledByPlayerMessage.message","&6$ %amount%  &aбыл взят, и теперь у вас есть &6$ %balance%");
            messagesCfg.set("language.russian.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.russian.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.hindi.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.hindi.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.hindi.playerKilledPlayerMessage.message","&aतुमको दिया गया था &6$ %amount%  &aऔर अब है &6$ %balance%");
            messagesCfg.set("language.hindi.playerKilledByPlayerMessage.message","&6$ %amount%  &aलिया गया था और अब आपके पास है &6$ %balance%");
            messagesCfg.set("language.italian.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.italian.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_simplified.playerKilledPlayerMessage.message","&a你被给予 &6$ %amount% 元 &a，现在余额 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_simplified.playerKilledByPlayerMessage.message","&a你被拿走 &6$ %amount% 元 &a，现在余额 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_simplified.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_simplified.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_traditional.playerKilledPlayerMessage.message","&a你被給予 &6$ %amount% 元 &a，現在餘額 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_traditional.playerKilledByPlayerMessage.message","&a你被拿走 &6$ %amount% 元 &a，現在餘額 &6$ %balance% 元 &a。");
            messagesCfg.set("language.chinese_traditional.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.chinese_traditional.playerKilledByPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.polish.playerKilledPlayerMessage.message", "&aOtrzymałeś &6$ %amount%  &ai masz obecnie &6$ %balance%");
            messagesCfg.set("language.polish.playerKilledByPlayerMessage.message","Straciłeś &6$ %amount%  &ai teraz masz &6$ %balance%");
            messagesCfg.set("language.polish.playerKilledPlayerMessage.location",ACTION_BAR);
            messagesCfg.set("language.polish.playerKilledByPlayerMessage.location",ACTION_BAR);
        }
        renameCustomDropsMessage();
        messagesCfg.save(messagesFile);
    }

    public static void renameCustomDropsMessage(){
        if (!MessagesConfigManager.messagesCfg.isSet("language.english.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.english.invalidMobErrorMessage.message", "&cError: &6%mobName% &7is not a valid mob");
            messagesCfg.set("language.english.invalidMobErrorMessage.location", CHAT_MENU);
        }else {
            messagesCfg.set("language.english.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.english.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.english.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.english.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.english.addCustomDropInvalidMobErrorMessage", null);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.french.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.french.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.french.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.french.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.french.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.french.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.french.invalidMobErrorMessage.message", "&cErreur: &6%mobName% &7n'est pas un mob valide");
            messagesCfg.set("language.french.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.spanish.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.spanish.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.spanish.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.spanish.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.spanish.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.spanish.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.spanish.invalidMobErrorMessage.message", "&cError: &6%mobName% &7no es una mafia válida");
            messagesCfg.set("language.spanish.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.german.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.german.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.german.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.german.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.german.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.german.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.german.invalidMobErrorMessage.message", "&cError: &6%mobName% &7ist kein gültiger mob");
            messagesCfg.set("language.german.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.italian.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.italian.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.italian.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.italian.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.italian.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.italian.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.italian.invalidMobErrorMessage.message", "&cErrore: &6%mobName% &7non è un mob valido");
            messagesCfg.set("language.italian.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.russian.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.russian.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.russian.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.russian.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.russian.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.russian.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.russian.invalidMobErrorMessage.message", "&cОшибка: &6%mobName% &7is not a valid mob");
            messagesCfg.set("language.russian.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.hindi.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.hindi.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.hindi.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.hindi.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.hindi.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.hindi.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.hindi.invalidMobErrorMessage.message", "&cत्रुटि: &6%mobName% &7वैध भीड़ नहीं है");
            messagesCfg.set("language.hindi.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.chinese_traditional.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.chinese_traditional.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.chinese_traditional.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.chinese_traditional.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.chinese_traditional.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.chinese_traditional.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.chinese_traditional.invalidMobErrorMessage.message", "&c錯誤: &6%mobName% &7不是有效的");
            messagesCfg.set("language.chinese_traditional.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.chinese_simplified.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.chinese_simplified.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.chinese_simplified.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.chinese_simplified.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.chinese_simplified.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.chinese_simplified.addCustomDropInvalidMobErrorMessage", null);
        } else {
            messagesCfg.set("language.chinese_simplified.invalidMobErrorMessage.message", "&c错误: &6%mobName% &7不是有效的暴徒");
            messagesCfg.set("language.chinese_simplified.invalidMobErrorMessage.location", CHAT_MENU);
        }
        if (!MessagesConfigManager.messagesCfg.isSet("language.polsih.addCustomDropInvalidMobErrorMessage.message")) {
            messagesCfg.set("language.polish.invalidMobErrorMessage.message", "&cError: &6%mobName% &7nie jest prawidłową nazwą stworzenia");
            messagesCfg.set("language.polish.invalidMobErrorMessage.location", CHAT_MENU);
        }else {
            messagesCfg.set("language.polish.invalidMobErrorMessage.message", MessagesConfigManager.messagesCfg.getString("language.polish.addCustomDropInvalidMobErrorMessage.message"));
            messagesCfg.set("language.polish.invalidMobErrorMessage.location", MessagesConfigManager.messagesCfg.getString("language.polish.addCustomDropInvalidMobErrorMessage.location"));
            messagesCfg.set("language.polish.addCustomDropInvalidMobErrorMessage", null);
        }
    }

    public static void add163CommandErrorMessages() {
        if (Boolean.FALSE.equals(messagesCfg.isSet("language.english.removeCustomDropsCommandErrorMessage.message"))) {
            List<String> languageList = new ArrayList<>();
            languageList.add("english");
            languageList.add("french");
            languageList.add("spanish");
            languageList.add("german");
            languageList.add("italian");
            languageList.add("russian");
            languageList.add("hindi");
            languageList.add("chinese_traditional");
            languageList.add("chinese_simplified");
            languageList.add("polish");
            for (String language : languageList){
                messagesCfg.set("language." + language  + ".removeCustomDropsCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleCustomDropsCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleDefaultDropsCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".worthCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".dropsCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".languageCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleKMCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleMoneyFromSpawnEggsCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleMoneyFromSpawnersCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".toggleMoneyFromTamedWolvesCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language  + ".reloadCommandErrorMessage.location", CHAT_MENU);
            }
            messagesCfg.set("language.english.removeCustomDropsCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk removeCustomDrop [mobName] [itemName]");
            messagesCfg.set("language.english.toggleCustomDropsCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleCustomDrops [mobName]");
            messagesCfg.set("language.english.toggleDefaultDropsCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleDefaultDrops [mobName]");
            messagesCfg.set("language.english.worthCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk worth [mobName]");
            messagesCfg.set("language.english.dropsCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk drops [mobName]");
            messagesCfg.set("language.english.languageCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk language [language]");
            messagesCfg.set("language.english.toggleKMCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleKM");
            messagesCfg.set("language.english.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.english.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.english.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.english.reloadCommandErrorMessage.message", "&cError: &7Enter command like this -> /mk reload");

            messagesCfg.set("language.french.removeCustomDropsCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk removeCustomDrop [nom_mob] [object-nom]");
            messagesCfg.set("language.french.toggleCustomDropsCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleCustomDrops [nom_mob]");
            messagesCfg.set("language.french.toggleDefaultDropsCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleDefaultDrops [nom_mob]");
            messagesCfg.set("language.french.worthCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk worth [nom_mob]");
            messagesCfg.set("language.french.dropsCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk drops [nom_mob]");
            messagesCfg.set("language.french.languageCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk language [langue]");
            messagesCfg.set("language.french.toggleKMCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleKM");
            messagesCfg.set("language.french.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.french.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.french.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.french.reloadCommandErrorMessage.message", "&cErreur: &7Entrez la commande comme celle-ci -> /mk reload");

            messagesCfg.set("language.german.removeCustomDropsCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk removeCustomDrop [mobName] [artikelname]");
            messagesCfg.set("language.german.toggleCustomDropsCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk toggleCustomDrops [mobName]");
            messagesCfg.set("language.german.toggleDefaultDropsCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein ->/mk toggleDefaultDrops [mobName]");
            messagesCfg.set("language.german.worthCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk worth [mobName]");
            messagesCfg.set("language.german.dropsCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk drops [mobName]");
            messagesCfg.set("language.german.languageCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk language [sprache]");
            messagesCfg.set("language.german.toggleKMCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk toggleKM");
            messagesCfg.set("language.german.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.german.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.german.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.german.reloadCommandErrorMessage.message", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk reload");

            messagesCfg.set("language.spanish.removeCustomDropsCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk removeCustomDrop [nombre de mob] [articulo-nombre]");
            messagesCfg.set("language.spanish.toggleCustomDropsCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleCustomDrops [nombre de mob]");
            messagesCfg.set("language.spanish.toggleDefaultDropsCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleDefaultDrops [nombre de mob]");
            messagesCfg.set("language.spanish.worthCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk worth [nombre de mob]");
            messagesCfg.set("language.spanish.dropsCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk drops [nombre de mob]");
            messagesCfg.set("language.spanish.languageCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk language [idioma]");
            messagesCfg.set("language.spanish.toggleKMCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleKM");
            messagesCfg.set("language.spanish.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.spanish.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.spanish.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.spanish.reloadCommandErrorMessage.message", "&cError: &7ingrese un comando como este -> /mk reload");

            messagesCfg.set("language.italian.removeCustomDropsCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk removeCustomDrop [mobName] [nome dell'elemento]");
            messagesCfg.set("language.italian.toggleCustomDropsCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleCustomDrops [mobName]");
            messagesCfg.set("language.italian.toggleDefaultDropsCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleDefaultDrops [mobName]");
            messagesCfg.set("language.italian.worthCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk worth [mobName]");
            messagesCfg.set("language.italian.dropsCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk drops [mobName]");
            messagesCfg.set("language.italian.languageCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk language [linguaggio]");
            messagesCfg.set("language.italian.toggleKMCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleKM");
            messagesCfg.set("language.italian.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.italian.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.italian.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.italian.reloadCommandErrorMessage.message", "&cErrore: &7Inserisci il comando in questo modo -> /mk reload");

            messagesCfg.set("language.hindi.removeCustomDropsCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk removeCustomDrop [mob-नाम] [वस्तु का नाम]");
            messagesCfg.set("language.hindi.toggleCustomDropsCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleCustomDrops [mob-नाम]");
            messagesCfg.set("language.hindi.toggleDefaultDropsCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleDefaultDrops [mob-नाम]");
            messagesCfg.set("language.hindi.worthCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk worth [mob-नाम]");
            messagesCfg.set("language.hindi.dropsCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk drops [mob-नाम]");
            messagesCfg.set("language.hindi.languageCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk language [भाषा: हिन्दी]");
            messagesCfg.set("language.hindi.toggleKMCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleKM");
            messagesCfg.set("language.hindi.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.hindi.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.hindi.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.hindi.reloadCommandErrorMessage.message", "&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk reload");

            messagesCfg.set("language.chinese_traditional.removeCustomDropsCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk removeCustomDrop [生物名稱] [物品名稱]。");
            messagesCfg.set("language.chinese_traditional.toggleCustomDropsCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleCustomDrops [生物名稱]。");
            messagesCfg.set("language.chinese_traditional.toggleDefaultDropsCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleDefaultDrops [生物名稱]。");
            messagesCfg.set("language.chinese_traditional.worthCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk worth [生物名稱]。");
            messagesCfg.set("language.chinese_traditional.dropsCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk drops [生物名稱]。");
            messagesCfg.set("language.chinese_traditional.languageCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk language [語言]。");
            messagesCfg.set("language.chinese_traditional.toggleKMCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleKM。");
            messagesCfg.set("language.chinese_traditional.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleMoneyFromSpawnEggs。");
            messagesCfg.set("language.chinese_traditional.toggleMoneyFromSpawnersCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleMoneyFromSpawners。");
            messagesCfg.set("language.chinese_traditional.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk toggleMoneyFromTamedWolves。");
            messagesCfg.set("language.chinese_traditional.reloadCommandErrorMessage.message", "&c錯誤 ： &7用法說明 -> /mk reload。");

            messagesCfg.set("language.chinese_simplified.removeCustomDropsCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk removeCustomDrop [生物名称] [物品名称]。");
            messagesCfg.set("language.chinese_simplified.toggleCustomDropsCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleCustomDrops [生物名称]。");
            messagesCfg.set("language.chinese_simplified.toggleDefaultDropsCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleDefaultDrops [生物名称]。");
            messagesCfg.set("language.chinese_simplified.worthCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk worth [生物名称]。");
            messagesCfg.set("language.chinese_simplified.dropsCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk drops [生物名称]。");
            messagesCfg.set("language.chinese_simplified.languageCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk language [语言]。");
            messagesCfg.set("language.chinese_simplified.toggleKMCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleKM。");
            messagesCfg.set("language.chinese_simplified.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleMoneyFromSpawnEggs。");
            messagesCfg.set("language.chinese_simplified.toggleMoneyFromSpawnersCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleMoneyFromSpawners。");
            messagesCfg.set("language.chinese_simplified.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk toggleMoneyFromTamedWolves。");
            messagesCfg.set("language.chinese_simplified.reloadCommandErrorMessage.message", "&c错误 ： &7用法说明 -> /mk reload。");

            messagesCfg.set("language.russian.removeCustomDropsCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk removeCustomDrop [mob-название] [название предмета]");
            messagesCfg.set("language.russian.toggleCustomDropsCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleCustomDrops [mob-название]");
            messagesCfg.set("language.russian.toggleDefaultDropsCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleDefaultDrops [mob-название]");
            messagesCfg.set("language.russian.worthCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk worth [mob-название]");
            messagesCfg.set("language.russian.dropsCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk drops [mob-название]");
            messagesCfg.set("language.russian.languageCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk language [язык]");
            messagesCfg.set("language.russian.toggleKMCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleKM");
            messagesCfg.set("language.russian.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.russian.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.russian.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.russian.reloadCommandErrorMessage.message", "&cОшибка: &7Введите команду, подобную этой -> /mk reload");
            
            messagesCfg.set("language.polish.removeCustomDropsCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk removeCustomDrop [nazwaStworzenia] [nazwaPrzedmiotu]");
            messagesCfg.set("language.polish.toggleCustomDropsCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleCustomDrops [nazwaStworzenia]");
            messagesCfg.set("language.polish.toggleDefaultDropsCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleDefaultDrops [nazwaStworzenia]");
            messagesCfg.set("language.polish.worthCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk worth [nazwaStworzenia]");
            messagesCfg.set("language.polish.dropsCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk drops [nazwaStworzenia]");
            messagesCfg.set("language.polish.languageCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk language [język]");
            messagesCfg.set("language.polish.toggleKMCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleKM");
            messagesCfg.set("language.polish.toggleMoneyFromSpawnEggsCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleMoneyFromSpawnEggs");
            messagesCfg.set("language.polish.toggleMoneyFromSpawnersCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleMoneyFromSpawners");
            messagesCfg.set("language.polish.toggleMoneyFromTamedWolvesCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk toggleMoneyFromTamedWolves");
            messagesCfg.set("language.polish.reloadCommandErrorMessage.message", "&cError: &7Wpisz komende w ten sposób -> /mk reload");
        }
    }

    public static void add165CommandErrorMessages() {
        if (Boolean.FALSE.equals(messagesCfg.isSet("language.english.incompleteCommandErrorMessage.message"))) {
            List<String> languageList = new ArrayList<>();
            languageList.add("english");
            languageList.add("french");
            languageList.add("spanish");
            languageList.add("german");
            languageList.add("italian");
            languageList.add("russian");
            languageList.add("hindi");
            languageList.add("chinese_traditional");
            languageList.add("chinese_simplified");
            languageList.add("polish");
            String incompleteMessage = ".incompleteCommandErrorMessage.message";
            for (String language : languageList) {
                messagesCfg.set("language." + language + ".incompleteCommandErrorMessage.location", CHAT_MENU);
            }
            messagesCfg.set("language.english" + incompleteMessage, "&cError: &7Available commands are -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.french" + incompleteMessage, "&cErreur: &7Les commandes disponibles sont -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.spanish" + incompleteMessage, "&cError: &7Los comandos disponibles son -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.german" + incompleteMessage, "&cError: &7Verfügbare Befehle sind -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.italian" + incompleteMessage, "&cErrore: &7I comandi disponibili sono -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.russian" + incompleteMessage, "&Ошибка: &7Доступные команды -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.hindi" + incompleteMessage, "&cरुटि: &7उपलब्ध आदेश हैं -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
            messagesCfg.set("language.chinese_traditional" + incompleteMessage, "&c錯誤 ： &7可用的指令 -> /mk [worth、drops、language、reload、removeCustomDrop、setLowWorth、setHighWorth、toggleCustomDrops、toggleDefaultDrops、toggleKM、toggleMoneyFromSpawnEggs、toggleMoneyFromSpawners、toggleMoneyFromTamedWolves。");
            messagesCfg.set("language.chinese_simplified" + incompleteMessage, "&c错误 ： &7可用的命令 -> /mk [worth、drops、language、reload、removeCustomDrop、setLowWorth、setHighWorth、toggleCustomDrops、toggleDefaultDrops、toggleKM、toggleMoneyFromSpawnEggs、toggleMoneyFromSpawners、toggleMoneyFromTamedWolves。");
            messagesCfg.set("language.polish" + incompleteMessage, "&cError: &7Dostępne komendy to -> /mk [worth, drops, language, reload, removeCustomDrop, setLowWorth, setHighWorth, toggleCustomDrops, toggleDefaultDrops, toggleKM, toggleMoneyFromSpawnEggs, toggleMoneyFromSpawners, toggleMoneyFromTamedWolves");
        }
    }
    public static void add169MobWorldRewardMessages() {
        if (Boolean.FALSE.equals(messagesCfg.isSet("language.english.mobRewardWorldsMessage.message"))) {
            List<String> languageList = new ArrayList<>();
            languageList.add("english");
            languageList.add("french");
            languageList.add("spanish");
            languageList.add("german");
            languageList.add("italian");
            languageList.add("russian");
            languageList.add("hindi");
            languageList.add("chinese_traditional");
            languageList.add("chinese_simplified");
            languageList.add("polish");
            String mobRewardWorldsMessage = ".mobRewardWorldsMessage.message";
            String mobRewardWorldsCommandErrorMessage = ".mobRewardWorldsCommandErrorMessage.message";
            for (String language : languageList) {
                messagesCfg.set("language." + language + ".mobRewardWorldsMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language + ".mobRewardWorldsCommandErrorMessage.location", CHAT_MENU);
            }
            messagesCfg.set("language.english" + mobRewardWorldsMessage, "&aMoney for &6%mobName% &ais rewarded in &6%worlds%");
            messagesCfg.set("language.french" + mobRewardWorldsMessage, "&aL'argent pour &6%mobName% &arécompensé dans &6%worlds");
            messagesCfg.set("language.spanish" + mobRewardWorldsMessage, "&aEl dinero de  &6%mobName% &ase recompensa ens &6%worlds");
            messagesCfg.set("language.german" + mobRewardWorldsMessage, "&aGeld für &6%mobName% &awird belohnt in &6%worlds");
            messagesCfg.set("language.italian" + mobRewardWorldsMessage, "&aI soldi per &6%mobName% &avengono ricompensati in &6%worlds");
            messagesCfg.set("language.russian" + mobRewardWorldsMessage, "&aДеньги для &6%mobName% &aвознаграждаются в &6%worlds");
            messagesCfg.set("language.hindi" + mobRewardWorldsMessage, "&6%mobName% &aके लिए धन &6%worlds . में पुरस्कृत किया जाता है");
            messagesCfg.set("language.chinese_traditional" + mobRewardWorldsMessage, "&6%mobName% &a的錢在 &6%worlds 中得到獎勵");
            messagesCfg.set("language.chinese_simplified" + mobRewardWorldsMessage, "&6%mobName% &a的钱在 &6%worlds 中得到奖励");
            messagesCfg.set("language.polish" + mobRewardWorldsMessage, "&aPięniądze za zabicie &6%mobName% &azostały otrzymane w &6%worlds%");
            
            messagesCfg.set("language.english" + mobRewardWorldsCommandErrorMessage, "&cError: &7Enter command like this -> /mk mobRewardWorlds [mobName]");
            messagesCfg.set("language.french" + mobRewardWorldsCommandErrorMessage, "&cErreur: &7Entrez la commande comme celle-ci -> /mk mobRewardWorlds [nom_mob]");
            messagesCfg.set("language.spanish" + mobRewardWorldsCommandErrorMessage, "&cError: &7ingrese un comando como este -> / mk mobRewardWorlds [nombre de mob]");
            messagesCfg.set("language.german" + mobRewardWorldsCommandErrorMessage, "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk mobRewardWorlds [mobName]");
            messagesCfg.set("language.italian" + mobRewardWorldsCommandErrorMessage, "&cErrore: &7Inserisci il comando in questo modo -> /mk mobRewardWorlds [mobName]");
            messagesCfg.set("language.russian" + mobRewardWorldsCommandErrorMessage, "&cОшибка: &7Введите команду, подобную этой -> /mk mobRewardWorlds [mobName]");
            messagesCfg.set("language.hindi" + mobRewardWorldsCommandErrorMessage, "'&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk mobRewardWorlds [mob-नाम]");
            messagesCfg.set("language.chinese_traditional" + mobRewardWorldsCommandErrorMessage, "&c錯誤 ： &7用法說明 -> /mk mobRewardWorlds [生物名稱]");
            messagesCfg.set("language.chinese_simplified" + mobRewardWorldsCommandErrorMessage, "&c错误 ： &7用法说明 -> /mk mobRewardWorlds [生物名称]");
            messagesCfg.set("language.polish" + mobRewardWorldsCommandErrorMessage, "&cError: &7Wpisz komnędne w ten sposób -> /mk mobRewardWorlds [nazwaStworzenia]");
        }
    }

    public static void add170MobWorldAddRemoveMessages() {
        if (Boolean.FALSE.equals(messagesCfg.isSet("language.english.addWorldSuccessMessage.message"))) {
            List<String> languageList = new ArrayList<>();
            languageList.add("english");
            languageList.add("french");
            languageList.add("spanish");
            languageList.add("german");
            languageList.add("italian");
            languageList.add("russian");
            languageList.add("hindi");
            languageList.add("chinese_traditional");
            languageList.add("chinese_simplified");
            languageList.add("polish");
            String addWorldSuccessMessage = ".addWorldSuccessMessage.message";
            String addWorldCommandErrorMessage = ".addWorldCommandErrorMessage.message";
            String removeWorldSuccessMessage = ".removeWorldSuccessMessage.message";
            String removeWorldCommandErrorMessage = ".removeWorldCommandErrorMessage.message";
            String removeWorldFailureMessage = ".removeWorldFailureMessage.message";
            for (String language : languageList) {
                messagesCfg.set("language." + language + ".addWorldSuccessMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language + ".addWorldCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language + ".removeWorldSuccessMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language + ".removeWorldCommandErrorMessage.location", CHAT_MENU);
                messagesCfg.set("language." + language + ".removeWorldFailureMessage.location", CHAT_MENU);
            }
            messagesCfg.set("language.english" + addWorldSuccessMessage, "&aAdded &6%worlds% &aworld");
            messagesCfg.set("language.french" + addWorldSuccessMessage, "&aAjout du monde &6%worlds%");
            messagesCfg.set("language.spanish" + addWorldSuccessMessage, "&aAñadido &6%worlds% &amundo");
            messagesCfg.set("language.german" + addWorldSuccessMessage, "&6%worlds% &aWelt hinzugefügt");
            messagesCfg.set("language.italian" + addWorldSuccessMessage, "&aAggiunto &6%worlds% &amondo");
            messagesCfg.set("language.russian" + addWorldSuccessMessage, "&aДобавлен &6%worlds% &aмир");
            messagesCfg.set("language.hindi" + addWorldSuccessMessage, "&aजोड़ा गया &6%worlds% &aदुनिया");
            messagesCfg.set("language.chinese_traditional" + addWorldSuccessMessage, "&a新增 &6%worlds% &a世界");
            messagesCfg.set("language.chinese_simplified" + addWorldSuccessMessage, "&a新增 &6%worlds% &a世界");
            messagesCfg.set("language.polish" + addWorldSuccessMessage, "&aDodano &6%worlds% &ado świata");

            messagesCfg.set("language.english" + addWorldCommandErrorMessage, "&cError: &7Enter command like this -> /mk addWorld [worldName]");
            messagesCfg.set("language.french" + addWorldCommandErrorMessage, "&cErreur: &7Entrez la commande comme celle-ci -> /mk addWorld [nom du monde]");
            messagesCfg.set("language.spanish" + addWorldCommandErrorMessage, "&cError: &7ingrese un comando como este -> /mk addWorld [nombre mundial]");
            messagesCfg.set("language.german" + addWorldCommandErrorMessage, "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk addWorld [Weltname]");
            messagesCfg.set("language.italian" + addWorldCommandErrorMessage, "&cErrore: &7Inserisci il comando in questo modo -> /mk addWorld [nome Mondiale]");
            messagesCfg.set("language.russian" + addWorldCommandErrorMessage, "&cОшибка: &7Введите команду, подобную этой -> /mk addWorld [мировое имя]");
            messagesCfg.set("language.hindi" + addWorldCommandErrorMessage, "'&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk addWorld [दुनिया का नाम]");
            messagesCfg.set("language.chinese_traditional" + addWorldCommandErrorMessage, "&c錯誤 ： &7用法說明 -> /mk addWorld [世界名]");
            messagesCfg.set("language.chinese_simplified" + addWorldCommandErrorMessage, "&c错误 ： &7用法说明 -> /mk addWorld [世界名]");
            messagesCfg.set("language.polish" + addWorldCommandErrorMessage, "&cError: &7Wpisz komende w ten sposób -> /mk addWorld [nazwaŚwiata]");

            messagesCfg.set("language.english" + removeWorldSuccessMessage, "&aRemoved &6%worlds% &aworld");
            messagesCfg.set("language.french" + removeWorldSuccessMessage, "&aMonde &6%worlds% supprimé");
            messagesCfg.set("language.spanish" + removeWorldSuccessMessage, "&aEliminado &6%worlds% &amundo");
            messagesCfg.set("language.german" + removeWorldSuccessMessage, "&6%worlds% &aWelt Welt entfernt");
            messagesCfg.set("language.italian" + removeWorldSuccessMessage, "&Rimosso &6%worlds% &amondo");
            messagesCfg.set("language.russian" + removeWorldSuccessMessage, "&Удален &6%worlds% &aмир");
            messagesCfg.set("language.hindi" + removeWorldSuccessMessage, "&aहटाया गया &6%worlds% &aदुनिया");
            messagesCfg.set("language.chinese_traditional" + removeWorldSuccessMessage, "&a移除 &6%worlds% &a世界");
            messagesCfg.set("language.chinese_simplified" + removeWorldSuccessMessage, "&a移除 &6%worlds% &a世界");
            messagesCfg.set("language.polish" + removeWorldSuccessMessage, "&aUsnięto &6%worlds% &aze świata");

            messagesCfg.set("language.english" + removeWorldCommandErrorMessage, "&cError: &7Enter command like this -> /mk removeWorld [worldName]");
            messagesCfg.set("language.french" + removeWorldCommandErrorMessage, "&cErreur: &7Entrez la commande comme celle-ci -> /mk removeWorld [nom du monde]");
            messagesCfg.set("language.spanish" + removeWorldCommandErrorMessage, "&cError: &7ingrese un comando como este -> /mk removeWorld [nombre mundial]");
            messagesCfg.set("language.german" + removeWorldCommandErrorMessage, "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk removeWorld [Weltname]");
            messagesCfg.set("language.italian" + removeWorldCommandErrorMessage, "&cErrore: &7Inserisci il comando in questo modo -> /mk removeWorld [Mondiale]");
            messagesCfg.set("language.russian" + removeWorldCommandErrorMessage, "&cОшибка: &7Введите команду, подобную этой -> /mk removeWorld [мировое имя]");
            messagesCfg.set("language.hindi" + removeWorldCommandErrorMessage, "'&cत्रुटि: &7इस तरह कमांड दर्ज करें -> /mk removeWorld [दुनिया का नाम]");
            messagesCfg.set("language.chinese_traditional" + removeWorldCommandErrorMessage, "&c錯誤 ： &7用法說明 -> /mk removeWorld [世界名]");
            messagesCfg.set("language.chinese_simplified" + removeWorldCommandErrorMessage, "&c错误 ： &7用法说明 -> /mk removeWorld [世界名]");
            messagesCfg.set("language.polish" + removeWorldCommandErrorMessage, "&cError: &7Wpisz komende w ten sposób -> /mk removeWorld [nazwaŚwiata]");

            messagesCfg.set("language.english" + removeWorldFailureMessage, "&cError: &7World doesn't exist or has incorrect capitalization");
            messagesCfg.set("language.french" + removeWorldFailureMessage, "&cErreur: &7Le monde n'existe pas ou a une majuscule incorrecte");
            messagesCfg.set("language.spanish" + removeWorldFailureMessage, "&cError: &7El mundo no existe o tiene mayúsculas incorrectas");
            messagesCfg.set("language.german" + removeWorldFailureMessage, "&cError: &7Welt existiert nicht oder hat falsche Groß-/Kleinschreibung");
            messagesCfg.set("language.italian" + removeWorldFailureMessage, "&cErrore: &7Il mondo non esiste o ha lettere maiuscole errate");
            messagesCfg.set("language.russian" + removeWorldFailureMessage, "&cОшибка: &7Мир не существует или в нем неверно написаны заглавные буквы.");
            messagesCfg.set("language.hindi" + removeWorldFailureMessage, "'&cत्रुटि: &7दुनिया मौजूद नहीं है या गलत कैपिटलाइज़ेशन है");
            messagesCfg.set("language.chinese_traditional" + removeWorldFailureMessage, "&c錯誤 ： &7World 不存在或大小寫不正確");
            messagesCfg.set("language.chinese_simplified" + removeWorldFailureMessage, "&c错误 ： &7World 不存在或大小写不正确");
            messagesCfg.set("language.polish" + removeWorldFailureMessage, "&cError: &7Świat nie istnieje lub ma nieprawidłową wielokość liter");
        }
    }
}
