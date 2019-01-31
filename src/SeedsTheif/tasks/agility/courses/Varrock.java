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
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Varrock extends Task {

    private boolean hasTeletab = true;

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.AGILITY) < 50;
    }

    @Override
    public int execute() {
        Store.setAction("Varrock course");
        Position player = Players.getLocal().getPosition();

        if (!onCourse()) return getToCourse();

        for (Obstacle obstacle : Obstacles.VARROCK) {
            if (obstacle.validate(player)) {
                obstacle.execute();
                Time.sleepUntil(() -> !Players.getLocal().isMoving() && !Players.getLocal().isMoving(), 500, 5000);
                return 1000;
            }
        }

        if (!Players.getLocal().isMoving() && !Players.getLocal().isAnimating()) {
            Movement.walkTo(Locations.VARROCK.ROUGH_WALL);
        }

        return 1000;
    }

    private int getToCourse() {
        if (!Locations.VARROCK.COURSE.contains(Players.getLocal())) {
            if (hasTeletab) {
                Item teletab = Inventory.getFirst("Varrock teleport");
                if (teletab != null) {
                    teletab.interact("Break");
                    Time.sleep(1000);
                    hasTeletab = false;
                } else {
                    if (Bank.isOpen()) {
                        if (Bank.contains("Varrock teleport")) {
                            Bank.withdraw("Varrock teleport", 1);
                            Time.sleepUntil(() -> Inventory.contains("Varrock teleport"), 500, 2000);
                        } else {
                            Log.severe("Has no teletab");
                            hasTeletab = false;
                            return 1000;
                        }
                    } else {
                        Bank.open();
                        Time.sleep(2000, 3000);
                    }
                }
            } else {
                Movement.walkTo(Locations.VARROCK.ROUGH_WALL);
            }
        }
        return 1000;
    }

    private boolean onCourse() {
        return Locations.VARROCK.COURSE.contains(Players.getLocal()) || Players.getLocal().getFloorLevel() != 0;
    }
}
