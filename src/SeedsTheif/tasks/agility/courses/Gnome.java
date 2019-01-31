package SeedsTheif.tasks.agility.courses;

import SeedsTheif.data.Locations;
import SeedsTheif.data.Obstacle;
import SeedsTheif.data.Obstacles;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.function.Predicate;

public class Gnome extends Task {
    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.AGILITY) < 10;
    }

    @Override
    public int execute() {
        Store.setAction("Gnome course");
        Position player = Players.getLocal().getPosition();

        if (!onCourse()) return getToCourse();

        for (Obstacle obstacle : Obstacles.GNOME) {
            if (obstacle.validate(player)) {
                obstacle.execute();
                Time.sleepUntil(() -> !Players.getLocal().isMoving() && !Players.getLocal().isMoving(), 500, 5000);
                return 1000;
            }
        }

        if (!Players.getLocal().isMoving() && !Players.getLocal().isAnimating() && player.getFloorLevel() == 0) {
            Movement.walkTo(Locations.GNOME.LOG_BALANCE);
        }

        return 1000;
    }

    private int getToCourse() {
        if (Locations.GNOME.OUTPOST_TO_COURSE.contains(Players.getLocal())) {
            if (Dialog.isOpen()) {
                if (Dialog.canContinue()) Dialog.processContinue();
                Dialog.process("Okay then.");
                Time.sleepUntil(() -> !Players.getLocal().isAnimating(), 500, 5000);
            } else {
                if (!Locations.GNOME.STRONGHOLD.contains(Players.getLocal())) {
                    SceneObject gate = SceneObjects.getNearest(190);
                    if (gate != null) gate.click();
                } else {
                    Movement.walkTo(Locations.GNOME.LOG_BALANCE);
                }
            }
        } else {
            if (Dialog.isOpen()) {
                if (Dialog.canContinue()) Dialog.processContinue();
                Dialog.process(option -> option.contains("Outpost"));
                Time.sleepUntil(() -> !Players.getLocal().isAnimating(), 500, 5000);
            }

            Predicate<Item> passageNecklacePredicate = item -> item.getName().contains("passage(");
            Item passageNecklace = Inventory.getFirst(passageNecklacePredicate);
            if (passageNecklace != null) {
                passageNecklace.interact("Rub");
            } else {
                if (Bank.isOpen()) {
                    if (Bank.contains(passageNecklacePredicate)) {
                        Bank.withdraw(passageNecklacePredicate, 1);
                        Time.sleepUntil(() -> Inventory.contains(passageNecklacePredicate), 500, 2000);
                    } else {
                        Log.severe("Has no necklace of passage");
                        return -1;
                    }
                } else {
                    Bank.open();
                    Time.sleep(2000, 3000);
                }
            }
        }
        return 1000;
    }

    private boolean onCourse() {
        return Locations.GNOME.COURSE.contains(Players.getLocal()) || Players.getLocal().getFloorLevel() != 0;
    }
}
