package SI;

public interface Features {

    /* ---------------------------------------- Required Variable ------------------------------------------- */
    public static final int ROWS_OF_INVADERS = 5;
    public static final int COLS_OF_INVADERS = 10;
    public static final int RATE_OF_INVADERS = 1; // will drop down every x seconds
    public static final int INV_RATE_OF_FIRE = 10;  // one invader will shoot randomly every x seconds
    public static final int DEFENDER_LIVES   = 3;

    /* ------------------------------------------ Other Variables -------------------------------------------- */

    public static final int BARRIER_SPACING   = 20; // x/100 away from the middle
    public static final int BARRIER_WIDTH     = 32; // pixel size of barrier
    public static final int BARRIER_CUSHION   = 128; // cushion between defender and barriers of x pixels
    public static final int SPEED_OF_DEFENDER = 10;
    public static final int INVADER_START_X   = 10; // x/100 away from the middle
    public static final int INVADER_START_Y   = 38; // x/100 away from the middle
    public static final int INVADER_Y_POS_PLS = 65; // middle of invaders should be x/ 100 up the screen
    public static final int X_MOVE_INVADERS   = 10;
    public static final int Y_MOVE_INVADERS   = 40;
    public static final int SPEED_OF_SHOT     = 10;
    public static final int BOMB_DROP_RATE    = 5;
    public static final int SCORE_FONT_SIZE   = 20;
    public static final int GAMEOVER_FONT_SIZE= 48;
}
