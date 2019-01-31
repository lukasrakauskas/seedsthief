package SeedsTheif.data;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.ui.Log;

public class Obstacle {
    private Area area;
    private Position position;
    private String obstacleName;
    private Position obstaclePosition;

    public Obstacle(Area area, Position position, String obstacleName) {
        this.area = area;
        this.position = position;
        this.obstacleName = obstacleName;
    }

    public Obstacle(Area area, Position position, String obstacleName, Position obstaclePosition) {
        this.area = area;
        this.position = position;
        this.obstacleName = obstacleName;
        this.obstaclePosition = obstaclePosition;
    }

    public boolean validate(Position player) {
        Log.info("Validate " + obstacleName);
        if (area != null) return area.contains(player);
        if (position != null) return player.equals(position);
        return false;
    }

    public void execute() {
        Log.info("Execute " + obstacleName);
        if (obstaclePosition != null) {
            for (SceneObject obj : SceneObjects.getAt(obstaclePosition)) {
                if (obj != null && obj.getName().equalsIgnoreCase(obstacleName)) {
                    obj.click();
                } else {
                    Movement.walkTo(obstaclePosition);
                }
            }
        } else {
            SceneObject object = SceneObjects.getNearest(obstacleName);
            if (object != null) {
                object.click();
            }
        }
    }
}
