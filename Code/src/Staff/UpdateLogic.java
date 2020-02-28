package Staff;

import Movie.*;

/**
 * class that handles updating of info data
 */
public class UpdateLogic {
    MovieCollection movieCollection = MovieCollection.getInstance();

    /**
     * method that handles updating of user Age class
     * @param newUserAge new watcher age rating
     * @param chosenMovieIdx  ID of movie to be edited
     */
    public void updateUserAge(String newUserAge, int chosenMovieIdx){
        MovieClass chosenMovie = movieCollection.getMovies().get(chosenMovieIdx);
        chosenMovie.setUserAge(newUserAge);
        movieCollection.updateMovieCollection(chosenMovie, chosenMovieIdx);
    }
    /**
     * method that handles updating of user Age class
     * @param option new watcher age rating
     * @param chosenMovieIdx  ID of movie to be edited
     */
    public void updateUserAge(int option, int chosenMovieIdx){
        String s;
        if (option == 1)
            s = "Child";
        else if (option == 2)
            s ="Student";
        else
            s = "Adult";
        updateUserAge(s, chosenMovieIdx);
    }

    /**
     * method that handles updating of movie status in movie list
     * @param movieStatus new movie status
     * @param chosenMovieIdx ID of movie to be updated
     */
    public void updateStatus(MovieStatus movieStatus, int chosenMovieIdx){
        MovieClass chosenMovie = movieCollection.getMovies().get(chosenMovieIdx);
        chosenMovie.setMovieStatus(movieStatus);
        movieCollection.updateMovieCollection(chosenMovie, chosenMovieIdx);
    }

    /**
     * method that handles updating of movie status in movie list
     * @param statusOption new movie status
     * @param chosenMovieIdx ID of movie to be updated
     */
    public void updateStatus(int statusOption, int chosenMovieIdx){
        MovieStatus movieStatus;
        if (statusOption == 1)
            movieStatus = new StatusNowShowing();
        else if (statusOption == 2)
            movieStatus = new StatusUpcoming();
        else
            movieStatus = new StatusEndShowing();
       updateStatus(movieStatus,chosenMovieIdx);
    }
}
