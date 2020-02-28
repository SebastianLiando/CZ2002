package Ticket;

/**
 * Interface as base of all price modifier classes
 */
public interface IPrice {
    public void setPrice(double d);
    public String getName();
    public double getPrice();
}
