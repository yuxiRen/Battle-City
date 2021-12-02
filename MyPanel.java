import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener, Runnable{
    MyTank myTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Bomb> bombs = new Vector<>();
    int enemyNums = 3;
    
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;

    public MyPanel() {
        myTank = new MyTank(100, 100);
        myTank.setSpeed(3);
        for (int i = 0; i < enemyNums; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirection(2);
            Bullet bullet = new Bullet(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            enemyTank.bullets.add(bullet);
            new Thread(bullet).start();
            enemyTanks.add(enemyTank);
        }
        img1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//draw a black panel
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
        //draw bullet
        if (myTank.bullet != null && myTank.bullet.isLive) {
            g.draw3DRect(myTank.bullet.x, myTank.bullet.y, 2, 2, false);
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.life > 6) {
                g.drawImage(img1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(img2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(img3, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(i); 
            }
        }
        for (int i = 0; i < enemyNums; i++) {
            EnemyTank et = enemyTanks.get(i);
            if (et.isLive) {
                drawTank(et.getX(), et.getY(), g, et.getDirection(), 1);
                for (int j = 0; j < et.bullets.size(); j++) {
                    Bullet bullet = et.bullets.get(j);
                    // draw bullet
                    if (bullet.isLive) {
                        g.draw3DRect(bullet.x, bullet.y, 2, 2, false);
                    } else {
                        et.bullets.remove(j);
                    }
                }
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case 0: //Up
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1: //Right
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2: //Down
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3: //Left
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }

    public void hitTank(Bullet bullet, EnemyTank enemyTank) {
        int tankDir = enemyTank.getDirection();
        int x = enemyTank.getX();
        int y = enemyTank.getY();
        if ((tankDir == 0 || tankDir == 2) &&
        (bullet.x > x && bullet.x < x + 40 && bullet.y > y && bullet.y < y + 60)) {
            bullet.isLive = false;
            enemyTank.isLive = false;
            bombs.add(new Bomb(x, y));
        } else if ((tankDir == 1 || tankDir == 3) &&
        (bullet.x > x && bullet.x < x + 60 && bullet.y > y && bullet.y < y + 40)) {
            bullet.isLive = false;
            enemyTank.isLive = false;
            bombs.add(new Bomb(x, y));
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int input = e.getKeyCode();
        if (input == KeyEvent.VK_UP) {
            myTank.setDirection(0);
            myTank.moveUp();
        } else if (input == KeyEvent.VK_RIGHT) {
            myTank.setDirection(1);
            myTank.moveRight();
        } else if (input == KeyEvent.VK_DOWN) {
            myTank.setDirection(2);
            myTank.moveDown();
        } else if (input == KeyEvent.VK_LEFT) {
            myTank.setDirection(3);
            myTank.moveLeft();
        }else if (input == KeyEvent.VK_SPACE) {
            myTank.shot();
        }
        this.repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (myTank.bullet != null && myTank.bullet.isLive) {
                for (EnemyTank et : enemyTanks) {
                    hitTank(myTank.bullet, et);
                }
            }
            this.repaint();
        }
    }
}
