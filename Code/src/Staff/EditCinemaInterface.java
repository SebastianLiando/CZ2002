package Staff;

import Movie.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class that handles input of cinema editing features
 */
public class EditCinemaInterface {
    CineplexCollection cineplexCollection = CineplexCollection.getInstance();
    Scanner sc = new Scanner(System.in);
    EditLogic editLogic = new EditLogic();

    /**
     * chooses which feature to run
     */
    public void start(){
        System.out.println("Choose operation: ");
        System.out.println("1. Add cinema");
        System.out.println("2. Edit showing movies in a cinema");
        System.out.println("0. Back");
        int choice;
        do{
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 0)
                return;
            else if (choice > 2)
                System.out.println("Error Choice!");
            else
                startOperation(choice);
        }while (choice < 0 || choice > 2);
    }

    /**
     * main running program of this class
     * @param opId feature to run
     */
    private void startOperation(int opId){
        System.out.println("Select which cineplex to change: ");
        int i =0;
        ArrayList<Cineplex> availableCineplex = cineplexCollection.getCineplex();
        for(Cineplex c : availableCineplex){
            System.out.println((++i) + ". " + c.getName());
        }
        int choice;
        do {
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 0)
                return;
            else if (choice > availableCineplex.size())
                System.out.println("Error choice!");
            else {
                if (opId == 1) {
                    startAddCinema(choice);
                	start();
                }
                else if (opId == 2) {
                    startEditCinema(choice);
                	start();
                }
            }
        }while (choice < 0 || choice > availableCineplex.size());

    }

    /**
     * chooses feature of cinema editing
     * @param cineplexChoice cineplex of cinema to be edited
     */
    private void startEditCinema(int cineplexChoice){
        Cineplex chosenCineplex = cineplexCollection.getCineplex().get(cineplexChoice-1);
        int i = 0, cinemaChoice;

        do {
            System.out.println("Choose which cinema to modify: ");
            for (Cinema c : chosenCineplex.getCinemas()) {
                System.out.println((++i) + ". " + c.getName());
            }
            System.out.println("0. Back");
            cinemaChoice = sc.nextInt();
            if (cinemaChoice == 0) return;
            if (cinemaChoice < 0 || cinemaChoice > chosenCineplex.getCinemas().size())
                System.out.println("Error choice!");
        }while (cinemaChoice < 0 || cinemaChoice > chosenCineplex.getCinemas().size());

        int opChoice;
        do {
            System.out.println("Select operation");
            System.out.println("1. Add new show to the cinema");
            System.out.println("2. Remove a show from the cinema");
            System.out.println("3. Change the timing of a show from the cinema");
            opChoice = sc.nextInt();
            if (opChoice < 1 || opChoice > 3)
                System.out.println("Error choice!");
            else if (opChoice == 1){
                startAddShow(cineplexChoice, cinemaChoice);
            }else{
                startUpdateShow(cineplexChoice, cinemaChoice, opChoice-1);
            }
        }while (opChoice < 1 || opChoice > 3);
    }

    /**
     * handles the input of new cinema info
     * @param chosenCineplexIdx ID of cineplex to be added a new cinema
     */
    private void startAddCinema(int chosenCineplexIdx){
        System.out.println("Enter new cinema name: ");
        String newName = sc.nextLine();
        System.out.println("Enter the cinema quality: ");
        System.out.println("1. Silver ");
        System.out.println("2. Gold ");
        System.out.println("3. Platinum ");
        int typeChoice = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter base price: ");
        double basePrice = sc.nextDouble();
        sc.nextLine();

        try{
            editLogic.addCinemaToCineplex(chosenCineplexIdx,newName,typeChoice, basePrice);
            System.out.println("Successfully added!");
        }catch (Exception e) {
            System.out.println("Failed to add cinema! " + e.getMessage());
        }
    }

    /**
     * handles what movie to be added
     * @param chosenCineplexIdx ID of cineplex of the inserted movie
     * @param chosenCinemaIdx ID of cinema of the inserted movie
     */
    private void startAddShow(int chosenCineplexIdx, int chosenCinemaIdx){
        //Choose which movie from moviecollection
        int choice;
        MovieCollection movieCollection = MovieCollection.getInstance();
        ArrayList<MovieClass> availableMovies = movieCollection.getMovies();

        do {
            System.out.println("Choose title to add");
            int i = 0;
            for (MovieClass m : movieCollection.getMovies()) {
                System.out.println((++i) + ". " + m.getTitle());
            }
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 0) return;
            else if (choice < 0 || choice > availableMovies.size()) System.out.println("Error choice!");
        }while(choice < 0 || choice > availableMovies.size() );
        MovieClass chosenMovie = availableMovies.get(choice - 1);
        //Choose start time
        System.out.println("Enter show date (mm-dd-yyyy)");
        String date = sc.nextLine();
        System.out.println("Enter the starting time (HH.MM)");
        String time = sc.nextLine();

        try {
            editLogic.insertMovieToCinema(chosenCineplexIdx, chosenCinemaIdx, chosenMovie, date, time);
        }catch(Exception e){
            System.out.println("Failed to add movie to cinema! " + e.getMessage());
        }
    }

    /**
     * handles the input of showing movie update
     * @param chosenCineplexIdx ID of cineplex to be updated
     * @param chosenCinemaIdx ID of cinema to be updated
     * @param op what operation to be run
     */
    private void startUpdateShow(int chosenCineplexIdx, int chosenCinemaIdx, int op){
        //Choose which date
        int choice;
        String word = (op == 1) ? "remove" : "update";
        ArrayList<LocalDate> showdates =editLogic.displayShowInCinema(chosenCineplexIdx, chosenCinemaIdx);
        do {
            System.out.println("Select which date to "+ word +" from");
            int i = 0;

            for (LocalDate ld : showdates) {
                System.out.println((++i) + ". " + ld.format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
            System.out.println("0. Back");
            choice = sc.nextInt();
            if (choice == 0)
                return;
            else if (choice < 0 || choice > showdates.size())
                System.out.println("Error choice!");
        }while (choice < 0 || choice > showdates.size());
        LocalDate toUpdateKey = showdates.get(choice - 1);
        //Choose which one to remove
        ArrayList<MovieShowing> toUpdateList = editLogic.getShowingMovies(chosenCineplexIdx,chosenCinemaIdx,toUpdateKey);
        int movieChoice;
        System.out.println("Choose which movie show to "+ word);
        do {
            int i = 0;
            DateTimeFormatter displayTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
            for (MovieShowing m : toUpdateList) {
                System.out.printf("%d. %-40s", ++i, m.getMovieTitle());
                System.out.println(m.getStartTime().format(displayTimeFormat) + " - " + m.getEndTime().format(displayTimeFormat));
            }
            System.out.println("0. Back");
            movieChoice = sc.nextInt();
            if (movieChoice == 0) return;
            if (movieChoice < 0 || movieChoice > toUpdateList.size())
                System.out.println("Error choice!");
        }while(movieChoice < 0 || movieChoice > toUpdateList.size());

        if (op == 1) {
            editLogic.removeMovie(chosenCineplexIdx, chosenCinemaIdx, toUpdateKey, movieChoice);
            System.out.println("Movie has been removed.");
        }else{
            editLogic.removeMovie(chosenCineplexIdx, chosenCinemaIdx, toUpdateKey, movieChoice);
            String oldDate = toUpdateKey.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            String oldTime = toUpdateList.get(movieChoice-1).getStartTime().format(DateTimeFormatter.ofPattern("HH.mm"));
            System.out.println("Enter new show date (mm-dd-yyyy)");
            String newDate = sc.nextLine();
            System.out.println("Enter the new starting time (HH.MM)");
            String newTime = sc.nextLine();
            try {
                editLogic.insertMovieToCinema(chosenCineplexIdx, chosenCinemaIdx, toUpdateList.get(movieChoice - 1), newDate, newTime);
            }catch(Exception e){
                System.out.println("Unable to update! " + e.getMessage());
                try {
                    editLogic.insertMovieToCinema(chosenCineplexIdx, chosenCinemaIdx, toUpdateList.get(movieChoice - 1), oldDate, oldTime);
                }catch (Exception e2){
                    System.out.println("An error occurred!");
                }
            }
        }
    }
}
