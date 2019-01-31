package SeedsTheif.tasks.thieving;

import SeedsTheif.data.Items;
import SeedsTheif.data.Victim;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;

public class Drop extends Task {
    @Override
    public boolean validate() {
        if (Utils.getVictim() == Victim.MASTER_FARMER) {
            return Utils.isStunned() || (Inventory.isFull() && Inventory.containsAnyExcept(Items.KEEP));
        }
        if (Utils.getVictim() == Victim.TEA_STALL || Utils.getVictim() == Victim.SILK_STALL) {
            return Inventory.contains(Items.DROP);
        }
        return false;
    }

    @Override
    public int execute() {
        Item[] items = Utils.getVictim() == Victim.MASTER_FARMER
                ? Inventory.getItems(item -> !Arrays.asList(Items.KEEP).contains(item.getName()))
                : Inventory.getItems(item -> Arrays.asList(Items.DROP).contains(item.getName()));
        for (Item item : items) {
            item.interact("Drop");
            Time.sleep(100, 300);
        }
        return 1000;
    }
}
