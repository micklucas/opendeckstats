package net.oi.swccg.gemp.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import net.oi.swccg.gemp.constant.DeckNameMapping;
import net.oi.swccg.gemp.dto.DeckRankingsResponse;
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
    private GameResults master;
    private List<Record> allDeckRecords;

    /**
     * Service method to import the game results to the master data
     * @param gameResults input game results
     * @return response object for the POST game results endpoint
     */
    public UploadResultsResponse loadGameResults(List<GameResult> gameResults) {
        Map<GameResult, List<String>> validatedInputResults;
        Date today = Calendar.getInstance().getTime();
        Date thresholdDate = OpenDeckStatsUtil.adjustDate(today, -180);
        List<Record> darkSideDeckRecords = new ArrayList<>();
        List<Record> lightSideDeckRecords = new ArrayList<>();
        UploadResultsResponse response = new UploadResultsResponse();
        Map<InputDeckIdentifier, String> deckMapping = DeckNameMapping.deckNameMapping;

        //load existing results from file
        if (master == null)
            master = helper.inputToJsonObjectGameResults();

        //validate input against updated master game result records
        validatedInputResults = validator.validateGameResultsRequest(gameResults, master, thresholdDate);

        //add results from request to all results
        helper.updateExistingMasterResults(master, thresholdDate, deckMapping);
        helper.addInputResultsToMaster(master, validatedInputResults);

        //aggregate and compile deck records
        if (allDeckRecords == null)
            allDeckRecords = new ArrayList<>();

        helper.calculateDeckRecords(master.getGameResults(), darkSideDeckRecords, Side.D);
        helper.calculateDeckRecords(master.getGameResults(), lightSideDeckRecords, Side.L);
        allDeckRecords.addAll(darkSideDeckRecords);
        allDeckRecords.addAll(lightSideDeckRecords);

        //save new master results file
        helper.exportMasterToJsonFile(master);

        //build response to return to client
        helper.buildUploadResultsResponse(validatedInputResults, master, response, deckMapping);

        return response;
    }

    /**
     * Service method to calculate and return current deck rankings
     * @return object containing ranked decks for each Side
     */
    public DeckRankingsResponse returnDeckRankings() {
        DeckRankingsResponse deckRankings = new DeckRankingsResponse();
        List<Record> darkSideDeckRecords = new ArrayList<>();
        List<Record> lightSideDeckRecords = new ArrayList<>();

        if (master == null)
            master = helper.inputToJsonObjectGameResults();

        //aggregate and compile deck records
        if (allDeckRecords == null)
            allDeckRecords = new ArrayList<>();

        helper.calculateDeckRecords(master.getGameResults(), darkSideDeckRecords, Side.D);
        helper.calculateDeckRecords(master.getGameResults(), lightSideDeckRecords, Side.L);
        allDeckRecords.addAll(darkSideDeckRecords);
        allDeckRecords.addAll(lightSideDeckRecords);
        darkSideDeckRecords.sort(new RecordComparator());
        lightSideDeckRecords.sort(new RecordComparator());
        deckRankings.setDarkSide(darkSideDeckRecords);
        deckRankings.setLightSide(lightSideDeckRecords);

        return deckRankings;
    }

    /**
     * Service method to calculate and return current deck rankings in a nicely formatted report form
     * @return string object of the formatted report of deck rankings
     */
    public String returnDeckRankingsReport() {
        String report = "";
        DeckRankingsResponse rankings = returnDeckRankings();
        List<Record> darkSideDecks = rankings.getDarkSide();
        List<Record> lightSideDecks = rankings.getLightSide();

        report = "DARK" + 
               "\n-------------------------------------------------------------------------" + 
               "\nRank   Deck                                     Diff  Total   Win  Loss" + 
               "\n-------------------------------------------------------------------------";

        report = helper.buildDeckRankingsReport(report, darkSideDecks);

        report = report + "\n\nLIGHT" + 
               "\n-------------------------------------------------------------------------" + 
               "\nRank   Deck                                     Diff  Total   Win  Loss" + 
               "\n-------------------------------------------------------------------------";

        report = helper.buildDeckRankingsReport(report, lightSideDecks);

        return report;
    }
}
