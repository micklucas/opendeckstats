package net.oi.swccg.gemp.entity;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;

public class GameResult implements Serializable {

    @Size(min=1,max=50,message="Dark Side archetype name must be populated and less than 50 characters long.")
    private String darkSideArchetype;

    @Size(min=1,max=50,message="Dark Side archetype short name must be populated and less than 10 characters long.")
    private String darkSideArchetypeShort;

    @Size(min=1,max=10,message="Dark Side player name must be populated and less than 10 characters long.")
    private String darkSidePlayer;

    @Size(min=1,max=50,message="Light Side archetype name must be populated and less than 50 characters long.")
    private String lightSideArchetype;

    @Size(min=1,max=10,message="Light Side archetype short name must be populated and less than 10 characters long.")
    private String lightSideArchetypeShort;

    @Size(min=1,max=10,message="Light Side player name must be populated and less than 10 characters long.")
    private String lightSidePlayer;

    private Side winner;
    
    @JsonFormat(pattern="yyyyMMdd")
    private Date date;
    
    private String gameId;

    public String getDarkSideArchetype() {
        return darkSideArchetype;
    }

    public void setDarkSideArchetype(String darkSideArchetype) {
        this.darkSideArchetype = darkSideArchetype;
    }

    public String getDarkSideArchetypeShort() {
        return darkSideArchetypeShort;
    }

    public void setDarkSideArchetypeShort(String darkSideArchetypeShort) {
        this.darkSideArchetypeShort = darkSideArchetypeShort;
    }

    public String getDarkSidePlayer() {
        return darkSidePlayer;
    }

    public void setDarkSidePlayer(String darkSidePlayer) {
        this.darkSidePlayer = darkSidePlayer;
    }

    public String getLightSideArchetype() {
        return lightSideArchetype;
    }

    public void setLightSideArchetype(String lightSideArchetype) {
        this.lightSideArchetype = lightSideArchetype;
    }

    public String getLightSideArchetypeShort() {
        return lightSideArchetypeShort;
    }

    public void setLightSideArchetypeShort(String lightSideArchetypeShort) {
        this.lightSideArchetypeShort = lightSideArchetypeShort;
    }

    public String getLightSidePlayer() {
        return lightSidePlayer;
    }

    public void setLightSidePlayer(String lightSidePlayer) {
        this.lightSidePlayer = lightSidePlayer;
    }

    public Side getWinner() {
        return winner;
    }

    public void setWinner(Side winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((darkSideArchetype == null) ? 0 : darkSideArchetype.hashCode());
        result = prime * result + ((darkSideArchetypeShort == null) ? 0 : darkSideArchetypeShort.hashCode());
        result = prime * result + ((darkSidePlayer == null) ? 0 : darkSidePlayer.hashCode());
        result = prime * result + ((lightSideArchetype == null) ? 0 : lightSideArchetype.hashCode());
        result = prime * result + ((lightSideArchetypeShort == null) ? 0 : lightSideArchetypeShort.hashCode());
        result = prime * result + ((lightSidePlayer == null) ? 0 : lightSidePlayer.hashCode());
        result = prime * result + ((winner == null) ? 0 : winner.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
        GameResult other = (GameResult) obj;
        if (darkSideArchetype == null) {
            if (other.darkSideArchetype != null)
                return false;
        } else if (!darkSideArchetype.equals(other.darkSideArchetype))
            return false;
        if (darkSideArchetypeShort == null) {
            if (other.darkSideArchetypeShort != null)
                return false;
        } else if (!darkSideArchetypeShort.equals(other.darkSideArchetypeShort))
            return false;
        if (darkSidePlayer == null) {
            if (other.darkSidePlayer != null)
                return false;
        } else if (!darkSidePlayer.equals(other.darkSidePlayer))
            return false;
        if (lightSideArchetype == null) {
            if (other.lightSideArchetype != null)
                return false;
        } else if (!lightSideArchetype.equals(other.lightSideArchetype))
            return false;
        if (lightSideArchetypeShort == null) {
            if (other.lightSideArchetypeShort != null)
                return false;
        } else if (!lightSideArchetypeShort.equals(other.lightSideArchetypeShort))
            return false;
        if (lightSidePlayer == null) {
            if (other.lightSidePlayer != null)
                return false;
        } else if (!lightSidePlayer.equals(other.lightSidePlayer))
            return false;
        if (winner == null) {
            if (other.winner != null)
                return false;
        } else if (!winner.equals(other.winner))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (gameId == null) {
            if (other.gameId != null)
                return false;
        } else if (!gameId.equals(other.gameId))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "GameResult [darkSideArchetype=" + darkSideArchetype + ", darkSideArchetypeShort="
                + darkSideArchetypeShort + ", darkSidePlayer=" + darkSidePlayer + ", lightSideArchetype="
                + lightSideArchetype + ", lightSideArchetypeShort=" + lightSideArchetypeShort + ", lightSidePlayer="
                + lightSidePlayer + ", winner=" + winner + ", date=" + date + ", gameId=" + gameId + "]";
    }

}
