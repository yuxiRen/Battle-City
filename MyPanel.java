import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
    MyTank hero = null;

    public MyPanel() {
        hero = new MyTank(100, 100);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(hero.getX(), hero.getY(), g, 0, 0);
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
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
            default:
                System.out.println("wrong direction");
        }
    }
}
