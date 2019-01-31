package SeedsTheif.tasks.agility.courses;

import SeedsTheif.data.Locations;
import SeedsTheif.data.Obstacle;
import SeedsTheif.data.Obstacles;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.function.Predicate;

public class Draynor extends Task {
    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.AGILITY) >= 10;
    }

    @Override
    public int execute() {
        Store.setAction("Draynor course");
        Position player = Players.getLocal().getPosition();

        if (!onCourse()) return getToCourse();

        for (Obstacle obstacle : Obstacles.DRAYNOR) {
            if (obstacle.validate(player)) {
                obstacle.execute();
                Time.sleepUntil(() -> !Players.getLocal().isMoving() && !Players.getLocal().isMoving(), 500, 5000);
                return 1000;
            }
        }

        if (!Players.getLocal().isMoving() && !Players.getLocal().isAnimating()) {
            Movement.walkTo(Locations.DRAYNOR.ROUGH_WALL);
        }

        return 1000;
    }

    private int getToCourse() {
        if (Locations.DRAYNOR.COURSE.contains(Players.getLocal())) {
            Movement.walkTo(Locations.DRAYNOR.ROUGH_WALL);
        } else {
            if (Dialog.isOpen()) {
                Dialog.process(option -> option.contains("Draynor"));
            } else {
                Predicate<Item> gloryPredicate = item -> item.getName().contains("glory") && Arrays.asList(item.getActions()).contains("Rub");
                Item glory = Inventory.getFirst(gloryPredicate);
                if (glory != null) {
                    glory.interact("Rub");
                } else {
                    if (Bank.isOpen()) {
                        if (Bank.contains(gloryPredicate)) {
                            Bank.withdraw(gloryPredicate, 1);
                            Time.sleepUntil(() -> Inventory.contains(gloryPredicate), 500, 2000);
                        } else {
                            Log.severe("Has no amulet of glory");
                            return -1;
                        }
                    } else {
                        Bank.open();
                        Time.sleep(2000, 3000);
                    }
                }
            }
        }
        return 1000;
    }

    private boolean onCourse() {
        return Locations.DRAYNOR.COURSE.contains(Players.getLocal()) || Players.getLocal().getFloorLevel() != 0;
    }
}
