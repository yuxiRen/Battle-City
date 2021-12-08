import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Recorder {
    private static int destroyedEnemyTankNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "myRecord.txt";

    public static void saveRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(destroyedEnemyTankNum + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
