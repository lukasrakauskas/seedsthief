package SeedsTheif.tasks;

import SeedsTheif.data.Store;
import SeedsTheif.tasks.agility.courses.Draynor;
import SeedsTheif.tasks.agility.courses.Gnome;
import SeedsTheif.tasks.agility.courses.Varrock;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.local.Health;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class TrainAgility extends Task {

    private final Task[] tasks = {
            new Varrock(),
            new Draynor(),
            new Gnome()
    };

    @Override
    public boolean validate() {
        return Skills.getLevel(Skill.AGILITY) < 50;
    }

    @Override
    public int execute() {
        Store.setAction("Train agility");

        if (Dialog.canContinue()) Dialog.processContinue();

        if (!Inventory.contains("Jug of wine") || !Inventory.contains(item -> item.getName().contains("Stamina"))) {
            if (Players.getLocal().getFloorLevel() == 0) {
                if (Bank.isOpen()) {
                    if (!Inventory.contains("Jug of wine")) {
                        Bank.withdraw("Jug of wine", 5);
                        Time.sleepUntil(() -> Inventory.contains("Jug of wine"), 500, 2000);
                    }
                    if (!Inventory.contains(item -> item.getName().contains("Stamina"))) {
                        Bank.withdraw("Stamina potion(4)", 5);
                        Time.sleepUntil(() -> Inventory.contains(item -> item.getName().contains("Stamina")), 500, 2000);
                    }
                } else {
                    Bank.open();
                }
                return 1000;
            }
        }

        if (Health.getCurrent() <= 5) {
            Item wine = Inventory.getFirst("Jug of wine");
            if (wine != null) {
                wine.interact("Drink");
                return 600;
            }
        }

        if (Inventory.contains("Vial", "Jug")) {
            Item trash = Inventory.getFirst("Vial", "Jug");
            if (trash != null) {
                trash.interact("Drop");
                return 300;
            }
        }

        if (Movement.isStaminaEnhancementActive() || Movement.getRunEnergy() >= 20) {
            if (!Movement.isRunEnabled()) {
                Movement.toggleRun(true);
                return 1000;
            }
        }

        if (!Movement.isStaminaEnhancementActive() && Movement.getRunEnergy() < 20) {
            Item stamina = Inventory.getFirst(item -> item.getName().contains("Stamina"));
            if (stamina != null) {
                stamina.interact("Drink");
                return 1000;
            }
        }

        Pickable mark = Pickables.getNearest("Mark of grace");
        if (mark != null && Movement.isInteractable(mark)) {
            mark.click();
            return 1000;
        }

        for (Task task : tasks) {
            if (task.validate()) {
                return task.execute();
            }
        }

        return 800;
    }
}
