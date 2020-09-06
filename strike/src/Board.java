import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board extends JPanel implements Runnable {
    private Player player;
    private Thread board;
    private Object monitor;
    private CopyOnWriteArrayList<Enemy> enemies;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.white);
        setFocusable(true);
        monitor = new Object();
        player = new Player(-1, 607, -1, 1031, monitor);
        enemies = new CopyOnWriteArrayList<>();
        enemies.add(new Enemy(1000, 0));
        Enemy.setLowerBorder(610);
        Enemy.setUpperBorder(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(player.getImage(), player.getX(),
                player.getY(), this);
        for (Enemy enemy : enemies) {
            g2d.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            enemy.setVisible(true);
        }
        List<Bullet> bullets = player.getBullets();
        synchronized (bullets) {
            for (Bullet bullet : bullets) {
                g2d.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
        g2d.drawString("Enemies killed: " + player.getKills(), 400, 10);
    }

    public void addNotify() {
        super.addNotify();
        board = new Thread(this);
        board.setDaemon(true);
        board.start();
    }

    @Override
    public void run() {
        while (true) {
            step();
            updateBullets();
            enemyMove();
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            int bulletHeight;
            bullet:
            for (Bullet bullet : playerBullets) {
                if (bullet.isVisible()) {
                    bulletX = bullet.getX();
                    bulletY = bullet.getY();
                    bulletHeight = bullet.getHeight();
                    bulletWidth = bullet.getWidth();
                    Iterator<Enemy> iterator = enemies.iterator();
                    Enemy enemy;
                    while (iterator.hasNext()) {
                        enemy = iterator.next();
                        if (((bulletX + bulletWidth >= enemy.getX()) &&
                                (bulletX + bulletWidth <= enemy.getX() + enemy.getWidth())) &&
                                ((bulletY <= enemy.getY() + enemy.getHeight()) && bulletY >= enemy.getY())) {
                            enemy.setVisible(false);
                            enemies.remove(enemy);
                            enemy = null;
                            playerBullets.remove(bullet);
                            bullet.setVisible(false);
                            bullet = null;
                            player.enemyKilled();
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
            enemies.add(new Enemy(1000, 0));
        } else {
            for (Enemy enemy : enemies) {
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
