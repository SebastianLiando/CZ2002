package Movie;

import java.io.Serializable;

/**
 * class to represent an upcoming movie
 */
public class StatusUpcoming extends MovieStatus implements Serializable{

    /**
     * construcs this class
     */
    public StatusUpcoming(){
        description = "Upcoming";
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
        return true;
    }
}
