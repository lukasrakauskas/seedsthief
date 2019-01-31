package SeedsTheif.tasks.agility.courses;

import SeedsTheif.data.Locations;
import SeedsTheif.data.Obstacle;
import SeedsTheif.data.Obstacles;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import java.util.Arrays;
import java.util.List;

public class Varrock extends Task {

    @Override
    public boolean validate() {
        return Skills.getCurrentLevel(Skill.AGILITY) >= 30;
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
            Movement.walkTo(Locations.VARROCK.ROUGH_WALL);
        }
        return 1000;
    }

    private boolean onCourse() {
        return Locations.VARROCK.COURSE.contains(Players.getLocal()) || Players.getLocal().getFloorLevel() != 0;
    }
}
