package Staff;

import Movie.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

/**
 * class to handle appropriate method calling of update features
 */
public class EditLogic {
    CineplexCollection cineplexCollection = CineplexCollection.getInstance();

    /**
     * handles adding new cinema to cineplex
     * @param choice ID of cineplex to be updated
     * @param newName name of cinema to be added
     * @param newType type of cinema to be added
     * @param basePrice base price of cinema to be added
     * @throws Exception if input cinema is invalid in any way
     */
    public void addCinemaToCineplex(int choice, String newName, int newType, double basePrice) throws Exception{
        ArrayList<Cineplex> availableCineplex = cineplexCollection.getCineplex();
        Cineplex cineplexToModify = availableCineplex.get(choice-1);
        if (basePrice <= 0) {
            throw new Exception("Irregular baseprice!");
        }
        if (newType < 1 || newType > 3) {
            throw new Exception("Invalid type input!");
        }
        else{
            for (Cinema c : cineplexToModify.getCinemas()){
                if (c.getName().compareTo(newName) == 0)
                    throw new Exception("A same cinema with the same name is already in the cineplex");
            }
            CinemaType cinemaType;
            switch (newType){
                case 2:
                    cinemaType = new CinemaGold();
                    break;
                case 3:
                    cinemaType = new CinemaPlatinum();
                    break;
                default:
                    cinemaType = new CinemaSilver();
            }
            Cinema c = new Cinema(newName,cinemaType,basePrice);
            cineplexCollection.updateCineplex(choice-1, cineplexToModify);
            cineplexToModify.addCinema(c);
        }
    }

    /**
     * Gets the date where there is a movie;
     */

    public ArrayList<LocalDate> displayShowInCinema(int chosenCineplexIdx, int chosenCinemaIdx){
        Cineplex cineplex = cineplexCollection.getCineplex().get(chosenCineplexIdx-1);
        Cinema cinema = cineplex.getCinemas().get(chosenCinemaIdx-1);
        ArrayList<LocalDate> datesWithMovies = new ArrayList<LocalDate>();
        for(Map.Entry<LocalDate, ArrayList<MovieShowing>> e: cinema.getMovieShowlist().entrySet()){
            if (!e.getValue().isEmpty())
                datesWithMovies.add(e.getKey());
        }
        return datesWithMovies;
    }

    /**
     * gets the showing movie in input cinema
     * @param chosenCineplexIdx ID of input cineplex
     * @param chosenCinemaIdx ID of input cinema
     * @param ld date of movie search
     * @return list of movies showing in the cinema
     */
    public ArrayList<MovieShowing> getShowingMovies(int chosenCineplexIdx, int chosenCinemaIdx, LocalDate ld){
        Cineplex cineplex = cineplexCollection.getCineplex().get(chosenCineplexIdx-1);
        Cinema cinema = cineplex.getCinemas().get(chosenCinemaIdx-1);
        return cinema.getMovieShowlist().get(ld);
    }

    /**
     * remove movie from a cinema
     * @param chosenCineplexIdx ID of input cineplex
     * @param chosenCinemaIdx ID of input cinema
     * @param ld date of movie showing
     * @param removeIndex index of movie to be removed
     */
    public void removeMovie(int chosenCineplexIdx, int chosenCinemaIdx, LocalDate ld, int removeIndex){
        Cineplex cineplex = cineplexCollection.getCineplex().get(chosenCineplexIdx-1);
        Cinema cinema = cineplex.getCinemas().get(chosenCinemaIdx-1);
        ArrayList<MovieShowing> updatedList = getShowingMovies(chosenCineplexIdx,chosenCinemaIdx, ld);
        updatedList.remove(removeIndex);
        cinema.updateMovieShowlist(ld, updatedList);
        cineplex.updateCinema(cinema, chosenCinemaIdx-1);
        cineplexCollection.updateCineplex(chosenCineplexIdx-1, cineplex);
    }

    /**
     * convert time from string input
     * @param s string input containing time
     * @return hour and minute converted
     * @throws Exception if input string is not a valid time format
     */
    private LocalTime parseTime(String s) throws Exception{
        String hour = s.substring(0,2);
        String minute = s.substring(3);
        int intHour = Integer.parseInt(hour);
        int intMinute = Integer.parseInt(minute);
        if (intHour < 0 || intHour > 23 || intMinute < 0 || intMinute > 59)
            throw new Exception("Invalid time!");
        return LocalTime.of(intHour, intMinute);
    }

    /**
     * convert date from string input
     * @param s string input containing date
     * @return month and date converted
     * @throws Exception if input string is not a valid date format
     */
    private LocalDate parseDate(String s) throws Exception{
        String month = s.substring(0,2); int intMonth = Integer.parseInt(month);
        String day = s.substring(3,5); int intDay = Integer.parseInt(day);
        String year = s.substring(6); int intYear = Integer.parseInt(year);
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.of(intYear, intMonth, intDay);
        }catch(Exception e) {
            throw new Exception("Invalid date!");
        }
        return parsedDate;
    }

    /**
     * checks if there is already any movie showing in the new movie timeslot
     * @param list showing movie in the target cinema
     * @param startTime start time of the movie
     * @param endTime end time of the movie
     * @return if there is any movie clash in that time slot
     */
    public boolean checkScheduleClash(ArrayList<MovieShowing> list, LocalTime startTime, LocalTime endTime){
        for(MovieShowing m : list){
            LocalTime bookedStartTime = m.getStartTime();
            LocalTime bookedEndTime = m.getEndTime();
            if (startTime.compareTo(bookedStartTime) >= 0 && startTime.compareTo(bookedEndTime) <= 0) {
            	return true;
            }
            else if (endTime. compareTo(bookedStartTime) >= 0 && endTime.compareTo(bookedEndTime) <= 0)
            {
            	return true;
            	
            }
            else if (startTime.compareTo(bookedStartTime) <= 0 && endTime.compareTo(bookedEndTime) >= 0)
                {
            	return true;
                }
        }
        return false;
    }

    /**
     * inserts new movie showing into a cinema
     * @param chosenCineplexIdx ID of cineplex to be inserted into
     * @param chosenCinemaIdx ID of cinema to be inserted into
     * @param movie movie to be inserted into showing list
     * @param date date of the movie to be inserted
     * @param time time of the movie to be inserted
     * @throws Exception if the inserted movie is not valid or there are time slot clash
     */
    public void insertMovieToCinema(int chosenCineplexIdx, int chosenCinemaIdx, MovieClass movie, String date, String time) throws Exception{
        if (!movie.getStatusClass().isSchedulable()) throw new Exception("Movie is not able to be scheduled yet!");

        LocalTime chosenStartTime = parseTime(time);
        LocalDate chosenDate = parseDate(date);
        Cineplex cineplex = cineplexCollection.getCineplex().get(chosenCineplexIdx-1);
        Cinema cinema = cineplex.getCinemas().get(chosenCinemaIdx-1);
        ArrayList<MovieShowing> movieShowings = new ArrayList<>();
        MovieShowing newShow = new MovieShowing(cinema, movie, chosenDate, chosenStartTime);

        if (cinema.getMovieShowlist().containsKey(chosenDate)){
            movieShowings = cinema.getMovieShowlist().get(chosenDate);
            if (!checkScheduleClash(movieShowings, chosenStartTime, chosenStartTime.plus(movie.getMinutesDuration(), ChronoUnit.MINUTES)))
            	
                movieShowings.add(newShow);
            else
                throw new Exception("Schedule clashed!");
        }else
            movieShowings.add(newShow);

        cinema.updateMovieShowlist(chosenDate, movieShowings);
    }

    public void insertMovieToCinema(int chosenCineplexIdx, int chosenCinemaIdx, MovieShowing movie, String date, String time) throws Exception {
        MovieCollection movieCollection = MovieCollection.getInstance();
        for(MovieClass m : movieCollection.getMovies()){
            if (m.getTitle().compareTo(movie.getMovieTitle()) == 0){
                insertMovieToCinema(chosenCineplexIdx, chosenCinemaIdx, m, date, time);
            }
        }
    }
}
