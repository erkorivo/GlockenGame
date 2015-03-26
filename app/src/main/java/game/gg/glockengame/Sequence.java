package game.gg.glockengame;

import org.json.JSONObject;

/**
 * Created by erik on 3/22/15.
 */
public class Sequence {
    private int[][] rhythm;
    private String[][] notes;

    public void newSeq(int[][] nums, String[][] notes) {
        this.rhythm = nums;
        this.notes = notes;
    }

    public void setRhythm(int[][] rhythm) {
        this.rhythm = rhythm;
    }

    public void setNotes(String[][] notes) {
        this.notes = notes;
    }
}
