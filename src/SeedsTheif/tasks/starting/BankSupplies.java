package SeedsTheif.tasks.starting;

import SeedsTheif.data.Items;
import SeedsTheif.data.Store;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class BankSupplies extends Task {
    @Override
    public boolean validate() {
        return Inventory.containsAll(Items.SUPPLIES) || Bank.isOpen();
    }

    @Override
    public int execute() {
        Store.setAction("Bank supplies");

        if (Bank.isOpen()) {
            if (Bank.containsAll(Items.SUPPLIES)) {
                Store.setSuppliesBought();
            }
            Bank.depositInventory();
            Time.sleepUntil(Inventory::isEmpty, 500, 2000);
        } else {
            Bank.open();
        }
        return 1000;
    }
}
