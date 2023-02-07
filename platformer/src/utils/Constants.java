package utils;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class PlayerConstants{



        public static final int IDLE = 0;
        public static final int IDLE_1 = 1;
        public static final int WALK = 2;
        public static final int RUNNING = 3;
        public static final int CROUCH = 4;
        public static final int JUMP = 5;
        public static final int ABILITY = 6;
        public static final int DEATH = 7;
        public static final int ATTACK = 8;


        // used to avoid OOB exceptions
        public static int GetSpriteAmount(int player_action){
            switch(player_action){
                case IDLE:
                case IDLE_1:
                    return 2;
                case ABILITY:
                    return 3;
                case WALK:
                    return 4;
                case CROUCH:
                    return 6;
                case RUNNING:
                case JUMP:
                case DEATH:
                case ATTACK:
                    return 8;
                default:
                    return 1;
            }
        }

    }
}
