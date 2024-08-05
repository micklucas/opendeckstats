package net.oi.swccg.gemp.dto;

import java.io.Serializable;

public class InputDeckIdentifier implements Serializable {

    public InputDeckIdentifier(String archetype, String side) {
        this.archetype = archetype;
        this.side = side;
    }

    public InputDeckIdentifier() {
        super();
    }

    private String archetype;
    private String side;

    public String getArchetype() {
        return archetype;
    }

    public void setArchtype(String archetype) {
        this.archetype = archetype;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((archetype == null) ? 0 : archetype.hashCode());
        result = prime * result + ((side == null) ? 0 : side.hashCode());
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
        InputDeckIdentifier other = (InputDeckIdentifier) obj;
        if (archetype == null) {
            if (other.archetype != null)
                return false;
        } else if (!archetype.equals(other.archetype))
            return false;
        if (side != other.side)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InputDeckIdentifier [archetype=" + archetype + ", side=" + side + "]";
    }
}
