package SeedsTheif.data;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;

public class Store {
    private static String action;

    private static int attempts = 0;
    private static int fails = 0;
    private static int success = 0;

    private static Npc target;

    private static boolean hasVarrockTab = true;

    private static boolean talkedWithBrian = false;

    private static int crateCount = 0;

    private static boolean suppliesBought = Skills.getLevel(Skill.THIEVING) != 1 && Skills.getLevel(Skill.AGILITY) != 1;

    public static String getAction() {
        return action == null || action.isEmpty() ? "Idle" : action;
    }

    public static void setAction(String action) {
        Store.action = action;
    }

    public static int getFails() {
        return fails;
    }

    public static int getAttempts() {
        return attempts;
    }

    public static int getSuccess() {
        return success;
    }

    public static void incrementAttempts() {
        Store.attempts++;
    }

    public static void incrementFails() {
        Store.fails++;
    }

    public static void incrementSuccess() {
        Store.success++;
    }

    public static Npc getTarget() {
        return target;
    }

    public static void setTarget(Npc target) {
        Store.target = target;
    }

    public static boolean hasVarrockTab() {
        return hasVarrockTab;
    }

    public static void setVarrockTab(boolean hasVarrockTab) {
        Store.hasVarrockTab = hasVarrockTab;
    }

    public static void setTalkedWithBrianOnce(boolean talkedWithBrian) {
        Store.talkedWithBrian = talkedWithBrian;
    }

    public static boolean hasTalkedWithBrianOnce() {
        return talkedWithBrian;
    }

    public static void setCrateCount(int crateCount) {
        Store.crateCount = crateCount;
    }

    public static int getCrateCount() {
        return crateCount;
    }

    public static boolean isSuppliesBought() {
        return suppliesBought;
    }

    public static void setSuppliesBought() {
        Store.suppliesBought = true;
    }
}
