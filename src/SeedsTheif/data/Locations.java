package SeedsTheif.data;

import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;

import java.util.ArrayList;
import java.util.Arrays;

public class Locations {
    public static class GNOME {
        public static final Area STRONGHOLD = Area.polygonal(
                new Position(2456, 3389, 0),
                new Position(2457, 3386, 0),
                new Position(2459, 3384, 0),
                new Position(2464, 3384, 0),
                new Position(2465, 3385, 0),
                new Position(2466, 3389, 0),
                new Position(2468, 3391, 0),
                new Position(2492, 3416, 0),
                new Position(2492, 3440, 0),
                new Position(2483, 3443, 0),
                new Position(2468, 3443, 0),
                new Position(2440, 3442, 0),
                new Position(2433, 3413, 0),
                new Position(2454, 3391, 0),
                new Position(2456, 3389, 0));
        public static final Area COURSE = Area.rectangular(2469, 3440, 2490, 3414);
        public static final Area OUTPOST_TO_COURSE = Area.rectangular(2429, 3338, 2491, 3441);
        public static final Position LOG_BALANCE = new Position(2474, 3436, 0);
        public static final Position OBSTACLE_NET1 = new Position(2474, 3429, 0);
        public static final Position TREE_BRANCH1 = new Position(2473, 3423, 1);
        public static final Position BALANCING_ROPE = new Position(2473, 3420, 2);
        public static final Position TREE_BRANCH2 = new Position(2483, 3420, 2);
        public static final Position OBSTACLE_NET2 = new Position(2487, 3420, 0);
        public static final Position OBSTACLE_PIPE = new Position(2487, 3428, 0);

    }
    public static class DRAYNOR {
        public static final Area COURSE = Area.rectangular(3106, 3245, 3087, 3283);
        public static final Position ROUGH_WALL = new Position(3103, 3279, 0);
        public static final Area TIGHTROPE1 = Area.rectangular(3102, 3277, 3097, 3281, 3);
        public static final Area TIGHTROPE2 = Area.polygonal(
            new Position(3091, 3277, 3),
            new Position(3093, 3275, 3),
            new Position(3090, 3272, 3),
            new Position(3087, 3274, 3),
            new Position(3089, 3276, 3)
        );
        public static final Area NARROW_WALL = Area.rectangular(3089, 3265, 3094, 3268, 3);
        public static final Area WALL = Area.rectangular(3088, 3261, 3087, 3257, 3);
        public static final Area GAP = Area.rectangular(3087, 3255, 3094, 3253, 3);
        public static final Area CRATE = Area.rectangular(3096, 3256, 3101, 3261, 3);
    }
    public static class VARROCK {
        /*
        Climb : Rough wall : 3221 3414 0
        Cross : Clothes line : 3219 3414 3
        Leap : Gap : 3208 3414 3
        Balance : Wall : 3197 3416 1
        Leap : Gap : 3192 3406 3
        Leap : Gap : 3193 3398 3
        Leap : Gap : 3218 3399 3
        Hurdle : Ledge : 3236 3403 3
        Jump-off : Edge : 3236 3410 3
         */
        public static final Area COURSE = Area.rectangular(3242, 3421, 3189, 3392);
        public static final Position ROUGH_WALL = new Position(3221, 3414, 0);
        public static final Area CLOTHES_LINE = Area.rectangular(3214, 3410, 3219, 3419, 3);
        public static final Area GAP1 = Area.rectangular(3208, 3413,3201, 3417, 3);
        public static final Area WALL = Area.rectangular(3197, 3416, 3193, 3416, 1);
        public static final Area GAP2 = Area.rectangular(3192, 3406, 3198, 3402, 3);
        public static final Area GAP3 = Area.polygonal(
                new Position(3182, 3382, 3),
                new Position(3182, 3399, 3),
                new Position(3201, 3399, 3),
                new Position(3202, 3404, 3),
                new Position(3208, 3404, 3),
                new Position(3209, 3395, 3),
                new Position(3201, 3394, 3),
                new Position(3195, 3387, 3),
                new Position(3189, 3381, 3)
        );
        public static final Area GAP4 = Area.rectangular(3218, 3393, 3232, 3403, 3);
        public static final Area LEDGE = Area.rectangular(3236, 3408, 3240, 3403, 3);
        public static final Area EDGE = Area.rectangular(3236, 3415, 3240, 3410, 3);
    }

    public static Area BURTHORPE = Area.rectangular(2878, 3558, 2918, 3533);
}
