package Movie;

import java.io.Serializable;

/**
 * class used to represent a gold-class cinema properties
 */
public class CinemaGold extends CinemaType implements Serializable{
    /**
     * constructor of gold cinema
     */
    public CinemaGold(){
        height = 12;
        width =  12;
    }

    /**
     * returns the height of the cinema
     * @return the height of the cinema
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * returns the width of the cinema
     * @return the width of the cinema
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * returns the name class of the cinema
     * @return
     */
    @Override
    public String toString() {
        return "Gold";
    }
}
