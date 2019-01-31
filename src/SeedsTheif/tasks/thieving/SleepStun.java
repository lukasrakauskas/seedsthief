package SeedsTheif.tasks.thieving;

import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.script.task.Task;

public class SleepStun extends Task {
    @Override
    public boolean validate() {
        return Utils.isStunned();
    }

    @Override
    public int execute() {
        if (Utils.isStunned()) Time.sleepUntil(() -> !Utils.isStunned(), 500, 5000);
        return 1000;
    }
}
