package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.MazeLocations;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class ConsumePotions extends Task {
    private final Predicate<Item> ENERGY_POTION = item -> item.getName().contains("Energy potion");
    private final Predicate<Item> STAMINA_POTION = item -> item.getName().contains("Stamina potion");

    @Override
    public boolean validate() {
        if (MazeLocations.MAZE.contains(Players.getLocal())) return false;
        return Movement.getRunEnergy() < 80 || !Movement.isStaminaEnhancementActive();
    }

    @Override
    public int execute() {
        if (Inventory.contains(ENERGY_POTION) && Inventory.contains(STAMINA_POTION)) {
            if (Bank.isOpen()) {
                Bank.close();
                Time.sleepUntil(Bank::isClosed, 500, 200);
            }

            if (Movement.getRunEnergy() < 80) {
                Inventory.getFirst(ENERGY_POTION).click();
                Time.sleep(600, 1000);
            }

            if (Movement.getRunEnergy() >= 80 && !Movement.isStaminaEnhancementActive()) {
                Inventory.getFirst(STAMINA_POTION).click();
                Time.sleep(600, 1000);
            }
        } else {
            if (Bank.isOpen()) {
                if (Inventory.containsAnyExcept(item -> item.getName().contains("potion"))) {
                    Bank.depositInventory();
                    Time.sleepUntil(Inventory::isEmpty, 500, 2000);
                }

                if (Bank.getCount("Rogue's equipment crate") == 5) {
                    Store.setCrateCount(5);
                    return 1000;
                }

                if (Bank.contains(ENERGY_POTION) && Bank.contains(STAMINA_POTION)) {
                    if (!Inventory.contains(ENERGY_POTION)) {
                        Bank.withdraw(ENERGY_POTION, 2);
                        Time.sleepUntil(() -> Inventory.contains(ENERGY_POTION), 500, 2000);
                    }

                    if (!Inventory.contains(STAMINA_POTION)) {
                        Bank.withdraw(STAMINA_POTION, 1);
                        Time.sleepUntil(() -> Inventory.contains(STAMINA_POTION), 500, 2000);
                    }
                } else {
                    Log.severe("Has no stamina or energy potions in bank");
                    return -1;
                }
            } else {
                Bank.open();
                Time.sleepUntil(Bank::isOpen, 500, 2000);
            }
        }
        return 1000;
    }
}
