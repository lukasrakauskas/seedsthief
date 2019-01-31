package SeedsTheif.tasks.starting;

import SeedsTheif.data.Items;
import SeedsTheif.data.Store;
import SeedsTheif.utils.GE;
import SeedsTheif.utils.ItemBuyData;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class GetSupplies extends Task {
    private final ItemBuyData[] supplies = new ItemBuyData[] {
            new ItemBuyData("Jug of wine", 10, 6000),
            new ItemBuyData("Dodgy necklace", 2000, 300), // 200 count
            new ItemBuyData("Varrock teleport", 1000, 5),
            new ItemBuyData("Stamina potion(4)", 8000, 40),
            new ItemBuyData("Energy potion(4)", 1000, 40),
            new ItemBuyData("Games necklace(8)", 2000, 1),
            new ItemBuyData("Amulet of glory(6)", 20000, 1),
            new ItemBuyData("Necklace of passage(5)", 2000, 1),
    };

    @Override
    public boolean validate() {
        if (Bank.isOpen() && Bank.containsAll(Items.SUPPLIES)) return false;
        return Game.isLoggedIn() && Utils.hasMembership() && !Inventory.containsAll(Items.SUPPLIES);
    }

    @Override
    public int execute() {
        Store.setAction("Get supplies");
        if (!Inventory.contains("Coins")) {
            if (Bank.isOpen()) {
                Bank.withdrawAll("Coins");
            } else {
                Bank.open();
            }
        }

        if (Inventory.contains("Coins")) {
            GE.buyItems(supplies);
        }

        return 1000;
    }
}
