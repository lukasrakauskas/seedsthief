package SeedsTheif.tasks.thieving;

import SeedsTheif.data.Victim;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class EquipDodgyNecklace extends Task {
    @Override
    public boolean validate() {
        return Utils.getVictim() == Victim.MASTER_FARMER && !Equipment.contains("Dodgy necklace") && Inventory.contains("Dodgy necklace");
    }

    @Override
    public int execute() {
        Inventory.getFirst("Dodgy necklace").interact(a -> true);
        Time.sleepUntil(() -> Equipment.contains("Dodgy necklace"), 500, 5000);
        return 1000;
    }
}
