package net.oi.swccg.gemp.entity;

import java.io.Serializable;

public class Record implements Serializable {

    public Record(String deck) {
        this.deck = deck;
        this.totalGames = 0;
        this.wins = 0;
        this.losses = 0;
        this.differential = 0;
    }

    private String deck;
    private Integer totalGames;
    private Integer wins;
    private Integer losses;
    private Integer differential;

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public Integer getDifferential() {
        return differential;
    }

    public void setDifferential(Integer differential) {
        this.differential = differential;
    }

    public void addGame() {
        totalGames = totalGames + 1;
    }

    public void addWin() {
        wins = wins + 1;
    }

    public void addLoss() {
        losses = losses + 1;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((deck == null) ? 0 : deck.hashCode());
        result = prime * result + ((totalGames == null) ? 0 : totalGames.hashCode());
        result = prime * result + ((wins == null) ? 0 : wins.hashCode());
        result = prime * result + ((losses == null) ? 0 : losses.hashCode());
        result = prime * result + ((differential == null) ? 0 : differential.hashCode());
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
        Record other = (Record) obj;
        if (deck == null) {
            if (other.deck != null)
                return false;
        } else if (!deck.equals(other.deck))
            return false;
        if (totalGames == null) {
            if (other.totalGames != null)
                return false;
        } else if (!totalGames.equals(other.totalGames))
            return false;
        if (wins == null) {
            if (other.wins != null)
                return false;
        } else if (!wins.equals(other.wins))
            return false;
        if (losses == null) {
            if (other.losses != null)
                return false;
        } else if (!losses.equals(other.losses))
            return false;
        if (differential == null) {
            if (other.differential != null)
                return false;
        } else if (!differential.equals(other.differential))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Record [deck=" + deck + ", totalGames=" + totalGames + ", wins=" + wins + ", losses=" + losses
                + ", differential=" + differential + "]";
    }
}
