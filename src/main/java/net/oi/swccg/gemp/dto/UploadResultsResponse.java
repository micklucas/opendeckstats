package net.oi.swccg.gemp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UploadResultsResponse implements Serializable {
    
    private Integer totalGamesSubmitted;
    private Integer totalGamesProcessed;
    private List<GameResultResponse> invalidGameResults = new ArrayList<>();
    private List<InputDeckIdentifier> unresolvedInputDeckNames;

    public Integer getTotalGamesSubmitted() {
        return totalGamesSubmitted;
    }

    public void setTotalGamesSubmitted(Integer totalGamesSubmitted) {
        this.totalGamesSubmitted = totalGamesSubmitted;
    }

    public Integer getTotalGamesProcessed() {
        return totalGamesProcessed;
    }

    public void setTotalGamesProcessed(Integer totalGamesProcessed) {
        this.totalGamesProcessed = totalGamesProcessed;
    }

    public List<GameResultResponse> getInvalidGameResults() {
        return invalidGameResults;
    }

    public void setInvalidGameResults(List<GameResultResponse> invalidGameResults) {
        this.invalidGameResults = invalidGameResults;
    }

    public List<InputDeckIdentifier> getUnresolvedInputDeckNames() {
        return unresolvedInputDeckNames;
    }

    public void setUnresolvedInputDeckNames(List<InputDeckIdentifier> unresolvedInputDeckNames) {
        this.unresolvedInputDeckNames = unresolvedInputDeckNames;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((totalGamesSubmitted == null) ? 0 : totalGamesSubmitted.hashCode());
        result = prime * result + ((totalGamesProcessed == null) ? 0 : totalGamesProcessed.hashCode());
        result = prime * result + ((invalidGameResults == null) ? 0 : invalidGameResults.hashCode());
        result = prime * result + ((unresolvedInputDeckNames == null) ? 0 : unresolvedInputDeckNames.hashCode());
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
        UploadResultsResponse other = (UploadResultsResponse) obj;
        if (totalGamesSubmitted == null) {
            if (other.totalGamesSubmitted != null)
                return false;
        } else if (!totalGamesSubmitted.equals(other.totalGamesSubmitted))
            return false;
        if (totalGamesProcessed == null) {
            if (other.totalGamesProcessed != null)
                return false;
        } else if (!totalGamesProcessed.equals(other.totalGamesProcessed))
            return false;
        if (invalidGameResults == null) {
            if (other.invalidGameResults != null)
                return false;
        } else if (!invalidGameResults.equals(other.invalidGameResults))
            return false;
        if (unresolvedInputDeckNames == null) {
            if (other.unresolvedInputDeckNames != null)
                return false;
        } else if (!unresolvedInputDeckNames.equals(other.unresolvedInputDeckNames))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Response [totalGamesSubmitted=" + totalGamesSubmitted + ", totalGamesProcessed=" + totalGamesProcessed
                + ", invalidGameResults=" + invalidGameResults + ", unresolvedInputDeckNames="
                + unresolvedInputDeckNames + "]";
    }

}
