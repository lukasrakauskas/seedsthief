package SeedsTheif.tasks.thieving;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.script.task.Task;

public class OpenPouch extends Task {
    @Override
    public boolean validate() {
        return Inventory.contains(item -> item.getName().equals("Coin pouch") && item.getStackSize() == 28)
                || (Skills.getLevel(Skill.THIEVING) > 5 && Inventory.contains("Coin pouch"));
    }

    @Override
    public int execute() {
        Inventory.getFirst("Coin pouch").click();
        Time.sleepUntil(() -> !Inventory.contains("Coin pouch"), 500, 2000);
        return 1000;
    }
}
