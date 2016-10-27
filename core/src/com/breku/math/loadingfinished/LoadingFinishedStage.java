package com.breku.math.loadingfinished;

import com.breku.math.configuration.ContextConstants;
import com.breku.math.game.level.GameType;
import com.breku.math.game.level.LevelDifficulty;
import com.breku.math.integration.GoogleApiService;
import com.breku.math.integration.persistance.TurnData;
import com.breku.math.screen.ScreenType;
import com.breku.math.screen.manager.AssetManagerWrapper;
import com.breku.math.stage.AbstractStage;

import java.util.*;

/**
 * Created by brekol on 25.10.16.
 */
public class LoadingFinishedStage extends AbstractStage {


    private Map<String, Object> additionalDataCopy;
    private List<PlayerStats> playerStatsList = new ArrayList<>();
    private List<LevelAndGameTypeStats> levelAndGameTypeList = new ArrayList<>();

    public LoadingFinishedStage(GoogleApiService googleApiService, AssetManagerWrapper assetManagerWrapper) {
        super(googleApiService, assetManagerWrapper);
    }

    @Override
    public void initialize() {
        super.initialize();
        playerStatsList.clear();
        levelAndGameTypeList.clear();
        additionalDataCopy = new HashMap<>(getAdditionalData());
        final TurnData turnData = (TurnData) additionalDataCopy.get(ContextConstants.ADDITIONAL_DATA_TURN_DATA);

        final Map<String, String> playersNameMap = turnData.getPlayersNameMap();
        final Set<String> participantIds = playersNameMap.keySet();

        for (final String participantId : participantIds) {
            PlayerStats playerStats = new PlayerStats();
            final String playerName = playersNameMap.get(participantId);
            playerStats.setName(playerName);
            playerStats.setParticipantId(participantId);
            playerStats.setScoreList(turnData.getScoreFor(participantId));
            playerStatsList.add(playerStats);
        }
        if (playerStatsList.size() < 2) {
            playerStatsList.add(new PlayerStats());
        }
        levelAndGameTypeList = turnData.getLevelAndGameTypeStats();

    }

    @Override
    public void draw() {
        super.draw();

        getBatch().begin();
        drawHeaderAndScores();
        drawLevelAndGameTypes();

        getBatch().end();

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        setAdditionalData(additionalDataCopy);
        if (additionalDataCopy.get(ContextConstants.ADDITIONAL_DATA_SET_GAME_TYPE) != null &&
                (boolean) additionalDataCopy.get(ContextConstants.ADDITIONAL_DATA_SET_GAME_TYPE)) {
            setTargetScreenType(ScreenType.GAME_TYPE);
        } else {
            setTargetScreenType(ScreenType.GAME);
        }
        return true;
    }

    private void drawLevelAndGameTypes() {
        for (int i = 0; i < levelAndGameTypeList.size(); i++) {
            final LevelAndGameTypeStats levelAndGameTypeStats = levelAndGameTypeList.get(i);
            final LevelDifficulty levelDifficulty = levelAndGameTypeStats.getLevelDifficulty();
            final GameType gameType = levelAndGameTypeStats.getGameType();
            font.draw(getBatch(), levelDifficulty.name(), 700, 720 - i * 150);
            font.draw(getBatch(), gameType.name(), 700, 680 - i * 150);
        }
    }

    private void drawHeaderAndScores() {
        for (int i = 0; i < playerStatsList.size(); i++) {
            final PlayerStats playerStats = playerStatsList.get(i);
            final String name = playerStats.getName();
            final List<Integer> scoreList = playerStats.getScoreList();
            int x = 300 + i * 800;
            font.draw(getBatch(), name, x, 800);

            for (int j = 0; j < scoreList.size(); j++) {
                final String score = getStringScore(scoreList.get(j));
                font.draw(getBatch(), score, x, 700 - j * 150);
            }

        }
    }

    private String getStringScore(Integer integer) {
        if (integer != null) {
            return String.valueOf(integer);
        }
        return "?";
    }
}
