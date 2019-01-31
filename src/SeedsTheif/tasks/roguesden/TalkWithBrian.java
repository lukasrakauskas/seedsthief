package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.MazeLocations;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class TalkWithBrian extends Task {
    @Override
    public boolean validate() {
        if (MazeLocations.MAZE.contains(Players.getLocal())) return false;
        return MazeLocations.ROGUES_DEN.contains(Players.getLocal()) && !Store.hasTalkedWithBrianOnce();
    }

    @Override
    public int execute() {
        if (Dialog.isOpen()) {
            if (Dialog.canContinue()) Dialog.processContinue();
            Dialog.process(option -> option.contains("Yes actually"));
            if (Dialog.process(option -> option.contains("sounds good")) || Dialog.process(option -> option.contains("maze again"))) {
                Store.setTalkedWithBrianOnce(true);
            }
        } else {
            Npc brian = Npcs.getNearest(npc -> npc.getName().contains("Brian"));
            if (brian != null) {
                if (Movement.isInteractable(brian)) {
                    brian.interact("Talk-to");
                    Time.sleepUntil(Dialog::isOpen, 500, 5000);
                } else {
                    Movement.walkTo(brian);
                    Time.sleepUntil(() -> Movement.isInteractable(brian), 500, 5000);
                }
            }
        }
        return 1000;
    }
}
