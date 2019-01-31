package SeedsTheif.tasks;

import SeedsTheif.data.Store;
import SeedsTheif.tasks.thieving.*;
import SeedsTheif.tasks.thieving.victims.MasterFarmer;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.script.task.Task;

public class FarmSeeds extends Task {
    private final String[] ROGUE_PIECES = new String[] {
            "Rogue mask",
            "Rogue top",
            "Rogue trousers",
            "Rogue boots",
            "Rogue gloves"
    };

    private final Task[] tasks = {
            new EatFood(),
            new Drop(),
            new SleepStun(),
            new Banking(),
            new EquipDodgyNecklace(),
            new MasterFarmer()
    };

    @Override
    public boolean validate() {
        return Equipment.containsAll(ROGUE_PIECES);
    }

    @Override
    public int execute() {
        Store.setAction("Farm seeds");

        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 300;
    }
}
