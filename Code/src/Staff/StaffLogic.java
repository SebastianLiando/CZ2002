package Staff;

import java.util.Arrays;

/**
 * handles the login authentication of staff
 */
public class StaffLogic {
	/**
	 * checks the staff credentials
	 * @param n userID of staff
	 * @param p password of staff
	 * @return authorized login or not
	 */
	public boolean check(String n, int p) {
		
		if(n.compareTo("Staff1")==0 && p == 1111){ //Check password blom ada
 			return false;
		}else {
			return true;
		}
	}
}
