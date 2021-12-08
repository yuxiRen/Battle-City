import java.io.BufferedWriter;
import java.io.FileWriter;

public class Recorder {
    private static int destroyedEnemyTankNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "myRecord.txt";

    public static void adddestroyedEnemyTankNum() {
        destroyedEnemyTankNum++;
    }

    public static int getDestroyedEnemyTankNum() {
        return destroyedEnemyTankNum;
    }

    public static void setDestroyedEnemyTankNum(int destroyedEnemyTankNum) {
        Recorder.destroyedEnemyTankNum = destroyedEnemyTankNum;
    }
}
