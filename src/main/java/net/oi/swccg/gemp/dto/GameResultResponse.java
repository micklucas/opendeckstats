package net.oi.swccg.gemp.dto;

import java.util.List;
import net.oi.swccg.gemp.entity.GameResult;

public class GameResultResponse extends GameResult {

    public GameResultResponse(GameResult gameResult) {
        this.setDarkSideArchetype(gameResult.getDarkSideArchetype());
        this.setDarkSideArchetypeShort(gameResult.getDarkSideArchetypeShort());
        this.setDarkSidePlayer(gameResult.getDarkSidePlayer());
        this.setLightSideArchetype(gameResult.getLightSideArchetype());
        this.setLightSideArchetypeShort(gameResult.getLightSideArchetypeShort());
        this.setLightSidePlayer(gameResult.getLightSidePlayer());
        this.setWinner(gameResult.getWinner());
        this.setDate(gameResult.getDate());
        this.setGameId(gameResult.getGameId());
    }

    private List<String> validationMessages;

    public List<String> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((validationMessages == null) ? 0 : validationMessages.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameResultResponse other = (GameResultResponse) obj;
        if (validationMessages == null) {
            if (other.validationMessages != null)
                return false;
        } else if (!validationMessages.equals(other.validationMessages))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GameResultResponse [validationMessages=" + validationMessages + "]";
    }
}
