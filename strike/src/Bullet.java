public class Bullet extends Sprite {
    private static final int BOARD_WIDTH = 1150;
    private static final int BULLET_SPEED = 15;

    public Bullet(int x, int y) {
        super(x, y);
        loadImage("resources/bullet.png", 30, 15);
        getImageDimensions();
    }

    public void move() {
        x += BULLET_SPEED;
        if (x > BOARD_WIDTH) {
            visible = false;
        }
    }
}
