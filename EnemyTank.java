import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    Vector<Bullet> bullets = new Vector<>();
    boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }
    @Override
    public void run() {
        while (true) {
            if (isLive && bullets.size() == 0) {
                Bullet bullet = null;
                switch (getDirection()) {
                    case 0:
                        bullet = new Bullet(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        bullet = new Bullet(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        bullet = new Bullet(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        bullet = new Bullet(getX(), getY() + 20, 3);
                        break;
                }
                bullets.add(bullet);
                new Thread(bullet).start();
            }
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            
            //randomly change direction
            setDirection((int) (Math.random() * 4));
            if (!isLive) {
                break;
            }
        }
    }
}
