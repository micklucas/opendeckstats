package net.oi.swccg.gemp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import net.oi.swccg.gemp.constant.DeckNameMapping;
import net.oi.swccg.gemp.dto.InputDeckIdentifier;
import net.oi.swccg.gemp.dto.UploadResultsResponse;
import net.oi.swccg.gemp.entity.GameResult;
import net.oi.swccg.gemp.entity.GameResults;
import net.oi.swccg.gemp.entity.Side;
import net.oi.swccg.gemp.util.OpenDeckStatsUtil;
import net.oi.swccg.gemp.entity.Record;

@Component
public class GameResultService {

    public GameResultService(GameResultServiceHelper helper, GameResultValidator validator) {
        this.helper = helper;
        this.validator = validator;
    }

    private GameResultServiceHelper helper;
    private GameResultValidator validator;

    /**
     * Service method to import the game results to the master data
     * @param gameResults input game results
     * @return response object for the POST game results endpoint
     */
    public UploadResultsResponse loadGameResults(List<GameResult> gameResults) {
        Map<GameResult, List<String>> validatedInputResults;
        GameResults master;
        Date today = Calendar.getInstance().getTime();
        Date thresholdDate = OpenDeckStatsUtil.adjustDate(today, -180);
        List<Record> darkSideDeckRecords = new ArrayList<>();
        List<Record> lightSideDeckRecords = new ArrayList<>();
        UploadResultsResponse response = new UploadResultsResponse();
        Map<InputDeckIdentifier, String> deckMapping = DeckNameMapping.deckNameMapping;

        //load existing results from file
        master = helper.inputToJsonObjectGameResults();

        //validate input against updated master game result records
        validatedInputResults = validator.validateGameResultsRequest(gameResults, master, thresholdDate);

        //add results from request to all results
        helper.updateExistingMasterResults(master, thresholdDate, deckMapping);
        helper.addInputResultsToMaster(master, validatedInputResults);

        //aggregate and compile deck records
        helper.calculateDeckRecords(master.getGameResults(), darkSideDeckRecords, Side.D);
        helper.calculateDeckRecords(master.getGameResults(), lightSideDeckRecords, Side.L);

        //save new master results file
        helper.exportMasterToJsonFile(master);

        //build response to return to client
        helper.buildUploadResultsResponse(validatedInputResults, master, response, deckMapping);

        return response;
    }
}
