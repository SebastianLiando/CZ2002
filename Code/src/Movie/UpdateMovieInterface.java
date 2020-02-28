package Movie;

import Staff.UpdateLogic;

import java.util.Scanner;

/**
 * handles the input to update a movie information or status
 */
public class UpdateMovieInterface {
    MovieCollection movieCollection = MovieCollection.getInstance();
    UpdateLogic updateLogic = new UpdateLogic();
    Scanner sc = new Scanner(System.in);

    /**
     * chooses which feature to be run
     */
    public void start(){
        int choice;
        do {
            System.out.println("Select operation");
            System.out.println("1. Update movie status");
            System.out.println("2. Update user age for a movie");
            System.out.println("0. Back");
            choice = sc.nextInt();
            if(choice == 0)
                return;
            else if (choice > 2)
                System.out.println("Error choice!");
            else
                startPreUpdate(choice);
        }while(choice != 0);
    }

    /**
     * handles input of actual status/info of movie
     * @param op what operation to be run
     */
    private void startPreUpdate(int op){
        //Select which movie to update
        int choice;
        do {
            int i = 0;
            System.out.println("Choose which movie to update");
            for (MovieClass m : movieCollection.getMovies()) {
                System.out.println((++i) + ". " + m.getTitle());
            }
            System.out.println("0. Back");
            choice = sc.nextInt();
            if (choice == 0)
                return;
            else if (choice < 0 || choice > movieCollection.getMovies().size())
                System.out.println("Error choice!");
        }while(choice < 0 || choice > movieCollection.getMovies().size());
        MovieClass chosenMovie = movieCollection.getMovies().get(choice-1);
        //Choose update to what status
        if (op == 1){
            System.out.println("Current status: " + chosenMovie.getStatus());
            int statChoice;
            do {
                System.out.println("Choose new status ");
                System.out.println("1. Now Showing");
                System.out.println("2. Upcoming");
                System.out.println("3. End of Showing");
                statChoice = sc.nextInt();
            }while(statChoice <1 || statChoice > 3);
            updateLogic.updateStatus(statChoice, choice-1);
        }else if (op == 2){
            System.out.println("Current userage: " + chosenMovie.getUserAge());
            int ageChoice;
            do {
                System.out.println("Choose new user age");
                System.out.println("1. Child");
                System.out.println("2. Student");
                System.out.println("3. Adult");
                ageChoice = sc.nextInt();
            }while(ageChoice <1 || ageChoice > 3);
            updateLogic.updateUserAge(ageChoice, choice-1);
        }
    }
}
