package SeedsTheif.tasks.thieving;

import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class RunFromCombat extends Task {
    @Override
    public boolean validate() {
        return Utils.isInCombat();
    }

    @Override
    public int execute() {
        if (!Movement.isRunEnabled()) Movement.toggleRun(true);
        if (Utils.isInCombat()) Movement.walkTo(BankLocation.getNearest().getPosition());
        return 300;
    }
}
