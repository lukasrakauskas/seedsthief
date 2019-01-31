package SeedsTheif.tasks.starting;

import SeedsTheif.data.Store;
import SeedsTheif.utils.GE;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;

public class GetMembership extends Task {
    private InterfaceComponent BOND_REDEEMED = Interfaces.getComponent(162, 58, 200);

    @Override
    public boolean validate() {
        return Game.isLoggedIn() && !Utils.hasMembership() &&
                (Inventory.contains("Old school bond (untradeable)")
                    || Inventory.getFirst(x -> x.getName().equals("Coins") && x.getStackSize() >= 4000000) != null);
    }

    @Override
    public int execute() {
        Store.setAction("Get membership");

        if (Dialog.isOpen()) {
            if (Dialog.canContinue()) {
                if (BOND_REDEEMED.getText().contains("14 days")) {
                    Game.logout();
                    return 3000;
                } else {
                    Dialog.processContinue();
                }
            } else {
                Time.sleepUntil(Dialog::canContinue, 500, 10000);
            }
            return 3000;
        }

        if (Interfaces.isOpen(66)) {
            InterfaceComponent confirmBtn = Interfaces.getComponent(66, 24);
            if (confirmBtn.getComponent(10).getText().contains("Confirm")) {
                confirmBtn.interact("Confirm");
                Time.sleep(5000);
            } else {
                Interfaces.getComponent(66, 7).click();
                Time.sleep(5000);
            }
            return 1000;
        }

        if (!Inventory.contains("Old school bond (untradeable)")) {
            GE.buy("Old school bond", 4000000, 1);
        } else {
            Inventory.getFirst("Old school bond (untradeable)").interact("Redeem");
        }

        return 1000;
    }
}
