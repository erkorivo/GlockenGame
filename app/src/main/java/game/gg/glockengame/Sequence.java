package game.gg.glockengame;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Updated by erik on 3/29/15.
 */
public class Sequence extends ArrayList {
    private ArrayList<Object> rhythm;
    private ArrayList<String> notes;

    public Sequence(ArrayList<Object> nums, ArrayList<String> notes) {
        this.rhythm = nums;
        this.notes = notes;
    }

    public void setRhythm(ArrayList<Object> rhythm) {
        this.rhythm = rhythm;
    }

    public String getRhythms(){
        return String.valueOf(rhythm);
    }


    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public String getNotes(){
        return String.valueOf(notes);
    }
}
