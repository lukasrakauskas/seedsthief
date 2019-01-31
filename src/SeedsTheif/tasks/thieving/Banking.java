package SeedsTheif.tasks.thieving;

import SeedsTheif.data.Victim;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class Banking extends Task {
    @Override
    public boolean validate() {
        if (Utils.getVictim() == Victim.TEA_STALL) return false;
        if (Utils.getVictim() == Victim.MASTER_FARMER) {
            return !Inventory.contains("Jug of wine") || !Inventory.contains("Dodgy necklace") || Inventory.isFull();
        }
        return !Inventory.contains("Jug of wine");
    }

    @Override
    public int execute() {
        if (!Bank.isOpen()) openBank();
        if (shouldDeposit()) depositItems();

        if (Utils.getVictim() == Victim.MASTER_FARMER) {
            if (!Inventory.contains("Dodgy necklace")) {
                Bank.withdraw("Dodgy necklace", 5);
                Time.sleepUntil(() -> Inventory.contains("Dodgy necklace"), 500, 5000);
            }
        }

        if (!Inventory.contains("Jug of wine")) {
            Bank.withdraw("Jug of wine", 15);
            Time.sleepUntil(() -> Inventory.contains("Jug of wine"), 500, 5000);
        }

        return 1000;
    }

    private void openBank() {
        Bank.open();
        Time.sleepUntil(Bank::isOpen, 500, 5000);
    }

    private boolean shouldDeposit() {
        return Inventory.containsAnyExcept("Jug of wine", "Dodgy necklace") || Inventory.isFull();
    }

    private void depositItems() {
        Bank.depositInventory();
        Time.sleepUntil(Inventory::isEmpty, 300, 2000);
    }

}
