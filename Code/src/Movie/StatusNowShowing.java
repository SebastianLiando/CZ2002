package Movie;

import java.io.Serializable;

/**
 * class to represent a currently showing movie
 */
public class StatusNowShowing extends MovieStatus implements Serializable{
    /**
     * constructs this class
     */
    public StatusNowShowing(){
        description = "Now Showing";
    }

    /**
     * returns the current status of the movie
     * @return what is the status
     */
    @Override
    public boolean isSchedulable() {
        return true;
    }

    /**
     * returns if the movie is schedulable
     * @return schedulable or not
     */
    @Override
    public String toString() {
        return description;
    }
}
