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
        g.fillRect(0, 0, 1000, 750  );
    }
}
