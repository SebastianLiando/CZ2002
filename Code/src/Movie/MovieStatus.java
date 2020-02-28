package Movie;

import java.io.Serializable;

/**
 * abstract class of a movie status
 */
public abstract class MovieStatus implements Serializable {
    String description;
    public abstract String toString();
    public abstract boolean isSchedulable();
}
