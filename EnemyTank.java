import java.util.Vector;

public class EnemyTank extends Tank {
    Vector<Bullet> bullets = new Vector<>();
    public EnemyTank(int x, int y) {
        super(x, y);
    }

}
