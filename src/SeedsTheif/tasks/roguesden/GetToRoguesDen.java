package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.Locations;
import SeedsTheif.data.MazeLocations;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.function.Predicate;

public class GetToRoguesDen extends Task {
    Predicate<Item> GAMES_NECKLACE = item -> item.getName().contains("Games necklace");

    @Override
    public boolean validate() {
        Position player = Players.getLocal().getPosition();
        return !(MazeLocations.ROGUES_DEN.contains(player) || MazeLocations.MAZE.contains(player));
    }

    @Override
    public int execute() {
        if (Locations.BURTHORPE.contains(Players.getLocal())) {
            SceneObject trapdoor = SceneObjects.getNearest("Trapdoor");
            if (trapdoor != null) {
                if (Movement.isInteractable(trapdoor)) {
                    trapdoor.click();
                } else {
                    Movement.walkTo(trapdoor);
                }
            }
        } else {
            if (Dialog.isOpen()) {
                Dialog.process(option -> option.contains("Burthorpe"));
                Time.sleepUntil(() -> !Players.getLocal().isAnimating(), 1000, 6000);
                return 1000;
            }

            if (Inventory.contains(GAMES_NECKLACE)) {
                Inventory.getFirst(GAMES_NECKLACE).interact("Rub");
            } else {
                if (Bank.isOpen()) {
                    if (Bank.contains(GAMES_NECKLACE)) {
                        Bank.withdraw(GAMES_NECKLACE, 1);
                        Time.sleepUntil(() -> Inventory.contains(GAMES_NECKLACE), 500, 2000);
                    } else {
                        Log.severe("No games necklace");
                        return -1;
                    }
                } else {
                    Bank.open();
                }
            }
        }

        return 1000;
    }
}
