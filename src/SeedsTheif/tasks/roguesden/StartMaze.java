package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.MazeLocations;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class StartMaze extends Task {
    @Override
    public boolean validate() {
        return MazeLocations.ROGUES_DEN.contains(Players.getLocal());
    }

    @Override
    public int execute() {
        if (!Inventory.isEmpty() || Equipment.getItems().length != 0) {
            if (Bank.isOpen()) {
                if (!Inventory.isEmpty()) {
                    Bank.depositInventory();
                    Time.sleepUntil(Inventory::isEmpty, 500, 2000);
                }
                if (Equipment.getItems().length != 0) {
                    Bank.depositEquipment();
                    Time.sleepUntil(() -> Equipment.getItems().length == 0, 500, 2000);
                }
                Store.setCrateCount(Bank.getCount("Rogue's equipment crate"));
            } else {
                Bank.open();
            }
            return 1000;
        }

        if (!Movement.isRunEnabled()) {
            Movement.toggleRun(true);
            Time.sleepUntil(Movement::isRunEnabled, 500, 2000);
        }

        SceneObject doorway = SceneObjects.getNearest("Doorway");
        if (doorway != null) doorway.click();
        return 1000;
    }
}
