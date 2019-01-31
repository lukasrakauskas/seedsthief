package SeedsTheif.tasks;

import SeedsTheif.data.Items;
import SeedsTheif.data.Store;
import SeedsTheif.tasks.thieving.*;
import SeedsTheif.tasks.thieving.victims.Man;
import SeedsTheif.tasks.thieving.victims.MasterFarmer;
import SeedsTheif.tasks.thieving.victims.SilkStall;
import SeedsTheif.tasks.thieving.victims.TeaStall;
import SeedsTheif.utils.Utils;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.ChatMessageListener;
import org.rspeer.runetek.event.types.ChatMessageEvent;
import org.rspeer.script.task.Task;

import java.util.Arrays;

public class TrainThieving extends Task implements ChatMessageListener {

    private final Task[] tasks = {
            new EatFood(),
            new RunFromCombat(),
            new Drop(),
            new SleepStun(),
            new Banking(),
            new OpenPouch(),
            new EquipDodgyNecklace(),
            new MasterFarmer(),
            new SilkStall(),
            new TeaStall(),
            new Man()
    };

    @Override
    public boolean validate() {
        return Skills.getLevel(Skill.AGILITY) >= 50 && Skills.getLevel(Skill.THIEVING) < 50;
    }

    @Override
    public int execute() {
        Store.setAction("Train Thieving");

        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 300;
    }


    @Override
    public void notify(ChatMessageEvent chatMessageEvent) {
//        String msg = chatMessageEvent.getMessage();
//        if (msg.contains("ou attempt")) Store.incrementAttempts();
//        if (msg.contains("ou fail")) Store.incrementFails();
//        if (msg.contains("ou pick")) Store.incrementSuccess();
    }
}
