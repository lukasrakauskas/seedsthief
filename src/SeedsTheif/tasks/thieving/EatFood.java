package SeedsTheif.tasks.thieving;

import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.script.task.Task;

public class EatFood extends Task {
    @Override
    public boolean validate() {
        return Health.getCurrent() <= 3 && Inventory.contains("Jug of wine");
    }

    @Override
    public int execute() {
        if (Inventory.contains("Jug of wine")) {
            Inventory.getFirst("Jug of wine").interact("Drink");
            Time.sleepUntil(() -> Health.getCurrent() > 3, 500, 5000);
        }
        return 1000;
    }
}
