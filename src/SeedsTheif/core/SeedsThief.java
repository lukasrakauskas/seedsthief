package SeedsTheif.core;

import SeedsTheif.interfaces.PaintRenderer;
import SeedsTheif.tasks.*;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.event.EventDispatcher;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ScriptMeta(developer = "f0kkus", desc = "Master farmer thief", name = "SeedsThief")
public class SeedsThief extends TaskScript {

    public static StopWatch timer;
    private EventDispatcher eventDispatcher;
    private PaintRenderer paintRenderer;

    private final Task[] tasks = {
            new Starting(),
//            new Muling(),
            new FarmSeeds(),
            new RoguesDen(),
            new TrainAgility(),
            new TrainThieving()
    };

    @Override
    public void onStart() {
        timer = StopWatch.start();
        eventDispatcher = Game.getEventDispatcher();
        eventDispatcher.register(paintRenderer = new PaintRenderer(this));
        Log.fine("Started Seeds Thief");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(Utils::sendStatusUpdate, 0, 5, TimeUnit.SECONDS);
    }

    @Override
    public int loop() {
        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 400;
    }

    @Override
    public void onStop() {
        eventDispatcher.deregister(paintRenderer);
    }
}
