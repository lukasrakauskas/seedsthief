package SeedsTheif.tasks.thieving.victims;

import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.*;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;

public class TeaStall extends Task {
    private Area VARROCK = Area.rectangular(3175, 3456, 3273, 3382);

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.THIEVING) >= 5;
    }

    @Override
    public int execute() {
        if (!VARROCK.contains(Players.getLocal())) getToVarrock();

        if (VARROCK.contains(Players.getLocal())) {
            if (Players.getLocal().getPosition().equals(new Position(3269, 3412, 0))) {
                SceneObject teaStall = SceneObjects.getNearest("Tea Stall");
                if (teaStall != null && hasAction(teaStall, "Steal-from")) {
                    teaStall.click();
                }
            } else {
                Movement.walkTo(new Position(3269, 3412, 0));
            }
        }

        return 600;
    }

    private void getToVarrock() {
        if (Store.hasVarrockTab()) {
            if (Inventory.contains("Varrock teleport")) {
                if (Bank.isOpen()) {
                    Bank.close();
                } else {
                    Inventory.getFirst("Varrock teleport").click();
                }
            } else {
                if (Bank.isOpen()) {
                    if (Bank.contains("Varrock teleport")) {
                        Bank.withdraw("Varrock teleport", 1);
                    } else {
                        Store.setVarrockTab(false);
                    }
                } else {
                    Bank.open();
                }
            }
        } else {
            Movement.walkTo(new Position(3269, 3412, 0));
        }
    }

    private boolean hasAction(SceneObject sceneObject, String action) {
        return Arrays.asList(sceneObject.getActions()).contains(action);
    }
}
