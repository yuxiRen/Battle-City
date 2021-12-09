import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import javax.swing.JFrame;

public class Game extends JFrame {
    MyPanel mp = null;
    public static Scanner scr = new Scanner(System.in);

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        System.out.println("Do you want continue the last game? y or n");
        String choice = scr.next();
        mp = new MyPanel(choice);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300, 950);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.saveRecord();
                System.exit(0);
            }
        });
    }
}
