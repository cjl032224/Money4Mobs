# Money4Mobs
## Give money to players and custom drops when they kill a mob
### Permissions for M4M
Link to default mobs.yml file ---> https://github.com/latch93/Money4Mobs/blob/master/src/Latch/Money4Mobs/mobs.yml

Command | Permission | Description
------ | ------ | ------
Not Applicable | m4m.rewardMoney | Needed permission for players to receive money.
/mk toggleKM | m4m.command.mk.toggleKM | Turn on/off mob kill message for player. (this is for each individual player to toggle message.
mk worth [mobName] | m4m.command.mk.worth | Gets worth of mob.
/mk setHighWorth [mobName] [amount] | m4m.command.mk.setHighWorth | Sets the high worth amount for the given mob.
/mk setLowWorth [mobName] [amount] | m4m.command.mk.setLowWorth | Sets the low worth amount for the given mob.
/mk drops [mobName] | m4m.command.mk.drops | Displays custom drops, if any, and chance of custom mob dropping the custom item.
/mk addCustomDrop [mobName] [itemName] [amount] [chance] | m4m.command.mk.addCustomDrop | Sets a custom item to drop for the given mob. Chance is represent in percent from 0 to 100%.
/mk removeCustomDrop [mobName] [itemName] | m4m.command.mk.removeCustomDrop | Removes a custom item set for the given mob.
/mk toggleCustomDrops [mobName] | m4m.command.mk.toggleCustomDrops | Toggles if the given mob will drop custom items set.
/mk toggleDefaultDrops [mobName] | m4m.command.mk.toggleDefaultDrops | Toggles if the given mob will keep their default drops. If false for Cows, they will no longer drop leather and raw beef.
/mk toggleMoneyFromSpawnEggs | m4m.command.mk.spawneggs | Toggles if players get money from mobs spawned in with eggs. Defaults to false on first time load.
/mk toggleMoneyFromSpawners | m4m.command.mk.spawners | Toggles if players get money from mobs spawned in from spawners. Defaults to false on first time load.
/mk language [language] | m4m.command.mk.language | Changes language of messages for M4M per player.
/mk reload | m4m.command.mk.reload | Reloads M4M data with manually changed values in config files without needing to reload or restart the whole server
/mk toggleMoneyFromTamedWolves | m4m.command.mk.toggleMoneyFromTamedWolves | Toggles if players receive money when their tamed wolf kills a mob
/mk mobRewardWorlds [mobName] | m4m.command.mk.mobRewardWorlds | Displays the worlds a mob will get rewarded money in
/mk addWorld [worldName] | m4m.command.mk.addWorld | Adds a world to the mobs.yml file for each mob with a value of true
/mk removeWorld [worldName] | m4m.command.mk.removeWorld | Removes an existing world from the mobs.yml file
Not Applicable| m4m.bypass.ipBan | Allows a user to bypass the ipBan option for players on the same IP

## Permissions for group specific multipliers
## 'operator' multiplier should not be deleted or key changed
### Group/player should only have 1 multiplier
Permission | Description
------------ | ------------
m4m.multiplier.level-1 | Permission for level-1 multiplier
m4m.multiplier.level-2 | Permission for level-2 multiplier
m4m.multiplier.level-3 | Permission for level-3 multiplier
m4m.multiplier.level-4 | Permission for level-4 multiplier
m4m.multiplier.level-5 | Permission for level-5 multiplier

## These level multipliers can be updated and more multipliers are able to be added
## Example in config.yml -
#### group-multiplier:
  #### level-1: 1.0 
  #### superbad: 1.5
  #### naruto: 2.0 
### These permissions would be as follows  
Permission | Description
------------ | ------------
m4m.multiplier.level-1 | Permission for level-1 multiplier
m4m.multiplier.superbad | Permission for superbad multiplier
m4m.multiplier.naruto | Permission for naruto multiplier

Link to default mobs.yml file ---> https://github.com/Latch93/Money4Mobs/blob/master/src/Latch/Money4Mobs/mobs.yml
Link to default config.yml file ---> https://github.com/Latch93/Money4Mobs/blob/master/src/Latch/Money4Mobs/config.yml


## How to set up a custom message
* Messages go off of the language set in users.language and corresponds to the language.[language] 
* * If the users language is english, then in the messages.yml file, the message layout needs to be language.english, not language.English. This option is case sensitive.
* Colors are the the default Minecraft color codes, "&7, &a, &f"
* In order to display the amount rewarded to the player upon mob kill, use '%amount%'
* In order to display the balance of the player after being rewarded the money, use '%balance%'
* In order to display the low worth of a mob, use '%lowWorth%'
* In order to display the high worth of a mob, use '%highWorth'
* In order to display the mob name, use '%mobName%'
* In order to display the item name, use '%itemName%'
* In order to display the chance that an item has to drop, use '%chance%'
* If the default message does not already contain one of the placeholders, then the message will not display properly
Each word or value needs to be separated by a space. The color code should be before the word or group of words, i.e., '&6Hello'
#### Examples
 * 1.) 
         customDropsTrueMessage:
            message: '&aCustom drops for &6%mobName% &amobs set to &6true'
            location: ChatMenu
            
        * 1- Result) "Custom drops for Ghast mobs set to true"
 * 2.)
         customDropsFalseMessage:
            message: '&aCustom drops for &6%mobName% &amobs set to &6false'
            location: ChatMenu
            
        * 2 - Result) "Custom drops for Ghast mobs set to false"
 * 3.)
         languageChangeSuccessMessage:
           message: '&aChanged Money4Mobs messages to &6English'
           location: ChatMenu
           
        * 3 - Result) "Changed Money4Mobs messages to English"
 * 4.)
         moneyRewardedMessage:
           message: '&aYou were given &6$ %amount%  &aand now have &6$ %balance%'
           location: ActionBar
           
       * 4 - Result) "Rewarded $10 and now have $100"
 * 5.) 
         moneySubtractedMessage:
           message: '&6$ %amount%  &awas taken and you now have &6$ %balance%'
           location: ActionBar
           
        * 5 - Result) "$10 was taken and you now have $100"
        
 Message Name | Available Placeholders | Reason for Triggering Display
------ | ------- | -------
mobKillerOnMessage |  | /mk toggleKM - Turns on the display for the kill message
mobKillerOffMessage |  | /mk toggleKM - Turns off the display for the kill message
accessDeniedMessage |  | Occurs when a player does not have permission for a command
eggSpawnRewardTrueMessage |  | /mk toggleMoneyFromSpawnEggs - When the 'spawneggs' property in mobs.yml gets set to true 
eggSpawnRewardFalseMessage |  | /mk toggleMoneyFromSpawnEggs - When the 'spawneggs' property in mobs.yml gets set to false 
spawnerSpawnRewardTrueMessage |  | /mk toggleMoneyFromSpawners - When the 'spawners' property in mobs.yml gets set to true 
spawnerSpawnRewardFalseMessage |  | /mk toggleMoneyFromSpawners - When the 'spawners' property in mobs.yml gets set to false 
tamedWolvesRewardTrueMessage |  | /mk toggleMoneyFromTamedWolves -  When the 'tamedWolvesGiveMoney' property in mobs.yml gets set to true 
tamedWolvesRewardFalseMessage |  | /mk toggleMoneyFromTamedWolves -  When the 'tamedWolvesGiveMoney' property in mobs.yml gets set to false 
reloadingMessage |  | /mk reload - Tells the command sender that Money4Mobs is reloading
reloadConfirmMessage |  | /mk reload - Tells the command sender that Money4Mobs has completed reloading
addCustomDropsErrorMessage |  |  /mk addCustomDrop - When a command sender tries to add a drop to a player
invalidMobErrorMessage | %mobName% | When a command sender inputs a mob that is invalid
addCustomDropAlreadyPresentErrorMessage | %itemName% | /mk addCustomDrop - When a command sender tries to add a drop to a mob that is already present
addCustomDropSuccessMessage | %mobName%, %chance%, %amount%, %itemName  | /mk addCustomDrop - When a command sender successfully adds a drop to a mob
removeCustomDropSuccessMessage | %mobName% | /mk removeCustomDrop - When a command sender successfully removes a drop from a mob
customDropsDoNotExistErrorMessage | %mobName% | /mk drops - When there are no drops present for the mob
customDropsNotEnabledMessage | %mobName% | /mk drops - When the '[mobName].customDrops' property in mobs.yml is set to false
setLowWorthTooHighErrorMessage | %mobName% | /mk setLowWorth - When the command sender tries to set a low worth that is higher than the high worth for that mob
setHighWorthTooLowErrorMessage | %mobName% | /mk setHighWorth - When the command sender tries to set a high worth that is lower than the low worth for that mob
mobDropInfoMessage | %mobName%, %chance%, %amount%, %itemName% | /mk drops - Displays the drop information for mobs on success
customDropsNotSetMessage | %mobName% |  /mk drops - When the '[mobName].customDrops' property in mobs.yml is set to true, but no drops are present for that mob
mobWorthMessage | %mobName%, %lowWorth% | /mk worth - Displays the worth of a mob when both the lowWorth and highWorth are the same amount
mobRangeWorthMessage | %mobName%, %lowWorth%, %highWorth% | /mk worth - Displays the worth of a mob when the lowWorth and highWorth are a different amount than each other
setLowWorthSuccessMessage | %mobName%, %lowWorth% | /mk setLowWorth - When the command sender successfully sets the lowWorth for that mob
setHighWorthSuccessMessage | %mobName%, %highWorth% | /mk setHighWorth - When the command sender successfully sets the highWorth for that mob
defaultDropsTrueMessage | %mobName% | /mk toggleDefaultDrops - When the '[mobName].keepDefaultDrops' property in mobs.yml gets set to true 
defaultDropsFalseMessage | %mobName% | /mk toggleDefaultDrops - When the '[mobName].keepDefaultDrops' property in mobs.yml gets set to false
customDropsTrueMessage | %mobName% | /mk toggleCustomDrops - When the '[mobName].customDrops' property in mobs.yml gets set to true 
customDropsFalseMessage | %mobName% | /mk toggleCustomDrops - When the '[mobName].customDrops' property in mobs.yml gets set to false 
languageChangeSuccessMessage |  | /mk language - When the command sender's language in the users.yml file is updated successfully
moneyRewardedMessage | %amount%, %balance%, %mobName% | When a player kills a mob and the low worth is greater than 0
moneySubtractedMessage | %amount%, %balance%, %mobName% | When a player kills a mob and the low worth is less than 0
removeCustomDropsCommandErrorMessage | | /mk removeCustomDrop - When a player improperly uses the command
toggleCustomDropsCommandErrorMessage | | /mk toggleCustomDrops - When a player improperly uses the command
toggleDefaultDropsCommandErrorMessage | | /mk toggleDefaultDrops - When a player improperly uses the command
worthCommandErrorMessage | | /mk worth - When a player improperly uses the command
dropsCommandErrorMessage | |  /mk drops - When a player improperly uses the command
languageCommandErrorMessage | | /mk language - When a player improperly uses the command
toggleKMCommandErrorMessage | | /mk toggleKM - When a player improperly uses the command
toggleMoneyFromSpawnEggsCommandErrorMessage | | /mk toggleMoneyFromSpawnEggs - When a player improperly uses the command
toggleMoneyFromSpawnersCommandErrorMessage | | /mk toggleMoneyFromSpawners - When a player improperly uses the command
toggleMoneyFromTamedWolvesCommandErrorMessage | | /mk toggleMoneyFromTamedWolves - When a player improperly uses the command
reloadCommandErrorMessage | | /reload- When a player improperly uses the command
setLowWorthCommandErrorMessage |  | /mk setLowWorth - When a player improperly uses the command
setHighWorthCommandErrorMessage |  | /mk setHighWorth - When a player improperly uses the command
addCustomDropsCommandErrorMessage |  | /mk addCustomDrop - When a player improperly uses the command
incompleteCommandErrorMessage | | Displays if the user tries to type a command that does not exist
mobRewardWorldsMessage | %mobName% | /mk mobRewardWorlds - When a player checks the worlds a mob is rewarded money in 
mobRewardWorldsCommandErrorMessage | | /mk mobRewardWorlds - When a player improperly uses the command
addWorldSuccessMessage | %worlds% | /mk addWorld - When a player successfully adds a new world to the mobs.yml
addWorldCommandErrorMessage | | /mk addWorld - When a player improperly uses the command
removeWorldSuccessMessage | %worlds% | /mk removeWorld - When a player successfully removes an existing world from the mobs.yml
removeWorldCommandErrorMessage | | /mk removeWorld - When a player improperly uses the command
removeWorldFailureMessage | | /mk removeWorld - When a player tries to remove a world that does not exist, or has incorrect capitalization

Action Multipliers in config.yml when 'isMultipliersAggregate' is false will take the highest priority multiplier that fit the conditions
Action Multipliers in config.yml when 'isMultipliersAggregate' is true will multiply all active multipliers that fit the conditions
 Multiplier | Condition for Triggering | Additional Comments
------ | ------- | -------
riding-horse | If a player kills a mob whilst riding a horse | 
riding-mule | If a player kills a mob whilst riding a mule | 
riding-donkey | If a player kills a mob whilst riding a donkey | 
riding-strider | If a player kills a mob whilst riding a strider | 
riding-pig | If a player kills a mob whilst riding a pig | 
fallDamage| If a player kills a mob with fall damage |
longDistance| If a player kills a mob equal or greater than the distance set in longDistance.distance | Distance is set in blocks away from player
projectile| If a player kills a mob with a bow or crossbow |
noWeapon| If a player kills a mob without a weapon | If a player beats a mob to death with an item that is not a weapon or tool, i.e. (Hoe, Sword, Shield, Trident, etc)
mountedMob| If a player kills a mob that is mounted, i.e, spider jockey | This is only for the mob riding the other mob. So skeleton on a spider and not the spider

mobSpawnedReasons.yml
Adds mobs to a list if mob was spawned by a spawn egg or spawner
Removes mob from the list if the mob is killed by any action other than command

## Performance change for reasons mob spawned. Option in config.yml 'oldSpawnReasonLogic'
I've updated a toggle in the config file 'oldSpawnReasonLogic'. If this is true, then it will use the old logic which only keeps a track of mobs in the session. If this is false, it will use the newer method.

### The trade off of the old method:
#### Pros:
#####    Better performance on server
#### Cons:
#####    Some mobs will give money even if spawneggs/spawners is set to false. Sometimes when a chunk is reloaded, but happens when the server is restarted or reloaded

### The trade off of the new method
#### Pros:
#####    Keeps track of every mob spawned so no matter the instance, a mob will or will not give money based on configuration settings
#### Cons:
#####    Performance heavy and can bog down a server if it contains a stacked mob plugin or has many players
