package Movie;

import java.io.Serializable;

/**
 * class used to represent a silver-class cinema properties
 */
public class CinemaSilver extends CinemaType implements Serializable {
    /**
     * constructor of silver cinema
     */
    public CinemaSilver(){
        height = 12;
        width =  16;
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
        return "Silver";
    }
}
