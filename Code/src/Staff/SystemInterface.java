package Staff;

import Movie.Cinema;
import Movie.Cineplex;
import Movie.CineplexCollection;
import Ticket.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to receive input from staff and calls the appropriate methods
 */
public class SystemInterface {
    Scanner sc = new Scanner(System.in);
    SystemLogic systemLogic = new SystemLogic();
    CineplexCollection cineplexCollection = CineplexCollection.getInstance();

    /**
     * method that handles what type of price or price modifier to be updated
     * @param ticketPricing
     */
    public void startPrice(TicketPricing ticketPricing){
        int choice;
        do {
            System.out.println("Select which price category to modify: ");
            System.out.println("1. User age");
            System.out.println("2. Cinema quality");
            System.out.println("3. Weekday/Weekend");
            System.out.println("4. Cinema base price");
            System.out.println("0. Back");
            choice = sc.nextInt();
            if (choice == 0)
                return;
            else if (choice > 4)
                System.out.println("Error choice! Please reenter");
            else if (choice == 4) {
                startSelectCineplex();
            }else {
                startChangePrice(ticketPricing, choice);
            }
        }while(choice < 0 || choice > 4);
    }

    /**
     * handles modification of price modifier
     * @param ticketPricing object of price modifier to be updated
     * @param option which modifier to be modified
     */
    private void startChangePrice(TicketPricing ticketPricing, int option){
        int choice;
        ArrayList<IPrice> prices = systemLogic.getPrice(option);

        do {
            int i = 0;
            System.out.println("Select which to modify: ");
            for(IPrice ip :prices){
                System.out.println((++i) + ". " + ip.getName());
            }
            choice = sc.nextInt();
            if(choice == 0)
                return;
            else if (choice >= prices.size())
                System.out.println("Error choice! You picked an unavailable choice");
            else{
                IPrice ip = prices.get(choice - 1);
                System.out.println("Current price modifier: " + ip.getPrice());
                System.out.println("Enter new price modifier (-1 to cancel): ");
                double mod = sc.nextDouble();
                if (mod == -1.0)
                    return;
                else {
                    ip.setPrice(mod);
                    prices.set(choice-1, ip);
                    ticketPricing.setPrice(prices, option);
                    System.out.println("Modified!");
                }
            }
        }while(choice < 0 || choice >= prices.size());
    }

    /**
     * method to handle choosing of cineplex to be modified
     */
    private void startSelectCineplex(){
        int i = 0, choice;
        do {
            System.out.println("Choose which cineplex has the cinema");
            for (Cineplex c : cineplexCollection.getCineplex()) {
                System.out.println((++i) + ". " + c.getName());
            }
            System.out.println("0. Back");
            choice = sc.nextInt();
            if (choice == 0) return;
            if (choice < 0 || choice > cineplexCollection.getCineplex().size())
                System.out.println("Error choice!");
        }while (choice < 0 || choice > cineplexCollection.getCineplex().size());
        startSelectCinema(choice);
    }
    /**
     * method to handle choosing of cinema to be modified
     */
    private void startSelectCinema(int option){

        ArrayList<Cinema> cinemas = cineplexCollection.getCineplex().get(option-1).getCinemas();
        int i = 0, choice;
        do{
            System.out.println("Select the cinema");
            for (Cinema c : cinemas) {
                System.out.println((++i) + ". " + c.getName());
            }
            System.out.println("0. Back");
            choice = sc.nextInt();
            if (choice == 0) return;
            if (choice < 0 || choice >cinemas.size())
                System.out.println("Error choice!");
        }while(choice < 0 || choice > cinemas.size());

        System.out.println("Current base price: " + cinemas.get(choice - 1).getBasePrice());
        System.out.println("Enter new base price: ");
        double newBasePrice = sc.nextDouble();
        try {
            systemLogic.changeBasePrice(newBasePrice, option, choice);
        }catch(Exception e){
            System.out.println("Failed to update base price: " + e.getMessage());
        }
    }

    /**
     * method to handle creation of new holiday
     * @param holidayList current list of holiday objects
     */
    public void startHoliday(HolidayList holidayList){
        int month, date;

        System.out.println("--Creating new holiday--");
        System.out.println("Enter month (1-12): ");
        month = sc.nextInt();
        System.out.println("Enter date (1-31): ");
        date = sc.nextInt();
        HolidayController holidayController = new HolidayController();
        if (!holidayController.dateCheck(month, date)){
            System.out.println("Error date format! Returning...");
            return;
        }else{
            if (holidayController.insertHoliday(month, date, holidayList))
                System.out.println("Added holiday succesfully");
            else
                System.out.println("Failed to add as the indicated date is already set as a holiday");
            return;
        }
    }
}
