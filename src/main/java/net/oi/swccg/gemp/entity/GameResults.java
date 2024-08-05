package net.oi.swccg.gemp.entity;

import java.io.Serializable;
import java.util.List;

public class GameResults implements Serializable {

    List<GameResult> gameResults;

    public void setGameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameResults == null) ? 0 : gameResults.hashCode());
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
        GameResults other = (GameResults) obj;
        if (gameResults == null) {
            if (other.gameResults != null)
                return false;
        } else if (!gameResults.equals(other.gameResults))
            return false;
        return true;
    }
}
