# Money4Mobs
## Give money to players and custom drops when they kill a mob
### Permissions for M4M
Link to default mobs.yml file ---> https://github.com/lakeboy93/Money4Mobs/blob/master/src/Latch/Money4Mobs/mobs.yml
Command | Permission | Description
------ | ------- | -------
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
/mk defaultLanguage [language] | m4m.command.mk.defaultLanguage | Updates the default language of the server. Updates each user's language option with the given language
/mk reload | m4m.command.mk.reload | Reloads M4M data with manually changed values in config files without needing to reload or restart the whole server
/mk toggleMoneyFromTamedWolves | m4m.command.mk.toggleMoneyFromTamedWolves | Toggles if players receive money when their tamed wolf kills a mob

## Permissions for group specific multipliers - 5 levels plus Operator
### If group/player has multiple level permissions set, the highest level take precedent
Permission | Description
------------ | ------------
m4m.multiplier.level-1 | Permission for level-1 multiplier
m4m.multiplier.level-2 | Permission for level-2 multiplier
m4m.multiplier.level-3 | Permission for level-3 multiplier
m4m.multiplier.level-4 | Permission for level-4 multiplier
m4m.multiplier.level-5 | Permission for level-5 multiplier

## How to set up a custom kill message
To add spaces to your message, '|' symbol needs to be added. 
Colors are the the default Minecraft colors and are uppercased and surrounded by '%' symbols
In order to display the amount rewarded to the player upon mob kill, use '%AMOUNT%'
In order to display the balance of the player after being rewarded the money, use '%BALANCE%'
Each word or value needs to be separated by a space. Adding a space does not equate to a space in the result
#### Examples
* 1.) customMessage: '%GREEN% Rewarded | %GOLD% $ %AMOUNT% | %GREEN% and | now | have | %GOLD% $ %BALANCE% %GREEN% .'
 * 1 - Result) "Rewarded $10 and now have $100."
* 2.) customMessage: '%GREEN% Rewarded %GOLD% $ %AMOUNT% | %GREEN% and | now have | %GOLD% $ %BALANCE% %GREEN% | .'
 * 2 - Result) "Rewarded$10 and nowhave $100 ."
 
