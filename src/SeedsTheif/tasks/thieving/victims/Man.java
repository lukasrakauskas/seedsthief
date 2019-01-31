package SeedsTheif.tasks.thieving.victims;

import SeedsTheif.data.Store;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.*;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Man extends Task {
    private Area LUMBRIDGE = Area.rectangular(3201, 3236, 3242, 3200).setIgnoreFloorLevel(true);

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.THIEVING) >= 1;
    }

    @Override
    public int execute() {
        Store.setAction("Man");
        if (!LUMBRIDGE.contains(Players.getLocal())) getToLumbridge();

        if (LUMBRIDGE.contains(Players.getLocal())) {

            if (!Inventory.contains("Jug of wine")) {
                if (Bank.isOpen()) {
                    Bank.withdraw("Jug of wine", 15);
                    Time.sleepUntil(() -> Inventory.contains("Jug of wine"), 500, 5000);
                } else {
                    Bank.open();
                    Time.sleep(1000, 3000);
                }
            } else {
                Npc man = Npcs.getNearest("Man");
                if (man != null) {
                    man.interact("Pickpocket");
                } else {
                    Movement.walkTo(new Position(3221, 3218, 0));
                }
            }
        }

        return 300;
    }

    private void getToLumbridge() {
        if (!Players.getLocal().isAnimating()) Magic.cast(Spell.Modern.HOME_TELEPORT);
        Time.sleepUntil(() -> !Players.getLocal().isAnimating(), 500, 10000);
    }
}
