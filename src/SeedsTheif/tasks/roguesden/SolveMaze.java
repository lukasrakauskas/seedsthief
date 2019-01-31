package SeedsTheif.tasks.roguesden;

import SeedsTheif.data.MazeLocations;
import SeedsTheif.data.MazeLocations;
import SeedsTheif.data.MazeLocations;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.movement.position.ScenePosition;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

import java.util.Arrays;
import java.util.List;

public class SolveMaze extends Task {
    @Override
    public boolean validate() {
        return MazeLocations.MAZE.contains(Players.getLocal());
        // or has special gem
    }

    @Override
    public int execute() {
        Player me = Players.getLocal();

        if (MazeLocations.CONTORTION_BARS.contains(me)) {
            SceneObject bars = SceneObjects.getNearest("Contortion Bars");
            if (bars != null) bars.interact(a -> true);
        }

        if (MazeLocations.FIRST_PENDULUM.contains(me)) {
            // corner
            Movement.setWalkFlag(new Position(3037, 5002, 1));
        }

        if (me.getPosition().equals(new Position(3037, 5002, 1))) {
            Movement.setWalkFlag(new Position(3028, 5002, 1));
        }

        if (me.getPosition().equals(new Position(3028, 5002, 1))) {
            SceneObject grill = SceneObjects.getNearest("Grill");
            if (grill != null) grill.interact(a -> true);
        }

        if (me.getPosition().equals(new Position(3023, 5001, 1))) {
            Movement.setWalkFlag(new Position(3015, 5002, 1));
        }

        if (me.getPosition().equals(new Position(3015, 5002, 1))) {
            Movement.setWalkFlag(new Position(3013, 5004, 1));
        }

        if (me.getPosition().equals(new Position(3013, 5004, 1))) {
            Movement.setWalkFlag(new Position(3008, 5003, 1));
        }

        if (me.getPosition().equals(new Position(3008, 5003, 1))) {
            SceneObject ledge = SceneObjects.getNearest("Ledge");
            if (ledge != null) ledge.interact(a -> true);
        }

        if (MazeLocations.LEDGE_TO_SAW.contains(me)) {
            Movement.walkTo(new Position(2971, 5017, 1));
        }

        if (me.getPosition().equals(new Position(2971, 5017, 1))) {
            Movement.setWalkFlag(new Position(2969, 5017, 1));
        }

        if (MazeLocations.PENDULUMS_TO_LEDGE.contains(me)) {
            if (!me.getPosition().equals(new Position(2959, 5028, 1))) {
                Movement.setWalkFlag(new Position(2959, 5028, 1));
            } else {
                SceneObject ledge = SceneObjects.getNearest("Ledge");
                if (ledge != null) ledge.interact(a -> true);
            }
        }

        if (me.getPosition().equals(new Position(2958, 5035, 1))) {
            Movement.setWalkFlag(new Position(2963, 5050, 1));
        }

        if (me.getPosition().equals(new Position(2963, 5050, 1))) {
            SceneObject floor = SceneObjects.getFirstAt(new Position(2963, 5051, 1));
            if (floor != null) floor.interact("Search");
        }

        if (me.getPosition().equals(new Position(2963, 5051, 1))) {
            SceneObject passageway = SceneObjects.getFirstAt(new Position(2957, 5069, 1));
            if (passageway != null) passageway.interact(a -> true);
        }

        if (MazeLocations.PASSAGE.contains(me)) {
            if (me.getPosition().equals(new Position(2957, 5072, 1))) {
                Movement.setWalkFlag(new Position(2957, 5074, 1));
            } else {
                SceneObject passageway = SceneObjects.getFirstAt(new Position(2955, 5095, 1));
                if (passageway != null) {
                    passageway.interact(a -> true);
                } else {
                    Movement.walkTo(new Position(2954, 5089, 1));
                }
            }
        }

        if (me.getPosition().equals(new Position(2955, 5098, 1))) {
            SceneObject passageway = SceneObjects.getFirstAt(new Position(2972, 5097, 1));
            if (passageway != null) {
                passageway.interact(a -> true);
            }
        }

        if (me.getPosition().equals(new Position(2972, 5094, 1))) {
            SceneObject grill = SceneObjects.getFirstAt(new Position(2972, 5094, 1));
            if (grill != null) {
                grill.interact(a -> true);
            }
        }

        if (me.getPosition().equals(new Position(2972, 5093, 1))) {
            Movement.setWalkFlag(new Position(2974, 5085, 1));
        }

        if (me.getPosition().equals(new Position(2974, 5085, 1))) {
            SceneObject ledge = SceneObjects.getNearest("Ledge");
            if (ledge != null) ledge.interact(a -> true);
        }

        if (me.getPosition().equals(new Position(2991, 5087, 1))) {
            SceneObject wall = SceneObjects.getFirstAt(new Position(2993, 5087, 1));
            if (wall != null) wall.interact("Search");
        }

        if (me.getPosition().equals(new Position(2993, 5088, 1))) {
            Movement.setWalkFlag(new Position(3000, 5087, 1));
        }

        if (me.getPosition().equals(new Position(3000, 5087, 1))) {
            Movement.setWalkFlag(new Position(3005, 5089, 1));
        }

        if (me.getPosition().equals(new Position(3005, 5089, 1))) {
            Pickable tile = Pickables.getFirstAt(new Position(3018, 5080, 1));
            if (tile != null) tile.interact(a -> true);
        }

        if (Inventory.contains("Tile")) {
            SceneObject door = SceneObjects.getNearest("Door");
            InterfaceComponent puzzle = Interfaces.getComponent(293, 3);
            if (puzzle != null) {
                puzzle.interact(a -> true);
            } else {
                if (door != null) {
                    door.interact(a -> true);
                }
            }
        }

        if (me.getPosition().equals(new Position(3024, 5082, 1))) {
            SceneObject grill = SceneObjects.getNearest("Grill");
            if (grill != null) grill.interact(a -> true);
        }

        if (MazeLocations.GRILL_PUZZLE.contains(me)) {
            List<Position> positions = Arrays.asList(
                    new Position(3032, 5078, 1),
                    new Position(3036, 5076, 1),
                    new Position(3039, 5079, 1),
                    new Position(3042, 5076, 1),
                    new Position(3044, 5069, 1),
                    new Position(3041, 5068, 1),
                    new Position(3040, 5070, 1),
                    new Position(3038, 5069, 1)
            );

            positions.forEach(position -> {
                SceneObject grill = SceneObjects.getFirstAt(position);
                if (grill != null && grill.isPositionInteractable()) {
                    grill.interact(a -> true);
                }
            });
        }

        if (MazeLocations.PAST_GRILL_PUZZLE.contains(me)) {
            Movement.walkTo(new Position(3028, 5033, 1));
        }

        if (me.getPosition().equals(new Position(3028, 5033, 1))) {
            SceneObject grill = SceneObjects.getNearest("Grill");
            if (grill != null) grill.interact(a -> true);
        }

        if (me.getPosition().equals(new Position(3014, 5033, 1))) {
            SceneObject grill = SceneObjects.getFirstAt(new Position(3010, 5033, 1));
            if (grill != null) grill.interact(a -> true);
        }

        if (me.getPosition().equals(new Position(3009, 5033, 1))) {
            Movement.setWalkFlag(new Position(3008, 5033, 1));
        }

        if (me.getPosition().equals(new Position(3008, 5033, 1))) {
            Movement.walkTo(new Position(2992, 5045, 1));
        }

        if (me.getPosition().equals(new Position(3000, 5034, 1))) {
            Movement.walkTo(new Position(2992, 5045, 1));
        }

        if (me.getPosition().equals(new Position(2993, 5044, 1))) {
            Movement.walkTo(new Position(2992, 5045, 1));
        }

        if (me.getPosition().equals(new Position(2992, 5045, 1))) {
            Movement.walkTo(new Position(2992, 5049, 1));
            return 3000;
        }

        if (me.getPosition().equals(new Position(2992, 5049, 1))) {
            Movement.walkTo(new Position(2992, 5053, 1));
            return 3000;
        }

        if (me.getPosition().equals(new Position(2992, 5053, 1))) {
            Movement.walkTo(new Position(2992, 5067, 1));
        }

        if (me.getPosition().equals(new Position(2992, 5067, 1))) {
            Movement.walkTo(new Position(2992, 5071, 1));
            return 3000;
        }

        if (me.getPosition().equals(new Position(2992, 5071, 1))) {
            Movement.walkTo(new Position(2992, 5075, 1));
            return 3000;
        }

        if (me.getPosition().equals(new Position(2992, 5075, 1))) {
            Movement.walkTo(new Position(3001, 5067, 1));
        }

        if (MazeLocations.GUARDS.contains(me)) {
            if (!Inventory.contains("Flash powder")) {
                ScenePosition position = new Position(3009, 5063, 1).toScene();
                Pickable badPowder = Pickables.getNearest("Flash powder");
                if (badPowder != null) {
                    Pickable powder = new Pickable(badPowder.getProvider(), position.getX(), position.getY(), position.getFloorLevel());
                    powder.interact("Take");
                    return 1500;
                }
            } else {
                if (Inventory.getFirst(5559).getStackSize() == 5) {
                    Npc guard = Npcs.getNearest(3191);
                    if (guard != null) {
                        Inventory.use(item -> item.getId() == 5559, guard);
                    }
                } else {
                    Movement.walkTo(new Position(3028, 5056, 1));
                }
            }
        }

        if (MazeLocations.LAST_PENDULUMS.contains(me)) {
            Movement.setWalkFlag(new Position(3028, 5047, 1));
        }

        if (me.getPosition().equals(new Position(3028, 5047, 1))) {
            SceneObject safe = SceneObjects.getNearest("Wall safe");
            if (safe != null) safe.interact(a -> true);
        }

        return 1000;
    }
}
