package Terrarius.Viewer.ItemShop;

import static Terrarius.Utils.GameConstants.*;

public class ItemShopViewerConstants {

    public static int SIDEBAR_X = 0;
    public static int SIDEBAR_Y = 0;

    public static int SIDEBAR_OFFSET = 0;

    public static int SIDEBAR_HEIGHT = SCREEN_HEIGHT;
    public static int SIDEBAR_WIDTH = 25;

    public static int TITLE_X = SIDEBAR_WIDTH + 1;
    public static int TITLE_Y = 0;

    public static int TITLE_HEIGHT = 10;
    public static int TITLE_WIDTH = SCREEN_WIDTH - SIDEBAR_WIDTH - 1;

    public static int LISTINGS_X = SIDEBAR_WIDTH + 1;
    public static int LISTINGS_Y = TITLE_Y + TITLE_HEIGHT;

    public static int LISTINGS_HEIGHT = SCREEN_HEIGHT - TITLE_HEIGHT;
    public static int LISTINGS_WIDTH = SCREEN_WIDTH - SIDEBAR_WIDTH - 1;

    public static int MAX_LISTINGS_PER_LINE = 4;
    public static int MAX_LISTINGS_PER_ROW = 4;
    public static int MAX_LISTINGS_PER_PAGE = MAX_LISTINGS_PER_LINE * MAX_LISTINGS_PER_ROW; //16



}
