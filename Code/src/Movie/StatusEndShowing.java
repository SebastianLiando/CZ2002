package Movie;

import java.io.Serializable;

/**
 * class to represent an ended movie
 */
public class StatusEndShowing extends MovieStatus implements Serializable{
    /**
     * constructs this class
     */
    public StatusEndShowing(){
        description = "End of Showing";
    }

    /**
     * returns the current status of the movie
     * @return what is the status
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * returns if the movie is schedulable
     * @return schedulable or not
     */
    @Override
    public boolean isSchedulable() {
        return false;
    }
}
