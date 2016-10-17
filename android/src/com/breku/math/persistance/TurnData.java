package com.breku.math.persistance;

import android.util.Log;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by brekol on 16.10.16.
 */
public class TurnData {

    private static final String TAG = "TurnData";
    public String data = "";
    public int turnCounter;
    public LevelDifficulty levelDifficulty;
    public GameType gameType;

    // Creates a new instance of TurnData.
    static public TurnData unpersist(byte[] byteArray) {

        if (byteArray == null) {
            Log.d(TAG, "Empty array---possible bug.");
            return new TurnData();
        }

        String st = null;
        try {
            st = new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            return null;
        }

        Log.d(TAG, "====UNPERSIST \n" + st);

        TurnData retVal = new TurnData();

        try {
            JSONObject obj = new JSONObject(st);

            if (obj.has("data")) {
                retVal.data = obj.getString("data");
            }
            if (obj.has("turnCounter")) {
                retVal.turnCounter = obj.getInt("turnCounter");
            }
            if (obj.has("levelDifficulty")) {
                final int levelDifficultyOrdinal = obj.getInt("levelDifficulty");
                retVal.levelDifficulty = LevelDifficulty.values()[levelDifficultyOrdinal];

            }
            if (obj.has("gameType")) {
                final int gameTypeOrdinal = obj.getInt("gameType");
                retVal.gameType = GameType.values()[gameTypeOrdinal];
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error during getting data from json. " + e.getMessage(), e);

        }

        return retVal;
    }

    // This is the byte array we will write out to the TBMP API.
    public byte[] persist() {
        JSONObject retVal = new JSONObject();

        try {
            retVal.put("data", data);
            retVal.put("turnCounter", turnCounter);
            retVal.put("levelDifficulty", levelDifficulty.ordinal());
            retVal.put("gameType", gameType.ordinal());

        } catch (JSONException e) {
            Log.e(TAG, "Error during persisting turn data. " + e.getMessage(), e);
        }

        String st = retVal.toString();

        Log.d(TAG, "==== PERSISTING\n" + st);

        return st.getBytes(Charset.forName("UTF-8"));
    }
}
