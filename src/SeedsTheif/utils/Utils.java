package SeedsTheif.utils;

import SeedsTheif.data.Store;
import SeedsTheif.data.Victim;
import org.json.JSONArray;
import org.json.JSONObject;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;

public class Utils {
    public static boolean isStunned() {
        return Players.getLocal().getGraphic() != -1;
    }

    public static Victim getVictim() {
        int thievingLvl = Skills.getCurrentLevel(Skill.THIEVING);
        if (thievingLvl <= 5) return Victim.MAN;
        if (thievingLvl <= 20) return Victim.TEA_STALL;
        if (thievingLvl <= 38) return Victim.SILK_STALL;
        return Victim.MASTER_FARMER;
    }

    public static boolean isInCombat() {
        Npc targeter = Npcs.getFirst(x -> x.getTarget() == Players.getLocal());
        if (targeter != null && Players.getLocal().isHealthBarVisible()) {
            if (targeter.getName().equals("Stray dog") || targeter.getName().equals("Master Farmer")) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean hasMembership() {
        return !Interfaces.getComponent(109, 2).getText().contains("None");
    }

    public static void sendStatusUpdate() {
        JSONObject message = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject botStatus = new JSONObject();

        payload.put("rspeer_name", Script.getRSPeerUser().getUsername());

        botStatus.put("status", Store.getAction());
        botStatus.put("name", Players.getLocal().getName());
        botStatus.put("world", Worlds.getCurrent());

        JSONArray bots = new JSONArray();
        bots.put(botStatus);
        payload.put("bots", bots);

        message.put("type", "UPDATE_BOT_STATUS");
        message.put("payload", payload);

        WSClient.sendMessage(message.toString());
    }
}
