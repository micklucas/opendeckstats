package net.oi.swccg.gemp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import net.oi.swccg.gemp.entity.Record;
import net.oi.swccg.gemp.entity.GameResult;
import net.oi.swccg.gemp.entity.Side;

@Component
public class GameResultServiceWorker {
    
    /**
     * Tabulate deck records from master game result data for one side
     * @param gameResults master game result data
     * @param side Dark Side or Light Side
     * @param records calculated records for each deck for the input side
     */
    public void calculateDeckRecords(List<GameResult> gameResults, Side side, List<Record> records) {
        List<String> decks;
        List<GameResult> deckResults = new ArrayList<>();
        
        if (side.equals(Side.D))
            decks = gameResults.stream().map(r -> r.getDarkSideArchetypeShort()).distinct().collect(Collectors.toList());
        else
            decks = gameResults.stream().map(r -> r.getLightSideArchetypeShort()).distinct().collect(Collectors.toList());

        for (String deck : decks)
        {
            Record record = new Record(deck);

            deckResults.clear();
            
            if (side.equals(Side.D))
            {
                deckResults = gameResults.stream().filter(r -> r.getDarkSideArchetypeShort().equals(deck)).collect(Collectors.toList());
                
                for (GameResult result : deckResults)
                {
                    updateRecord(record, Side.D, result.getWinner());
                }
            }
            else
            {
                deckResults = gameResults.stream().filter(r -> r.getLightSideArchetypeShort().equals(deck)).collect(Collectors.toList());

                for (GameResult result : deckResults)
                {
                    updateRecord(record, Side.L, result.getWinner());
                }
            }

            record.setDifferential(record.getWins() - record.getLosses());
            records.add(record);
        }
    }

    /**
     * Update a record for a deck for one game result
     * @param record deck's record object
     * @param side Dark Side or Light Side
     * @param winner winning side for the input game
     */
    private void updateRecord(Record record, Side side, Side winner) {
        record.addGame();

        if (winner.equals(side))
            record.addWin();
        else
            record.addLoss();
    }
}
