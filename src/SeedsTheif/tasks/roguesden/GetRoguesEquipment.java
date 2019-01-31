package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class GetRoguesEquipment extends Task {

    private final String[] ROGUE_PIECES = new String[] {
            "Rogue mask",
            "Rogue top",
            "Rogue trousers",
            "Rogue boots",
            "Rogue gloves"
    };

    @Override
    public boolean validate() {
        return Store.getCrateCount() == 5;
        // might work might not
    }

    @Override
    public int execute() {
        if (Inventory.containsAll(ROGUE_PIECES)) {
            for (String piece : ROGUE_PIECES) {
                Item item = Inventory.getFirst(piece);
                if (item != null) item.click();
                Time.sleep(500, 1000);
            }
            return 1000;
        }

        if (Inventory.contains("Rogue's equipment crate")) {
            if (Bank.isOpen()) Bank.close();

            for (int i = 0; i < ROGUE_PIECES.length; i++) {
                if (Dialog.isOpen()) {
                    Dialog.process(option -> option.contains("Rogue equipment"));

                    if (!Inventory.contains(ROGUE_PIECES[i])) {
                        Dialog.process(i);
                        Time.sleep(500, 1000);
                    }
                } else {
                    if (Inventory.contains("Rogue's equipment crate")) Inventory.getFirst("Rogue's equipment crate").click();
                    Time.sleepUntil(Dialog::isOpen, 500, 2000);
                }
            }

        } else {
            if (Bank.isOpen()) {
                Bank.withdrawAll("Rogue's equipment crate");
            } else {
                Bank.open();
            }
        }
        return 1000;
    }
}
