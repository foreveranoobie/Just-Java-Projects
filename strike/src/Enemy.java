public class Enemy extends Sprite {
    private static int upperBorder;
    private static int lowerBorder;
    private static final int ENEMY_VERT_SPEED = 5;
    private int direction = 1;
    private int dx;

    public Enemy(int x, int y) {
        super(x, y);
        dx = 0;
        loadImage("resources/enemy.png", 180, 100);
        getImageDimensions();
    }

    public void move() {
        if (isVisible()) {
            if (y + ENEMY_VERT_SPEED > lowerBorder) {
                direction = -1;
                dx = -2;
            } else if (y + ENEMY_VERT_SPEED < upperBorder) {
                direction = 1;
                dx = -2;
            } else {
                dx = 0;
            }
            y += (ENEMY_VERT_SPEED * direction);
            x += dx;
        }
    }

    public static void setUpperBorder(int upperBorder) {
        Enemy.upperBorder = upperBorder;
    }

    public static void setLowerBorder(int lowerBorder) {
        Enemy.lowerBorder = lowerBorder;
    }
}
