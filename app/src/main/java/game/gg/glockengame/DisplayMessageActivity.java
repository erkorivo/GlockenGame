package game.gg.glockengame;

import android.content.res.AssetManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class DisplayMessageActivity extends ActionBarActivity {

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
        try {
            input = assetManager.open("sequences/" + message + ".json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            //byte buffer into a string;
            String json = new String(buffer);
            TextView jsonView = new TextView(this);
            jsonView.setTextSize(14);
            jsonView.setText(json);
            setContentView(jsonView);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // begin sequence deserialization
//        JSONObject jObj = null;
//        jObj = new JSONObject();


//        String str = jObj.toString();
//        textView.setTextSize(14);
//        textView.setText(str);
//        setContentView(textView);

        Sequence sequence = new Sequence();
        /*
        try {
        sequence.setRhythm(jObj.getJSONObject("rhythm"));
        } catch (JSONException e) {
        e.printStackTrace();
        }
        try {
        sequence.setNotes(jObj.getJSONObject("notes"));
        } catch (JSONException e) {
        e.printStackTrace();
        }
        */


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
