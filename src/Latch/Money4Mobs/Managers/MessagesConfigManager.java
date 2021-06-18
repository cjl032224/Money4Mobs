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
            messagesCfg.set("messages.french.addCustomDropSuccessMessage", "&aAjout de &6%amount%  %itemName% &aà &6%mobName% &agouttes avec une &6%chance% % &ade chance de tomber");
            messagesCfg.set("messages.french.removeCustomDropSuccessMessage", "&6%itemName% &agouttes supprimées de &6%mobName% &amobs");
            messagesCfg.set("messages.french.customDropsDoNotExistErrorMessage", "&cErreur: &7Drops n''existent pas pour &6%mobName% &amobs");
            messagesCfg.set("messages.french.customDropsNotEnabledMessage", "&aLes largages personnalisés ne sont pas activés pour &6%mobName% &amobs");
            messagesCfg.set("messages.french.setLowWorthTooHighErrorMessage", "&cErreur: &7La valeur faible pour &6%mobName% &7mobs est supérieure à la valeur que vous définissez");
            messagesCfg.set("messages.french.setHighWorthTooLowErrorMessage", "&cErreur: &7Valeur élevée pour &6%mobName% &7mobs est inférieure à la valeur que vous définissez");
            messagesCfg.set("messages.french.mobDropInfoMessage", "&6%mobName% &amobs ont &6%chance% % &achance de laisser tomber &6%amount%  %itemName%");
            messagesCfg.set("messages.french.customDropsNotSetMessage", "&6%mobName% &amobs n''ont pas de drop personnalisé défini");
            messagesCfg.set("messages.french.mobWorthMessage", "&6%mobName% &amobs valent &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.french.mobRangeWorthMessage", "&6%mobName% &amobs valent entre &6%lowWorth% &aet &6%highWorth% &adollars");
            messagesCfg.set("messages.french.setLowWorthSuccessMessage", "&aFaible valeur pour &6%mobName% &amobs a été défini sur &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.french.setHighWorthSuccessMessage", "&aLa valeur élevée pour &6%mobName% &amobs a été définie sur &6%highWorth% &adollar(s)");
            messagesCfg.set("messages.french.defaultDropsTrueMessage", "&aLes baisses par défaut pour &6% mob Name% et les mobs définis sur &6vrai");
            messagesCfg.set("messages.french.defaultDropsFalseMessage", "&aLa valeur par défaut diminue pour &6%mobName% &amobs défini sur &6faux");
            messagesCfg.set("messages.french.customDropsTrueMessage", "&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6vrai");
            messagesCfg.set("messages.french.customDropsFalseMessage", "&aDécharges personnalisées pour &6%mobName% &amobs défini sur &6faux");

            // Spanish
            messagesCfg.set("messages.spanish.mobKillerOnMessage", "&aMensaje de MobKiller &6en");
            messagesCfg.set("messages.spanish.mobKillerOffMessage", "&aMensaje de MobKiller &6desactivado");
            messagesCfg.set("messages.spanish.accessDeniedMessage", "&cNo tienes acceso a este comando");
            messagesCfg.set("messages.spanish.eggSpawnRewardTrueMessage", "&aEl dinero recompensado por las turbas generadas con huevos se establece en &6verdadero");
            messagesCfg.set("messages.spanish.eggSpawnRewardFalseMessage", "&aEl dinero recompensado por las turbas generadas con huevos se establece en &6falso");
            messagesCfg.set("messages.spanish.spawnerSpawnRewardTrueMessage", "&aEl dinero recompensado por las turbas generadoras se establece en &6verdadero");
            messagesCfg.set("messages.spanish.spawnerSpawnRewardFalseMessage", "&aEl dinero recompensado por las turbas generadoras se establece en &6falso");
            messagesCfg.set("messages.spanish.tamedWolvesRewardTrueMessage", "&aDinero recompensado por turbas asesinadas por lobos domesticados establecido en &6verdadero");
            messagesCfg.set("messages.spanish.tamedWolvesRewardFalseMessage", "&aDinero otorgado por turbas asesinadas por lobos domesticados establecido en &6falso");
            messagesCfg.set("messages.spanish.reloadingMessage", "&bRecarga de Money4Mobs");
            messagesCfg.set("messages.spanish.reloadConfirmMessage", "&6Recarga completa de Money4Mobs");
            messagesCfg.set("messages.spanish.overrideKillMessageTrue", "&aoverrideKillMessage establecido en &6verdadero");
            messagesCfg.set("messages.spanish.overrideKillMessageFalse", "&aoverrideKillMessage establecido en &6falso");
            messagesCfg.set("messages.spanish.setLowWorthCommandErrorMessage", "&cError: &7ingrese un comando como este -> / mk setLowWorth  [nombre de mob] [monto]");
            messagesCfg.set("messages.spanish.setHighWorthCommandErrorMessage", "&cError: &7ingrese un comando como este -> /mk setHighWorth [nombre de mob] [monto]");
            messagesCfg.set("messages.spanish.addCustomDropsErrorMessage", "&cError: No se pueden agregar caídas personalizadas a las jugadoras");
            messagesCfg.set("messages.spanish.addCustomDropsCommandErrorMessage", "&cError: &7ingrese un comando como este -> /mk addCustomDrop [nombre de mob] [monto] [oportunidad]");
            messagesCfg.set("messages.spanish.addCustomDropInvalidMobErrorMessage", "&cError: &6%mobName% &7no es una mafia válida");
            messagesCfg.set("messages.spanish.addCustomDropAlreadyPresentErrorMessage", "&cError: &6%itemName% &7ya está presente como una gota personalizada");
            messagesCfg.set("messages.spanish.addCustomDropSuccessMessage", "&aSe agregó &6%amount% %itemName% &aa &6%mobName% &agotas con un &6%chance% &ade posibilidad de caer");
            messagesCfg.set("messages.spanish.removeCustomDropSuccessMessage", "&6%itemName% &agotas eliminadas de &6%mobName% &amobs");
            messagesCfg.set("messages.spanish.customDropsDoNotExistErrorMessage", "&cError: &7Las gotas no existen para &6%mobName% &amobs");
            messagesCfg.set("messages.spanish.customDropsNotEnabledMessage", "&aLas gotas personalizadas no están habilitadas para &6%mobName% &amobs");
            messagesCfg.set("messages.spanish.setLowWorthTooHighErrorMessage", "&cError: &7Baja valor para &6%mobName% %7mobs es más alta que el valor que está configurando");
            messagesCfg.set("messages.spanish.setHighWorthTooLowErrorMessage", "&7Alto valor para &6%mobName% &7mobs es menor que el valor que está estableciendo");
            messagesCfg.set("messages.spanish.mobDropInfoMessage", "&6%mobName% &amobs tener un &6%chance% % &aposibilidad de caer &6%amount%  %itemName%");
            messagesCfg.set("messages.spanish.customDropsNotSetMessage", "&6%mobName% &amobs no tengo ningún conjunto de gotas personalizado");
            messagesCfg.set("messages.spanish.mobWorthMessage", "&6%mobName% &amobs valen &6%lowWorth% &aadólar(s)");
            messagesCfg.set("messages.spanish.mobRangeWorthMessage", "&6%mobName% &amobs valen entre &6%lowWorth% &ay &6%highWorth% &adolares");
            messagesCfg.set("messages.spanish.setLowWorthSuccessMessage", "&aBaja valor para &6%mobName% &amobs se ha establecido en &6%lowWorth% &adólar(s)");
            messagesCfg.set("messages.spanish.setHighWorthSuccessMessage", "&aAlto valor para &6%mobName% &amobs se ha establecido en &6%highWorth% &adólar(s)");
            messagesCfg.set("messages.spanish.defaultDropsTrueMessage", "&aGotas predeterminadas para &6%mobName% &amobs ajustado a &6verdadero");
            messagesCfg.set("messages.spanish.defaultDropsFalseMessage", "&aGotas predeterminadas para &6%mobName% &amobs ajustado a &6falso");
            messagesCfg.set("messages.spanish.customDropsTrueMessage", "&aGotas personalizadas para &6%mobName% &amobs ajustado a &6verdadero");
            messagesCfg.set("messages.spanish.customDropsFalseMessage", "&aGotas personalizadas para &6%mobName% &amobs ajustado a &6falso");

            // German
            messagesCfg.set("messages.german.mobKillerOnMessage", "&aMobKiller nachricht &6am");
            messagesCfg.set("messages.german.mobKillerOffMessage", "&aMobKiller nachricht &6aus");
            messagesCfg.set("messages.german.accessDeniedMessage", "&cSie haben keinen Zugriff auf diesen Befehl");
            messagesCfg.set("messages.german.eggSpawnRewardTrueMessage", "&aGeld, das von Mobs, die mit Eiern gespawnt wurden, belohnt wird, wird auf &6wahr gesetzt");
            messagesCfg.set("messages.german.eggSpawnRewardFalseMessage", "&aGeld, das von Mobs, die mit Eiern gespawnt wurden, belohnt wird, wird auf &6falsch gesetzt");
            messagesCfg.set("messages.german.spawnerSpawnRewardTrueMessage", "&aGeld, das von Spawner-Mobs belohnt wird, ist eingestellt auf &6wahr");
            messagesCfg.set("messages.german.spawnerSpawnRewardFalseMessage", "aGeld, das von Spawner-Mobs belohnt wird, ist eingestellt auf &6falsch");
            messagesCfg.set("messages.german.tamedWolvesRewardTrueMessage", "&aGeld, das von Mobs belohnt wird, die von gezähmten Wölfen getötet wurden, setzen auf &6wahr");
            messagesCfg.set("messages.german.tamedWolvesRewardFalseMessage", "&aGeld, das von Mobs belohnt wird, die von gezähmten Wölfen getötet wurden, setzen auf &6falsch");
            messagesCfg.set("messages.german.reloadingMessage", "&bMoney4Mobs Neuladen");
            messagesCfg.set("messages.german.reloadConfirmMessage", "&6Money4Mobs Nachladen abgeschlossen");
            messagesCfg.set("messages.german.overrideKillMessageTrue", "&aoverrideKillMessage einstellen &6wahr");
            messagesCfg.set("messages.german.overrideKillMessageFalse", "&aoverrideKillMessage einstellen &6falsch");
            messagesCfg.set("messages.german.setLowWorthCommandErrorMessage", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk setLowWorth [mobName] [menge]");
            messagesCfg.set("messages.german.setHighWorthCommandErrorMessage", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk setHighWorth [mobName] [menge]");
            messagesCfg.set("messages.german.addCustomDropsErrorMessage", "&cError: Benutzerdefinierte Drops können Spielern nicht hinzugefügt werden");
            messagesCfg.set("messages.german.addCustomDropsCommandErrorMessage", "&cError: &7Geben Sie einen Befehl wie diesen ein -> /mk addCustomDrop [mobName] [menge] [chance]");
            messagesCfg.set("messages.german.addCustomDropInvalidMobErrorMessage", "&cError: &6%mobName% &7ist kein gültiger mob");
            messagesCfg.set("messages.german.addCustomDropAlreadyPresentErrorMessage", "&cError: &6%itemName% &7ist bereits als benutzerdefinierter Tropfen vorhanden");
            messagesCfg.set("messages.german.addCustomDropSuccessMessage", "&aAdded &6%amount%  %itemName% &azu &6%mobName% &a fällt mit a&6%chance% % &achance zu fallen");
            messagesCfg.set("messages.german.removeCustomDropSuccessMessage", "&6%itemName%  &atropfen entfernt von &6%mobName% &amobs");
            messagesCfg.set("messages.german.customDropsDoNotExistErrorMessage", "&cError: &7Tropfen gibt es nicht für &6%mobName% &amobs");
            messagesCfg.set("messages.german.customDropsNotEnabledMessage", "&aBenutzerdefinierte Drops sind nicht aktiviert für &6%mobName% &amobs");
            messagesCfg.set("messages.german.setLowWorthTooHighErrorMessage", "&cError: &7Niedriger Wert für &6%mobName% %7mobs ist höher als der von dir eingestellte Wert");
            messagesCfg.set("messages.german.setHighWorthTooLowErrorMessage", "&cError: &7Hoher Wert für &6%mobName% &7mobs ist niedriger als der von Ihnen eingestellte Wert");
            messagesCfg.set("messages.german.mobDropInfoMessage", "&6%mobName% &amobs habe einen &6%chance% % &achance fallen zu lassen &6%amount%  %itemName%");
            messagesCfg.set("messages.german.customDropsNotSetMessage", "&6%mobName% &amobs habe keine benutzerdefinierten Drops");
            messagesCfg.set("messages.german.mobWorthMessage", "&6%mobName% &amobs sind es wert &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.german.mobRangeWorthMessage", "&6%mobName% &amobs sind es wert zwischen &6%lowWorth% &aund &6%highWorth% &adollars");
            messagesCfg.set("messages.german.setLowWorthSuccessMessage", "&aNiedriger Wert für &6%mobName% &amobs wurde eingestellt auf &6%lowWorth% &adollar(s)");
            messagesCfg.set("messages.german.setHighWorthSuccessMessage", "&aHoher Wert für &6%mobName% &amobs wurde eingestellt auf &6%highWorth% &adollar(s)");
            messagesCfg.set("messages.german.defaultDropsTrueMessage", "&aStandardtropfen für &6%mobName% &amobs einstellen &6wahr");
            messagesCfg.set("messages.german.defaultDropsFalseMessage", "&aStandardtropfen für &6%mobName% &amobs einstellen &6falsch");
            messagesCfg.set("messages.german.customDropsTrueMessage", "&aBenutzerdefinierte Tropfen für &6%mobName% &amobs einstellen &6wahr");
            messagesCfg.set("messages.german.customDropsFalseMessage", "&aBenutzerdefinierte Tropfen für &6%mobName% &amobs einstellen &6falsch");

            // Italian
            messagesCfg.set("messages.italian.mobKillerOnMessage", "&aMessaggio MobKiller &6attivo");
            messagesCfg.set("messages.italian.mobKillerOffMessage", "&aMessaggio MobKiller &6disattivato");
            messagesCfg.set("messages.italian.accessDeniedMessage", "&cNon hai accesso a questo comando");
            messagesCfg.set("messages.italian.eggSpawnRewardTrueMessage", "&aIl denaro ricompensato dai mob generati con le uova è impostato su &6vero");
            messagesCfg.set("messages.italian.eggSpawnRewardFalseMessage", "&aIl denaro ricompensato dai mob generati con le uova è impostato su &6falso");
            messagesCfg.set("messages.italian.spawnerSpawnRewardTrueMessage", "&aIl denaro ricompensato dai mob generatori è impostato su &6vero");
            messagesCfg.set("messages.italian.spawnerSpawnRewardFalseMessage", "&aIl denaro ricompensato dai mob generatori è impostato su &6falso");
            messagesCfg.set("messages.italian.tamedWolvesRewardTrueMessage", "&aDenaro ricompensato da mob uccisi da lupi addomesticati impostati su &6vero");
            messagesCfg.set("messages.italian.tamedWolvesRewardFalseMessage", "&aDenaro ricompensato da mob uccisi da lupi addomesticati impostati su &6falso");
            messagesCfg.set("messages.italian.reloadingMessage", "&bRicaricare Money4Mobs");
            messagesCfg.set("messages.italian.reloadConfirmMessage", "&6Money4Mobs Ricarica completata");
            messagesCfg.set("messages.italian.overrideKillMessageTrue", "&aoverrideKillMessage impostato &6vero");
            messagesCfg.set("messages.italian.overrideKillMessageFalse", "&aoverrideKillMessage impostato &6falso");
            messagesCfg.set("messages.italian.setLowWorthCommandErrorMessage", "&cErrore: &7Inserisci il comando in questo modo -> /mk setLowWorth [mobNome] [quantità]");
            messagesCfg.set("messages.italian.setHighWorthCommandErrorMessage", "&cErrore: &7Inserisci il comando in questo modo -> /mk setHighWorth [mobNome] [quantità]");
            messagesCfg.set("messages.italian.addCustomDropsErrorMessage", "&cErrore: Custom drops cannot be added to players");
            messagesCfg.set("messages.italian.addCustomDropsCommandErrorMessage", "&cErrore: &7Inserisci il comando in questo modo -> /mk addCustomDrop [mobNome] [quantità] [opportunità]");
            messagesCfg.set("messages.italian.addCustomDropInvalidMobErrorMessage", "&cErrore: &6%mobName% &7non è un mob valido");
            messagesCfg.set("messages.italian.addCustomDropAlreadyPresentErrorMessage", "&cErrore: &6%itemName% &7è già presente come drop personalizzato");
            messagesCfg.set("messages.italian.addCustomDropSuccessMessage", "&aAggiunto &6%amount%  %itemName% &aper &6%mobName% &agocce con a &6%chance% % &apossibilità di cadere");
            messagesCfg.set("messages.italian.removeCustomDropSuccessMessage", "&6%itemName%  &agocce rimosse da &6%mobName% &amobs");
            messagesCfg.set("messages.italian.customDropsDoNotExistErrorMessage", "&cErrore: &7Le gocce non esistono per &6%mobName% &amobs");
            messagesCfg.set("messages.italian.customDropsNotEnabledMessage", "&aI drop personalizzati non sono abilitati per &6%mobName% &amobs");
            messagesCfg.set("messages.italian.setLowWorthTooHighErrorMessage", "&cErrore: &7Valore basso per &6%mobName% %7mobs è superiore al valore che stai impostando");
            messagesCfg.set("messages.italian.setHighWorthTooLowErrorMessage", "&cErrore: &7Grande valore per &6%mobName% &7mobs è inferiore al valore che stai impostando");
            messagesCfg.set("messages.italian.mobDropInfoMessage", "&6%mobName% &amobs avere un &6%chance% % &apossibilità di cadere &6%amount%  %itemName%");
            messagesCfg.set("messages.italian.customDropsNotSetMessage", "&6%mobName% &amobs non hai set di drop personalizzati");
            messagesCfg.set("messages.italian.mobWorthMessage", "&6%mobName% &amobs valgono &6%lowWorth% &adollar(o/i)");
            messagesCfg.set("messages.italian.mobRangeWorthMessage", "&6%mobName% &amobs valgono tra &6%lowWorth% &ae &6%highWorth% &adollari");
            messagesCfg.set("messages.italian.setLowWorthSuccessMessage", "&aValore basso per &6%mobName% &amobs è stato impostato su &6%lowWorth% &adollar(o/i)");
            messagesCfg.set("messages.italian.setHighWorthSuccessMessage", "&aGrande valore per &6%mobName% &amobs è stato impostato su &6%highWorth% &adollar(o/i)");
            messagesCfg.set("messages.italian.defaultDropsTrueMessage", "&aGocce predefinite per &6%mobName% &amobs impostato &6vero");
            messagesCfg.set("messages.italian.defaultDropsFalseMessage", "&aGocce predefinite per &6%mobName% &amobs impostato &6falso");
            messagesCfg.set("messages.italian.customDropsTrueMessage", "&aDrop personalizzati per &6%mobName% &amobs impostato &6vero");
            messagesCfg.set("messages.italian.customDropsFalseMessage", "&aDrop personalizzati per &6%mobName% &amobs impostato &6falso");
            messagesCfg.save(messagesFile);

        }
        messagesCfg = YamlConfiguration.loadConfiguration(messagesFile);
    }
}
