package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

/**
 * SBI-01 A class implementing the IDataStorage interface It is used to hold
 * fake data so other classes can implement methods before real data is
 * available.
 *
 * @author Ricky Zhao
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public class FakeDataStorage implements IDataStorage {

    private List<Movie> _movies = new ArrayList<Movie>();
    private List<Food> _foods = new ArrayList<Food>();
    private List<Seat> _seats = new ArrayList<Seat>();
    private List<Theater> _theaters = new ArrayList<Theater>();
    private List<Showing> _showings = new ArrayList<Showing>();

    DateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public FakeDataStorage() {
        try {
            _movies.add(new Movie("This is Spinal Tap", _dateFormat.parse("1984-03-02")));
            _movies.add(new Movie("Airplane", _dateFormat.parse("1980-07-02")));
            _movies.add(new Movie("The Notebook", _dateFormat.parse("1980-07-02")));    // Definitely did not cry

            _foods.add(new Food(Food.FoodType.POPCORN, 4.99, Food.FoodSize.S));
            _foods.add(new Food(Food.FoodType.POPCORN, 5.99, Food.FoodSize.M));
            _foods.add(new Food(Food.FoodType.POPCORN, 6.99, Food.FoodSize.L));
            _foods.add(new Food(Food.FoodType.SODA, 2.50, Food.FoodSize.S));
            _foods.add(new Food(Food.FoodType.SODA, 2.99, Food.FoodSize.M));
            _foods.add(new Food(Food.FoodType.SODA, 3.50, Food.FoodSize.L));

            _seats.add(new Seat("A", 1));
            _seats.add(new Seat("A", 2));
            _seats.add(new Seat("A", 3));
            _seats.add(new Seat("A", 4));
            _seats.add(new Seat("A", 5));
            _seats.add(new Seat("B", 1));
            _seats.add(new Seat("B", 2));
            _seats.add(new Seat("B", 3));

            _theaters.add(new Theater((ArrayList<Seat>) _seats));
            _theaters.get(0).setName("A");

            _showings.add(new Showing("8PM", _movies.get(0), _theaters.get(0), 14.99));
            _showings.add(new Showing("9PM", _movies.get(1), _theaters.get(0), 14.99));
            _showings.add(new Showing("9PM", _movies.get(2), _theaters.get(0), 14.99));
            _showings.get(0).setSoldOut(true); // Set spinal tap to unavailable

        } catch (ParseException e) {
            System.out.println("Bad date format");
        }
    }

    @Override
    public List<Movie> getMovies() {
        return _movies;
    }

    @Override
    public List<Food> getFoods() {
        return _foods;
    }

    @Override
    public List<Seat> getSeats() {
        return _seats;
    }

    @Override
    public List<Theater> getTheaters() {
        return _theaters;
    }

    @Override
    public List<Showing> getShowings() {
        return _showings;
    }
}
