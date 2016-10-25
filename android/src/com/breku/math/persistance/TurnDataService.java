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
public class TurnDataService {

    private static final String TAG = "TurnDataService";

    private TurnData turnData;

    public void clearTurnData() {
        turnData = null;
    }

    public void setTurnData(TurnData turnData) {
        this.turnData = turnData;
    }

    // Creates a new instance of TurnDataService.
    public TurnData unpersist(byte[] byteArray) {

        final String jsonString = getStringFromByteArray(byteArray);
        Log.d(TAG, String.format("Unpersisting jsonString=[%s]", jsonString));

        TurnData result = new TurnData();

        try {
            JSONObject obj = new JSONObject(jsonString);

            if (obj.has("data")) {
                result.setData(obj.getString("data"));
            }
            if (obj.has("turnCounter")) {
                result.setTurnCounter(obj.getInt("turnCounter"));
            }
            if (obj.has("levelDifficulty")) {
                final int levelDifficultyOrdinal = obj.getInt("levelDifficulty");
                result.setLevelDifficulty(LevelDifficulty.values()[levelDifficultyOrdinal]);
            }
            if (obj.has("gameType")) {
                final int gameTypeOrdinal = obj.getInt("gameType");
                result.setGameType(GameType.values()[gameTypeOrdinal]);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Error during getting data from json. " + e.getMessage(), e);

        }

        return result;
    }

    public void incrementTurnCounter() {
        turnData.setTurnCounter(turnData.getTurnCounter() + 1);
    }

    // This is the byte array we will write out to the TBMP API.
    public byte[] persist() {
        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("data", turnData.getData());
            jsonObject.put("turnCounter", turnData.getTurnCounter());
            jsonObject.put("levelDifficulty", turnData.getLevelDifficulty().ordinal());
            jsonObject.put("gameType", turnData.getGameType().ordinal());

        } catch (JSONException e) {
            Log.e(TAG, "Error during persisting turn data. " + e.getMessage(), e);
        }
        return getBytesFromJsonObject(jsonObject);
    }

    private String getStringFromByteArray(byte[] byteArray) {
        try {
            return new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Cannot create string from byteArray", e);
            throw new IllegalStateException(e);
        }
    }

    private byte[] getBytesFromJsonObject(JSONObject jsonObject) {
        final String jsonString = jsonObject.toString();
        Log.d(TAG, String.format("Persisting jsonString=[%s]", jsonString));
        return jsonString.getBytes(Charset.forName("UTF-8"));
    }
}
