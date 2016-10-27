package com.breku.math.integration.persistance;

import com.badlogic.gdx.Gdx;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by brekol on 16.10.16.
 */
public class TurnDataService {

    private static final String TAG = "TurnDataService";

    private TurnData turnData;
    private Gson gson = new GsonBuilder().create();

    public void clearTurnData() {
        turnData = null;
    }

    public void setTurnData(TurnData turnData) {
        this.turnData = turnData;
    }

    // Creates a new instance of TurnDataService.
    public TurnData unpersist(byte[] byteArray) {
        final String jsonString = getStringFromByteArray(byteArray);
        Gdx.app.debug(TAG, String.format("Unpersisting jsonString=[%s]", jsonString));
        return gson.fromJson(jsonString, TurnData.class);

    }

    // This is the byte array we will write out to the TBMP API.
    public byte[] persist() {
        final String jsonString = gson.toJson(turnData);
        return getBytesFromJsonObject(jsonString);
    }

    public void incrementTurnCounter() {
        turnData.setTurnCounter(turnData.getTurnCounter() + 1);
        turnData.setFirstRound(false);
    }

    public void updateScoreForPlayer(final String participantId, final int score) {
        final Round currentRound = turnData.getCurrentRound();
        final Map<String, Integer> scoreMap = currentRound.getScoreMap();
        scoreMap.put(participantId, score);
    }

    public void updateLevelAndGameType(LevelDifficulty levelDifficulty, GameType gameType) {
        final Round currentRound = turnData.getCurrentRound();
        currentRound.setGameType(gameType);
        currentRound.setLevelDifficulty(levelDifficulty);
    }

    public void updateName(String participantId, String name) {
        final Map<String, String> playersNameMap = turnData.getPlayersNameMap();
        playersNameMap.put(participantId, name);
    }

    private String getStringFromByteArray(byte[] byteArray) {
        try {
            return new String(byteArray, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Gdx.app.error(TAG, "Cannot create string from byteArray", e);
            throw new IllegalStateException(e);
        }
    }

    private byte[] getBytesFromJsonObject(final String jsonString) {
        Gdx.app.debug(TAG, String.format("Persisting jsonString=[%s]", jsonString));
        return jsonString.getBytes(Charset.forName("UTF-8"));
    }
}
