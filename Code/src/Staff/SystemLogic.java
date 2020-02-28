package Staff;

import Movie.Cinema;
import Movie.Cineplex;
import Movie.CineplexCollection;
import Ticket.*;

import java.util.ArrayList;

/**
 * class that handles updating of price values
 */
public class SystemLogic {
    TicketPricing ticketPricing =TicketPricing.getInstance();

    /**
     * method to retrieve price modifiers
     * @param option what type of price modifier to be retrieved
     * @return price modifier array
     */
    public ArrayList<IPrice> getPrice(int option){
        ArrayList<IPrice> solution = new ArrayList<IPrice>();
        if (option == 1) {
        	solution.addAll(ticketPricing.getUserAge());
        }
        else if (option == 2)
            solution.addAll(ticketPricing.getType());
        else if (option == 3)
            solution.addAll(ticketPricing.getDay());

        return solution;
    }

    /**
     * updates base price of tickets
     * @param newBasePrice new base price
     * @param cineplexIndex ID of cineplex to be edited
     * @param cinemaIndex ID of cinema to be edited
     * @throws Exception if price inserted does not makes sense
     */
    public void changeBasePrice(double newBasePrice, int cineplexIndex, int cinemaIndex) throws Exception{
        CineplexCollection cineplexCollection = CineplexCollection.getInstance();
        Cineplex updatedCineplex = cineplexCollection.getCineplex().get(cineplexIndex-1);
        Cinema updatedCinema = updatedCineplex.getCinemas().get(cinemaIndex-1);

        if (newBasePrice <= 0) throw new Exception("Irregular base price");

        updatedCinema.setBasePrice(newBasePrice);
        updatedCineplex.updateCinema(updatedCinema, cinemaIndex-1);
        cineplexCollection.updateCineplex(cineplexIndex-1, updatedCineplex);
    }
}
