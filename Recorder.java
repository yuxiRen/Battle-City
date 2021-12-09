import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Recorder {
    private static int destroyedEnemyTankNum = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "myRecord.txt";
    private static Vector<EnemyTank> enemyTanks = null;

    public static Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static void saveRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(destroyedEnemyTankNum + "\r\n");
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " "
                            + enemyTank.getDirection();
                    bw.write(record);
                    bw.newLine();
                }
            }
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
