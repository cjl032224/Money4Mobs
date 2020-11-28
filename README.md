# Money4Mobs
## Give money to players and custom drops when they kill a mob
### Permissions for M4M
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

### Permissions for group specific multipliers - 5 levels plus Operator
#### If group/player has multiple level permissions set, the highest level take precedent
Permission | Description
------------ | ------------
m4m.multiplier.level-1 | Permission for level-1 multiplier
m4m.multiplier.level-2 | Permission for level-2 multiplier
m4m.multiplier.level-3 | Permission for level-3 multiplier
m4m.multiplier.level-4 | Permission for level-4 multiplier
m4m.multiplier.level-5 | Permission for level-5 multiplier
