package SeedsTheif.utils;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.GrandExchange;
import org.rspeer.runetek.api.component.GrandExchangeSetup;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.runetek.providers.RSGrandExchangeOffer;
import org.rspeer.ui.Log;

import java.util.Arrays;

public class GE {
    public static boolean buy(String item, int price, int quantity){
        Log.fine(item);

        if (Inventory.contains(item)) {
            return true;
        }

        if (GrandExchange.isOpen()) {
            RSGrandExchangeOffer offer = GrandExchange.getFirst(o -> o.getItemDefinition().getName().equals(item));

            if (offer != null) {
                if (offer.getProgress() == RSGrandExchangeOffer.Progress.FINISHED) {
                    GrandExchange.collectAll();
                } else {
                    Time.sleepUntil(() -> offer.getProgress() == RSGrandExchangeOffer.Progress.FINISHED, 500, 2000);
                }
            } else {
                if (GrandExchangeSetup.isOpen()) {
                    if (GrandExchangeSetup.getItem() == null || !GrandExchangeSetup.getItem().getName().equals(item)) {
                        GrandExchangeSetup.setItem(item);
                        Time.sleepUntil(() -> GrandExchangeSetup.getItem() != null, 500, 2000);
                    } else {
                        if (GrandExchangeSetup.getPricePerItem() != price) {
                            GrandExchangeSetup.setPrice(price);
                            Time.sleepUntil(() -> GrandExchangeSetup.getPricePerItem() == price, 500, 2000);
                        }

                        if (GrandExchangeSetup.getQuantity() != quantity) {
                            GrandExchangeSetup.setQuantity(quantity);
                            Time.sleepUntil(() -> GrandExchangeSetup.getQuantity() == quantity, 500, 2000);
                        }

                        if (GrandExchangeSetup.getPricePerItem() == price && GrandExchangeSetup.getQuantity() == quantity) {
                            GrandExchangeSetup.confirm();
                            Time.sleepUntil(() -> !GrandExchangeSetup.isOpen(), 500, 2000);
                        }
                    }
                } else {
                    GrandExchange.open(GrandExchange.View.BUY_OFFER);
                }
            }

        } else {
            SceneObject booth = SceneObjects.getNearest(10061);
            if (booth != null) {
                booth.interact("Exchange");
            }
        }

        return false;
    }

    public static boolean buy(ItemBuyData data){
        return GE.buy(data.getName(), data.getPrice(), data.getCount());
    }

    public static boolean buyItems(ItemBuyData[] data) {
        return Arrays.stream(data).allMatch(GE::buy);
    }
}
