package net.oi.swccg.gemp.service;

import java.util.Comparator;
import net.oi.swccg.gemp.entity.Record;

/* Comparator implementation for Record to correctly sort deck records.  A decks are first ranked according to highest total win-loss differential.  If 
 * win-loss differentials are equal, then they are next ranked according to most total games.  If total games are equal, then the decks rank the same overall
 */
public class RecordComparator implements Comparator<Record> {

    @Override
    public int compare(Record record1, Record record2) {
        if (record1.getDifferential() != record2.getDifferential())
        {
            if (record1.getDifferential() > record2.getDifferential())
                return -1;
            else
                return 1;
        }
        else
        {
            if (record1.getTotalGames() != record2.getTotalGames())
            {
                if (record1.getTotalGames() > record2.getTotalGames())
                    return -1;
                else
                    return 1;
            }
            else
                return 0;
        }
    }
}
