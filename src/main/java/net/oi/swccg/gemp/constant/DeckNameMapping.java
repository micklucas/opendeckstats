package net.oi.swccg.gemp.constant;

import java.util.HashMap;
import java.util.Map;
import net.oi.swccg.gemp.dto.InputDeckIdentifier;

public class DeckNameMapping {
    
    public static Map<InputDeckIdentifier, String> deckNameMapping;
    static {
        deckNameMapping = new HashMap<>();
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Luke", "L"), "SkywalkerSaga");
        deckNameMapping.put(new InputDeckIdentifier("Combat", "D"), "LTMTFM");
        deckNameMapping.put(new InputDeckIdentifier("Hoth CRv", "D"), "Hoth CRv");
        deckNameMapping.put(new InputDeckIdentifier("Tatooine CRv", "D"), "Tatooine CRv");
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Anakin", "L"), "SkywalkerSaga");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Vader", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Dooku", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Maul", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("Senate", "L"), "PMCTTS");
        deckNameMapping.put(new InputDeckIdentifier("Watto", "D"), "NMNPND");
        deckNameMapping.put(new InputDeckIdentifier("Walkers", "D"), "TSWBDIM");
        deckNameMapping.put(new InputDeckIdentifier("Hunt Down", "D"), "HDADTJ");
        deckNameMapping.put(new InputDeckIdentifier("WHAP", "L"), "WHAP");
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Rey", "L"), "SkywalkerSaga");
        deckNameMapping.put(new InputDeckIdentifier("MWYHL", "L"), "MWYHL");
        deckNameMapping.put(new InputDeckIdentifier("RST", "L"), "RST");
        deckNameMapping.put(new InputDeckIdentifier("Combat", "L"), "WHT");
    }
}
