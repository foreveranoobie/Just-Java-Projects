import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board extends JPanel implements Runnable {
    private Player player;
    private Thread board;
    private Object monitor;
    private CopyOnWriteArrayList<Enemy> enemies;
    private boolean hasLost = false;
    private int enemiesCount = 1;

    public Board() {
        initBoard();
        //SoundUtil.initSound();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);
        monitor = new Object();
        player = new Player(-1, 607, -1, 1031, monitor);
        enemies = new CopyOnWriteArrayList<>();
        enemies.add(new Enemy(1000, 0, 1));
        Enemy.setLowerBorder(610);
        Enemy.setUpperBorder(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
        if (hasLost) {
            try {
                System.in.read();
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (hasLost) {
            board.interrupt();
            g2d.drawString("You lost! Press enter to exit", 300, 300);
            return;
        }
        g2d.drawImage(player.getImage(), player.getX(),
                player.getY(), this);
        synchronized (enemies) {
            for (Enemy enemy : enemies) {
                g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
                enemy.setVisible(true);
            }
        }
        List<Bullet> bullets = player.getBullets();
        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
        g2d.drawString("Enemies killed: " + player.getKills(), 400, 10);
        g2d.drawString("| Magazine: " + player.getShots(), 600, 10);
    }

    public void addNotify() {
        super.addNotify();
        board = new Thread(this);
        board.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                step();
                updateBullets();
                enemyMove();
                repaint();
                Thread.sleep(40);
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    private void step() {
        player.move();
        repaint(player.getX() - 1, player.getY() - 1,
                player.getWidth() + 2, player.getHeight() + 2);
    }

    private void updateBullets() {
        List<Bullet> playerBullets = player.getBullets();
        synchronized (playerBullets) {
            int bulletX;
            int bulletWidth;
            int bulletY;
            bullet:
            for (Bullet bullet : playerBullets) {
                if (bullet.isVisible()) {
                    bulletX = bullet.getX();
                    bulletY = bullet.getY();
                    bulletWidth = bullet.getWidth();
                    Iterator<Enemy> iterator = enemies.iterator();
                    Enemy enemy;
                    while (iterator.hasNext()) {
                        enemy = iterator.next();
                        if (((bulletX + bulletWidth >= enemy.getX()) &&
                                (bulletX + bulletWidth <= enemy.getX() + enemy.getWidth())) &&
                                ((bulletY <= enemy.getY() + enemy.getHeight()) && bulletY >= enemy.getY())) {
                            enemy.shoot();
                            if (!enemy.isVisible()) {
                                enemies.remove(enemy);
                                enemy = null;
                                player.enemyKilled();
                            }
                            playerBullets.remove(bullet);
                            bullet.setVisible(false);
                            bullet = null;
                            continue bullet;
                        }
                    }
                    bullet.move();
                } else {
                    playerBullets.remove(bullet);
                }
            }
        }
    }

    private void enemyMove() {
        if (enemies.isEmpty()) {
            if (player.getKills() % 5 == 0) {
                Enemy.incrementSpeed();
                Enemy.increaseMaxHealth();
                System.out.println("Max health increased to " + Enemy.getMaxHealth());
            }
            if (player.getKills() % 7 == 0) {
                Enemy.increaseDx();
                enemiesCount++;
            }
            synchronized (enemies) {
                for (int num = 0; num < enemiesCount; num++) {
                    if (num % 2 == 0) {
                        enemies.add(new Enemy(1000, num * 90, 1));
                    } else {
                        enemies.add(new Enemy(1000, num * 90, -1));
                    }
                }
            }
        } else {
            for (Enemy enemy : enemies) {
                if (enemy.isCrossed()) {
                    hasLost = true;
                    return;
                }
                enemy.move();
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
