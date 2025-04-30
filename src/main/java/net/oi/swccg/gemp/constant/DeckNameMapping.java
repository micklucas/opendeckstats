package net.oi.swccg.gemp.constant;

import java.util.HashMap;
import java.util.Map;
import net.oi.swccg.gemp.dto.InputDeckIdentifier;

public class DeckNameMapping {
    
    public static Map<InputDeckIdentifier, String> deckNameMapping;
    static {
        deckNameMapping = new HashMap<>();
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Luke", "L"), "TheForceIsStrongInMyFamily");
        deckNameMapping.put(new InputDeckIdentifier("Combat", "D"), "LTMTFM");
        deckNameMapping.put(new InputDeckIdentifier("Hoth CRv", "D"), "Hoth CRv");
        deckNameMapping.put(new InputDeckIdentifier("Tatooine CRv", "D"), "Tatooine CRv");
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Anakin", "L"), "TheForceIsStrongInMyFamily");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Vader", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Dooku", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("ROTS - Maul", "D"), "RevengeOfTheSith");
        deckNameMapping.put(new InputDeckIdentifier("Senate", "L"), "PMCTTS");
        deckNameMapping.put(new InputDeckIdentifier("Watto", "D"), "NMNPND");
        deckNameMapping.put(new InputDeckIdentifier("Walkers", "D"), "TSWBDIM");
        deckNameMapping.put(new InputDeckIdentifier("Hunt Down", "D"), "HDADTJ");
        deckNameMapping.put(new InputDeckIdentifier("WHAP", "L"), "WHAP");
        deckNameMapping.put(new InputDeckIdentifier("Skywalker Saga - Rey", "L"), "TheForceIsStrongInMyFamily");
        deckNameMapping.put(new InputDeckIdentifier("MWYHL", "L"), "MWYHL");
        deckNameMapping.put(new InputDeckIdentifier("RST", "L"), "RST");
        deckNameMapping.put(new InputDeckIdentifier("Combat", "L"), "WHT");
        deckNameMapping.put(new InputDeckIdentifier("Invasion", "D"), "I");
        deckNameMapping.put(new InputDeckIdentifier("Hoth CPv", "L"), "Hoth CPv");
        deckNameMapping.put(new InputDeckIdentifier("Tatooine: Watto's Junkyard", "D"), "Tatooine: Watto's Junkyard");
        deckNameMapping.put(new InputDeckIdentifier("Home One: War Room LTWWv", "L"), "Home One: War Room LTWWv");
        deckNameMapping.put(new InputDeckIdentifier("Tatooine: Slave Quarters LTWWv", "L"), "Tatooine: Slave Quarters LTWWv");
        deckNameMapping.put(new InputDeckIdentifier("City In The Clouds", "L"), "CITC");
        deckNameMapping.put(new InputDeckIdentifier("Senate", "D"), "MLITL");
        deckNameMapping.put(new InputDeckIdentifier("Starkiller Base CRv", "D"), "Starkiller Base CRv");
        deckNameMapping.put(new InputDeckIdentifier("Hoth: Main Power Generators (1st Marker) LTWWv", "L"), "Hoth: Main Power Generators (1st Marker) LTWWv");
    }
}
