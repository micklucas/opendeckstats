package net.oi.swccg.gemp.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import net.oi.swccg.gemp.entity.Record;
import net.oi.swccg.gemp.constant.Constant;
import net.oi.swccg.gemp.constant.DeckNameMapping;
import net.oi.swccg.gemp.dto.GameResultResponse;
import net.oi.swccg.gemp.dto.InputDeckIdentifier;
import net.oi.swccg.gemp.dto.UploadResultsResponse;
import net.oi.swccg.gemp.entity.GameResult;
import net.oi.swccg.gemp.entity.GameResults;
import net.oi.swccg.gemp.entity.Side;
import net.oi.swccg.gemp.exception.ProcessException;

@Component
public class GameResultServiceHelper {
    
    public GameResultServiceHelper(GameResultServiceWorker worker) {
        this.worker = worker;
    }

    @Value("${opendeck.record.file}")
    private String recordFile;

    private GameResultServiceWorker worker;

    /**
     * Read in the persisted game results master file
     * @return master game results data
     */
    public GameResults inputToJsonObjectGameResults() {
        GameResults results = new GameResults();

        try {
            BufferedReader br = new BufferedReader(new FileReader(recordFile));
            results = new Gson().fromJson(br, GameResults.class);
        } catch (Exception e) {
            throw new ProcessException(e);
        }

        return results;
    }

    /**
     * Save the updated master game result data to the persistent file
     * @param master master game result data
     */
    public void exportMasterToJsonFile(GameResults master) {
        Gson gson = new Gson();

        try {
            FileWriter writer = new FileWriter(recordFile);

            writer.write(gson.toJson(master));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new ProcessException(e);
        }
    }

    /**
     * Remove game results from the master data that is older than the retention threshold
     * @param master master game result data
     * @param thresholdDate the oldest date in which game results may be retained for, based on the retention rate
     */
    public void removeResultsPastThreshold(GameResults master, Date thresholdDate) {        
        List<GameResult> currentResults = master.getGameResults().stream().filter(r -> r.getDate().after(thresholdDate)).collect(Collectors.toList());

        master.setGameResults(currentResults);
    }

    /**
     * Add the input game results to the master game result data
     * @param master master game result data
     * @param inputResults input game results
     */
    public void addInputResultsToMaster(GameResults master, Map<GameResult, List<String>> inputResults) {
        Map<InputDeckIdentifier, String> deckMapping = DeckNameMapping.deckNameMapping;

        for (GameResult inputResult : inputResults.keySet())
        {
            if (inputResults.get(inputResult).isEmpty())
                master.getGameResults().add(transformInputGameResultToPersistent(inputResult, deckMapping));
        }

    }

    /**
     * Update persisted master game result data
     * @param master master game result data 
     * @param thresholdDate the oldest date in which game results may be retained for, based on the retention rate
     * @param deckMapping map of archetypes to persistent deck identifiers
     */
    public void updateExistingMasterResults(GameResults master, Date thresholdDate, Map<InputDeckIdentifier, String> deckMapping) {
        String darkSideDeckName;
        String lightSideDeckName;
        InputDeckIdentifier darkSideDeckIdentifier;
        InputDeckIdentifier lightSideDeckIdentifier;

        removeResultsPastThreshold(master, thresholdDate);

        for (GameResult result : master.getGameResults())
        {
            darkSideDeckIdentifier = null;
            lightSideDeckIdentifier = null;
            darkSideDeckIdentifier = new InputDeckIdentifier(result.getDarkSideArchetype(), "D");
            lightSideDeckIdentifier = new InputDeckIdentifier(result.getLightSideArchetype(), "L");
            darkSideDeckName = deckMapping.get(darkSideDeckIdentifier);
            lightSideDeckName = deckMapping.get(lightSideDeckIdentifier);
            result.setDarkSideArchetypeShort(resolvePersistentDeckName(darkSideDeckName, result.getDarkSideArchetype(), result.getDarkSideArchetypeShort()));
            result.setLightSideArchetypeShort(resolvePersistentDeckName(lightSideDeckName, result.getLightSideArchetype(), result.getLightSideArchetypeShort()));
        }
    }

    /**
     * Transfom a game result from input to a game result that will be persisted
     * @param inputGameResult game result from the input request
     * @param deckMapping map of archetypes to persistent deck identifiers
     * @return game result to persist to the master data
     */
    public GameResult transformInputGameResultToPersistent(GameResult inputGameResult, Map<InputDeckIdentifier, String> deckMapping) {
        GameResult gameResult = new GameResult();
        InputDeckIdentifier darkSideDeck = new InputDeckIdentifier(inputGameResult.getDarkSideArchetype(), "D");
        InputDeckIdentifier lightSideDeck = new InputDeckIdentifier(inputGameResult.getLightSideArchetype(), "L");
        String darkSideDeckName = deckMapping.get(darkSideDeck);
        String lightSideDeckName = deckMapping.get(lightSideDeck);

        gameResult.setDarkSideArchetype(inputGameResult.getDarkSideArchetype());
        gameResult.setDarkSideArchetypeShort(resolvePersistentDeckName(darkSideDeckName, inputGameResult.getDarkSideArchetype(), inputGameResult.getDarkSideArchetypeShort()));
        gameResult.setDarkSidePlayer(inputGameResult.getDarkSidePlayer());
        gameResult.setLightSideArchetype(inputGameResult.getLightSideArchetype());
        gameResult.setLightSideArchetypeShort(resolvePersistentDeckName(lightSideDeckName, inputGameResult.getLightSideArchetype(), inputGameResult.getLightSideArchetypeShort()));
        gameResult.setLightSidePlayer(inputGameResult.getLightSidePlayer());
        gameResult.setWinner(inputGameResult.getWinner());
        gameResult.setDate(inputGameResult.getDate());
        gameResult.setGameId(inputGameResult.getGameId());

        return gameResult;
    }

    /**
     * Determine the deck identifier to persist based on the its archetype data
     * @param deckName target deck name identifier to persist
     * @param archetype deck archetype
     * @param archetypeShort shorthand or alternate deck archetype
     * @return the deck identifier to persist
     */
    private String resolvePersistentDeckName(String deckName, String archetype, String archetypeShort) {
        if (deckName != null)
            return deckName;
        else
            return (archetypeShort != null ? archetypeShort : archetype);
    }

    /**
     * Calculate overall records for decks included in the master data
     * @param gameResults master game result data
     * @param records records of decks that are included in the master game result data
     * @param side Dark Side or Light Side decks to have their records tabulated
     */
    public void calculateDeckRecords(List<GameResult> gameResults, List<Record> records, Side side) {
        worker.calculateDeckRecords(gameResults, side, records);
    }

    /**
     * Build the response to be returned by the POST game results endpoint
     * @param validatedInputResults input game results from the input request that have undergone validation
     * @param master master game result data
     * @param response response object for the POST game results endpoint
     * @param deckMapping map of archetypes to persistent deck identifiers
     */
    public void buildUploadResultsResponse(Map<GameResult, List<String>> validatedInputResults, GameResults master, UploadResultsResponse response, Map<InputDeckIdentifier, String> deckMapping) {
        List<InputDeckIdentifier> masterDeckIdentifiers = new ArrayList<>();

        response.setTotalGamesSubmitted(validatedInputResults.keySet().size());
        response.setTotalGamesProcessed(validatedInputResults.values().stream().filter(r -> r.isEmpty()).collect(Collectors.toList()).size());

        for (GameResult gameResult : validatedInputResults.keySet())
        {
            if (!validatedInputResults.get(gameResult).isEmpty())
            {
                if (validatedInputResults.get(gameResult).contains(Constant.EMPTY_GAME_ENTRY_LOG))
                {
                    response.setTotalGamesSubmitted(response.getTotalGamesSubmitted()-1);
                    continue;
                }

                GameResultResponse gameResultResponse = new GameResultResponse(gameResult);

                gameResultResponse.setValidationMessages(validatedInputResults.get(gameResult));
                response.getInvalidGameResults().add(gameResultResponse);
            }
        }

        masterDeckIdentifiers.addAll(master.getGameResults().stream().map(r -> new InputDeckIdentifier(r.getDarkSideArchetype(), "D")).distinct().collect(Collectors.toList()));
        masterDeckIdentifiers.addAll(master.getGameResults().stream().map(r -> new InputDeckIdentifier(r.getLightSideArchetype(), "L")).distinct().collect(Collectors.toList()));
        response.setUnresolvedInputDeckNames(masterDeckIdentifiers.stream().filter(d -> !deckMapping.keySet().contains(d)).collect(Collectors.toList()));
    }
}