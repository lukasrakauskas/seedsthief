package SeedsTheif.data;

import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;

public class Obstacles {
    /*
            if (isPlayerAt(Locations.GNOME.LOG_BALANCE)) {
                interactWithObstacle("Walk-across", "Log balance");
            } else if (isPlayerAt(Locations.GNOME.OBSTACLE_NET1)) {
                interactWithObstacle("Climb-over", "Obstacle net");
            } else if (isPlayerAt(Locations.GNOME.TREE_BRANCH1)) {
                interactWithObstacle("Climb", "Tree branch");
            } else if (isPlayerAt(Locations.GNOME.BALANCING_ROPE)) {
                interactWithObstacle("Walk-on", "Balancing rope");
            } else if (isPlayerAt(Locations.GNOME.TREE_BRANCH2)) {
                interactWithObstacle("Climb-down", "Tree branch");
            } else if (isPlayerAt(Locations.GNOME.OBSTACLE_NET2)) {
                interactWithObstacle("Climb-over", "Obstacle net");
            } else if (isPlayerAt(Locations.GNOME.OBSTACLE_PIPE)) {
                interactWithObstacle("Squeeze-through", "Obstacle pipe");
            } else {
                Movement.walkTo(Locations.GNOME.LOG_BALANCE);
            }
            */
    public static Obstacle[] GNOME = new Obstacle[] {
            new Obstacle(null, Locations.GNOME.LOG_BALANCE, "Log balance"),
            new Obstacle(null, Locations.GNOME.OBSTACLE_NET1, "Obstacle net"),
            new Obstacle(null, Locations.GNOME.TREE_BRANCH1, "Tree branch"),
            new Obstacle(null, Locations.GNOME.BALANCING_ROPE, "Balancing rope"),
            new Obstacle(null, Locations.GNOME.TREE_BRANCH2, "Tree branch"),
            new Obstacle(null, Locations.GNOME.OBSTACLE_NET2, "Obstacle net"),
            new Obstacle(null, Locations.GNOME.OBSTACLE_PIPE, "Obstacle pipe")
    };

    /*
    if (isPlayerAt(Locations.DRAYNOR.ROUGH_WALL)) {
        interactWithObstacle("Climb", "Rough wall");
    } else if (isPlayerAt(Locations.DRAYNOR.TIGHTROPE1)) {
        interactWithObstacle("Cross", "Tightrope");
    } else if (isPlayerAt(Locations.DRAYNOR.TIGHTROPE2)) {
        interactWithObstacle("Cross", "Tightrope");
    } else if (isPlayerAt(Locations.DRAYNOR.NARROW_WALL)) {
        interactWithObstacle("Balance", "Narrow wall");
    } else if (isPlayerAt(Locations.DRAYNOR.WALL)) {
        interactWithObstacle("Jump-up", "Wall");
    } else if (isPlayerAt(Locations.DRAYNOR.GAP)) {
        interactWithObstacle("Jump", "Gap");
    } else if (isPlayerAt(Locations.DRAYNOR.CRATE)) {
        interactWithObstacle("Climb-down", "Crate");
    } else {
        Movement.walkTo(Locations.DRAYNOR.ROUGH_WALL);
    }
    */
    public static Obstacle[] DRAYNOR = new Obstacle[] {
            new Obstacle(null, Locations.DRAYNOR.ROUGH_WALL, "Rough wall"),
            new Obstacle(Locations.DRAYNOR.TIGHTROPE1, null, "Tightrope"),
            new Obstacle(Locations.DRAYNOR.TIGHTROPE2, null, "Tightrope"),
            new Obstacle(Locations.DRAYNOR.NARROW_WALL, null, "Narrow wall"),
            new Obstacle(Locations.DRAYNOR.WALL, null, "Wall"),
            new Obstacle(Locations.DRAYNOR.GAP, null, "Gap"),
            new Obstacle(Locations.DRAYNOR.CRATE, null, "Crate")
    };

    /*
    if (isPlayerAt(Locations.VARROCK.ROUGH_WALL)) {
                return interactWithObstacle("Climb", "Rough wall");
            } else if (isPlayerAt(Locations.VARROCK.CLOTHES_LINE)) {
                return interactWithObstacle("Cross", "Clothes line");
            } else if (isPlayerAt(Locations.VARROCK.GAP1)) {
                return interactWithObstacle("Leap", "Gap");
            } else if (isPlayerAt(Locations.VARROCK.WALL)) {
                return interactWithObstacle("Balance", "wall");
            } else if (isPlayerAt(Locations.VARROCK.GAP2)) {
                return interactWithObstacle("Leap", "Gap");
            } else if (isPlayerAt(Locations.VARROCK.GAP3)) {
                return interactWithObstacle("Leap", "Gap", new Position(3209, 3397, 3));
            } else if (isPlayerAt(Locations.VARROCK.GAP4)) {
                return interactWithObstacle("Leap", "Gap", new Position(3233, 3402, 3));
            } else if (isPlayerAt(Locations.VARROCK.LEDGE)) {
                return interactWithObstacle("Hurdle", "Ledge");
            } else if (isPlayerAt(Locations.VARROCK.EDGE)) {
                return interactWithObstacle("Jump-off", "Edge");
            } else {
                Movement.walkTo(Locations.VARROCK.ROUGH_WALL);
            }
     */

    public static Obstacle[] VARROCK = new Obstacle[] {
            new Obstacle(null, Locations.VARROCK.ROUGH_WALL, "Rough wall"),
            new Obstacle(Locations.VARROCK.CLOTHES_LINE, null, "Clothes line"),
            new Obstacle(Locations.VARROCK.GAP1, null, "Gap"),
            new Obstacle(Locations.VARROCK.WALL, null, "Wall"),
            new Obstacle(Locations.VARROCK.GAP2, null, "Gap"),
            new Obstacle(Locations.VARROCK.GAP3, null, "Gap", new Position(3209, 3397, 3)),
            new Obstacle(Locations.VARROCK.GAP4, null, "Gap", new Position(3233, 3402, 3)),
            new Obstacle(Locations.VARROCK.LEDGE, null, "Ledge"),
            new Obstacle(Locations.VARROCK.EDGE, null, "Edge")
    };
}
