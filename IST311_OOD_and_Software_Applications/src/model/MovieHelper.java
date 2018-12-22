package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.FakeDataStorage;
import model.IDataStorage;
import model.Movie;
import model.Showing;

/**
 * SBI-02 A helper class related to tasks of a movie It will hold methods that
 * technically are categories as the "view" aspect of a movie That will help us
 * keep the view separate from the model
 *
 * @author Phil O'Connell <pxo4@psu.edu>
 * @author Ricky Zhao <riz5034@psu.edu>
 */
public class MovieHelper {

    /**
     * Return a string having the movie title, followed by the year it was
     * released in parentheses e.g., "Airplane (1980)"
     *
     * @param movie
     * @return the movie title and release year
     * @author Phil O'Connell <pxo4@psu.edu>
     */
    public static String TitleAndReleaseYear(Movie movie) {
        SimpleDateFormat FMT_YEAR = new SimpleDateFormat("yyyy");
        String title = movie.getTitle();
        String year = FMT_YEAR.format(movie.getReleaseDate());

        return String.format("%s (%s)", title, year);
    }

    /**
     * SBI-02
     * Return a list of movies that contain the substring
     * @param substring the substring of the movie
     * @return the list of movies containing the substring
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public static List<Movie> searchTitle(String substring) {
        List<Movie> foundMovies = new ArrayList<>();
        IDataStorage _movieStorage = new FakeDataStorage();

        for (Movie movie : _movieStorage.getMovies()) {
            if (movie.getTitle().toLowerCase().contains(substring.toLowerCase())) {
                foundMovies.add(movie);
            }
        }
        
        for(Movie movie : foundMovies) {
            System.out.print("Movie: " + movie.getTitle());
            System.out.println("\tRelease Date: " + movie.getReleaseDate());
            System.out.println("");
        }

        return foundMovies;
    }

    /**
     * Return the list of movies
     * @return the list of movies
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public static List<Movie> getAll() {
        IDataStorage _movieStorage = new FakeDataStorage();
        for(Movie movie : _movieStorage.getMovies()) {
            System.out.print("Movie: " + movie.getTitle());
            System.out.print("\tRelease Date: " + movie.getReleaseDate());
            System.out.println("");
        }
        
        return _movieStorage.getMovies();
    }
    
    
    public static List<Showing> getShowing(Movie movie) {
        List<Showing> movieShowings = new ArrayList<Showing>();
        List<Showing> _showings = new FakeDataStorage().getShowings();
        
        for(Showing showing : _showings) {
            if((showing.getMovie().getTitle() == movie.getTitle() 
                    && (showing.getMovie().getReleaseDate().compareTo(movie.getReleaseDate()) == 0))) {
                movieShowings.add(showing);
                // Display - time, movie, theater, cost
                System.out.println("Showing: " + showing.getTime() + "\tMovie: " + showing.getMovie().getTitle() 
                        + "\tTheater: " + showing.getTheater().getName() + "\tCost: $" + showing.getCost());
            }
        }
        
        return movieShowings;
    }
    
    /**
     * SBI-14, SBI-15
     * Returns a list of showings containing a given word
     * @param substring the substring to search
     * @return the list of showings
     * 
     * @author Ricky Zhao <riz5034@psu.edu>
     */
    public static List<Showing> searchShowing(String substring) {
        List<Showing> showings = new FakeDataStorage().getShowings();
        List<Showing> foundShowings = new ArrayList<Showing>();

        for(Showing showing : showings) {
            if(showing.getMovie().getTitle().toLowerCase().contains(substring.toLowerCase())) {
                foundShowings.add(showing);
            }
        }
        
        // Print time, movie, theater, and cost
        for(Showing showing : foundShowings) {
            System.out.print("Time: " + showing.getTime());
            System.out.print("\tMovie: " + showing.getMovie().getTitle());
            System.out.print("\tTheater: " + showing.getTheater().getName());
            System.out.println("\tCost: " + showing.getCost());
            
        }
        
        return foundShowings;
    }
}
