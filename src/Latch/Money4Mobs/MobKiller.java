package Latch.Money4Mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public abstract class MobKiller implements CommandExecutor {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static MobConfigManager cfgm;

    private static List<MobModel> mobListFromConfig = new ArrayList<MobModel>();
    private static List<MobModel> mm = cfgm.getMobModelFromConfig();
    public static void rewardPlayerMoney(Player pa, Entity e, Economy econ) {

        Integer money = 0;
        String es = e.toString();

        for(int i = 0; i < mm.size(); i++){
            String entity = "Craft"+mm.get(i).getMobName();
            if(es.equals(entity)){
                money = mm.get(i).getWorth();
            }
        }
        EconomyResponse r = econ.depositPlayer(pa, money);
        List<Latch.Money4Mobs.Player> playerList = Money4Mobs.getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            if (pa.getName().equals(playerList.get(i).getPlayerName())) {
                Boolean displayMessage = playerList.get(i).getKillerMessage();
                if(displayMessage == true) {
                    if (r.amount != 0) {
                        if(r.transactionSuccess()) {
                            pa.sendMessage(String.format("You were given %s and now have %s", econ.format(r.amount), econ.format(r.balance)));
                        } else {
                            pa.sendMessage(String.format("An error occured: %s", r.errorMessage));
                        }
                    }
                }
            }
        }
    }


}
