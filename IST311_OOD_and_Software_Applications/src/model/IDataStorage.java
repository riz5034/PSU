package model;

import java.util.List;

/**
 * SBI-01 An interface for a data storage It will be used to create data
 * storages
 *
 * @author Ricky Zhao <riz5034@psu.edu>
 * @author Shakhawat Hossain <sxh599@psu.edu>
 */
public interface IDataStorage {

    public List<Movie> getMovies();
    public List<Food> getFoods();
    public List<Seat> getSeats();
    public List<Theater> getTheaters();
    public List<Showing> getShowings();
}
