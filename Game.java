import javax.swing.JFrame;

public class Game extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
