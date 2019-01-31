package SeedsTheif.tasks.thieving.victims;

import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.movement.transportation.CharterShip;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.function.Predicate;

public class SilkStall extends Task {
    private final Predicate<Item> COINS = item -> item.getName().equals("Coins") && item.getStackSize() >= 3200;

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.THIEVING) >= 20;
    }

    @Override
    public int execute() {
        if (!inArdougne()) return getToArdougne();

        if (inArdougne()) {
            // change

            if (Players.getLocal().getPosition().equals(new Position(2662, 3316, 0))) {
                SceneObject silkStall = SceneObjects.getFirstAt(new Position(2662, 3314, 0));
                if (silkStall != null && hasAction(silkStall, "Steal-from")) {
                    silkStall.click();
                }
            } else {
                // change
                Movement.walkTo(new Position(2662, 3316, 0));
                return 1500;
            }

        }
        return 600;
    }

    private int getToArdougne() {
        if (Inventory.contains(COINS)) {
            if (CharterShip.isInterfaceOpen()) {
                CharterShip.charter(CharterShip.Destination.PORT_KHAZARD);
                Time.sleepUntil(this::inArdougne, 1000, 5000);
            } else {
                Position position = CharterShip.getNearest().getPosition();
                if (position.distance() < 5) {
                    CharterShip.open();
                } else {
                    Movement.walkTo(position);
                    return 1500;
                }
            }
        } else {
            if (Bank.isOpen()) {
                if (Inventory.containsAnyExcept(COINS)) {
                    Bank.depositInventory();
                    Time.sleepUntil(Inventory::isEmpty, 500, 2000);
                }

                if (Bank.contains(COINS)) {
                    Bank.withdraw(COINS, 3200);
                    Time.sleepUntil(() -> Inventory.contains(COINS), 500, 2000);
                } else {
                    Log.severe("Has no coins for ships");
                    return -1;
                }
            } else {
                Bank.open();
                Time.sleep(1000, 2000);
            }
        }
        return 1000;
    }

    private boolean inArdougne() {
        return Players.getLocal().getX() < 2757;
    }

    private boolean hasAction(SceneObject sceneObject, String action) {
        return Arrays.asList(sceneObject.getActions()).contains(action);
    }
}
