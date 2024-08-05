package net.oi.swccg.gemp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

import net.oi.swccg.gemp.constant.Constant;
import net.oi.swccg.gemp.entity.GameResult;
import net.oi.swccg.gemp.entity.GameResults;
import net.oi.swccg.gemp.entity.Side;
import org.apache.commons.lang3.EnumUtils;

@Component
public class GameResultValidator {
    
    /**
     * Validate the game result included in the overall request
     * @param inputResults input game results
     * @param masterResults master game result data
     * @param thresholdDate the oldest date in which game results may be retained for, based on the retention rate
     * @return map with each input game result being a key, with the mapped value being a list of any validation errors
     */
    public Map<GameResult, List<String>> validateGameResultsRequest(List<GameResult> inputResults, GameResults masterResults, Date thresholdDate) {
        List<String> validationMessages;
        List<String> masterGameIds = masterResults.getGameResults().stream().map(r -> r.getGameId()).collect(Collectors.toList());
        Map<GameResult, List<String>> validatedInputResults = new HashMap<>();

        for (GameResult gameResult : inputResults)
        {
            validationMessages = new ArrayList<>();

            if (gameResult.getDarkSideArchetype() == null || gameResult.getLightSideArchetype() == null || gameResult.getDarkSidePlayer() == null || gameResult.getLightSidePlayer() == null
                || gameResult.getDate() == null || gameResult.getWinner() == null)
            {
                validationMessages.add(Constant.EMPTY_GAME_ENTRY_LOG);
            }
            else
            {
                if (!EnumUtils.isValidEnum(Side.class, gameResult.getWinner().toString()))
                    validationMessages.add("Value for Side must be either D or L.");

                if (gameResult.getDarkSidePlayer().equals(gameResult.getLightSidePlayer()))
                    validationMessages.add("Dark Side player and Light Side player may not be the same.");

                if (masterGameIds.contains(gameResult.getGameId()))
                    validationMessages.add("This has a duplicate game id.");

                if (gameResult.getDate().before(thresholdDate))
                    validationMessages.add("Game date is older than the retention rate.");
            }

            validatedInputResults.put(gameResult, validationMessages);
        }

        return validatedInputResults;
    }
}
