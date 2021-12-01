import java.util.Vector;

public class EnemyTank extends Tank {
    Vector<Bullet> bullets = new Vector<>();
    boolean isLive = true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
