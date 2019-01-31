package SeedsTheif.tasks;

import SeedsTheif.data.Store;
import SeedsTheif.tasks.starting.BankSupplies;
import SeedsTheif.tasks.starting.GetMembership;
import SeedsTheif.tasks.starting.GetSupplies;
import SeedsTheif.tasks.starting.HopToMembers;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Starting extends Task {
    private final Area GRAND_EXCHANGE_AREA = Area.rectangular(3143, 3511, 3185, 3469);

    private final Task[] tasks = {
            new GetMembership(),
            new HopToMembers(),
            new GetSupplies(),
            new BankSupplies()
    };

    @Override
    public boolean validate() {
        return !Store.isSuppliesBought() && GRAND_EXCHANGE_AREA.contains(Players.getLocal());
    }

    @Override
    public int execute() {
        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 1000;
    }
}
