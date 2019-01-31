package SeedsTheif.tasks.thieving.victims;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.function.Predicate;

public class MasterFarmer extends Task {
    private final Area DRAYNOR = Area.rectangular(3066, 3263, 3107, 3238);
    private final String[] ROGUE_PIECES = new String[] {
            "Rogue mask",
            "Rogue top",
            "Rogue trousers",
            "Rogue boots",
            "Rogue gloves"
    };

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.THIEVING) >= 38 || Equipment.containsAll(ROGUE_PIECES);
    }

    @Override
    public int execute() {
        if (!DRAYNOR.contains(Players.getLocal())) return getToDraynor();

        if (DRAYNOR.contains(Players.getLocal())) {
            if (Health.getCurrent() > 3) {
                Npc farmer = Npcs.getNearest("Master Farmer");
                if (farmer != null) {
                    farmer.interact("Pickpocket");
                } else {
                    Movement.walkTo(new Position(3081, 3252, 0));
                }
            }
        }
        return 300;
    }

    private int getToDraynor() {
        if (Dialog.isOpen()) {
            Dialog.process(option -> option.contains("Draynor"));
        } else {
            Predicate<Item> gloryPredicate = item -> item.getName().contains("glory(");
            Item glory = Inventory.getFirst(gloryPredicate);
            if (glory != null) {
                glory.interact("Rub");
            } else {
                if (Bank.isOpen()) {
                    if (Bank.contains(gloryPredicate)) {
                        Bank.withdraw(gloryPredicate, 1);
                        Time.sleepUntil(() -> Inventory.contains(gloryPredicate), 500, 2000);
                    } else {
                        Log.severe("Has no amulet of glory");
                        return -1;
                    }
                } else {
                    Bank.open();
                    Time.sleep(2000, 3000);
                }
            }
        }
        return 1000;
    }

}
