package SeedsTheif.interfaces;

import SeedsTheif.core.SeedsThief;
import SeedsTheif.data.Store;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;

import java.awt.*;

public class PaintRenderer implements RenderListener {

    private SeedsThief main;

    public PaintRenderer(SeedsThief main) {
        this.main = main;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Player me = Players.getLocal();
        Graphics g = renderEvent.getSource();

        if (me.getTargetIndex() != -1)
            me.getTarget().getPosition().outline(g);

        int x = 372;
        int y = 19;

        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(x - 5, y - 15, 150, 100);
        g.setColor(Color.red);

        g.setColor(Color.decode("#B63EA3"));
        g.drawString("Seeds Thief", x, y);
        g.setColor(Color.GREEN);

        g.drawString("Runtime: " + SeedsThief.timer.toElapsedString(), x, y += 11);
        g.drawString("Status: " + Store.getAction(), x, y += 11);
        g.drawString("Crate count: " + Store.getCrateCount(), x, y += 11);
    }
}