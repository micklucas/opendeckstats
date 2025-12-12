package net.oi.swccg.gemp.dto;

import java.util.ArrayList;
import java.util.List;
import net.oi.swccg.gemp.entity.Record;

public class DeckRankingsResponse {
    
    public DeckRankingsResponse() {
        darkSide = new ArrayList<>();
        lightSide = new ArrayList<>();
    }

    private List<Record> darkSide;
    private List<Record> lightSide;

    public List<Record> getDarkSide() {
        return darkSide;
    }

    public void setDarkSide(List<Record> darkSide) {
        this.darkSide = darkSide;
    }
    
    public List<Record> getLightSide() {
        return lightSide;
    }
    
    public void setLightSide(List<Record> lightSide) {
        this.lightSide = lightSide;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((darkSide == null) ? 0 : darkSide.hashCode());
        result = prime * result + ((lightSide == null) ? 0 : lightSide.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeckRankingsResponse other = (DeckRankingsResponse) obj;
        if (darkSide == null) {
            if (other.darkSide != null)
                return false;
        } else if (!darkSide.equals(other.darkSide))
            return false;
        if (lightSide == null) {
            if (other.lightSide != null)
                return false;
        } else if (!lightSide.equals(other.lightSide))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DeckRankingsResponse [darkSide=" + darkSide + ", lightSide=" + lightSide + "]";
    }
}
