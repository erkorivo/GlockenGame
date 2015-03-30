package game.gg.glockengame;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class DisplayMessageActivity extends ActionBarActivity {

    private ArrayList<Sequence> sequences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        setContentView(textView);

        // get sequence (and display, for testing)
        AssetManager assetManager = getAssets();
        InputStream input;
        String json = "";
        try {
            input = assetManager.open("sequences/" + message + ".json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            json = new String(buffer);  //byte buffer into a string
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView jsonView = new TextView(this);
//        // raw JSON view
//        jsonView.setTextSize(14);
//        jsonView.setText(json);
//        setContentView(jsonView);

        sequences = new ArrayList();

        // begin sequence deserialization
        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jSequence = jObject.getJSONArray("sequence");
            String len = "Stringified JSON array:\n";
            // sequentially get JSON data
            Log.d("begin array deserial.", "beginning first loop");
            for (int i = 0; i < jSequence.length(); i++) {
                JSONObject jRealObject = jSequence.getJSONObject(i);
                JSONArray jRhythmArray = jRealObject.getJSONArray("rhythm");
                JSONArray jNotesArray = jRealObject.getJSONArray("notes");
                len +="\trhythms " + i + ": " + jRhythmArray + "\n";
                len +="\tnotes " + i + ": " + jNotesArray + "\n";

                //parse each array for individual lists
                ArrayList<Object> rhythms = new ArrayList<Object>();
                ArrayList<String> notes = new ArrayList<String>();
                if (jRhythmArray != null) {
                    for (int j=0;j<jRhythmArray.length();j++){
                        rhythms.add(jRhythmArray.get(j).toString());
                    }
                }
                if (jNotesArray != null) {
                    for (int j=0;j<jNotesArray.length();j++){
                        notes.add(jNotesArray.get(j).toString());
                    }
                }
                Sequence seq = new Sequence(rhythms, notes);
                sequences.add(seq);
            }

            len += "\n\tdone entering info. Check our sequences\n";

            // print all sequence data for debugging
            for (Sequence s : sequences) {
                len += "\t\trhythms: " + s.getRhythms() + "\n";
                len += "\t\tnotes: " + s.getNotes() + "\n\n";
            }

            jsonView.setTextSize(14);
            jsonView.setText(len);
            setContentView(jsonView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
