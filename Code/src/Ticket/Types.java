package Ticket;

import java.io.Serializable;

/**
 * This class is used as base class for Ticket Types multiplier
 */
public class Types implements IPrice,Serializable{
	protected double price;
	protected String name;

	/**
	 * returns price multiplier
	 * @return base price multiplier
	 */
	public double getPrice() {
		return 1;
	}

	/**
	 * returns name of costumer
	 * @return costumer name
	 */
	public String getName() {
		return name;
	}


	/**
	 * sets new base multiplier
	 * @param d new multiplier to be set
	 */
	public void setPrice(double d){price = d;}
}
