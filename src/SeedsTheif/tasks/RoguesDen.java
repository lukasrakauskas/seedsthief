package SeedsTheif.tasks;

import SeedsTheif.tasks.roguesden.*;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.script.task.Task;

public class RoguesDen extends Task {

    private final Task[] tasks = {
            new GetRoguesEquipment(),
            new GetToRoguesDen(),
            new TalkWithBrian(),
            new ConsumePotions(),
            new StartMaze(),
            new SolveMaze()
    };

    @Override
    public boolean validate() {
        return !Equipment.containsAll(
                "Rogue mask",
                "Rogue top",
                "Rogue gloves",
                "Rogue trousers",
                "Rogue boots"
        ) && Skills.getLevel(Skill.THIEVING) >= 50 && Skills.getLevel(Skill.AGILITY) >= 50;
    }

    @Override
    public int execute() {

        if (Dialog.canContinue()) {
            Dialog.processContinue();
            return 1000;
        }

        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 1000;
    }
}
