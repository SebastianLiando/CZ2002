package Movie;

import java.io.Serializable;

/**
 * class used to represent a platinum-class cinema properties
 */
public class CinemaPlatinum extends CinemaType implements Serializable{
    /**
     * constructor of platinum cinema
     */
    public CinemaPlatinum(){
        height = 12;
        width =  8;
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
        return "Platinum";
    }
}
