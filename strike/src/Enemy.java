public class Enemy extends Sprite {
    private static int upperBorder;
    private static int lowerBorder;
    private static int vertSpeed = 5;
    //private static final int ENEMY_VERT_SPEED = 700;
    private int direction = 1;
    private static int dx = 2;
    private boolean isCrossed = false;
    private static int maxHealth = 10;
    private int currentHealth;

    public Enemy(int x, int y, int direction) {
        super(x, y);
        currentHealth = maxHealth;
        loadImage("resources/enemy.png", 180, 100);
        getImageDimensions();
        this.direction = direction;
    }

    public void move() {
        if (isVisible()) {
            if (y + vertSpeed > lowerBorder) {
                direction = -1;
                x = x - dx;
            } else if (y + vertSpeed < upperBorder) {
                direction = 1;
                x = x - dx;
            }
            y += (vertSpeed * direction);
            if (x <= 0) {
                isCrossed = true;
            }
        }
    }

    public boolean isCrossed() {
        return isCrossed;
    }

    public static void incrementSpeed() {
        vertSpeed = vertSpeed + 1;
    }

    public static void increaseMaxHealth() {
        maxHealth = maxHealth + 5;
    }

    public static int getMaxHealth() {
        return maxHealth;
    }

    public void shoot() {
        currentHealth = currentHealth - 10;
        System.out.printf("Enemy got damaged from %d to %d\n", currentHealth + 10, currentHealth);
        if (currentHealth <= 0) {
            setVisible(false);
        }
    }

    public static void increaseDx() {
        dx = dx + 1;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public static void setUpperBorder(int upperBorder) {
        Enemy.upperBorder = upperBorder;
    }

    public static void setLowerBorder(int lowerBorder) {
        Enemy.lowerBorder = lowerBorder;
    }
}
