package SeedsTheif.tasks.starting;

import SeedsTheif.data.Store;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.Worlds;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.WorldHopper;
import org.rspeer.runetek.providers.RSWorld;
import org.rspeer.script.task.Task;

public class HopToMembers extends Task {
    @Override
    public boolean validate() {
        if (Game.isLoggedIn()) {
            RSWorld current = Worlds.get(Game.getClient().getCurrentWorld());
            if (current != null) {
                return !current.isMembers();
            } else {
                Game.getClient().loadWorlds();
            }
        }
        return false;
    }

    @Override
    public int execute() {
        Store.setAction("Hop to members world");
        if (WorldHopper.isOpen()) {
            WorldHopper.randomHop(
                    world -> world.getId() != Game.getClient().getCurrentWorld() &&
                            world.isMembers() &&
                            !world.isSkillTotal() &&
                            !world.isHighRisk() &&
                            !world.isPVP()
            );
            Time.sleepUntil(() -> Worlds.get(Game.getClient().getCurrentWorld()).isMembers(), 500, 4000);
        } else {
            WorldHopper.open();
            Time.sleepUntil(WorldHopper::isOpen, 500, 3000);
        }
        return 1000;
    }
}
