package Movie;

/**
 * Abstract class of a cinema's type
 */
public abstract class CinemaType {
    protected int height;
    protected int width;

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract String toString();
}
