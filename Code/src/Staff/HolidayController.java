package Staff;

import Ticket.Holiday;
import Ticket.HolidayList;

import java.time.LocalDate;

/**
 * class to check if a holiday input is valid
 */
public class HolidayController {
    /**
     * checks if the input date is a valid date
     * @param month month of input holiday
     * @param date date of input holiday
     * @return validity of the date
     */
    public boolean dateCheck(int month, int date){
        if (month < 1 || month > 12 || date < 1 || date > 31)
            return false;
        else{
            LocalDate localDate = LocalDate.of(2000, month, date);
            return date <= localDate.lengthOfMonth();
        }
    }

    /**
     * checks if there are any holiday with the same date in the holiday list
     * @param month month of inserted holiday
     * @param date date of inserted holiday
     * @param holidayList current holidays
     * @return validity of the input holiday
     */
    public boolean insertHoliday(int month, int date, HolidayList holidayList){
        Holiday newHoliday = new Holiday(date, month);
        for(Holiday h : holidayList.getAllHolidayRaw())
            if (newHoliday.equals(h))
                return false;

        holidayList.addHoliday(newHoliday);
        return true;
    }
}
