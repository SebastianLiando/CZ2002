package Ticket;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class holds information of a holiday
 */

public class Holiday implements Serializable{
	public LocalDate date;

	/**
	 * returns month of the holiday
	 * @return month of holiday
	 */
	public int getMonth() {
		return date.getMonthValue();
	}

	/**
	 * returns day of the holiday
	 * @return day of holiday
	 */
	public int getDate() {
		return date.getDayOfMonth();
	}

	/**
	 * sets the month and date of the holiday
	 * @param day date of the holiday
	 * @param month month of the holiday
	 */
	public Holiday(int day, int month) {
		date = LocalDate.of(2019,month,day);
	}

	/**
	 * checks if this object's date is already in another holiday object
	 * @param obj the other holiday object
	 * @return boolean value of this holiday's existence in other holiday object
	 */
	@Override
	public boolean equals(Object obj) {
		Holiday comparedHoliday = (Holiday) obj;
		return (getDate() == comparedHoliday.getDate() && getMonth() == comparedHoliday.getMonth());
	}
}
