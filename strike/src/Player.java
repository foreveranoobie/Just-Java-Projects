import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {
    private int x;
    private int y;
    private int w;
    private int h;
    private Image image;
    private int dx;
    private int dy;
    private int windowUpperBorder;
    private int windowBottomBorder;
    private int windowRightBorder;
    private int windowLeftBorder;
    private List<Bullet> bullets;
    private Object monitor;
    private int kills;

    public Player(int windowUpperBorder, int windowBottomBorder, int windowLeftBorder, int windowRightBorder,
                  Object monitor) {
        this.windowUpperBorder = windowUpperBorder;
        this.windowBottomBorder = windowBottomBorder;
        this.windowLeftBorder = windowLeftBorder;
        this.windowRightBorder = windowRightBorder;
        kills = 0;
        bullets = new CopyOnWriteArrayList<>();
        this.monitor = monitor;
        loadImage();
    }

    private void loadImage() {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File("resources/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = bufferedImage.getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    private void fire() {
        synchronized (bullets) {
            bullets.add(new Bullet(x + w, y + h / 6));
        }
    }

    public void move() {
        if (x + dx < windowRightBorder && x + dx > windowLeftBorder) {
            x += dx;
        }
        if (y + dy > windowUpperBorder && y + dy < windowBottomBorder) {
            y += dy;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return w;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int h) {
        this.h = h;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void enemyKilled() {
        kills++;
    }

    public int getKills() {
        return kills;
    }
}
