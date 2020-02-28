package Staff;

import Movie.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * handles input of new movie addition
 */
public class AddInterface {
    Scanner sc = new Scanner(System.in);
    MovieCollection movieCollection = MovieCollection.getInstance();

    /**
     * main running program of this class
     */
    public void start(){
        String title;
        String director;
        String synopsis;
        String userAge;
        MovieStatus status;
        int numOfCast;
        int duration;
        ArrayList<String> castsInput = new ArrayList<String>() ;
        System.out.println("Enter title: ");
        title = sc.nextLine();
        boolean flag = true;
        for(MovieClass movie : movieCollection.getMovies()){
            if(movie.getTitle().compareTo(title) == 0) {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println("Enter director: ");
            director = sc.nextLine();

            do {
                System.out.println("Enter number of casts: ");
                numOfCast = sc.nextInt();
                sc.nextLine();
            } while (numOfCast < 1);

            for (int i = 0; i < numOfCast; i++) {
                System.out.printf("Enter cast %d: ", i + 1);
                String tmp = sc.nextLine();
                castsInput.add(tmp);
            }
            int statChoice;
            do {
                System.out.println("Choose status ");
                System.out.println("1. Now Showing");
                System.out.println("2. Upcoming");
                System.out.println("3. End of Showing");
                statChoice = sc.nextInt();
                sc.nextLine();
                if (statChoice < 1 || statChoice > 3)
                    System.out.println("Error choice!");
            }while(statChoice < 1 || statChoice > 3);
            if (statChoice == 1)
                status = new StatusNowShowing();
            else if (statChoice == 2)
                status = new StatusUpcoming();
            else
                status = new StatusEndShowing();
            System.out.println("Enter synopsis: ");
            synopsis = sc.nextLine();
            System.out.println("Enter user age: ");
            userAge = sc.nextLine().toUpperCase();
            do {
                System.out.println("Enter duration: ");
                duration = sc.nextInt();
                sc.nextLine();
                if (duration <= 0 )
                    System.out.println("Invalid duration!");
            }while(duration <= 0);
            movieCollection.addMovie(castsInput, title, director, synopsis, status, userAge, duration);
            return;
        }else{
            System.out.println("Movie already exists! Cannot create the movie");
        }
    }
}
